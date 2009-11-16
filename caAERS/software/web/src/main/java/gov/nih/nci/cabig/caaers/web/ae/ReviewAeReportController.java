package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ReportValidationService;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.service.ReportSubmittability;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.validation.ValidationError;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.acegisecurity.context.SecurityContext;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.mvc.SimpleFormController;



public class ReviewAeReportController extends SimpleFormController{

	private static final String MAIN_FIELD_GROUP = "main";
	private ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;
	private ReportDao reportDao;
	private StudyParticipantAssignmentDao assignmentDao;
	private Configuration configuration;
	private MessageSource messageSource;
	private CSMUserRepository csmUserRepository;
	private EvaluationService evaluationService;
	private ReportValidationService reportValidationService;
	
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
		ReviewAeReportCommand command = new ReviewAeReportCommand(expeditedAdverseEventReportDao, reportDao);
		String aeReportId = request.getParameter("aeReport");
		String reportId = request.getParameter("report");
		ExpeditedAdverseEventReport aeReport = expeditedAdverseEventReportDao.getById(Integer.parseInt(aeReportId));
		if(reportId != null && !reportId.equals("") && !reportId.equals("null"))
			command.setReportId(Integer.parseInt(reportId));
		else
			command.setReportId(null);
		command.setAeReport(aeReport);
		command.setWorkflowEnabled(configuration.get(Configuration.ENABLE_WORKFLOW));
		return command;
	}
	
	@SuppressWarnings("unchecked")
    @Override
    protected Map referenceData(final HttpServletRequest request, final Object cmd, final Errors errors) throws Exception {
		ReviewAeReportCommand command = (ReviewAeReportCommand) cmd;
		Map<String, Object> refdata = new HashMap<String, Object>();
        Map<Integer, ReportSubmittability> reportMessages = new HashMap<Integer, ReportSubmittability>();

        // evaluate business rules.
        ReportSubmittability reportSubmittability = new ReportSubmittability();
        for (ExpeditedReportSection section : ExpeditedReportSection.values()) {

            if (!section.isAssociatedToBusinessRules()) continue;

            ValidationErrors validationErrors = evaluationService.validateReportingBusinessRules( command.getAeReport(), section);
            for (ValidationError vError : validationErrors.getErrors()) {
                reportSubmittability.addValidityMessage(section, messageSource.getMessage(vError.getCode(), vError.getReplacementVariables(), vError.getMessage(), Locale.getDefault()));
            }
        }

        reportMessages.put(ExpeditedAdverseEventInputCommand.ZERO, reportSubmittability);

        // -- check the report submittability
        for (Report report : command.getAeReport().getReports()) {
        		reportMessages.put(report.getId(), reportValidationService.isSubmittable(report));
        }
        refdata.put("reportMessages", reportMessages);

        // This is to check if the logged in person is SAE-Coordinator.
        // Data coordinator cannot submit a report.
        SecurityContext context = (SecurityContext)request.getSession().getAttribute("ACEGI_SECURITY_CONTEXT");
		String userId = ((org.acegisecurity.userdetails.User)context.getAuthentication().getPrincipal()).getUsername();
		boolean isUserSAECoordinator = false;
		if(!csmUserRepository.isSuperUser(userId)){
			User user = csmUserRepository.getUserByName(userId);
			if(user.getUserGroupTypes().contains(UserGroupType.caaers_central_office_sae_cd)){
					isUserSAECoordinator = true;
			}
		}
        
        //boolean canSubmit = false;
        //if(reportMessages.get(command.ZERO).isSubmittable() && reportMessages.get(command.getReportId()).isSubmittable() && isUserSAECoordinator)
        //	canSubmit = true;
        
        
        //refdata.put("canSubmit", canSubmit);
		refdata.put("isUserSAECoordinato", isUserSAECoordinator);
        
        return refdata;
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
	
	public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
	
	public void setEvaluationService(EvaluationService evaluationService){
		this.evaluationService = evaluationService;
	}
	
	public void setReportValidationService(ReportValidationService reportValidationService){
		this.reportValidationService = reportValidationService;
	}
	
	public void setReportDao(ReportDao reportDao){
		this.reportDao = reportDao;
	}
	
	@Required
    public void setCsmUserRepository(final CSMUserRepository csmUserRepository) {
        this.csmUserRepository = csmUserRepository;
    }
}