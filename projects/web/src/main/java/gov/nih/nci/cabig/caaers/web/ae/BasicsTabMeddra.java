package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;

/**
 * @author Krikor Krumlian
 */
public class BasicsTabMeddra extends AeTab {
    private static final String MAIN_FIELD_GROUP = "main";
    private static final String CTC_TERM_FIELD_GROUP = "ctcTerm";

    private static final Collection<Grade> EXPEDITED_GRADES = new ArrayList<Grade>(5);
    static {
        EXPEDITED_GRADES.addAll(Arrays.asList(Grade.values()));
        EXPEDITED_GRADES.remove(Grade.NORMAL);
    }

    private LowLevelTermDao lowLevelTermDao;

    public BasicsTabMeddra() {
        super("Enter basic AE information", "AEs", "ae/enterBasicMeddra");

    }

    private Map<Object, Object> createAttributionOptions() {
        Map<Object, Object> attributionOptions = new LinkedHashMap<Object, Object>();
        attributionOptions.put("", "Please select");
        attributionOptions.putAll(
            InputFieldFactory.collectOptions(Arrays.asList(Attribution.values()), "name", null));
        return attributionOptions;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, InputFieldGroup> createFieldGroups(ExpeditedAdverseEventInputCommand command) {
    	//-

        RepeatingFieldGroupFactory mainFieldFactory = new RepeatingFieldGroupFactory(MAIN_FIELD_GROUP, "aeReport.adverseEvents");
        mainFieldFactory.addField(InputFieldFactory.createLongSelectField("grade", "Grade", true,
                InputFieldFactory.collectOptions(EXPEDITED_GRADES, "name", null)));
        mainFieldFactory.addField(InputFieldFactory.createDateField(
                "startDate", "Start date"));
        mainFieldFactory.addField(InputFieldFactory.createDateField(
                "endDate", "End date"));
        InputField attributionField = InputFieldFactory.createSelectField(
            "attributionSummary", "Attribution to study", false, createAttributionOptions());
        InputFieldAttributes.setDetails(attributionField,
            "Indicate the likelihood that this AE is attributable to any element of the study protocol.");
        mainFieldFactory.addField(attributionField);
        mainFieldFactory.addField(InputFieldFactory.createSelectField(
            "hospitalization", "Hospitalization", true,
                InputFieldFactory.collectOptions(Arrays.asList(Hospitalization.values()), "name", "displayName")));
        InputField exField = InputFieldFactory.createBooleanSelectField(
                "expected", "Expected", true);
        InputFieldAttributes.setDetails(exField, "If known, specify whether the AE is expected or not, as determined by the protocol guidelines. If this is a CTEP Sponsored trial, you may refer also to the AdEERS Agent Specific Adverse Event List (ASAEL).");
        mainFieldFactory.addField(exField);

        InputField commentsField = InputFieldFactory.createTextArea(
                "comments", "Comments", false);
        InputFieldAttributes.setColumns(commentsField, 50);
        mainFieldFactory.addField(commentsField);

        RepeatingFieldGroupFactory meddraTermFieldFactory = new RepeatingFieldGroupFactory(CTC_TERM_FIELD_GROUP, "aeReport.adverseEvents");
        InputField lowLevelTermField = InputFieldFactory.createAutocompleterField("adverseEventMeddraLowLevelTerm.lowLevelTerm", "MedDRA code", true);
        meddraTermFieldFactory.addField(lowLevelTermField);
    	//-
        InputFieldGroupMap map = new InputFieldGroupMap();
        int aeCount = command.getAeReport().getAdverseEvents().size();
        map.addRepeatingFieldGroupFactory(mainFieldFactory, aeCount);
        map.addRepeatingFieldGroupFactory(meddraTermFieldFactory, aeCount);
        return map;
    }

    // TODO: I don't need this method
    @Override
    public Map<String, Object> referenceData(ExpeditedAdverseEventInputCommand command) {
        Map<String, Object> refdata = super.referenceData(command);
        return refdata;
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
    }

    private void validateAdverseEvent(AdverseEvent ae, int index, Map<String, InputFieldGroup> groups, Errors errors) {
    	if(index == 0){
        	InputField field = groups.get(MAIN_FIELD_GROUP + index).getFields().get(1);
        	if(ae.getStartDate() == null) errors.rejectValue(field.getPropertyName(), "REQUIRED", "Missing " + field.getDisplayName());
        }
    }

    @Override
    public ExpeditedReportSection section() {
    	return ExpeditedReportSection.BASICS_SECTION;
    }
    ////// CONFIGURATION

    @Required
    public void setLowLevelTermDao(LowLevelTermDao lowLevelTermDao) {
        this.lowLevelTermDao = lowLevelTermDao;
    }

    // for testing
    LowLevelTermDao getLowLevelTermDao() {
        return lowLevelTermDao;
    }
}
