package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import com.semanticbits.security.contentfilter.IdFetcher;
import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository;
import gov.nih.nci.cabig.caaers.utils.FetcherUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A base class of all IdFetcher implementations, which provides the basic infrastructure level requirements.
 *  
 * @author: Biju Joseph
 */
public abstract class AbstractIdFetcher extends HibernateDaoSupport implements IdFetcher {
    protected final Log log = LogFactory.getLog(AbstractIdFetcher.class);

    protected CSMUserRepository csmUserRepository;
    protected FetcherUtils fetcherUtils;

    /**
     * Will fetch the user identified by the loginId. 
     * @param loginId - username
     * @return   - A user
     */
    public User findUser(String loginId){
        return csmUserRepository.getUserByName(loginId);
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
                    if (value instanceof List) {
                    	hibernateQuery.setParameterList(key, (List) value);
                    } else {
                    	hibernateQuery.setParameter(key, value);
                    }
                    //hibernateQuery.setParameter(key, value);

                }
                return hibernateQuery.list();
            }

        });
    }

   


    /**
     * Find organization Id from SiteResearchStaff.
     * @param siteResearchStaffList - List of SiteResearchStaff
     * @return
     */
    protected Set<Integer> findOrganizationIdFromSiteResearchStaff(List<SiteResearchStaff> siteResearchStaffList){
       HashSet<Integer> set = new HashSet<Integer>();
       if(siteResearchStaffList != null){
            for(SiteResearchStaff srs : siteResearchStaffList){
               set.add(srs.getOrganization().getId());
            }
       }

        return set;
    }



    public CSMUserRepository getCsmUserRepository() {
        return csmUserRepository;
    }

    public void setCsmUserRepository(CSMUserRepository csmUserRepository) {
        this.csmUserRepository = csmUserRepository;
    }

    public FetcherUtils getFetcherUtils() {
        return fetcherUtils;
    }

    public void setFetcherUtils(FetcherUtils fetcherUtils) {
        this.fetcherUtils = fetcherUtils;
    }
    
}
