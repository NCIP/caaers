package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.Ctc;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Rhett Sutphin
 */
@Transactional(readOnly=true)
public class CtcDao extends CaaersDao<Ctc> {
    public Class<Ctc> domainClass() {
        return Ctc.class;
    }

    @SuppressWarnings("unchecked")
    public List<Ctc> getAll() {
        return getHibernateTemplate().find("from Ctc");
    }

    public Ctc getCtcV2() {
        return getById(2);
    }

    public Ctc getCtcaeV3() {
        return getById(3);
    }
}
