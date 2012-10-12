package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.ExternalAdverseEventDao;
import gov.nih.nci.cabig.caaers.dao.ReconciliationReportDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.dto.AdverseEventDTO;
import gov.nih.nci.cabig.caaers.domain.dto.TermDTO;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
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
import java.util.*;

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
        flow.addTab(new AdverseEventLinkTab("Map Adverse Events", "Link Adverse Events", "ae/ae_reconcile_link"));
        flow.addTab(new AdverseEventMergeTab("Merge Adverse Events", "Merge Adverse Events", "ae/ae_reconcile_merge"));
        flow.addTab(new AdverseEventSelectTab("Choose Adverse Events", "Choose Adverse Events", "ae/ae_reconcile_choose"));
        flow.addTab(new AdverseEventReconcileSummaryTab("Reconciliation Report", "Adverse Event Reconciliation Report", "ae/ae_reconcile_summary.jsp"));
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

                    mv = new ModelAndView("forward:/pages/ae/reconciliationReport?decorator=standardNoHeader&customDecoration=true");
                    request.setAttribute("report", report);
                }
            }
        }

        if(mv != null) return mv;
        return super.handleRequestInternal(request, response);   
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {

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
    protected void onBind(HttpServletRequest request, Object oCommand, BindException errors) throws Exception {
        super.onBind(request, oCommand, errors);

        //reload the reporting period on every submit.
        AdverseEventReconciliationCommand reconciliationCommand = (AdverseEventReconciliationCommand) oCommand;
        if(reconciliationCommand.getReportingPeriod() != null) {
            Integer id = reconciliationCommand.getReportingPeriod().getId();
            reconciliationCommand.setReportingPeriod(adverseEventReportingPeriodDao.getById(id));
        }

    }

    @Override
    protected AdverseEventReportingPeriod getPrimaryDomainObject(AdverseEventReconciliationCommand command) {
        return command.getReportingPeriod();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected AdverseEventReportingPeriodDao getDao() {
        return adverseEventReportingPeriodDao;
    }

    protected boolean shouldSave(HttpServletRequest request, AdverseEventReconciliationCommand command, Tab<AdverseEventReconciliationCommand> tab) {
        return true;
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
            AdverseEventReportingPeriod reportingPeriod = command.getReportingPeriod();
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
            
            //delete caaers-Aes
            for(AdverseEventDTO ae : command.getRejectedInternalAeList()){
                AdverseEvent adverseEvent = reportingPeriod.findAdverseEventById(ae.getId());
                if(adverseEvent != null)adverseEvent.retire();
            }

            //ae's to be updated
            
            boolean isCTC = reportingPeriod.getStudy().getAeTerminology().getTerm() == Term.CTC;
            boolean isMedDRA = reportingPeriod.getStudy().getAeTerminology().getTerm() == Term.MEDDRA;

            Set<Integer> termIdSet = new HashSet<Integer>();
            for(AdverseEventDTO ae: command.getUnMappedExternalAeList()){
                termIdSet.add(ae.getTerm().getId());
            }
            for(AdverseEventDTO ae : command.getMatchedAeMapping().values()){
                termIdSet.add(ae.getTerm().getId());
            }
            Map<Integer, CtcTerm> ctcTermMap = new HashMap<Integer, CtcTerm>();
            Map<Integer, LowLevelTerm> meddraTermMap = new HashMap<Integer, LowLevelTerm>();
            if(isCTC){
                List<CtcTerm> ctcTerms = ctcTermDao.findByIds(new ArrayList<Integer>(termIdSet));
                if(ctcTerms != null){
                    for(CtcTerm ctcTerm : ctcTerms) ctcTermMap.put(ctcTerm.getId(), ctcTerm);
                }
            }else if(isMedDRA){
                List<LowLevelTerm> meddraTerms = lowLevelTermDao.findByIds(new ArrayList<Integer>(termIdSet));
                if(meddraTerms != null){
                    for(LowLevelTerm meddraTerm : meddraTerms) meddraTermMap.put(meddraTerm.getId(), meddraTerm);
                }
            }
            //update caAERS Aes
            for(Map.Entry<AdverseEventDTO , AdverseEventDTO> entry : command.getMatchedAeMapping().entrySet()){
                 AdverseEvent adverseEvent = reportingPeriod.findAdverseEventById(entry.getKey().getId());
                 String mergeMapKey = entry.getKey().getId() + "_" + entry.getValue().getId();
                 AdverseEventDTO merged = command.getMergeMap().get(mergeMapKey) ;
                 if(isCTC){
                    CtcTerm ctcTerm = adverseEvent.getAdverseEventCtcTerm().getCtcTerm();
                    if(ctcTerm.getId() != merged.getTerm().getId()){
                        CtcTerm newTerm = ctcTermMap.get(merged.getTerm().getId());
                        adverseEvent.getAdverseEventCtcTerm().setCtcTerm(newTerm);
                    }
                 }
                 if(isMedDRA){
                    LowLevelTerm meddraTerm = adverseEvent.getAdverseEventMeddraLowLevelTerm().getLowLevelTerm();
                    if(meddraTerm.getId() != merged.getTerm().getId()){
                        LowLevelTerm newTerm = meddraTermMap.get(merged.getTerm().getId());
                        adverseEvent.getAdverseEventMeddraLowLevelTerm().setTerm(newTerm);
                    }
                 }
                 merged.mergeChanges(adverseEvent);
            }

            //new caAERS Aes
            for(AdverseEventDTO ae : command.getUnMappedExternalAeList()){
                 AdverseEvent adverseEvent = new AdverseEvent();
                 adverseEvent.setGradedDate(new Date());
                 if(isCTC){
                     CtcTerm ctcTerm = ctcTermMap.get(ae.getTerm().getId());
                     AdverseEventCtcTerm adverseEventCtcTerm = new AdverseEventCtcTerm();
                     adverseEventCtcTerm.setCtcTerm(ctcTerm);
                     adverseEvent.setAdverseEventCtcTerm(adverseEventCtcTerm);
                     adverseEventCtcTerm.setAdverseEvent(adverseEvent);
                 }
                if(isMedDRA){
                    LowLevelTerm meddraTerm = meddraTermMap.get(ae.getTerm().getId());
                    AdverseEventMeddraLowLevelTerm adverseEventMeddraLowLevelTerm = new AdverseEventMeddraLowLevelTerm();
                    adverseEventMeddraLowLevelTerm.setLowLevelTerm(meddraTerm);
                    adverseEvent.setAdverseEventMeddraLowLevelTerm(adverseEventMeddraLowLevelTerm);
                    adverseEventMeddraLowLevelTerm.setAdverseEvent(adverseEvent);
                }
                ae.mergeChanges(adverseEvent);
                reportingPeriod.addAdverseEvent(adverseEvent);
            }

            report.setAdverseEventReportingPeriod(reportingPeriod);
            reconciliationReportDao.save(report);

            reportingPeriod.setOldAeMapping(null);
            adverseEventReportingPeriodDao.save(reportingPeriod);

            ModelAndView mv  = new ModelAndView("ae/ae_reconcile_report");
            mv.addObject("report", report);
            mv.addObject("flashMessage", messageSource.getMessage("reconciliation.report.saved", new Object[] {}, "Reconciliation report saved", Locale.getDefault()));
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
