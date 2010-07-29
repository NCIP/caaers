package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.CaaersUserProvisioningException;
import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.dao.security.RolePrivilegeDao;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.LocalInvestigator;
import gov.nih.nci.cabig.caaers.domain.LocalResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.RemoteInvestigator;
import gov.nih.nci.cabig.caaers.domain.RemoteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaffRole;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepositoryImpl;
import gov.nih.nci.cabig.caaers.utils.ObjectPrivilegeParser;
import gov.nih.nci.cabig.caaers.utils.el.EL;
import gov.nih.nci.cabig.ctms.suite.authorization.ProvisioningSession;
import gov.nih.nci.cabig.ctms.suite.authorization.ProvisioningSessionFactory;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRole;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRoleMembership;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElementPrivilegeContext;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroupRoleContext;
import gov.nih.nci.security.authorization.domainobjects.Role;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import gov.nih.nci.security.exceptions.CSTransactionException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.MailException;

/**
 * The Facade Layer to CSM. 
 * @author: Biju Joseph
 */
public class CaaersSecurityFacadeImpl implements CaaersSecurityFacade  {

    private static final String OBJ_ID_SEPARATOR = ".";
	protected static CaaersSecurityFacade instance;
    protected final Log log = LogFactory.getLog(getClass());
	private CSMUserRepositoryImpl csmUserRepository;
	private RolePrivilegeDao rolePrivilegeDao;
	private ProvisioningSessionFactory provisioningSessionFactory;
	private String ORGANIZATION_PE="HealthcareSite";
	private String STUDY_PE="Study";

    public CaaersSecurityFacadeImpl() {
        instance = this;
    }

	//Roles for which we have to make an exception in terms of scoping
	private String USER_ADMINISTRATOR = "user_administrator";
	private String PO_INFO_MANAGER = "person_and_organization_information_manager";
	
	public static Integer ALL_IDS_FABRICATED_ID = -99887766;
	
