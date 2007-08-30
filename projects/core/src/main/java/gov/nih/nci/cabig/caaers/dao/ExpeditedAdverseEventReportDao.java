package gov.nih.nci.cabig.caaers.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.hibernate.LockMode;

/**
 * @author Rhett Sutphin
 * @author Krikor Krumlian
 */
@Transactional(readOnly=true)
public class ExpeditedAdverseEventReportDao extends GridIdentifiableDao<ExpeditedAdverseEventReport>
    implements MutableDomainObjectDao<ExpeditedAdverseEventReport>
{
    private static final String JOINS
        = " join o.adverseEventsInternal as adverseEvents join adverseEvents.adverseEventTerm as aeTerm join aeTerm.term as ctcTerm " +
        " join o.assignment as assignment join assignment.participant as p join p.identifiers as pIdentifier " +
        " join assignment.studySite as ss join ss.study as s join s.identifiers as sIdentifier";

    private ReportDao reportDao;

    @Override
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
        // since we can't cascade SAVE_UPDATE, we have to do this instead
//TODO : Review this and delete
//        for (Report r : report.getReports()) {
//            reportDao.save(r);
//        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void reassociate(ExpeditedAdverseEventReport report) {
        super.reassociate(report);
        if (report.getReporter().isTransient()) {
            log.debug("Reporter unsaved; skipping reassociate cascade");
        } else {
            getHibernateTemplate().lock(report.getReporter(), LockMode.NONE);
        }
        if (report.getPhysician().isTransient()) {
            log.debug("Physican unsaved; skipping reassociate cascade");
        } else {
            getHibernateTemplate().lock(report.getPhysician(), LockMode.NONE);
        }
        // delegate to ReportDao to reassociate reports so that it can control transactionality
        for (Report r : report.getReports()) {
            reportDao.reassociate(r);
        }
    }

    @SuppressWarnings("unchecked")
	public List<ExpeditedAdverseEventReport> searchExpeditedReports(Map props) throws ParseException{

		List<Object> params = new ArrayList<Object>();
		boolean firstClause = true;
		StringBuilder queryBuf = new StringBuilder(" select distinct o from ")
         .append(domainClass().getName()).append(" o ").append(JOINS);


		if (props.get("expeditedDate") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append(" o.detectionDate").append(" = ? ");
			String p = (String)props.get("expeditedDate");
			params.add(stringToDate(p));
			firstClause = false;
		}

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
			params.add( p.toLowerCase() );
			firstClause = false;
		}


		if (props.get("studyIdentifier") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("LOWER(").append("sIdentifier.value").append(") LIKE ?");
			String p = (String)props.get("studyIdentifier");
			params.add('%' + p.toLowerCase() + '%');
			firstClause = false;
		}
		if (props.get("studyShortTitle") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("LOWER(").append("s.shortTitle").append(") LIKE ?");
			String p = (String)props.get("studyShortTitle");
			params.add('%' + p.toLowerCase() + '%');
			firstClause = false;
		}
		if (props.get("participantIdentifier") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("LOWER(").append("pIdentifier.value").append(") LIKE ?");
			String p = (String)props.get("participantIdentifier");
			params.add('%' + p.toLowerCase() + '%');
			firstClause = false;
		}
		if (props.get("participantFirstName") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("LOWER(").append("p.firstName").append(") LIKE ?");
			String p = (String)props.get("participantFirstName");
			params.add('%' + p.toLowerCase() + '%');
			firstClause = false;
		}
		if (props.get("participantLastName") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("LOWER(").append("p.lastName").append(") LIKE ?");
			String p = (String)props.get("participantLastName");
			params.add('%' + p.toLowerCase() + '%');
			firstClause = false;
		}
		if (props.get("participantEthnicity") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("LOWER(").append("p.ethnicity").append(") LIKE ?");
			String p = (String)props.get("participantEthnicity");
			params.add( p.toLowerCase() );
			firstClause = false;
		}
		if (props.get("participantGender") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("LOWER(").append("p.gender").append(") LIKE ?");
			String p = (String)props.get("participantGender");
			params.add( p.toLowerCase() );
			firstClause = false;
		}

		if (props.get("participantDateOfBirth") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append(" p.dateOfBirth").append(" = ? ");
			String p = (String)props.get("participantDateOfBirth");
			params.add(stringToDate(p));
			firstClause = false;
		}

		log.debug("::: " + queryBuf.toString() );
		getHibernateTemplate().setMaxResults(CaaersDao.DEFAULT_MAX_RESULTS_SIZE);
		return getHibernateTemplate().find(queryBuf.toString(), params.toArray());
    }

    public void setReportDao(ReportDao reportDao) {
        this.reportDao = reportDao;
    }
}
