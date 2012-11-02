package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.api.AdeersReportGenerator;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.domain.AdditionalInformationDocument;
import gov.nih.nci.cabig.caaers.domain.AdditionalInformationDocumentType;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.repository.ReportValidationService;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.service.ReportSubmittability;
import gov.nih.nci.cabig.caaers.service.adverseevent.AdditionalInformationDocumentService;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.validation.ValidationError;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.mvc.SimpleFormController;
import  gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;


public class ReviewAeReportController extends SimpleFormController{

	private static final String MAIN_FIELD_GROUP = "main";
	private ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;
	private ReportDao reportDao;
	private StudyParticipantAssignmentDao assignmentDao;
	private Configuration configuration;
	private MessageSource messageSource;
	private EvaluationService evaluationService;
	private ReportValidationService reportValidationService;
    private AdeersReportGenerator adeersReportGenerator;
    private AdditionalInformationDocumentService additionalInformationDocumentService;
    String tempDir = System.getProperty("java.io.tmpdir");

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
        Report report = reportDao.getById(Integer.parseInt(reportId));
        String xml = adeersReportGenerator.generateCaaersXml(aeReport, report);
        String pngOutFile = WebUtils.getBaseFileName(aeReportId ,reportId);


        List<String> list = adeersReportGenerator.generateImage(xml, tempDir + File.separator + pngOutFile);
        command.addFiles(aeReportId, reportId, list);


        if (reportId != null && !reportId.equals("") && !reportId.equals("null"))
            command.setReportId(Integer.parseInt(reportId));
        else
            command.setReportId(null);
        
        //(CAAERS-5865)to perform sync only for ctep-esys studies, 
        //set studyOutOfSync to false, so sync will not run for non-ctep-esys studies
        if(aeReport.getStudy().getCtepEsysIdentifier() == null) {
        	command.setStudyOutOfSync(false);
        }
        
        command.setAeReport(aeReport);
        for (Report r : aeReport.getReports()) {
            if (r.getId().equals(Integer.parseInt(reportId)))
                command.setWorkflowEnabled(r.getReportDefinition().getWorkflowEnabled());
        }
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

            ValidationErrors validationErrors = evaluationService.validateReportingBusinessRules(command.getAeReport(), section);
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
        boolean isReportReviewer = SecurityUtils.hasAuthorityOf(UserGroupType.ae_expedited_report_reviewer);

        //boolean canSubmit = false;
        //if(reportMessages.get(command.ZERO).isSubmittable() && reportMessages.get(command.getReportId()).isSubmittable() && isReportReviewer)
        //	canSubmit = true;


        //refdata.put("canSubmit", canSubmit);
        refdata.put("isUserSAECoordinato", isReportReviewer);

        //additional information documents

        Integer additionalInformationId = command.getAeReport().getAdditionalInformation().getId();
        List<AdditionalInformationDocument> additionalInformationDocuments = additionalInformationDocumentService.
                findByAdditionalInformationId(additionalInformationId);

        Map<String, List<AdditionalInformationDocument>> documents = AdditionalInformationDocument.groupDocumentsByDocumentType(additionalInformationDocuments);

        refdata.put("documents", documents);
        refdata.put("additionalInformationId", additionalInformationId);


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

    public AdeersReportGenerator getAdeersReportGenerator() {
        return adeersReportGenerator;
    }

    public void setAdeersReportGenerator(AdeersReportGenerator adeersReportGenerator) {
        this.adeersReportGenerator = adeersReportGenerator;
    }

    @Required
    public void setAdditionalInformationDocumentService(AdditionalInformationDocumentService additionalInformationDocumentService) {
        this.additionalInformationDocumentService = additionalInformationDocumentService;
    }
}