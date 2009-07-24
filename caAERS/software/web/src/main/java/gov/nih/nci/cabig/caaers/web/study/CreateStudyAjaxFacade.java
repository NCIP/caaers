package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.DiseaseCategoryDao;
import gov.nih.nci.cabig.caaers.dao.DiseaseTermDao;
import gov.nih.nci.cabig.caaers.dao.InvestigationalNewDrugDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.SiteInvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.dao.query.ajax.StudySiteAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.dao.query.ajax.StudySearchableAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.DiseaseCategory;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerm;
import gov.nih.nci.cabig.caaers.domain.Epoch;
import gov.nih.nci.cabig.caaers.domain.ExpectedAECtcTerm;
import gov.nih.nci.cabig.caaers.domain.ExpectedAEMeddraLowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug;
import gov.nih.nci.cabig.caaers.domain.LocalInvestigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Retireable;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyAgentINDAssociation;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySiteAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudyAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.repository.InvestigatorRepository;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ResearchStaffRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.StudySiteAjaxableDomainObjectRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.StudySearchableAjaxableDomainObjectRepository;
import gov.nih.nci.cabig.caaers.tools.ObjectTools;
import gov.nih.nci.cabig.caaers.web.dwr.AjaxOutput;
import gov.nih.nci.cabig.caaers.web.dwr.IndexChange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.servlet.mvc.AbstractFormController;

/**
 * @author Rhett Sutphin
 * @author Krikor Krumlian
 * @author Ion C. Olaru
 * @author Biju Joseph
 */
public class CreateStudyAjaxFacade {

    public static final String AJAX_REQUEST_PARAMETER = "_isAjax";
    public static final String AJAX_INDEX_PARAMETER = "index";
    public static final String AJAX_SUBVIEW_PARAMETER = "_subview";
    public static final String CREATE_STUDY_FORM_NAME = CreateStudyController.class.getName() + ".FORM.command";
    public static final String EDIT_STUDY_FORM_NAME = EditStudyController.class.getName() + ".FORM.command";
    public static final String CREATE_STUDY_REPLACED_FORM_NAME = CREATE_STUDY_FORM_NAME + ".to-replace";
    public static final String EDIT_STUDY_REPLACED_FORM_NAME = EDIT_STUDY_FORM_NAME + ".to-replace";
    private static final Log log = LogFactory.getLog(CreateStudyAjaxFacade.class);

    private static Class<?>[] CONTROLLERS = {CreateStudyController.class, EditStudyController.class};
    public Class<?>[] controllers() {
        return CONTROLLERS;
    }
    
    private AgentDao agentDao;
    private DiseaseCategoryDao diseaseCategoryDao;
    private DiseaseTermDao diseaseTermDao;
    private SiteInvestigatorDao siteInvestigatorDao;
    private ResearchStaffDao researchStaffDao;
    private ResearchStaffRepository researchStaffRepository;
    private OrganizationDao organizationDao;
    private OrganizationRepository organizationRepository;
    private InvestigationalNewDrugDao investigationalNewDrugDao;
    private InvestigatorRepository investigatorRepository;
    private StudyDao studyDao;
    protected LowLevelTermDao lowLevelTermDao;
    protected CtcTermDao ctcTermDao;

    private StudySiteAjaxableDomainObjectRepository studySiteAjaxableDomainObjectRepository;
    private StudySearchableAjaxableDomainObjectRepository studySearchableAjaxableDomainObjectRepository;

    public List<SiteInvestigator> matchSiteInvestigator(final String text, final int indexId) {
        String[] arr = new String[] { text };
        StudyCommand studyCommand = getStudyCommand(getHttpServletRequest());
        int siteId = studyCommand.getStudy().getStudyOrganizations().get(indexId).getOrganization().getId();
        List<SiteInvestigator> siteInvestigators = investigatorRepository.getBySubnames(arr, siteId);
        return ObjectTools.reduceAll(siteInvestigators,
                        new ObjectTools.Initializer<SiteInvestigator>() {
                            public void initialize(final SiteInvestigator instance) {
                                instance.setInvestigator(new LocalInvestigator());
                            }
                        }, "id", "investigator.firstName", "investigator.lastName", "investigator.externalId");
    }

