package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.*;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.dao.query.ajax.StudySiteAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySiteAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.repository.*;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.StudySearchableAjaxableDomainObjectRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.StudySiteAjaxableDomainObjectRepository;
import gov.nih.nci.cabig.caaers.service.AdeersIntegrationFacade;
import gov.nih.nci.cabig.caaers.service.ProxyWebServiceFacade;
import gov.nih.nci.cabig.caaers.tools.ObjectTools;
import gov.nih.nci.cabig.caaers.utils.ranking.RankBasedSorterUtils;
import gov.nih.nci.cabig.caaers.utils.ranking.Serializer;
import gov.nih.nci.cabig.caaers.web.dwr.AjaxOutput;
import gov.nih.nci.cabig.caaers.web.dwr.IndexChange;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.servlet.mvc.AbstractFormController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

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
    private AgentRepository agentRepository;
    private DeviceRepository deviceRepository;
    private DiseaseCategoryDao diseaseCategoryDao;
    private DiseaseTermDao diseaseTermDao;
    private SiteInvestigatorDao siteInvestigatorDao;
    private ResearchStaffDao researchStaffDao;
    private SiteResearchStaffDao siteResearchStaffDao;
    private ResearchStaffRepository researchStaffRepository;
    private OrganizationDao organizationDao;
    private OrganizationRepository organizationRepository;
    private InvestigationalNewDrugDao investigationalNewDrugDao;
    private InvestigatorRepository investigatorRepository;
    private StudyDao studyDao;
    private StudyRepository studyRepository;
    protected LowLevelTermDao lowLevelTermDao;
    protected CtcTermDao ctcTermDao;

    private StudySiteAjaxableDomainObjectRepository studySiteAjaxableDomainObjectRepository;
    private StudySearchableAjaxableDomainObjectRepository studySearchableAjaxableDomainObjectRepository;

    private AdeersIntegrationFacade proxyWebServiceFacade;
    /**
     * Retrieves StudySite's Investigators for the autocompleter through AJAX
     * */
    public List<SiteInvestigator> matchSiteInvestigator(final String text, final int indexId) {
        String[] subtexts = text.split("[\\s]+");
        StudyCommand studyCommand = getStudyCommand(getHttpServletRequest());
        int siteId = studyCommand.getStudy().getActiveStudyOrganizations().get(indexId).getOrganization().getId();
        List<SiteInvestigator> siteInvestigators = investigatorRepository.getBySubnames(subtexts, siteId);
        siteInvestigators = RankBasedSorterUtils.sort(siteInvestigators, text, new Serializer<SiteInvestigator>(){
            public String serialize(SiteInvestigator object) {
                return object.getInvestigator().getFullName();  
            }
        });
        return ObjectTools.reduceAll(siteInvestigators,
                        new ObjectTools.Initializer<SiteInvestigator>() {
                            public void initialize(final SiteInvestigator instance) {
                                instance.setInvestigator(new LocalInvestigator());
                            }
                        }, "id", "investigator.firstName", "investigator.lastName", "investigator.externalId");
    }

    /**
     * Retrieves StudySite's ResearchStaffs for the autocompleter through AJAX
     * */
    public List<ResearchStaff> matchResearch(final String text, final int indexId) {
        StudyCommand command = getStudyCommand(getHttpServletRequest());
        int siteId = command.getStudy().getStudyOrganizations().get(indexId).getOrganization().getId();
        List<ResearchStaff> researchStaffs = researchStaffRepository.getBySubnames(new String[] { text }, siteId);
        researchStaffs = RankBasedSorterUtils.sort(researchStaffs, text, new Serializer<ResearchStaff>(){
            public String serialize(ResearchStaff object) {
                return object.getFullName();  
            }
        });
        return ObjectTools.reduceAll(researchStaffs, "id", "firstName", "lastName", "externalId");
    }

    /**
     * Retrieves Site's ResearchStaffs for the autocompleter through AJAX
     * */
    public List<SiteResearchStaff> matchSiteResearchStaff(final String text, final int indexId) {
        String[] subtexts = text.split("[\\s]+");
        StudyCommand command = getStudyCommand(getHttpServletRequest());
        int siteId = command.getStudy().getActiveStudyOrganizations().get(indexId).getOrganization().getId();
        List<SiteResearchStaff> siteResearchStaffs =  researchStaffRepository.getSiteResearchStaffBySubnames(subtexts, siteId);
        siteResearchStaffs = RankBasedSorterUtils.sort(siteResearchStaffs , text, new Serializer<SiteResearchStaff>(){
            public String serialize(SiteResearchStaff object) {
                return object.getResearchStaff().getFullName();
            }
        });

        return ObjectTools.reduceAll(siteResearchStaffs,
                        new ObjectTools.Initializer<SiteResearchStaff>() {
                            public void initialize(final SiteResearchStaff instance) {
                                instance.setResearchStaff(new LocalResearchStaff());
                            }
                        }, "id", "researchStaff.firstName", "researchStaff.lastName", "researchStaff.externalId");
    }

    /**
     * Retrieves Study object from the HTTP Session
     * */
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

    /*
    * Retrieves the Study Command Object
    * */
    private StudyCommand getStudyCommand(final HttpServletRequest request) {
        StudyCommand command = getStudyFromSession(request);
        request.setAttribute(AbstractFormController.DEFAULT_COMMAND_NAME, command);
        return command;
    }

    /**
     * Retrieves Sites for the autocompleter through AJAX
     * */
    public List<StudySiteAjaxableDomainObject> matchSites(final String text, final Integer studyId){
    	if(studyId == null) 
    		return new ArrayList<StudySiteAjaxableDomainObject>();
    	
    	StudySiteAjaxableDomainObjectQuery query = new StudySiteAjaxableDomainObjectQuery();
    	query.filterByStudy(studyId);
        List<StudySiteAjaxableDomainObject> sites = studySiteAjaxableDomainObjectRepository.findStudySites(query);
    	return sites;
    	
    }
    
    /**
     * Retrieves Agents for the autocompleter through AJAX
     * */
    public List<Agent> matchAgents(final String text) {
        List<Agent> agents = agentRepository.getAgentsByNameOrNsc(text, text, true);
        agents = RankBasedSorterUtils.sort(agents, text, new Serializer<Agent>() {
            public String serialize(Agent object) {
                return object.getDisplayName();
            }
        });
        return ObjectTools.reduceAll(agents, "id", "name", "nscNumber", "description");
    }

    /**
     * Retrieves INDs for the autocompleter through AJAX
     * */
    public List<InvestigationalNewDrug> matchINDs(final String text) {
        List<InvestigationalNewDrug> inds = investigationalNewDrugDao.findByIds(new String[] { text });
        inds = RankBasedSorterUtils.sort(inds , text, new Serializer<InvestigationalNewDrug>(){
            public String serialize(InvestigationalNewDrug object) {
                return object.getNumberAndHolderName();
            }
        });
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
    public List<Organization> restrictOrganizations(final String text, Boolean skipFiltering) {
        List<Organization> orgs = organizationRepository.restrictBySubnames(extractSubnames(text), skipFiltering);
        orgs = RankBasedSorterUtils.sort(orgs , text, new Serializer<Organization>(){
            public String serialize(Organization object) {
                return object.getFullName();
            }
        });
        return ObjectTools.reduceAll(orgs, "id", "name", "nciInstituteCode", "externalId");
    }

    /**
     * @author Ion C. Olaru
     * Brings all the study's related organizations (CC + FS + Study Sites)  
     *
     * */
    public List<Organization> matchStudyOrganizations(final String text, final Integer studyId) {
        List<StudyOrganization> sos = organizationRepository.getApplicableOrganizationsFromStudyOrganizations(text, studyId);
        sos = RankBasedSorterUtils.sort(sos , text, new Serializer<StudyOrganization>(){
            public String serialize(StudyOrganization object) {
                return object.getOrganization().getFullName();
            }
        });
        Set<Organization> organizations = new HashSet<Organization>();
        for (StudyOrganization so : sos) {
            organizations.add(ObjectTools.reduce(so.getOrganization(), "id", "name", "nciInstituteCode", "externalId"));
        }
        return new ArrayList(organizations);
    }

    /**
     * Retrieves Organizations for the autocompleter through AJAX
     * */
    public List<Organization> matchOrganization(final String text) {
    	OrganizationQuery query = new OrganizationQuery();
    	query.filterByOrganizationNameOrNciCode(text);
        List<Organization> orgs = organizationDao.getBySubnames(query);
        orgs = RankBasedSorterUtils.sort(orgs , text, new Serializer<Organization>(){
            public String serialize(Organization object) {
                return object.getFullName();
            }
        });
        return ObjectTools.reduceAll(orgs, "id", "name", "nciInstituteCode", "externalId");
    }

    private String[] extractSubnames(final String text) {
        return text.split("\\s+");
    }

    /**
     * Retrieves Disease Categories based on the parent category and text to match
     * */
    public List<DiseaseCategory> matchDiseaseCategories(final String text, final Integer categoryId) {
        List<DiseaseCategory> diseaseCategories = diseaseCategoryDao.getBySubname(extractSubnames(text), categoryId);
        diseaseCategories =  RankBasedSorterUtils.sort(diseaseCategories , text, new Serializer<DiseaseCategory>(){
            public String serialize(DiseaseCategory object) {
                return object.getName();
            }
        });
        return diseaseCategories;
    }

    /**
     * Retrieves Disease Categories based on the parent category
     * */
    public List<DiseaseCategory> matchDiseaseCategoriesByParentId(final Integer parentCategoryId) {
        List<DiseaseCategory> diseaseCategories = diseaseCategoryDao.getByParentId(parentCategoryId);
        return diseaseCategories;
    }

    /**
     * Retrieves Disease Terms based on the category
     * */
    public List<DiseaseTerm> matchDiseaseTermsByCategoryId(final Integer categoryId) {
        List<DiseaseTerm> diseaseTerms = diseaseTermDao.getByCategoryId(categoryId);
        return diseaseTerms;
    }

    /**
     * Add Solicited AE items to the collection
     * */
    public String addSolicitedAE(String listOfTermIDs[], String listOfTerms[]) {
        
        HttpServletRequest request = getHttpServletRequest();
        request.setAttribute("listOfTermIDs", listOfTermIDs);
        request.setAttribute("listOfTerms", listOfTerms);
        request.setAttribute(AJAX_REQUEST_PARAMETER, "AJAX");
        request.setAttribute(AJAX_SUBVIEW_PARAMETER, "addSolicitedAERows");
        
        String url = getCurrentPageContextRelative(WebContextFactory.get());
        return getOutputFromJsp(url);
    }

    /**
     * Generate the next order number for Epochs
     * */
    private int generateNextEpochOrderNumber() {
    	StudyCommand command = getStudyCommand(getHttpServletRequest());
    	List<Epoch> listOfEpochs = command.getStudy().getEpochs();
    	return listOfEpochs.get(listOfEpochs.size()-1).getEpochOrder() + 1;
    }
    
    
    /**
     * Add a study Site through AJAX
     * */
    public String addStudySite(final int index) {
        HttpServletRequest request = getHttpServletRequest();
        getStudyCommand(request);
        setRequestAttributes(request, index, -1, "studySiteSection");
        String url = getCurrentPageContextRelative(WebContextFactory.get());
        return getOutputFromJsp(url);
    }

    /**
     * Delete a study Site through AJAX
     * */
    public boolean deleteStudySite(final int index) {
        StudyCommand command = getStudyCommand(getHttpServletRequest());
        return command.getStudy().getStudySites().remove(index) != null;
    }

    /**
     * Add a study Identifier through AJAX
     * */
    public String addIdentifier(final int index, final int type) {
        HttpServletRequest request = getHttpServletRequest();
        StudyCommand command = getStudyCommand(request);

        if (type == 1) {
            command.getStudy().getIdentifiersLazy().add(new SystemAssignedIdentifier());
        } else if (type == 2) {
            command.getStudy().getIdentifiersLazy().add(new OrganizationAssignedIdentifier());
        }
        studyDao.save(command.getStudy());
        request.setAttribute("type", type);
        setRequestAttributes(request, command.getStudy().getIdentifiersLazy().size() - 1, index, "studyIdentifierSection");
        String url = getCurrentPageContextRelative(WebContextFactory.get());
        String html = getOutputFromJsp(url);
        request.setAttribute(AJAX_INDEX_PARAMETER, index);
        return html;

    }

    /**
     * Delete a study Identifier through AJAX
     * */
    public boolean deleteIdentifier(final int index) {
        StudyCommand command = getStudyCommand(getHttpServletRequest());
        return command.getStudy().getIdentifiersLazy().remove(index) != null;
    }


    /**
     * Add a study Investigator through AJAX
     * */
    public String addInvestigator(final int index) {
        HttpServletRequest request = getHttpServletRequest();
        getStudyCommand(request);
        setRequestAttributes(request, index, -1, "studySiteInvestigatorSection");
        String url = getCurrentPageContextRelative(WebContextFactory.get());
        return getOutputFromJsp(url);
    }

    /**
     * Add a study Personnel through AJAX
     * */
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
        
    	changes = createDeleteChangeList(indexToDelete, list.size(), displayName);
    	o = list.remove(indexToDelete);
        try {
            saveIfAlreadyPersistent(command.getStudy());
        } catch (DataIntegrityViolationException die) {
            log.error("Error occured while deleting [listProperty :" + listProperty + ", indexToDelete :" + indexToDelete + ", displayName :" + displayName + "]", die);
            list.add(indexToDelete, o);
            command.reloadStudy();
            return new AjaxOutput("Unable to delete. The object being removed is referenced elsewhere.");
        }

        return new AjaxOutput(changes);
    }

    /**
     * Create the list of elements to be deleted
     * */
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
    
    /**
     * Create the list of elements to be deleted
     * */
    private List<IndexChange> createDeleteChangeList(int indexToDelete, List<? extends Retireable> list, String displayName, boolean softDeleted) {
        List<IndexChange> changes = new ArrayList<IndexChange>();
        changes.add(new IndexChange(indexToDelete, softDeleted ? indexToDelete : null));
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

    /**
     * Save the study if the Study is a persistent Object already
     * */
    private void saveIfAlreadyPersistent(Study study) {
        if (study.getId() != null) {
            this.studyRepository.save(study);
        }
    }

    /**
     * Set the HTTP Request Attributes for the AJAX calls
     * */
    private void setRequestAttributes(final HttpServletRequest request, final int index, final int listEditorIndex, final String subview) {
        request.setAttribute(AJAX_INDEX_PARAMETER, index);
        request.setAttribute(AJAX_SUBVIEW_PARAMETER, subview);
        request.setAttribute(AJAX_REQUEST_PARAMETER, "AJAX");
        request.setAttribute("listEditorIndex", listEditorIndex);
    }

    /**
     * Build the HTML content for the AJAX calls
     */
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

    /**
     * Get the flow current page relative to the context
     * */
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

    /**
     * Replace the controller command object
     * */
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

    /**
     * Retrieve the controller command object
     * */
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

    /**
     * Build the queryString from the Map
     * */
    private String createQueryString(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(entry.getKey()).append('=').append(entry.getValue()).append('&');
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    /**
     * Build the HTML for the AJAX call
     * */
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

    /**
     * Remove an expected term from the Study
     * */
    public AjaxOutput removeStudyTerm(int _index) {
        StudyCommand command = (StudyCommand)extractCommand();
        if(command.getStudy().getId() != null){
            Study study = studyRepository.getById(command.getStudy().getId());
            command.setStudy(study);
        }
        Study study = command.getStudy();

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
        if(study.getId() != null){
            command.save();
        }

        return ajaxOutput;
    }
    
//    /**
//     * Remove an expected term from the Study
//     * */
//    public AjaxOutput removeTATerm(int _ta_index, int _tas_index, int _tasae_index) {
//        StudyCommand command = (StudyCommand)extractCommand();
//        if(command.getStudy().getId() != null){
//            Study study = studyRepository.getById(command.getStudy().getId());
//            command.setStudy(study);
//        }
//        Study study = command.getStudy();
//
//        ((TreatmentAssignmentAgent)(study.getTreatmentAssignments().get(_ta_index).getTreatmentAssignmentStudyInterventions().get(_tas_index))).getAbstractStudyInterventionExpectedAEs().remove(_tasae_index);
//
//        AjaxOutput ajaxOutput = new AjaxOutput();
//        ajaxOutput.setHtmlContent(renderAjaxView("expectedAEsSection", study.getId(), new HashMap<String, String>()));
//        if(study.getId() != null){
//            command.save();
//        }
//
//        return ajaxOutput;
//    }

    /**
     * Add an expected AE to the Study
     */
    public AjaxOutput addExpectedAE(int[] listOfTermIDs) {

        AjaxOutput ajaxOutput = new AjaxOutput();
        StudyCommand command = (StudyCommand)extractCommand();
        if(command.getStudy().getId() != null){
            Study study = studyRepository.getById(command.getStudy().getId());
            command.setStudy(study);
        }

        boolean isMeddra = command.getStudy().getAeTerminology().getTerm() == Term.MEDDRA;

        List studyTerms = (isMeddra) ? command.getStudy().getExpectedAEMeddraLowLevelTerms() : command.getStudy().getExpectedAECtcTerms();
        int firstIndex = studyTerms.size();
        HashSet<Integer> terms = new HashSet<Integer>();
        for (int i=0; i<studyTerms.size(); i++) {
            terms.add(((AbstractExpectedAE)studyTerms.get(i)).getTerm().getId());
        }

        List<Integer> filteredTermIDs = new ArrayList<Integer>();
        // List<String> removedTerms = new ArrayList<String>();

        // the list of terms to be added
        // filter off the terms that are already present
        for (int id : listOfTermIDs) {
            if (!terms.contains(id))
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

        if(command.getStudy().getId() != null){
            command.save();
        }


        int lastIndex = studyTerms.size() - 1;
        Map<String, String> params = new LinkedHashMap<String, String>(); // preserve order for testing
        params.put("firstIndex", Integer.toString(firstIndex));
        params.put("lastIndex", Integer.toString(lastIndex));
        params.put("isSingle", Boolean.toString(true));
        ajaxOutput.setHtmlContent(renderAjaxView("expectedAEsSection", command.getStudy().getId(), params));

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
	@Required
	public void setStudyRepository(StudyRepository studyRepository) {
		this.studyRepository = studyRepository;
	}

    public AjaxOutput fetchSiteReseachStaffActiveRoles(int rsID){
    	StudyCommand command = (StudyCommand)extractCommand();
    	AjaxOutput out = new AjaxOutput();
    	out.setObjectContent(command.getStudyPersonnelRoles());
    	return out;
    }

    /**
     *
     * @param studyIdentifier Study Funding Sponsor Identifier
     * @param createOrUpdate It takes values if "CREATE" or "UPDATE
     * @return
     */
    public AjaxOutput syncStudyWithAdEERS(String studyIdentifier, String nciInstituteCode, String createOrUpdate) {
        AjaxOutput out = new AjaxOutput();
        String _result = "";

        try {
            OrganizationAssignedIdentifier id = new OrganizationAssignedIdentifier();
            id.setType(OrganizationAssignedIdentifier.SPONSOR_IDENTIFIER_TYPE);
            id.setValue(studyIdentifier);
            Organization org = new LocalOrganization();
            org.setNciInstituteCode(nciInstituteCode); //populate me ??
            id.setOrganization(org);
            _result = proxyWebServiceFacade.syncStudy(id, createOrUpdate);
            Integer.parseInt(_result);
            out.setObjectContent(_result);
        } catch (NumberFormatException e) {
            out.setError(true);
            out.setErrorMessage(_result);
        }
        return out;
    }

    /**
     * This method is invioked from Study flow, Interventions page, Devices autocompleter
     * @param text
     * @return
     */
    public List<Device> fetchDevicesByText(String text) {
        List<Device> l = deviceRepository.getByMatchText(text, true);
        l = RankBasedSorterUtils.sort(l , text, new Serializer<Device>(){
            public String serialize(Device object) {
                return object.getDisplayName();
            }
        });
        return ObjectTools.reduceAll(l, "id", "commonName", "brandName", "type");
    }

    public SiteResearchStaffDao getSiteResearchStaffDao() {
        return siteResearchStaffDao;
    }

    public void setSiteResearchStaffDao(SiteResearchStaffDao siteResearchStaffDao) {
        this.siteResearchStaffDao = siteResearchStaffDao;
    }

    public DeviceRepository getDeviceRepository() {
        return deviceRepository;
    }

    public void setDeviceRepository(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public AdeersIntegrationFacade getProxyWebServiceFacade() {
        return proxyWebServiceFacade;
    }

    public void setProxyWebServiceFacade(AdeersIntegrationFacade proxyWebServiceFacade) {
        this.proxyWebServiceFacade = proxyWebServiceFacade;
    }

    public AgentRepository getAgentRepository() {
        return agentRepository;
    }

    public void setAgentRepository(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }
}
