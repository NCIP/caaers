package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.dao.*;
import gov.nih.nci.cabig.caaers.web.rule.notification.ReportTypeCommand;
import gov.nih.nci.cabig.caaers.web.fields.*;
import gov.nih.nci.cabig.caaers.domain.ConfigPropertyType;
import gov.nih.nci.cabig.caaers.domain.ConfigProperty;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class CreateConfigPropertyController extends SimpleFormController {

    private Logger log = Logger.getLogger(CreateConfigPropertyController.class);

    private static final String REPORTTYPE_FIELD_GROUP = "ReportType";
    private InputFieldGroup rtFieldGroup;
    private ConfigPropertyDao cpDao;
    private static final String viewName = "rule/notification/createConfigProperty";

    public CreateConfigPropertyController() {
        setFormView(viewName);
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        ReportTypeCommand command = new ReportTypeCommand();
        String configTypeName = request.getParameter("configPropertyType");
        if(StringUtils.isNotEmpty(configTypeName)){
        	command.setConfigPropertyType(ConfigPropertyType.valueOf(configTypeName));
        }
        return command;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Map referenceData(final HttpServletRequest request, final Object command, final Errors errors) throws Exception {
        Map<Object, Object> refDataMap = new LinkedHashMap<Object, Object>();
        ReportTypeCommand rpCommand = (ReportTypeCommand) command;
        Map<String, InputFieldGroup> groupMap = createFieldGroups(command);
        refDataMap.put("fieldGroups", groupMap);
        return refDataMap;
    }

    protected Map<String, InputFieldGroup> createFieldGroups(Object command) {
        InputFieldGroupMap fieldMap = new InputFieldGroupMap();
        rtFieldGroup = new DefaultInputFieldGroup(REPORTTYPE_FIELD_GROUP);
        
        InputField codeField = InputFieldFactory.createTextField("code", "Code", true);
        InputFieldAttributes.setHelpProperty(codeField, "configProperty.create_config_property.code");
        rtFieldGroup.getFields().add(codeField);
        
        InputField nameField = InputFieldFactory.createTextField("name", "Name", true);
        InputFieldAttributes.setSize(nameField, 50);
        InputFieldAttributes.setHelpProperty(nameField, "configProperty.create_config_property.name");
        rtFieldGroup.getFields().add(nameField);
        
        InputField descField = InputFieldFactory.createTextArea("description", "Description", false);
        InputFieldAttributes.setHelpProperty(descField, "configProperty.create_config_property.description");
        InputFieldAttributes.setColumns(descField, 50);
        rtFieldGroup.getFields().add(descField);
        
        fieldMap.addInputFieldGroup(rtFieldGroup);
        return fieldMap;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object cmd, BindException errors) throws Exception {
        ReportTypeCommand command = (ReportTypeCommand) cmd;

        command.getCp().setName(command.getName());
        command.getCp().setCode(command.getCode());
        command.getCp().setDescription(command.getDescription());

        cpDao.save(command.getCp());
        ModelAndView modelAndView = new ModelAndView("rule/notification/confirmConfigProperty");

        return modelAndView;
    }

    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException e) throws Exception {
        super.onBindAndValidate(request, command, e);
        
        ReportTypeCommand cmd = (ReportTypeCommand) command;
        BeanWrapper cmdWrapper = new BeanWrapperImpl(cmd);
        for(InputField field : createFieldGroups(cmd).get(REPORTTYPE_FIELD_GROUP).getFields()){
        	field.validate(cmdWrapper, e);
        }
        
        List<ConfigProperty> list = cpDao.getByType(cmd.getConfigPropertyType());
        for (ConfigProperty cp : list) {
            if (cp.getCode().equals(cmd.getCode())) {
                e.rejectValue("code", "CP_011", "Duplicate report group code");
            }
            if (cp.getName().equals(cmd.getName())) {
                e.rejectValue("name", "CP_012", "Duplicate report group name");
            }
        }
    }


    public String getViewName() {
        return viewName;
    }

    public ConfigPropertyDao getCpDao() {
        return cpDao;
    }

    public void setCpDao(ConfigPropertyDao cpDao) {
        this.cpDao = cpDao;
    }
}