    public List<ResearchStaff> matchResearch(final String text, final int indexId) {

        StudyCommand command = getStudyCommand(getHttpServletRequest());
        int siteId = command.getStudy().getStudyOrganizations().get(indexId).getOrganization().getId();
        List<ResearchStaff> researchStaff = researchStaffRepository.getBySubnames(new String[] { text }, siteId);
        return ObjectTools.reduceAll(researchStaff, "id", "firstName", "lastName", "externalId");
    }

    private StudyCommand getStudyFromSession(final HttpServletRequest request) {

        StudyCommand command = (StudyCommand) request.getSession().getAttribute(CREATE_STUDY_REPLACED_FORM_NAME);

        if (command == null) {
            command = (StudyCommand) request.getSession().getAttribute(CREATE_STUDY_FORM_NAME);
        }

        if (command == null) {
            command = (StudyCommand) request.getSession().getAttribute(EDIT_STUDY_REPLACED_FORM_NAME);
        }

        if (command == null) {
            command = (StudyCommand) request.getSession().getAttribute(EDIT_STUDY_FORM_NAME);
        }

        return command;

    }

    private StudyCommand getStudyCommand(final HttpServletRequest request) {
        StudyCommand command = getStudyFromSession(request);
        request.setAttribute(AbstractFormController.DEFAULT_COMMAND_NAME, command);
        return command;
    }

    // TODO: Need to refactor this to a different class (may be a common super class)
    private void updateStudyCommand(HttpServletRequest request, StudyCommand studyCommand) {

        System.out.println("... updateStudyCommand.");
        if (studyCommand.getStudy().getId() == null) return;

        Study study = studyDao.getStudyDesignById(studyCommand.getStudy().getId());
        studyCommand.setStudy(study);

        StudyCommand command = (StudyCommand)request.getSession().getAttribute(CREATE_STUDY_REPLACED_FORM_NAME);
        if (command != null) {
            request.getSession().setAttribute(CREATE_STUDY_REPLACED_FORM_NAME, studyCommand);
            return;
        }

        command = (StudyCommand)request.getSession().getAttribute(CREATE_STUDY_FORM_NAME);
        if (command != null) {
            request.getSession().setAttribute(CREATE_STUDY_FORM_NAME, studyCommand);
            return;
        }

        command = (StudyCommand)request.getSession().getAttribute(EDIT_STUDY_REPLACED_FORM_NAME);
        if (command != null) {
            request.getSession().setAttribute(EDIT_STUDY_REPLACED_FORM_NAME, studyCommand);
            return;
        }
        
        command = (StudyCommand)request.getSession().getAttribute(EDIT_STUDY_FORM_NAME);
        if (command != null) {
            request.getSession().setAttribute(EDIT_STUDY_FORM_NAME, studyCommand);
            return;
        }
    }

    public List<StudySiteAjaxableDomainObject> matchSites(final String text, final Integer studyId){
    	if(studyId == null) 
    		return new ArrayList<StudySiteAjaxableDomainObject>();
    	
    	StudySiteAjaxableDomainObjectQuery query = new StudySiteAjaxableDomainObjectQuery();
    	query.filterByStudy(studyId);
    	return studySiteAjaxableDomainObjectRepository.findStudySites(query);
    	
    }
    
    public List<Agent> matchAgents(final String text) {
        List<Agent> agents = agentDao.getBySubnames(extractSubnames(text));
        return ObjectTools.reduceAll(agents, "id", "name", "nscNumber", "description");
    }

    public List<InvestigationalNewDrug> matchINDs(final String text) {
        List<InvestigationalNewDrug> inds = investigationalNewDrugDao
                        .findByIds(new String[] { text });
        return ObjectTools.reduceAll(inds, "id", "strINDNo", "holderName");
    }
    
    /**
     * This ajax call will set the data entry status on this study. 
     * Will save the study. 
     * @return
     */
    public String openStudy(){
    	StudyCommand command = getStudyCommand(getHttpServletRequest());
    	command.openStudy();
    	return command.getDataEntryStatus();
    	
    }
    
    /*
     * added this method to call this wherever any security filtering on organization is required
     */
    public List<Organization> restrictOrganizations(final String text) {
        List<Organization> orgs = organizationRepository.restrictBySubnames(extractSubnames(text));
        return ObjectTools.reduceAll(orgs, "id", "name", "nciInstituteCode", "externalId");
    }
    
