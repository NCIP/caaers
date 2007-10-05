package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;

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
    }

    private Map<Object, Object> createAttributionOptions() {
        Map<Object, Object> attributionOptions = new LinkedHashMap<Object, Object>();
        attributionOptions.put("", "Please select");
        attributionOptions.putAll(
            InputFieldFactory.collectOptions(Arrays.asList(Attribution.values()), "name", null));
        return attributionOptions;
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command) {
        InputField attributionField = InputFieldFactory.createSelectField(
            "attributionSummary", "Attribution to lead IND", false, createAttributionOptions());
        InputFieldAttributes.setDetails(attributionField,
            "Select from the list the most appropriate term describing the relationship of the event to the lead intervention or agent.");
        InputField exField = InputFieldFactory.createBooleanSelectField(
                "expected", "Expected", true);
        InputFieldAttributes.setDetails(exField, "If known, specify whether the AE is expected or not, as determined by the protocol guidelines. If this is a CTEP Sponsored trial, you may refer also to the AdEERS Agent Specific Adverse Event List (ASAEL).");
        InputField commentsField = InputFieldFactory.createTextArea("comments", "Comments", false);
        InputFieldAttributes.setColumns(commentsField, 50);
        creator.createRepeatingFieldGroup(MAIN_FIELD_GROUP, "adverseEvents",
            InputFieldFactory.createLongSelectField("grade", "Grade", true,
                    InputFieldFactory.collectOptions(EXPEDITED_GRADES, "name", null)),
            InputFieldFactory.createDateField("startDate", "Start date"),
            InputFieldFactory.createDateField("endDate", "End date"),
            attributionField,
            InputFieldFactory.createSelectField(
                "hospitalization", "Hospitalization", true,
                    InputFieldFactory.collectOptions(Arrays.asList(Hospitalization.values()), "name", "displayName")),
            exField,
            commentsField
        );
    }

    @Override
    public ExpeditedReportSection section() {
        return ExpeditedReportSection.BASICS_SECTION;
    }

    @Override
    protected void validate(
        ExpeditedAdverseEventInputCommand command, BeanWrapper commandBean,
        Map<String, InputFieldGroup> fieldGroups, Errors errors
    ) {
        // TODO: validate that there is at least one AE
        for (ListIterator<AdverseEvent> lit = command.getAeReport().getAdverseEvents().listIterator(); lit.hasNext();) {
            AdverseEvent ae =  lit.next();
            validateAdverseEvent(ae, lit.previousIndex(), fieldGroups, errors);
        }

        InputField firstStartDateField = fieldGroups.get(MAIN_FIELD_GROUP + '0').getFields().get(1);
        if (command.getAeReport().getAdverseEvents().get(0).getStartDate() == null) {
            errors.rejectValue(firstStartDateField.getPropertyName(), "REQUIRED",
                firstStartDateField.getDisplayName() + " required for primary AE");
        }
    }

    protected void validateAdverseEvent(AdverseEvent ae, int index, Map<String, InputFieldGroup> groups, Errors errors) {
    }
}
