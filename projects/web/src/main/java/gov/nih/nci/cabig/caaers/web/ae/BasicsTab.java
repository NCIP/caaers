package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Outcome;
import gov.nih.nci.cabig.caaers.domain.OutcomeType;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;

import java.util.Iterator;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.ArrayList;
import java.util.ListIterator;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 */
public abstract class BasicsTab extends AeTab {
    protected static final String MAIN_FIELD_GROUP = "main";

    protected static final Collection<Grade> EXPEDITED_GRADES = new ArrayList<Grade>(5);
    static {
        EXPEDITED_GRADES.addAll(Arrays.asList(Grade.values()));
        EXPEDITED_GRADES.remove(Grade.NORMAL);
    }

    public BasicsTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
        setAutoPopulateHelpKey(true);
        addHelpKeyExclusion("ctc-category", "grade", "startDate", "endDate", "hospitalization",
                        "comments");
    }

    private Map<Object, Object> createAttributionOptions() {
        Map<Object, Object> attributionOptions = new LinkedHashMap<Object, Object>();
        attributionOptions.put("", "Please select");
        attributionOptions.putAll(InputFieldFactory.collectOptions(Arrays.asList(Attribution
                        .values()), "name", null));
        return attributionOptions;
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator,
                    ExpeditedAdverseEventInputCommand command) {
        InputField attributionField = InputFieldFactory.createSelectField("attributionSummary",
                        "Attribution to study", false, createAttributionOptions());
        /*
         * InputFieldAttributes.setDetails(attributionField, "Select from the list the most
         * appropriate term describing the relationship of the event to the study interactions or
         * interventions.");
         */
        InputField exField = InputFieldFactory.createBooleanSelectField("expected", "Expected",
                        true);
        /*
         * InputFieldAttributes.setDetails(exField, "Specify whether the AE is expected or not.
         * &quot;Unexpected&quot; events " + "are those that differ in nature, severity or frequency
         * from what is described in the investigator's " + "brochure or informed consent document.
         * For agents under a CTEP IND, refer also to the AdEERS Agent Specific " + "Adverse Event
         * List (ASAEL). For commercial agents or agents under a non-CTEP IND, refer also to the
         * package insert.");
         */
        InputField commentsField = InputFieldFactory.createTextArea("comments", "Comments", false);
        InputFieldAttributes.setColumns(commentsField, 80);
        InputFieldAttributes.setRows(commentsField, 5);

        creator.createRepeatingFieldGroup(MAIN_FIELD_GROUP, "adverseEvents", InputFieldFactory
                        .createLongSelectField("grade", "Grade", true, InputFieldFactory
                                        .collectOptions(EXPEDITED_GRADES, "name", null)),
                        InputFieldFactory.createDateField("startDate", "Start date"),
                        InputFieldFactory.createDateField("endDate", "End date"), attributionField,
                        InputFieldFactory.createSelectField("hospitalization", "Hospitalization",
                                        true, InputFieldFactory.collectOptions(Arrays
                                                        .asList(Hospitalization.values()), "name",
                                                        "displayName")), exField, commentsField);
    }

    @Override
    public ExpeditedReportSection section() {
        return ExpeditedReportSection.BASICS_SECTION;
    }

    @Override
    protected void validate(ExpeditedAdverseEventInputCommand command, BeanWrapper commandBean,
                    Map<String, InputFieldGroup> fieldGroups, Errors errors) {
        super.validate(command, commandBean, fieldGroups, errors);
        // TODO: validate that there is at least one AE
        for (ListIterator<AdverseEvent> lit = command.getAeReport().getAdverseEvents()
                        .listIterator(); lit.hasNext();) {
            AdverseEvent ae = lit.next();
            validateAdverseEvent(ae, lit.previousIndex(), fieldGroups, errors);
        }

        InputField firstStartDateField = fieldGroups.get(MAIN_FIELD_GROUP + '0').getFields().get(1);
        if (command.getAeReport().getAdverseEvents().get(0).getStartDate() == null) {
            errors.rejectValue(firstStartDateField.getPropertyName(), "REQUIRED",
                            firstStartDateField.getDisplayName() + " required for primary AE");
        }
    }

    protected void validateAdverseEvent(AdverseEvent ae, int index,
                    Map<String, InputFieldGroup> groups, Errors errors) {
    }

    protected void outcomeHelpKeyExclusion() {
        addHelpKeyExclusion("outcomes[1]", "outcomes[2]", "outcomes[3]", "outcomes[4]",
                        "outcomes[5]", "outcomes[6]", "outcomes[7]", "outcomeDate", "otherOutcome");
    }

    protected InputFieldGroup getOutcomeInputFieldGroup(ExpeditedAdverseEventInputCommand command) {

        InputFieldGroup optional = new DefaultInputFieldGroup("outcomes");
        for (String code : command.getOutcomes().keySet()) {
            OutcomeType outcomeType = OutcomeType.getByCode(Integer.parseInt(code));
            optional.getFields().add(
                            InputFieldFactory.createCheckboxField("outcomes[" + code + ']',
                                            outcomeType.getDisplayName()));
            if (outcomeType == OutcomeType.DEATH) {
                optional.getFields().add(
                                InputFieldFactory.createDateField("outcomeDate", "date", false));
            }
            if (outcomeType == OutcomeType.OTHER_SERIOUS) {
                optional.getFields().add(
                                InputFieldFactory.createTextField("otherOutcome", "", false));
            }
        }
        return optional;
    }

    protected void postProcessOutcomes(ExpeditedAdverseEventInputCommand command) {
        // override disabled checkboxes on the UI - better way to do this is using images. I prefer
        // not
        // to use JS manipulation as that would allow hacking if someone wants.
        Hospitalization hospitalization = command.getAeReport().getAdverseEvents().size() > 0 ? command
                        .getAeReport().getAdverseEvents().get(0).getHospitalization()
                        : null;

        boolean isHospitalization = (hospitalization != null && hospitalization.getName().contains(
                        OutcomeType.HOSPITALIZATION.getName())) ? true : false;

        command.getOutcomes().put("3", isHospitalization);

        ArrayList<String> codes = new ArrayList<String>();
        // remove
        for (Iterator<Outcome> outcomes = command.getAeReport().getOutcomes().iterator(); outcomes
                        .hasNext();) {
            Outcome outcome = outcomes.next();
            String code = outcome.getOutcomeType().getCode().toString();
            codes.add(code);
            boolean x = command.getOutcomes().get(code);
            if (x == false) {
                outcomes.remove();
            }
        }
        // add
        for (String code : command.getOutcomes().keySet()) {
            if (!codes.contains(code) && command.getOutcomes().get(code) == true) {
                Outcome outcome = new Outcome();
                outcome.setOutcomeType(OutcomeType.getByCode(Integer.parseInt(code)));
                command.getAeReport().addOutcomes(outcome);
            }
        }

        // Add the date & other if exist
        for (Outcome outcome : command.getAeReport().getOutcomes()) {
            if (outcome.getOutcomeType() == OutcomeType.DEATH) {
                outcome.setDate(command.getOutcomeDate());
            }
            if (outcome.getOutcomeType() == OutcomeType.OTHER_SERIOUS) {
                outcome.setOther(command.getOtherOutcome());
            }
        }

    }
}
