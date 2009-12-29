package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.RoutineAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.repository.InvestigatorRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ResearchStaffRepository;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.rules.business.service.AdverseEventEvaluationService;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.web.user.ResetPasswordController;
import gov.nih.nci.cabig.ctms.lang.NowFactory;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.web.servlet.mvc.AbstractFormController;

/**
 * @author Rhett Sutphin
 * @author Krikor Krumlian
 */
public class ImportAjaxFacade {

    public static final String AJAX_REQUEST_PARAMETER = "_isAjax";

    public static final String AJAX_INDEX_PARAMETER = "index";

    public static final String AJAX_SUBVIEW_PARAMETER = "_subview";

    public static final String IMPORT_FORM_NAME = ImportController.class.getName()
                    + ".FORM.command";

    private static final Log log = LogFactory.getLog(ImportAjaxFacade.class);

    private NowFactory nowFactory;

    private ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;

    private ParticipantDao participantDao;

    private StudyDao studyDao;
    
    private StudyRepository studyRepository;
    
    protected ResearchStaffRepository researchStaffRepository;
    
    protected InvestigatorRepository investigatorRepository;


    private AdverseEventEvaluationService adverseEventEvaluationService;// = new AdverseEventEvaluationServiceImpl();

    private ImportCommand getImportCommandFromSession(final HttpServletRequest request) {
        ImportCommand importCommand = (ImportCommand) request.getSession().getAttribute(IMPORT_FORM_NAME);
        return importCommand;
    }

    private ImportCommand getImportCommand(final HttpServletRequest request) {
        ImportCommand importCommand = getImportCommandFromSession(request);
        request.setAttribute(AbstractFormController.DEFAULT_COMMAND_NAME, importCommand);
        return importCommand;
    }

    /*
     * public String addStudyPersonnel(final int index) { HttpServletRequest request =
     * getHttpServletRequest(); getImportCommand(request); setRequestAttributes(request, index, -1,
     * "studySitePersonnelSection"); String url =
     * getCurrentPageContextRelative(WebContextFactory.get()); return getOutputFromJsp(url); }
     */

    public String saveObjectBlock(final int loopNumber, String type) {
    	
    	try{
    		if ("participant".equals(type)) {
                return saveParticipants();
            }
            if ("study".equals(type)) {
                return saveStudies();
            }
//            if ("routineAe".equals(type)) {
//                return saveRoutineAeBlock(loopNumber);
//            }
            if ("investigator".equals(type)) {
                return saveInvestigators();
            }
            if ("researchstaff".equals(type)) {
                return saveResearchStaff();
            }
    	}catch(Exception e){
    		log.error("Exception while Importing", e);
    		return "ERR";
    	}
        return "NA";
    }
    
    
    private String saveInvestigators(){
    	HttpServletRequest request = getHttpServletRequest();
        ImportCommand importCommand = getImportCommand(request);
        List<DomainObjectImportOutcome<Investigator>> importableInvestigators = importCommand.getImportableInvestigators();
        for (DomainObjectImportOutcome<Investigator> importOutcome : importableInvestigators) {
    		investigatorRepository.save(importOutcome.getImportedDomainObject(), ResetPasswordController.getURL(request
                    .getScheme(), request.getServerName(), request.getServerPort(), request
                    .getContextPath()));
        }
    	return "DONE";
    }
    
    private String saveResearchStaff(){
      	HttpServletRequest request = getHttpServletRequest();
        ImportCommand importCommand = getImportCommand(request);
        List<DomainObjectImportOutcome<ResearchStaff>> importableResearchStaff = importCommand.getImportableResearchStaff();
        for (DomainObjectImportOutcome<ResearchStaff> importOutcome : importableResearchStaff) {
        	//researchStaffDao.save(importOutcome.getImportedDomainObject());
        	researchStaffRepository.save(importOutcome.getImportedDomainObject(), ResetPasswordController.getURL(request
                    .getScheme(), request.getServerName(), request.getServerPort(), request
                    .getContextPath()));
        }
        
        
        
        
    	return "DONE";
    }
    
    private String saveStudies() {
        HttpServletRequest request = getHttpServletRequest();
        ImportCommand importCommand = getImportCommand(request);
        for (DomainObjectImportOutcome<Study> importOutcome : importCommand.getImportableStudies()) {
        	studyRepository.save(importOutcome.getImportedDomainObject());
        }
        return "DONE";
    }

