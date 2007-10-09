package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.DiseaseTerm;
import java.util.List;

/**
 * @author Krikor Krumlian
 */
public class DiseaseTermDao extends CaaersDao<DiseaseTerm> {

	public Class<DiseaseTerm> domainClass() {
        return DiseaseTerm.class;
    }

	@SuppressWarnings("unchecked")
    public List<DiseaseTerm> getAll() {
        return getHibernateTemplate().find("from DiseaseTerm");
    }
    
    @SuppressWarnings("unchecked")
    public List<DiseaseTerm> getByCategoryId(Integer parentId) {
        return getHibernateTemplate().find("from DiseaseTerm dt where dt.category.id =?",
        		new Object[] { parentId });
    }
    
    @SuppressWarnings("unchecked")
    public DiseaseTerm getByTermName(final String name) {
		List<DiseaseTerm> results = getHibernateTemplate().find("from "+ domainClass().getName() +" where term= ?", name);
		return results.size() > 0 ? results.get(0) : null;
	}
    
    @SuppressWarnings("unchecked")
    public DiseaseTerm getByMeddra(final String name) {
		List<DiseaseTerm> results = getHibernateTemplate().find("from "+ domainClass().getName() +" where medraCode= ?", name);
		return results.size() > 0 ? results.get(0) : null;
	}
    
}
