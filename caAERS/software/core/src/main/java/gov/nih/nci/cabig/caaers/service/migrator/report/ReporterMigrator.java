/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.report;

import org.apache.axis.utils.StringUtils;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

/**
 * @author :medaV Date: 1/14/13
 * @author Biju Joseph (Refactored)
 */
public class ReporterMigrator implements Migrator<ExpeditedAdverseEventReport> {
	

    public boolean isValid(ReportPerson reportPerson){
        if(reportPerson == null) return false;
        if(StringUtils.isEmpty(reportPerson.getEmailAddress()) &&
                StringUtils.isEmpty(reportPerson.getFirstName()) &&
                StringUtils.isEmpty(reportPerson.getLastName())) return false;

        return true;
    }

    /**
     * A reporter could be
     * 1) Research Staff
     * 2) If not an Investigator
     * 3) If not Manually entered
     * @param aeReportSrc
     * @param aeReportDest
     * @param outcome
     */
    public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
       if(aeReportSrc.getReporter() == null) return;
    	aeReportDest.setReporter(new Reporter());
        ReportPerson srcReportPerson = aeReportSrc.getReporter();
        ReportPerson destReportPerson = aeReportDest.getReporter();
        
        // the reporter can be null
        if(srcReportPerson == null) return;

        if (!isValid(srcReportPerson)) {
            outcome.addError("ER-RM-1", "Reporter is missing in the source");
            return;
        }

        StudySite site = aeReportDest.getAssignment().getStudySite();
        if (site == null) {
            outcome.addError("ER-RM-2", "Study Site is missing in the source");
            return;
        }

        // A reporter can be Manually entered OR a Research Staff OR an Investigator.

        // 2.1 Retrieve the Research Staff from Study Site.
        SiteResearchStaff siteResearchStaff = srcReportPerson.getEmailAddress() != null ? site.findSiteResearchStaffByEmail(srcReportPerson.getEmailAddress()) : null;
        siteResearchStaff = siteResearchStaff == null ? site.findSiteResearchStaffByName(srcReportPerson.getFirstName(), srcReportPerson.getLastName()) : null;
        if (siteResearchStaff != null) {
            destReportPerson.copy(siteResearchStaff);
            destReportPerson.copy(srcReportPerson);
            return;
        }
        // 2.2 Retrieve the Investigator from Study Site.
        SiteInvestigator siteInvestigator = srcReportPerson.getEmailAddress() != null ? site.findSiteInvestigatorByEmail(srcReportPerson.getEmailAddress()) : null;
        siteInvestigator = siteInvestigator == null ? site.findSiteInvestigatorByName(srcReportPerson.getFirstName(), srcReportPerson.getLastName()) : null;
        if (siteInvestigator != null) {
            destReportPerson.copy(siteInvestigator);
            destReportPerson.copy(srcReportPerson);
            return;
        }

        // 3. if investigator is not found create a new reporter and do a Manual copy.
        destReportPerson.copy(srcReportPerson);
    }

}
