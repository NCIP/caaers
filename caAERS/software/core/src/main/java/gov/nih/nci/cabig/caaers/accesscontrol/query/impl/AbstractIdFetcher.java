/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.dao.query.NativeSQLQuery;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;

import java.sql.SQLException;
import java.util.*;

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

    protected CaaersSecurityFacade caaersSecurityFacade;
    private UserGroupType[] applicableSiteScopedRoles ; 
    private UserGroupType[] applicableStudyScopedRoles ;


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
    public String getSiteScopedSQL(UserGroupType role) {
    	return null;
    }

    /**
     * Will return the Study scoped SQL query
     * @return
     */
    public String getStudyScopedSQL(UserGroupType role) {
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
     * @param role
     * @param sql
     * @param nativeQuery
     * @return
     */
    private AbstractQuery createQuery(String loginId, UserGroupType role, String sql, boolean nativeQuery){
       AbstractQuery query;
       if(nativeQuery){
         query = new NativeSQLQuery(sql);
         ((NativeSQLQuery)query).setScalar("id", StandardBasicTypes.INTEGER);
       }else{
           query = new HQLQuery(sql);
       }
        
       if(loginId != null) query.getParameterMap().put("LOGIN_ID", loginId);

       //TODO : implement the role split logic

       if(role != null) query.getParameterMap().put(role.hqlAlias(), Boolean.TRUE) ;
       return query;

    }

    /**
     * Will fetch all the accessible subjectIds per-role
     * @param loginId - username
     * @return
     */
	public List fetch(String loginId){

       Map<Integer, IndexEntry> indexEntryMap = new LinkedHashMap<Integer, IndexEntry>();

        //for all site scoped roles
        UserGroupType[] siteScopedRoles = getApplicableSiteScopedRoles();
        if(siteScopedRoles != null){
              for(UserGroupType role : siteScopedRoles){
                  String sql = getSiteScopedSQL(role);
                  boolean nativeSQL = sql != null;
                  String hql = nativeSQL ? sql : getSiteScopedHQL(role);
                  if(hql != null){
                      AbstractQuery query = createQuery(loginId, role, hql, nativeSQL);
                      List<Integer> ids = (List<Integer>) search(query);
                      for(Integer id : ids){
                          IndexEntry entry = indexEntryMap.get(id);
                          if(entry == null){
                              entry = new IndexEntry(id);
                              indexEntryMap.put(id, entry);
                          }
                          entry.addRole(role);
                      }
                  }
              }
        }

        //for all study scoped roles
        UserGroupType[] studyScopedRolse = getApplicableStudyScopedRoles();
        if(studyScopedRolse != null){

          for(UserGroupType role : studyScopedRolse){
            String sql = getStudyScopedSQL(role);
            boolean nativeSQL = sql != null;
            String hql = nativeSQL ? sql : getStudyScopedHQL(role);

            if(hql != null){
                AbstractQuery query = createQuery(loginId, role, hql, nativeSQL);
                List<Integer> ids = (List<Integer>) search(query);
                for(Integer id : ids){
                    IndexEntry entry = indexEntryMap.get(id);
                    if(entry == null){
                        entry = new IndexEntry(id);
                        indexEntryMap.put(id, entry);
                    }
                    entry.addRole(role);
                }
            }
          }
        }

        List<IndexEntry> list = new ArrayList<IndexEntry>(indexEntryMap.values());
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



}
