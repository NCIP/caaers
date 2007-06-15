package gov.nih.nci.cabig.caaers.dao;

import edu.nwu.bioinformatics.commons.CollectionUtils;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.ctms.dao.AbstractDomainObjectDao;
import gov.nih.nci.cabig.ctms.domain.DomainObject;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Rhett Sutphin
 */
public abstract class CaaersDao<T extends DomainObject> extends AbstractDomainObjectDao<T> {
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
        if (subnames == null || subnames.length == 0) return Collections.emptyList();

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
     * @see #searchByExample(gov.nih.nci.cabig.ctms.domain.DomainObject, boolean) 
     */
    public List<T> searchByExample(T example) {
        return searchByExample(example, true);
    }

    /**
     * Helper for DAOs which support domain classes with multiple identifiers.
     */
    @SuppressWarnings("unchecked")
    protected T findByIdentifier(final Identifier identifier) {
        return (T) CollectionUtils.firstElement(getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Criteria criteria = session.createCriteria(domainClass()).createCriteria("identifiers");

                if (identifier.getType() != null) {
                    criteria.add(Restrictions.eq("type", identifier.getType()));
                }

                if (identifier.getSource() != null) {
                    criteria.add(Restrictions.eq("source", identifier.getSource()));
                }

                if (identifier.getValue() != null) {
                    criteria.add(Restrictions.eq("value", identifier.getValue()));
                }

                return criteria.list();
            }
        }) );
    }
    
    /**
     * This method will reassociate the domain object to hibernate session.
     * @param o - the domain object instance that is to be reassociated
     */
    protected void reassociate(T o){
    	getHibernateTemplate().lock(o, LockMode.NONE);
    	
    }
}
