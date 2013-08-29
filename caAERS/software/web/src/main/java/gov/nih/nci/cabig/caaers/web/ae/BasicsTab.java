/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 * @author Ion C. Olaru
 *
 */
public abstract class BasicsTab extends AeTab {
    protected CtcTermDao ctcTermDao;
    protected static final String MAIN_FIELD_GROUP = "main";

    protected static final Collection<Grade> EXPEDITED_GRADES = new ArrayList<Grade>(5);
    protected static final Integer VERBATIM_MAX_SIZE = 65;
    

    static {
        EXPEDITED_GRADES.addAll(Arrays.asList(Grade.values()));
        EXPEDITED_GRADES.remove(Grade.NORMAL);
        EXPEDITED_GRADES.remove(Grade.NOT_EVALUATED);
    }

    public BasicsTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
        setAutoPopulateHelpKey(true);
        /*addHelpKeyExclusion("ctc-category", "grade", "startDate", "endDate", "hospitalization", "comments");*/
    }

    private Map<Object, Object> createAttributionOptions() {
        Map<Object, Object> attributionOptions = new LinkedHashMap<Object, Object>();
        attributionOptions.put("", "Please select");
        attributionOptions.putAll(WebUtils.collectOptions(Arrays.asList(Attribution.values()), "name", null));
        return attributionOptions;
    }

    @Override
    public void onDisplay(HttpServletRequest request, ExpeditedAdverseEventInputCommand command) {
        super.onDisplay(request, command);
        AbstractExpeditedAdverseEventInputCommand expeditedCommand = (AbstractExpeditedAdverseEventInputCommand) command;
        Map<Object , Object> genericGradeOptions =  WebUtils.collectOptions(EXPEDITED_GRADES, "name", null);
        Map<Integer, Map<Object, Object>> aeGradeOptions = new HashMap<Integer, Map<Object, Object>>();
        for(AdverseEvent ae : command.getAeReport().getActiveAdverseEvents()){
            Integer key = ae.getId() != null ? ae.getId() : -1;
            Map<Object, Object> options = genericGradeOptions;
            if(ae.getAdverseEventTerm() instanceof AdverseEventCtcTerm){
                CtcTerm ctcTerm = ((AdverseEventCtcTerm) ae.getAdverseEventTerm()).getCtcTerm();
                if(ctcTermDao != null && ctcTerm != null){
                    List<CtcGrade> ctcGrades = ctcTermDao.getById(ctcTerm.getId()).getContextualGrades();
                    if(ctcGrades != null && !ctcGrades.isEmpty()) options = WebUtils.collectCustomOptions(ctcGrades, "name", "code", "displayName", ":  ") ;
                }
            }
            aeGradeOptions.put(key, options );
        }
        expeditedCommand.setAeGradeOptionsMap(aeGradeOptions);
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command) {

        InputField attributionField = InputFieldFactory.createSelectField("attributionSummary", "Attribution to study intervention", "aeReport.adverseEvents.attributionSummary", false, createAttributionOptions());
        InputField expectedField = InputFieldFactory.createBooleanSelectField("expected", "Expected", "aeReport.adverseEvents.expected", false);
        InputField timeOfEventField = createTimeField("eventApproximateTime", "Event time", "aeReport.adverseEvents.eventApproximateTime.hourString");
        InputField otherVerbatimField = InputFieldFactory.createTextField("detailsForOther","Verbatim", "aeReport.adverseEvents.detailsForOther", false);
        InputFieldAttributes.setSize(otherVerbatimField, 30);
        InputField riskField = InputFieldFactory.createBooleanSelectField("participantAtRisk", "Does this place participant at increased risk?", "aeReport.adverseEvents.participantAtRisk", false);

        creator.createRepeatingFieldGroup(MAIN_FIELD_GROUP, "adverseEvents", otherVerbatimField,
                InputFieldFactory.createLongSelectField("grade", "Grade", "aeReport.adverseEvents.grade", true, WebUtils.collectOptions(EXPEDITED_GRADES, "name", null)),
                InputFieldFactory.createDateTimeField("gradedDate", "Awareness date", "aeReport.adverseEvents.gradedDate", false),
                InputFieldFactory.createPastDateField("startDate", "Start date", "aeReport.adverseEvents.startDate", false),
                InputFieldFactory.createPastDateField("endDate", "End date", "aeReport.adverseEvents.endDate", false),
                attributionField,
                timeOfEventField,
                InputFieldFactory.createTextField("eventLocation", "Where was the patient when the event occurred?", "aeReport.adverseEvents.eventLocation"),
                InputFieldFactory.createSelectField("hospitalization", "Did AE cause hospitalization?", "aeReport.adverseEvents.hospitalization", false, WebUtils.collectOptions(Arrays.asList(Hospitalization.YES, Hospitalization.NO), "name", "displayName", "Please select")),
                expectedField,
                riskField);
    }

    @Override
    public ExpeditedReportSection[] section() {
        return new ExpeditedReportSection[] { ExpeditedReportSection.BASICS_SECTION, ExpeditedReportSection.ADVERSE_EVENT_SECTION};
    }

    @Override
    protected void validate(ExpeditedAdverseEventInputCommand cmd, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {

        AbstractExpeditedAdverseEventInputCommand command = (AbstractExpeditedAdverseEventInputCommand) cmd;
        super.validate(command, commandBean, fieldGroups, errors);
        
        for (ListIterator<AdverseEvent> lit = command.getAeReport().getAdverseEvents().listIterator(); lit.hasNext();) {
            AdverseEvent ae = lit.next();
            validateAdverseEvent(ae, lit.previousIndex(), fieldGroups, errors);
        }
        InputField firstStartDateField = fieldGroups.get(MAIN_FIELD_GROUP + '0').getFields().get(2);
        
        if (command.getAeReport().getAdverseEvents().size() > 0 && command.getAeReport().getAdverseEvents().get(0).getStartDate() == null) {
            errors.rejectValue(firstStartDateField.getPropertyName(), "SAE_025", firstStartDateField.getDisplayName() + " required for primary AE");
        }
        
        //short i = 0;
        //for(AdverseEvent ae: command.getAeReport().getAdverseEvents()){
        	// Check if start date of course is greater than the start date of the ae.
        //    if(command.getAdverseEventReportingPeriod().getStartDate() != null && ae.getStartDate() != null &&
        //    		DateUtils.compareDate(command.getAdverseEventReportingPeriod().getStartDate(), ae.getStartDate()) > 0)
        //    	errors.rejectValue("aeReport.adverseEvents[" + i + "].startDate", "SAE_044");
        	
        //	i++;
        //}

        WebUtils.populateErrorFieldNames(command.getRulesErrors(), errors);
    }
    
    
    
    protected void validateAdverseEvent(AdverseEvent ae, int index, Map<String, InputFieldGroup> groups, Errors errors) {
        if (ae.getDetailsForOther() != null && ae.getDetailsForOther().length() > VERBATIM_MAX_SIZE) {
            InputField verbatimField = groups.get(MAIN_FIELD_GROUP + index).getFields().get(0);
            errors.rejectValue(verbatimField.getPropertyName(), "SAE_021", new Object[] {VERBATIM_MAX_SIZE}, "The size of the verbatim value should not exceed " +  VERBATIM_MAX_SIZE + " characters.");
        }

        //validate Awareness date, start date and end date
        Date awarenessDate = ae.getGradedDate();
        Date startDate = ae.getStartDate();
        Date endDate = ae.getEndDate();

        if(awarenessDate != null && startDate != null){
            InputField awarenessDateField = groups.get(MAIN_FIELD_GROUP + index).getFields().get(2);
            if(DateUtils.compareDate(startDate, awarenessDate) > 0) {
                errors.rejectValue(awarenessDateField.getPropertyName(), "CAE_021", new Object[]{}, "The Awareness date must not be earlier than the start date.");
            }
        }

        if(awarenessDate != null && endDate != null){
            InputField awarenessDateField = groups.get(MAIN_FIELD_GROUP + index).getFields().get(2);
            if(DateUtils.compareDate(awarenessDate, endDate) > 0) {
                errors.rejectValue(awarenessDateField.getPropertyName(), "CAE_022",  "The Awareness date must not be later than End date.");
            }
        }

        if(startDate != null && endDate != null){
            InputField endDateField = groups.get(MAIN_FIELD_GROUP + index).getFields().get(4);
            if(DateUtils.compareDate(startDate, endDate) > 0){
                errors.rejectValue(endDateField.getPropertyName(), "CAE_014" , "Start Date must not be later than End date.");
            }
        }
    }

    protected void outcomeHelpKeyExclusion() {
        /*addHelpKeyExclusion("outcomes", "outcomeOtherDetails");*/
    }

    protected List<InputFieldGroup> getOutcomeInputFieldGroups(ExpeditedAdverseEventInputCommand command) {
        String groupBaseName = "outcomes";
        List<InputFieldGroup> outcomeGroups = new ArrayList<InputFieldGroup>();
        int size = command.getAeReport().getAdverseEvents().size();
        for (int i = 0; i < size; i++) {

            InputFieldGroup outcomeFieldGrp = new DefaultInputFieldGroup(groupBaseName + i);
            List<InputField> outcomeFields = outcomeFieldGrp.getFields();
            Map<Integer, Boolean> oneOutcomeMap = command.getOutcomes().get(i);

            for (Integer code : oneOutcomeMap.keySet()) {
                OutcomeType outcomeType = OutcomeType.getByCode(code);

                outcomeFields.add(InputFieldFactory.createCheckboxField("outcomes[" + i + "][" + code + "]", outcomeType.getDisplayName()));

                if (outcomeType == OutcomeType.OTHER_SERIOUS) {
                    outcomeFields.add(InputFieldFactory.createTextField("outcomeOtherDetails[" + i + "]", ""));
                }
            }
            outcomeGroups.add(outcomeFieldGrp);
        }

        return outcomeGroups;
    }


    protected void removeOutcomeIfExists(List<Outcome> outcomes, OutcomeType type) {
        if (outcomes == null || outcomes.isEmpty()) return;
        Outcome o = null;
        for (Outcome outcome : outcomes) {
            if (outcome.getOutcomeType() == type) {
                o = outcome;
                break;
            }
        }
        if (o != null) outcomes.remove(o);
    }

    protected void addOutcomeIfDontExist(List<Outcome> outcomes, OutcomeType type, String otherString) {

        if (!outcomes.isEmpty()) {
            for (Outcome outcome : outcomes) {
                if (outcome.getOutcomeType() == type) {
                    if (type == OutcomeType.OTHER_SERIOUS) outcome.setOther(otherString);
                    return;
                }
            }
        }

        Outcome o = new Outcome();
        o.setOutcomeType(type);
        o.setOther(otherString);
        outcomes.add(o);
    }

    protected void postProcessOutcomes(ExpeditedAdverseEventInputCommand command) {
        int i = 0;

        for (AdverseEvent ae : command.getAeReport().getAdverseEvents()) {
            Map<Integer, Boolean> outcomeMap = command.getOutcomes().get(i);
            for (Map.Entry<Integer, Boolean> entry : outcomeMap.entrySet()) {
                if (entry.getValue()) {
                    addOutcomeIfDontExist(ae.getOutcomes(), OutcomeType.getByCode(entry.getKey()), command.getOutcomeOtherDetails().get(i));
                } else {
                    removeOutcomeIfExists(ae.getOutcomes(), OutcomeType.getByCode(entry.getKey()));
                }
            }

            //special case, DEATH garde should select DEATH
            if (ae.getGrade() != null && ae.getGrade().equals(Grade.DEATH))
                addOutcomeIfDontExist(ae.getOutcomes(), OutcomeType.DEATH, null);

            //special case HOSPITALIZATION
            if (ae.getHospitalization() != null && ae.getHospitalization().equals(Hospitalization.YES))
                addOutcomeIfDontExist(ae.getOutcomes(), OutcomeType.HOSPITALIZATION, null);

            i++;
        }

    }
    
    @Override
    public boolean hasEmptyMandatoryFields(ExpeditedAdverseEventInputCommand command, HttpServletRequest request) {
    	boolean primaryAEStartDateNotFilled = false;
    	List<AdverseEvent> adverseEvents = command.getAeReport().getAdverseEvents();
    	if(adverseEvents != null && adverseEvents.size() > 0){
    		primaryAEStartDateNotFilled = adverseEvents.get(0).getStartDate() == null;
    	}
        if(primaryAEStartDateNotFilled) return true;
    	return super.hasEmptyMandatoryFields(command, request);
    }

    public CtcTermDao getCtcTermDao() {
        return ctcTermDao;
    }

    public void setCtcTermDao(CtcTermDao ctcTermDao) {
        this.ctcTermDao = ctcTermDao;
    }
}
