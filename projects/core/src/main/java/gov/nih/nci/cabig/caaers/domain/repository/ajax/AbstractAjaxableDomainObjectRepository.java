package gov.nih.nci.cabig.caaers.domain.repository.ajax;

import gov.nih.nci.cabig.caaers.dao.query.ajax.AbstractAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.ajax.AbstractAjaxableDomainObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author Biju Joseph
 */
public abstract class AbstractAjaxableDomainObjectRepository extends HibernateDaoSupport {
    protected final Log log = LogFactory.getLog(getClass());

    /**
     * Search for objects using query
     *
     * @param query The query for finding organizations.
     * @return The list of organizations.
     */
    @SuppressWarnings({"unchecked"})
    //must not make this method as public method
    protected List<Object[]> find(final AbstractAjaxableDomainObjectQuery query) {
        String queryString = query.getQueryString();
        log.debug("::: " + queryString);
        List<Object[]> objectArray = (List<Object[]>) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(final Session session) throws HibernateException,
                    SQLException {
                org.hibernate.Query hiberanteQuery = session.createQuery(query.getQueryString());
                Map<String, Object> queryParameterMap = query.getParameterMap();
               // hiberanteQuery.setMaxResults(null);
                for (String key : queryParameterMap.keySet()) {
                    Object value = queryParameterMap.get(key);
                    hiberanteQuery.setParameter(key, value);


                }
                return hiberanteQuery.list();
            }

        });
        return objectArray;

    }

    protected AbstractAjaxableDomainObject getObjectById(List<? extends AbstractAjaxableDomainObject> ajaxableDomainObjects,
                                                         Integer id) {

        for (AbstractAjaxableDomainObject object : ajaxableDomainObjects) {
            if (object.getId().equals(id)) {
                return object;
            }
        }

        return null;
    }
}
