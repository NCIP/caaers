package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.Condition;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This dao perfoms the CURD operations on Study specific Condition
 * @author Ion C. Olaru
 */
@Transactional()
public class ConditionDao extends CaaersDao<Condition> {

    /**
     * The domain class this Dao represents in this case Condition
     * @return
     */
	@Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<Condition> domainClass() {
        return Condition.class;
    }

    /**
     * Will retrieve all the Condition objects from DB
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Condition> getAll() {
        HibernateTemplate ht;
        ht = getHibernateTemplate();
        return ht.find("from Condition");
    }

    /**
     * Will retrieve all the Condition objects from DB matching the condition name. 
     * @param text  - The condition name to match. 
     * @return - List of Condition objects. 
     */
    @SuppressWarnings("unchecked")
    public List<Condition> getAllByText(String text) {
        HibernateTemplate ht;
        ht = getHibernateTemplate();
        return ht.find("from Condition c where lower(c.conditionName) like ?", "%" + text.toLowerCase() + "%");
    }

    /**
     * Will get the Condition object matching the ID. 
     * @param id
     * @return
     */
    @Override
    public Condition getById(int id) {
        return super.getById(id);
    }

}