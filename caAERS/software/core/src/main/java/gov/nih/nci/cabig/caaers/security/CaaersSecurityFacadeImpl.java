/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.RoleMembership;
import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.dao.security.RolePrivilegeDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;
import gov.nih.nci.cabig.caaers.domain.repository.UserRepository;
import gov.nih.nci.cabig.caaers.utils.ObjectPrivilegeParser;
import gov.nih.nci.cabig.caaers.utils.el.EL;
import gov.nih.nci.cabig.ctms.suite.authorization.ProvisioningSessionFactory;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.util.StringUtilities;
import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;

/**
 * The Facade Layer to CSM. 
 * @author: Biju Joseph
 */
public class CaaersSecurityFacadeImpl implements CaaersSecurityFacade  {

	protected static CaaersSecurityFacade instance;
    protected final Log log = LogFactory.getLog(getClass());
	private UserRepository userRepository;
	private UserProvisioningManager userProvisioningManager;
	private RolePrivilegeDao rolePrivilegeDao;
	private ProvisioningSessionFactory provisioningSessionFactory;

    private String orgIdFetchHQL = "select distinct o.id from Organization o " +
                                    " where o.nciInstituteCode in (:identifiers)";

    private String studyIdFetchHQL =" select distinct s.id from Study s " +
                                    " join s.identifiers as identifier " +
                                    " where identifier.value in (:identifiers)";
    private String studyIdByOrgNCICodeHQL =" select distinct s.id from Study s " +
                                     " join s.studyOrganizations so " +
                                     " join so.organization o " +
                                     " where so.retiredIndicator = false and " +
                                     " o.nciInstituteCode in (:identifiers)";

    //all organizations conducting the specific studies
    private String studySiteHQL = new StringBuilder("select distinct so.organization.id from StudyOrganization so ")
            .append("where so.study.id in (:studyIds) ")
            .append("and so.retiredIndicator <> true ").toString();

    //sites that the specified organizations are managing
    private String allStudySiteOrgIdHQL = new StringBuilder("select distinct ss.organization.id from StudyOrganization so, StudySite ss ")
            .append("where ss.study = so.study ")
            .append("and so.organization.id in (:orgIds) ")
            .append("and ss.retiredIndicator <> true ")
            .append("and so.retiredIndicator <> true ")
            .append("and so.class in ('SFS', 'SCC') ").toString();

    //site the specified organizations are managing - which belongs to a specific set of study
    private String studySiteOrgIdHQL = new StringBuilder("select distinct ss.organization.id from StudyOrganization so, StudySite ss ")
            .append("where ss.study = so.study ")
            .append("and so.organization.id in (:orgIds) ")
            .append("and ss.retiredIndicator <> true ")
            .append("and so.retiredIndicator <> true ")
            .append("and so.study.id in (:studyIds) ")
            .append("and so.class in ('SFS', 'SCC') ").toString();

