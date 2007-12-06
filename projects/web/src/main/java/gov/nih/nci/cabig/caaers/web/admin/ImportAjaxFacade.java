package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.DiseaseCategoryDao;
import gov.nih.nci.cabig.caaers.dao.DiseaseTermDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.InvestigationalNewDrugDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.RoutineAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.SiteInvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.DiseaseCategory;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerm;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.RoutineAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.Status;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyAgentINDAssociation;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.rules.business.service.AdverseEventEvaluationService;
import gov.nih.nci.cabig.caaers.rules.business.service.AdverseEventEvaluationServiceImpl;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.tools.ObjectTools;
import gov.nih.nci.cabig.caaers.web.ae.CreateAdverseEventAjaxFacade.IndexChange;
import gov.nih.nci.cabig.ctms.lang.NowFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.mvc.AbstractFormController;

/**
 * @author Rhett Sutphin
 * @author Krikor Krumlian
 */
public class ImportAjaxFacade {

	public static final String AJAX_REQUEST_PARAMETER = "_isAjax";

	public static final String AJAX_INDEX_PARAMETER = "index";

	public static final String AJAX_SUBVIEW_PARAMETER = "_subview";

	public static final String IMPORT_FORM_NAME = ImportController.class.getName() + ".FORM.command";

	private static final Log log = LogFactory.getLog(ImportAjaxFacade.class);

	private NowFactory nowFactory;
	private RoutineAdverseEventReportDao routineAdverseEventReportDao;
	private ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;
	private ParticipantDao participantDao;
	private StudyDao studyDao;
	private AdverseEventEvaluationService adverseEventEvaluationService = new AdverseEventEvaluationServiceImpl();

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
	public String addStudyPersonnel(final int index) {
		HttpServletRequest request = getHttpServletRequest();
		getImportCommand(request);
		setRequestAttributes(request, index, -1, "studySitePersonnelSection");
		String url = getCurrentPageContextRelative(WebContextFactory.get());
		return getOutputFromJsp(url);
	}
	*/
	
	public String saveObjectBlock(final int loopNumber, String type){
		if ("participant".equals(type)){
			return saveParticipantBlock(loopNumber);
		}
		if ("study".equals(type)){
			return saveStudyBlock(loopNumber);
		}
		if ("routineAe".equals(type)){
			return saveRoutineAeBlock(loopNumber);
		}
		return "NA";
	}
	
	public String saveStudyBlock(final int loopNumber) {
		HttpServletRequest request = getHttpServletRequest();
		
		ImportCommand importCommand = getImportCommand(request);
		//int startIndex = importCommand.getRoutineStart();
		int startIndex = 5 * loopNumber;
		int endIndex = importCommand.getImportableStudies().size() ;
		int loopBy = startIndex + 5 >= endIndex ? endIndex :startIndex + 5;
		System.out.println("startIndex : " + startIndex + " endIndex " + endIndex + " loopBy " + loopBy);
		saveStudies(importCommand.getImportableStudies(),startIndex, loopBy );
		//importCommand.setRoutineStart(loopBy);
		return String.valueOf(loopBy - startIndex);
	}
	
	public String saveParticipantBlock(final int loopNumber) {
		HttpServletRequest request = getHttpServletRequest();
		
		ImportCommand importCommand = getImportCommand(request);
		//int startIndex = importCommand.getRoutineStart();
		int startIndex = 5 * loopNumber;
		int endIndex = importCommand.getImportableParticipants().size() ;
		int loopBy = startIndex + 5 >= endIndex ? endIndex :startIndex + 5;
		System.out.println("startIndex : " + startIndex + " endIndex " + endIndex + " loopBy " + loopBy);
		saveParticipants(importCommand.getImportableParticipants(),startIndex, loopBy );
		//importCommand.setRoutineStart(loopBy);
		return String.valueOf(loopBy - startIndex);
	}
	
	private void saveParticipants(List<DomainObjectImportOutcome<Participant>> importableParticipants,int startIndex, int endIndex){
		if (importableParticipants.size() > 0){
			
			for (DomainObjectImportOutcome<Participant> importOutcome : importableParticipants.subList(startIndex, endIndex)) {
				participantDao.save(importOutcome.getImportedDomainObject());
			}
		}
	}
	
