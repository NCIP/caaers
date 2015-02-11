package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.TreatmentInformation;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.CompositeMigrator;
import gov.nih.nci.cabig.caaers.service.migrator.ExpeditedAdverseEventReportConverterUtility;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class ExpeditedAdverseEventReportSynchronizer extends CompositeMigrator<ExpeditedAdverseEventReport> {
    
	private final ExpeditedAdverseEventReportConverterUtility utility = new ExpeditedAdverseEventReportConverterUtility();
	
	public void preMigrate(ExpeditedAdverseEventReport src, ExpeditedAdverseEventReport dest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {

        if(src.getInvestigationalDeviceAdministered() != null) dest.setInvestigationalDeviceAdministered(src.getInvestigationalDeviceAdministered());
        //set the created date is not present and is available in the source
        if(src.getCreatedAt() != null) dest.setCreatedAt(src.getCreatedAt());
        
        //TODO: sync the rest.
        migrateTreatment(src, dest);

    }

	private void migrateTreatment(ExpeditedAdverseEventReport src,
			ExpeditedAdverseEventReport dest) {
		TreatmentInformation destTreat = dest.getTreatmentInformation();
		if(destTreat == null) {
			destTreat = new TreatmentInformation();
			destTreat.setReport(dest);
		}
		
		final TreatmentInformation srcTreat = src.getTreatmentInformation();
		if(srcTreat != null) {
			if(srcTreat.getTreatmentDescription() != null) destTreat.setTreatmentDescription(srcTreat.getTreatmentDescription());
			if(srcTreat.getInvestigationalAgentAdministered() != null) destTreat.setInvestigationalAgentAdministered(srcTreat.getInvestigationalAgentAdministered());
			if(srcTreat.getAdverseEventCourse() != null) destTreat.setAdverseEventCourse(srcTreat.getAdverseEventCourse());
			if(srcTreat.getFirstCourseDate() != null) destTreat.setFirstCourseDate(srcTreat.getFirstCourseDate());
			if(srcTreat.getGridId() != null) destTreat.setGridId(srcTreat.getGridId());
			if(srcTreat.getTotalCourses() != null) destTreat.setTotalCourses(srcTreat.getTotalCourses());
			if(srcTreat.getTreatmentAssignmentDescription() != null) destTreat.setTreatmentAssignmentDescription(srcTreat.getTreatmentAssignmentDescription());
			
			final TreatmentAssignment taSrc = srcTreat.getTreatmentAssignment();
			if(taSrc != null) {
				TreatmentAssignment taDest = destTreat.getTreatmentAssignment();
				if(taDest == null) {
					taDest = new TreatmentAssignment();
				}
				
				if(taSrc.getCode() ==  null) taDest.setCode(taSrc.getCode());
				if(taSrc.getComments() ==  null) taDest.setComments(taSrc.getComments());
				if(taSrc.getDescription() ==  null) taDest.setDescription(taSrc.getDescription());
				if(taSrc.getDoseLevelOrder() ==  null) taDest.setDoseLevelOrder(taSrc.getDoseLevelOrder());
			}
		}
		
		dest.setTreatmentInformation(destTreat);
		
	}
}
