package gov.nih.nci.cabig.caaers.dao;

import java.util.List;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Rhett Sutphin
 */
@Transactional(readOnly=true)
public class ExpeditedAdverseEventReportDao extends GridIdentifiableDao<ExpeditedAdverseEventReport>
    implements MutableDomainObjectDao<ExpeditedAdverseEventReport>
{
    private static final String JOINS
        = " join o.adverseEventsInternal as adverseEvents join adverseEvents.ctcTerm as ctcTerm ";

    public Class<ExpeditedAdverseEventReport> domainClass() {
        return ExpeditedAdverseEventReport.class;
    }

    public List<ExpeditedAdverseEventReport> getByCriteria(
        String[] subnames, List<String> substringMatchProperties
    ) {
        return findBySubname(subnames, null, null, substringMatchProperties, null, JOINS);
    }

    @Transactional(readOnly=false)
    public void save(ExpeditedAdverseEventReport report) {
        getHibernateTemplate().saveOrUpdate(report);
        for (AdverseEvent ae : report.getAdverseEvents()) {
            getHibernateTemplate().saveOrUpdate(ae);
        }
        if (report.getReporter().isSavable()) {
            getHibernateTemplate().saveOrUpdate(report.getReporter());
        } else {
            log.debug("Reporter not savable; skipping cascade");
        }
        if (report.getPhysician().isSavable()) {
            getHibernateTemplate().saveOrUpdate(report.getPhysician());
        } else {
            log.debug("Physican not savable; skipping cascade");
        }
    }
}
