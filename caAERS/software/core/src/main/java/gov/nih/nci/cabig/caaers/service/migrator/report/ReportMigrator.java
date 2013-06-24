/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.report;

import java.util.List;

import com.aparzev.util.CollectionUtils;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;
import org.apache.cxf.common.util.StringUtils;

/**
 * User:medaV
 * Date: 1/28/13
 */
public class ReportMigrator implements Migrator<ExpeditedAdverseEventReport> {

    private ReportDefinitionDao reportDefinitionDao;

	public void setReportDefinitionDao(ReportDefinitionDao reportDefinitionDao) {
		this.reportDefinitionDao = reportDefinitionDao;
	}

	public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
    	
		List<Report> srcReports = aeReportSrc.getReports();

    	 if(CollectionUtils.isEmpty(srcReports) )  {
             outcome.addError("ER-RM-1", "Report Definitions are missing from the Source.");
             return;
         }
    	 
    	 // Iterate through the Source Reports.
    	 for ( Report rpt : srcReports ) {
    		String reportDefinitionName = rpt.getReportDefinition() != null ? rpt.getName() : null;
    		ReportDefinition repDef = loadReportDefinition(reportDefinitionName);
    		
    		if ( repDef == null ) {
                outcome.addError("ER-RM-4", "Unable to Load Report Definition for " + String.valueOf(reportDefinitionName));
                return;
    		}
            Report newReport = repDef.createReport();
            newReport.copy(rpt);
            aeReportDest.addReport(newReport);
    	 }

    }
	

	/**
	 *  load existing report definition from database.
	 * @param reportDefinitionName - The name of the report definition to load
	 * @return
	 */
	private ReportDefinition loadReportDefinition(String reportDefinitionName) {
        if(StringUtils.isEmpty(reportDefinitionName)) return null;
	    return reportDefinitionDao.getByName(reportDefinitionName);
	}

}
