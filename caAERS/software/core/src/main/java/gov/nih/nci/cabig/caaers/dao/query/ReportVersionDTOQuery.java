/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.cabig.caaers.domain.ReportStatus;

import java.util.Date;

/*
*
* @author Ion C. Olaru
* 
* */
public class ReportVersionDTOQuery extends AbstractQuery {

    public ReportVersionDTOQuery() {
        super("select new gov.nih.nci.cabig.caaers.domain.report.ReportVersionDTO(rv, rd.label, subject.firstName, subject.lastName, study.shortTitle, identifier.value, aer.id, report.id, period.cycleNumber, period.startDate, site.name, site.nciInstituteCode, assignment.id, studyIdentifier.value) " +
                "from ExpeditedAdverseEventReport aer");
        join("aer.reports as report");
        join("report.reportVersions as rv");
        join("report.reportDefinition as rd");
        // join("report.aeReport as aeReport");
        join("aer.reportingPeriod as period");
        join("period.assignment as assignment");
        join("assignment.participant as subject");
//        join("subject.identifiers as identifier");
        leftJoin("subject.identifiers as identifier with identifier.primaryIndicator = :primaryIndicator");
        join("assignment.studySite as ss");
        join("ss.organization as site");
        join("ss.study as study");
        leftJoin("study.identifiers as studyIdentifier with studyIdentifier.primaryIndicator = :primaryIndicator");
//        andWhere("identifier.primaryIndicator = :primaryIndicator");
        setParameter("primaryIndicator", Boolean.TRUE);
    }

    public void filterByReportStatus(ReportStatus status) {
        orWhere("rv.reportStatus = :status");
        setParameter("status", status);
    }

    public void filterByDatesBetween(Date startDate, Date endDate){
        andWhere("( " +
                "(rv.dueOn >= :startDate and rv.dueOn <= :endDate) or " +
                "(rv.submittedOn >= :startDate and rv.submittedOn <= :endDate) or " +
                "(rv.withdrawnOn >= :startDate and rv.withdrawnOn <= :endDate)" +
                " )");
        setParameter("startDate", startDate);
        setParameter("endDate", endDate);
    }

}
