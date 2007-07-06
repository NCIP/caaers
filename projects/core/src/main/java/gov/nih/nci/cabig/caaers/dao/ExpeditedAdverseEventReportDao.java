package gov.nih.nci.cabig.caaers.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.LockMode;

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

    @Override
    public void reassociate(ExpeditedAdverseEventReport report) {
        super.reassociate(report);
        if (report.getReporter().isSavable()) {
            getHibernateTemplate().lock(report.getReporter(), LockMode.NONE);
        } else {
            log.debug("Reporter not savable; skipping reassociate cascade");
        }
        if (report.getPhysician().isSavable()) {
            getHibernateTemplate().lock(report.getReporter(), LockMode.NONE);
        } else {
            log.debug("Physican not savable; skipping reassociate cascade");
        }
    }

    public List<ExpeditedAdverseEventReport> searchExpeditedReports(Map props) {

		List<Object> params = new ArrayList<Object>();
		boolean firstClause = true;
		StringBuilder queryBuf = new StringBuilder(" select distinct o from ")
         .append(domainClass().getName()).append(" o ").append(JOINS);
		
		if (props.get("ctcTerm") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("LOWER(").append("ctcTerm.term").append(") LIKE ?");
			String p = (String)props.get("ctcTerm");
			params.add('%' + p.toLowerCase() + '%');
			firstClause = false;
		}
		if (props.get("ctcCtepCode") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("LOWER(").append("ctcTerm.ctepCode").append(") LIKE ?");
			String p = (String)props.get("ctcCtepCode");
			params.add('%' + p.toLowerCase() + '%');
			firstClause = false;
		}
		
		if (props.get("ctcCategory") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("LOWER(").append("ctcTerm.category.name").append(") LIKE ?");
			String p = (String)props.get("ctcCategory");
			params.add('%' + p.toLowerCase() + '%');
			firstClause = false;
		}
	
		log.debug("::: " + queryBuf.toString() );
		return getHibernateTemplate().find(queryBuf.toString(), params.toArray());
    }
}
