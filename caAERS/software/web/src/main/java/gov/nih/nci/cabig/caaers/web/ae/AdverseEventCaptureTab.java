package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.OutcomeType;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
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
            	InputField verbatimField = InputFieldFactory.createTextField("detailsForOther", "Verbatim",verbatimMandatory);
                InputFieldAttributes.setSize(verbatimField, 25);
                mainFieldFactory.addField(verbatimField);
                
                //grade
                InputField gradeField = InputFieldFactory.createLongSelectField("grade","Grade", !ae.getSolicited() & unRetired, createGradeOptions(ae, isMeddraStudy ? "Meddra" : "Ctc"));
                mainFieldFactory.addField(gradeField);
                
                //startDate
                InputField startDateField = InputFieldFactory.createPastDateField("startDate", "Start date", false & unRetired);
                mainFieldFactory.addField(startDateField);
                
                //endDate
                InputField endDateField = InputFieldFactory.createPastDateField("endDate", "End date", false & unRetired);
                mainFieldFactory.addField(endDateField);
                
                //attribution
                InputField attributionField = InputFieldFactory.createSelectField("attributionSummary", "Attribution to study intervention", false & unRetired, createAttributionOptions());
                mainFieldFactory.addField(attributionField);
                
                //Hospitalization
                InputField hospitalizationField = InputFieldFactory.createSelectField("hospitalization", "Did AE cause hospitalization?", false & unRetired, createHospitalizationOptions());
                mainFieldFactory.addField(hospitalizationField);
                
                //expectedness
                InputField expectednessField = InputFieldFactory.createSelectField("expected", "Expected", false & unRetired, createExpectedOptions());
                mainFieldFactory.addField(expectednessField);
                
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

    @Override
    public void beforeBind(HttpServletRequest request, CaptureAdverseEventInputCommand command) {
        super.beforeBind(request, command);
        command.reassociate();
    }

    @Override
    public Map<String, Object> referenceData(CaptureAdverseEventInputCommand command) {
        //initalize the seriousness outcome indicators
        command.initializeOutcomes();
    	return super.referenceData(command);
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
                if (ae.getGrade() != null) {
    				if (ae.getGrade().getCode() > 2 && ae.getHospitalization() == null)
    					errors.rejectValue("adverseEvents[" + i + "].hospitalization", "CAE_004", "Hospitalization must be entered if grade is greater than 2");
    			}
            }
            
            // Check if end date is greater than the start date
            if(ae.getEndDate() != null && ae.getStartDate() != null && DateUtils.compareDate(ae.getStartDate(), ae.getEndDate()) > 0)
            	errors.rejectValue("adverseEvents[" + i + "].endDate" , "CAE_014", "The \"End date\" can not be before the \"Start date\". It should be either be the same day or later.");
            
            // Check if start date of course is greater than the start date of the ae.
            //if(command.getAdverseEventReportingPeriod().getStartDate() != null && ae.getStartDate() != null &&
            //		DateUtils.compareDate(command.getAdverseEventReportingPeriod().getStartDate(), ae.getStartDate()) > 0)
            //	errors.rejectValue("adverseEvents[" + i + "].startDate", "CAE_015");
            
            i++;
        }

        command.setErrorsForFields(new HashMap<String, Boolean>());
        WebUtils.populateErrorFieldNames(command.getErrorsForFields(), errors);
    }
    
    
}