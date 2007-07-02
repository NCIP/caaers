package gov.nih.nci.cabig.caaers.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
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
		= " join o.adverseEventsInternal as adverseEvents join adverseEvents.ctcTerm as ctcTerm ";
	
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
    
    public List<RoutineAdverseEventReport> searchRoutineReports(Map props) {

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
