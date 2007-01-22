package gov.nih.nci.cabig.caaers.dao;


import edu.nwu.bioinformatics.commons.CollectionUtils;
import gov.nih.nci.cabig.caaers.domain.DomainObject;
import gov.nih.nci.cabig.caaers.domain.GridIdentifiable;
import gov.nih.nci.cabig.caaers.domain.Study;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public abstract class GridIdentifiableDao<T extends DomainObject & GridIdentifiable> extends CaaersDao<T>{
    
	public T getByGridId(String gridId) {
        Study example = new Study();
        example.setGridId(gridId);
        return (T) CollectionUtils.firstElement(getHibernateTemplate().findByExample(example));
    }

    public T getByGridId(T template) {
        return getByGridId(template.getGridId());
    }
}
