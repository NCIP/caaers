package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.OutcomeType;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.caaers.web.CaaersFieldConfigurationManager;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.MultipleFieldGroupFactory;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * @author Biju Joseph
 */
public class AdverseEventCaptureTab extends AdverseEventTab {
	
	// The names of the field groups
    private static final String MAIN_FIELD_GROUP = "main";
    private static final String OUTCOME_FIELD_GROUP ="outcomes";
    private static final String TAB_NAME = AdverseEventCaptureTab.class.getName();
    
    //max characters allowed for Verbatim
    private static final Integer VERBATIM_MAX_SIZE = 65;
    
    public AdverseEventCaptureTab() {
        super("Enter Adverse Events", "Adverse Events", "ae/captureAdverseEvents");
        setAutoPopulateHelpKey(true);
    }


    /**
     * This method will create the fields to be displayed on the screen.
     *  For CTC/MedDRA Study the following fields will be created:
     *    0. Other(MedDRA) - [conditional, only appear for CTC studies, if term is otherspecify].
     *    1. Verbatim
     *    2. Grade
     *    3. StartDate
     *    4. End Date
     *    5. Attribution To Study
     *    6. Time Of Event
     *    7. Event Location
     *    8. Hospitalization
     *    9. Expectedness
     *   10. Outcome
     *   
     * Note:- We should run the adverse events against the index fixed list, since that list will have null items in it, we should skip if 'AdverseEvent' is null.
     */

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(CaptureAdverseEventInputCommand cmd) {

        InputFieldGroupMap fieldGrpMap = new InputFieldGroupMap();
        MultipleFieldGroupFactory mainFieldFactory;
        
        if(cmd.getAdverseEventReportingPeriod() != null){
        	
        	Study study = cmd.getAdverseEventReportingPeriod().getStudy();
        	boolean isMeddraStudy = study.getAeTerminology().getTerm() == Term.MEDDRA;
        	int size = cmd.getAdverseEvents().size();
        	
        	//create one field group - One row of main groups
            mainFieldFactory = new MultipleFieldGroupFactory(MAIN_FIELD_GROUP, "adverseEvents");
            
        	for (int i = 0; i < size; i++) {
        		
                AdverseEvent ae = cmd.getAdverseEvents().get(i);
                if (ae == null) continue;
                
                boolean unRetired = !ae.isRetired();
                
                //other MedDRA
        		InputField otherMeddraField = (ae.getSolicited()) ? InputFieldFactory.createLabelField("lowLevelTerm.meddraTerm", "Other (MedDRA)", false & unRetired) :
        															InputFieldFactory.createAutocompleterField("lowLevelTerm", "Other(MedDRA)", true & unRetired);
        		//only add otherMedDRA on non MedDRA and otherRequired=true
                if(ae.getAdverseEventTerm().isOtherRequired() && study.getOtherMeddra() != null){
                	mainFieldFactory.addField(otherMeddraField);
                }
                
            	//verbatim - Is required when there is no other MedDRA
                boolean verbatimMandatory = (study.getOtherMeddra() == null) && (ae.getAdverseEventTerm().isOtherRequired());
                boolean verbatimApplicable = caaersFieldConfigurationManager.isFieldApplicable(TAB_NAME, "aeReport.adverseEvents.detailsForOther");
                boolean verbatimRequired =  verbatimApplicable && (verbatimMandatory || isFieldRequired(ae, "adverseEvents[].detailsForOther"));
            	InputField verbatimField = InputFieldFactory.createTextField("detailsForOther", "Verbatim", "aeReport.adverseEvents.detailsForOther", verbatimRequired);
                InputFieldAttributes.setSize(verbatimField, 25);
                mainFieldFactory.addField(verbatimField);
                
                //grade
                InputField gradeField = InputFieldFactory.createLongSelectField("grade","Grade", "aeReport.adverseEvents.grade", isFieldRequired(ae, "adverseEvents[].grade"), createGradeOptions(ae, isMeddraStudy ? "Meddra" : "Ctc"));
                mainFieldFactory.addField(gradeField);
                
                //startDate
                InputField startDateField = InputFieldFactory.createPastDateField("startDate", "Start date", "aeReport.adverseEvents.startDate", isFieldRequired(ae, "adverseEvents[].startDate"));
                mainFieldFactory.addField(startDateField);
                
                //endDate
                InputField endDateField = InputFieldFactory.createPastDateField("endDate", "End date", "aeReport.adverseEvents.endDate", isFieldRequired(ae, "adverseEvents[].endDate"));
                mainFieldFactory.addField(endDateField);
                
                //attribution
                InputField attributionField = InputFieldFactory.createSelectField("attributionSummary", "Attribution to study intervention", "aeReport.adverseEvents.attributionSummary", isFieldRequired(ae, "adverseEvents[].attributionSummary"), createAttributionOptions());
                mainFieldFactory.addField(attributionField);
                
                //Hospitalization
                InputField hospitalizationField = InputFieldFactory.createSelectField("hospitalization", "Did AE cause hospitalization?", "aeReport.adverseEvents.hospitalization", isFieldRequired(ae, "adverseEvents[].hospitalization"), createHospitalizationOptions());
                mainFieldFactory.addField(hospitalizationField);
                
                //expectedness
                InputField expectednessField = InputFieldFactory.createSelectField("expected", "Expected", "aeReport.adverseEvents.expected", isFieldRequired(ae, "adverseEvents[].expected"), createExpectedOptions());
                mainFieldFactory.addField(expectednessField);
                
                //Time of event
                InputField timeOfEventField = createTimeField("eventApproximateTime", "Event time", "aeReport.adverseEvents.eventApproximateTime.hourString");
                mainFieldFactory.addField(timeOfEventField);
                
                //Participant at risk
                InputField riskField = InputFieldFactory.createBooleanSelectField("participantAtRisk", "Does this place participant at increased risk?", "aeReport.adverseEvents.participantAtRisk", isFieldRequired(ae, "adverseEvents[].participantAtRisk"));
                mainFieldFactory.addField(riskField);
                
                //Event location
                InputField eventLocationField = InputFieldFactory.createTextField("eventLocation", "Where was the patient when the event occurred?", "aeReport.adverseEvents.eventLocation", isFieldRequired(ae, "adverseEvents[].eventLocation"));
                mainFieldFactory.addField(eventLocationField);

                InputFieldGroup fieldGroup = mainFieldFactory.createGroup(i);
                mainFieldFactory.addFieldGroup(fieldGroup);
                mainFieldFactory.clearFields();
                
                
                
                //now add the fields related to outcomes
                InputFieldGroup outcomeFieldGrp = new DefaultInputFieldGroup(OUTCOME_FIELD_GROUP + i);
                List<InputField> outcomeFields = outcomeFieldGrp.getFields();
                Map<Integer, Boolean> oneOutcomeMap = cmd.getOutcomes().get(i);

                for (Integer code : oneOutcomeMap.keySet()) {
                    OutcomeType outcomeType = OutcomeType.getByCode(code);

                    outcomeFields.add(InputFieldFactory.createCheckboxField("outcomes[" + i + "][" + code + "]", outcomeType.getDisplayName()));

                    if (outcomeType == OutcomeType.OTHER_SERIOUS) {
                        outcomeFields.add(InputFieldFactory.createTextField("outcomeOtherDetails[" + i + "]", ""));
                    }
                }
                fieldGrpMap.addInputFieldGroup(outcomeFieldGrp);
        	}
        	
        	 fieldGrpMap.addMultipleFieldGroupFactory(mainFieldFactory);
        }
        
        return fieldGrpMap;
    }
    
