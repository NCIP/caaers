package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.dto.AdverseEventDTO;
import gov.nih.nci.cabig.caaers.domain.dto.TermDTO;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.AutomaticSaveAjaxableFormController;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public AdverseEventReconciliationController() {
        Flow<AdverseEventReconciliationCommand> flow = new Flow<AdverseEventReconciliationCommand>("Reconcile Adverse Events");
        flow.addTab(new AdverseEventLinkTab("Link Adverse Event Data", "Link Adverse Events", "ae/ae_reconcile_link"));
        flow.addTab(new AdverseEventMergeTab("Merge Adverse Event Data", "Merge Adverse Events", "ae/ae_reconcile_merge"));
        flow.addTab(new AdverseEventSelectTab("Choose New Adverse Event Data", "Choose Adverse Events", "ae/ae_reconcile_choose"));
        flow.addTab(new AdverseEventReconciliationReportTab("Reconciliation Summary", "Summary", "ae/ae_reconcile_summary"));
        setFlow(flow);
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        List<AdverseEventDTO> externalAes = new ArrayList<AdverseEventDTO>();
        List<AdverseEventDTO> internalAes = new ArrayList<AdverseEventDTO>();
        populateInternalAes(internalAes);
        populateExternalAes(externalAes);
        
        AdverseEventReconciliationCommand command = new AdverseEventReconciliationCommand(internalAes, externalAes);
        command.setReportingPeriod(adverseEventReportingPeriodDao.getById(44));
        command.link(14,4);
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
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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

    private AdverseEventDTO mockAdverseEvent(int id, int termId, String termCode, String term, String grade, String startDate, String endDate, String verbatim, String whySerious, String attibution){
        AdverseEventDTO dto = new AdverseEventDTO();
        dto.setId(id);
        TermDTO t = new TermDTO();
        t.setId(termId);
        t.setCode(termCode);
        t.setName(term);
        dto.setTerm(t);
        dto.setGrade(grade);
        dto.setStartDate(startDate);
        dto.setEndDate(endDate);
        dto.setVerbatim(verbatim);
        dto.setWhySerious(whySerious);
        dto.setAttribution(attibution);
        return dto;
    }

    private void populateInternalAes(List<AdverseEventDTO> aeList){
        AdverseEventDTO dto = null;


        dto = mockAdverseEvent(12, 102, "5490", "Nausea", "Severe", "10/09/2011", "10/11/2011", "Redness in left eye", "", "Probable");
        dto.setSource("caAERS");
        dto.setExternalID("92");
        aeList.add(dto);

        dto = mockAdverseEvent(14, 104, "5492", "Lymphopenia", "Moderate", "10/09/2011", "10/13/2011", "", "", "Unrelated");
        dto.setSource("caAERS");
        aeList.add(dto);

        dto = mockAdverseEvent(15, 205, "7493", "Proctitis", "Mild", "", "", "Sick stomach", "Hospitalized", "Unrelated");
        dto.setSource("caAERS");
        aeList.add(dto);

        dto = mockAdverseEvent(16, 706, "6496", "Alkaline phosphatase", "Moderate", "", "", "Sick stomach", "Hospitalized", "Unrelated");
        dto.setSource("caAERS");
        aeList.add(dto);
    }
    private void populateExternalAes(List<AdverseEventDTO> aeList){
        AdverseEventDTO dto = null;

        dto = mockAdverseEvent(1, 101, "4490", "Pain", "Mild", "10/10/2011", "10/11/2011", "Redness in eye", "Hospitalized", "Likely");
        dto.setSource("Force");
        dto.setExternalID("91");
        aeList.add(dto);

        dto = mockAdverseEvent(2, 102, "5490", "Nausea", "Severe", "10/09/2011", "10/12/2011", "Red eye", "", "Probable");
        dto.setSource("Force");
        dto.setExternalID("92");
        aeList.add(dto);

        dto = mockAdverseEvent(3, 103, "5491", "Vomiting", "Severe", "10/09/2011", "10/10/2011", "Red neck", "", "Probable");
        dto.setSource("Force");
        dto.setExternalID("93");
        aeList.add(dto);

        dto = mockAdverseEvent(4, 104, "5492", "Lymphopenia", "Moderate", "10/09/2011", "10/13/2011", "", "", "Definite");
        dto.setSource("Force");
        dto.setExternalID("94");
        aeList.add(dto);

        dto = mockAdverseEvent(5, 105, "5493", "Fistula-intestinal", "Mild", "10/09/2011", "", "Sick stomach", "Hospitalized", "Unrelated");
        dto.setSource("Force");
        dto.setExternalID("95");
        aeList.add(dto);

        dto = mockAdverseEvent(6, 106, "5496", "Gastritis", "Mild", "", "", "Sick stomach", "Hospitalized", "Unrelated");
        dto.setSource("Force");
        dto.setExternalID("96");
        aeList.add(dto);
    }
}
