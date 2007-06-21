package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Rhett Sutphin
 */
@Transactional(readOnly=true)
public class ExpeditedAdverseEventReportDao extends GridIdentifiableDao<ExpeditedAdverseEventReport>
    implements MutableDomainObjectDao<ExpeditedAdverseEventReport>
{
    public Class<ExpeditedAdverseEventReport> domainClass() {
        return ExpeditedAdverseEventReport.class;
    }

    @Transactional(readOnly=false)
    public void save(ExpeditedAdverseEventReport report) {
        getHibernateTemplate().saveOrUpdate(report);
        for (AdverseEvent ae : report.getAdverseEvents()) {
            getHibernateTemplate().saveOrUpdate(ae);
        }
    }
}
