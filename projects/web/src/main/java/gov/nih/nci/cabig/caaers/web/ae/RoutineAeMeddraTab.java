package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Attribution;
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
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;

import org.springframework.validation.Errors;
import org.springframework.beans.BeanWrapper;

/**
 * @author Krikor Krumlian
 */
public class RoutineAeMeddraTab extends AeRoutTab {
    private static final String REPORT_FIELD_GROUP = "report";
    private static final String MAIN_FIELD_GROUP = "main";
    private static final String MEDDRA_TERM_FIELD_GROUP = "ctcTerm";

    private InputFieldGroup reportFieldGroup;
    private RepeatingFieldGroupFactory mainFieldFactory,meddraTermFieldFactory;
    
    private static final Collection<Grade> GRADES = new ArrayList<Grade>(5);
    static {
        GRADES.addAll(Arrays.asList(Grade.values()));
    }

    public RoutineAeMeddraTab() {
        super("Enter basic AE information", "Adverse Events", "ae/meddraTerms");
        
        reportFieldGroup = new DefaultInputFieldGroup(REPORT_FIELD_GROUP);
        reportFieldGroup.getFields().add(InputFieldFactory.createDateField(
            "aeRoutineReport.startDate", "Start date", true));
        reportFieldGroup.getFields().add(InputFieldFactory.createDateField(
                "aeRoutineReport.endDate", "End date", true));
        
        mainFieldFactory = new RepeatingFieldGroupFactory(MAIN_FIELD_GROUP, "aeRoutineReport.adverseEvents");
        mainFieldFactory.addField(InputFieldFactory.createSelectField("grade", "Grade", true,
                InputFieldFactory.collectOptions(GRADES, "name", "code")));
        InputField attributionField = InputFieldFactory.createSelectField(
            "attributionSummary", "Attribution to study", false, createAttributionOptions());
        InputFieldAttributes.setDetails(attributionField,
            "Indicate the likelihood that this adverse event is attributable to any element of the study protocol.");
        mainFieldFactory.addField(attributionField);
        mainFieldFactory.addField(InputFieldFactory.createSelectField(
            "hospitalization", "Hospitalization", true,
                InputFieldFactory.collectOptions(Arrays.asList(Hospitalization.values()), "name", "displayName")));
        mainFieldFactory.addField(InputFieldFactory.createBooleanSelectField(
            "expected", "Expected", true));
     
        
        meddraTermFieldFactory = new RepeatingFieldGroupFactory(MEDDRA_TERM_FIELD_GROUP, "aeRoutineReport.adverseEvents");
        InputField lowLevelTermField = InputFieldFactory.createAutocompleterField("adverseEventMeddraLowLevelTerm.lowLevelTerm", "MedDRA code", true);
        lowLevelTermField.getAttributes().put("size", 25);
        meddraTermFieldFactory.addField(lowLevelTermField);

    }
    
    private Map<Object, Object> createAttributionOptions() {
        Map<Object, Object> attributionOptions = new LinkedHashMap<Object, Object>();
        attributionOptions.put("", "Please select");
        attributionOptions.putAll(
            InputFieldFactory.collectOptions(Arrays.asList(Attribution.values()), "name", "displayName"));
        return attributionOptions;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, InputFieldGroup> createFieldGroups(RoutineAdverseEventInputCommand command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        map.addInputFieldGroup(reportFieldGroup);
        int aeCount = command.getAeRoutineReport().getAdverseEvents().size();
        map.addRepeatingFieldGroupFactory(mainFieldFactory, aeCount);
        map.addRepeatingFieldGroupFactory(meddraTermFieldFactory, aeCount);
        return map;
    }
    
    
    @Override
    protected void validate(
        RoutineAdverseEventInputCommand command, BeanWrapper commandBean,
        Map<String, InputFieldGroup> fieldGroups, Errors errors
    ) {
        if (command.getAeRoutineReport().getAdverseEvents() != null) {
			for (ListIterator<AdverseEvent> lit = command.getAeRoutineReport()
					.getAdverseEvents().listIterator(); lit.hasNext();) {
				AdverseEvent ae = lit.next();
				validateAdverseEvent(ae, lit.previousIndex(), fieldGroups,
						errors);
			}
		}
    }

    private void validateAdverseEvent(AdverseEvent ae, int index, Map<String, InputFieldGroup> groups, Errors errors) {
        LowLevelTerm lowLevelTerm = ae.getAdverseEventMeddraLowLevelTerm().getLowLevelTerm();
        if (lowLevelTerm == null ) {
            InputField field = groups.get(MEDDRA_TERM_FIELD_GROUP + index).getFields().get(0);
            errors.rejectValue(field.getPropertyName(), "REQUIRED", "Missing " + field.getDisplayName());
        }
    }
    
    ////// CONFIGURATION
}
