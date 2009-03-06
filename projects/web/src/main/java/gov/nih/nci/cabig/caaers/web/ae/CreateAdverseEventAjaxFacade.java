package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.tools.ObjectTools.reduce;
import static gov.nih.nci.cabig.caaers.tools.ObjectTools.reduceAll;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.AnatomicSiteDao;
import gov.nih.nci.cabig.caaers.dao.ChemoAgentDao;
import gov.nih.nci.cabig.caaers.dao.ConditionDao;
import gov.nih.nci.cabig.caaers.dao.CtcCategoryDao;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.CtepStudyDiseaseDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.InterventionSiteDao;
import gov.nih.nci.cabig.caaers.dao.LabCategoryDao;
import gov.nih.nci.cabig.caaers.dao.LabLoadDao;
import gov.nih.nci.cabig.caaers.dao.LabTermDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.PreExistingConditionDao;
import gov.nih.nci.cabig.caaers.dao.PriorTherapyDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.TreatmentAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.UserDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.dao.query.ajax.ParticipantAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.dao.query.ajax.StudySearchableAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.AnatomicSite;
import gov.nih.nci.cabig.caaers.domain.ChemoAgent;
import gov.nih.nci.cabig.caaers.domain.CodedGrade;
import gov.nih.nci.cabig.caaers.domain.Condition;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.CtepStudyDisease;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReportChild;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.InterventionSite;
import gov.nih.nci.cabig.caaers.domain.LabCategory;
import gov.nih.nci.cabig.caaers.domain.LabLoad;
import gov.nih.nci.cabig.caaers.domain.LabTerm;
import gov.nih.nci.cabig.caaers.domain.ParticipantHistory;
import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;
import gov.nih.nci.cabig.caaers.domain.PriorTherapy;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.ajax.ParticipantAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudyAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.ParticipantAjaxableDomainObjectRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.StudySearchableAjaxableDomainObjectRepository;
import gov.nih.nci.cabig.caaers.service.InteroperationService;
import gov.nih.nci.cabig.caaers.tools.ObjectTools;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.web.dwr.AjaxOutput;
import gov.nih.nci.cabig.caaers.web.dwr.IndexChange;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.acegisecurity.context.SecurityContext;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.bean.Row;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.core.TableModelImpl;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;

/**
 * @author Rhett Sutphin
 */
public class CreateAdverseEventAjaxFacade {

    private static final Log log = LogFactory.getLog(CreateAdverseEventAjaxFacade.class);
    private static Class<?>[] CONTROLLERS = {EditAdverseEventController.class, CreateAdverseEventController.class};

    protected StudyDao studyDao;
    protected ParticipantDao participantDao;
    protected StudyParticipantAssignmentDao assignmentDao;
    protected CtcTermDao ctcTermDao;
    protected CtcCategoryDao ctcCategoryDao;
    protected CtcDao ctcDao;
    protected LowLevelTermDao lowLevelTermDao;
    protected ExpeditedAdverseEventReportDao aeReportDao;
    protected AnatomicSiteDao anatomicSiteDao;
    protected InteroperationService interoperationService;
    protected PriorTherapyDao priorTherapyDao;
    protected PreExistingConditionDao preExistingConditionDao;
    protected AgentDao agentDao;
    protected TreatmentAssignmentDao treatmentAssignmentDao;
    protected ExpeditedReportTree expeditedReportTree;
    protected ConfigProperty configProperty;
    protected ReportRepository reportRepository;
    protected LabCategoryDao labCategoryDao;
    protected LabTermDao labTermDao;
    protected ChemoAgentDao chemoAgentDao;
    protected InterventionSiteDao interventionSiteDao;
    protected CtepStudyDiseaseDao ctepStudyDiseaseDao;
    protected AdverseEventReportingPeriodDao reportingPeriodDao;
    protected LabLoadDao labLoadDao;
    private StudySearchableAjaxableDomainObjectRepository studySearchableAjaxableDomainObjectRepository;
    private ParticipantAjaxableDomainObjectRepository participantAjaxableDomainObjectRepository;
    private ConditionDao conditionDao;
	private AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository;
	private UserDao userDao;


    public Class<?>[] controllers() {
        return CONTROLLERS;
    }

    public List<AnatomicSite> matchAnatomicSite(String text) {
        return anatomicSiteDao.getBySubnames(extractSubnames(text));
    }

    public AnatomicSite getAnatomicSiteById(String anatomicSiteId) throws Exception {
        return anatomicSiteDao.getById(Integer.parseInt(anatomicSiteId));
    }

    public String buildAnatomicSiteTable(String el, final Map parameterMap, String tableId, HttpServletRequest request) throws Exception {

        try {
            TableModel model = getTableModel(parameterMap, request);
            List<AnatomicSite> anatomicSites = anatomicSiteDao.getAll();

            String onInvokeAction = "showShowAllTable('" + el + "', '" + tableId + "')";
            addTableAndRowToModel(model, tableId, anatomicSites, onInvokeAction);

            Column columnTerm = model.getColumnInstance();
            columnTerm.setProperty("name");
            columnTerm.setTitle("Primary site of disease");
            columnTerm
                    .setCell("gov.nih.nci.cabig.caaers.web.search.link.AnatomicSiteLinkDisplayCell");
            model.addColumn(columnTerm);


            return model.assemble().toString();

        }
        catch (Exception e) {
            log.error("error while retriving the anatomicSites" + e.toString() + " message" + e.getMessage());
        }

        return "";

    }

