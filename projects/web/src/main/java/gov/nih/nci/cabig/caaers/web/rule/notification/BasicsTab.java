package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

public class BasicsTab extends TabWithFields<ReportDefinitionCommand> {


	private InputFieldGroupMap map;

	public BasicsTab(String longTitle, String shortTitle, String viewName) {

		super(longTitle, shortTitle, viewName);
		map = new InputFieldGroupMap();
		InputFieldGroup orgFieldGroup = new DefaultInputFieldGroup("reportDefinitionOrganization");
		InputField orgField = InputFieldFactory.createAutocompleterField("reportDefinition.organization", "Organization", true);
		InputFieldAttributes.setDetails(orgField, "Enter a portion of the organization name that you are looking");
		orgFieldGroup.getFields().add(orgField);
		map.addInputFieldGroup(orgFieldGroup);

		//setup the fileds
		InputFieldGroup fieldGroup  = new DefaultInputFieldGroup("reportDefinitionFieldGroup");
		List<InputField> fields = fieldGroup.getFields();
		InputField nameField = InputFieldFactory.createTextField("reportDefinition.name","Name", true);
        InputFieldAttributes.setSize(nameField, 50);
        fields.add(nameField);
		InputField descField = InputFieldFactory.createTextArea("reportDefinition.description", "Description", false);
		InputFieldAttributes.setColumns(descField, 50);
		fields.add(descField);

		InputField amendableField = InputFieldFactory.createBooleanSelectField("reportDefinition.amendable", "Amendable?", true);
		fields.add(amendableField);

		fields.add(InputFieldFactory.createSelectField("reportDefinition.timeScaleUnitType", "Time Scale UOM", true,
				createMapFromArray(TimeScaleUnit.values())));
		fields.add(InputFieldFactory.createTextField("reportDefinition.duration", "Time Till Report Due", true));

		map.addInputFieldGroup(fieldGroup);


	}

	public BasicsTab() {
		this("Report Definition", "Basic Details", "rule/notification/basicsTab");
	}

	@Override
	public Map<String, InputFieldGroup> createFieldGroups(ReportDefinitionCommand command) {
		return map;
	}
    protected Map<Object, Object> createMapFromArray(Object[] arr){
    	Map<Object,Object> map = new LinkedHashMap<Object, Object>();
    	map.put("", "Select a Value");
    	for(Object o : arr)
    		map.put(o, o);
    	return map;
    }


	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.web.fields.TabWithFields#validate(java.lang.Object, org.springframework.beans.BeanWrapper, java.util.Map, org.springframework.validation.Errors)
	 */
	@Override
	protected void validate(ReportDefinitionCommand command, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {
		super.validate(command, commandBean, fieldGroups, errors);
		if(command.getReportDefinition().getDuration() == null){
			errors.rejectValue("reportDefinition.duration", "REQUIRED",	"Invalid Time Till Report Due");
		}
	}
}
