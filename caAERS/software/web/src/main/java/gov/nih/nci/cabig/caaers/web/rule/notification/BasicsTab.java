/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.domain.ReportFormatType;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;
import gov.nih.nci.cabig.caaers.web.fields.*;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ion C. Olaru
 */
public class BasicsTab extends TabWithFields<ReportDefinitionCommand> {

    private InputFieldGroupMap map;
    
    public BasicsTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
    }

    public BasicsTab() {
        this("Report Definition", "Details", "rule/notification/basicsTab");
        addFieldDecorators(new SecurityObjectIdFieldDecorator(ReportDefinition.class), new ReadonlyFieldDecorator());
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
        //InputFieldAttributes.setDetails(orgField,"Enter a portion of the organization name that you are looking for.");
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

        InputField headerField = InputFieldFactory.createTextArea("reportDefinition.header", "Header", false);
        InputFieldAttributes.setColumns(headerField, 50);
        fields.add(headerField);

        InputField footerField = InputFieldFactory.createTextArea("reportDefinition.footer", "Footer", false);
        InputFieldAttributes.setColumns(footerField, 50);
        fields.add(footerField);

        InputField amendableField = InputFieldFactory.createBooleanSelectField("reportDefinition.amendable", "Amendable?", true);
        fields.add(amendableField);

        InputField reportGroupField = InputFieldFactory.createSelectField("reportDefinition.group", "Report Group", true, command.getGroupOptions());
        fields.add(reportGroupField);
        
        InputField reportTypeField = InputFieldFactory.createSelectField("reportDefinition.reportType", "Report Type", true, command.collectReportTypeOptions());
        fields.add(reportTypeField);
        
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

        InputField parentReportDefinition = InputFieldFactory.createSelectField("reportDefinition.parent", "Parent", false, command.getParentOptions());
        fields.add(parentReportDefinition);
        
        fields.add(InputFieldFactory.createBooleanSelectField("reportDefinition.workflowEnabled", "Workflow enabled?", true));
        fields.add(InputFieldFactory.createBooleanSelectField("reportDefinition.includeNonSeriousAes", "Include all non-serious AEs by default?", true));

        fields.add(InputFieldFactory.createBooleanSelectField("reportDefinition.enabled", "Enabled?", true));

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

}
