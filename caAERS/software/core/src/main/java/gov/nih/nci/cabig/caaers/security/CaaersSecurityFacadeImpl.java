package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.RoleMembership;
import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.dao.security.RolePrivilegeDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;
import gov.nih.nci.cabig.caaers.domain.repository.UserRepository;
import gov.nih.nci.cabig.caaers.utils.ObjectPrivilegeParser;
import gov.nih.nci.cabig.caaers.utils.el.EL;
import gov.nih.nci.cabig.ctms.suite.authorization.ProvisioningSessionFactory;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.util.StringUtilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
                                     " where o.nciInstituteCode in (:identifiers)";
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
				privilegedRoles = getRolesFromRolePrivilegeMapping(objectId,
						privilege);				
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
	private List<String> getRolesFromRolePrivilegeMapping(String objectId,
			String privilege) {
		
		List<String> privilegedRoles = CSMCacheManager.getRolesFromCache(objectId, privilege);
		if (privilegedRoles==null) {		
			privilegedRoles = rolePrivilegeDao.getRoles(objectId, privilege);
			if (privilegedRoles!=null) {
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
        List<IndexEntry> entries = new ArrayList<IndexEntry>();
        
        User user = userRepository.getUserByLoginName(userName);
        for(RoleMembership roleMembership : user.getRoleMembershipMap().values()){

           IndexEntry entry = new IndexEntry(roleMembership.getRole());
           entries.add(entry);
           if(roleMembership.isGlobalScoped()) continue;
           if((roleMembership.isAllSite() && roleMembership.isAllStudy()) ||
              (roleMembership.isSiteScoped() && roleMembership.isAllSite()) ){
              //can access all studies
              entry.getEntityIds().add(ALL_IDS_FABRICATED_ID);
              continue;
           }
           if(roleMembership.isStudyScoped() && !roleMembership.isAllStudy()){
              //can access only specific studies
              if(CollectionUtils.isNotEmpty(roleMembership.getStudyIdentifiers())){
                  List<String> studyIdentifiers = new ArrayList<String>(roleMembership.getStudyIdentifiers());
                  List<Integer> studyIds = getStudyIdsByIdentifiersFromDB(studyIdentifiers);
                  if(studyIds != null) entry.getEntityIds().addAll(studyIds);
                  continue;
              }
           }
           //site-scoped specific sites OR study-scoped all-study roles can access all studies associated to specified organizations.
           if(CollectionUtils.isNotEmpty(roleMembership.getOrganizationNCICodes())){
               List<String> orgIdentifiers = new ArrayList<String>(roleMembership.getOrganizationNCICodes());
               List<Integer> studyIds = getStudyIdsByOrganizationNCICodesFromDB(orgIdentifiers);
               if(studyIds != null) entry.getEntityIds().addAll(studyIds);
           }
        }

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
        List<IndexEntry> entries = new ArrayList<IndexEntry>();
        User user = userRepository.getUserByLoginName(userName);
        for(RoleMembership roleMembership : user.getRoleMembershipMap().values()){
            IndexEntry entry = new IndexEntry(roleMembership.getRole());
            entries.add(entry);
            if(roleMembership.isGlobalScoped()) continue;
            if(roleMembership.isAllSite()) {
                entry.getEntityIds().add(ALL_IDS_FABRICATED_ID); //can access all sites
                continue;
            }
            //only the ones mentioned in NCI codes
            if(CollectionUtils.isNotEmpty(roleMembership.getOrganizationNCICodes())){
                List<String> orgIdentifiers = new ArrayList<String>(roleMembership.getOrganizationNCICodes());
                List<Integer> orgIds = getOrganizationIdsByIdentifiersFromDB(orgIdentifiers);
                if(orgIds != null) entry.getEntityIds().addAll(orgIds);
            }

        }


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
		log.debug("IN clearUserCache - " + userName);
		if (StringUtilities.isBlank(userName)) {
			return;
		}
        
		User user = userRepository.getUserByLoginName(userName);
		gov.nih.nci.security.authorization.domainobjects.User csmUser = user.getCsmUser();
        if(csmUser == null) return;

		Long id = csmUser.getUserId();
		if (id != null ) {

			String loginId = id.toString();

			//loginId is cacheKey , remove the cache for that user .. 
			CacheManager cacheManager = CSMCacheManager.getCacheManager() ;
			
			Cache cache = cacheManager.getCache(loginId);
			if (cache == null) {
				return;
			} else {
				cacheManager.removeCache(loginId);
				log.debug("Cleared cache for user - " + userName);
			}
		}
		return;
			
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