    private boolean isFieldRequired(AdverseEvent ae, String fieldPath){
    	if(ae.getSolicited() || ae.isRetired())
    		return false;
    	else{
    		return caaersFieldConfigurationManager.isFieldMandatory(TAB_NAME, fieldPath);
    	}
    }

    @Override
    public void beforeBind(HttpServletRequest request, CaptureAdverseEventInputCommand command) {
        super.beforeBind(request, command);
        command.reassociate();
    }

    @Override
    public Map<String, Object> referenceData(CaptureAdverseEventInputCommand command) {
        //initalize the seriousness outcome indicators
        command.initializeOutcomes();
        
        Map<String, Object> refData = super.referenceData(command);
        Boolean outcomesMandatory = false;
        // Put a flag in the referenceData to mark Outcome as mandatory if configured so.
        if(caaersFieldConfigurationManager.isFieldMandatory(TAB_NAME, "adverseEvents[].outcomes")){
        	outcomesMandatory = true;
        }
        
        refData.put("outcomesMandatory", outcomesMandatory);
        
        //return super.referenceData(command);
        return refData;
    }



    @Override
    public void postProcess(HttpServletRequest request, CaptureAdverseEventInputCommand command, Errors errors) {
        if (findInRequest(request, CaptureAdverseEventController.AJAX_SUBVIEW_PARAMETER) != null || errors.hasErrors())
            return; //ignore if this is an ajax request
        
        //reset the reporting method and action
        command.set_action(null);
        command.setReportingMethod(null);
        command.setPrimaryAdverseEventId(null);
        
        //sync the seriousness outcomes
        command.synchronizeOutcome();
        
    }



