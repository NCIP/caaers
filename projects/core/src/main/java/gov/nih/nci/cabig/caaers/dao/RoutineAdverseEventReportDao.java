package gov.nih.nci.cabig.caaers.dao;

import org.springframework.transaction.annotation.Transactional;

import gov.nih.nci.cabig.caaers.domain.RoutineAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

/**
 * @author Krikor Krumlian
 */
@Transactional(readOnly=true)
public class RoutineAdverseEventReportDao extends GridIdentifiableDao<RoutineAdverseEventReport>
    implements MutableDomainObjectDao<RoutineAdverseEventReport>
{
    public Class<RoutineAdverseEventReport> domainClass() {
        return RoutineAdverseEventReport.class;
    }

    @Transactional(readOnly=false)
    public void save(RoutineAdverseEventReport report) {
        getHibernateTemplate().saveOrUpdate(report);
        for (AdverseEvent ae : report.getAdverseEvents()) {
            getHibernateTemplate().saveOrUpdate(ae);
        }
    }
}
