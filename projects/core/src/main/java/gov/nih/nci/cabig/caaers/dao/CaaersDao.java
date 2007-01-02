package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.DomainObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author Rhett Sutphin
 */
/* TODO: much of this class is shared with PSC.  Refactor into a shared library. */
public abstract class CaaersDao<T extends DomainObject> extends HibernateDaoSupport {
    protected final Log log = LogFactory.getLog(getClass());

    @SuppressWarnings("unchecked")
    public T getById(int id) {
        return (T) getHibernateTemplate().get(domainClass(), id);
    }

    public abstract Class<T> domainClass();
}