    private TableModel getTableModel(Map parameterMap, HttpServletRequest request) {
        Context context = null;
        if (parameterMap == null) {
            context = new HttpServletRequestContext(request);
        } else {
            context = new HttpServletRequestContext(request, parameterMap);
        }

        TableModel model = new TableModelImpl(context);
        return model;
    }


    public List<PriorTherapy> matchPriorTherapies(String text) {
        return priorTherapyDao.getBySubnames(extractSubnames(text));
    }

    public List<PreExistingCondition> matchPreExistingConds(String text) {
        return preExistingConditionDao.getBySubnames(extractSubnames(text));
    }

    public List<LowLevelTerm> matchLowLevelTermsByCode(int version_id, String text) {
        List<LowLevelTerm> terms = lowLevelTermDao.getByVersionSubnames(version_id, extractSubnames(text));
        return ObjectTools.reduceAll(terms, "id", "meddraCode", "meddraTerm");
    }

    public List<Condition> matchConditions(String text) {
        List<Condition> conditions = conditionDao.getAllByText(text);
        return conditions;
    }

    public List<ChemoAgent> matchChemoAgents(String text) {
        String[] excerpts = {text};
        List<ChemoAgent> agents = chemoAgentDao.getBySubname(excerpts);
        return agents;
    }

    public ChemoAgent getChemoAgentById(String chemoAgentId) throws Exception {

        return chemoAgentDao.getById(Integer.parseInt(chemoAgentId));
    }

    public String buildChemoAgentsTable(String el, final Map parameterMap, String tableId, HttpServletRequest request) throws Exception {

        try {
            List<ChemoAgent> chemoAgents = chemoAgentDao.getAll();
            TableModel model = getTableModel(parameterMap, request);

//            String onInvokeAction = "showShowAllTable('" + tableId + "')";
            String onInvokeAction = "showShowAllTable('" + el + "', '" + tableId + "')";

            addTableAndRowToModel(model, tableId, chemoAgents, onInvokeAction);

            Column columnTerm = model.getColumnInstance();
            columnTerm.setProperty("name");
            columnTerm.setTitle("Agent");
            columnTerm.setCell("gov.nih.nci.cabig.caaers.web.search.link.ChemoAgentLinkDisplayCell");
            model.addColumn(columnTerm);


            return model.assemble().toString();


        }
        catch (Exception e) {
            log.error("error while retriving the ctc terms" + e.toString() + " message" + e.getMessage());
        }

        return "";

    }

    public List<InterventionSite> matchInterventionSites(String text) {
        String[] excerpts = {text};
        List<InterventionSite> sites = interventionSiteDao.getBySubname(excerpts);
        return sites;
    }

    public List<Agent> matchAgents(String text) {
        List<Agent> agents = agentDao.getBySubnames(extractSubnames(text));
        return ObjectTools.reduceAll(agents, "id", "name", "nscNumber", "description");
    }

    public Integer getDiseaseFromStudyDisease(String studyDiseaseId) {
        CtepStudyDisease ctepStudyDisease = ctepStudyDiseaseDao.getById(Integer.parseInt(studyDiseaseId));
        return ctepStudyDisease.getTerm().getId();
    }


    public User getResearchStaff(String text) {
        User user = userDao.getById(Integer.parseInt(text));
        return reduce(user, "id", "firstName", "lastName", "middleName", "emailAddress", "phoneNumber", "faxNumber");
    }
    
    public User getInvestigator(String text){
    	User user = userDao.getById(Integer.parseInt(text));
        return reduce(user, "id", "firstName", "lastName", "middleName", "emailAddress", "phoneNumber", "faxNumber");
    }
    
    public List<ParticipantAjaxableDomainObject> matchParticipants(String text, Integer studyId) {

        ParticipantAjaxableDomainObjectQuery query = new ParticipantAjaxableDomainObjectQuery();
        query.filterParticipantsWithMatchingText(text);
        query.filterByStudy(studyId);

        List<ParticipantAjaxableDomainObject> participantAjaxableDomainObjects = participantAjaxableDomainObjectRepository.findParticipants(query);
        return participantAjaxableDomainObjects;
    }


    /*
    * The extra condition "o.status <> 'Administratively Complete'" as fix for bug 9514
    */


    public List<StudyAjaxableDomainObject> matchStudies(String text, Integer participantId, boolean ignoreCompletedStudy) {

        StudySearchableAjaxableDomainObjectQuery domainObjectQuery = new StudySearchableAjaxableDomainObjectQuery();
        domainObjectQuery.filterStudiesWithMatchingText(text);
        if (participantId != null) {
            domainObjectQuery.filterByParticipant(participantId);
        }
        domainObjectQuery.filterByStudyStatus(ignoreCompletedStudy);
        List<StudyAjaxableDomainObject> studies = studySearchableAjaxableDomainObjectRepository.findStudies(domainObjectQuery);
        return studies;
    }


