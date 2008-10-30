package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.dao.CaaersDao;
import gov.nih.nci.cabig.caaers.domain.Condition;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Ion C. Olaru
 */

public class ConditionDao extends CaaersDao<Condition> {

    public Class<Condition> domainClass() {
        return Condition.class;
    }

    @SuppressWarnings("unchecked")
    public List<Condition> getAll() {
        return getHibernateTemplate().find("from Condition");
    }

    @Override
    public Condition getById(int id) {
        return super.getById(id);
    }

}