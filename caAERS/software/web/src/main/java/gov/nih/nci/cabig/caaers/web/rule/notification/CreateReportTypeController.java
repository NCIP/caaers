package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.dao.*;
import gov.nih.nci.cabig.caaers.web.rule.notification.ReportTypeCommand;
import gov.nih.nci.cabig.caaers.web.fields.*;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.domain.ConfigPropertyType;
import gov.nih.nci.cabig.caaers.domain.ConfigProperty;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class CreateReportTypeController extends SimpleFormController {

    private Logger log = Logger.getLogger(CreateReportTypeController.class);

    private static final String REPORTTYPE_FIELD_GROUP = "ReportType";
    private InputFieldGroup rtFieldGroup;
    private ConfigPropertyDao cpDao;
    private static final String viewName = "rule/notification/createReportType";

    public CreateReportTypeController() {
        setFormView(viewName);
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        ReportTypeCommand command = new ReportTypeCommand();
        return command;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Map referenceData(final HttpServletRequest request, final Object command, final Errors errors) throws Exception {
        Map<Object, Object> refDataMap = new LinkedHashMap<Object, Object>();
        ReportTypeCommand rpCommand = (ReportTypeCommand) command;
        Map<String, InputFieldGroup> groupMap = createFieldGroups(command);
        populateHelpAttributeOnFields(groupMap);
        refDataMap.put("fieldGroups", groupMap);
        return refDataMap;
    }

    protected Map<String, InputFieldGroup> createFieldGroups(Object command) {
        InputFieldGroupMap fieldMap = new InputFieldGroupMap();
        rtFieldGroup = new DefaultInputFieldGroup(REPORTTYPE_FIELD_GROUP);

        rtFieldGroup.getFields().add(InputFieldFactory.createTextField("code", "Code", true));
        rtFieldGroup.getFields().add(InputFieldFactory.createTextField("name", "Name", true));
        rtFieldGroup.getFields().add(InputFieldFactory.createTextArea("description", "Description", false));

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
        ModelAndView modelAndView = new ModelAndView("rule/notification/confirmReportType");

        return modelAndView;
    }

    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException e) throws Exception {
        super.onBindAndValidate(request, command, e);

        ReportTypeCommand cmd = (ReportTypeCommand) command;
        List<ConfigProperty> list = cpDao.getByType(ConfigPropertyType.REPORT_TYPE);
        for (ConfigProperty cp : list) {
            if (cp.getCode().equals(cmd.getCode())) {
                e.rejectValue("code", "", "Duplicate report type code");
            }
            if (cp.getName().equals(cmd.getName())) {
                e.rejectValue("name", "", "Duplicate report type name");
            }
        }
    }

    protected void populateHelpAttributeOnFields(Map<String, InputFieldGroup> groupMap) {

        if (groupMap == null || groupMap.isEmpty()) return;
        for (InputFieldGroup group : groupMap.values()) {
            for (InputField field : group.getFields()) {
                setHelpKeyAttribute(field);
            }
        }
    }

    final protected void setHelpKeyAttribute(InputField field) {
        String helpKeyPrefix = (getViewName() != null) ? getViewName().replaceAll("/", ".") : "";
        String[] nameSubset = null;
        nameSubset = field.getPropertyName().split("\\.");
        field.getAttributes().put(InputField.HELP, helpKeyPrefix + "." + field.getPropertyName().replaceAll("(\\[\\d+\\])", ""));
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