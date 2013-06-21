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
public class PhysicianMigrator implements Migrator<ExpeditedAdverseEventReport> {


    public boolean isValid(ReportPerson reportPerson){
        if(reportPerson == null) return false;
        if(StringUtils.isEmpty(reportPerson.getEmailAddress()) &&
                StringUtils.isEmpty(reportPerson.getFirstName()) &&
                StringUtils.isEmpty(reportPerson.getLastName())) return false;

        return true;
    }

    /**
     * A Physician could be
     * 1) an Investigator
     * 2) If not Manually entered
     * @param aeReportSrc
     * @param aeReportDest
     * @param outcome
     */
    public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
        aeReportDest.setPhysician(new Physician());
        ReportPerson srcReportPerson = aeReportSrc.getPhysician();
        ReportPerson destReportPerson = aeReportDest.getPhysician();


        if (!isValid(srcReportPerson)) {
            outcome.addError("ER-PM-1", "Physician is missing in the source");
            return;
        }

        StudySite site = aeReportDest.getAssignment().getStudySite();
        if (site == null) {
            outcome.addError("ER-PM-2", "Study Site is missing in the source");
            return;
        }

        // A Physician can be Manually entered OR an Investigator.
        // 2.2 Retrieve the Investigator from Study Site.
        SiteInvestigator siteInvestigator = srcReportPerson.getEmailAddress() != null ? site.findSiteInvestigatorByEmail(srcReportPerson.getEmailAddress()) : null;
        siteInvestigator = siteInvestigator == null ? site.findSiteInvestigatorByName(srcReportPerson.getFirstName(), srcReportPerson.getLastName()) : null;
        if (siteInvestigator != null) {
            destReportPerson.copy(siteInvestigator);
            destReportPerson.copy(srcReportPerson);
            return;
        }

        // 3. if investigator is not found create a new physician and do a Manual copy.
        destReportPerson.copy(srcReportPerson);
    }

}
