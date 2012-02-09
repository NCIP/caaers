package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.domain.Submitter;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import gov.nih.nci.cabig.ctms.web.tabs.*;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Biju Joseph
 * @author Krikor Krumlian
 */
public class SubmitReportController extends AutomaticSaveFlowFormController<SubmitExpeditedAdverseEventCommand, Report, ReportDao> {
    public static final String AJAX_SUBVIEW_PARAMETER = "subview";
    private Configuration configuration;
    protected ReportDao reportDao;
    private ReportRepository reportRepository;
	
	protected final Log log = LogFactory.getLog(getClass());
	
    public SubmitReportController() {
        setCommandClass(SubmitExpeditedAdverseEventCommand.class);
        setFlowFactory(new FlowFactory<SubmitExpeditedAdverseEventCommand>(){
            public Flow<SubmitExpeditedAdverseEventCommand> createFlow(SubmitExpeditedAdverseEventCommand command) {
                Flow<SubmitExpeditedAdverseEventCommand> flow = new Flow<SubmitExpeditedAdverseEventCommand>("Submit the Report");
                flow.addTab(new SubmitterTab());
                flow.addTab(new SubmitReportTab());
                flow.addTab(new SubmitReportResultTab());
                return flow;
            }
        });
        setBindOnNewForm(false);
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        binder.setDisallowedFields("aeReport", "report");
        super.initBinder(request, binder);
    }

    @Override
    protected Report getPrimaryDomainObject(SubmitExpeditedAdverseEventCommand command) {
       return command.getReport();
    }

    @Override
    protected ReportDao getDao() {
        return reportDao;
    }

    @Override
	protected Map referenceData(HttpServletRequest request, Object o,	Errors errors, int page) throws Exception {
        SubmitExpeditedAdverseEventCommand command = (SubmitExpeditedAdverseEventCommand) o;
		Map referenceData =  super.referenceData(request, command, errors, page);
        referenceData.put("aesummary", command.getSummary());
		return referenceData;
    }
    
    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
    	if(log.isDebugEnabled()) log.debug("In form backing object");

        Report report = null;
        String reportId = request.getParameter("reportId");
        if(reportId != null){
            int repId = WebUtils.getIntParameter(request, "reportId");
            report = reportDao.getById(repId);
            log.debug("fetched report : " + reportId);
        }

        Boolean workflowEnabled = getConfiguration().get(Configuration.ENABLE_WORKFLOW);
        Boolean unidentifiedMode = getConfiguration().get(Configuration.UNIDENTIFIED_MODE);
        
        SubmitExpeditedAdverseEventCommand command = new SubmitExpeditedAdverseEventCommand(report, BooleanUtils.isTrue(unidentifiedMode), reportDao, reportRepository);

        command.setReportId(reportId);
        command.setFrom(request.getParameter("from"));
        command.setWorkflowEnabled(workflowEnabled);
        command.setReport(report);
        if(report.getSubmitter() == null) {
            report.setSubmitter(new Submitter());
        }

        return command;
    }

    @Override
    protected Object currentFormObject(HttpServletRequest request, Object oCommand) throws Exception {
        SubmitExpeditedAdverseEventCommand command =  (SubmitExpeditedAdverseEventCommand) oCommand;
        if(command.getReport() != null) {
            command.setReport(reportDao.getById(command.getReport().getId()));
        }
        if(command.getReport().getSubmitter() == null) {
            command.getReport().setSubmitter(new Submitter());
        }
        return super.currentFormObject(request, oCommand);
    }

    @Override
    protected boolean shouldSave(HttpServletRequest request, SubmitExpeditedAdverseEventCommand command, Tab<SubmitExpeditedAdverseEventCommand> tab) {
        if(log.isDebugEnabled()) log.debug("should save Report ? " + !command.isSubmissionInprogress() );
        return ! command.isSubmissionInprogress();
    }

    @Override
    protected SubmitExpeditedAdverseEventCommand save(SubmitExpeditedAdverseEventCommand command, Errors errors) {
        if(log.isDebugEnabled()) log.debug("Saving report [" + command.getReport().getId() + "]? " + !errors.hasErrors());
        if(!errors.hasErrors()) command.save();
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object oCommand, BindException errors) throws Exception {
    	log.debug("In processFinish");
        SubmitExpeditedAdverseEventCommand command = (SubmitExpeditedAdverseEventCommand) oCommand;
        ModelAndView modelAndView;
        Map<String, Object> model = new ModelMap("participant", command.getReport().getAeReport().getParticipant().getId());
        model.put("study", command.getReport().getAeReport().getStudy().getId());
        modelAndView = new ModelAndView("redirectToAeList", model);
        log.debug("Returning the viewname as :" + modelAndView.getViewName());
        return modelAndView;
    }


    @Override
    protected String getViewName(HttpServletRequest request, Object command, int page) {
        log.debug("In getViewName");
        String subviewName = request.getParameter(AJAX_SUBVIEW_PARAMETER);

        if (subviewName != null) {
            log.debug("Identified as Ajax view");
            // for side-effects:
            super.getViewName(request, command, page);
            return "ae/ajax/" + subviewName;
        } else {
            log.debug("Identified as non Ajax view");
            return super.getViewName(request, command, page);
        }
    }

    @Required
    public ReportDao getReportDao() {
        return reportDao;
    }

    public void setReportDao(ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    @Required
    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    @Required
    public ReportRepository getReportRepository() {
        return reportRepository;
    }

    public void setReportRepository(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }
}
