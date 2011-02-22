package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.api.AdeersReportGenerator;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.repository.ReportValidationService;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.service.ReportSubmittability;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.validation.ValidationError;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;



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

        /*String xmlFileName = "/home/nikhil/out/xslt/expedited_report_caaers_complete.xml";
        InputStream is =  new FileInputStream(xmlFileName);
        Writer writer = new StringWriter();
        if (is != null) {
            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                is.close();
            }
        }

        String caAERSXML = writer.toString();
        System.out.println("xml:" + caAERSXML);*/
        ExpeditedAdverseEventReport aeReport = expeditedAdverseEventReportDao.getById(Integer.parseInt(aeReportId));
        Report report = reportDao.getById(Integer.parseInt(reportId));
        String xml = adeersReportGenerator.generateCaaersXml(aeReport, report);
        String pngOutFile = "expeditedAdverseEvent-"+aeReportId + "-" + reportId+"report.png";


        List<String> list = adeersReportGenerator.generateImage(xml, tempDir + File.separator + pngOutFile);
        command.addFiles(aeReportId, reportId, list);


		if(reportId != null && !reportId.equals("") && !reportId.equals("null"))
			command.setReportId(Integer.parseInt(reportId));
		else
			command.setReportId(null);
		command.setAeReport(aeReport);
		for(Report r: aeReport.getReports()){
			if(r.getId().equals(Integer.parseInt(reportId)))
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
		boolean isReportReviewer = SecurityUtils.hasAuthorityOf(UserGroupType.ae_expedited_report_reviewer);
        
        //boolean canSubmit = false;
        //if(reportMessages.get(command.ZERO).isSubmittable() && reportMessages.get(command.getReportId()).isSubmittable() && isReportReviewer)
        //	canSubmit = true;
        
        
        //refdata.put("canSubmit", canSubmit);
		refdata.put("isUserSAECoordinato", isReportReviewer);
        
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
}