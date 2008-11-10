package gov.nih.nci.cabig.caaers.web.participant;

//java imports

import gov.nih.nci.cabig.caaers.dao.AbstractStudyDiseaseDao;
import gov.nih.nci.cabig.caaers.dao.AnatomicSiteDao;
import gov.nih.nci.cabig.caaers.dao.ChemoAgentDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.PreExistingConditionDao;
import gov.nih.nci.cabig.caaers.dao.PriorTherapyDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ParticipantRepository;
import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.AutomaticSaveAjaxableFormController;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.validation.validator.WebControllerValidator;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;

import java.util.Date;
import java.util.List;
import java.util.Iterator;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

public class CreateParticipantController extends AutomaticSaveAjaxableFormController<ParticipantInputCommand, Participant, ParticipantDao> {

    private static Log log = LogFactory.getLog(CreateParticipantController.class);

    private StudyDao studyDao;
    private OrganizationDao organizationDao;
    private StudySiteDao studySiteDao;
    private ParticipantDao participantDao;

    private ConfigProperty configurationProperty;
    protected WebControllerValidator webControllerValidator;

    OrganizationRepository organizationRepository;
    ParticipantRepository participantRepository;

    protected PriorTherapyDao priorTherapyDao;
    protected AnatomicSiteDao anatomicSiteDao;
    protected PreExistingConditionDao preExistingConditionDao;
    protected AbstractStudyDiseaseDao abstractStudyDiseaseDao;
    protected ChemoAgentDao chemoAgentDao;

    public CreateParticipantController() {
    }

    @Override
    public FlowFactory<ParticipantInputCommand> getFlowFactory() {
        return new FlowFactory<ParticipantInputCommand>() {
            public Flow<ParticipantInputCommand> createFlow(ParticipantInputCommand cmd) {
            	
            	/**
            	 * Third level tabs are secured now , Any changes in this flow needs to reflect in 
            	 * applicationContext-web-security.xml <util:map id="tabObjectPrivilegeMap"> 
            	 */
            	
                Flow<ParticipantInputCommand> flow = new Flow<ParticipantInputCommand>("Enter Subject");
                flow.addTab(new CreateParticipantTab());
                flow.addTab(new SelectStudyForParticipantTab());
                flow.addTab(new SubjectMedHistoryTab());
                flow.addTab(new CreateParticipantReviewParticipantTab());
                return flow;
            }
        };
    }

    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        ParticipantInputCommand participantCommand = (ParticipantInputCommand) command;

        Participant participant = participantCommand.getParticipant();
        participantCommand.getAssignment().setParticipant(participant);
        participant.addAssignment(participantCommand.getAssignment());

        participantDao.save(participant);
        response.sendRedirect("view?participantId=" + participant.getId() + "&type=create");

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
        
        binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(false));
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        
        ControllerTools.registerDomainObjectEditor(binder, organizationDao);
        ControllerTools.registerDomainObjectEditor(binder, priorTherapyDao);
        ControllerTools.registerDomainObjectEditor(binder, anatomicSiteDao);
        ControllerTools.registerDomainObjectEditor(binder, preExistingConditionDao);
        ControllerTools.registerDomainObjectEditor(binder, studyDao);
        ControllerTools.registerDomainObjectEditor(binder, "assignment.diseaseHistory.abstractStudyDisease", abstractStudyDiseaseDao);
        ControllerTools.registerDomainObjectEditor(binder, chemoAgentDao);
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
    @Required
    public void setAnatomicSiteDao(AnatomicSiteDao anatomicSiteDao) {
		this.anatomicSiteDao = anatomicSiteDao;
	}
    @Required
    public void setPreExistingConditionDao(
			PreExistingConditionDao preExistingConditionDao) {
		this.preExistingConditionDao = preExistingConditionDao;
	}
    @Required
    public void setAbstractStudyDiseaseDao(
			AbstractStudyDiseaseDao abstractStudyDiseaseDao) {
		this.abstractStudyDiseaseDao = abstractStudyDiseaseDao;
	}
    @Required
    public void setChemoAgentDao(ChemoAgentDao chemoAgentDao) {
		this.chemoAgentDao = chemoAgentDao;
	}

    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors, int page) throws Exception {
        super.onBindAndValidate(request, command, errors, page);
        ParticipantInputCommand cmd = (ParticipantInputCommand) command;

        List<Identifier> sitePrimaryIdentifiers = participantDao.getSitePrimaryIdentifiers(cmd.getOrganization().getId().intValue());

        for (int i=0; i<sitePrimaryIdentifiers.size(); i++) {
            Identifier sID = sitePrimaryIdentifiers.get(i);
            for (int j=0; j<cmd.getParticipant().getIdentifiers().size(); j++) {
                Identifier pID = cmd.getParticipant().getIdentifiers().get(j);
                
                if (sID.getValue().equals(pID.getValue())) {
                    errors.reject("ERR_DUPLICATE_SITE_PRIMARY_IDENTIFIER", new Object[] {cmd.getOrganization().getName(), pID.getValue()}, "Duplicate identifiers for the same site.");
                }
            }
        }

        // if the target tab is not the next to the crrent one
        if (getTargetPage(request, command, errors, page) - page > 1) {
            // if the assisgnment object needed by SubjectMedHistoryTab is not in the command 
            if (cmd.assignment == null || cmd.assignment.getId() == null)
                if (getTab((ParticipantInputCommand) command, getTargetPage(request, command, errors, page)).getClass() == SubjectMedHistoryTab.class)
                    errors.reject("ERR_SELECT_STUDY_FROM_DETAILS", "Please select a study from the \"Details\" tab");
        }

    }
}
