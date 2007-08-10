package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.MeddraVersion;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Krikor Krumlian
 */
@Transactional(readOnly=true)
public class MeddraVersionDao extends CaaersDao<MeddraVersion> {
    public Class<MeddraVersion> domainClass() {
        return MeddraVersion.class;
    }

    @SuppressWarnings("unchecked")
    public List<MeddraVersion> getAll() {
        return getHibernateTemplate().find("from MeddraVersion");
    }

    public MeddraVersion getMeddraV9() {
        return getById(9);
    }
}
