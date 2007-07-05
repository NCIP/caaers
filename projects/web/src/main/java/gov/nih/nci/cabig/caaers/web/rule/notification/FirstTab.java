package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;
import gov.nih.nci.cabig.caaers.web.fields.AutocompleterField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.DefaultSelectField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultTextArea;
import gov.nih.nci.cabig.caaers.web.fields.DefaultTextField;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

public class FirstTab extends TabWithFields<ReportDefinitionCommand> {

	
	private InputFieldGroupMap map;
	
	public FirstTab(String longTitle, String shortTitle, String viewName) {
		
		super(longTitle, shortTitle, viewName);
		map = new InputFieldGroupMap();
		InputFieldGroup orgFieldGroup = new DefaultInputFieldGroup("reportDefinitionOrganization");
		AutocompleterField orgField = new AutocompleterField("organization", "Organization", true);
		orgField.getAttributes().put(InputField.DETAILS,"Enter a portion of the organization name that you are looking");
		orgFieldGroup.getFields().add(orgField);
		map.addInputFieldGroup(orgFieldGroup);
		
		//setup the fileds
		InputFieldGroup fieldGroup  = new DefaultInputFieldGroup("reportDefinitionFieldGroup");
		List<InputField> fields = fieldGroup.getFields();
		fields.add(new DefaultTextField("name","Name", true));
		fields.add(new DefaultTextArea("description", "Description", false));
		fields.add(new DefaultSelectField("timeScaleType", "Time Scale UOM", true,
				createMapFromArray(TimeScaleUnit.values())));
		fields.add(new DefaultTextField("duration", "Time Till Report Due", true));
		
		map.addInputFieldGroup(fieldGroup);
		
		
	}

	public FirstTab() {
		this("Report Definition", "Basic Details", "rule/notification/firstTab");
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

	@Override
	public void postProcess(HttpServletRequest req,
			ReportDefinitionCommand cmd, Errors errors) {
		super.postProcess(req, cmd, errors);
		ReportDefinitionCommand nfCmd = (ReportDefinitionCommand) cmd;
		if (!errors.hasErrors())
			nfCmd.updateReportCalendarTemplate();
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.web.fields.TabWithFields#validate(java.lang.Object, org.springframework.beans.BeanWrapper, java.util.Map, org.springframework.validation.Errors)
	 */
	@Override
	protected void validate(ReportDefinitionCommand command, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {
		super.validate(command, commandBean, fieldGroups, errors);
		if (!NumberUtils.isDigits(command.getDuration())) {
			errors.rejectValue("duration", "REQUIRED",
					"Invalid Time Till Report Due");
		}
	}
}
