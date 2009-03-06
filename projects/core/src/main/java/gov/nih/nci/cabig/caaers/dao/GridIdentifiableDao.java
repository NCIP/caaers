package gov.nih.nci.cabig.caaers.dao;

import edu.nwu.bioinformatics.commons.CollectionUtils;
import gov.nih.nci.cabig.ctms.domain.DomainObject;
import gov.nih.nci.cabig.ctms.domain.GridIdentifiable;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Sujith Vellat Thayyilthodi
 * @author Rhett Sutphin
 */
@Transactional(readOnly = true)
public abstract class GridIdentifiableDao<T extends DomainObject & GridIdentifiable> extends CaaersDao<T> implements gov.nih.nci.cabig.ctms.dao.GridIdentifiableDao<T> {
    /**
     * Get object of class&ltT&gt specified by the grid ID in the template.
     * 
     * @param template
     *                The template that contains the grid ID.
     * @return The object of class&ltT&gt.
     */
    @SuppressWarnings("unchecked")
    public T getByGridId(T template) {
        return (T) CollectionUtils.firstElement(getHibernateTemplate().findByExample(template));
    }

    /*
     * TODO: this doesn't seem to be used anywhere. Probably can safely remove it.
     * @SuppressWarnings("unchecked") public T getByExample(T template, String[] propertyNames,
     * DomainObject[] propertyValues) { Criteria criteria =
     * getSession().createCriteria(template.getClass()).add(Example.create(template)); for (int
     * count = 0; count < propertyNames.length; count++) {
     * criteria.createCriteria(propertyNames[count]).add(Example.create(propertyValues[count])); }
     * return (T) CollectionUtils.firstElement(criteria.list()); }
     */
    /**
     * Get object of class&ltT&gt specified by the grid ID.
     * 
     * @param gridId
     *                The grid ID of the object.
     * @return The object of class&ltT&gt.
     */
    @SuppressWarnings("unchecked")
    public T getByGridId(String gridId) {
        StringBuilder query = new StringBuilder("from ").append(domainClass().getName()).append(" o where gridId = ?");
        Object[] params = { gridId };
        return (T) CollectionUtils.firstElement(getHibernateTemplate().find(query.toString(), params));
    }
}
