package gov.nih.nci.cabig.caaers.web.participant;

//java imports

import gov.nih.nci.cabig.caaers.dao.PriorTherapyDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.query.StudyHavingStudySiteQuery;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.validation.validator.WebControllerValidator;
import gov.nih.nci.cabig.caaers.web.ae.CaptureAdverseEventInputCommand;
import gov.nih.nci.cabig.caaers.web.ae.BeginTab;
import gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab;
import gov.nih.nci.cabig.caaers.web.ae.AdverseEventConfirmTab;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.AutomaticSaveAjaxableFormController;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.AbstractTabbedFlowFormController;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;
import gov.nih.nci.cabig.ctms.domain.MutableDomainObject;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.ServletRequestDataBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class CreateParticipantController2 extends AutomaticSaveAjaxableFormController<ParticipantInputCommand, Participant, ParticipantDao> {

    private static Log log = LogFactory.getLog(CreateParticipantController.class);

    private StudyDao studyDao;
    private OrganizationDao organizationDao;
    private StudySiteDao studySiteDao;
    private ParticipantDao participantDao;

    private ConfigProperty configurationProperty;
    protected WebControllerValidator webControllerValidator;

    OrganizationRepository organizationRepository;

    protected PriorTherapyDao priorTherapyDao;

    public CreateParticipantController2() {
    }

    @Override
    public FlowFactory<ParticipantInputCommand> getFlowFactory() {
        return new FlowFactory<ParticipantInputCommand>() {
            public Flow<ParticipantInputCommand> createFlow(ParticipantInputCommand cmd) {
                Flow<ParticipantInputCommand> flow = new Flow<ParticipantInputCommand>("Create Participant 2");
                flow.addTab(new ParticipantTab());
                flow.addTab(new SelectStudyForParticipantTab());
                flow.addTab(new SubjectMedHistoryTab());
                flow.addTab(new ReviewParticipantTab());
                return flow;
            }
        };
    }

    protected ModelAndView processFinish(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, BindException e) throws Exception {
        return null;  
    }

    @Override
    protected Object formBackingObject(final HttpServletRequest request) throws Exception {
    	
        ParticipantInputCommand participantInputCommand = new ParticipantInputCommand();
        participantInputCommand.init(configurationProperty.getMap().get("participantIdentifiersType").get(0).getCode()); //initialise the command
        
        return participantInputCommand;
    }

    protected void initBinder(HttpServletRequest httpServletRequest, ServletRequestDataBinder binder) throws Exception {
        super.initBinder(httpServletRequest, binder);
        ControllerTools.registerDomainObjectEditor(binder, organizationDao);
        ControllerTools.registerDomainObjectEditor(binder, priorTherapyDao);
        ControllerTools.registerDomainObjectEditor(binder, studyDao);
        
        binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(false));
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));

    }

    protected Object findInRequest(final HttpServletRequest request, final String attributName) {
        Object attr = request.getParameter(attributName);
        if (attr == null) {
            attr = request.getAttribute(attributName);
        }
        return attr;
    }

    @Override
    protected boolean suppressValidation(HttpServletRequest request, Object cmd) {
    	
    	ParticipantInputCommand command = (ParticipantInputCommand) cmd;
    	
        // supress validation when target page is less than current page.
        int curPage = getCurrentPage(request);
        int targetPage = getTargetPage(request, curPage);
        if (targetPage < curPage) return true;
        
        
        // supress for ajax and delete requests
        if(isAjaxRequest(request) && !StringUtils.equals("save",command.getTask())) return true;
        
        
        return super.suppressValidation(request, command);
    }

    @Required
    public void setStudySiteDao(final StudySiteDao studySiteDao) {
        this.studySiteDao = studySiteDao;
    }

    @Required
    public void setStudyDao(final StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public ConfigProperty getConfigurationProperty() {
        return configurationProperty;
    }

    @Required
    public void setConfigurationProperty(ConfigProperty configurationProperty) {
        this.configurationProperty = configurationProperty;
    }

    public WebControllerValidator getWebControllerValidator() {
        return webControllerValidator;
    }

    public void setWebControllerValidator(WebControllerValidator webControllerValidator) {
        this.webControllerValidator = webControllerValidator;
    }

    public OrganizationDao getOrganizationDao() {
        return organizationDao;
    }

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public OrganizationRepository getOrganizationRepository() {
        return organizationRepository;
    }

    public void setOrganizationRepository(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public ParticipantDao getParticipantDao() {
        return participantDao;
    }

    public void setParticipantDao(ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }

    protected ParticipantDao getDao() {
        return participantDao;
    }

    protected Participant getPrimaryDomainObject(ParticipantInputCommand command) {
        return command.getParticipant();
    }
    
    
    @Required
    public void setPriorTherapyDao(PriorTherapyDao priorTherapyDao) {
		this.priorTherapyDao = priorTherapyDao;
	}
}