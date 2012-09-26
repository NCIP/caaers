package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.ExternalAdverseEventDao;
import gov.nih.nci.cabig.caaers.dao.ReconciliationReportDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.dto.AdverseEventDTO;
import gov.nih.nci.cabig.caaers.domain.dto.TermDTO;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.AutomaticSaveAjaxableFormController;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Biju Joseph
 */
public class AdverseEventReconciliationController extends AutomaticSaveAjaxableFormController<AdverseEventReconciliationCommand, AdverseEventReportingPeriod, AdverseEventReportingPeriodDao> {
    private Configuration configuration;
    private AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
    private ReconciliationReportDao reconciliationReportDao;
    private ExternalAdverseEventDao externalAdverseEventDao;
    private CtcTermDao ctcTermDao;
    private LowLevelTermDao lowLevelTermDao;

    public AdverseEventReconciliationController() {
        Flow<AdverseEventReconciliationCommand> flow = new Flow<AdverseEventReconciliationCommand>("Reconcile Adverse Events");
        flow.addTab(new AdverseEventLinkTab("Link Adverse Event Data", "Link Adverse Events", "ae/ae_reconcile_link"));
        flow.addTab(new AdverseEventMergeTab("Merge Adverse Event Data", "Merge Adverse Events", "ae/ae_reconcile_merge"));
        flow.addTab(new AdverseEventSelectTab("Choose New Adverse Event Data", "Choose Adverse Events", "ae/ae_reconcile_choose"));
        setFlow(flow);
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ModelAndView mv = null;
        if(request.getParameter("showReport")!= null){
            HttpSession session = request.getSession(false);
            if(session != null){
                String formAttrName = getFormSessionAttributeName(request);
                Object command = session.getAttribute(formAttrName);
                if(command != null){
                    AdverseEventReconciliationCommand reconciliationCommand = (AdverseEventReconciliationCommand) command;
                    
                    //process rejections
                    String rejectedExternalAeStr = request.getParameter("rejectedExternalAeStr");
                    String rejectedInternalAeStr = request.getParameter("rejectedInternalAeStr");
                    if(StringUtils.isNotEmpty(rejectedExternalAeStr)){
                      reconciliationCommand.setRejectedExternalAeStr(rejectedExternalAeStr);
                      reconciliationCommand.processExternalAeRejections();
                    }
                    if(StringUtils.isNotEmpty(rejectedInternalAeStr)){
                        reconciliationCommand.setRejectedInternalAeStr(rejectedInternalAeStr);
                        reconciliationCommand.processInternalAeRejections();
                    }
                    ReconciliationReport report = reconciliationCommand.generateReconcilationReport();
                    mv = new ModelAndView("ae/ae_reconcile_report");
                    mv.addObject("report", report);
                }
            }
        }

        if(mv != null) return mv;
        return super.handleRequestInternal(request, response);   
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {

        List<AdverseEventDTO> externalAes = new ArrayList<AdverseEventDTO>();
        List<AdverseEventDTO> internalAes = new ArrayList<AdverseEventDTO>();
        List<AdverseEventDTO> errorAes = new ArrayList<AdverseEventDTO>();

        AdverseEventReconciliationCommand command = new AdverseEventReconciliationCommand(reconciliationReportDao, externalAdverseEventDao, ctcTermDao,  lowLevelTermDao);
        String rpId = request.getParameter("rpId");
        if(StringUtils.isNotEmpty(rpId)){
            AdverseEventReportingPeriod reportingPeriod = adverseEventReportingPeriodDao.getById(Integer.parseInt(rpId));
            command.setReportingPeriod(reportingPeriod);
            command.loadExternalAdverseEvents();
            command.loadInternalAdverseEvents();
            command.doAutoMapping();
        }
        return command;
    }

    @Override
    protected AdverseEventReportingPeriod getPrimaryDomainObject(AdverseEventReconciliationCommand command) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected AdverseEventReportingPeriodDao getDao() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    protected boolean shouldSave(HttpServletRequest request, AdverseEventReconciliationCommand command, Tab<AdverseEventReconciliationCommand> tab) {
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Map referenceData(HttpServletRequest request, Object o,	Errors errors, int page) throws Exception {
        Map referenceData =  super.referenceData(request, o, errors, page);
        populateSummary(referenceData, (AdverseEventReconciliationCommand) o);
        return referenceData;
    }
    
    private void populateSummary(Map referenceData, AdverseEventReconciliationCommand command){
        Map<String, String> summary = new LinkedHashMap<String, String>();

        summary.put("Study", (command.getStudy() == null) ? "" : "(" +  command.getStudy().getPrimaryIdentifierValue() + ") " + command.getStudy().getShortTitle());
        if (getUnidentifiedMode()) {
            summary.put("Participant", "(" + (command.getAssignment() == null ? "" : command.getAssignment().getStudySubjectIdentifier()) + ")");
        } else {
            summary.put("Participant", (command.getParticipant() == null) ? "" : "(" + command.getParticipant().getPrimaryIdentifierValue() + ") " + command.getParticipant().getFullName());
        }

        if(command.getReportingPeriod() != null){
            summary.put("Course", command.getReportingPeriod().getName());
        }
        referenceData.put("routineAeSummary", summary);
    }


        @Override
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object cmd, BindException errors) throws Exception {
        
        if(!errors.hasErrors()){
            AdverseEventReconciliationCommand command = (AdverseEventReconciliationCommand) cmd;
            AdverseEventReportingPeriod reportingPeriod = adverseEventReportingPeriodDao.getById(command.getReportingPeriod().getId());
            ReconciliationReport report = command.generateReconcilationReport();

            //mark delete external Aes
            List<String> externalIds = command.getRejectedExternalAeExternalIds();
            if(!externalIds.isEmpty()){
                externalAdverseEventDao.updateStatus(ExternalAEReviewStatus.PENDING, ExternalAEReviewStatus.REJECTED, externalIds);
            }
            //mark the incorrect - error items
            List<String> errorIds = command.getIncorrectExternalAeExternalIds();
            if(!errorIds.isEmpty()){
                externalAdverseEventDao.updateStatus(ExternalAEReviewStatus.PENDING, ExternalAEReviewStatus.ERROR, errorIds);
            }
            //mark reviewed - external Aes
            List<String> reviewedExternalIds = command.getAllReviewedExternalAeExternalIds();
            if(!reviewedExternalIds.isEmpty()){
                externalAdverseEventDao.updateStatus(ExternalAEReviewStatus.PENDING, ExternalAEReviewStatus.REVIEWED, reviewedExternalIds);
            }
            
            List<AdverseEvent> aeList = reportingPeriod.getAdverseEvents();
            //delete caaers-Aes
            for(AdverseEventDTO ae : command.getRejectedExternalAeList()){
                AdverseEvent adverseEvent = reportingPeriod.findAdverseEventById(ae.getId());
                if(adverseEvent != null)aeList.remove(adverseEvent);
            }

            
            //update caAERS Aes

            //save the reconciliation report

            report.setAdverseEventReportingPeriod(reportingPeriod);
            reconciliationReportDao.save(report);

            reportingPeriod.setOldAeMapping(null);
            adverseEventReportingPeriodDao.save(reportingPeriod);

            ModelAndView mv  = new ModelAndView("ae/ae_reconcile_report");
            mv.addObject("report", report);
            return mv;
        }
        return null; 
    }


    public boolean getUnidentifiedMode(){
        boolean unidentifiedMode;
        if (configuration.get(Configuration.UNIDENTIFIED_MODE) == null) unidentifiedMode = false;
        else unidentifiedMode =  configuration.get(Configuration.UNIDENTIFIED_MODE);
        return unidentifiedMode;
    }
    public Configuration getConfiguration() {
        return configuration;
    }
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public AdverseEventReportingPeriodDao getAdverseEventReportingPeriodDao() {
        return adverseEventReportingPeriodDao;
    }

    public void setAdverseEventReportingPeriodDao(AdverseEventReportingPeriodDao adverseEventReportingPeriodDao) {
        this.adverseEventReportingPeriodDao = adverseEventReportingPeriodDao;
    }

    public ReconciliationReportDao getReconciliationReportDao() {
        return reconciliationReportDao;
    }

    public void setReconciliationReportDao(ReconciliationReportDao reconciliationReportDao) {
        this.reconciliationReportDao = reconciliationReportDao;
    }

    public ExternalAdverseEventDao getExternalAdverseEventDao() {
        return externalAdverseEventDao;
    }

    public void setExternalAdverseEventDao(ExternalAdverseEventDao externalAdverseEventDao) {
        this.externalAdverseEventDao = externalAdverseEventDao;
    }

    public CtcTermDao getCtcTermDao() {
        return ctcTermDao;
    }

    public void setCtcTermDao(CtcTermDao ctcTermDao) {
        this.ctcTermDao = ctcTermDao;
    }

    public LowLevelTermDao getLowLevelTermDao() {
        return lowLevelTermDao;
    }

    public void setLowLevelTermDao(LowLevelTermDao lowLevelTermDao) {
        this.lowLevelTermDao = lowLevelTermDao;
    }

}
