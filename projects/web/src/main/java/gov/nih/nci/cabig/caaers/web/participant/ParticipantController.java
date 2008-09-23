package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.dao.AbstractStudyDiseaseDao;
import gov.nih.nci.cabig.caaers.dao.AnatomicSiteDao;
import gov.nih.nci.cabig.caaers.dao.ChemoAgentDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.PreExistingConditionDao;
import gov.nih.nci.cabig.caaers.dao.PriorTherapyDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.DateValue;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.repository.ParticipantRepository;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.tools.editors.DateValueEditor;
import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.AutomaticSaveAjaxableFormController;
import gov.nih.nci.cabig.caaers.validation.validator.WebControllerValidator;
import gov.nih.nci.cabig.ctms.web.tabs.AutomaticSaveFlowFormController;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

/**
 * Base Controller class to handle the basic work flow in the Creation / Updation of a
 * NewParticipantCommand Design This uses AbstractTabbedFlowFormController to implement tabbed
 * workflow
 *
 * @author Saurabh
 * @author Ion
 * @author Biju Joseph
 */
public abstract class ParticipantController<C extends ParticipantInputCommand> extends AutomaticSaveAjaxableFormController<C, Participant, ParticipantDao> {

    private static Log log = LogFactory.getLog(ParticipantController.class);
    protected WebControllerValidator webControllerValidator;
    private OrganizationDao organizationDao;
    protected ParticipantRepository participantRepository;
    protected ParticipantDao participantDao;
    protected StudyParticipantAssignmentDao assignmentDao;
    protected PriorTherapyDao priorTherapyDao;
    protected AnatomicSiteDao anatomicSiteDao;
    protected PreExistingConditionDao preExistingConditionDao;
    protected AbstractStudyDiseaseDao abstractStudyDiseaseDao;
    protected ChemoAgentDao chemoAgentDao;
    protected StudyDao studyDao;

    public ParticipantController() {
        setCommandClass(ParticipantInputCommand.class);
        Flow<C> flow = new Flow<C>("Create Subject");

        layoutTabs(flow);
        setFlow(flow);
        setAllowDirtyBack(true);
        setAllowDirtyForward(false);

    }

    @Required
    public void setParticipantRepository(final ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    protected void initBinder(final HttpServletRequest request, final ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(false));
        ControllerTools.registerDomainObjectEditor(binder, organizationDao);
        ControllerTools.registerDomainObjectEditor(binder, assignmentDao);
        ControllerTools.registerDomainObjectEditor(binder, priorTherapyDao);
        ControllerTools.registerDomainObjectEditor(binder, anatomicSiteDao);
        ControllerTools.registerDomainObjectEditor(binder, preExistingConditionDao);
        ControllerTools.registerDomainObjectEditor(binder, studyDao);
        ControllerTools.registerDomainObjectEditor(binder, "assignment.diseaseHistory.abstractStudyDisease", abstractStudyDiseaseDao);
        ControllerTools.registerDomainObjectEditor(binder, chemoAgentDao);

    }

    @Override
    protected ModelAndView processFinish(final HttpServletRequest request, final HttpServletResponse response, final Object command, final BindException errors) throws Exception {
        log.debug("Entering Process Finish ...");
        ParticipantInputCommand participantCommand = (ParticipantInputCommand) command;
        Participant participant = participantCommand.getParticipant();
        participantDao.save(participant);

/*
        ModelAndView modelAndView = new ModelAndView("par/par_confirm");
        modelAndView.addObject("participant", participant);
        modelAndView.addAllObjects(errors.getModel());
*/

        response.sendRedirect("view?participantId=" + participant.getId() + "&type=confirm");
        return null;
        //		
        // return modelAndView;
        // return modelAndView;
    }

    @Override
    protected boolean suppressValidation(final HttpServletRequest request, final Object command) {
        // supress for ajax and delete requests
        Object isAjax = findInRequest(request, "_isAjax");
        if (isAjax != null) {
            return true;
        }
        String action = (String) findInRequest(request, "_action");
        if (org.apache.commons.lang.StringUtils.isNotEmpty(action)) {
            return true;
        }
        return super.suppressValidation(request, command);
    }

    @Override
    protected String getViewName(final HttpServletRequest request, final Object command, final int page) {
        Object subviewName = findInRequest(request, "_subview");
        if (subviewName != null) {
            return "par/ajax/" + subviewName;
        } else {
            return super.getViewName(request, command, page);
        }
    }

    /**
     * Returns the value associated with the <code>attributeName</code>, if present in
     * HttpRequest parameter, if not available, will check in HttpRequest attribute map.
     */
    protected Object findInRequest(final HttpServletRequest request, final String attributName) {

        Object attr = request.getParameter(attributName);
        if (attr == null) {
            attr = request.getAttribute(attributName);
        }
        return attr;
    }


    // /LOGIC
    @Override
    protected Participant getPrimaryDomainObject(final C command) {
        return command.getParticipant();
    }

    /**
     * Template method to let the subclass decide the order of tab
     */
    protected abstract void layoutTabs(Flow<C> flow);

    @Override
    @SuppressWarnings("unchecked")
    protected Map referenceData(final HttpServletRequest request, final Object command, final Errors errors, final int page) throws Exception {
        Map<String, Object> refdata = super.referenceData(request, command, errors, page);
        return refdata;
    }

    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors, int page) throws Exception {
        super.onBindAndValidate(request, command, errors, page);
        if (!suppressValidation(request, command)) {

            webControllerValidator.validate(request, command, errors);
        }
    }

    /**
     * Override this in sub controller if summary is needed
     *
     * @return
     */
    protected boolean isSummaryEnabled() {
        return false;
    }

    public void setPriorTherapyDao(PriorTherapyDao priorTherapyDao) {
        this.priorTherapyDao = priorTherapyDao;
    }

    public void setAnatomicSiteDao(AnatomicSiteDao anatomicSiteDao) {
        this.anatomicSiteDao = anatomicSiteDao;
    }

    public void setPreExistingConditionDao(
            PreExistingConditionDao preExistingConditionDao) {
        this.preExistingConditionDao = preExistingConditionDao;
    }

    public void setAbstractStudyDiseaseDao(
            AbstractStudyDiseaseDao abstractStudyDiseaseDao) {
        this.abstractStudyDiseaseDao = abstractStudyDiseaseDao;
    }

    public void setChemoAgentDao(ChemoAgentDao chemoAgentDao) {
        this.chemoAgentDao = chemoAgentDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    @Required
    public void setOrganizationDao(final OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    @Override
    protected ParticipantDao getDao() {
        return participantDao;
    }

    public void setParticipantDao(final ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }

    @Required
    public void setWebControllerValidator(WebControllerValidator webControllerValidator) {
        this.webControllerValidator = webControllerValidator;
    }

    @Required
    public void setAssignmentDao(StudyParticipantAssignmentDao assignmentDao) {
        this.assignmentDao = assignmentDao;
    }


}
