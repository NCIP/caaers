package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.dao.query.InvestigatorQuery;
import gov.nih.nci.cabig.caaers.domain.Investigator;
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
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the Investigator domain object.
 * 
 * @author Kulasekaran
 * 
 */
@Transactional(readOnly = true)
public class InvestigatorDao extends GridIdentifiableDao<Investigator> implements
                MutableDomainObjectDao<Investigator> {
    private static final List<String> SUBSTRING_MATCH_PROPERTIES = Arrays.asList("firstName",
                    "lastName");

    private static final List<String> EXACT_MATCH_PROPERTIES = Collections.emptyList();

    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    @Override
    public Class<Investigator> domainClass() {
        return Investigator.class;
    }

    /**
     * Save the investigator.
     * 
     * @param investigator
     *                The investigator to be saved.
     */
    @Transactional(readOnly = false)
    public void save(final Investigator investigator) {
        getHibernateTemplate().saveOrUpdate(investigator);
    }

    /**
     * Get the list of investigators matching the name fragments.
     * 
     * @param subnames
     *                the name fragments to search on.
     * @return List of matching investigators.
     */
    public List<Investigator> getBySubnames(final String[] subnames) {
        return findBySubname(subnames, SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
    }

    /**
     * Gets the Investigator by id. This initialize the Investigator and load all the objects.
     * 
     * @param id
     *                the id
     * 
     * @return the Investigator by id
     */
    public Investigator getInvestigatorById(final int id) {
        Investigator investigator = (Investigator) getHibernateTemplate().get(domainClass(), id);
        initialize(investigator);

        return investigator;
    }

    /**
     * TODO
     * 
     * @param investigator
     * @return
     */
    public Investigator initialize(final Investigator investigator) {
        HibernateTemplate ht = getHibernateTemplate();
        ht.initialize(investigator.getSiteInvestigatorsInternal());

        return investigator;
    }

    /**
     * Get list of investigators based on query.
     * 
     * @param query
     *                The query used to search for investigators.
     * @return The list of investigators.
     */
    @SuppressWarnings("unchecked")
    public List<Investigator> searchInvestigator(final InvestigatorQuery query) {
        String queryString = query.getQueryString();
        log.debug("::: " + queryString.toString());
        return (List<Investigator>) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(final Session session) throws HibernateException,
                            SQLException {
                org.hibernate.Query hibernateQuery = session.createQuery(query.getQueryString());
                Map<String, Object> queryParameterMap = query.getParameterMap();
                for (String key : queryParameterMap.keySet()) {
                    Object value = queryParameterMap.get(key);
                    hibernateQuery.setParameter(key, value);

                }
                return hibernateQuery.list();
            }

        });
    }
    
    /**
     * Get the user who has specified email address.
     * 
     * @param loginId
     *                The loginId of the user.
     * @return The user.
     */
    public Investigator getByLoginId(String loginId) {
        List<Investigator> results = getHibernateTemplate().find(
                        "from Investigator where loginId= ?", loginId);
        Investigator investigator =  results.size() > 0 ? results.get(0) : null;
        if(investigator != null) initialize(investigator);
        return investigator;
    }

}