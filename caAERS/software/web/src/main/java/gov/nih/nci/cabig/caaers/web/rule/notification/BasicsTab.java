package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.domain.ReportFormatType;
import gov.nih.nci.cabig.caaers.domain.ConfigProperty;
import gov.nih.nci.cabig.caaers.domain.ConfigPropertyType;
import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

public class BasicsTab extends TabWithFields<ReportDefinitionCommand> {

    private InputFieldGroupMap map;

    public BasicsTab(String longTitle, String shortTitle, String viewName) {

        super(longTitle, shortTitle, viewName);
    }

    public BasicsTab() {
        this("Report Definition", "Details", "rule/notification/basicsTab");
    }
    
    @Override
    public Map<String, Object> referenceData(HttpServletRequest request,ReportDefinitionCommand command) {
    	// TODO Auto-generated method stub
    	return super.referenceData(request, command);
    }
    
    

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(ReportDefinitionCommand command) {
        map = new InputFieldGroupMap();
        InputFieldGroup orgFieldGroup = new DefaultInputFieldGroup("reportDefinitionOrganization");
        InputField orgField = InputFieldFactory.createAutocompleterField("reportDefinition.organization", "Organization", true);
        InputFieldAttributes.setDetails(orgField,"Enter a portion of the organization name that you are looking for.");
        orgFieldGroup.getFields().add(orgField);
        map.addInputFieldGroup(orgFieldGroup);

        // setup the fileds
        InputFieldGroup fieldGroup = new DefaultInputFieldGroup("reportDefinitionFieldGroup");
        List<InputField> fields = fieldGroup.getFields();
        InputField nameField = InputFieldFactory.createTextField("reportDefinition.name", "Name", true);
        InputFieldAttributes.setSize(nameField, 50);
        fields.add(nameField);

        InputField labelField = InputFieldFactory.createTextField("reportDefinition.label", "Display Name", true);
        InputFieldAttributes.setSize(labelField, 50);
        fields.add(labelField);

        InputField descField = InputFieldFactory.createTextArea("reportDefinition.description", "Description", false);
        InputFieldAttributes.setColumns(descField, 50);
        fields.add(descField);

        InputField amendableField = InputFieldFactory.createBooleanSelectField("reportDefinition.amendable", "Amendable?", true);
        fields.add(amendableField);

/*
        InputField expeditedField = InputFieldFactory.createBooleanSelectField("reportDefinition.expedited", "Report is expedited?", true);
        fields.add(expeditedField);

*/
        Map<Object, Object> reportTypeOptions = new LinkedHashMap<Object, Object>();
        List<ConfigProperty> list = command.getCpRepository().getByType(ConfigPropertyType.REPORT_TYPE);
        for (int i=0; i<list.size(); i++) {
            reportTypeOptions.put(list.get(i).getId(), list.get(i).getName());
        }
        // reportTypeOptions.put("-1", "Create new...");
        InputField reportType = InputFieldFactory.createSelectField("reportDefinition.reportType", "Report type?", true, reportTypeOptions);
        fields.add(reportType);

        Map<Object, Object> reportFormatTypesOptions = new LinkedHashMap<Object, Object>();
        reportFormatTypesOptions.put("", "Please select");
        reportFormatTypesOptions.putAll(WebUtils.collectOptions(Arrays.asList(ReportFormatType.values()), "name", "displayName"));
        InputField reportFormatField = InputFieldFactory.createSelectField("reportDefinition.reportFormatType", "Report Format", true, reportFormatTypesOptions);
        fields.add(reportFormatField);

        InputField attributionRequiredField = InputFieldFactory.createBooleanSelectField("reportDefinition.attributionRequired", "Attribution required?", true);
        fields.add(attributionRequiredField);

        fields.add(InputFieldFactory.createSelectField("reportDefinition.timeScaleUnitType","Time Scale UOM", true, createMapFromArray(TimeScaleUnit.values())));
        InputField timeTillReportDueField = InputFieldFactory.createTextField("reportDefinition.duration", "Time until report due", true);
        InputFieldAttributes.setSize(timeTillReportDueField, 2);
        fields.add(timeTillReportDueField);
        fields.add(InputFieldFactory.createBooleanSelectField("reportDefinition.physicianSignOff", "Physician signoff required?", true));

        Map<Object, Object> parentOptions = new LinkedHashMap<Object, Object>();
        parentOptions.put("", "Please select");

        if (command.getMODE().equals("EDIT"))
            command.getReportDefinitionDao().reassociate(command.getReportDefinition());

        if (command.getReportDefinition() != null && command.getReportDefinition().getOrganization() != null) {
            List<ReportDefinition> rdList = command.getReportDefinitionDao().getAll(command.getReportDefinition().getOrganization().getId());
            for (int i=0; i<rdList.size(); i++) {
                ReportDefinition rd = rdList.get(i);
                if (rd.getId() != command.getReportDefinition().getId())
                    parentOptions.put(rd.getId(), rd.getName());
            }
        }
        InputField parentReportDefinition = InputFieldFactory.createSelectField("reportDefinition.parent", "Parent", false, parentOptions);
        fields.add(parentReportDefinition);

        map.addInputFieldGroup(fieldGroup);
        return map;
    }

    protected Map<Object, Object> createMapFromArray(Object[] arr) {
        Map<Object, Object> map = new LinkedHashMap<Object, Object>();
        map.put("", "Select a Value");
        for (Object o : arr) map.put(o, o);
        return map;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.cabig.caaers.web.fields.TabWithFields#validate(java.lang.Object,
     *      org.springframework.beans.BeanWrapper, java.util.Map,
     *      org.springframework.validation.Errors)
     */
    @Override
    protected void validate(ReportDefinitionCommand command, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {
        super.validate(command, commandBean, fieldGroups, errors);
        if (command.getReportDefinition().getDuration() == null) {
            errors.rejectValue("reportDefinition.duration", "RPD_002", "Invalid Time Till Report Due");
        }
        
        //check for duplicate report definitions.
        if(command.isSimilarReportDefinitionExist(command.getReportDefinition())){
        	errors.rejectValue("reportDefinition.name","RPD_001","Duplicate Report Definition!.");
        }
    }

    public void onBind(HttpServletRequest request, ReportDefinitionCommand command, Errors errors) {
        // request.getParameter("")
    }
}
