/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import com.aparzev.lang.StringUtils;
import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.dao.query.NativeSQLQuery;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;
import gov.nih.nci.cabig.caaers.domain.index.OrganizationIndex;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;

import java.sql.SQLException;
import java.util.*;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.semanticbits.security.contentfilter.IdFetcher;

/**
 * A base class of all IdFetcher implementations, which provides the basic infrastructure level requirements.
 *  
 * @author Biju Joseph
 */
public abstract class AbstractIdFetcher extends HibernateDaoSupport implements IdFetcher {

    protected final Log log = LogFactory.getLog(AbstractIdFetcher.class);
    private static final String DB_NAME  = "databaseName";
    protected CaaersSecurityFacade caaersSecurityFacade;
    private UserGroupType[] applicableSiteScopedRoles ; 
    private UserGroupType[] applicableStudyScopedRoles ;
    private Properties properties;
    private String dbName;


    public String getDbName(){
        if(dbName == null){
            dbName = properties.getProperty(DB_NAME);
        }
        return dbName;
    }

    public boolean isPostgeSQL(){
        return StringUtils.equals("postgres", getDbName());
    }

    /**
     * Will return the Site scoped HQL query
     * @return
     */
    public String getSiteScopedHQL(UserGroupType role) {
    	return null;
    }

    /**
     * Will return the Study scoped HQL query
     * @return
     */
    public String getStudyScopedHQL(UserGroupType role) {
    	return null;
    }



    /**
     * Will return the Site scoped SQL query
     * @return
     */
    public String getSiteScopedSQL(UserGroupType role ) {
    	return null;
    }

    /**
     * Will return the Study scoped SQL query
     * @return
     */
    public String getStudyScopedSQL(UserGroupType role ) {
    	return null;
    }



    /**
     * All the Site scoped roles that require subject indexing
     * @return
     */
    public  UserGroupType[] getApplicableSiteScopedRoles() {
    	return this.applicableSiteScopedRoles;
    }


    /**
     * All the Study scoped roles that require subject indexing
     * @return
     */
    public UserGroupType[] getApplicableStudyScopedRoles() {
    	return this.applicableStudyScopedRoles;
    }

    /**
     * Generates the query
     * @param loginId
     * @param sql
     * @param nativeQuery
     * @return
     */
    protected AbstractQuery createQuery(String loginId, String sql, boolean nativeQuery){
       AbstractQuery query;
       if(nativeQuery){
         query = new NativeSQLQuery(sql);
         ((NativeSQLQuery)query).setScalar("id", StandardBasicTypes.INTEGER);
       }else{
           query = new HQLQuery(sql);
       }
        
       if(loginId != null) query.getParameterMap().put("LOGIN_ID", loginId);

       return query;

    }

    protected Integer getOrganizationAllSiteAccessRoles(String loginId ){
        AbstractQuery query = new HQLQuery("select role from OrganizationIndex oi where loginId=:LOGIN_ID and organization.id = " + Integer.MIN_VALUE);
        query.getParameterMap().put("LOGIN_ID", loginId);
        List<Integer> ids = (List<Integer>) search(query);
        if(!ids.isEmpty()) return ids.get(0) ;
        return 0;
    }


    protected Integer getStudyAllSiteAccessRoles(String loginId ){
        AbstractQuery query = new HQLQuery("select role from StudyIndex oi where loginId=:LOGIN_ID and study.id = " + Integer.MIN_VALUE);
        query.getParameterMap().put("LOGIN_ID", loginId);
        List<Integer> ids = (List<Integer>) search(query);
        if(!ids.isEmpty()) return ids.get(0) ;
        return 0;
    }


