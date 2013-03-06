/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.validation.validator;

import java.util.Date;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;

import gov.nih.nci.cabig.caaers.validation.CaaersFieldConfigurationManager;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import gov.nih.nci.cabig.caaers.domain.*;

/**
 * This class validates an AdverseEventReportingPeriod object.
 * 
 * Following validations are done by this validator - 
 * All AE's (solicited and observed) MUST have a grade.
 * All AE's with grade >= 2 must have the hospitalization question answered.
 * All AE's with grade >= 1 must have attribution answered.
 * All AE's with "Other, Specify" terms must have Verbatim entered or Other MedDRA term selected.
 * The course/evaluation period must have an end date.
 * The end date for the course/reporting period must be <= the current date. 
 * 
 * @author Sameer Sawant
 * @author Biju Joseph
 */

public class AdverseEventReportingPeriodValidator implements Validator{

    private CaaersFieldConfigurationManager caaersFieldConfigurationManager;

	/**
	 * This Validator validates just AdverseEventReportingPeriod instances
	 */
	public boolean supports(Class clazz) {
		return AdverseEventReportingPeriod.class.equals(clazz);
	}


    public boolean isMandatory(String groupName, String fieldName){
        return caaersFieldConfigurationManager.isFieldMandatory(groupName, fieldName );
    }
	
	/**
	 * This method validates if all the AEs of the Reporting Period are graded. 
	 * @param adverseEventReportingPeriod
	 * @return boolean
	 */
	public boolean validGradeValues(AdverseEventReportingPeriod adverseEventReportingPeriod){

        if(isMandatory(CaaersFieldConfigurationManager.AE_FIELD_GROUP, "adverseEvents[].grade")){
           for(AdverseEvent ae: adverseEventReportingPeriod.getAdverseEvents()){
			if(ae.isRetired()) continue;
            if(ae.getGrade() == null) return false;
            if(ae.getGrade() == Grade.NOT_EVALUATED && (!ae.getSolicited()) ) return false;
		   } 
        }

		return true;
	}
	
	/**
	 * This method validates if all AE's with grade >= 2 have the hospitalization question answered.
	 * @param adverseEventReportingPeriod
	 * @return boolean
	 */
	public boolean validHospitalization(AdverseEventReportingPeriod adverseEventReportingPeriod){
        if(isMandatory(CaaersFieldConfigurationManager.AE_FIELD_GROUP, "adverseEvents[].hospitalization")){
          for(AdverseEvent ae: adverseEventReportingPeriod.getAdverseEvents()){
			if(ae.isRetired()) continue;
			if(ae.getGrade() != null && ae.getGrade().getCode() >= 2 && ae.getHospitalization() == null){
				return false;
			}
		  }  
        }

		return true;
	}
	
	/**
	 * This method validates if all AE's with grade >= 1 have attribution answered.
	 * @param adverseEventReportingPeriod
	 * @return boolean
	 */
	public boolean validAttribution(AdverseEventReportingPeriod adverseEventReportingPeriod){
        if(isMandatory(CaaersFieldConfigurationManager.AE_FIELD_GROUP, "adverseEvents[].attributionSummary")){
            for(AdverseEvent ae: adverseEventReportingPeriod.getAdverseEvents()){
                if(ae.isRetired()) continue;
                if(ae.getGrade() != null && ae.getGrade().getCode() >= 1 && ae.getAttributionSummary() == null){
                    return false;
                }
            }   
        }

		return true;
	}
	
	/**
	 * This method validates whether the reporting period has an end date associated.
	 * @param adverseEventReportingPeriod
	 * @return boolean
	 */
	public boolean validateEndDateNotNull(AdverseEventReportingPeriod adverseEventReportingPeriod){
        if(isMandatory(CaaersFieldConfigurationManager.COURSE_FIELD_GROUP, "reportingPeriod.endDate")){
		    if(adverseEventReportingPeriod.getEndDate() == null)return false;
        }
		return true;
	}
	