    public List<Organization> matchOrganization(final String text) {
        List<Organization> orgs = organizationDao.getBySubnames(extractSubnames(text));
        return ObjectTools.reduceAll(orgs, "id", "name", "nciInstituteCode", "externalId");
    }

    private String[] extractSubnames(final String text) {
        return text.split("\\s+");
    }

    public List<DiseaseCategory> matchDiseaseCategories(final String text, final Integer categoryId) {
        List<DiseaseCategory> diseaseCategories = diseaseCategoryDao.getBySubname(
                        extractSubnames(text), categoryId);
        return diseaseCategories;
    }

    public List<DiseaseCategory> matchDiseaseCategoriesByParentId(final Integer parentCategoryId) {
        List<DiseaseCategory> diseaseCategories = diseaseCategoryDao
                        .getByParentId(parentCategoryId);
        return diseaseCategories;
    }

    public List<DiseaseTerm> matchDiseaseTermsByCategoryId(final Integer categoryId) {
        List<DiseaseTerm> diseaseTerms = diseaseTermDao.getByCategoryId(categoryId);
        return diseaseTerms;
    }

    public String addSolicitedAE(String listOfTermIDs[], String listOfTerms[]) {
        
        HttpServletRequest request = getHttpServletRequest();
        request.setAttribute("listOfTermIDs", listOfTermIDs);
        request.setAttribute("listOfTerms", listOfTerms);
        request.setAttribute(AJAX_REQUEST_PARAMETER, "AJAX");
        request.setAttribute(AJAX_SUBVIEW_PARAMETER, "addSolicitedAERows");
        
        String url = getCurrentPageContextRelative(WebContextFactory.get());
        return getOutputFromJsp(url);
    }

    private int generateNextEpochOrderNumber() {
    	StudyCommand command = getStudyCommand(getHttpServletRequest());
    	List<Epoch> listOfEpochs = command.getStudy().getEpochs();
    	return listOfEpochs.get(listOfEpochs.size()-1).getEpochOrder() + 1;
    }
    
    
    public String addStudySite(final int index) {
        HttpServletRequest request = getHttpServletRequest();
        getStudyCommand(request);
        setRequestAttributes(request, index, -1, "studySiteSection");
        String url = getCurrentPageContextRelative(WebContextFactory.get());
        return getOutputFromJsp(url);
    }

    public boolean deleteStudySite(final int index) {
        StudyCommand command = getStudyCommand(getHttpServletRequest());
        return command.getStudy().getStudySites().remove(index) != null;
    }

    public String addIdentifier(final int index, final int type) {
        HttpServletRequest request = getHttpServletRequest();
        StudyCommand command = getStudyCommand(request);

        if (type == 1) {
            command.getStudy().getIdentifiersLazy().add(new SystemAssignedIdentifier());
        } else if (type == 2) {
            command.getStudy().getIdentifiersLazy().add(new OrganizationAssignedIdentifier());
        }

        request.setAttribute("type", type);
        setRequestAttributes(request, command.getStudy().getIdentifiersLazy().size() - 1, index, "studyIdentifierSection");
        String url = getCurrentPageContextRelative(WebContextFactory.get());
        String html = getOutputFromJsp(url);
        request.setAttribute(AJAX_INDEX_PARAMETER, index);
        return html;

    }

    public String addTreatmentAssignment(final int index) {
        HttpServletRequest request = getHttpServletRequest();
        StudyCommand command = getStudyCommand(request);
        command.getStudy().addTreatmentAssignment(new TreatmentAssignment());

        setRequestAttributes(request, index, -1, "treatmentAssignmentSection");

        String url = getCurrentPageContextRelative(WebContextFactory.get());
        return getOutputFromJsp(url);
    }

    public boolean deleteIdentifier(final int index) {
        StudyCommand command = getStudyCommand(getHttpServletRequest());
        return command.getStudy().getIdentifiersLazy().remove(index) != null;
    }

    public String addStudyAgent(final int index) {
        HttpServletRequest request = getHttpServletRequest();
        StudyCommand command = getStudyCommand(request);
        int newIndex = command.getStudy().getStudyAgents().size();
        // pre-initialize the agent at index
        command.getStudy().getStudyAgents().get(newIndex);
        setRequestAttributes(request, newIndex, -1, "studyAgentSection");
        String url = getCurrentPageContextRelative(WebContextFactory.get());
        return getOutputFromJsp(url);
    }
    
    

