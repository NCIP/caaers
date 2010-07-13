package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.CaaersNoSuchUserException;
import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.semanticbits.security.contentfilter.IdFetcher;

/**
 * A base class of all IdFetcher implementations, which provides the basic infrastructure level requirements.
 *  
 * @author: Biju Joseph
 */
public abstract class AbstractIdFetcher extends HibernateDaoSupport implements IdFetcher {

    protected final Log log = LogFactory.getLog(AbstractIdFetcher.class);

    protected CSMUserRepository csmUserRepository;
    
    protected CaaersSecurityFacade caaersSecurityFacade;
    protected final String ORG_INDEX_BASE_QUERY = "select oi.organization.id from OrganizationIndex oi where oi.roleCode = :ROLE_CODE and oi.loginId = :LOGIN_ID";
    protected final String STUDY_INDEX_BASE_QUERY = "select sti.study.id from StudyIndex sti where sti.roleCode = :ROLE_CODE and sti.loginId = :LOGIN_ID";

    /**
     * Will return the Site scoped HQL query
     * @return
     */
    public abstract String getSiteScopedHQL();

    /**
     * Will return the Study scoped HQL query
     * @return
     */
    public abstract String getStudyScopedHQL();


    /**
     * All the Site scoped roles that require subject indexing
     * @return
     */
    public abstract UserGroupType[] getApplicableSiteScopedRoles();


    /**
     * All the Study scoped roles that require subject indexing
     * @return
     */
    public abstract UserGroupType[] getApplicableStudyScopedRoles();

    /**
     * Will fetch all the accessible subjectIds per-role
     * @param loginId - username
     * @return
     */
	public List fetch(String loginId){

        List<IndexEntry> list = new ArrayList<IndexEntry>();

        //for all site scoped roles
        for(UserGroupType role : getApplicableSiteScopedRoles()){
            list.add(fetch(loginId, role, getSiteScopedHQL()));
        }

        //for all study scoped roles
        for(UserGroupType role : getApplicableStudyScopedRoles()){
            list.add(fetch(loginId, role, getStudyScopedHQL()));
        }

        return list;
	}


    /**
     * Will fetch the user identified by the loginId. 
     * @param loginId - username
     * @return   - A user
     */
    public User findUser(String loginId){
    	User user = null;
    	gov.nih.nci.security.authorization.domainobjects.User csmUser = null;
    	try{
    		user = csmUserRepository.getUserByName(loginId);
    	}catch(CaaersNoSuchUserException e){
    		return null;
    	}
        return user;
    }

    @SuppressWarnings("unchecked")
	public List<?> search(final AbstractQuery query){
    	String queryString = query.getQueryString();
        log.debug("::: " + queryString.toString());
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


    /**
     * Will execute the query and will return an IndexEntry
     * @param loginId
     * @param role
     * @param hql
     * @return
     */
    protected IndexEntry fetch(String loginId, UserGroupType role, String hql){
       IndexEntry entry = new IndexEntry(role);
       HQLQuery query = new HQLQuery(hql);
       query.getParameterMap().put("LOGIN_ID", loginId);
       query.getParameterMap().put("ROLE_CODE", role.getCode()) ;

       List<Integer> resultList = (List<Integer>) search(query);
       entry.setEntityIds(resultList);

       if(log.isDebugEnabled()){
           log.debug("HQL : " + hql + "\r\n [" + loginId + ", " + role.name() + "] fetched : " + String.valueOf(resultList));
       }

       return entry;
    }

    

   /*
	public  List fetchOld(String loginId) {

        List<Integer> participantIdList = null;

        User user = findUser(loginId);

        if(user != null){
            if(user instanceof ResearchStaff){
                participantIdList =  fetch((ResearchStaff)user);
            }else{
                participantIdList =  fetch((Investigator) user);
            }

           if(log.isDebugEnabled()){
             log.debug(getClass().getName() + " found, IDs accessible for [ " + loginId + " ] are : " + String.valueOf(participantIdList));
           }
        }

       return participantIdList;
	}
*/
    /**
     * Will fetch Ids of entities accessible to investigators
     * @param inv - An investigator
     * @return List of IDs of entities
     */
    public  List<Integer> fetch(Investigator inv){
    	return null;
    }

    /**
     * Will fetch Ids of entities accessible to research staff
     * @param rs - An research staff
     * @return List of IDs of entities
     */
    public List<Integer> fetch(ResearchStaff rs){
    	return null;
    }

    /**
     * Will return the OrganizationIds for the user for the roles mentioned. 
     * @param loginId
     * @param roles
     * @return
     */
    protected Set<Integer> getAccessibleOrganizations(String loginId, UserGroupType... roles){
       return null;
    }

    protected Set<Integer> getAccessibleStudys(String loginId, UserGroupType... roles){
        return null;
    }

/*
    protected List<Integer> getAccesibleOrganizationsIncludingStudySites(String loginId){
    	StringBuilder hql = new StringBuilder("select distinct oi.organization.id from  OrganizationIndex oi");
		hql.append(" where oi.loginId = :loginId ");
		HQLQuery query = new HQLQuery(hql.toString());
        query.setParameter("loginId", loginId);
        List<Integer> organizationIds = (List<Integer>) search(query);
        
        // get all orgs from DB ..
        String hqlStr = "select distinct o.id from Organization o ";
        query = new HQLQuery(hqlStr);
        List<Integer> allOrgsInDB = (List<Integer>) search(query);
        
        // check for these organizations , if these are SCC or SFS on any Study . 
        List<Integer> studySiteIds = new ArrayList<Integer>();
        if (organizationIds.size()>0) {
            if (organizationIds.size() != allOrgsInDB.size()) {
	        	StringBuilder sql = new StringBuilder("select distinct so.organization.id from StudyOrganization so where so.study.id in ");
	            sql.append(" (select distinct so.study.id from StudyOrganization so");
	            sql.append(" where so.class = 'SFS'");
	            sql.append(" or so.class = 'SCC'");
	            sql.append(" and so.organization.id in (:organizationIds) )");
	        	query = new HQLQuery(sql.toString());
	        	query.setParameterList("organizationIds", organizationIds);
	        	studySiteIds = (List<Integer>) search(query);
            } else {
            	//if org index has all orgs in DB , that means all study sites are applicable ..
            	List<Integer> allIds = new ArrayList<Integer>();
            	allIds.add(CaaersSecurityFacadeImpl.ALL_IDS_FABRICATED_ID);
            	return allIds;
            }
        }
        if (studySiteIds.size() > 0) {
        	organizationIds.addAll(studySiteIds);
        }
        return organizationIds;
    }
*/    


    public CSMUserRepository getCsmUserRepository() {
        return csmUserRepository;
    }

    public void setCsmUserRepository(CSMUserRepository csmUserRepository) {
        this.csmUserRepository = csmUserRepository;
    }

	public void setCaaersSecurityFacade(CaaersSecurityFacade caaersSecurityFacade) {
		this.caaersSecurityFacade = caaersSecurityFacade;
	}

	public CaaersSecurityFacade getCaaersSecurityFacade() {
		return caaersSecurityFacade;
	}

}
