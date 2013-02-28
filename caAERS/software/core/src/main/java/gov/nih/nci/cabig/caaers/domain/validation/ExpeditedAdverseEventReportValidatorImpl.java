/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.validation;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Lab;
import gov.nih.nci.cabig.caaers.domain.MedicalDevice;
import gov.nih.nci.cabig.caaers.domain.StudyDevice;
import gov.nih.nci.cabig.caaers.domain.TreatmentInformation;
import gov.nih.nci.security.util.StringUtilities;

import java.util.Date;

import org.springframework.validation.Errors;

/**
 * This class validates an ExpeditedAdverseEventReport object.
 * 
 * Following validations are done by this validator
 * Study Device: either device or one among (commonName,brandName) should be specified .
 * Treatment Information: course numbers should not be greater than total number of courses.
 * Disease History: 'Diagnosis date' cannot be a future date
 * Adverse event :'First learned date' cannot be a future date; cannot be before the 'Start date'
 * Lab: 'Baseline value date', 'Worst value date' and 'Recovery date' cannot be future dates.
 * Lab: 'Baseline value date', cannot be after 'Worst value date' and 'Worst value date' cannot be after 'Recovery value date'.
 * 
 * @author Ramakrishna Gundala
 */

public class ExpeditedAdverseEventReportValidatorImpl implements ExpeditedAdverseEventReportValidator{

	/**
	 * This Validator validates ExpeditedAdverseEventReport instances
	 */
	public boolean supports(Class clazz) {
		return ExpeditedAdverseEventReport.class.equals(clazz);
	}


	public void validateStudyDevice(ExpeditedAdverseEventReport expeditedAdverseEventReport, Errors errors){
		for(MedicalDevice medicalDevice : expeditedAdverseEventReport.getMedicalDevices()){
			StudyDevice studyDevice = null;
			if((studyDevice = medicalDevice.getStudyDevice()) != null){
				if(studyDevice.getDevice() == null){
			           if(StringUtilities.isBlank(studyDevice.getOtherBrandName()) & StringUtilities.isBlank(studyDevice.getOtherCommonName())){
			        	   errors.reject("SAE_038","Invalid Study Device. Device or one among (other brand name, other common name) is required.");
			           }
			        }
			}
		}
        
	}
	
	public void validateTreatmentInformation(ExpeditedAdverseEventReport expeditedAdverseEventReport, Errors errors){
		if(expeditedAdverseEventReport.getTreatmentInformation() == null) return;
		TreatmentInformation treatmentInformation = expeditedAdverseEventReport.getTreatmentInformation();
			if(treatmentInformation.getAdverseEventCourse() != null){
				if(treatmentInformation.getAdverseEventCourse().getNumber() != null &&  treatmentInformation.getTotalCourses() != null && 
						(treatmentInformation.getAdverseEventCourse().getNumber() > treatmentInformation.getTotalCourses())){
			        	   errors.reject("CIN_BR1_ERR","'The course number on which the event occurred' must not be greater than 'Total number of courses'.");
			        }
			}
	}
	
	
	public void validateDiseaseHistoryDiagnosisDate(ExpeditedAdverseEventReport expeditedAdverseEventReport, Errors errors){ 
		if(expeditedAdverseEventReport.getDiseaseHistory() == null ||
				expeditedAdverseEventReport.getDiseaseHistory().getDiagnosisDate() == null) return;
        if(expeditedAdverseEventReport.getDiseaseHistory().getDiagnosisDate().toDate().after(new Date())){
        	errors.reject("SAE_038","Disease history diagnosis date cannot be a future date.");
       }

	}
	

	public void validateAdverseEventAwarenessDate(ExpeditedAdverseEventReport expeditedAdverseEventReport, Errors errors){ 
		for(AdverseEvent adverseEvent : expeditedAdverseEventReport.getAdverseEvents()){
			if(adverseEvent.getGradedDate() != null ){
				if(adverseEvent.getGradedDate().after(new Date())){
					errors.reject("CAE_024","Adverse event 'First learned date' cannot be a future date");
				}
				if(adverseEvent.getStartDate() != null){
					if(adverseEvent.getGradedDate().before(adverseEvent.getStartDate())){
						errors.reject("CAE_021", "Adverse event 'First learned date' cannot be before the 'Start date' ");
					}
				}
			}
		}

	}
	
	public void validateLabDates(ExpeditedAdverseEventReport expeditedAdverseEventReport, Errors errors){ 
		for(Lab lab : expeditedAdverseEventReport.getLabs()){
			if(lab.getBaseline() != null && lab.getBaseline().getDate() != null ){
				if(lab.getBaseline().getDate().after(new Date())){
					errors.reject("LAB_BR6_ERR","Lab 'Baseline value date' cannot be a future date");
				}
			}
			
			if(lab.getNadir() != null && lab.getNadir().getDate() != null ){
				if(lab.getNadir().getDate().after(new Date())){
					errors.reject("LAB_BR7_ERR","Lab 'Worst value date' cannot be a future date");
				}
			}
			
			if(lab.getRecovery() != null && lab.getRecovery().getDate() != null ){
				if(lab.getRecovery().getDate().after(new Date())){
					errors.reject("LAB_BR8_ERR","Lab 'Recovery value date' cannot be a future date");
				}
			}
			
			if(lab.getRecovery() != null && lab.getRecovery().getDate() != null && lab.getNadir() != null && lab.getNadir().getDate() != null){
				if(lab.getNadir().getDate().after(lab.getRecovery().getDate())){
					errors.reject("LAB_BR4_ERR","The 'Date' of the 'Worst value' cannot be later than the 'Date' of the 'Recovery value'.");
				}
			}
			
			if(lab.getBaseline() != null && lab.getBaseline().getDate() != null && lab.getNadir() != null && lab.getNadir().getDate() != null){
				if(lab.getBaseline().getDate().after(lab.getNadir().getDate())){
					errors.reject("LAB_BR3_ERR","The 'Date' of the 'Baseline value' cannot be later than the 'Date' of the 'Worst value'.");
				}
			}
		}

	}
	
	
	public void validate(Object obj, Errors errors) {
		ExpeditedAdverseEventReport expeditedAdverseEventReport = (ExpeditedAdverseEventReport) obj;
		validateAdverseEventAwarenessDate(expeditedAdverseEventReport, errors);
		validateDiseaseHistoryDiagnosisDate(expeditedAdverseEventReport, errors);
		validateLabDates(expeditedAdverseEventReport, errors);
		validateStudyDevice(expeditedAdverseEventReport, errors);
		validateTreatmentInformation(expeditedAdverseEventReport, errors);
	}

}