	//For all Investigators
	private String AE_REPORTER = "ae_reporter";

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade#getRoles(java.lang.String, gov.nih.nci.cabig.caaers.domain.Study)
	 */
	public Collection<String> getRoles(String userName, Study study) {
		Set<String> roles = new HashSet<String>();
		final gov.nih.nci.security.authorization.domainobjects.User user = csmUserRepository
				.getCSMUserByName(userName);

		if (user != null) {
			String loginId = user.getUserId() + "";
			// first get user's roles provisioned on this study directly
			String protGroupName = STUDY_PE + OBJ_ID_SEPARATOR
					+ study.getCoordinatingCenterIdentifier().getValue();
			roles.addAll(getRoles(userName, protGroupName));

			// need to check for the situation when the user has been
			// provisioned on all studies of his/her organization with a single
			// entry.
			Collection<String> orgWideStudyRoles = getRoles(userName, STUDY_PE);
			if (CollectionUtils.isNotEmpty(orgWideStudyRoles)) {
				for (String roleName : orgWideStudyRoles) {
					List<Integer> studyIds = getStudyIdsByRoleName(loginId,
							roleName);
					if (studyIds!=null && studyIds.contains(study.getId())) {
						roles.add(roleName);
					}
				}
			}
			
			// get an intersection of the study's and user's organizations, and collect roles on them
			// per Denis, Biju, Ion, Monish discussion on 07/01/2010.					
			List<StudyOrganization> studyOrganizations = study.getActiveStudyOrganizations();
			if (CollectionUtils.isNotEmpty(studyOrganizations)) {
				for (StudyOrganization studyOrganization : studyOrganizations) {
					Organization org = studyOrganization.getOrganization();
					if (org!=null) {
						roles.addAll(getRoles(userName, org));
					}
				}
			}
			
		}
		return roles;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade#getRoles(java.lang.String, gov.nih.nci.cabig.caaers.domain.Organization)
	 */
	public Collection<String> getRoles(String userName, Organization org) {
		Set<String> roles = new HashSet<String>();
		// first get user's roles provisioned on this organization directly
		String protGroupName = ORGANIZATION_PE + OBJ_ID_SEPARATOR + org.getNciInstituteCode();		
		roles.addAll(getRoles(userName, protGroupName));
		// now, user can be provisioned in CSM with a single entry giving him or her roles on all sites
		// so we need to check for that as well.
		roles.addAll(getRoles(userName, ORGANIZATION_PE));
		return roles;
	}

	private Collection<String> getRoles(String userName, String protectionGroupName) {
		Set<String> roles = new HashSet<String>();		
		final gov.nih.nci.security.authorization.domainobjects.User user = csmUserRepository
				.getCSMUserByName(userName);
		if (user != null) {
			try {
				String loginId = user.getUserId() + "";
				Set<ProtectionGroupRoleContext> contexts = this.getProtectionGroupRoleContextForUser(loginId);
				for (ProtectionGroupRoleContext context : contexts) {
					ProtectionGroup pe = context.getProtectionGroup();
					String caaersEquivalentName = pe.getProtectionGroupName();// call
					// SecurityObjectIdGenerator.toCaaersObjectName
					if (protectionGroupName.equals(caaersEquivalentName)) {
						Iterator it = context.getRoles().iterator();
						while (it.hasNext()) {
							Role role = (Role) it.next();
							if (SecurityUtils.isScoped(role.getName())) {
								roles.add(role.getName());
							}
						}
						
					} 
				}
			} catch (CSObjectNotFoundException e) {
				log.error(ExceptionUtils.getFullStackTrace(e), e);
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
     * Will provision user in CSM, i.e. will only populate the role associations and protection group association.
     *
     * @param user - The logged-in user.
     */
    public void provisionUser(User user) throws CaaersUserProvisioningException{
    	try{
        	if(user instanceof RemoteResearchStaff || user instanceof LocalResearchStaff){
        		provisionResearchStaff((ResearchStaff)user);
        	}else if(user instanceof RemoteInvestigator || user instanceof LocalInvestigator){
        		provisionInvestigator((Investigator)user);
        	}
    	}catch(Exception ex){
    		log.error("Exception in CaaersSecurityFacadeImpl.provisionUser() "+ex.getMessage());
    		throw new CaaersUserProvisioningException("Exception while provisioning Organizations/Studies for user - "+user.getLoginId() ,ex);
    	}
    }
    
    /**
     * Will only populate the role associations and protection group association for ResearchStaff
     * @param investigator
     */
    public void provisionInvestigator(Investigator investigator){
    	
    	if(StringUtils.isEmpty(investigator.getLoginId())) return;
    	
    	gov.nih.nci.security.authorization.domainobjects.User csmUser = csmUserRepository.getUserProvisioningManager().getUser(investigator.getLoginId());
    	if(csmUser == null) return;
    	
    	try {
			String groupId;
	    	ProvisioningSession provisioningSession = provisioningSessionFactory.createSession(csmUser.getUserId());
			SuiteRole suiteRole = SuiteRole.getByCsmName(AE_REPORTER);
			provisioningSession.deleteRole(suiteRole);
			groupId = csmUserRepository.getGroupIdByName(AE_REPORTER);
			csmUserRepository.getUserProvisioningManager().removeUserFromGroup(groupId,String.valueOf(csmUser.getUserId()));
			
			SuiteRoleMembership suiteRoleMembership = new SuiteRoleMembership(suiteRole,null,null);
			for(SiteInvestigator eachSiteInv : investigator.getActiveSiteInvestigators()){
				suiteRoleMembership.addSite(eachSiteInv.getOrganization().getNciInstituteCode());
			}
			List<String> studyIndetifiers = getAllInvestigatorStudies(investigator.getLoginId());
			for(String studyIdentifier : studyIndetifiers){
				suiteRoleMembership.addStudy(studyIdentifier);
			}
			if(suiteRoleMembership.getSiteIdentifiers() != null && suiteRoleMembership.getSiteIdentifiers().size() > 0){
				provisioningSession.replaceRole(suiteRoleMembership);
				csmUserRepository.getUserProvisioningManager().addGroupsToUser(String.valueOf(csmUser.getUserId()), new String[]{groupId});
			}
    	}catch (CSObjectNotFoundException e) {
			throw new CaaersUserProvisioningException("Exception while provisioning user - "+csmUser.getLoginName() ,e);
		} catch (CSTransactionException e) {
			throw new CaaersUserProvisioningException("Exception while provisioning user - "+csmUser.getLoginName() ,e);
		} catch (Exception e){
			throw new CaaersUserProvisioningException("Exception while provisioning user - "+csmUser.getLoginName() ,e);
		}
    }
    
    /**
     * Will only populate the role associations and protection group association for ResearchStaff
     * @param researchStaff
     */
    public void provisionResearchStaff(ResearchStaff researchStaff){
    	
    	if(StringUtils.isEmpty(researchStaff.getLoginId())) return;
       	
    	gov.nih.nci.security.authorization.domainobjects.User csmUser = csmUserRepository.getUserProvisioningManager().getUser(researchStaff.getLoginId());
    	if(csmUser == null) return;
    	
		try {
			String groupId;
	    	ProvisioningSession provisioningSession = provisioningSessionFactory.createSession(csmUser.getUserId());
	    	for(SiteResearchStaff eachSrs : researchStaff.getSiteResearchStaffs()){
	    		for(SiteResearchStaffRole eachSrsRole : eachSrs.getSiteResearchStaffRoles()){
	    			SuiteRole suiteRole = SuiteRole.getByCsmName(eachSrsRole.getRoleCode());
	    			provisioningSession.deleteRole(suiteRole);
	    			groupId = csmUserRepository.getGroupIdByName(eachSrsRole.getRoleCode());
	    			csmUserRepository.getUserProvisioningManager().removeUserFromGroup(groupId,String.valueOf(csmUser.getUserId()));
	    		}
	    	}
	   		for(SiteResearchStaff eachSrs : researchStaff.getActiveSiteResearchStaff()){
				for(SiteResearchStaffRole eachSrsRole : eachSrs.getActiveSiteResearchStaffRoles()){
					SuiteRole suiteRole = SuiteRole.getByCsmName(eachSrsRole.getRoleCode());
					if(suiteRole.isScoped()){
						SuiteRoleMembership suiteRoleMembership = provisioningSession.getProvisionableRoleMembership(suiteRole);
						if(suiteRole.isSiteScoped() && suiteRole.isStudyScoped()){
							String orgIdentifier = eachSrsRole.getSiteResearchStaff().getOrganization().getNciInstituteCode();
				    		suiteRoleMembership.addSite(orgIdentifier);
							List<String> studyIndetifiers = getAllResearchStaffStudies(researchStaff.getLoginId());
				    		for(String studyIdentifier : studyIndetifiers){
				    			suiteRoleMembership.addStudy(studyIdentifier);
				    		}
						}else if(suiteRole.isSiteScoped()){
							if(suiteRole.getCsmName().equals(USER_ADMINISTRATOR) || suiteRole.getCsmName().equals(PO_INFO_MANAGER)){
								suiteRoleMembership.forAllSites();
							}else{
								String orgIdentifier = eachSrsRole.getSiteResearchStaff().getOrganization().getNciInstituteCode();
	    			    		suiteRoleMembership.addSite(orgIdentifier);
							}
						}
						provisioningSession.replaceRole(suiteRoleMembership);
					}
					groupId = csmUserRepository.getGroupIdByName(eachSrsRole.getRoleCode());
					csmUserRepository.getUserProvisioningManager().addGroupsToUser(String.valueOf(csmUser.getUserId()), new String[]{groupId});
				}
			}
		} catch (CSObjectNotFoundException e) {
			throw new CaaersUserProvisioningException("Exception while provisioning user - "+csmUser.getLoginName() ,e);
		} catch (CSTransactionException e) {
			throw new CaaersUserProvisioningException("Exception while provisioning user - "+csmUser.getLoginName() ,e);
		} catch (Exception e){
			throw new CaaersUserProvisioningException("Exception while provisioning user - "+csmUser.getLoginName() ,e);
		}
    }
    
    
    /**
     * Will provision studies for ResearchStaff in CSM.
     * @param researchStaff
     */
    public void provisionStudiesForResearchStaff(ResearchStaff researchStaff){
    	if(StringUtils.isEmpty(researchStaff.getLoginId())) return;
       	
    	gov.nih.nci.security.authorization.domainobjects.User csmUser = csmUserRepository.getUserProvisioningManager().getUser(researchStaff.getLoginId());
    	if(csmUser == null) return;
    	
		try {
			ProvisioningSession provisioningSession = provisioningSessionFactory.createSession(csmUser.getUserId());
	   		for(SiteResearchStaff eachSrs : researchStaff.getActiveSiteResearchStaff()){
				for(SiteResearchStaffRole eachSrsRole : eachSrs.getActiveSiteResearchStaffRoles()){
					SuiteRole suiteRole = SuiteRole.getByCsmName(eachSrsRole.getRoleCode());
					if(suiteRole.isScoped()){
						SuiteRoleMembership suiteRoleMembership = provisioningSession.getProvisionableRoleMembership(suiteRole);
						if(suiteRole.isSiteScoped() && suiteRole.isStudyScoped()){
							suiteRoleMembership.getStudyIdentifiers().clear();
							List<String> studyIndetifiers = getAllResearchStaffStudies(researchStaff.getLoginId());
				    		for(String studyIdentifier : studyIndetifiers){
				    			suiteRoleMembership.addStudy(studyIdentifier);
				    		}
						}
						provisioningSession.replaceRole(suiteRoleMembership);
					}
				}
			}
		}catch (Exception e){
			throw new CaaersUserProvisioningException("Exception while provisioning studies for - "+csmUser.getLoginName() ,e);
		}
    }
    
    /**
     * Will provision studies for Investigator in CSM.
     * @param researchStaff
     */
    public void provisionStudiesForInvestigator(Investigator investigator){
    	if(StringUtils.isEmpty(investigator.getLoginId())) return;
    	
    	gov.nih.nci.security.authorization.domainobjects.User csmUser = csmUserRepository.getUserProvisioningManager().getUser(investigator.getLoginId());
    	if(csmUser == null) return;
    	
    	try {
	    	ProvisioningSession provisioningSession = provisioningSessionFactory.createSession(csmUser.getUserId());
			SuiteRole suiteRole = SuiteRole.getByCsmName(AE_REPORTER);
			
			SuiteRoleMembership suiteRoleMembership = provisioningSession.getProvisionableRoleMembership(suiteRole);
			suiteRoleMembership.getStudyIdentifiers().clear();
			List<String> studyIndetifiers = getAllInvestigatorStudies(investigator.getLoginId());
			for(String studyIdentifier : studyIndetifiers){
				suiteRoleMembership.addStudy(studyIdentifier);
			}
			provisioningSession.replaceRole(suiteRoleMembership);
    	}catch (Exception e){
			throw new CaaersUserProvisioningException("Exception while provisioning studies for - "+csmUser.getLoginName() ,e);
		}
    }
    
    
    /**
     * This method will return a list if co-ordinating center assigned identifiers for studies which the investigator has access to. 
     * @param loginId
     * @return
     */
    @SuppressWarnings("unchecked")
	private List<String> getAllInvestigatorStudies(String loginId){
    	Date d = new Date();
    	StringBuilder hql = new StringBuilder("select distinct ids.value from StudyInvestigator sti "); 
					  hql.append("join sti.studyOrganization so ");
					  hql.append("join so.study s ");
					  hql.append("join s.identifiers ids ");
					  hql.append("join sti.siteInvestigator si ");
					  hql.append("join si.investigator i ");
					  hql.append("where i.loginId = :loginId and  ");
					  hql.append("sti.startDate<=:stDate and  ");
					  hql.append("( sti.endDate is null or sti.endDate >= :enDate ) and ");
					  hql.append("sti.retiredIndicator <> true and ");
					  hql.append("ids.type = 'Coordinating Center Identifier'");
    	
    	HQLQuery query = new HQLQuery(hql.toString());
        query.getParameterMap().put("loginId", loginId);
        query.getParameterMap().put("stDate", d);
        query.getParameterMap().put("enDate", d);
        List<String> studyIdentifiers = (List<String>)search(query);
    	return studyIdentifiers;
    }
    
    /**
     * This method will return a list if co-ordinating center assigned identifiers for studies which the research staff has access to. 
     * @param loginId
     * @return
     */
    @SuppressWarnings("unchecked")
	private List<String> getAllResearchStaffStudies(String loginId){
    	Date d = new Date();
        StringBuilder hql = new StringBuilder("select distinct ids.value from StudyPersonnel sp ")
								        .append("join sp.studyOrganization so ")
								        .append("join so.study s ")
								        .append("join s.identifiers ids ")
								        .append("join sp.siteResearchStaff srs ")
								        .append("join srs.researchStaff rs ")
								        .append("where rs.loginId = :loginId ")
								        .append("and sp.startDate<= :stDate ")
								        .append("and (sp.endDate is null or sp.endDate >= :enDate ) " )
								        .append("and sp.retiredIndicator <> true and ")
								        .append("ids.type = 'Coordinating Center Identifier'");
    	
    	HQLQuery query = new HQLQuery(hql.toString());
        query.getParameterMap().put("loginId", loginId);
        query.getParameterMap().put("stDate", d);
        query.getParameterMap().put("enDate", d);
        List<String> studyIdentifiers = (List<String>)search(query);
    	return studyIdentifiers;
    }
    
    /**
     * Will create or update a csm user.
     *
     * @param user - A user defined in CSM.
     * @param changeURL - The URL send email
     */
    public void createOrUpdateCSMUser(User user,String changeURL) {
    	if(user instanceof RemoteResearchStaff || user instanceof LocalResearchStaff){
    		try{
    			csmUserRepository.createOrUpdateCSMUserAndGroupsForResearchStaff((ResearchStaff)user, changeURL);
    		}catch(MailException e){
    			provisionResearchStaff((ResearchStaff)user);
    			throw e;
    		}
    		provisionResearchStaff((ResearchStaff)user);
    		
    	}else if(user instanceof RemoteInvestigator || user instanceof LocalInvestigator){
    		try{
    			csmUserRepository.createOrUpdateCSMUserAndGroupsForInvestigator((Investigator)user, changeURL);
    		}catch(MailException e){
    			provisionInvestigator((Investigator)user);
    			throw e;
    		}
    		provisionInvestigator((Investigator)user);
    	}
    }

    /**
     * Will get the accessible protection element Ids (ObjectIDs) for the login.
     *
     * @param loginId - The loginId
     * @return
     */
    public List<String> getAccessibleProtectionElements(String loginId) {
		List<String> pes = new ArrayList<String>();
    	try {
			Set<ProtectionElementPrivilegeContext> contexts = getProtectionElementPrivilegeContextForUser(loginId);
			for (ProtectionElementPrivilegeContext context : contexts) {
				ProtectionElement pe = context.getProtectionElement();
				pes.add(pe.getProtectionElementName());
			}
		} catch (CSObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return pes;  
    }
    /**
     * Will get the accessible ProtectionGroupRoleContexts for the login. Gets from CSM or Cache
     * @param loginId
     * @return
     * @throws CSObjectNotFoundException
     */
    public Set<ProtectionGroupRoleContext> getProtectionGroupRoleContextForUser(String loginId) throws CSObjectNotFoundException {
    	Set<ProtectionGroupRoleContext> contexts = null;
    	contexts = CSMCacheManager.getContextFromCache(loginId, loginId, CSMCacheManager.PROTECTION_GROUP_ROLE_CONTEXT);
    	if (contexts  == null ) {
    		contexts = csmUserRepository.getUserProvisioningManager().getProtectionGroupRoleContextForUser(loginId);
    		CSMCacheManager.addProtectionGroupRoleContextToCache(loginId , loginId, contexts);
    	}
		return contexts;
    }
    
    /**
     * Will get the accessible ProtectionElementPrivilegeContext for the login. Gets from CSM or Cache
     * @param loginId
     * @return
     * @throws CSObjectNotFoundException
     */
    public Set<ProtectionElementPrivilegeContext> getProtectionElementPrivilegeContextForUser(String loginId) throws CSObjectNotFoundException {
    	Set<ProtectionElementPrivilegeContext> contexts = null;
    	contexts = CSMCacheManager.getContextFromCache(loginId, loginId, CSMCacheManager.PROTECTION_ELEMENT_PRIVILEGE_CONTEXT);
    	if (contexts  == null ) {
    		contexts = csmUserRepository.getUserProvisioningManager().getProtectionElementPrivilegeContextForUser(loginId);
    		CSMCacheManager.addProtectionElementPrivilegeContextToCache(loginId, loginId, contexts);
    	}
    	return contexts;
    }
    public List<IndexEntry> getAccessibleStudyIds(String userName) {
		//		get csm user DB id . 
    	String loginId = csmUserRepository.getCSMUserByName(userName).getUserId()+"";
    	Map<UserGroupType, List<Integer>> accessibleStudyIds = new HashMap<UserGroupType , List<Integer>>();

    	try {
			Set<ProtectionGroupRoleContext> contexts = getProtectionGroupRoleContextForUser(loginId);
			
			for (ProtectionGroupRoleContext context : contexts) {
				ProtectionGroup pe = context.getProtectionGroup();
				List<String> roles = getRoles(context);
				for (String roleName:roles) {
				
					// if user has global role , no need index data for that context . 
					if (!SecurityUtils.isScoped(roleName)) {
						continue;
					}
					
					UserGroupType userGroupType = UserGroupType.valueOf(roleName);
					List<Integer> ids = accessibleStudyIds.get(userGroupType);
					String caaersEquivalentName = pe.getProtectionGroupName();// call SecurityObjectIdGenerator.toCaaersObjectName
					
					
					// if STUDY_PE , that means user have access to all studies (all means not all , he has access to studies 
					// on organizations which he belongs to as this role on that organization ) 
					
					if (roleName != null && caaersEquivalentName.equals(STUDY_PE)) {
						// get acessible organizations with above role , and then associated Studies
						List<Integer>  studyIds= getStudyIdsByRoleName(loginId, roleName);
						if (ids == null) {
							accessibleStudyIds.put(userGroupType, studyIds);
						} else {
							ids.addAll(studyIds);
							accessibleStudyIds.put(userGroupType, ids);
						}
					} else {
						SuiteRole suiteRole = SuiteRole.getByCsmName(roleName);
						// if user is study scoped 
						if(suiteRole.isSiteScoped() && suiteRole.isStudyScoped()){
							List identifiers = new ArrayList();
							String[] tokens = caaersEquivalentName.split("\\.");
							if (tokens.length == 2) {
								if (tokens[0].equals(STUDY_PE)) {
									identifiers.add(tokens[1]);
								}
							}
							if (identifiers.size() > 0) {
								// get caAERS IDs from Studies table , primary key ..
								String hql = " select distinct s.id from Study s join s.identifiers as identifier where identifier.value in (:identifiers)";
								HQLQuery query = new HQLQuery(hql.toString());
								query.setParameterList("identifiers", identifiers);
								List<Integer>  studyIds= (List<Integer>) search(query);
								if (ids == null) {
									accessibleStudyIds.put(userGroupType, studyIds);
								} else {
									ids.addAll(studyIds);
									accessibleStudyIds.put(userGroupType, ids);
								}							
							}
						} else if (suiteRole.isSiteScoped()) {
							//if the user is site scoped , no study information is provisioned .. only Org is provisioned and he has access to all srtudies for those orgs 
							//get acessible organization with above role, and then associated Studies
							List<Integer>  studyIds = getStudyIdsByRoleName(loginId, roleName);
							if (ids == null) {
								accessibleStudyIds.put(userGroupType, studyIds);
							} else {
								ids.addAll(studyIds);
								accessibleStudyIds.put(userGroupType, ids);
							}
						}
					}
				}
			}	

		} catch (CSObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		   List<IndexEntry> list = new ArrayList<IndexEntry>();
		   Set keys = accessibleStudyIds.keySet();
	       Iterator itr = keys.iterator();
	       while (itr.hasNext()) {
	    	   UserGroupType key = (UserGroupType)itr.next();
	    	   List<Integer> pIds = accessibleStudyIds.get(key);
	    	   if(pIds != null && !accessibleStudyIds.isEmpty()){
	               IndexEntry ie = new IndexEntry(key);
	               ie.setEntityIds(pIds);
	               list.add(ie);
	           }
	       }
	       return list;
    }


	/**
	 * @param loginId
	 * @param roleName
	 * @return
	 */
	private List<Integer> getStudyIdsByRoleName(String loginId,
			 String roleName) {
		List<Integer> resultList = new ArrayList<Integer>();
		String hql;
		List<Integer> userOrgs = getAccessibleOrganizationIdsFilterByRole(loginId, roleName);
		if (userOrgs.size() > 0) {			
			if (userOrgs.size() == 1 && (userOrgs.get(0) == ALL_IDS_FABRICATED_ID)) {
				hql = "select study.id from Study study ";	
				HQLQuery query = new HQLQuery(hql.toString());
				resultList = (List<Integer>) search(query);
			} else {			
				hql = "select distinct so.study.id from StudyOrganization so where so.organization.id in (:userOrgs) ";					
				HQLQuery query = new HQLQuery(hql.toString());
				query.setParameterList("userOrgs", userOrgs);
				resultList = (List<Integer>) search(query);
			}
		}
		return resultList;
	}

	public List<IndexEntry> getAccessibleOrganizationIds(String userName) {
		//		get csm user DB id . 
    	String loginId = csmUserRepository.getCSMUserByName(userName).getUserId()+"";
    	Map<UserGroupType, List<Integer>> accessibleOrganizationIds = new HashMap<UserGroupType , List<Integer>>();

    	try {
			Set<ProtectionGroupRoleContext> contexts = getProtectionGroupRoleContextForUser(loginId);
			
			for (ProtectionGroupRoleContext context : contexts) {
				ProtectionGroup pe = context.getProtectionGroup();
				List<String> roles = getRoles(context);
				for (String roleName:roles) {
				
					// if user has global role , no need index data for that context . 
					if (!SecurityUtils.isScoped(roleName)) {
						continue;
					}
					
					UserGroupType userGroupType = UserGroupType.valueOf(roleName);
					List<Integer> ids = accessibleOrganizationIds.get(userGroupType);
					String caaersEquivalentName = pe.getProtectionGroupName();// call SecurityObjectIdGenerator.toCaaersObjectName
	
					if (caaersEquivalentName.equals(ORGANIZATION_PE)) {
						// all orgs , no no need to get the existing list and add .. just override it 
						accessibleOrganizationIds.put(userGroupType, getAllOrganizationIdsFromDB());
					} else {
						//parse name ..
						String[] tokens = caaersEquivalentName.split("\\.");
						List identifiers = new ArrayList();
						if (tokens.length == 2) {
							if (tokens[0].equals(ORGANIZATION_PE)) {
								identifiers.add(tokens[1]);
							}
						}
						// get caAERS IDs from Organizations table , primary key ..
						if (identifiers.size() > 0) {
							List<Integer> orgIds = getOrganizationIdsByIdentifiersFromDB(identifiers);
							if (orgIds.size() > 0) {
								if (ids == null){
									accessibleOrganizationIds.put(userGroupType, orgIds);
								} else {
									ids.addAll(orgIds);
									accessibleOrganizationIds.put(userGroupType, ids);
								}
							}
						}
	
					}
				
				}
				
			}

		} catch (CSObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   List<IndexEntry> list = new ArrayList<IndexEntry>();
		   Set keys = accessibleOrganizationIds.keySet();
	       Iterator itr = keys.iterator();
	       while (itr.hasNext()) {
	    	   UserGroupType key = (UserGroupType)itr.next();
	    	   List<Integer> pIds = accessibleOrganizationIds.get(key);
	    	   if(pIds != null && !accessibleOrganizationIds.isEmpty()){
	               IndexEntry ie = new IndexEntry(key);
	               ie.setEntityIds(pIds);
	               list.add(ie);
	           }
	       }
	       return list;
	}

    /**
     * 
     * @param loginId
     * @param roleNameToCheck
     * @return
     */
    @SuppressWarnings("unchecked")
	private List<Integer> getAccessibleOrganizationIdsFilterByRole(String loginId, String roleNameToCheck) {
    	try {
			Set<ProtectionGroupRoleContext> contexts = getProtectionGroupRoleContextForUser(loginId);
			List identifiers = new ArrayList();
			for (ProtectionGroupRoleContext context : contexts) {
				ProtectionGroup pe = context.getProtectionGroup();
				String caaersEquivalentName = pe.getProtectionGroupName();// call SecurityObjectIdGenerator.toCaaersObjectName
				List<String> roles = getRoles(context);
				for (String roleName:roles) {
					if (roleName != null && roleName.equals(roleNameToCheck) && caaersEquivalentName.equals(ORGANIZATION_PE)) {
						// here we need all orgs in DB , instead of getting all orgs just fabricate an org id to indicate all ORGS .(ALL_ORGS_FABRICATED_ID)
						List<Integer> allIds = new ArrayList<Integer>();
						allIds.add(ALL_IDS_FABRICATED_ID);
						return allIds;
						//return getAllOrganizationIdsFromDB();
						
					} else {
						//parse name ..
						String[] tokens = caaersEquivalentName.split("\\.");
						if (tokens.length == 2) {
							// check if user has access to this org AS this role . 
							if (tokens[0].equals(ORGANIZATION_PE) && roleName.equals(roleNameToCheck)) {
								identifiers.add(tokens[1]);
							}
						}
					}					
				}
				// check user has access to all orgs AS this role .
				
			}
			if (identifiers.size() > 0) {
				return getOrganizationIdsByIdentifiersFromDB(identifiers);
			}
		} catch (CSObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return new ArrayList<Integer>();
    }
    private List<String> getRoles(ProtectionGroupRoleContext context) {
    	List<String> rolesList = new ArrayList<String>();
    	Set roles = context.getRoles();
		Iterator itr = roles.iterator();

		while (itr.hasNext()) {
			Role role = (Role)itr.next();
			rolesList.add(role.getName());
		}
		return rolesList;
    }
    
    private List<Integer> getAllOrganizationIdsFromDB() {
    	List<Integer> resultList = new ArrayList<Integer>();
		String hql = "select distinct o.id from Organization o ";
		HQLQuery query = new HQLQuery(hql.toString());
		resultList = (List<Integer>) search(query);
		return resultList;
    }
    
    private List<Integer> getOrganizationIdsByIdentifiersFromDB(List identifiers) {
    	List<Integer> resultList = new ArrayList<Integer>();
		String hql = " select distinct o.id from Organization o where o.nciInstituteCode in (:identifiers)";
		HQLQuery query = new HQLQuery(hql.toString());
        query.setParameterList("identifiers", identifiers);
        resultList = (List<Integer>) search(query);
		return resultList;
    }

	public void clearUserCache(String userName) {
		String loginId = csmUserRepository.getCSMUserByName(userName).getUserId()+"";
		//loginId is cacheKey , remove the cache for that user .. 
		CacheManager cacheManager = CSMCacheManager.getCacheManager() ;
		
		Cache cache = cacheManager.getCache(loginId);
		if (cache == null) {
			return;
		} else {
			cacheManager.removeCache(loginId);
		}	
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

	public void setCsmUserRepository(CSMUserRepositoryImpl csmUserRepository) {
		this.csmUserRepository = csmUserRepository;
	}

	public CSMUserRepositoryImpl getCsmUserRepository() {
		return csmUserRepository;
	}
}