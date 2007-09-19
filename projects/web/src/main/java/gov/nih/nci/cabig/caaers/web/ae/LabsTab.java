package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.Lab;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;

import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 */
public class LabsTab extends AeTab {
	private ConfigProperty configurationProperty;

	public LabsTab() {
		super("Diagnostic test and lab results", "Labs", "ae/labs");
	}

	private void addLabValueFields(RepeatingFieldGroupFactory fieldFactory,
			String propName, String displayName) {
		fieldFactory.addField(InputFieldFactory.createTextField(propName
				+ ".value", displayName + " value", false));
		fieldFactory.addField(InputFieldFactory.createDateField(propName
				+ ".date", displayName + " date", false));
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, InputFieldGroup> createFieldGroups(
			ExpeditedAdverseEventInputCommand command) {
		// -
		RepeatingFieldGroupFactory fieldFactory = new RepeatingFieldGroupFactory(
				"lab", "aeReport.labs");
		fieldFactory
				.setDisplayNameCreator(new RepeatingFieldGroupFactory.DisplayNameCreator() {
					public String createDisplayName(int index) {
						char c = (char) ('A' + index);
						return "Lab " + c;
					}
				});
		 fieldFactory.addField(InputFieldFactory.createAutocompleterField("name", "Lab test name"));
		 InputField otherField = InputFieldFactory.createTextField("other", "Other", false);
		 InputFieldAttributes.setSize(otherField, 50);
	     fieldFactory.addField(otherField);

		fieldFactory.addField(InputFieldFactory.createSelectField("units",
				"Units", true, InputFieldFactory.collectOptions(configurationProperty.getMap().get("labUnitsRefData"),
						"code","desc", "Please select")));
		addLabValueFields(fieldFactory, "baseline", "Baseline");
		addLabValueFields(fieldFactory, "nadir", "Worst");
		addLabValueFields(fieldFactory, "recovery", "Recovery");
		// -
		InputFieldGroupMap groups = new InputFieldGroupMap();
		groups.addRepeatingFieldGroupFactory(fieldFactory, command
				.getAeReport().getLabs().size());
		return groups;
	}

	@Override
	protected void validate(ExpeditedAdverseEventInputCommand command,
			BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups,
			Errors errors) {
		super.validate(command, commandBean, fieldGroups, errors);
		int index = 0;
		for(Lab lab : command.getAeReport().getLabs()){
			if(lab.getName() == null && lab.getOther() == null){
				errors.rejectValue(
		                String.format("aeReport.labs[%d]", index),
		                "REQUIRED",
		                "Either a known test name or other is required"
		            );
			}
			index++;
		}

	}

	public ConfigProperty getConfigurationProperty() {
		return configurationProperty;
	}

	public void setConfigurationProperty(ConfigProperty configurationProperty) {
		this.configurationProperty = configurationProperty;
	}

	@Override
	public ExpeditedReportSection section() {
		return ExpeditedReportSection.LABS_SECTION;
	}
}
