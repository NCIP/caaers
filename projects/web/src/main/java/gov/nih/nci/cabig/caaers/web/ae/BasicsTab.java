package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.web.fields.*;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

import java.util.*;

/**
 * @author Rhett Sutphin
 */
public abstract class BasicsTab extends AeTab {
    protected static final String MAIN_FIELD_GROUP = "main";

    protected static final Collection<Grade> EXPEDITED_GRADES = new ArrayList<Grade>(5);

    static {
        EXPEDITED_GRADES.addAll(Arrays.asList(Grade.values()));
        EXPEDITED_GRADES.remove(Grade.NORMAL);
        EXPEDITED_GRADES.remove(Grade.NOT_EVALUATED);
    }

    public BasicsTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
        setAutoPopulateHelpKey(true);
        addHelpKeyExclusion("ctc-category", "grade", "startDate", "endDate", "hospitalization", "comments");
    }

    private Map<Object, Object> createAttributionOptions() {
        Map<Object, Object> attributionOptions = new LinkedHashMap<Object, Object>();
        attributionOptions.put("", "Please select");
        attributionOptions.putAll(WebUtils.collectOptions(Arrays.asList(Attribution.values()), "name", null));
        return attributionOptions;
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command) {

        InputField attributionField = InputFieldFactory.createSelectField("attributionSummary", "Attribution to study", false, createAttributionOptions());
        InputField exField = InputFieldFactory.createBooleanSelectField("expected", "Expected", false);
        InputField timeOfEventField = createTimeField("eventApproximateTime", "Event time");

        InputField commentsField = InputFieldFactory.createTextArea("comments", "Comments", false);
        InputFieldAttributes.setColumns(commentsField, 80);
        InputFieldAttributes.setRows(commentsField, 5);

        creator.createRepeatingFieldGroup(MAIN_FIELD_GROUP, "adverseEvents",
                InputFieldFactory.createLongSelectField("grade", "Grade", true, WebUtils.collectOptions(EXPEDITED_GRADES, "name", null)),
                InputFieldFactory.createPastDateField("startDate", "Start date", false),
                InputFieldFactory.createPastDateField("endDate", "End date", false),
                attributionField,
                timeOfEventField,
                InputFieldFactory.createTextField("eventLocation", "Where was the patient when the event occurred?"),
                InputFieldFactory.createSelectField("hospitalization", "Hospitalization or prolongation of existing hospitalization?", false,
                        WebUtils.collectOptions(Arrays.asList(Hospitalization.values()), "name", "displayName")),
                exField,
                commentsField);
    }

    @Override
    public ExpeditedReportSection[] section() {
        return new ExpeditedReportSection[] { ExpeditedReportSection.BASICS_SECTION};
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
            errors.rejectValue(firstStartDateField.getPropertyName(), "REQUIRED", firstStartDateField.getDisplayName() + " required for primary AE");
        }

    }

    protected void validateAdverseEvent(AdverseEvent ae, int index, Map<String, InputFieldGroup> groups, Errors errors) {

    }

    protected void outcomeHelpKeyExclusion() {
        addHelpKeyExclusion("outcomes", "outcomeOtherDetails");
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
}
