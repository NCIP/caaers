package gov.nih.nci.cabig.caaers.rules.dao;


import gov.nih.nci.cabig.caaers.rules.domain.DomainObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class AbstractDao<T extends DomainObject> extends HibernateDaoSupport {
    protected final Log log = LogFactory.getLog(getClass());

    @SuppressWarnings("unchecked")
    public T getById(int id) {
        return (T) getHibernateTemplate().get(domainClass(), id);
    }

	protected abstract Class domainClass();

}