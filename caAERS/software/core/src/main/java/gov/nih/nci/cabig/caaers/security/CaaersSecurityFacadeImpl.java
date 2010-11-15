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
import gov.nih.nci.cabig.caaers.domain.StudyInvestigator;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudyPersonnel;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepositoryImpl;
import gov.nih.nci.cabig.caaers.utils.ObjectPrivilegeParser;
import gov.nih.nci.cabig.caaers.utils.el.EL;
import gov.nih.nci.cabig.ctms.suite.authorization.ProvisioningSession;
import gov.nih.nci.cabig.ctms.suite.authorization.ProvisioningSessionFactory;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteAuthorizationAccessException;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRole;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRoleMembership;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElementPrivilegeContext;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroupRoleContext;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import gov.nih.nci.security.exceptions.CSTransactionException;
import gov.nih.nci.security.util.StringUtilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.MailException;

/**
 * The Facade Layer to CSM. 
 * @author: Biju Joseph
 */
public class CaaersSecurityFacadeImpl implements CaaersSecurityFacade  {

	protected static CaaersSecurityFacade instance;
    protected final Log log = LogFactory.getLog(getClass());
	private CSMUserRepositoryImpl csmUserRepository;
	private RolePrivilegeDao rolePrivilegeDao;
	private ProvisioningSessionFactory provisioningSessionFactory;

    public CaaersSecurityFacadeImpl() {
        instance = this;
    }

	//Roles for which we have to make an exception in terms of scoping
	private String USER_ADMINISTRATOR = "user_administrator";
	private String PO_INFO_MANAGER = "person_and_organization_information_manager";
	
	public static Integer ALL_IDS_FABRICATED_ID = Integer.MIN_VALUE;
	
