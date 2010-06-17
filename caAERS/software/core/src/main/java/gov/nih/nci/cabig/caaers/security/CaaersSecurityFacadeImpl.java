package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.dao.security.RolePrivilegeDao;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElementPrivilegeContext;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroupRoleContext;
import gov.nih.nci.security.authorization.domainobjects.Role;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import gov.nih.nci.security.provisioning.AuthorizationManagerImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * The Facade Layer to CSM. 
 * @author: Biju Joseph
 */
public class CaaersSecurityFacadeImpl extends HibernateDaoSupport implements CaaersSecurityFacade  {

    private static CaaersSecurityFacade instance;

	private AuthorizationManagerImpl csmUserProvisioningManager;
	private RolePrivilegeDao rolePrivilegeDao;
	
	private String ORGANIZATION_PE="HealthcareSite";
	private String STUDY_PE="Study";

    public CaaersSecurityFacadeImpl() {
        instance = this;
    }

    /**
     * Will check the authorization status.
     *
     * @param auth      - The acegi authentication object
     * @param objectId  - The secure object Id
     * @param privilege - The privilege (CREATE,UPDATE)
     * @return
     */



    public boolean checkAuthorization(Authentication authentication, String objectId, String privilege) {
		//Fetch all the roles of the logged in user.
		//Granted Authorities is populated when user is authenticated. 

        if (true) return true;

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
     * Will provision user in CSM, ie. will only populate the role associations and protection group association.
     *
     * @param user - The logged-in user.
     */
    public void provisionUser(User user) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Will create or update a csm user.
     *
     * @param csmUser - A user defined in CSM.
     */
    public void createOrUpdateCSMUser(gov.nih.nci.security.authorization.domainobjects.User csmUser) {
        //To change body of implemented methods use File | Settings | File Templates.
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
			Set<ProtectionElementPrivilegeContext> contexts = csmUserProvisioningManager.getProtectionElementPrivilegeContextForUser(loginId);
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
    public List<Integer> getAccessibleStudyIds(String loginId) {
    	List<Integer> resultList = new ArrayList<Integer>();
    	try {
			Set<ProtectionGroupRoleContext> contexts = csmUserProvisioningManager.getProtectionGroupRoleContextForUser(loginId);
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
	public List<Integer> getAccessibleOrganizationIds(String loginId) {
    	List<Integer> resultList = new ArrayList<Integer>();
    	try {
			Set<ProtectionGroupRoleContext> contexts = csmUserProvisioningManager.getProtectionGroupRoleContextForUser(loginId);
			List identifiers = new ArrayList();
			String hql = "";
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
			Set<ProtectionGroupRoleContext> contexts = csmUserProvisioningManager.getProtectionGroupRoleContextForUser(loginId);
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
    
    @SuppressWarnings("unchecked")
	private List<?> search(final AbstractQuery query){
    	String queryString = query.getQueryString();

       return (List<?>) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
                org.hibernate.Query hibernateQuery = session.createQuery(query.getQueryString());
                Map<String, Object> queryParameterMap = query.getParameterMap();
                for (String key : queryParameterMap.keySet()) {
                    Object value = queryParameterMap.get(key);
                    if (value instanceof Collection) {
                    	hibernateQuery.setParameterList(key, (Collection) value);
                    } else {
                    	hibernateQuery.setParameter(key, value);
                    }

                }
                return hibernateQuery.list();
            }

        });
    }

	public AuthorizationManagerImpl getCsmUserProvisioningManager() {
		return csmUserProvisioningManager;
	}

	public void setCsmUserProvisioningManager(
			AuthorizationManagerImpl csmUserProvisioningManager) {
		this.csmUserProvisioningManager = csmUserProvisioningManager;
	}
	
	public RolePrivilegeDao getRolePrivilegeDao() {
		return rolePrivilegeDao;
	}

	public void setRolePrivilegeDao(RolePrivilegeDao rolePrivilegeDao) {
		this.rolePrivilegeDao = rolePrivilegeDao;
	}

    public static CaaersSecurityFacade getInstance() {
        return instance;
    }

}
