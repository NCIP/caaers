package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
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
    private static final String REPORT_FIELD_GROUP = "report";
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
        InputFieldGroup reportFieldGroup = new DefaultInputFieldGroup(REPORT_FIELD_GROUP);
        reportFieldGroup.getFields().add(InputFieldFactory.createDateField(
            "aeReport.detectionDate", "Detection date", true));

        RepeatingFieldGroupFactory mainFieldFactory = new RepeatingFieldGroupFactory(MAIN_FIELD_GROUP, "aeReport.adverseEvents");
        mainFieldFactory.addField(InputFieldFactory.createLongSelectField("grade", "Grade", true,
                InputFieldFactory.collectOptions(EXPEDITED_GRADES, "name", null)));
        InputField attributionField = InputFieldFactory.createSelectField(
            "attributionSummary", "Attribution to study", false, createAttributionOptions());
        InputFieldAttributes.setDetails(attributionField,
            "Indicate the likelihood that this AE is attributable to any element of the study protocol.");
        mainFieldFactory.addField(attributionField);
        mainFieldFactory.addField(InputFieldFactory.createSelectField(
            "hospitalization", "Hospitalization", true,
                InputFieldFactory.collectOptions(Arrays.asList(Hospitalization.values()), "name", "displayName")));
        mainFieldFactory.addField(InputFieldFactory.createBooleanSelectField(
            "expected", "Expected", true));
        mainFieldFactory.addField(InputFieldFactory.createTextArea(
            "comments", "Comments", false));

        RepeatingFieldGroupFactory meddraTermFieldFactory = new RepeatingFieldGroupFactory(CTC_TERM_FIELD_GROUP, "aeReport.adverseEvents");
        InputField lowLevelTermField = InputFieldFactory.createAutocompleterField("adverseEventMeddraLowLevelTerm.lowLevelTerm", "MedDRA code", true);
        meddraTermFieldFactory.addField(lowLevelTermField);
    	//-
        InputFieldGroupMap map = new InputFieldGroupMap();
        map.addInputFieldGroup(reportFieldGroup);
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
        }
    }
    @Override
    public ExpeditedReportSection section() {
    	return ExpeditedReportSection.BASICS_MEDRA_SECTION;
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
