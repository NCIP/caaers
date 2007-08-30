package gov.nih.nci.cabig.caaers.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	
	 private static final String JOINS 
		= " join o.adverseEventsInternal as adverseEvents join adverseEvents.adverseEventTerm as aeTerm join aeTerm.term as ctcTerm "  + 
		  " join o.assignment as assignment join assignment.participant as p join p.identifiers as pIdentifier " + 
          " join assignment.studySite as ss join ss.study as s join s.identifiers as sIdentifier";
	
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
    
    @SuppressWarnings("unchecked")
	public List<RoutineAdverseEventReport> searchRoutineReports(Map props) throws ParseException {

		List<Object> params = new ArrayList<Object>();
		boolean firstClause = true;
		StringBuilder queryBuf = new StringBuilder(" select distinct o from ")
         .append(domainClass().getName()).append(" o ").append(JOINS);
		
		
		if (props.get("date") != null) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("(  o.startDate").append(" = ? ");
			queryBuf.append("or   o.endDate").append(" = ? )");
			String p = (String)props.get("date");
			params.add(stringToDate(p));
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
}