	/**
	 * This method validates whether the end date associated is a future date. If its future date, then the validation fails.
	 * @param adverseEventReportingPeriod
	 * @return boolean
	 */
	public boolean validateFutureEndDate(AdverseEventReportingPeriod adverseEventReportingPeriod){
         if(isMandatory(CaaersFieldConfigurationManager.COURSE_FIELD_GROUP, "reportingPeriod.endDate")){
            Date currentDate = new Date();
		    if(adverseEventReportingPeriod.getEndDate() != null && adverseEventReportingPeriod.getEndDate().compareTo(currentDate) >= 0)
			    return false;
         }

		return true;
	}
	
	/**
	 * This method validates if whether all AE's with "Other, Specify" terms have Verbatim entered or Other MedDRA term selected.
	 * @param adverseEventReportingPeriod
	 * @return boolean
	 */
	public boolean validateOtherSpecifyTerms(AdverseEventReportingPeriod adverseEventReportingPeriod){
        if(isMandatory(CaaersFieldConfigurationManager.AE_FIELD_GROUP, "adverseEvents[].detailsForOther")){
            for(AdverseEvent ae: adverseEventReportingPeriod.getAdverseEvents()){
                if(ae.isRetired()) continue;
                if(ae.getAdverseEventTerm().isOtherRequired()){
                    if(ae.getDetailsForOther() == null && ae.getLowLevelTerm() == null){
                        return false;
                    }
                }
            }
        }

		
		return true;
	}


    public boolean validateTAC(AdverseEventReportingPeriod adverseEventReportingPeriod){
        if(isMandatory(CaaersFieldConfigurationManager.AE_FIELD_GROUP, "adverseEvents[].detailsForOther")){
           TreatmentAssignment tac = adverseEventReportingPeriod.getTreatmentAssignment();
           return tac != null  && !tac.isRetired(); 
        }
        return true;
    }
	
	
	public void validate(Object obj, Errors e) {
		AdverseEventReportingPeriod adverseEventReportingPeriod = (AdverseEventReportingPeriod) obj;
		
		// Validate - All AE's should be graded.
		if(!validGradeValues(adverseEventReportingPeriod))	
			e.reject("CAE_007","All adverse events should be graded.");
		
		// Validate - All AE's with grade >= 2 must have the hospitalization question answered.
		if(!validHospitalization(adverseEventReportingPeriod))
			e.reject("CAE_008","Hospitalization must be entered if grade is greater than 2.");
		
		// Validate - All AE's with grade >= 1 must have attribution answered.
		if(!validAttribution(adverseEventReportingPeriod))
			e.reject("CAE_009","Attribution must be entered if grade is greater than 1.");
		
		// Validate - The course/evaluation period must have an end date.
		if(!validateEndDateNotNull(adverseEventReportingPeriod))
			e.reject("CAE_010","End date should be entered for the course.");
		
		// Validate - The end date for the course/reporting period must be <= the current date.
		if(!validateFutureEndDate(adverseEventReportingPeriod))
			e.reject("CAE_011","End date of the course cannot have a future date value.");
		
		// Validate - All AE's with "Other, Specify" terms must have Verbatim entered or Other MedDRA term selected.
		if(!validateOtherSpecifyTerms(adverseEventReportingPeriod))
			e.reject("CAE_012","Either verbatim or Other Meddra should be added if the AE term is of kind - otherSpecify.");

		//validate if TAC, present is active
		if(!validateTAC(adverseEventReportingPeriod)){
			e.reject("CAE_013","Treatment assignment associated to this course, is incorrect or removed from protocol.");
		}
	}

    public CaaersFieldConfigurationManager getCaaersFieldConfigurationManager() {
        return caaersFieldConfigurationManager;
    }

    public void setCaaersFieldConfigurationManager(CaaersFieldConfigurationManager caaersFieldConfigurationManager) {
        this.caaersFieldConfigurationManager = caaersFieldConfigurationManager;
    }
}
