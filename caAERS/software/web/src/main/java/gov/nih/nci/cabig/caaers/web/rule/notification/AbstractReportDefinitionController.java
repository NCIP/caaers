package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ConfigPropertyDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.ReportFormatType;
import gov.nih.nci.cabig.caaers.domain.repository.ConfigPropertyRepositoryImpl;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode;
import gov.nih.nci.cabig.caaers.domain.report.Mandatory;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportFormat;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;
import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.AutomaticSaveAjaxableFormController;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

public abstract class AbstractReportDefinitionController extends AutomaticSaveAjaxableFormController<ReportDefinitionCommand, ReportDefinition, ReportDefinitionDao> {
    public static final String AJAX_SUBVIEW_PARAMETER = "subview";
    public static final String AJAX_REQUEST_PARAMETER = "isAjax";

    private ConfigProperty configurationProperty;
    protected ReportDefinitionDao reportDefinitionDao;
    protected Map<String, String> roles;
    protected OrganizationDao organizationDao;
    protected ConfigPropertyRepositoryImpl configPropertyRepository;
    protected ConfigPropertyDao configPropertyDao;

    public AbstractReportDefinitionController() {
        initFlow();
        super.setAllowDirtyBack(false);
        super.setAllowDirtyForward(false);
    }

    // initializes the flow
    protected void initFlow() {
        setFlow(new Flow<ReportDefinitionCommand>(getFlowName()));
        BasicsTab firstTab = new BasicsTab();
        ReportDeliveryDefinitionTab deliveryDefTab = new ReportDeliveryDefinitionTab();
        ReportMandatoryFieldDefinitionTab mandatoryFieldTab = new ReportMandatoryFieldDefinitionTab();
        NotificationsTab secondTab = new NotificationsTab();
        ReviewTab thirdTab = new ReviewTab();

        getFlow().addTab(firstTab);
        getFlow().addTab(deliveryDefTab);
        getFlow().addTab(mandatoryFieldTab);
        getFlow().addTab(secondTab);
        getFlow().addTab(thirdTab);
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        ControllerTools.registerDomainObjectEditor(binder, organizationDao);
        ControllerTools.registerDomainObjectEditor(binder, reportDefinitionDao);
        ControllerTools.registerDomainObjectEditor(binder, configPropertyDao);
        ControllerTools.registerEnumEditor(binder, ReportFormat.class);
        ControllerTools.registerEnumEditor(binder, TimeScaleUnit.class);
        ControllerTools.registerEnumEditor(binder, ReportFormatType.class);
        ControllerTools.registerEnumEditor(binder, Mandatory.class);
    }

    @Override
    protected ModelAndView processFinish(HttpServletRequest req, HttpServletResponse res, Object cmd, BindException arg3) throws Exception {
        ReportDefinitionCommand rpDefCmd = (ReportDefinitionCommand) cmd;
        reportDefinitionDao.save(rpDefCmd.getReportDefinition());
        Map<String, Object> model = new ModelMap();
        // model.put("study", command.getStudy().getId());
        return new ModelAndView("redirectToNotificationList", model);
    }

    @Override
    protected String getViewName(HttpServletRequest request, Object command, int page) {
        Object subviewName = findInRequest(request, AJAX_SUBVIEW_PARAMETER);
        if (subviewName != null) {
            return "rule/notification/ajax/" + subviewName;
        } else {
            return super.getViewName(request, command, page);
        }
    }

    /** Should return the name of the flow */
    public abstract String getFlowName();

    private Object findInRequest(HttpServletRequest request, String attributName) {

        Object attr = request.getParameter(attributName);
        if (attr == null) attr = request.getAttribute(attributName);
        return attr;
    }

    @Override
    protected boolean suppressValidation(HttpServletRequest request, Object command) {
        if (StringUtils.equals(request.getParameter("_action"), "deleteDelivery")) return true;
        return isAjaxAddRequest(request) ? true : super.suppressValidation(request, command);
    }

    protected boolean isAjaxAddRequest(HttpServletRequest request) {
        return findInRequest(request, AJAX_REQUEST_PARAMETER) != null;
    }

    protected void populateMandatoryFields(List<ReportMandatoryFieldDefinition> mfList, TreeNode node) {
        if (StringUtils.isNotEmpty(node.getPropertyPath())) {
            ReportMandatoryFieldDefinition mf = new ReportMandatoryFieldDefinition(node.getPropertyPath());
            mfList.add(mf);
        }
        if (node.getChildren() != null) {
            for (TreeNode n : node.getChildren())
                populateMandatoryFields(mfList, n);
        }
    }

    @Override
    protected ReportDefinitionDao getDao() {
        return reportDefinitionDao;
    }

    @Override
    protected ReportDefinition getPrimaryDomainObject(ReportDefinitionCommand cmd) {
        return cmd.getReportDefinition();
    }

    // /BEAN PROPERTIES
    public ReportDefinitionDao getReportDefinitionDao() {
        return reportDefinitionDao;
    }

    public void setReportDefinitionDao(ReportDefinitionDao rdDao) {
        this.reportDefinitionDao = rdDao;
    }

    public void setRoles(Map<String, String> roleList) {
        this.roles = roleList;
    }

    public Map<String, String> getAllRoles() {
        return roles;
    }

    public OrganizationDao getOrganizationDao() {
        return organizationDao;
    }

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public ConfigProperty getConfigurationProperty() {
        return configurationProperty;
    }

    public void setConfigurationProperty(ConfigProperty configurationProperty) {
        this.configurationProperty = configurationProperty;
    }

    public ConfigPropertyRepositoryImpl getConfigPropertyRepository() {
        return configPropertyRepository;
    }

    public void setConfigPropertyRepository(ConfigPropertyRepositoryImpl configPropertyRepository) {
        this.configPropertyRepository = configPropertyRepository;
    }

    public ConfigPropertyDao getConfigPropertyDao() {
        return configPropertyDao;
    }

    public void setConfigPropertyDao(ConfigPropertyDao configPropertyDao) {
        this.configPropertyDao = configPropertyDao;
    }
}
