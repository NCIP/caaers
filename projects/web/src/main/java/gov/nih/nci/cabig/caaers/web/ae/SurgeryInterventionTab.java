package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;


/**
 * @author Krikor Krumlian
 */
public class SurgeryInterventionTab extends AeTab {

    public SurgeryInterventionTab() {
        super("Surgery Intervention", "Surgery", "ae/surgeryIntervention");

    }

    @Override
    public InputFieldGroupMap createFieldGroups(ExpeditedAdverseEventInputCommand command) {
    	
        RepeatingFieldGroupFactory fieldFactory = new RepeatingFieldGroupFactory("surgeryIntervention", "aeReport.surgeryInterventions");
    	
    	fieldFactory.setDisplayNameCreator(new RepeatingFieldGroupFactory.DisplayNameCreator() {
            public String createDisplayName(int index) {
                return "Surgery " + (index + 1);
            }
        });


    	fieldFactory.addField(InputFieldFactory.createTextField("treatmentArm", "Treatment arm", false));
        InputField descField = InputFieldFactory.createTextArea("description", "Treatment arm description", false);
        InputFieldAttributes.setColumns(descField, 45);
        fieldFactory.addField(descField);
        fieldFactory.addField(InputFieldFactory.createAutocompleterField("anatomicSite", "Intervention site", false));
        fieldFactory.addField(InputFieldFactory.createDateField(
                "interventionDate", "Date of intervention",  false));
        //      -
        InputFieldGroupMap map = new InputFieldGroupMap();
        int siCount = command.getAeReport().getSurgeryInterventions().size();
        map.addRepeatingFieldGroupFactory(fieldFactory, siCount);
        return map;
    }

    @Override
    public ExpeditedReportSection section() {
    	return ExpeditedReportSection.SURGERY_INTERVENTION_SECTION;
    }
}
