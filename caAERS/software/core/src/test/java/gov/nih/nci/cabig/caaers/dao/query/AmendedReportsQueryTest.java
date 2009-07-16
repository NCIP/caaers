package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class AmendedReportsQueryTest extends TestCase {
	
	AmendedReportsQuery query;
	protected void setUp() throws Exception {
		super.setUp();
		query = new AmendedReportsQuery();
	}

	public void testFilterByOrganization() {
		query.filterByOrganization(5);
		assertEquals("select r from ReportVersion rv join rv.report as r join " +
				"r.reportDefinition as rd WHERE rd.organization.id =:orgId " +
				"order by rv.amendedOn desc", query.getQueryString());
	}

	public void testFilterByReportType() {
		query.filterByReportType(5);
		assertEquals("select r from ReportVersion rv join rv.report as r join " +
				"r.reportDefinition as rd WHERE rd.reportType.id =:reportTypeId " +
				"order by rv.amendedOn desc", query.getQueryString());
	}

	public void testFilterByExpeditedAdverseEventReport() {
		query.filterByExpeditedAdverseEventReport(5);
		assertEquals("select r from ReportVersion rv join rv.report as r join " +
				"r.reportDefinition as rd WHERE r.aeReport.id =:aeReportId " +
				"order by rv.amendedOn desc", query.getQueryString());
	}

	public void testFilterByReportStatus() {
		query.filterByReportStatus(ReportStatus.AMENDED);
		assertEquals("select r from ReportVersion rv join rv.report as r " +
				"join r.reportDefinition as rd WHERE rv.reportStatus =:status " +
				"order by rv.amendedOn desc" ,query.getQueryString());
	}

	public void testGetQueryString() {
		query.filterByExpeditedAdverseEventReport(5);
		query.filterByOrganization(5);
		query.filterByReportStatus(ReportStatus.FAILED);
		query.filterByReportType(5);
		assertEquals("select r from ReportVersion rv join rv.report as r " +
				"join r.reportDefinition as rd " +
				"WHERE r.aeReport.id =:aeReportId AND rd.organization.id =:orgId AND " +
				"rv.reportStatus =:status AND rd.reportType.id =:reportTypeId " +
				"order by rv.amendedOn desc", query.getQueryString());
	}

}
