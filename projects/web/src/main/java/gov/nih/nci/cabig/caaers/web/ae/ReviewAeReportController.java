package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.api.AdeersReportGenerator;
import gov.nih.nci.cabig.caaers.api.AdverseEventReportSerializer;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.SolicitedAdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.utils.IndexFixedList;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.MultipleFieldGroupFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.mvc.SimpleFormController;



public class ReviewAeReportController extends SimpleFormController{

	private static final String MAIN_FIELD_GROUP = "main";
	private ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;
	private StudyParticipantAssignmentDao assignmentDao;
	private Configuration configuration;
	
	public ReviewAeReportController(){
		setCommandClass(ReviewAeReportCommand.class);
		setSessionForm(true);
		setFormView("ae/reviewAeReportDetails");
		setSuccessView("ae/reviewAeReportDetails");
	}
	
	@Override
	public boolean isFormSubmission(HttpServletRequest request){
		return false;
	}
	
	@Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
		ReviewAeReportCommand command = new ReviewAeReportCommand(expeditedAdverseEventReportDao);
		String aeReportId = request.getParameter("aeReport");
		ExpeditedAdverseEventReport aeReport = expeditedAdverseEventReportDao.getById(Integer.parseInt(aeReportId));
		command.setAeReport(aeReport);
		command.setWorkflowEnabled(configuration.get(Configuration.ENABLE_WORKFLOW));
		return command;
	}
		
	@Required
	public Configuration getConfiguration() {
		return configuration;
	}
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	
	public void setExpeditedAdverseEventReportDao(ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao){
		this.expeditedAdverseEventReportDao = expeditedAdverseEventReportDao;
	}
	
	public ExpeditedAdverseEventReportDao getExpeditedAdverseEventReportDao(){
		return expeditedAdverseEventReportDao;
	}
	
	public void setAssignmentDao(StudyParticipantAssignmentDao assignmentDao){
		this.assignmentDao = assignmentDao;
	}
	
	public StudyParticipantAssignmentDao getAssignmentDao(){
		return assignmentDao;
	}
}