    public List<CtcTerm> matchTerms(String text, Integer ctcVersionId, Integer ctcCategoryId, int limit) throws Exception {
        List<CtcTerm> terms = ctcTermDao.getBySubname(extractSubnames(text), ctcVersionId, ctcCategoryId);
        // cut down objects for serialization
        for (CtcTerm term : terms) {
            term.getCategory().setTerms(null);
            term.getCategory().getCtc().setCategories(null);
        }
        while (terms.size() > limit) {
            terms.remove(terms.size() - 1);
        }
        return terms;
    }

    public List<CtcTerm> getTermsByCategory(Integer ctcCategoryId) throws Exception {
        List<CtcTerm> terms = null;//ctcCategoryDao.getById(ctcCategoryId).getTerms();

        // from rules UI page , if user selects terms without category a fabricated Id 0 is passed.
        // get all terms incase of this special condition -- srini
        if (ctcCategoryId == 0) {
            terms = ctcTermDao.getAll();
        } else {
            terms = ctcCategoryDao.getById(ctcCategoryId).getTerms();
        }

        // cut down objects for serialization
        for (CtcTerm term : terms) {
            term.getCategory().setTerms(null);
            term.getCategory().getCtc().setCategories(null);
        }
        return terms;
    }

    public List<CtcTerm> getTermByTermId(String ctcTermId) throws Exception {
        List<CtcTerm> terms = new ArrayList<CtcTerm>();
        CtcTerm ctcTerm = ctcTermDao.getById(Integer.parseInt(ctcTermId));
        ctcTerm.getCategory().setTerms(null);
        ctcTerm.getCategory().getCtc().setCategories(null);
        terms.add(ctcTerm);

        return terms;
    }

    public String buildTermsTableByCategory(final Map parameterMap, Integer ctcCategoryId, String tableId, HttpServletRequest request) throws Exception {
        if (ctcCategoryId == null || ctcCategoryId == 0) {
            return "";
        }

        try {
            List<CtcTerm> terms = getTermsByCategory(ctcCategoryId);
            TableModel model = getTableModel(parameterMap, request);
            String onInvokeAction = "buildTable('command'," + ctcCategoryId.intValue() + ",'" + tableId + "')";

            addTableAndRowToModel(model, tableId, terms, onInvokeAction);

            Column columnTerm = model.getColumnInstance();
            columnTerm.setProperty("fullName");
            columnTerm.setTitle("CTC term");
            columnTerm.setCell("gov.nih.nci.cabig.caaers.web.search.CtcTermLinkDisplayCell");
            model.addColumn(columnTerm);


            return model.assemble().toString();


        }
        catch (Exception e) {
            log.error("error while retriving the ctc terms" + e.toString() + " message" + e.getMessage());
        }

        return "";

    }

    public List<CtcCategory> getCategories(int ctcVersionId) {
        List<CtcCategory> categories = ctcDao.getById(ctcVersionId).getCategories();
        // cut down objects for serialization
        for (CtcCategory category : categories) {
            category.setTerms(null);
        }
        return categories;
    }

    public List<CtcCategory> getCategoriesWithStudyShortTitle(String studyShortTitle) {

        Study s = studyDao.getByShortTitle(studyShortTitle);
        Ctc ctc = s.getAeTerminology().getCtcVersion();


        List<CtcCategory> categories = ctc.getCategories();
        // cut down objects for serialization
        for (CtcCategory category : categories) {
            category.setTerms(null);
        }
        return categories;
    }


    public List<? extends CodedGrade> getTermGrades(int ctcTermId) {
        List<CodedGrade> list = ctcTermDao.getById(ctcTermId).getGrades();
        // have to detect whether it's a collection of Grade or CtcGrade;
        // if the latter, need to call reduce
        if (list.size() == 0) {
            return list;
        } else if (list.get(0) instanceof Grade) {
            return list;
        } else {
            return reduceAll(list, "grade", "text");
        }
    }

    public List<LabTerm> matchLabTerms(String text, Integer labCategoryId) {
        List<LabTerm> terms = labTermDao.getBySubname(extractSubnames(text), labCategoryId);
        // cut down objects for serialization
        for (LabTerm term : terms) {
            term.getCategory().setTerms(null);
            term.getCategory().getLabVersion().setCategories(null);
        }
        return terms;
    }

    public LabTerm getLabTermById(String labTermId) throws Exception {
        LabTerm labTerm = labTermDao.getById(Integer.parseInt(labTermId));
        // cut down objects for serialization
        labTerm.getCategory().setTerms(null);
        labTerm.getCategory().getLabVersion().setCategories(null);

        return labTerm;
    }

