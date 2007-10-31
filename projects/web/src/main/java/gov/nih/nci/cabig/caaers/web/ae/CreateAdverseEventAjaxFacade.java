package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.AnatomicSiteDao;
import gov.nih.nci.cabig.caaers.dao.CtcCategoryDao;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.PreExistingConditionDao;
import gov.nih.nci.cabig.caaers.dao.PriorTherapyDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.TreatmentAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.AnatomicSite;
import gov.nih.nci.cabig.caaers.domain.CodedGrade;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;
import gov.nih.nci.cabig.caaers.domain.PriorTherapy;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.service.ReportService;
import gov.nih.nci.cabig.caaers.service.InteroperationService;
import gov.nih.nci.cabig.caaers.tools.ObjectTools;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import static gov.nih.nci.cabig.caaers.tools.ObjectTools.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.ArrayList;

/**
 * @author Rhett Sutphin
 */
public class CreateAdverseEventAjaxFacade {
    private static final Log log = LogFactory.getLog(CreateAdverseEventAjaxFacade.class);
    private static Class<?>[] CONTROLLERS = {
        CreateAdverseEventController.class, EditAdverseEventController.class
    };

    private StudyDao studyDao;
    private ParticipantDao participantDao;
    private CtcTermDao ctcTermDao;
    private CtcCategoryDao ctcCategoryDao;
    private CtcDao ctcDao;
    private LowLevelTermDao lowLevelTermDao;
    private ExpeditedAdverseEventReportDao aeReportDao;
    private ResearchStaffDao researchStaffDao;
    private AnatomicSiteDao anatomicSiteDao;
    private InteroperationService interoperationService;
    private PriorTherapyDao priorTherapyDao;
    private PreExistingConditionDao preExistingConditionDao;
    private AgentDao agentDao;
    private TreatmentAssignmentDao treatmentAssignmentDao;
    private ExpeditedReportTree expeditedReportTree;
    private ConfigProperty configProperty;
    private ReportService reportService;

    public List<AnatomicSite> matchAnatomicSite(String text) {
        return anatomicSiteDao.getBySubnames(extractSubnames(text));
    }

    public List<PriorTherapy> matchPriorTherapies(String text) {
        return priorTherapyDao.getBySubnames(extractSubnames(text));
    }

    public List<PreExistingCondition> matchPreExistingConds(String text) {
        return preExistingConditionDao.getBySubnames(extractSubnames(text));
    }

    public List<LowLevelTerm> matchLowLevelTermsByCode(String text) {
        return lowLevelTermDao.getBySubnames(extractSubnames(text));
    }

    public List<Agent> matchAgents(String text) {
        List<Agent> agents = agentDao.getBySubnames(extractSubnames(text));
        return ObjectTools.reduceAll(agents,"id", "name", "nscNumber","description");
    }


    public ResearchStaff getResearchStaff(String text) {
        ResearchStaff researchStaff = researchStaffDao.getById(Integer.parseInt(text));
        return reduce(researchStaff, "id", "firstName", "lastName", "middleName", "emailAddress", "phoneNumber", "faxNumber");
    }

    public List<Participant> matchParticipants(String text, Integer studyId) {
    	List<Participant> participants;
    	if (studyId == null){
    		participants = participantDao.getBySubnamesJoinOnIdentifier(extractSubnames(text));
    	}
    	else {
    		participants = participantDao.matchParticipantByStudy(studyId, text);
    	}
        // cut down objects for serialization
        return reduceAll(participants, "firstName", "lastName", "id", "primaryIdentifierValue");
    }

    /* Depracated and replace by a hql based query to enhance performance
    public List<Participant> matchParticipants(String text, Integer studyId) {
        List<Participant> participants = participantDao.getBySubnames(extractSubnames(text));
        if (studyId != null) {
            for (Iterator<Participant> it = participants.iterator(); it.hasNext();) {
                Participant participant = it.next();
                if (!onStudy(participant, studyId)) it.remove();
            }
        }
        // cut down objects for serialization
        return reduceAll(participants, "firstName", "lastName", "id");
    }
    */

    private boolean onStudy(Participant participant, Integer studyId) {
        boolean onStudy = false;
        for (StudyParticipantAssignment assignment : participant.getAssignments()) {
            if (assignment.getStudySite().getStudy().getId().equals(studyId)) {
                onStudy = true;
                break;
            }
        }
        return onStudy;
    }