    /**
     * A row of IND is needed to display
     */
    public String addIND(final int index, final int indIndex, final int indType) {
        HttpServletRequest request = getHttpServletRequest();
        StudyCommand command = getStudyCommand(request);
        StudyAgent sa = command.getStudy().getStudyAgents().get(index);
        List<StudyAgentINDAssociation> aList = sa.getStudyAgentINDAssociations();
        aList.clear(); // we are sure there is only 1 IND as of now
        String html = "";

        if (AgentsTab.IND_TYPE_CTEP == indType) {
            InvestigationalNewDrug ind = investigationalNewDrugDao.fetchCtepInd();
            aList.get(0).setInvestigationalNewDrug(ind);
        } else if (AgentsTab.IND_TYPE_DCP_IND == indType) {
            InvestigationalNewDrug ind = investigationalNewDrugDao.fetchDcpInd();
            aList.get(0).setInvestigationalNewDrug(ind);
        } else if (AgentsTab.IND_TYPE_OTHER == indType) {
            AgentsTab agentTab = new AgentsTab();
            // pre-initialize one IND
            aList.get(0);
            request.setAttribute("fieldGroups", agentTab.createFieldGroups(command));
            request.setAttribute(AJAX_INDEX_PARAMETER, index);
            request.setAttribute("indIndex", indIndex);
            String url = "/pages/study/studyAgentIND";
            html = getOutputFromJsp(url);
        }
        return html;
    }

    public String addInvestigator(final int index) {
        HttpServletRequest request = getHttpServletRequest();
        getStudyCommand(request);
        setRequestAttributes(request, index, -1, "studySiteInvestigatorSection");
        String url = getCurrentPageContextRelative(WebContextFactory.get());
        return getOutputFromJsp(url);
    }

    public String addStudyPersonnel(final int index) {
        HttpServletRequest request = getHttpServletRequest();
        getStudyCommand(request);
        setRequestAttributes(request, index, -1, "studySitePersonnelSection");
        String url = getCurrentPageContextRelative(WebContextFactory.get());
        return getOutputFromJsp(url);
    }

    /**
     * Deletes an element in a list property of the current session command, shifting everything
     * else around as appropriate.
     * 
     * <p>
     * Note that other than the extract command bit, this is entirely non-ae-flow-specific.
     * </p>
     * 
     * @return A list of changes indicating which elements of the list were moved and where to. This
     *         list will be empty if the requested change is invalid or if the change is a no-op.
     *         The element to remove will be represented by a move to a negative index.
     */
    @SuppressWarnings( { "unchecked" })
    public AjaxOutput remove(String listProperty, int indexToDelete, String displayName) {
        HttpServletRequest request = getHttpServletRequest();
        StudyCommand command = getStudyCommand(request);

        List<Object> list = (List<Object>) new BeanWrapperImpl(command).getPropertyValue(listProperty);
        if (indexToDelete >= list.size()) {
            log.debug("Attempted to delete beyond the end; " + indexToDelete + " >= " + list.size());
            return new AjaxOutput("Unable to delete. Attempted to delete beyond the end; " + indexToDelete + " >= " + list.size());
        }
        if (indexToDelete < 0) {
            log.debug("Attempted to delete from an invalid index; " + indexToDelete + " < 0");
            return new AjaxOutput("Unable to delete. Attempted to delete from an invalid index; " + indexToDelete + " < 0");
        }
        
        List<IndexChange> changes = new ArrayList<IndexChange>() ;
        Object o = null;
        
        
        if(listProperty.equals("study.studyAgents")){
        	changes = createDeleteChangeList(indexToDelete, command.getStudy().getStudyAgents(), displayName, command.isDataEntryComplete());
        	command.deleteStudyAgentAtIndex(indexToDelete);
        }else if(listProperty.equals("study.treatmentAssignments")){
        	changes = createDeleteChangeList(indexToDelete, command.getStudy().getTreatmentAssignments(), displayName,command.isDataEntryComplete());
        	command.deleteTreatmentAssignmentAtIndex(indexToDelete);
        }else{
        	
        	changes = createDeleteChangeList(indexToDelete, list.size(), displayName);
        	o = list.remove(indexToDelete);
        }
        
        try {
            saveIfAlreadyPersistent(command.getStudy());
        } catch (DataIntegrityViolationException die) {
            log.error("Error occured while deleting [listProperty :" + listProperty + ", indexToDelete :" + indexToDelete + ", displayName :" + displayName + "]", die);
            list.add(indexToDelete, o);
            updateStudyCommand(request, command);
            return new AjaxOutput("Unable to delete. The object being removed is referenced elsewhere.");
        }

        return new AjaxOutput(changes);
    }