    public String buildLabTermsTable(final Map parameterMap, String labCategoryId, String tableId, HttpServletRequest request) throws Exception {

        if (labCategoryId == null || labCategoryId.equalsIgnoreCase("")) {
            return "";
        }


        try {
            TableModel model = getTableModel(parameterMap, request);
            List<LabTerm> terms = getLabTermsByCategory(Integer.parseInt(labCategoryId));

            String onInvokeAction = "showLabsTable('" + labCategoryId + "','" + tableId + "')";

            addTableAndRowToModel(model, tableId, terms, onInvokeAction);

            Column columnTerm = model.getColumnInstance();
            columnTerm.setProperty("term");
            columnTerm.setTitle("Lab test name");
            columnTerm.setCell("gov.nih.nci.cabig.caaers.web.search.link.LabTermLinkDisplayCell");
            model.addColumn(columnTerm);


            return model.assemble().toString();

        }
        catch (Exception e) {
            log.error("error while retriving the lab terms" + e.toString() + " message" + e.getMessage());
        }

        return "";

    }


    private void addTableAndRowToModel(final TableModel model, final String tableId, final Object items, final String onInvokeAction) {
        Table table = model.getTableInstance();
        table.setForm("command");
        table.setTableId(tableId);
        table.setTitle("");
        table.setAutoIncludeParameters(Boolean.FALSE);
        table.setImagePath(model.getContext().getContextPath() + "/images/table/*.gif");
        table.setFilterable(false);
        table.setSortable(false);
        table.setShowPagination(true);
        table.setItems(items);
        table.setSortRowsCallback("gov.nih.nci.cabig.caaers.web.table.SortRowsCallbackImpl");
                
        table.setOnInvokeAction(onInvokeAction);
        model.addTable(table);

        Row row = model.getRowInstance();
        row.setHighlightRow(Boolean.TRUE);
        model.addRow(row);
    }


    public List<LabTerm> getLabTermsByCategory(Integer labCategoryId) {
        List<LabTerm> terms;
        if (labCategoryId == 0) {
            terms = labTermDao.getAll();
        } else {
            terms = labCategoryDao.getById(labCategoryId).getTerms();
        }
        // cut down objects for serialization
        for (LabTerm term : terms) {
            term.getCategory().setTerms(null);
            term.getCategory().getLabVersion().setCategories(null);
        }
        return terms;
    }

    public List<LabCategory> getLabCategories() {
        List<LabCategory> categories = labCategoryDao.getAll();
        // cut down objects for serialization
        for (LabCategory category : categories) {
            category.setTerms(null);
        }
        return categories;
    }

    //will return the labTestNamesRefData Lov's matching the testName.
    public List<Lov> matchLabTestNames(String testName) {
        List<Lov> lovs = new ArrayList<Lov>();
        for (Lov lov : configProperty.getMap().get("labTestNamesRefData")) {
            if (StringUtils.containsIgnoreCase(lov.getDesc(), testName)) lovs.add(lov);
        }
        return ObjectTools.reduceAll(lovs, "code", "desc");
    }

    public List<TreatmentAssignment> matchTreatmentAssignment(String text, int studyId) {
        List<TreatmentAssignment> treatmentAssignments = treatmentAssignmentDao.getAssignmentsByStudyId(text, studyId);
        return ObjectTools.reduceAll(treatmentAssignments, "id", "code", "description");
    }

    private String[] extractSubnames(String text) {
        return text.split("\\s+");
    }

    public boolean pushAdverseEventToStudyCalendar(int aeReportId) {
        ExpeditedAdverseEventReport report = aeReportDao.getById(aeReportId);
        try {
            interoperationService.pushToStudyCalendar(report);
            return true;
        } catch (CaaersSystemException ex) {
            log.warn("Interoperation Service, is not working properly", ex);
            // this happens if the interoperationService isn't correctly configured
            return false;
        } catch (RuntimeException re) {
            log.error("Unexpected error in communicating with study calendar", re);
            return false;
        }
    }

    public boolean pushRoutineAdverseEventToStudyCalendar(int aeReportId) {
        if (true)
            throw new UnsupportedOperationException("No more supported");
        return false;
    }

    public String withdrawReportVersion(int aeReportId, int reportId) {
        ExpeditedAdverseEventReport aeReport = aeReportDao.getById(aeReportId);
        for (Report report : aeReport.getReports()) {
            if (report.getId().equals(reportId) && !report.getLastVersion().getReportStatus().equals(ReportStatus.COMPLETED)) {
                reportRepository.deleteReport(report);
                break;
            }
        }
        return "Success";
    }

    /**
     * Generic method which returns the contents of a JSP form section for the given
     * named section.
     */
    public String addFormSection(String name, int index, Integer aeReportId) {
        return renderIndexedAjaxView(name + "FormSection", index, aeReportId);
    }

    /**
     * Returns the HTML for the section of the basic AE entry form for
     * the adverse event with the given index
     *
     * @param index
     * @return
     */
    public String addRoutineAeMeddra(int index, Integer aeReportId) {
        return renderIndexedAjaxView("routineAdverseEventMeddraFormSection", index, aeReportId);
    }

    /**
     * Returns the HTML for the section of the other causes form for
     * the other cause with the given index
     *
     * @param index
     * @return
     */
    public String addPriorTherapy(int index, Integer aeReportId) {
        return renderIndexedAjaxView("priorTherapyFormSection", index, aeReportId);
    }

