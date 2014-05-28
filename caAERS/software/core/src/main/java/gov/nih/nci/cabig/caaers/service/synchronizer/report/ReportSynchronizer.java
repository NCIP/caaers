/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * @author Ramakrishna Gundala
 */
public class ReportSynchronizer implements Migrator<ExpeditedAdverseEventReport> {
    public void migrate(ExpeditedAdverseEventReport xmlAeReport, ExpeditedAdverseEventReport dbAeReport, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {

        List<Report> newlyFoundReports = new ArrayList<Report>();

        if (xmlAeReport.getReports() == null || xmlAeReport.getReports().isEmpty()) {
            outcome.addWarning("RS-WR-1", "The input for Reports is null, so not performing any operation.");
            return;
        }

        //create an index of Reports
        HashMap<Integer, Report> reportsIndex = new HashMap<Integer, Report>();
        for(Report report : dbAeReport.getReports()){ reportsIndex.put(report.getId(), report);}

        //try to find the Report in source , if found synchronize it.
        for(Report report : xmlAeReport.getReports()){
        	if(!StringUtils.isBlank(report.getCaseNumber())){
        		Report reportFound = dbAeReport.findReportByCaseNumber(report.getCaseNumber());
	            if(reportFound != null) {
	                synchronizeReport(report, reportFound);
	            }else {
	                newlyFoundReports.add(reportFound);
	            }
        	}
        }

        //add the new Report that are present in source.
        for(Report report : newlyFoundReports){
            dbAeReport.addReport(report);
        }
    }

    /**
     * Copy the values from the XML input to db Report.
     * @param xmlReport
     * @param dbReport
     */
    public void synchronizeReport(Report xmlReport, Report dbReport){
    	
    	if(xmlReport.getLastVersion().getCcEmails() != null) {
    		dbReport.getLastVersion().setCcEmails(xmlReport.getLastVersion().getCcEmails());
    	}
    }
}