    public AdverseEvent checkAEsUniqueness(CaptureAdverseEventInputCommand command) {
    	if(CollectionUtils.isEmpty(command.getAdverseEventReportingPeriod().getAdverseEvents())){
    		return null;
    	}
    	
    	Study study = command.getAdverseEventReportingPeriod().getStudy();
    	boolean hasOtherMeddra = study.getOtherMeddra() != null;
    	
    	
        List<String> aeStringList = new ArrayList<String>();
        
        for (AdverseEvent ae : command.getAdverseEventReportingPeriod().getAdverseEvents()) {
            if(ae.isRetired()) continue;
            
            StringBuffer key = new StringBuffer(String.valueOf(ae.getAdverseEventTerm().getTerm().getId()));
            
            if (ae.getAdverseEventTerm().isOtherRequired()) {
            	if(hasOtherMeddra){
            		if (ae.getLowLevelTerm() == null) continue;
                    key.append(String.valueOf(ae.getLowLevelTerm().getId()));
            	}else{
            		key.append(String.valueOf(ae.getDetailsForOther()));
            	}
            }
            
            if (aeStringList.contains(key.toString())) return ae;
            aeStringList.add(key.toString());
        }

        return null;
    }
    
    @Override
    protected void validate(CaptureAdverseEventInputCommand command, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {

        // START -> AE VALIDATION //
        AdverseEvent adverseEvent = checkAEsUniqueness(command);
        if (adverseEvent != null) {
            String name = adverseEvent.getDisplayName();
            errors.reject("DUPLICATE_EXPECTED_AE", new Object[]{name}, "ERR.");
        }
        // STOP -> AE VALIDATION //

        boolean foundGrade5 = false;
        
        short i = 0;
        for (AdverseEvent ae : command.getAdverseEventReportingPeriod().getAdverseEvents()) {
        	if(ae.isRetired()){
        		i++;
        		continue;
        	}
        	
        	
        	if(ae.getGrade() != null && ae.getGrade().equals(Grade.DEATH)){
        		if(foundGrade5){
        			errors.rejectValue("adverseEvents[" + i + "].grade", "SAE_033", "Only one adverse event with grade DEATH is allowed in a course.");
        		}
        		foundGrade5 = true;
        	}
        	
        	 // CHECKING VERBATIM LENGTH
            if (ae.getDetailsForOther() != null && ae.getDetailsForOther().length() > VERBATIM_MAX_SIZE) {
                errors.rejectValue("adverseEvents[" + i + "].detailsForOther", "SAE_021", new Object[] {VERBATIM_MAX_SIZE}, "The size of the verbatim value should not exceed " +  VERBATIM_MAX_SIZE + " characters.");
            }
            
            
            // If grade is greater than 2 then hospitalization cannot be null.
            if(!command.getAdverseEventReportingPeriod().isBaselineReportingType()) {
            	if(caaersFieldConfigurationManager.isFieldApplicable(TAB_NAME, "adverseEvents[].hospitalization"))
            		if (ae.getGrade() != null) {
            			if (ae.getGrade().getCode() > 2 && ae.getHospitalization() == null)
            				errors.rejectValue("adverseEvents[" + i + "].hospitalization", "CAE_004", "Hospitalization must be entered if grade is greater than 2");
            		}
            }
            
            // Check if end date is greater than the start date
            if(caaersFieldConfigurationManager.isFieldApplicable(TAB_NAME, "adverseEvents[].startDate") && caaersFieldConfigurationManager.isFieldApplicable(TAB_NAME, "adverseEvents[].endDate"))
            	if(ae.getEndDate() != null && ae.getStartDate() != null && DateUtils.compareDate(ae.getStartDate(), ae.getEndDate()) > 0)
            		errors.rejectValue("adverseEvents[" + i + "].endDate" , "CAE_014", "The \"End date\" can not be before the \"Start date\". It should be either be the same day or later.");
            
            // Check if start date of course is greater than the start date of the ae.
            //if(command.getAdverseEventReportingPeriod().getStartDate() != null && ae.getStartDate() != null &&
            //		DateUtils.compareDate(command.getAdverseEventReportingPeriod().getStartDate(), ae.getStartDate()) > 0)
            //	errors.rejectValue("adverseEvents[" + i + "].startDate", "CAE_015");
            
            // Special validation for outcomes as it cannot be validated through the fieldgroup framework.
            
            if(caaersFieldConfigurationManager.isFieldMandatory(TAB_NAME, "adverseEvents[].outcomes") && !ae.getSolicited()){
            	if(ae.getOutcomes() == null || ae.getOutcomes().isEmpty())
            		errors.rejectValue("adverseEvents[" + i + "].outcomes", "CAE_016", "Missing outcomes");
            }
            
            if(caaersFieldConfigurationManager.isFieldMandatory(TAB_NAME, "adverseEvents[].eventApproximateTime.hourString") && !ae.getSolicited()){
            	if(ae.getEventApproximateTime() == null || ae.getEventApproximateTime().getHourString() == null)
            		errors.rejectValue("adverseEvents[" + i + "].eventApproximateTime.hourString", "CAE_017", "Missing event time");
            }
            i++;
        }

        command.setErrorsForFields(new HashMap<String, Boolean>());
        WebUtils.populateErrorFieldNames(command.getErrorsForFields(), errors);
    }
    
    
}