    /**
     * Will fetch all the accessible investigators per-role
     * @param loginId - username
     * @return
     */
    public List fetch(String loginId){

        IndexEntry allSiteEntry = new IndexEntry(Integer.MIN_VALUE,0);

        Map<Integer, IndexEntry> indexEntryMap = new LinkedHashMap<Integer, IndexEntry>();
        int orgAllSiteBit = getOrganizationAllSiteAccessRoles(loginId);



        //for all site scoped roles
        UserGroupType[] siteScopedRoles = getApplicableSiteScopedRoles();
        if(siteScopedRoles != null){
            for(UserGroupType role : siteScopedRoles){
                if(role.isSelected(orgAllSiteBit)){
                    allSiteEntry.addRole(role); continue;
                }

                //fetch the organization specific investigators
                String sql = getSiteScopedSQL(role);
                boolean nativeSql = sql != null;
                String hql = nativeSql ? sql : getSiteScopedHQL(role);
                AbstractQuery query = createQuery(loginId, hql, nativeSql);
                List<Integer> ids = (List<Integer>) search(query);
                if(CollectionUtils.isEmpty(ids)) continue;
                for(Integer id : ids){
                    if(!indexEntryMap.containsKey(id)) indexEntryMap.put(id, new IndexEntry(id,0));
                    indexEntryMap.get(id).addRole(role);
                }
            }
        }

        //for all study scoped roles
        UserGroupType[] studyScopedRoles = getApplicableStudyScopedRoles();
        if(studyScopedRoles != null){

            int studyAllSiteRoleBit = getStudyAllSiteAccessRoles(loginId);

            for(UserGroupType role : studyScopedRoles){
                //is all site all study ?  - all entities in the system
                if(role.isSelected(orgAllSiteBit) && role.isSelected(studyAllSiteRoleBit)){
                    allSiteEntry.addRole(role); continue;
                }

                //all other cases - I can access entities on the study-sites I manage .
                //What are study-site ? - study-orgs that I have access-to via orgainzation and study indexes.
                String sql = getStudyScopedSQL(role);
                boolean nativeSql = sql != null;
                String hql = nativeSql ? sql : getStudyScopedHQL(role);
                AbstractQuery query = createQuery(loginId, hql, nativeSql);
                List<Integer> ids = (List<Integer>) search(query);
                if(CollectionUtils.isEmpty(ids)) continue;
                for(Integer id : ids){
                    if(!indexEntryMap.containsKey(id)) indexEntryMap.put(id, new IndexEntry(id,0));
                    indexEntryMap.get(id).addRole(role);
                }
            }
        }

        List<IndexEntry> list = new ArrayList<IndexEntry>();

        if(allSiteEntry.hasRoles()) list.add(allSiteEntry);

        list.addAll(indexEntryMap.values());
        if(log.isInfoEnabled()){
            log.info("Fetcher (" + getClass().getName() + " fetched " + String.valueOf(list));
        }
        return list;
    }

    @SuppressWarnings("unchecked")
	public List<?> search(final AbstractQuery query){
       String queryString = query.getQueryString();
       if(log.isDebugEnabled()) log.debug("::: " + queryString);
       return (List<?>) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
                if(query instanceof NativeSQLQuery){
                    org.hibernate.SQLQuery nativeQuery = session.createSQLQuery(query.getQueryString());
                    Map<String, org.hibernate.type.Type> scalarMap = ((NativeSQLQuery) query).getScalarMap();
                    for(String key : scalarMap.keySet()){
                       nativeQuery.addScalar(key, scalarMap.get(key));
                    }
                    Map<String, Object> queryParameterMap = query.getParameterMap();
                    for (String key : queryParameterMap.keySet()) {
                        Object value = queryParameterMap.get(key);
                        nativeQuery.setParameter(key, value);
                    }
                    return nativeQuery.list();
                }else {
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
            }

        });
    }

	public void setCaaersSecurityFacade(CaaersSecurityFacade caaersSecurityFacade) {
		this.caaersSecurityFacade = caaersSecurityFacade;
	}

	public CaaersSecurityFacade getCaaersSecurityFacade() {
		return caaersSecurityFacade;
	}

	public void setApplicableSiteScopedRoles(
			UserGroupType[] applicableSiteScopedRoles) {
		this.applicableSiteScopedRoles = applicableSiteScopedRoles;
	}

	public void setApplicableStudyScopedRoles(
			UserGroupType[] applicableStudyScopedRoles) {
		this.applicableStudyScopedRoles = applicableStudyScopedRoles;
	}

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
