package gov.nih.nci.cabig.caaers.domain.factory;

import com.semanticbits.aenotification.AENotification;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.RoutineAdverseEventReport;

import java.util.Date;

/**
 * @author Biju Joseph
 */
public class AENotificationFactory {

    public AENotification createAENotificationForExpeditedAdverseEventReport(final ExpeditedAdverseEventReport aeReport) {
        AENotification aeNotification = new AENotification();
        aeNotification.setRegistrationGridId(aeReport.getAssignment().getGridId());
        Date detectionDate = aeReport.getAdverseEvents().get(0).getStartDate();
        if (detectionDate == null) detectionDate = aeReport.getCreatedAt();
        aeNotification.setDetectionDate(new java.sql.Date(detectionDate.getTime()));
        aeNotification.setDescription(aeReport.getNotificationMessage());
        return aeNotification;

    }

    public AENotification createAENotificationForRoutineAdverseEventReport(final RoutineAdverseEventReport roReport) {
        AENotification aeNotification = new AENotification();
        aeNotification.setRegistrationGridId(roReport.getAssignment().getGridId());
        Date detectionDate = roReport.getAdverseEvents().get(0).getStartDate();
        if (detectionDate == null) detectionDate = roReport.getStartDate();
        aeNotification.setDetectionDate(new java.sql.Date(detectionDate.getTime()));
        aeNotification.setDescription(roReport.getNotificationMessage());
        return aeNotification;
    }
}