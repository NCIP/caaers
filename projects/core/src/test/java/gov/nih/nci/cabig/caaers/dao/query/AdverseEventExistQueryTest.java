package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.cabig.caaers.domain.AdverseEventCtcTerm;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import junit.framework.TestCase;

public class AdverseEventExistQueryTest extends TestCase {
	
	AdverseEventExistQuery query = new AdverseEventExistQuery();
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testFilterByDifferentAdverseEventId() {
		assertEquals("select ae from AdverseEventReportingPeriod rp  left join rp.adverseEvents as " +
				"ae  left outer join ae.lowLevelTerm as llt  left outer join ae.adverseEventTerm as " +
				"term", query.getQueryString());
		query.filterByDifferentAdverseEventId(55);
		assertEquals("select ae from AdverseEventReportingPeriod rp  left join rp.adverseEvents as ae  " +
				"left outer join ae.lowLevelTerm as llt  " +
				"left outer join ae.adverseEventTerm as term WHERE  ae.id != :aeid", query.getQueryString()) ;
	}

	public void testFilterByAdverseEventTerm() {
		assertEquals("select ae from AdverseEventReportingPeriod rp  left join rp.adverseEvents as " +
				"ae  left outer join ae.lowLevelTerm as llt  left outer join ae.adverseEventTerm as " +
				"term", query.getQueryString());
		AdverseEventCtcTerm term = new AdverseEventCtcTerm();
		CtcTerm ctcTerm = new CtcTerm();
		ctcTerm.setId(55);
		term.setTerm(ctcTerm);
		query.filterByAdverseEventTerm(term);
		assertEquals("select ae from AdverseEventReportingPeriod rp  left join rp.adverseEvents as ae  " +
				"left outer join ae.lowLevelTerm as llt  left outer join ae.adverseEventTerm as term " +
				"WHERE term.term.id = :termId", query.getQueryString());
	}

	public void testFilterByLowLevelTerm() {
		assertEquals("select ae from AdverseEventReportingPeriod rp  left join rp.adverseEvents as " +
				"ae  left outer join ae.lowLevelTerm as llt  left outer join ae.adverseEventTerm as " +
				"term", query.getQueryString());
		
		LowLevelTerm llt = new LowLevelTerm();
		llt.setId(55);
		query.filterByLowLevelTerm(llt);
		assertEquals("select ae from AdverseEventReportingPeriod rp  left join rp.adverseEvents as ae  left outer join ae.lowLevelTerm as llt  left outer join ae.adverseEventTerm as term WHERE llt.id = :lltId", query.getQueryString());
	}
	
	public void testFilterByReportingPeriodId(){
		query.filterByReportingPeriodId(55);
		assertEquals("select ae from AdverseEventReportingPeriod rp  left join rp.adverseEvents as ae  left outer join ae.lowLevelTerm as llt  left outer join ae.adverseEventTerm as term WHERE  rp.id = :rpid", query.getQueryString());
	}

}
