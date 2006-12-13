package gov.nih.nci.cabig.caaers.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import gov.nih.nci.cabig.caaers.domain.DomainObject;

import java.lang.annotation.ElementType;

/**
 * @author Rhett Sutphin
 */
/* TODO: much of this class is shared with PSC.  Refactor into a shared library. */
public abstract class CaaersDao<T extends DomainObject> extends HibernateDaoSupport {
    @SuppressWarnings("unchecked")
    public T getById(int id) {
        return (T) getHibernateTemplate().get(domainClass(), id);
    }

    public abstract Class<T> domainClass();
}
