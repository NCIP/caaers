package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.dao.query.ResearchStaffQuery;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the ResearchStaff domain object.
 * 
 * @author Kulasekaran
 */
@Transactional(readOnly = true)
public class ResearchStaffDao extends GridIdentifiableDao<ResearchStaff> implements
                MutableDomainObjectDao<ResearchStaff> {

    private static final List<String> SUBSTRING_MATCH_PROPERTIES = Arrays.asList("firstName",
                    "lastName");
    
    private static final List<String> NCIIDENTIFIER_MATCH_PROPERTIES = Arrays.asList("nciIdentifier");

    private static final List<String> EXACT_MATCH_PROPERTIES = Collections.emptyList();

    private static final List<Object> EXTRA_PARAMS = Collections.emptyList();

    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    @Override
    public Class<ResearchStaff> domainClass() {
        return ResearchStaff.class;
    }

    /**
     * Save or update the research staff in the db.
     * 
     * @param The
     *                research staff.
     */
    @Transactional(readOnly = false)
    public void save(final ResearchStaff researchStaff) {
        getHibernateTemplate().saveOrUpdate(researchStaff);
    }
    @SuppressWarnings( { "unchecked" })
    public List<ResearchStaff> findResearchStaff(final ResearchStaffQuery query) {
    	return searchResearchStaff(query);
    }
    /**
     * Search for research staffs using query.
     * 
     * @param query
     *                The query used to search for research staffs
     * @return The list of research staffs.
     */
    @SuppressWarnings( { "unchecked" })
    public List<ResearchStaff> searchResearchStaff(final ResearchStaffQuery query) {
        String queryString = query.getQueryString();
        log.debug("::: " + queryString.toString());
        return (List<ResearchStaff>) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(final Session session) throws HibernateException,
                            SQLException {
                org.hibernate.Query hiberanteQuery = session.createQuery(query.getQueryString());
                Map<String, Object> queryParameterMap = query.getParameterMap();
                for (String key : queryParameterMap.keySet()) {
                    Object value = queryParameterMap.get(key);
                    hiberanteQuery.setParameter(key, value);

                }
                return hiberanteQuery.list();
            }

        });

    }

    /**
     * Get the list of research staffs matching the name fragments and belonging to specified site.
     * 
     * @param subnames
     *                the name fragments to search on.
     * @param site
     *                The organization ID of the site.
     * @return List of matching research staffs.
     */
    public List<ResearchStaff> getBySubnames(final String[] subnames, final int site) {

        return findBySubname(subnames, "o.organization.id = '" + site + "'", EXTRA_PARAMS,
                        SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
    }
    
    /**
     * Get the list of research staffs matching the NciIdentifier and belonging to specified site.
     * 
     * @param subnames
     *                the name fragments to search on.
     * @param site
     *                The organization ID of the site.
     * @return List of matching research staffs.
     */
    public List<ResearchStaff> getByNciIdentifier(final String[] subnames, final int site) {

        return findBySubname(subnames, "o.organization.id = '" + site + "'", EXTRA_PARAMS,
        		NCIIDENTIFIER_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
    }
  

}