	//For all Investigators
	private String AE_REPORTER = "ae_reporter";
	
	
	 /**
     * Will create or update a csm user.
     *
     * @param caaersUser - A user defined in CSM.
     * @param changeURL - The URL send email
     */
    public gov.nih.nci.security.authorization.domainobjects.User createOrUpdateCSMUser(User caaersUser,String changeURL) {
    	gov.nih.nci.security.authorization.domainobjects.User csmUser = null;
		try{
			csmUser = csmUserRepository.createOrUpdateCSMUser(caaersUser, changeURL);
		}catch(MailException e){
			throw e;
		}
		return csmUser;
    }
	
    
	/*
	 * (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade#provisionRoleMemberships(gov.nih.nci.security.authorization.domainobjects.User, java.util.List)
	 */
	public void provisionRoleMemberships(
			gov.nih.nci.security.authorization.domainobjects.User csmUser,
			List<SuiteRoleMembership> roleMemberships) {
		
		//Fetch all the existing groups of the Given User.
		List<UserGroupType> userGroups = csmUserRepository.getUserGroups(csmUser.getLoginName());
		//Erase all the existing SuiteRoleMemberships of the User
		ProvisioningSession session = provisioningSessionFactory.createSession(csmUser.getUserId());
		for(UserGroupType group : userGroups){
			session.deleteRole(SuiteRole.getByCsmName(group.getCsmName()));
		}
		
		//Provision the newly provided SuiteRoleMemberships for the User in CSM.
		for(SuiteRoleMembership roleMembership : roleMemberships){
			session.replaceRole(roleMembership);
		}
	}
	

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade#getRoles(java.lang.String, gov.nih.nci.cabig.caaers.domain.Study)
	 */
    //BJ : refactored to use SuiteRoleMembership
	public Collection<String> getRoles(String userName, Study study) {
		Set<String> roles = new HashSet<String>();
        gov.nih.nci.security.authorization.domainobjects.User csmUser = csmUserRepository.getCSMUserByName(userName);
        ProvisioningSession session = provisioningSessionFactory.createSession(csmUser.getUserId());

        String studyCCIdentifier = study.getCoordinatingCenterIdentifier().getValue();
        
        List<UserGroupType> userGroups = csmUserRepository.getUserGroups(csmUser.getLoginName());
        for(UserGroupType group : userGroups){
        	SuiteRoleMembership membership = session.getProvisionableRoleMembership(SuiteRole.getByCsmName(group.getCsmName()));
        	SuiteRole suiteRole = membership.getRole();
        	
            if(!suiteRole.isScoped()) continue;

            if(suiteRole.isSiteScoped() && membership.isAllSites()){
                roles.add(suiteRole.getCsmName()); //all study access
                continue; 
            }

            if(suiteRole.isStudyScoped() && !membership.isAllStudies()){
                //specific studies
                List<String> studyIdentifiers = membership.getStudyIdentifiers();
                if(studyIdentifiers.contains(studyCCIdentifier))  roles.add(suiteRole.getCsmName());
                continue;
            }
            //siteScoped-non-all-site and  studyScoped-all-study roles
            //can access the study if the site is an active study organization is present in membership
            List<String> nciCodes = membership.getSiteIdentifiers();
            lblNci:
            for(String nciCode : nciCodes){
                for(StudyOrganization so : study.getActiveStudyOrganizations()){
                    if(so.getOrganization().getNciInstituteCode().equals(nciCode)){
                        roles.add(suiteRole.getCsmName());
                        break lblNci;
                    }
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
        gov.nih.nci.security.authorization.domainobjects.User csmUser = csmUserRepository.getCSMUserByName(userName);
        ProvisioningSession session = provisioningSessionFactory.createSession(csmUser.getUserId());
        
        List<UserGroupType> userGroups = csmUserRepository.getUserGroups(csmUser.getLoginName());
        for(UserGroupType group : userGroups){
        	SuiteRoleMembership membership = session.getProvisionableRoleMembership(SuiteRole.getByCsmName(group.getCsmName()));
        	SuiteRole suiteRole = membership.getRole();
            if(!suiteRole.isScoped()) continue;

            if(membership.isAllSites()){
                roles.add(suiteRole.getCsmName()); //all site access
            } else {
                if(membership.getSiteIdentifiers().contains(org.getNciInstituteCode())) roles.add(suiteRole.getCsmName());
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
    private void provisionSitesForResearchStaff(ResearchStaff researchStaff,ProvisioningSession provisioningSession){

    	for(SiteResearchStaff eachSrs : researchStaff.getInActiveSiteResearchStaff()){
    		SuiteRoleMembership suiteRoleMembership = null;
    		for(SiteResearchStaffRole eachSrsRole : eachSrs.getSiteResearchStaffRoles()){
    			SuiteRole suiteRole = SuiteRole.getByCsmName(eachSrsRole.getRoleCode());
    			if(suiteRole.isScoped()){
        			List<SiteResearchStaff> srsList = researchStaff.findSiteResearchStaffByRoles(eachSrsRole.getRoleCode());
        			if(srsList == null || srsList.isEmpty()){
        				provisioningSession.deleteRole(suiteRole);
        			}else{
        				suiteRoleMembership = provisioningSession.getProvisionableRoleMembership(suiteRole);
        				if(!suiteRoleMembership.isAllSites()){
        					suiteRoleMembership.removeSite(eachSrs.getOrganization().getNciInstituteCode());
        					provisioningSession.replaceRole(suiteRoleMembership);
        				}
        			}
    			}else{
    				provisioningSession.deleteRole(suiteRole);
    			}
    		}
    	}
    	for(SiteResearchStaff eachSrs : researchStaff.getActiveSiteResearchStaff()){
    		SuiteRoleMembership suiteRoleMembership = null;
    		for(SiteResearchStaffRole eachSrsRole : eachSrs.getInActiveSiteResearchStaffRoles()){
    			SuiteRole suiteRole = SuiteRole.getByCsmName(eachSrsRole.getRoleCode());
    			if(suiteRole.isScoped()){
        			List<SiteResearchStaff> srsList = researchStaff.findSiteResearchStaffByRoles(eachSrsRole.getRoleCode());
        			if(srsList.isEmpty()){
        				provisioningSession.deleteRole(suiteRole);
        			}else{
       					suiteRoleMembership = provisioningSession.getProvisionableRoleMembership(suiteRole);
        				if(!suiteRoleMembership.isAllSites()){
        					suiteRoleMembership.removeSite(eachSrs.getOrganization().getNciInstituteCode());
        					provisioningSession.replaceRole(suiteRoleMembership);
        				}
        			}
    			}else{
    				provisioningSession.deleteRole(suiteRole);
    			}
    		}
    		for(SiteResearchStaffRole eachSrsRole : eachSrs.getActiveSiteResearchStaffRoles()){
    			SuiteRole suiteRole = SuiteRole.getByCsmName(eachSrsRole.getRoleCode());
    			suiteRoleMembership = provisioningSession.getProvisionableRoleMembership(suiteRole);
    			if(suiteRole.isScoped()){
        			if(suiteRole.getCsmName().equals(USER_ADMINISTRATOR) || suiteRole.getCsmName().equals(PO_INFO_MANAGER)){
        				suiteRoleMembership.forAllSites();
        			}
        			if(!suiteRoleMembership.isAllSites()){
        				suiteRoleMembership.addSite(eachSrs.getOrganization().getNciInstituteCode());
        			}
            		provisioningSession.replaceRole(suiteRoleMembership);
    			}else{
    				provisioningSession.replaceRole(suiteRoleMembership);
    			}
    		}
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
    	
    	ProvisioningSession provisioningSession = provisioningSessionFactory.createSession(csmUser.getUserId());
		try {
			provisionSitesForResearchStaff(researchStaff, provisioningSession);
			provisionStudies(researchStaff, provisioningSession);
		} catch (Exception e){
			throw new CaaersUserProvisioningException("Exception while provisioning user - "+csmUser.getLoginName() ,e);
		}
    }
    
    
    /**
     * Will provision studies for StudyPersonnel in CSM.
     * @param studyPersonnel
     */
    public void provisionStudies(StudyPersonnel studyPersonnel){

    	if (studyPersonnel == null) return;
    	String loginId =  studyPersonnel.getSiteResearchStaff().getResearchStaff().getLoginId();
    	
    	if(StringUtils.isEmpty(loginId)) return;
    	gov.nih.nci.security.authorization.domainobjects.User csmUser = csmUserRepository.getUserProvisioningManager().getUser(loginId);
    	if(csmUser == null) return;
    	try {
        	ProvisioningSession provisioningSession = provisioningSessionFactory.createSession(csmUser.getUserId());
        	SuiteRole suiteRole = SuiteRole.getByCsmName(studyPersonnel.getRoleCode());
        	if(suiteRole.isStudyScoped()){
        		SuiteRoleMembership suiteRoleMembership = provisioningSession.getProvisionableRoleMembership(suiteRole);
            	String studyIdentifier = studyPersonnel.getStudyOrganization().getStudy().getCoordinatingCenterIdentifier().getValue();
            	if(!suiteRoleMembership.isAllStudies()){
                	if(studyPersonnel.isActive()){
                		suiteRoleMembership.addStudy(studyIdentifier);
                	}else if(studyPersonnel.isInActive()){
                		suiteRoleMembership.removeStudy(studyIdentifier);
                	}
            	}
            	provisioningSession.replaceRole(suiteRoleMembership);
        	}

		}catch (Exception e){
			throw new CaaersUserProvisioningException("Exception while provisioning studies for - "+loginId ,e);
		}
    }
    
    /**
     * Will provision studies for ResearchStaff in CSM.
     * @param researchStaff
     */
    private void provisionStudies(ResearchStaff researchStaff,ProvisioningSession provisioningSession){
		try {
	   		for(SiteResearchStaff eachSrs : researchStaff.getSiteResearchStaffs()){
				for(SiteResearchStaffRole eachSrsRole : eachSrs.getSiteResearchStaffRoles()){
					SuiteRole suiteRole = SuiteRole.getByCsmName(eachSrsRole.getRoleCode());
					if(suiteRole.isStudyScoped()){
						SuiteRoleMembership suiteRoleMembership = provisioningSession.getProvisionableRoleMembership(suiteRole);
	    				List<String> studiesToRemove = getAllRSStudiesToRemove(researchStaff.getLoginId());
	    				for(String studyIdentifier : studiesToRemove){
	    					if(!suiteRoleMembership.isAllStudies()){
	    						suiteRoleMembership.removeStudy(studyIdentifier);
	    					}
	    				}
	    				List<String> studiesToAdd = getAllRSStudiesToAdd(researchStaff.getLoginId());
	    				for(String studyIdentifier : studiesToAdd){
	    					if(!suiteRoleMembership.isAllStudies()){
	    						suiteRoleMembership.addStudy(studyIdentifier);
	    					}
	    				}
	    				try{
	    					if(suiteRoleMembership.getSiteIdentifiers() != null && suiteRoleMembership.getSiteIdentifiers().size() > 0){
	    						provisioningSession.replaceRole(suiteRoleMembership);
	    					}
	    				}catch(SuiteAuthorizationAccessException siteE){
	    					//allSite = true;
	    					provisioningSession.replaceRole(suiteRoleMembership);
	    				}
					}
				}
			}
		}catch (Exception e){
			throw new CaaersUserProvisioningException("Exception while provisioning studies for - "+researchStaff.getLoginId() ,e);
		}
    }
    
    /**
     * Will provision studies for Investigator in CSM.
     * @param studyInvestigator
     */
    public void provisionStudies(StudyInvestigator studyInvestigator){
    	
    	if(studyInvestigator == null) return;
    	
    	String loginId =  studyInvestigator.getSiteInvestigator().getInvestigator().getLoginId();
    	if(StringUtils.isEmpty(loginId)) return;

    	gov.nih.nci.security.authorization.domainobjects.User csmUser = csmUserRepository.getUserProvisioningManager().getUser(loginId);
    	if(csmUser == null) return;
    	
    	try {
	    	ProvisioningSession provisioningSession = provisioningSessionFactory.createSession(csmUser.getUserId());
			SuiteRole suiteRole = SuiteRole.getByCsmName(AE_REPORTER);
			SuiteRoleMembership suiteRoleMembership = provisioningSession.getProvisionableRoleMembership(suiteRole);
			String studyIdentifier = studyInvestigator.getStudyOrganization().getStudy().getCoordinatingCenterIdentifier().getValue();
        	if(!suiteRoleMembership.isAllStudies()){
            	if(studyInvestigator.isActive()){
            		suiteRoleMembership.addStudy(studyIdentifier);
            	}else if(studyInvestigator.isInActive()){
            		suiteRoleMembership.removeStudy(studyIdentifier);
            	}
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
	private List<String> getAllRSStudiesToAdd(String loginId){
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
     * This method will return a list if co-ordinating center assigned identifiers for studies which the research staff has access to. 
     * @param loginId
     * @return
     */
    @SuppressWarnings("unchecked")
	private List<String> getAllRSStudiesToRemove(String loginId){
    	Date d = new Date();
        StringBuilder hql = new StringBuilder("select distinct ids.value from StudyPersonnel sp ")
								        .append("join sp.studyOrganization so ")
								        .append("join so.study s ")
								        .append("join s.identifiers ids ")
								        .append("join sp.siteResearchStaff srs ")
								        .append("join srs.researchStaff rs ")
								        .append("where rs.loginId = :loginId ")
								        .append("and (sp.startDate > :stDate or (sp.startDate <= :stDate and sp.endDate <= :enDate))")
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

    /**
     * Will return all the Study accessible to the user.
     *
     * @param userName - the loginId of the user
     * @return
     */
    //BJ - Refactored to use the SuiteRoleMemberShip
    public List<IndexEntry> getAccessibleStudyIds(String userName){
        List<IndexEntry> entries = new ArrayList<IndexEntry>();
        gov.nih.nci.security.authorization.domainobjects.User csmUser = csmUserRepository.getCSMUserByName(userName);

        ProvisioningSession session = provisioningSessionFactory.createSession(csmUser.getUserId());
        
        List<UserGroupType> userGroups = getCsmUserRepository().getUserGroups(userName);
		for(UserGroupType userGroupType : userGroups){
            IndexEntry entry = new IndexEntry(userGroupType);
            entries.add(entry);

            List<Integer> studyIds = getAccessibleStudyIds(session, SuiteRole.getByCsmName(userGroupType.getCsmName()));
            entry.getEntityIds().addAll(studyIds);
		}
        return entries;
    }

    //will list the studies accessible to the user
    private List<Integer> getAccessibleStudyIds(ProvisioningSession session , SuiteRole suiteRole){

         List<Integer> studyIds = new ArrayList<Integer>();

         //global roles can be ignored
         if(!suiteRole.isScoped()) return studyIds;

         SuiteRoleMembership membership =  session.getProvisionableRoleMembership(suiteRole);
         if(membership == null) return studyIds;

         boolean studyScoped = suiteRole.isStudyScoped();
         boolean siteScoped = !studyScoped;
         boolean allSites = membership.isAllSites();
         boolean allStudies = membership.isAllStudies();

         if(allSites && allStudies || (siteScoped && allSites)){
             //all studies in the system
             studyIds.add(ALL_IDS_FABRICATED_ID);
             return studyIds;
         }

         if(studyScoped && !allStudies){
             List<String> studyIdentifiers = membership.getStudyIdentifiers();
             studyIds = getStudyIdsByIdentifiers(studyIdentifiers);
             return studyIds;
         }

         //-site scoped for specific sites  OR study scoped  all study roles
         List<String> orgIdentifiers =  membership.getSiteIdentifiers();
         // all studies belonging to the associated organizations
         List<Integer> orgIds = getOrganizationIdsByIdentifiersFromDB(orgIdentifiers);
         studyIds = getStudyIdsByOrganization(orgIds);
         return studyIds;


    }

	

    /**
     * Will return all the Organization accessible to the user.
     * 
     * @param userName - the loginId of the user
     * @return
     */
    //BJ - Refactored to use the SuiteRoleMemberShip
    public List<IndexEntry> getAccessibleOrganizationIds(String userName){
        List<IndexEntry> entries = new ArrayList<IndexEntry>();
        gov.nih.nci.security.authorization.domainobjects.User csmUser = csmUserRepository.getCSMUserByName(userName);

        ProvisioningSession session = provisioningSessionFactory.createSession(csmUser.getUserId());
        List<UserGroupType> userGroups = getCsmUserRepository().getUserGroups(userName);
		for(UserGroupType userGroupType : userGroups){
            IndexEntry entry = new IndexEntry(userGroupType);
            entries.add(entry);

            List<Integer> orgIds = getAccessibleOrganizationsForRole(session, SuiteRole.getByCsmName(userGroupType.getCsmName()));
            entry.getEntityIds().addAll(orgIds);
		}
        return entries;
    }
    
    //will return the organizations that a specific role can access
    private List<Integer> getAccessibleOrganizationsForRole(ProvisioningSession session, SuiteRole suiteRole){

        List<Integer> orgIds = new ArrayList<Integer>();
        if(!suiteRole.isScoped())  return orgIds;    //global roles
        SuiteRoleMembership membership =  session.getProvisionableRoleMembership(suiteRole);
        if(membership == null) return orgIds; // role is not provisioned

        if(membership.isAllSites()){
            orgIds.add(ALL_IDS_FABRICATED_ID);//all site access
            return orgIds;
        }

        //specific site access
        List<String> nciCodes = membership.getSiteIdentifiers();
        if(CollectionUtils.isEmpty(nciCodes)) return orgIds;
        return getOrganizationIdsByIdentifiersFromDB(nciCodes);

    }

    private List<Integer> getStudyIdsByOrganization(List<Integer> orgIds) {
    	List<Integer> resultList = new ArrayList<Integer>();
		HQLQuery query = new HQLQuery("select distinct s.id from Study s");
        if(orgIds != null){
            query = new HQLQuery("select distinct so.study.id from StudyOrganization so where so.organization.id in (:orgIds)" );
            query.setParameterList("orgIds", orgIds);
        }

		resultList = (List<Integer>) search(query);
		return resultList;
    }

    private List<Integer> getStudyIdsByIdentifiers(List<String> identifiers){
        String hql = " select distinct s.id from Study s join s.identifiers as identifier where identifier.value in (:identifiers)";
        HQLQuery query = new HQLQuery(hql.toString());
        query.setParameterList("identifiers", identifiers);
        List<Integer>  studyIds= (List<Integer>) search(query);
        return studyIds;
        
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
		log.debug("IN clearUserCache - " + userName);
		if (StringUtilities.isBlank(userName)) {
			return;
		}
		
		Long id = csmUserRepository.getCSMUserByName(userName).getUserId();
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

	public void setCsmUserRepository(CSMUserRepositoryImpl csmUserRepository) {
		this.csmUserRepository = csmUserRepository;
	}

	public CSMUserRepositoryImpl getCsmUserRepository() {
		return csmUserRepository;
	}

}