package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.DomainObject;
import gov.nih.nci.cabig.caaers.domain.Participant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.sql.SQLException;

/**
 * @author Rhett Sutphin
 */
/* TODO: much of this class is shared with PSC.  Refactor into a shared library. */
public abstract class CaaersDao<T extends DomainObject> extends HibernateDaoSupport {
    protected final Log log = LogFactory.getLog(getClass());

    public abstract Class<T> domainClass();

    @SuppressWarnings("unchecked")
    public T getById(int id) {
        return (T) getHibernateTemplate().get(domainClass(), id);
    }

    /**
     * A variation of {@link #findBySubname} that does not allow for extra conditions
     */
    protected List<T> findBySubname(
        String[] subnames, List<String> substringMatchProperties, List<String> exactMatchProperties
    ) {
        return findBySubname(subnames, null, null, substringMatchProperties, exactMatchProperties);
    }

    /**
     * A query builder for use by subclass DAOs.  It makes it easy to match a fragment of a name
     * or identifier against multiple properties.  This is intended for use in implementing
     * user-friendly dynamic searches; e.g., autocompleters.
     *
     * @param subnames the name fragments to search on
     * @param extraConditions custom HQL conditions with which to constrain the fragment matches
     * @param extraParameters parameters for the custom conditions
     * @param substringMatchProperties a list of properties of the implementing object which should
     *          be matched as case-insensitive substrings
     * @param exactMatchProperties a list of properties which should be matched as case-insensitive
     *          full strings
     * @return a list of matching domain object instances
     */
    @SuppressWarnings("unchecked")
    protected List<T> findBySubname(
        String[] subnames, String extraConditions, List<Object> extraParameters,
        List<String> substringMatchProperties, List<String> exactMatchProperties
    ) {
        StringBuilder query = new StringBuilder("from ")
            .append(domainClass().getName()).append(" o where ");
        if (extraConditions != null) query.append(extraConditions).append(" and ");
        List<Object> params = new LinkedList<Object>();
        if (extraParameters != null) params.addAll(extraParameters);

        for (int i = 0; i < subnames.length; i++) {
            buildSubnameQuery(subnames[i], query, params,
                substringMatchProperties, exactMatchProperties);
            if (i < subnames.length - 1) query.append(" and ");
        }
        
        return getHibernateTemplate().find(query.toString(), params.toArray());
    }

    private void buildSubnameQuery(
        String subname, StringBuilder query, List<Object> params,
        List<String> substringMatchProperties, List<String> exactMatchProperties
    ) {
        query.append('(');
        if (hasAny(substringMatchProperties)) {
            for (Iterator<String> it = substringMatchProperties.iterator(); it.hasNext();) {
                String prop = it.next();
                query.append("LOWER(o.").append(prop).append(") LIKE ?");
                params.add('%' + subname.toLowerCase() + '%');
                if (it.hasNext()) query.append(" or ");
            }
            if (hasAny(exactMatchProperties)) {
                query.append(" or ");
            }
        }
        if (hasAny(exactMatchProperties)) {
            for (Iterator<String> it = exactMatchProperties.iterator(); it.hasNext();) {
                String prop = it.next();
                query.append("LOWER(o.").append(prop).append(") = ?");
                params.add(subname.toLowerCase());
                if (it.hasNext()) query.append(" or ");
            }
        }
        query.append(')');
    }

    private boolean hasAny(List<String> properties) {
        return properties != null && properties.size() > 0;
    }

    /**
     * Searches based on an example object. For example, if you want to
     * search for a Participant based on last name:
     * <ul>
     * <li><code>Participant sample = new Participant();</code></li>
     * <li><code>sample.setLastName("last_namee");</code></li>
     * <li><code>participantDao.searchByExample(study)</code></li>
     * </ul>
     *
     * @return list of matching instances based on your sample object
     */
    @SuppressWarnings("unchecked")
    public List<T> searchByExample(final T sample, final boolean inexactMatches) {
        return (List<T>) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Example example = Example.create(sample).excludeZeroes();
                if (inexactMatches) example.ignoreCase().enableLike(MatchMode.ANYWHERE);

                return session.createCriteria(domainClass()).add(example).list();
            }
        });
    }

    /**
     * Default search -- case insensitive substring matches
     *
     * @see #searchByExample(gov.nih.nci.cabig.caaers.domain.DomainObject, boolean) 
     */
    public List<T> searchByExample(T example) {
        return searchByExample(example, true);
    }
}
