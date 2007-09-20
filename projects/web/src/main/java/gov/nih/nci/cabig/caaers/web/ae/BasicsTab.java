package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 */
public class BasicsTab extends AeTab {
    private static final String MAIN_FIELD_GROUP = "main";
    private static final String CTC_TERM_FIELD_GROUP = "ctcTerm";
    private static final String CTC_OTHER_FIELD_GROUP = "ctcOther";

    private static final Collection<Grade> EXPEDITED_GRADES = new ArrayList<Grade>(5);
    static {
        EXPEDITED_GRADES.addAll(Arrays.asList(Grade.values()));
        EXPEDITED_GRADES.remove(Grade.NORMAL);
    }

    private CtcDao ctcDao;

//    private InputFieldGroup reportFieldGroup;
//    private RepeatingFieldGroupFactory mainFieldFactory, ctcTermFieldFactory, ctcOtherFieldFactory;

    public BasicsTab() {

        super("Enter basic AE information", "Enter AEs", "ae/enterBasic");
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
            "attributionSummary", "Attribution to lead IND", false, createAttributionOptions());
        InputFieldAttributes.setDetails(attributionField,
            "Indicate the likelihood that this AE is attributable to any element of the study protocol.");
        mainFieldFactory.addField(attributionField);
        mainFieldFactory.addField(InputFieldFactory.createSelectField(
            "hospitalization", "Hospitalization", true,
                InputFieldFactory.collectOptions(Arrays.asList(Hospitalization.values()), "name", "displayName")));
        mainFieldFactory.addField(InputFieldFactory.createBooleanSelectField(
            "expected", "Expected", true));
        InputField commentsField = InputFieldFactory.createTextArea("comments", "Comments", false);
        InputFieldAttributes.setColumns(commentsField, 50);
        mainFieldFactory.addField(commentsField);

        RepeatingFieldGroupFactory ctcTermFieldFactory = new RepeatingFieldGroupFactory(CTC_TERM_FIELD_GROUP, "aeReport.adverseEvents");
        InputField ctcTermField = InputFieldFactory.createAutocompleterField("adverseEventCtcTerm.ctcTerm", "CTC term", true);
        InputFieldAttributes.setDetails(ctcTermField,
            "Type a portion of the CTC term you are looking for.  If you select a category, only terms in that category will be shown.");
        ctcTermFieldFactory.addField(ctcTermField);

        RepeatingFieldGroupFactory ctcOtherFieldFactory = new RepeatingFieldGroupFactory(CTC_OTHER_FIELD_GROUP, "aeReport.adverseEvents");
        ctcOtherFieldFactory.addField(InputFieldFactory.createTextArea("detailsForOther", "Other (specify)", false));

    	//-
        InputFieldGroupMap map = new InputFieldGroupMap();
        int aeCount = command.getAeReport().getAdverseEvents().size();
        map.addRepeatingFieldGroupFactory(mainFieldFactory, aeCount);
        map.addRepeatingFieldGroupFactory(ctcTermFieldFactory, aeCount);
        map.addRepeatingFieldGroupFactory(ctcOtherFieldFactory, aeCount);
        return map;
    }

    @Override
    public Map<String, Object> referenceData(ExpeditedAdverseEventInputCommand command) {
        Map<String, Object> refdata = super.referenceData(command);
        refdata.put("ctcCategories", command.getAssignment().getStudySite().getStudy().getTerminology().getCtcVersion().getCategories());
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
        CtcTerm ctcTerm = ae.getAdverseEventCtcTerm().getCtcTerm();

        if (ctcTerm != null && ctcTerm.isOtherRequired() && ae.getDetailsForOther() == null) {
            InputField field = groups.get(CTC_OTHER_FIELD_GROUP + index).getFields().get(0);
            errors.rejectValue(field.getPropertyName(), "REQUIRED", "Missing " + field.getDisplayName());
        }
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
    public void setCtcDao(CtcDao ctcDao) {
        this.ctcDao = ctcDao;
    }

    // for testing
    CtcDao getCtcDao() {
        return ctcDao;
    }
}
