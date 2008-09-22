package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.dao.*;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.web.ListValues;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.web.ae.AbstractAdverseEventInputController;
import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.AutomaticSaveAjaxableFormController;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Date;
import java.util.ArrayList;

import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

/**
 * @author Krikor Krumlian
 */

public class AssignParticipantController extends AutomaticSaveAjaxableFormController<AssignParticipantStudyCommand, Participant, ParticipantDao> {

    private static Log log = LogFactory.getLog(AssignParticipantController.class);
    private ParticipantDao participantDao;
    private StudySiteDao studySiteDao;
    protected ListValues listValues;

    protected PriorTherapyDao priorTherapyDao;
    protected AnatomicSiteDao anatomicSiteDao;
    protected PreExistingConditionDao preExistingConditionDao;
    protected AbstractStudyDiseaseDao abstractStudyDiseaseDao;
    protected ChemoAgentDao chemoAgentDao;

    @Override
    @SuppressWarnings(value = {"unchecked"})
    protected Map referenceData(HttpServletRequest request, Object command, Errors errors, int page) throws Exception {
        Map referenceData = super.referenceData(request, command, errors, page);
        referenceData.put("participantSearchType", listValues.getParticipantSearchType());
        referenceData.put("studySearchType", listValues.getStudySearchType());
        referenceData.put("assignType", "participant");
        return referenceData;
    }

    public AssignParticipantController() {
        setCommandClass(AssignParticipantStudyCommand.class);
        Flow<AssignParticipantStudyCommand> flow = new Flow<AssignParticipantStudyCommand>("Assign Subject to Study");
        flow.addTab(new AssignParticipantTab());
        flow.addTab(new AssignStudyTab());
        flow.addTab(new SubjectMedHistoryTab());
        flow.addTab(new ReviewAssignmentTab());
        setFlow(flow);
    }

    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        AssignParticipantStudyCommand command = new AssignParticipantStudyCommand();
        command.init();
        return command;
    }

    @Override
    protected String getFormSessionAttributeName() {
        // the entry point to this flow is from AssignController
        return AssignParticipantController.class.getName() + ".FORM." + getCommandName();
    }

    /**
     * Validation needs to be supressed when, - We go back in the flow. - We the page displayed is
     * the result of study/participant search.
     */
    @Override
    protected boolean suppressValidation(HttpServletRequest request, Object command) {
        int curPage = getCurrentPage(request);
        int targetPage = getTargetPage(request, curPage);

        if(isAjaxRequest(request) || targetPage < curPage) return true;
        return super.suppressValidation(request, command);
        // return (request.getParameter("studyType") != null) || (request.getParameter("participantType") != null) || (targetPage < curPage);
    }


    protected void initBinder(HttpServletRequest httpServletRequest, ServletRequestDataBinder binder) throws Exception {
        ControllerTools.registerDomainObjectEditor(binder, participantDao);
        ControllerTools.registerDomainObjectEditor(binder, studySiteDao);

        ControllerTools.registerDomainObjectEditor(binder, priorTherapyDao);
        ControllerTools.registerDomainObjectEditor(binder, anatomicSiteDao);
        ControllerTools.registerDomainObjectEditor(binder, preExistingConditionDao);
        ControllerTools.registerDomainObjectEditor(binder, "assignment.diseaseHistory.abstractStudyDisease", abstractStudyDiseaseDao);
        ControllerTools.registerDomainObjectEditor(binder, chemoAgentDao);

    }

    @Override
    protected String getViewName(final HttpServletRequest request, final Object command, final int page) {
        Object subviewName = findInRequest(request, AbstractAdverseEventInputController.AJAX_SUBVIEW_PARAMETER);
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

    @Override
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        log.debug("processFinish.");

        AssignParticipantStudyCommand assignParticipantStudyCommand = (AssignParticipantStudyCommand) command;
        Participant participant = assignParticipantStudyCommand.getParticipant();
        participantDao.reassociateUsingLock(participant);
        assignParticipantStudyCommand.getAssignment().setStudySite(assignParticipantStudyCommand.getStudySite());
        assignParticipantStudyCommand.getAssignment().setParticipant(participant);
        participant.addAssignment(assignParticipantStudyCommand.getAssignment());
        assignParticipantStudyCommand.getAssignment().setStudySubjectIdentifier(assignParticipantStudyCommand.getStudySubjectIdentifier());

/*
        assignParticipantStudyCommand.getAssignment().setDateOfEnrollment(new Date());
        assignParticipantStudyCommand.setStudy(assignParticipantStudyCommand.getStudySite().getStudy());
*/


        participantDao.save(participant);

        response.sendRedirect("view?participantId=" + participant.getId() + "&type=edit");

        return null;
    }

    public ParticipantDao getParticipantDao() {
        return participantDao;
    }

    public void setParticipantDao(ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }

    public ListValues getListValues() {
        return listValues;
    }

    public void setListValues(ListValues listValues) {
        this.listValues = listValues;
    }

    protected Participant getPrimaryDomainObject(AssignParticipantStudyCommand command) {
        return command.getParticipant();
    }

    protected ParticipantDao getDao() {
        return participantDao;
    }

    protected boolean shouldSave(HttpServletRequest request, AssignParticipantStudyCommand command, Tab<AssignParticipantStudyCommand> assignParticipantStudyCommandTab) {
        return false;
    }

    public StudySiteDao getStudySiteDao() {
        return studySiteDao;
    }

    public void setStudySiteDao(StudySiteDao studySiteDao) {
        this.studySiteDao = studySiteDao;
    }

    public PriorTherapyDao getPriorTherapyDao() {
        return priorTherapyDao;
    }

    public void setPriorTherapyDao(PriorTherapyDao priorTherapyDao) {
        this.priorTherapyDao = priorTherapyDao;
    }

    public AnatomicSiteDao getAnatomicSiteDao() {
        return anatomicSiteDao;
    }

    public void setAnatomicSiteDao(AnatomicSiteDao anatomicSiteDao) {
        this.anatomicSiteDao = anatomicSiteDao;
    }

    public PreExistingConditionDao getPreExistingConditionDao() {
        return preExistingConditionDao;
    }

    public void setPreExistingConditionDao(PreExistingConditionDao preExistingConditionDao) {
        this.preExistingConditionDao = preExistingConditionDao;
    }

    public AbstractStudyDiseaseDao getAbstractStudyDiseaseDao() {
        return abstractStudyDiseaseDao;
    }

    public void setAbstractStudyDiseaseDao(AbstractStudyDiseaseDao abstractStudyDiseaseDao) {
        this.abstractStudyDiseaseDao = abstractStudyDiseaseDao;
    }

    public ChemoAgentDao getChemoAgentDao() {
        return chemoAgentDao;
    }

    public void setChemoAgentDao(ChemoAgentDao chemoAgentDao) {
        this.chemoAgentDao = chemoAgentDao;
    }
}



