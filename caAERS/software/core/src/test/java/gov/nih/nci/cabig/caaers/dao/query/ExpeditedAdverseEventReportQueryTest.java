/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

/*
*
* @author Ion C. Olaru
*
* */
public class ExpeditedAdverseEventReportQueryTest extends TestCase{

	public void testQueryConstructor() throws Exception {
		ReportVersionDTOQuery q = new ReportVersionDTOQuery();
        assertEquals("Wrong parsing for constructor",
                "select new gov.nih.nci.cabig.caaers.domain.report.ReportVersionDTO(rv, rd.label, subject.firstName, " +
                        "subject.lastName, study.shortTitle, identifier.value, aer.id, report.id, period.cycleNumber, " +
                        "period.startDate, site.name, site.nciInstituteCode) from ExpeditedAdverseEventReport aer " +
                        "join aer.reports as report join report.reportVersions as rv " +
                        "join report.reportDefinition as rd " +
                        "join aer.reportingPeriod as period " +
                        "join period.assignment as assignment " +
                        "join assignment.participant as subject " +
                        "left join subject.identifiers as identifier with identifier.primaryIndicator = :primaryIndicator " +
                        "join assignment.studySite as ss " +
                        "join ss.organization as site " +
                        "join ss.study as study"
                        , q.getQueryString());
    }

}
