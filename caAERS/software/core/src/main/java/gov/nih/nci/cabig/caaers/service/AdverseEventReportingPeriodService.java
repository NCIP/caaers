package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;

/**
 * @author Ion C. Olaru
 *         Date: 2/24/12 -4:30 PM
 */
public class AdverseEventReportingPeriodService {

    /**
     * This method synchronize information from Reports with the one on Course
     * @param aerp gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod object to sync the reports of.
     */
    public void synchronizeReports(AdverseEventReportingPeriod aerp) {
        if (aerp == null || aerp.getAeReports().size() == 0) return;

        for (ExpeditedAdverseEventReport r : aerp.getAeReports()) {
            if (r.getTreatmentInformation() == null) continue;

            r.getTreatmentInformation().setFirstCourseDate(aerp.getAssignment().getStartDateOfFirstCourse());
            r.getTreatmentInformation().getAdverseEventCourse().setDate(aerp.getStartDate());
            r.getTreatmentInformation().setTreatmentAssignment(aerp.getTreatmentAssignment());
            r.getTreatmentInformation().setTreatmentAssignmentDescription(aerp.getTreatmentAssignmentDescription());
            r.getTreatmentInformation().getAdverseEventCourse().setNumber(aerp.getCycleNumber());
        }
    }
}