	private void saveStudies(List<DomainObjectImportOutcome<Study>> importableParticipants,int startIndex, int endIndex){
		if (importableParticipants.size() > 0){
			
			for (DomainObjectImportOutcome<Study> importOutcome : importableParticipants.subList(startIndex, endIndex)) {
				studyDao.save(importOutcome.getImportedDomainObject());
			}
		}
	}
	
	
	//
	public String saveRoutineAeBlock(final int loopNumber) {
		HttpServletRequest request = getHttpServletRequest();
		
		ImportCommand importCommand = getImportCommand(request);
		//int startIndex = importCommand.getRoutineStart();
		int startIndex = 5 * loopNumber;
		int endIndex = importCommand.getImportableRoutineAdverseEventReports().size() ;
		int loopBy = startIndex + 5 >= endIndex ? endIndex :startIndex + 5;
		System.out.println("startIndex : " + startIndex + " endIndex " + endIndex + " loopBy " + loopBy);
		saveRoutineAEs(importCommand.getImportableRoutineAdverseEventReports(),startIndex, loopBy );
		//importCommand.setRoutineStart(loopBy);
		
		
		return String.valueOf(loopBy - startIndex);
	}
	
	private void saveRoutineAEs(List<DomainObjectImportOutcome<RoutineAdverseEventReport>> importableRoutineAdverseEventReports,int startIndex, int endIndex){
	if (importableRoutineAdverseEventReports.size() > 0){
		
		for (DomainObjectImportOutcome<RoutineAdverseEventReport> importOutcome : importableRoutineAdverseEventReports.subList(startIndex, endIndex)) {
			if (importOutcome.getImportedDomainObject().getStatus() == Status.CURRENT) {
				// expedited reporting ? , create ExpeditedReport save routineReport,expeditedReport 
				ExpeditedAdverseEventReport expeditedReport = getExpedited(importOutcome.getImportedDomainObject());
				routineAdverseEventReportDao.save(importOutcome.getImportedDomainObject());
				if (expeditedReport != null) {
					expeditedAdverseEventReportDao.save(expeditedReport);
				}
			}else{
				// Status = Legacy => save routineAe 
				routineAdverseEventReportDao.save(importOutcome.getImportedDomainObject());
			}
		}
	}
	}
	
	public ExpeditedAdverseEventReport getExpedited(RoutineAdverseEventReport raer ){
		log.debug("Checking for expedited AEs");
		Study study = raer.getStudy();
		
		//Create the expedited Report
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
		}
    	catch(Exception e){
    		throw new CaaersSystemException("There was an error evaluating Routine AEs",e);
    	}
    }

	


	private void setRequestAttributes(final HttpServletRequest request, final int index, final int listEditorIndex,
			final String subview) {
		request.setAttribute(AJAX_INDEX_PARAMETER, index);
		request.setAttribute(AJAX_SUBVIEW_PARAMETER, subview);
		request.setAttribute(AJAX_REQUEST_PARAMETER, "AJAX");
		request.setAttribute("listEditorIndex", listEditorIndex);
	}

	private String getOutputFromJsp(final String jspResource) {
		String html = "Error in rendering...";
		try {
			html = WebContextFactory.get().forwardToString(jspResource);
		}
		catch (ServletException e) {
			throw new CaaersSystemException(e.getMessage(), e);
		}
		catch (IOException e) {
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
		}
		else if (!page.startsWith(contextPath)) {
			log.debug(page + " does not start with context path " + contextPath);
			return page;
		}
		else {
			return page.substring(contextPath.length());
		}
	}

	private HttpServletRequest getHttpServletRequest() {
		return WebContextFactory.get().getHttpServletRequest();
	}

	// //// CONFIGURATION
	

	public ExpeditedAdverseEventReportDao getExpeditedAdverseEventReportDao() {
		return expeditedAdverseEventReportDao;
	}

	public void setExpeditedAdverseEventReportDao(
			ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao) {
		this.expeditedAdverseEventReportDao = expeditedAdverseEventReportDao;
	}

	public RoutineAdverseEventReportDao getRoutineAdverseEventReportDao() {
		return routineAdverseEventReportDao;
	}

	public void setRoutineAdverseEventReportDao(
			RoutineAdverseEventReportDao routineAdverseEventReportDao) {
		this.routineAdverseEventReportDao = routineAdverseEventReportDao;
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
	
	

	
}
