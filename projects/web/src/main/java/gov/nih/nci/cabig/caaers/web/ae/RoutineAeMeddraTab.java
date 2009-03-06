package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * @author Krikor Krumlian
 */
public class RoutineAeMeddraTab extends AeRoutTab {
    private static final String REPORT_FIELD_GROUP = "report";

    private static final String MAIN_FIELD_GROUP = "main";

    private static final String MEDDRA_TERM_FIELD_GROUP = "ctcTerm";

    private InputFieldGroup reportFieldGroup;

    private RepeatingFieldGroupFactory mainFieldFactory, meddraTermFieldFactory;

    private static final Collection<Grade> GRADES = new ArrayList<Grade>(5);

    static {
        GRADES.addAll(Arrays.asList(Grade.values()));
    }

    public RoutineAeMeddraTab() {
        super("Enter basic AE information", "Adverse Events", "ae/meddraTerms");

        reportFieldGroup = new DefaultInputFieldGroup(REPORT_FIELD_GROUP);
        reportFieldGroup.getFields()
                .add(
                        InputFieldFactory.createPastDateField(
                                "aeRoutineReport.startDate", "From", true));
        reportFieldGroup.getFields().add(
                InputFieldFactory.createPastDateField("aeRoutineReport.endDate", "To", true));

        mainFieldFactory = new RepeatingFieldGroupFactory(MAIN_FIELD_GROUP,
                "aeRoutineReport.adverseEvents");
        mainFieldFactory.addField(InputFieldFactory.createSelectField("grade", "Grade", true,
                createGradeOptions()));
        InputField attributionField = InputFieldFactory.createSelectField("attributionSummary",
                "Attribution to study", true, createAttributionOptions());
        InputFieldAttributes
                .setDetails(
                        attributionField,
                        "Indicate the likelihood that this adverse event is attributable to any element of the study protocol.");
        mainFieldFactory.addField(attributionField);
        mainFieldFactory.addField(InputFieldFactory.createSelectField("hospitalization",
                "Hospitalization or prolongation of existing hospitalization?", false, createHospitalizationOptions()));
        // mainFieldFactory.addField(InputFieldFactory.createBooleanSelectField(
        // "expected", "Expected", true));
        mainFieldFactory.addField(InputFieldFactory.createSelectField("expected", "Expected", false,
                createExpectedOptions()));

        meddraTermFieldFactory = new RepeatingFieldGroupFactory(MEDDRA_TERM_FIELD_GROUP,
                "aeRoutineReport.adverseEvents");
        InputField lowLevelTermField = InputFieldFactory.createAutocompleterField(
                "adverseEventMeddraLowLevelTerm.lowLevelTerm", "MedDRA code", true);
        lowLevelTermField.getAttributes().put("size", 40);
        meddraTermFieldFactory.addField(lowLevelTermField);

    }

    private Map<Object, Object> createExpectedOptions() {
        Map<Object, Object> expectedOptions = new LinkedHashMap<Object, Object>();
        expectedOptions.put("", "Please select");
        expectedOptions.put(Boolean.TRUE, "Yes");
        expectedOptions.put(Boolean.FALSE, "No");
        return expectedOptions;
    }

    private Map<Object, Object> createGradeOptions() {
        Map<Object, Object> gradeOptions = new LinkedHashMap<Object, Object>();
        gradeOptions.put("", "Please select");
        gradeOptions.putAll(WebUtils.collectOptions(GRADES, "name", "code"));
        return gradeOptions;
    }

    private Map<Object, Object> createHospitalizationOptions() {
        Map<Object, Object> hospitalizationOptions = new LinkedHashMap<Object, Object>();
        hospitalizationOptions.putAll(WebUtils.collectOptions(Arrays
                .asList(Hospitalization.values()), "name", "displayName"));
        return hospitalizationOptions;
    }

    private Map<Object, Object> createAttributionOptions() {
        Map<Object, Object> attributionOptions = new LinkedHashMap<Object, Object>();
        attributionOptions.put("", "Please select");
        attributionOptions.putAll(WebUtils.collectOptions(Arrays.asList(Attribution
                .values()), "name", "displayName"));
        return attributionOptions;
    }

    private Map<Object, Object> createOptions(RoutineAdverseEventInputCommand command) {
        Map<Object, Object> assignmentsOptions = new LinkedHashMap<Object, Object>();
        assignmentsOptions.put("", "Please select");
        List<TreatmentAssignment> taList = command.getAeRoutineReport().getStudy()
                .getTreatmentAssignments();
        if (taList != null) {
            for (TreatmentAssignment ta : taList) {
                assignmentsOptions.put(ta.getId(), ta.getCode());
            }
        }

        return assignmentsOptions;
    }

    @Override
    @SuppressWarnings("unchecked")
    public InputFieldGroupMap createFieldGroups(RoutineAdverseEventInputCommand command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        reportFieldGroup.getFields()
                .add(
                        InputFieldFactory.createSelectField(
                                "aeRoutineReport.treatmentAssignment",
                                "Treatment assignment code", false,
                                createOptions(command)));

        map.addInputFieldGroup(reportFieldGroup);
        int aeCount = command.getAeRoutineReport().getAdverseEvents().size();
        map.addRepeatingFieldGroupFactory(mainFieldFactory, aeCount);
        map.addRepeatingFieldGroupFactory(meddraTermFieldFactory, aeCount);
        return map;
    }

    @Override
    protected void validate(RoutineAdverseEventInputCommand command, BeanWrapper commandBean,
                            Map<String, InputFieldGroup> fieldGroups, Errors errors) {
        Date startDate = command.getAeRoutineReport().getStartDate();
        Date endDate = command.getAeRoutineReport().getEndDate();

        if (startDate != null && endDate != null && (endDate.getTime() - startDate.getTime() < 0)) {
            InputField field = fieldGroups.get(REPORT_FIELD_GROUP).getFields().get(1);
            errors.rejectValue(field.getPropertyName(), "REQUIRED",
                    "To cannot be earlier than From");
        }

        if (command.getAeRoutineReport().getAdverseEvents() != null) {
            for (ListIterator<AdverseEvent> lit = command.getAeRoutineReport().getAdverseEvents()
                    .listIterator(); lit.hasNext();) {
                AdverseEvent ae = lit.next();
                validateAdverseEvent(ae, lit.previousIndex(), fieldGroups, errors);
            }
        }
    }

    private void validateAdverseEvent(AdverseEvent ae, int index,
                                      Map<String, InputFieldGroup> groups, Errors errors) {
        LowLevelTerm lowLevelTerm = ae.getAdverseEventMeddraLowLevelTerm().getLowLevelTerm();
        if (lowLevelTerm == null) {
            InputField field = groups.get(MEDDRA_TERM_FIELD_GROUP + index).getFields().get(0);
            errors.rejectValue(field.getPropertyName(), "REQUIRED", "Missing "
                    + field.getDisplayName());
        }
    }

    // //// CONFIGURATION
}