    private List<IndexChange> createDeleteChangeList(int indexToDelete, int length, String displayName) {
        List<IndexChange> changes = new ArrayList<IndexChange>();
        changes.add(new IndexChange(indexToDelete, null));
        for (int i = indexToDelete + 1; i < length; i++) {
            IndexChange change = new IndexChange(i, i - 1);
            change.setCurrentDisplayName(displayName + " " + (i + 1));
            changes.add(change);
        }
        return changes;
    }
    
    private List<IndexChange> createDeleteChangeList(int indexToDelete, List<? extends Retireable> list, String displayName, boolean softDeleted) {
        List<IndexChange> changes = new ArrayList<IndexChange>();
        changes.add(new IndexChange(indexToDelete, null));
        int length = list.size();
        int j = indexToDelete;
        int k = (softDeleted)? 0 : 1;
        
        for (int i = indexToDelete + 1; i < length; i++) {
            IndexChange change = new IndexChange(i, i - k);
            if(!list.get(i).isRetired()) j++;
            change.setCurrentDisplayName(displayName + " " + j);
            changes.add(change);
        }
        return changes;
    }

    private void saveIfAlreadyPersistent(Study study) {
        if (study.getId() != null) {
            this.studyDao.save(study);
        }
    }

    private void setRequestAttributes(final HttpServletRequest request, final int index, final int listEditorIndex, final String subview) {
        request.setAttribute(AJAX_INDEX_PARAMETER, index);
        request.setAttribute(AJAX_SUBVIEW_PARAMETER, subview);
        request.setAttribute(AJAX_REQUEST_PARAMETER, "AJAX");
        request.setAttribute("listEditorIndex", listEditorIndex);
    }

    private String getOutputFromJsp(final String jspResource) {
        String html = "Error in rendering...";
        try {
            html = WebContextFactory.get().forwardToString(jspResource);
        } catch (ServletException e) {
            throw new CaaersSystemException(e.getMessage(), e);
        } catch (IOException e) {
            throw new CaaersSystemException(e.getMessage(), e);
        }
        return html;
    }