    public CaaersSecurityFacadeImpl() {
        instance = this;
    }
	public static Integer ALL_IDS_FABRICATED_ID = Integer.MIN_VALUE;


	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade#getRoles(java.lang.String, gov.nih.nci.cabig.caaers.domain.Study)
	 */
    //BJ : refactored to use SuiteRoleMembership
	public Collection<String> getRoles(String userName, Study study) {
		Set<String> roles = new HashSet<String>();
		User user = userRepository.getUserByLoginName(userName);
        String studyCCIdentifier = study.getCoordinatingCenterIdentifier().getValue();
        for(RoleMembership roleMembership : user.getRoleMembershipMap().values()){
            if(roleMembership.isGlobalScoped()) continue; //not scoped at study level
            if(roleMembership.isSiteScoped() && roleMembership.isAllSite()){
                roles.add(roleMembership.getRole().getCsmName()); //all study access on all sites
                continue;
            }
            if(roleMembership.isStudyScoped() && !roleMembership.isAllStudy()){
                //only if study coordinating center identifier is present in the list of study-identifiers in membership. 
                if(roleMembership.getStudyIdentifiers().contains(studyCCIdentifier) ){
                    roles.add(roleMembership.getRole().getCsmName());
                    continue;
                }
            }

            //siteScoped-non-all-site and  studyScoped-all-study roles
            //can access the study if the site is an active study organization is present in membership
            lblNci:
            for(StudyOrganization so : study.getActiveStudyOrganizations()){
                String orgNCICode = so.getOrganization().getNciInstituteCode();
                if(roleMembership.getOrganizationNCICodes().contains(orgNCICode)){
                    roles.add(roleMembership.getRole().getCsmName());
                    break lblNci;
                }
            }


        }

		return roles;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade#getRoles(java.lang.String, gov.nih.nci.cabig.caaers.domain.Organization)
	 */
    //BJ : Refactored to use SuiteRoleMembership
	public Collection<String> getRoles(String userName, Organization org) {
		Set<String> roles = new HashSet<String>();
		User user = userRepository.getUserByLoginName(userName);
        for(RoleMembership roleMembership : user.getRoleMembershipMap().values()){
            if(roleMembership.isGlobalScoped()) continue; //not scoped role
            if(roleMembership.isAllSite()){
                roles.add(roleMembership.getRole().getCsmName()); //all site access
            }else{
                //specific site access
                if(roleMembership.getOrganizationNCICodes().contains(org.getNciInstituteCode())){
                    roles.add(roleMembership.getRole().getCsmName());
                }
            }
        }
		return roles;
	}

    /**
     * Will check the authorization status
     *
     * @param auth            - An authentication object
     * @param objectPrivilege - An object privilege (Eg: - "Study:READ || Study:UPDATE)
     * @return
     */
    public boolean checkAuthorization(Authentication auth, String objectPrivilege) {
    	//Return true id caaers_super_user
    	if(SecurityUtils.checkAuthorization(auth, UserGroupType.caaers_super_user)){
    		return true;
    	}
    	
        String op = objectPrivilege;
        ObjectPrivilegeParser p = new ObjectPrivilegeParser(objectPrivilege);
        for (String[] s : p.getDomainObjectPrivileges()) {
            String domainObject = s[0];
            String privilege = s[1];
            boolean isAuth = checkAuthorization(auth, domainObject, privilege);
            op = op.replace(domainObject + ":" + privilege, String.valueOf(isAuth));
        }
        EL el = new EL();
        String s = el.evaluate("${" + op + "}");
        boolean isAuth = Boolean.parseBoolean(s);
        return isAuth;
    }

    /**
     * Will check the authorization status.
     *
     * @param authentication      - The acegi authentication object
     * @param objectId  - The secure object Id
     * @param privilege - The privilege (CREATE,UPDATE)
     * @return
     */
    public boolean checkAuthorization(Authentication authentication, String objectId, String privilege) {
    	//Return true id caaers_super_user
    	if(SecurityUtils.checkAuthorization(authentication, UserGroupType.caaers_super_user)){
    		return true;
    	}

    	//Fetch all the roles of the logged in user.
		//Granted Authorities is populated when user is authenticated. 
		try{
			GrantedAuthority[] authorities = authentication.getAuthorities();
			List<String> privilegedRoles;
			if(authorities  != null){
				//Fetch all the roles which have the given privilege on the given objectId
				privilegedRoles = getRolesFromRolePrivilegeMapping(objectId, privilege);
				if(privilegedRoles != null){
					for(int i=0;i < authorities.length;i++){
						if(privilegedRoles.contains(authorities[i].getAuthority())){
							return true;
						}
					}					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;

    }

	/**
	 * @param objectId
	 * @param privilege
	 * @return
	 */
	private List<String> getRolesFromRolePrivilegeMapping(String objectId, String privilege) {
		List<String> privilegedRoles = CSMCacheManager.getRolesFromCache(objectId, privilege);
		if (privilegedRoles==null || privilegedRoles.isEmpty()) {
			privilegedRoles = rolePrivilegeDao.getRoles(objectId, privilege);
			if (privilegedRoles!=null || !privilegedRoles.isEmpty()) {
				CSMCacheManager.addRolePrivilegeMappingToCache(objectId, privilege, privilegedRoles);
			}
		}
		return privilegedRoles;
	}
    

    /**
     * Will return all the Study accessible to the user.
     *
     * @param userName - the loginId of the user
     * @return
     */
    //BJ - Refactored to use the RoleMembership
    public List<IndexEntry> getAccessibleStudyIds(String userName){
        Map<Integer, IndexEntry> indexMap = new HashMap<Integer, IndexEntry>() ;
        IndexEntry allSiteIndexEntry = new IndexEntry(ALL_IDS_FABRICATED_ID,0);

        List<IndexEntry> entries = new ArrayList<IndexEntry>();
        
        User user = userRepository.getUserByLoginName(userName);
        for(RoleMembership roleMembership : user.getRoleMembershipMap().values()){
            if(roleMembership.isGlobalScoped()) continue;

             //is all site all study ? - all study
            if(roleMembership.isAllSite() && roleMembership.isAllStudy()) {
                allSiteIndexEntry.addRole(roleMembership.getRole()); continue;
            }
            //is site scoped and & all site ? - all study
            if(roleMembership.isSiteScoped() && roleMembership.isAllSite()){
                allSiteIndexEntry.addRole(roleMembership.getRole()); continue;
            }

            //Study Scoped - specific studies - //can access only specific studies
            if(roleMembership.isStudyScoped() && !roleMembership.isAllStudy()) {

                if(CollectionUtils.isEmpty(roleMembership.getStudyIdentifiers())) continue;
                List<String> studyIdentifiers = new ArrayList<String>(roleMembership.getStudyIdentifiers());
                List<Integer> studyIds = getStudyIdsByIdentifiersFromDB(studyIdentifiers);
                if(CollectionUtils.isEmpty(studyIds)) continue;
                for(Integer studyId : studyIds){
                    if(!indexMap.containsKey(studyId)) indexMap.put(studyId, new IndexEntry(studyId,0));
                    indexMap.get(studyId).addRole(roleMembership.getRole());
                }
                continue;

           }

           //Site scoped but specific studies ? -  can access all the studies this organization is part of.
           //Study scoped  - all Studies ?   -  can access all the studies this organization is part of.
           //Any other case - can access all the studies this organization is part of.
           if(CollectionUtils.isEmpty(roleMembership.getOrganizationNCICodes())) continue;
           List<String> orgIdentifiers = new ArrayList<String>(roleMembership.getOrganizationNCICodes());
           List<Integer> studyIds = getStudyIdsByOrganizationNCICodesFromDB(orgIdentifiers);
           if(CollectionUtils.isEmpty(studyIds)) continue;
           for(Integer studyId : studyIds){
                if(!indexMap.containsKey(studyId)) indexMap.put(studyId, new IndexEntry(studyId,0));
                indexMap.get(studyId).addRole(roleMembership.getRole());
           }

        } //for


        if(allSiteIndexEntry.hasRoles()){
            entries.add(allSiteIndexEntry);
        }
        entries.addAll(indexMap.values());
        return entries;
    }
	

    /**
     * Will return all the Organization accessible to the user.
     * 
     * @param userName - the loginId of the user
     * @return
     */
    //BJ - Refactored to use the RoleMembership
    public List<IndexEntry> getAccessibleOrganizationIds(String userName){
        Map<Integer, IndexEntry> indexMap = new HashMap<Integer, IndexEntry>() ;
        IndexEntry allSiteIndexEntry = new IndexEntry(ALL_IDS_FABRICATED_ID,0);

        User user = userRepository.getUserByLoginName(userName);
        for(RoleMembership roleMembership : user.getRoleMembershipMap().values()){
            if(roleMembership.isGlobalScoped()) continue;


            //if site scoped and all site ?
            //if study scoped and all site all study ?
            if ( (roleMembership.isSiteScoped() && roleMembership.isAllSite()) ||
                 (roleMembership.isStudyScoped() && roleMembership.isAllSite() && roleMembership.isAllStudy())){
                allSiteIndexEntry.addRole(roleMembership.getRole()); continue;
            }



            //can access all organizations where the specified studies are conducted.
            if(roleMembership.isStudyScoped() && roleMembership.isAllSite() && !roleMembership.isAllStudy()){
                if(CollectionUtils.isEmpty(roleMembership.getStudyIdentifiers())) continue;
                List<String> studyIdentifiers = new ArrayList<String>(roleMembership.getStudyIdentifiers());
                List<Integer> studyIds = getStudyIdsByIdentifiersFromDB(studyIdentifiers);
                if(CollectionUtils.isEmpty(studyIds)) continue;

                HQLQuery query = new HQLQuery(studySiteHQL);
                query.setParameterList("studyIds", studyIds);
                List<Integer> additionalOrgIds = (List<Integer>) search(query);
                if (CollectionUtils.isEmpty(additionalOrgIds)) continue;
                for(Integer orgId : additionalOrgIds ){
                    if(!indexMap.containsKey(orgId)) indexMap.put(orgId, new IndexEntry(orgId,0));
                    indexMap.get(orgId).addRole(roleMembership.getRole());
                }
                continue;
            }



            //site scoped ? - specific sites  - can access also transitive study based dependencies
            //study scoped ? - specific sites and all study - also transitive based on the study
            //study scoped ? - specific sites and specific study -  transitive based on the specific study
            if(CollectionUtils.isEmpty(roleMembership.getOrganizationNCICodes())) continue;
            List<String> orgIdentifiers = new ArrayList<String>(roleMembership.getOrganizationNCICodes());
            List<Integer> orgIds = getOrganizationIdsByIdentifiersFromDB(orgIdentifiers);
            if(CollectionUtils.isEmpty(orgIds)) continue;
            for(Integer orgId : orgIds){
                if(!indexMap.containsKey(orgId)) indexMap.put(orgId, new IndexEntry(orgId,0));
                indexMap.get(orgId).addRole(roleMembership.getRole());
            }

            //workout the transitive dependencies
            List<Integer> additionalOrgIds = null;
            if ( (roleMembership.isSiteScoped() && !roleMembership.isAllSite())  ||  (roleMembership.isStudyScoped() && roleMembership.isAllStudy()) )   {
                //for all the study sites these organizations manage
                HQLQuery query = new HQLQuery(allStudySiteOrgIdHQL);
                query.setParameterList("orgIds", orgIds);
                additionalOrgIds = (List<Integer>) search(query);
            }

            if(roleMembership.isStudyScoped() && !roleMembership.isAllStudy()){
                //for specific study sites belonging to the study that this organizations manage.
                if(CollectionUtils.isEmpty(roleMembership.getStudyIdentifiers())) continue;
                List<String> studyIdentifiers = new ArrayList<String>(roleMembership.getStudyIdentifiers());
                List<Integer> studyIds = getStudyIdsByIdentifiersFromDB(studyIdentifiers);
                if(CollectionUtils.isEmpty(studyIds)) continue;

                HQLQuery query = new HQLQuery(studySiteOrgIdHQL);
                query.setParameterList("orgIds", orgIds);
                query.setParameterList("studyIds", studyIds);
                additionalOrgIds = (List<Integer>) search(query);
            }

            if(CollectionUtils.isEmpty(additionalOrgIds)) continue;
            for(Integer orgId : additionalOrgIds){
                if(!indexMap.containsKey(orgId)) indexMap.put(orgId, new IndexEntry(orgId,0));
                indexMap.get(orgId).addRole(roleMembership.getRole());
            }


        } //for


        List<IndexEntry> entries = new ArrayList<IndexEntry>();
        if(allSiteIndexEntry.hasRoles()) entries.add(allSiteIndexEntry);
        entries.addAll(indexMap.values());
        return entries;
    }

    private List<Integer> getStudyIdsByOrganizationNCICodesFromDB(List<String> orgIdentifiers){
        HQLQuery query = new HQLQuery(studyIdByOrgNCICodeHQL);
        query.setParameterList("identifiers", orgIdentifiers);
        List<Integer>  studyIds= (List<Integer>) search(query);
        return studyIds;
    }

    private List<Integer> getStudyIdsByIdentifiersFromDB(List<String> identifiers){
        HQLQuery query = new HQLQuery(studyIdFetchHQL);
        query.setParameterList("identifiers", identifiers);
        List<Integer>  studyIds= (List<Integer>) search(query);
        return studyIds;
        
    }
    
    private List<Integer> getOrganizationIdsByIdentifiersFromDB(List identifiers) {
    	List<Integer> resultList = null;

		HQLQuery query = new HQLQuery(orgIdFetchHQL);
        query.setParameterList("identifiers", identifiers);
        resultList = (List<Integer>) search(query);
		return resultList;
    }

	public void clearUserCache(String userName) {
		if(log.isDebugEnabled()) log.debug("Clearing cache for user :" + userName);
		if (StringUtilities.isBlank(userName)) {
			return;
		}
        //delegate the remove call to the CSM Cache Manager
       CSMCacheManager.removeUserFromCache(userName);
			
	}
	
	private List<?> search(final AbstractQuery query){
    	return rolePrivilegeDao.search(query);
    }

	public RolePrivilegeDao getRolePrivilegeDao() {
		return rolePrivilegeDao;
	}

	public void setRolePrivilegeDao(RolePrivilegeDao rolePrivilegeDao) {
		this.rolePrivilegeDao = rolePrivilegeDao;
	}

	public ProvisioningSessionFactory getProvisioningSessionFactory() {
		return provisioningSessionFactory;
	}

	public void setProvisioningSessionFactory(
			ProvisioningSessionFactory provisioningSessionFactory) {
		this.provisioningSessionFactory = provisioningSessionFactory;
	}

    public static CaaersSecurityFacade getInstance() {
        return instance;
    }

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void setUserProvisioningManager(
			UserProvisioningManager userProvisioningManager) {
		this.userProvisioningManager = userProvisioningManager;
	}
}