    /* Depracated and replace by a hql based query to enhance performance
    public List<Study> matchStudies(String text, Integer participantId) {
        List<Study> studies = studyDao.getBySubnames(extractSubnames(text));
        if (participantId != null) {
            for (Iterator<Study> it = studies.iterator(); it.hasNext();) {
                Study study = it.next();
                if (!onStudy(study, participantId)) it.remove();
            }
        }
        // cut down objects for serialization
        return reduceAll(studies, "id", "shortTitle");
    }
    */
    /*
     * The extra condition "o.status <> 'Administratively Complete'" as fix for bug 9514
     */
    public List<Study> matchStudies(String text, Integer participantId, boolean ignoreCompletedStudy) {
    	List<Study> studies ;
    	if (participantId == null){
    		studies = studyDao.getBySubnamesJoinOnIdentifier(extractSubnames(text),
    				(ignoreCompletedStudy)? "o.status <> '" + Study.STATUS_ADMINISTRATIVELY_COMPLETE +"'":null);
    	}
    	else{
    		studies = studyDao.matchStudyByParticipant(participantId, text, 
    				(ignoreCompletedStudy)? "o.status <> '" + Study.STATUS_ADMINISTRATIVELY_COMPLETE +"'":null);
    	}
        // cut down objects for serialization
        return reduceAll(studies, "id", "shortTitle", "primaryIdentifierValue");
    }

