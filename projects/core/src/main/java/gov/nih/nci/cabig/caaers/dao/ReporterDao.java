package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.Reporter;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author Kulasekaran
 */
@Transactional(readOnly=true)
public class ReporterDao extends GridIdentifiableDao<Reporter> {
    public Class<Reporter> domainClass() {
        return Reporter.class;
    }

    @Transactional(readOnly=false)
    public void save(Reporter reporter) {
        getHibernateTemplate().saveOrUpdate(reporter);
    }
}