    private String getCurrentPageContextRelative(final WebContext webContext) {
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

    private HttpServletRequest getHttpServletRequest() {
        return WebContextFactory.get().getHttpServletRequest();
    }

    // //// CONFIGURATION

    @Required
    public AgentDao getAgentDao() {
        return agentDao;
    }

    @Required
    public void setAgentDao(final AgentDao agentDao) {
        this.agentDao = agentDao;
    }

    @Required
    public DiseaseCategoryDao getDiseaseCategoryDao() {
        return diseaseCategoryDao;
    }

    @Required
    public void setDiseaseCategoryDao(final DiseaseCategoryDao diseaseCategoryDao) {
        this.diseaseCategoryDao = diseaseCategoryDao;
    }

    @Required
    public DiseaseTermDao getDiseaseTermDao() {
        return diseaseTermDao;
    }

    @Required
    public void setDiseaseTermDao(final DiseaseTermDao diseaseTermDao) {
        this.diseaseTermDao = diseaseTermDao;
    }

    public ResearchStaffDao getResearchStaffDao() {
        return researchStaffDao;
    }

    public void setResearchStaffDao(final ResearchStaffDao researchStaffDao) {
        this.researchStaffDao = researchStaffDao;
    }

    public OrganizationDao getOrganizationDao() {
        return organizationDao;
    }

    public void setOrganizationDao(final OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public SiteInvestigatorDao getSiteInvestigatorDao() {
        return siteInvestigatorDao;
    }

    public void setSiteInvestigatorDao(final SiteInvestigatorDao siteInvestigatorDao) {
        this.siteInvestigatorDao = siteInvestigatorDao;
    }

    /**
     * @return the investigationalNewDrugDao
     */
    public InvestigationalNewDrugDao getInvestigationalNewDrugDao() {
        return investigationalNewDrugDao;
    }

    /**
     * @param investigationalNewDrugDao
     *                the investigationalNewDrugDao to set
     */
    public void setInvestigationalNewDrugDao(final InvestigationalNewDrugDao investigationalNewDrugDao) {
        this.investigationalNewDrugDao = investigationalNewDrugDao;
    }

    public StudyDao getStudyDao() {
        return studyDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public StudySiteAjaxableDomainObjectRepository getStudySiteAjaxableDomainObjectRepository() {
        return studySiteAjaxableDomainObjectRepository;
    }

    public void setStudySiteAjaxableDomainObjectRepository(StudySiteAjaxableDomainObjectRepository studySiteAjaxableDomainObjectRepository) {
        this.studySiteAjaxableDomainObjectRepository = studySiteAjaxableDomainObjectRepository;
    }

    public StudySearchableAjaxableDomainObjectRepository getStudySearchableAjaxableDomainObjectRepository() {
        return studySearchableAjaxableDomainObjectRepository;
    }

    public void setStudySearchableAjaxableDomainObjectRepository(StudySearchableAjaxableDomainObjectRepository studySearchableAjaxableDomainObjectRepository) {
        this.studySearchableAjaxableDomainObjectRepository = studySearchableAjaxableDomainObjectRepository;
    }
    
    protected void saveCommand(Object cmd) {
        WebContext webContext = WebContextFactory.get();
        Object command = null;

        for (Class<?> controllerClass : controllers()) {
            String formSessionAttributeName = controllerClass.getName() + ".FORM.command" + ".to-replace";
            command = webContext.getSession().getAttribute(formSessionAttributeName);
            if (command != null) webContext.getSession().setAttribute(formSessionAttributeName, cmd);
            else {
                String formSessionAttributeNameNew = controllerClass.getName() + ".FORM.command";
                command = webContext.getSession().getAttribute(formSessionAttributeNameNew);
                if (command != null) webContext.getSession().setAttribute(formSessionAttributeNameNew, cmd);
            }
        }
    }

    protected Object extractCommand() {
        WebContext webContext = WebContextFactory.get();
        Object command = null;
        for (Class<?> controllerClass : controllers()) {
            String formSessionAttributeName = controllerClass.getName() + ".FORM.command" + ".to-replace";
            command = webContext.getSession().getAttribute(formSessionAttributeName);
            if (command == null) {
                String formSessionAttributeNameNew = controllerClass.getName() + ".FORM.command";
                log.debug("Command not found using name " + formSessionAttributeName + ", checking for " + formSessionAttributeNameNew);
//                System.out.println("Command not found using name " + formSessionAttributeName + ", checking for " + formSessionAttributeNameNew);
                command = webContext.getSession().getAttribute(formSessionAttributeNameNew);
                 if (command == null) {
                     log.debug("Command not found using name " + formSessionAttributeNameNew);
//                     System.out.println("Command not found using name " + formSessionAttributeNameNew);
                 } else {
                     log.debug("Command found using name " + formSessionAttributeNameNew);
//                     System.out.println("Command found using name " + formSessionAttributeNameNew);
                     break;
                 }
            } else {
                log.debug("Command found using name " + formSessionAttributeName);
//                System.out.println("Command found using name " + formSessionAttributeName);
                break;
            }
        }
        if (command == null) {
            throw new CaaersSystemException("Could not find command in session");
        } else {
            return command;
        }
    }

    private String createQueryString(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(entry.getKey()).append('=').append(entry.getValue()).append('&');
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    protected String renderAjaxView(String viewName, Integer studyId, Map<String, String> params) {
        WebContext webContext = WebContextFactory.get();

        if (studyId != null) params.put("studyId", studyId.toString());
        params.put(StudyController.AJAX_SUBVIEW_PARAMETER, viewName);

        String url = String.format("%s?%s", getCurrentPageContextRelative(webContext), createQueryString(params));
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

    public AjaxOutput removeStudyTerm(int _index) {
        Study study = ((StudyCommand)extractCommand()).getStudy();
        
        boolean isMeddra = study.getAeTerminology().getTerm() == Term.MEDDRA;
        List studyTerms = (isMeddra) ? study.getExpectedAEMeddraLowLevelTerms() : study.getExpectedAECtcTerms();
        // System.out.println("Removing element " + _index + " out of " + studyTerms.size());
        studyTerms.remove(_index);
        // System.out.println("Element removed. The new size is " + studyTerms.size() + "\n\r\n\r");

        int index = (isMeddra) ? study.getExpectedAEMeddraLowLevelTerms().size() : study.getExpectedAECtcTerms().size();
        Map<String, String> params = new LinkedHashMap<String, String>();
        index--;
        params.put("index", Integer.toString(index));
        params.put("isSingle", Boolean.toString(false));

        AjaxOutput ajaxOutput = new AjaxOutput();
        ajaxOutput.setHtmlContent(renderAjaxView("expectedAEsSection", study.getId(), params));

        return ajaxOutput;
    }

    public AjaxOutput addExpectedAE(int[] listOfTermIDs) {

        AjaxOutput ajaxOutput = new AjaxOutput();
        StudyCommand command = (StudyCommand)extractCommand();
        boolean isMeddra = command.getStudy().getAeTerminology().getTerm() == Term.MEDDRA;

        List studyTerms = (isMeddra) ? command.getStudy().getExpectedAEMeddraLowLevelTerms() : command.getStudy().getExpectedAECtcTerms();
        int firstIndex = studyTerms.size();

        List<Integer> filteredTermIDs = new ArrayList<Integer>();
        // List<String> removedTerms = new ArrayList<String>();

        // the list of terms to be added
        // filter off the terms that are already present
        for (int id : listOfTermIDs) {
            filteredTermIDs.add(id);
        }

        if (filteredTermIDs.isEmpty()) return ajaxOutput;

        for (int id : filteredTermIDs) {
            if (isMeddra) {
                // populate MedDRA term
                LowLevelTerm llt = lowLevelTermDao.getById(id);
                ExpectedAEMeddraLowLevelTerm studyllt = new ExpectedAEMeddraLowLevelTerm();
                studyllt.setLowLevelTerm(llt);
                command.getStudy().addExpectedAEMeddraLowLevelTerm(studyllt);
                studyllt.setStudy(command.getStudy());
            } else {
                // properly set CTCterm
                CtcTerm ctc = ctcTermDao.getById(id);
                ExpectedAECtcTerm studyCtc = new ExpectedAECtcTerm();
                studyCtc.setCtcTerm(ctc);
                command.getStudy().addExpectedAECtcTerm(studyCtc);
                studyCtc.setStudy(command.getStudy());
            }
        }

        Study mergeStudy = command.getStudy();
        if (command.getStudy().getId() != null) {
            mergeStudy = studyDao.merge(command.getStudy());
            command.setStudy(mergeStudy);
            saveCommand(command);
        }

        // 
/*
        if (!removedTerms.isEmpty()) {
            String[] removedTermsArray = removedTerms.toArray(new String[]{});
            ajaxOutput.setObjectContent(removedTermsArray);
        }
*/
        int lastIndex = studyTerms.size() - 1;
        Map<String, String> params = new LinkedHashMap<String, String>(); // preserve order for testing
        params.put("firstIndex", Integer.toString(firstIndex));
        params.put("lastIndex", Integer.toString(lastIndex));
        params.put("isSingle", Boolean.toString(true));
        ajaxOutput.setHtmlContent(renderAjaxView("expectedAEsSection", mergeStudy.getId(), params));

        return ajaxOutput;
    }

    public LowLevelTermDao getLowLevelTermDao() {
        return lowLevelTermDao;
    }

    public void setLowLevelTermDao(LowLevelTermDao lowLevelTermDao) {
        this.lowLevelTermDao = lowLevelTermDao;
    }

    public CtcTermDao getCtcTermDao() {
        return ctcTermDao;
    }

    public void setCtcTermDao(CtcTermDao ctcTermDao) {
        this.ctcTermDao = ctcTermDao;
    }

	public OrganizationRepository getOrganizationRepository() {
		return organizationRepository;
	}
	@Required
	public void setOrganizationRepository(OrganizationRepository organizationRepository) {
		this.organizationRepository = organizationRepository;
	}

	public void setResearchStaffRepository(ResearchStaffRepository researchStaffRepository) {
		this.researchStaffRepository = researchStaffRepository;
	}

	public void setInvestigatorRepository(InvestigatorRepository investigatorRepository) {
		this.investigatorRepository = investigatorRepository;
	}

}
