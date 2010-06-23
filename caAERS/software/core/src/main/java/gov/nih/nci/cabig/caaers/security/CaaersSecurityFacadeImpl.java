package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.CaaersUserProvisioningException;
import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.dao.security.RolePrivilegeDao;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.LocalInvestigator;
import gov.nih.nci.cabig.caaers.domain.LocalResearchStaff;
import gov.nih.nci.cabig.caaers.domain.RemoteInvestigator;
import gov.nih.nci.cabig.caaers.domain.RemoteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaffRole;
import gov.nih.nci.cabig.caaers.domain.User;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
	private String ORGANIZATION_PE="HealthcareSite";
	private String STUDY_PE="Study";

    public CaaersSecurityFacadeImpl() {
        instance = this;
    }

	//Roles for which we have to make an exception in terms of scoping
	private String USER_ADMINISTRATOR = "user_administrator";
	private String PO_INFO_MANAGER = "person_and_organization_information_manager";
	
	//For all Investigators
	private String AE_REPORTER = "ae_reporter";

    /**
     * Will check the authorization status
     *
     * @param auth            - An authentication object
     * @param objectPrivilege - An object privilege (Eg: - "Study:READ || Study:UPDATE)
     * @return
     */
    public boolean checkAuthorization(Authentication auth, String objectPrivilege) {
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
		//Fetch all the roles of the logged in user.
		//Granted Authorities is populated when user is authenticated. 

		try{
			GrantedAuthority[] authorities = authentication.getAuthorities();
			List<String> privilegedRoles;
			if(authorities  != null){
				//Fetch all the roles which have the given privilege on the given objectId
				privilegedRoles = rolePrivilegeDao.getRoles(objectId, privilege);				
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
    private void provisionInvestigator(Investigator investigator){
    	gov.nih.nci.security.authorization.domainobjects.User csmUser = csmUserRepository.getUserProvisioningManager().getUser(investigator.getLoginId());
    	if(csmUser == null){
    		return;
    	}
    	ProvisioningSession provisioningSession = provisioningSessionFactory.createSession(csmUser.getUserId());
		SuiteRoleMembership suiteRoleMembership = new SuiteRoleMembership(SuiteRole.getByCsmName(AE_REPORTER), null, null);
		
		List<String> orgIdentifiers = getAllOrganizationIdentifiers(investigator);
		if(orgIdentifiers == null || orgIdentifiers.isEmpty()){
			return;
		}
		for(String orgIdentifier : orgIdentifiers){
			suiteRoleMembership.addSite(orgIdentifier);
		}
		List<String> studyIndetifiers = getAllInvestigatorStudies(investigator.getLoginId());
		for(String studyIdentifier : studyIndetifiers){
			suiteRoleMembership.addStudy(studyIdentifier);
		}
		provisioningSession.replaceRole(suiteRoleMembership);
    }
    
    /**
     * Will only populate the role associations and protection group association for Investigator
     * @param researchStaff
     */
    private void provisionResearchStaff(ResearchStaff researchStaff){
       	gov.nih.nci.security.authorization.domainobjects.User csmUser = csmUserRepository.getUserProvisioningManager().getUser(researchStaff.getLoginId());
    	if(csmUser == null){
    		return;
    	}
    	ProvisioningSession provisioningSession = provisioningSessionFactory.createSession(csmUser.getUserId());
   		for(SiteResearchStaff eachSrs : researchStaff.getSiteResearchStaffs()){
			for(SiteResearchStaffRole eachSrsRole : eachSrs.getSiteResearchStaffRoles()){
				SuiteRole suiteRole = SuiteRole.getByCsmName(eachSrsRole.getRoleCode());
				if(eachSrsRole.isInActive()){
					provisioningSession.deleteRole(suiteRole);
				}else{
					if(suiteRole.isScoped()){
						SuiteRoleMembership suiteRoleMembership = new SuiteRoleMembership(suiteRole, null, null);
						List<String> orgIdentifiers = getAllOrganizationIdentifiers(researchStaff);
						if(orgIdentifiers == null || orgIdentifiers.isEmpty()){
							return;
						}
						if(suiteRole.isSiteScoped() && suiteRole.isStudyScoped()){
							for(String orgIdentifier : orgIdentifiers){
				    			suiteRoleMembership.addSite(orgIdentifier);
				    		}
							List<String> studyIndetifiers = getAllResearchStaffStudies(researchStaff.getLoginId());
				    		for(String studyIdentifier : studyIndetifiers){
				    			suiteRoleMembership.addStudy(studyIdentifier);
				    		}
						}else if(suiteRole.isSiteScoped()){
							if(suiteRole.getCsmName().equals(USER_ADMINISTRATOR) || suiteRole.getCsmName().equals(PO_INFO_MANAGER)){
								suiteRoleMembership.forAllSites();
							}else{
	    						for(String orgIdentifier : orgIdentifiers){
	    			    			suiteRoleMembership.addSite(orgIdentifier);
	    			    		}
							}
						}
						provisioningSession.replaceRole(suiteRoleMembership);
					}
				}
			}
		}
    }
    
    /**
     * This method returns a list of organization identifiers for a given user.
     * @param user
     * @return
     */
    private List<String> getAllOrganizationIdentifiers(User user){
    	List<String> orgIndetifiers = new ArrayList<String>();
    	if(user instanceof RemoteResearchStaff || user instanceof LocalResearchStaff){
    		List<SiteResearchStaff> siteRs = ((ResearchStaff)user).getSiteResearchStaffs();
    		for(SiteResearchStaff eachSiteRs : siteRs){
    			if(eachSiteRs.isActive()){
    				orgIndetifiers.add(eachSiteRs.getOrganization().getNciInstituteCode());
    			}
    		}
    	}else if(user instanceof RemoteInvestigator || user instanceof LocalInvestigator){
    		List<SiteInvestigator> siteInvs = ((Investigator)user).getSiteInvestigators();
    		for(SiteInvestigator eachSiteInv : siteInvs){
    			if(eachSiteInv.isActive()){
    				orgIndetifiers.add(eachSiteInv.getOrganization().getNciInstituteCode());
    			}
    		}
    	}
    	return orgIndetifiers;
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
    		csmUserRepository.createOrUpdateCSMUserAndGroupsForResearchStaff((ResearchStaff)user, changeURL);
    		provisionResearchStaff((ResearchStaff)user);
    	}else if(user instanceof RemoteInvestigator || user instanceof LocalInvestigator){
    		csmUserRepository.createOrUpdateCSMUserAndGroupsForInvestigator((Investigator)user, changeURL);
    		provisionInvestigator((Investigator)user);
    	}
    }

    /**
     * Will get the accessible protection element Ids (ObjectIDs) for the login.
     *
     * @param loginId - The loginId
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<String> getAccessibleProtectionElements(String loginId) {
		List<String> pes = new ArrayList<String>();
    	try {
			Set<ProtectionElementPrivilegeContext> contexts = csmUserRepository.getUserProvisioningManager().getProtectionElementPrivilegeContextForUser(loginId);
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
     * Will the caAERS database IDs of Study that one can access.
     *
     * @param loginId - The loginId
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Integer> getAccessibleStudyIds(String userName) {
    	String loginId = csmUserRepository.getCSMUserByName(userName).getUserId()+"";
    	List<Integer> resultList = new ArrayList<Integer>();
    	try {
			Set<ProtectionGroupRoleContext> contexts = csmUserRepository.getUserProvisioningManager().getProtectionGroupRoleContextForUser(loginId);
			List identifiers = new ArrayList();
			String hql = "";
			for (ProtectionGroupRoleContext context : contexts) {
				ProtectionGroup pe = context.getProtectionGroup();
				String roleName = getRoleName(context);
				String caaersEquivalentName = pe.getProtectionGroupName();// call SecurityObjectIdGenerator.toCaaersObjectName
				
				// if STUDY_PE , that means user have access to all studies (all means not all , he has access to studies 
				// on organizations which he belongs to as this role on that organization ) 
				
				if (roleName != null && caaersEquivalentName.equals(STUDY_PE)) {
					// get acessible organization with above role.  
					List<Integer> userOrgs = getAccessibleOrganizationIdsFilterByRole(loginId, roleName);
					if (userOrgs.size() > 0) {
						hql = "select distinct so.study.id from StudyOrganization so where so.organization.id in (:userOrgs) ";					
						HQLQuery query = new HQLQuery(hql.toString());
						query.setParameterList("userOrgs", userOrgs);
						resultList = (List<Integer>) search(query);
					}
				} else {
					//parse name ..
					String[] tokens = caaersEquivalentName.split("\\.");
					if (tokens.length == 2) {
						if (tokens[0].equals(STUDY_PE)) {
							identifiers.add(tokens[1]);
						}
					}					
				}

			}
			if (identifiers.size() > 0) {
				// get caAERS IDs from Studies table , primary key ..
				hql = " select distinct s.id from Study s join s.identifiers as identifier where identifier.value in (:identifiers)";
				HQLQuery query = new HQLQuery(hql.toString());
				query.setParameterList("identifiers", identifiers);
				resultList.addAll((List<Integer>) search(query));
			}
		} catch (CSObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultList ;  
    }


    /**
     * Will the caAERS database IDs of Organization that one can access.
     *
     * @param loginId - The loginId
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Integer> getAccessibleOrganizationIds(String userName) {
    	//get csm user DB id . 
    	String loginId = csmUserRepository.getCSMUserByName(userName).getUserId()+"";

    	List<Integer> resultList = new ArrayList<Integer>();
    	try {
			Set<ProtectionGroupRoleContext> contexts = csmUserRepository.getUserProvisioningManager().getProtectionGroupRoleContextForUser(loginId);
			List identifiers = new ArrayList();

			for (ProtectionGroupRoleContext context : contexts) {
				ProtectionGroup pe = context.getProtectionGroup();
				String caaersEquivalentName = pe.getProtectionGroupName();// call SecurityObjectIdGenerator.toCaaersObjectName

				if (caaersEquivalentName.equals(ORGANIZATION_PE)) {
					return getAllOrganizationIdsFromDB();
				} else {
					//parse name ..
					String[] tokens = caaersEquivalentName.split("\\.");
					if (tokens.length == 2) {
						if (tokens[0].equals(ORGANIZATION_PE)) {
							identifiers.add(tokens[1]);
						}
					}
				}
			}
			// get caAERS IDs from Organizations table , primary key ..
			if (identifiers.size() > 0) {
				return getOrganizationIdsByIdentifiersFromDB(identifiers);
			}
		} catch (CSObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultList ;  
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
			Set<ProtectionGroupRoleContext> contexts = csmUserRepository.getUserProvisioningManager().getProtectionGroupRoleContextForUser(loginId);
			List identifiers = new ArrayList();
			for (ProtectionGroupRoleContext context : contexts) {
				ProtectionGroup pe = context.getProtectionGroup();
				String caaersEquivalentName = pe.getProtectionGroupName();// call SecurityObjectIdGenerator.toCaaersObjectName
				String roleName = getRoleName(context);
				// check user has access to all orgs AS this role .
				if (roleName != null && roleName.equals(roleNameToCheck) && caaersEquivalentName.equals(ORGANIZATION_PE)) {
					return getAllOrganizationIdsFromDB();
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
			if (identifiers.size() > 0) {
				return getOrganizationIdsByIdentifiersFromDB(identifiers);
			}
		} catch (CSObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return new ArrayList<Integer>();
    }
    private String getRoleName(ProtectionGroupRoleContext context) {
    	Set roles = context.getRoles();
		Iterator itr = roles.iterator();

		while (itr.hasNext()) {
			Role role = (Role)itr.next();
			return  role.getName();
		}
		return null;
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