    private String saveParticipants() {
		HttpServletRequest request = getHttpServletRequest();
        ImportCommand importCommand = getImportCommand(request);
        for (DomainObjectImportOutcome<Participant> importOutcome : importCommand.getImportableParticipants()) {
        	participantDao.save(importOutcome.getImportedDomainObject());
        }
       return "DONE";
    }



    public ExpeditedAdverseEventReport getExpedited(RoutineAdverseEventReport raer) {
        log.debug("Checking for expedited AEs");
        Study study = raer.getStudy();

        
        studyDao.reassociate(study);
        
        // Create the expedited Report
        ExpeditedAdverseEventReport aeReport = new ExpeditedAdverseEventReport();
        aeReport.setAssignment(raer.getAssignment());
        aeReport.setCreatedAt(nowFactory.getNowTimestamp());

        try {
            for (AdverseEvent ae : raer.getAdverseEvents()) {

                String message = adverseEventEvaluationService.assesAdverseEvent(ae, study);
                if (message.equals("SERIOUS_ADVERSE_EVENT")) {
                    aeReport.addAdverseEvent(ae);
                }
            }
            return aeReport.getAdverseEvents().isEmpty() ? null : aeReport;
        } catch (Exception e) {
            throw new CaaersSystemException("There was an error evaluating Routine AEs", e);
        }
    }

    private void setRequestAttributes(final HttpServletRequest request, final int index,
                    final int listEditorIndex, final String subview) {
        request.setAttribute(AJAX_INDEX_PARAMETER, index);
        request.setAttribute(AJAX_SUBVIEW_PARAMETER, subview);
        request.setAttribute(AJAX_REQUEST_PARAMETER, "AJAX");
        request.setAttribute("listEditorIndex", listEditorIndex);
    }

    private String getOutputFromJsp(final String jspResource) {
        String html = "Error in rendering...";
        try {
            html = WebContextFactory.get().forwardToString(jspResource);
        } catch (ServletException e) {
            throw new CaaersSystemException(e.getMessage(), e);
        } catch (IOException e) {
            throw new CaaersSystemException(e.getMessage(), e);
        }
        return html;
    }

    private String getCurrentPageContextRelative(final WebContext webContext) {
        String contextPath = webContext.getHttpServletRequest().getContextPath();
        String page = webContext.getCurrentPage();
        if (contextPath == null) {
            log.debug("context path not set");
            return page;
        } else if (!page.startsWith(contextPath)) {
            log.debug(page + " does not start with context path " + contextPath);
            return page;
        } else {
            return page.substring(contextPath.length());
        }
    }

    private HttpServletRequest getHttpServletRequest() {
        return WebContextFactory.get().getHttpServletRequest();
    }
    
    
    public String testFullParticipantSave(){
    	HttpServletRequest request = getHttpServletRequest();
        ImportCommand importCommand = getImportCommand(request);
        log.debug("Number of participants :: " + importCommand.getImportableParticipants().size());
        for (DomainObjectImportOutcome<Participant> importOutcome : importCommand.getImportableParticipants()) {
        		participantDao.save(importOutcome.getImportedDomainObject());
        }
        return "DONE";
    }
    
    // //// CONFIGURATION

    public ExpeditedAdverseEventReportDao getExpeditedAdverseEventReportDao() {
        return expeditedAdverseEventReportDao;
    }

    public void setExpeditedAdverseEventReportDao(
                    ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao) {
        this.expeditedAdverseEventReportDao = expeditedAdverseEventReportDao;
    }


    public NowFactory getNowFactory() {
        return nowFactory;
    }

    public void setNowFactory(NowFactory nowFactory) {
        this.nowFactory = nowFactory;
    }

    public ParticipantDao getParticipantDao() {
        return participantDao;
    }

    public void setParticipantDao(ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }

    public StudyDao getStudyDao() {
        return studyDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }
    
    public void setAdverseEventEvaluationService(AdverseEventEvaluationService adverseEventEvaluationService) {
		this.adverseEventEvaluationService = adverseEventEvaluationService;
	}

	public void setResearchStaffRepository(
			ResearchStaffRepository researchStaffRepository) {
		this.researchStaffRepository = researchStaffRepository;
	}

	public void setInvestigatorRepository(
			InvestigatorRepository investigatorRepository) {
		this.investigatorRepository = investigatorRepository;
	}

	public void setStudyRepository(StudyRepository studyRepository) {
		this.studyRepository = studyRepository;
	}
}