    private boolean onStudy(Study study, Integer participantId) {
        boolean onStudy = false;
        for (StudySite studySite : study.getStudySites()) {
            for (StudyParticipantAssignment assignment : studySite.getStudyParticipantAssignments()) {
                if (assignment.getParticipant().getId().equals(participantId)) {
                    onStudy = true;
                    break;
                }
            }
        }
        return onStudy;
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
        List<CtcTerm> terms;
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

    public List<CtcCategory> getCategories(int ctcVersionId) {
        List<CtcCategory> categories = ctcDao.getById(ctcVersionId).getCategories();
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

    //will return the labTestNamesRefData Lov's matching the testName.
    public List<Lov> matchLabTestNames(String testName){
    	List<Lov> lovs = new ArrayList<Lov>();
    	for(Lov lov : configProperty.getMap().get("labTestNamesRefData")){
    		if(lov.getDesc().contains(testName)) lovs.add(lov);
    	}
    	return ObjectTools.reduceAll(lovs, "code", "desc");
    }

    public List<TreatmentAssignment> matchTreatmentAssignment(String text, int studyId){
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
    
    public String withdrawReportVersion(int aeReportId, int reportId) {
        ExpeditedAdverseEventReport aeReport = aeReportDao.getById(aeReportId);
        for (Report report : aeReport.getReports()) {
        	if (report.getId().equals(reportId) && !report.getLastVersion().getReportStatus().equals(ReportStatus.COMPLETED)){
        		reportService.withdrawLastReportVersion(report);
     	        break;
     		}
		}
        aeReportDao.save(aeReport);
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
     * @param index
     * @return
     */
    public String addRoutineAeMeddra(int index, Integer aeReportId) {
        return renderIndexedAjaxView("routineAdverseEventMeddraFormSection", index, aeReportId);
    }

    /**
     * Returns the HTML for the section of the other causes form for
     * the other cause with the given index
     * @param index
     * @return
     */
    public String addPriorTherapy(int index, Integer aeReportId) {
        return renderIndexedAjaxView("priorTherapyFormSection", index, aeReportId);
    }

    /**
     * Returns the HTML for the section of the other causes form for
     * the other cause with the given index
     * @param index
     * @return
     */
    public String addPriorTherapyAgent(int index, int parentIndex, Integer aeReportId) {
        return renderIndexedAjaxView("priorTherapyAgentFormSection", index, parentIndex, aeReportId);
    }

    /**
     * Reorders the list property of the current session command, moving the element at
     * <code>objectIndex</code> to <code>targetIndex</code> and shifting everything else
     * around as appropriate.
     *
     * <p>
     * Note that other than the extract command bit, this is entirely non-ae-flow-specific.
     * </p>
     *
     * @return A list of changes indicating which elements of the list were moved and where to.
     *      This list will be empty if the requested change is invalid or if the change is a no-op.
     */
    @SuppressWarnings({ "unchecked" })
    public List<IndexChange> reorder(String listProperty, int objectIndex, int targetIndex) {
        Object command = extractCommand();
        List<Object> list = (List<Object>) new BeanWrapperImpl(command).getPropertyValue(listProperty);
        if (targetIndex >= list.size()) {
            log.debug("Attempted to move past the end; " + targetIndex + " >= " + list.size());
            return Collections.emptyList();
        }
        if (targetIndex < 0) {
            log.debug("Attempted to move past the start; " + targetIndex + " < 0");
            return Collections.emptyList();
        }
        if (objectIndex == targetIndex) {
            log.debug("No move requested; " + objectIndex + " == " + targetIndex);
            return Collections.emptyList();
        }
        if (0 > objectIndex || objectIndex >= list.size()) {
            log.debug("No " + listProperty + " with index " + objectIndex);
            return Collections.emptyList();
        }
        Object o = list.remove(objectIndex);
        list.add(targetIndex, o);
        List<IndexChange> changes = createMoveChangeList(objectIndex, targetIndex);
        addDisplayNames(listProperty, changes);
        saveIfAlreadyPersistent((ExpeditedAdverseEventInputCommand) command);
        return changes;
    }

    private List<IndexChange> createMoveChangeList(int original, int target) {
        List<IndexChange> list = new ArrayList<IndexChange>();
        if (original < target) {
            list.add(new IndexChange(original, target));
            for (int i = original + 1 ; i <= target ; i++) {
                list.add(new IndexChange(i, i - 1));
            }
        } else {
            for (int i = target ; i < original ; i++) {
                list.add(new IndexChange(i, i + 1));
            }
            list.add(new IndexChange(original, target));
        }
        return list;
    }

    /**
     * Deletes an element in a list property of the current session command, shifting everything
     * else around as appropriate.
     *
     * <p>
     * Note that other than the extract command bit, this is entirely non-ae-flow-specific.
     * </p>
     *
     * @return A list of changes indicating which elements of the list were moved and where to.
     *      This list will be empty if the requested change is invalid or if the change is a no-op.
     *      The element to remove will be represented by a move to a negative index.
     */
    @SuppressWarnings({ "unchecked" })
    public List<IndexChange> remove(String listProperty, int indexToDelete) {
        Object command = extractCommand();
        List<Object> list = (List<Object>) new BeanWrapperImpl(command).getPropertyValue(listProperty);
        if (indexToDelete >= list.size()) {
            log.debug("Attempted to delete beyond the end; " + indexToDelete + " >= " + list.size());
            return Collections.emptyList();
        }
        if (indexToDelete < 0) {
            log.debug("Attempted to delete from an invalid index; " + indexToDelete + " < 0");
            return Collections.emptyList();
        }
        List<IndexChange> changes = createDeleteChangeList(indexToDelete, list.size());
        list.remove(indexToDelete);
        addDisplayNames(listProperty, changes);
        saveIfAlreadyPersistent((ExpeditedAdverseEventInputCommand) command);
        return changes;
    }

    private List<IndexChange> createDeleteChangeList(int indexToDelete, int length) {
        List<IndexChange> changes = new ArrayList<IndexChange>();
        changes.add(new IndexChange(indexToDelete, null));
        for (int i = indexToDelete + 1 ; i < length ; i++) {
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

    private String renderIndexedAjaxView(String viewName, int index, Integer aeReportId) {
        return renderIndexedAjaxView(viewName, index, null, aeReportId);
    }

    private String renderIndexedAjaxView(String viewName, int index, Integer parentIndex, Integer aeReportId) {
        Map<String, String> params = new LinkedHashMap<String, String>(); // preserve order for testing
        params.put("index", Integer.toString(index));
        if (parentIndex != null) params.put("parentIndex", Integer.toString(parentIndex));
        return renderAjaxView(viewName, aeReportId, params);
    }

    private String renderAjaxView(String viewName, Integer aeReportId, Map<String, String> params) {
        WebContext webContext = WebContextFactory.get();

        if (aeReportId != null) params.put("aeReport", aeReportId.toString());
        params.put(AbstractAdverseEventInputController.AJAX_SUBVIEW_PARAMETER, viewName);

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

    private Object extractCommand() {
        WebContext webContext = WebContextFactory.get();
        Object command = null;
        for (Class<?> controllerClass : CONTROLLERS) {
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

    // TODO: there's got to be a library version of this somewhere
    private String createQueryString(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(entry.getKey()).append('=').append(entry.getValue())
                .append('&');
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    ////// CONFIGURATION

    @Required
    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    @Required
    public void setParticipantDao(ParticipantDao participantDao) {
        this.participantDao = participantDao;
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
    public void setResearchStaffDao(ResearchStaffDao researchStaffDao) {
        this.researchStaffDao = researchStaffDao;
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
    public void setPreExistingConditionDao(
        PreExistingConditionDao preExistingConditionDao) {
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
    public void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }

    public static class IndexChange {
        private Integer original, current;
        private String currentDisplayName;

        public IndexChange(Integer original, Integer current) {
            this.original = original;
            this.current = current;
        }

        public Integer getOriginal() {
            return original;
        }

        public Integer getCurrent() {
            return current;
        }

        public String getCurrentDisplayName() {
            return currentDisplayName;
        }

        public void setCurrentDisplayName(String currentDisplayName) {
            this.currentDisplayName = currentDisplayName;
        }

        @Override
        public String toString() {
            return String.format("%d => %d", original, current);
        }
    }
}
