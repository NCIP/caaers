package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

public class AdverseEventReportingPeriodForReviewQueryTest extends TestCase {
	/**
	 * @author Biju Joseph
	 */
	public AdverseEventReportingPeriodForReviewQuery query;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		query = new AdverseEventReportingPeriodForReviewQuery();
	}
	
	public void testFilterByStudy() {
		query.filterByStudy(5);
		assertEquals("select rp from AdverseEventReportingPeriod rp  " +
				"join rp.assignment as spa  join spa.studySite as ss  join ss.study as s  " +
				"join spa.participant as p WHERE s.id =:studyId order by rp.id" , query.getQueryString());
	}

	public void testFilterByStudySite() {
		query.filterByStudySite(6);
		assertEquals("select rp from AdverseEventReportingPeriod rp  join rp.assignment as spa " +
				" join spa.studySite as ss  join ss.study as s  " +
				"join spa.participant as p WHERE ss.id =:studySiteId order by rp.id",query.getQueryString());
	}

	public void testFilterByParticipant() {
		query.filterByParticipant(7);
		assertEquals("select rp from AdverseEventReportingPeriod rp  join rp.assignment as spa  " +
				"join spa.studySite as ss  join ss.study as s  join spa.participant as p " +
				"WHERE p.id =:participantId order by rp.id",query.getQueryString());
	}
	
	public void testFilterByAll(){
		query.filterByParticipant(7);
		query.filterByStudySite(6);
		query.filterByStudy(5);
		assertEquals("select rp from AdverseEventReportingPeriod rp  join rp.assignment as spa  " +
				"join spa.studySite as ss  join ss.study as s  join spa.participant as p " +
				"WHERE p.id =:participantId AND ss.id =:studySiteId AND s.id =:studyId order by rp.id",query.getQueryString());
	}
	
}
