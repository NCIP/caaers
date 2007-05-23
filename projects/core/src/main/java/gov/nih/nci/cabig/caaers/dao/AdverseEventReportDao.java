package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Rhett Sutphin
 */
@Transactional
public class AdverseEventReportDao extends GridIdentifiableDao<AdverseEventReport>
    implements MutableDomainObjectDao<AdverseEventReport>
{
    public Class<AdverseEventReport> domainClass() {
        return AdverseEventReport.class;
    }

    public void save(AdverseEventReport report) {
        getHibernateTemplate().saveOrUpdate(report);
        for (AdverseEvent ae : report.getAdverseEvents()) {
            getHibernateTemplate().saveOrUpdate(ae);
        }
    }
}
