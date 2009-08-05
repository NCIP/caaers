package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.Condition;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ion C. Olaru
 */
@Transactional()
public class ConditionDao extends CaaersDao<Condition> {
	@Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<Condition> domainClass() {
        return Condition.class;
    }

    @SuppressWarnings("unchecked")
    public List<Condition> getAll() {
        HibernateTemplate ht;
        ht = getHibernateTemplate();
        return ht.find("from Condition");
    }

    @SuppressWarnings("unchecked")
    public List<Condition> getAllByText(String text) {
        HibernateTemplate ht;
        ht = getHibernateTemplate();
        return ht.find("from Condition c where lower(c.conditionName) like ?", "%" + text.toLowerCase() + "%");
    }

    @Override
    public Condition getById(int id) {
        return super.getById(id);
    }

}