    /**
     * Returns the HTML for the section of the other causes form for
     * the other cause with the given index
     *
     * @param index
     * @return
     */
    public String addPriorTherapyAgent(int index, int parentIndex, Integer aeReportId) {
        return renderIndexedAjaxView("priorTherapyAgentFormSection", index, parentIndex, aeReportId);
    }

    public double calculateBodySurfaceArea(double ht, String htUOM, double wt, String wtUOM) {
        return ParticipantHistory.bodySuraceArea(ht, htUOM, wt, wtUOM);
    }
    
    public String addAdverseEvent( int index, Integer aeReportId ){
    	return addNewAdverseEvent("adverseEventFormSection", index, aeReportId);
    }
    
    public String addAdverseEventMeddra(int index, Integer aeReportId){
    	return addNewAdverseEvent("adverseEventMeddraFormSection", index, aeReportId);
    }
    
    public String addNewAdverseEvent(String section, int index, Integer aeReportId){
    	try {
			Object cmd = extractCommand();
			ExpeditedAdverseEventInputCommand command = (ExpeditedAdverseEventInputCommand) cmd;
			
			command.reassociate();
			
			//create a new adverse event. 
			AdverseEvent ae = new AdverseEvent();
			ae.setReport(command.getAeReport());
			command.getAeReport().getReportingPeriod().addAdverseEvent(ae);
			command.getAeReport().addAdverseEvent(ae);
			
			command.updateOutcomes();
			command.saveReportingPeriod();
			saveIfAlreadyPersistent(command);
			
			return renderIndexedAjaxView(section, index, aeReportId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
    }

    /**
     * Reorders the list property of the current session command, moving the element at
     * <code>objectIndex</code> to <code>targetIndex</code> and shifting everything else
     * around as appropriate.
     * <p/>
     * <p>
     * Note that other than the extract command bit, this is entirely non-ae-flow-specific.
     * </p>
     *
     * @return A list of changes indicating which elements of the list were moved and where to.
     *         This list will be empty if the requested change is invalid or if the change is a no-op.
     */
    @SuppressWarnings({"unchecked"})
    public AjaxOutput reorder(String listProperty, int objectIndex, int targetIndex) {
    	List<IndexChange> changes = null;
    	boolean changesApplied =false;
    	 List<Object> list = null;
    	try {
    	Object cmd = extractCommand();
    	ExpeditedAdverseEventInputCommand command = (ExpeditedAdverseEventInputCommand) cmd;
        list = (List<Object>) new BeanWrapperImpl(command).getPropertyValue(listProperty);
        if (targetIndex >= list.size()) {
            log.debug("Attempted to move past the end; " + targetIndex + " >= " + list.size());
            return new AjaxOutput("Unable to reorder. Attempted to delete beyond the end; " + targetIndex + " >= " + list.size());
        }
        if (targetIndex < 0) {
            log.debug("Attempted to move past the start; " + targetIndex + " < 0");
            return new AjaxOutput("Unable to reorder. Attempted to move past the start; " + targetIndex + " < 0");
        }
        if (objectIndex == targetIndex) {
            log.debug("No move requested; " + objectIndex + " == " + targetIndex);
            return new AjaxOutput();
        }
        if (0 > objectIndex || objectIndex >= list.size()) {
            log.debug("No " + listProperty + " with index " + objectIndex);
            return new AjaxOutput();
        }
        
        command.reassociate();
        
        Object o = list.remove(objectIndex);
        list.add(targetIndex, o);
        changesApplied = true;
        
        changes = createMoveChangeList(objectIndex, targetIndex);
        addDisplayNames(listProperty, changes);
        
            saveIfAlreadyPersistent(command);
        } catch (OptimisticLockingFailureException ole) {
            log.error("Error occured while reordering [listProperty :" + listProperty +
                    ", objectIndex :" + targetIndex +
                    ", targetIndex :" + targetIndex + "]", ole);
            return new AjaxOutput("Unable to reorder at this point. The same data is being modified by someone else, please restart the page flow");
        }catch(Exception e){
        	log.error("Error occured while moving", e);
        	//revert the changes if they are applied.
        	if(changesApplied){
        		Object o = list.remove(targetIndex);
        		list.add(objectIndex, o);
        	}
        	return new AjaxOutput("Unable to re-order, please try again after saving the report");
        }
        return new AjaxOutput(changes);
    }

    private List<IndexChange> createMoveChangeList(int original, int target) {
        List<IndexChange> list = new ArrayList<IndexChange>();
        if (original < target) {
            list.add(new IndexChange(original, target));
            for (int i = original + 1; i <= target; i++) {
                list.add(new IndexChange(i, i - 1));
            }
        } else {
            for (int i = target; i < original; i++) {
                list.add(new IndexChange(i, i + 1));
            }
            list.add(new IndexChange(original, target));
        }
        return list;
    }

  

    /**
     * Deletes an element in a list property of the current session command, shifting everything
     * else around as appropriate.
     * <p/>
     * <p>
     * Note that other than the extract command bit, this is entirely non-ae-flow-specific.
     * </p>
     *
     * @return A list of changes indicating which elements of the list were moved and where to.
     *         This list will be empty if the requested change is invalid or if the change is a no-op.
     *         The element to remove will be represented by a move to a negative index.
     */
    @SuppressWarnings({"unchecked"})
    public AjaxOutput remove(String listProperty, int indexToDelete) {
        ExpeditedAdverseEventInputCommand command = (ExpeditedAdverseEventInputCommand) extractCommand();
        command.reassociate(); //reassociate to session
        command.getStudy(); //this is to fix the LazyInit execption on "Save&Continue" after a delete(GForge #11981, comments has the details) 
        List<Object> list = (List<Object>) new BeanWrapperImpl(command).getPropertyValue(listProperty);
        if (indexToDelete >= list.size()) {
            log.debug("Attempted to delete beyond the end; " + indexToDelete + " >= " + list.size());
            return new AjaxOutput("Unable to delete. Attempted to delete beyond the end; " + indexToDelete + " >= " + list.size());
        }
        if (indexToDelete < 0) {
            log.debug("Attempted to delete from an invalid index; " + indexToDelete + " < 0");
            return new AjaxOutput("Unable to delete. Attempted to delete beyond the end; " + indexToDelete + " >= " + list.size());
        }
        List<IndexChange> changes = createDeleteChangeList(indexToDelete, list.size());
        Object removedObject = list.get(indexToDelete);
        aeReportDao.cascaeDeleteToAttributions((DomainObject) removedObject, command.getAeReport());
        list.remove(indexToDelete);

        if (removedObject instanceof ExpeditedAdverseEventReportChild) {
            ExpeditedAdverseEventReportChild removedAEChild = (ExpeditedAdverseEventReportChild) removedObject;
            removedAEChild.setReport(null);
        }

        addDisplayNames(listProperty, changes);
        try {
            saveIfAlreadyPersistent(command);
        } catch (DataIntegrityViolationException die) {
            log.error("Error occured while deleting [listProperty :" + listProperty +
                    ", indexToDelete :" + indexToDelete +
                    "]", die);
            return new AjaxOutput("Unable to delete. The object being removed is referenced elsewhere.");
        } catch (OptimisticLockingFailureException ole) {
            log.error("Error occured while deleting [listProperty :" + listProperty +
                    ", indexToDelete :" + indexToDelete +
                    "]", ole);
            return new AjaxOutput("Unable to delete. The same data is being modified by someone else, please restart the page flow.");
        }
        return new AjaxOutput(changes);
    }

    private List<IndexChange> createDeleteChangeList(int indexToDelete, int length) {
        List<IndexChange> changes = new ArrayList<IndexChange>();
        changes.add(new IndexChange(indexToDelete, null));
        for (int i = indexToDelete + 1; i < length; i++) {
            changes.add(new IndexChange(i, i - 1));
        }
        return changes;
    }

    private void addDisplayNames(String listProperty, List<IndexChange> changes) {
        TreeNode listNode = expeditedReportTree.find(listProperty.split("\\.", 2)[1]);
        for (IndexChange change : changes) {
            if (change.getCurrent() != null) {
                change.setCurrentDisplayName(listNode.getDisplayName(change.getCurrent()));
            }
        }
    }

    private void saveIfAlreadyPersistent(ExpeditedAdverseEventInputCommand command) {
        if (command.getAeReport().getId() != null) {
            aeReportDao.save(command.getAeReport());
        }
    }

    protected String renderIndexedAjaxView(String viewName, int index, Integer aeReportId) {
        return renderIndexedAjaxView(viewName, index, null, aeReportId);
    }

    protected String renderIndexedAjaxView(String viewName, int index, Integer parentIndex, Integer aeReportId) {
        Map<String, String> params = new LinkedHashMap<String, String>(); // preserve order for testing
        params.put("index", Integer.toString(index));
        if (parentIndex != null) params.put("parentIndex", Integer.toString(parentIndex));
        return renderAjaxView(viewName, aeReportId, params);
    }


    protected String renderAjaxView(String viewName, Integer aeReportId, Map<String, String> params) {
        WebContext webContext = WebContextFactory.get();

        if (aeReportId != null) params.put("aeReport", aeReportId.toString());
        params.put(CaptureAdverseEventController.AJAX_SUBVIEW_PARAMETER, viewName);

        String url = String.format("%s?%s",
                getCurrentPageContextRelative(webContext), createQueryString(params));
        log.debug("Attempting to return contents of " + url);
        try {
            String html = webContext.forwardToString(url);
            if (log.isDebugEnabled()) log.debug("Retrieved HTML:\n" + html);
            return html;
        } catch (ServletException e) {
            throw new CaaersSystemException(e);
        } catch (IOException e) {
            throw new CaaersSystemException(e);
        }
    }
    
    protected WebContext getWebContext(){
    	return WebContextFactory.get();
    }

    protected Object extractCommand() {
        WebContext webContext = getWebContext();
        Object command = null;
        for (Class<?> controllerClass : controllers()) {
            String formSessionAttributeName = controllerClass.getName() + ".FORM.command";
            command = webContext.getSession().getAttribute(formSessionAttributeName);
            if (command == null) {
                log.debug("Command not found using name " + formSessionAttributeName);
            } else {
                log.debug("Command found using name " + formSessionAttributeName);
                break;
            }
        }
        if (command == null) {
            throw new CaaersSystemException("Could not find command in session");
        } else {
            return command;
        }
    }

    private String getCurrentPageContextRelative(WebContext webContext) {
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
    
    // For RoutingAndReview - Report comments.
    
    public AjaxOutput addReviewComment(String comment){
    	ExpeditedAdverseEventInputCommand command = (ExpeditedAdverseEventInputCommand) extractCommand();
    	command.reassociate();
    	command.getStudy();
    	String userId = getUserId();
    	adverseEventRoutingAndReviewRepository.addReportReviewComment(command.getAeReport(), comment, userId);
    	
        return fetchPreviousComments(command.getAeReport().getId(), userId);
    }
    
    public AjaxOutput editReviewComment(String comment, Integer commentId){
    	ExpeditedAdverseEventInputCommand command = (ExpeditedAdverseEventInputCommand) extractCommand();
    	command.reassociate();
    	String userId = getUserId();
    	adverseEventRoutingAndReviewRepository.editReportReviewComment(command.getAeReport(), comment, userId, commentId);
    	return fetchPreviousComments(command.getAeReport().getId(), getUserId());
    }
    
    public AjaxOutput fetchPreviousComments(Integer entityId, String userId){
		Map params = new HashMap<String, String>();
		params.put(RoutingAndReviewCommentController.AJAX_ENTITY, "aeReport");
        params.put(RoutingAndReviewCommentController.AJAX_ENTITY_ID, entityId.toString());
        params.put("userId", userId);
        params.put(RoutingAndReviewCommentController.AJAX_ACTION, "fetchComments");
        params.put(CaptureAdverseEventController.AJAX_SUBVIEW_PARAMETER, "reviewCommentsList");
        AjaxOutput output = new AjaxOutput();
		String html =  renderCommentsAjaxView(params);
		output.setHtmlContent(html);
		return output;
	}
    
    public AjaxOutput retrieveReviewComments(){
    	ExpeditedAdverseEventInputCommand command = (ExpeditedAdverseEventInputCommand) extractCommand();
    	command.reassociate();
    	return fetchPreviousComments(command.getAeReport().getId(), getUserId());
    }
    
    public AjaxOutput retrieveNextTransitions(){
    	ExpeditedAdverseEventInputCommand command = (ExpeditedAdverseEventInputCommand) extractCommand();
    	List<String> transitions = new ArrayList<String>();
    	if(command.getAeReport().getWorkflowId() != null){
    		transitions = adverseEventRoutingAndReviewRepository.nextTransitionNames(command.getAeReport().getWorkflowId(), getUserId());
    	}
    	AjaxOutput output = new AjaxOutput();
    	output.setObjectContent(transitions.toArray());
    	return output;
    }
    
    public AjaxOutput retrieveReviewCommentsAndActions(){
    	AjaxOutput output = retrieveReviewComments();
    	AjaxOutput transitionOutput = retrieveNextTransitions();
    	output.setObjectContent(transitionOutput.getObjectContent());
    	return output;
    	
    }
    
    public AjaxOutput advanceWorkflow(String transitionToTake){
    	ExpeditedAdverseEventInputCommand command = (ExpeditedAdverseEventInputCommand) extractCommand();
    	command.reassociate();
    	List<String> transitions = adverseEventRoutingAndReviewRepository.advanceReportWorkflow(command.getAeReport().getWorkflowId(), 
    			transitionToTake, command.getAeReport(), getUserId());
    	AjaxOutput output = new AjaxOutput();
    	output.setObjectContent(transitions.toArray());
    	return output;
    }
    
    protected String renderCommentsAjaxView(Map<String, String> params){
    	WebContext webContext = getWebContext();
    	String url = String.format("%s?%s",
    			"/pages/ae/listReviewComments", createQueryString(params));
        try {
            String html = webContext.forwardToString(url);
            return html;
        } catch (ServletException e) {
            throw new CaaersSystemException(e);
        } catch (IOException e) {
            throw new CaaersSystemException(e);
        }
    }
    
    protected String getUserId(){
		WebContext webContext = getWebContext();
		SecurityContext context = (SecurityContext)webContext.getHttpServletRequest().getSession().getAttribute("ACEGI_SECURITY_CONTEXT");
		String userId = ((org.acegisecurity.userdetails.User)context.getAuthentication().getPrincipal()).getUsername();
		return userId;
	}

    // For RoutingAndReview - Report comments ends here.


    // TODO: there's got to be a library version of this somewhere
    protected String createQueryString(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(entry.getKey()).append('=').append(entry.getValue())
                    .append('&');
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    //--------------- functionality added for Labviewr integration -------------------------
    public void dismissLab(int labId) {
        LabLoad labLoad = labLoadDao.getById(labId);
        if (labLoad != null) {
            labLoad.setDismissed(Boolean.TRUE);
            labLoadDao.save(labLoad);
        }
    }


    ////// CONFIGURATION

    @Required
    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    @Required
    public void setParticipantDao(final ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }
    
    @Required
    public void setAssignmentDao(StudyParticipantAssignmentDao assignmentDao){
    	this.assignmentDao = assignmentDao;
    }


    @Required
    public void setCtcDao(CtcDao ctcDao) {
        this.ctcDao = ctcDao;
    }

    @Required
    public void setCtcTermDao(CtcTermDao ctcTermDao) {
        this.ctcTermDao = ctcTermDao;
    }

    @Required
    public void setAeReportDao(ExpeditedAdverseEventReportDao aeReportDao) {
        this.aeReportDao = aeReportDao;
    }

    @Required
    public void setInteroperationService(InteroperationService interoperationService) {
        this.interoperationService = interoperationService;
    }

    @Required
    public void setAnatomicSiteDao(AnatomicSiteDao anatomicSiteDao) {
        this.anatomicSiteDao = anatomicSiteDao;
    }

    @Required
    public void setPriorTherapyDao(PriorTherapyDao priorTherapyDao) {
        this.priorTherapyDao = priorTherapyDao;
    }

    @Required
    public void setCtcCategoryDao(CtcCategoryDao ctcCategoryDao) {
        this.ctcCategoryDao = ctcCategoryDao;
    }

    @Required
    public void setLowLevelTermDao(LowLevelTermDao lowLevelTermDao) {
        this.lowLevelTermDao = lowLevelTermDao;
    }

    @Required
    public void setPreExistingConditionDao(PreExistingConditionDao preExistingConditionDao) {
        this.preExistingConditionDao = preExistingConditionDao;
    }

    @Required
    public void setAgentDao(AgentDao agentDao) {
        this.agentDao = agentDao;
    }

    @Required
    public void setExpeditedReportTree(ExpeditedReportTree expeditedReportTree) {
        this.expeditedReportTree = expeditedReportTree;
    }

    @Required
    public ConfigProperty getConfigurationProperty() {
        return configProperty;
    }

    public void setConfigurationProperty(ConfigProperty configProperty) {
        this.configProperty = configProperty;
    }

    @Required
    public TreatmentAssignmentDao getTreatmentAssignmentDao() {
        return treatmentAssignmentDao;
    }

    public void setTreatmentAssignmentDao(TreatmentAssignmentDao treatmentAssignmentDao) {
        this.treatmentAssignmentDao = treatmentAssignmentDao;
    }

    @Required
    public void setReportRepository(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }


    @Required
    public LabCategoryDao getLabCategoryDao() {
        return labCategoryDao;
    }

    public void setLabCategoryDao(LabCategoryDao labCategoryDao) {
        this.labCategoryDao = labCategoryDao;
    }

    @Required
    public LabTermDao getLabTermDao() {
        return labTermDao;
    }

    public void setLabTermDao(LabTermDao labTermDao) {
        this.labTermDao = labTermDao;
    }

    @Required
    public ChemoAgentDao getChemoAgentDao() {
        return chemoAgentDao;
    }

    public void setChemoAgentDao(ChemoAgentDao chemoAgentDao) {
        this.chemoAgentDao = chemoAgentDao;
    }

    @Required
    public InterventionSiteDao getInterventionSiteDao() {
        return interventionSiteDao;
    }

    public void setInterventionSiteDao(InterventionSiteDao interventionSiteDao) {
        this.interventionSiteDao = interventionSiteDao;
    }

    @Required
    public CtepStudyDiseaseDao getCtepStudyDiseaseDao() {
        return ctepStudyDiseaseDao;
    }

    public void setCtepStudyDiseaseDao(CtepStudyDiseaseDao ctepStudyDiseaseDao) {
        this.ctepStudyDiseaseDao = ctepStudyDiseaseDao;
    }

    private HttpServletRequest getHttpServletRequest() {
        return WebContextFactory.get().getHttpServletRequest();
    }

    public AdverseEventReportingPeriodDao getReportingPeriodDao() {
        return reportingPeriodDao;
    }

    public void setReportingPeriodDao(
            AdverseEventReportingPeriodDao reportingPeriodDao) {
        this.reportingPeriodDao = reportingPeriodDao;
    }

    public void setLabLoadDao(LabLoadDao labLoadDao) {
        this.labLoadDao = labLoadDao;
    }

    public AdverseEventRoutingAndReviewRepository getAdverseEventRoutingAndReviewRepository() {
		return adverseEventRoutingAndReviewRepository;
    }

    public void setAdverseEventRoutingAndReviewRepository(
			AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository) {
		this.adverseEventRoutingAndReviewRepository = adverseEventRoutingAndReviewRepository;
    }

    @Required
    public void setStudySearchableAjaxableDomainObjectRepository(StudySearchableAjaxableDomainObjectRepository studyAjaxableDomainObjectRepository) {
        this.studySearchableAjaxableDomainObjectRepository = studyAjaxableDomainObjectRepository;
    }

    @Required
    public void setParticipantAjaxableDomainObjectRepository(ParticipantAjaxableDomainObjectRepository participantAjaxableDomainObjectRepository) {
        this.participantAjaxableDomainObjectRepository = participantAjaxableDomainObjectRepository;
    }

    public ConditionDao getConditionDao() {
        return conditionDao;
    }
    
    @Required
    public void setConditionDao(ConditionDao conditionDao) {
        this.conditionDao = conditionDao;
    }
    @Required
    public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
