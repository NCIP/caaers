package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.Reporter;


/**
 * @author Kulasekaran
 */
public class ReporterDao extends GridIdentifiableDao<Reporter> {
    public Class<Reporter> domainClass() {
        return Reporter.class;
    }

    public void save(Reporter reporter) {
        getHibernateTemplate().saveOrUpdate(reporter);
    }
}

