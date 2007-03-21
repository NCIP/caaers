package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.dao.SiteDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.DiseaseTermDao;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Site;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyDisease;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.Participation;
import gov.nih.nci.cabig.caaers.tools.editors.DaoBasedEditor;
import gov.nih.nci.cabig.caaers.tools.editors.NullIdDaoBasedEditor;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.web.participant.CreateParticipantController;
import gov.nih.nci.cabig.caaers.web.tabbedflow.AbstractTabbedFlowFormController;
import gov.nih.nci.cabig.caaers.web.tabbedflow.Flow;
import gov.nih.nci.cabig.caaers.web.tabbedflow.Tab;
import gov.nih.nci.cabig.caaers.tools.editors.GridIdentifiableDaoBasedEditor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Kulasekaran
 */
public class CreateStudyController extends AbstractTabbedFlowFormController<Study> {
	
	private static Log log = LogFactory.getLog(CreateStudyController.class);
	private StudyDao studyDao;
	private SiteDao siteDao;
	private AgentDao agentDao;
	private DiseaseTermDao diseaseTermDao;
	private ConfigProperty configurationProperty;
	
	public CreateStudyController() {		
        setCommandClass(Study.class);        

        Flow<Study> flow = new Flow<Study>("Create Study");       
        
        flow.addTab(new Tab<Study>("Study Details", "Study Details", "study_details") {
            public Map<String, Object> referenceData() {
            	            	
                Map<String, Object> refdata = super.referenceData();
                Map <String, List<Lov>> configMap = configurationProperty.getMap();
                
                refdata.put("diseaseCodeRefData", configMap.get("diseaseCodeRefData"));
    	  		refdata.put("monitorCodeRefData",  configMap.get("monitorCodeRefData"));
    	  		refdata.put("phaseCodeRefData",  configMap.get("phaseCodeRefData"));
    	  		refdata.put("sponsorCodeRefData",  configMap.get("sponsorCodeRefData"));
    	  		refdata.put("statusRefData",  configMap.get("statusRefData"));
    	  		refdata.put("typeRefData",  configMap.get("typeRefData"));                
    	  		refdata.put("multiInstitutionIndicator", getBooleanList());
    	  		refdata.put("randomizedIndicator", getBooleanList());
    	  		refdata.put("blindedIndicator", getBooleanList());
    	  		refdata.put("nciIdentifier", getBooleanList());
    	  		return refdata;
            }        	
        });
        flow.addTab(new Tab<Study>("Study Identifiers", "Study Identifiers", "study_identifiers"){
            
        	public Map<String, Object> referenceData() {
                Map<String, Object> refdata = super.referenceData();
                Map <String, List<Lov>> configMap = configurationProperty.getMap();
                
                refdata.put("identifiersSourceRefData", getSites());
                refdata.put("identifiersTypeRefData", configMap.get("identifiersType"));                    
    	  		return refdata;
        	}
        });                 
        flow.addTab(new Tab<Study>("Study Sites", "Study Sites", "study_studysite") {
            
        	public Map<String, Object> referenceData() {
                Map<String, Object> refdata = super.referenceData();  
                Map <String, List<Lov>> configMap = configurationProperty.getMap();
                
                refdata.put("sitesRefData", getSites());
                refdata.put("studySiteStatusRefData", configMap.get("studySiteStatusRefData"));
    	  		refdata.put("studySiteRoleCodeRefData",  configMap.get("studySiteRoleCodeRefData"));
                return refdata;
           
        	}        	
        });

		flow.addTab(new Tab<Study>("Study Agents", "Study Agents", "study_studyagent") {
            
        	public Map<String, Object> referenceData() {
                Map<String, Object> refdata = super.referenceData();
                return refdata;
           
        	}        	
        });
		
		flow.addTab(new Tab<Study>("Study Disease", "Study Disease", "study_studydisease") {
            
        	public Map<String, Object> referenceData() {
                Map<String, Object> refdata = super.referenceData();
                return refdata;
           
        	}        	
        });
        
        
        flow.addTab(new Tab<Study>("Review and Submit", "Review and Submit", "study_reviewsummary"));                        
        setFlow(flow);        
    }
	
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		super.initBinder(request, binder);
		binder.registerCustomEditor(Date.class, ControllerTools
				.getDateEditor(true));
		binder.registerCustomEditor(Site.class, new DaoBasedEditor(
				siteDao));
		binder.registerCustomEditor(Agent.class, new NullIdDaoBasedEditor(
				agentDao));
		//ControllerTools.registerGridDomainObjectEditor(binder, "agent", agentDao);
	}
	
	/**
	 * Create a nested object graph that Create Study Design needs
	 * 
	 * @param request -
	 *            HttpServletRequest
	 * @throws ServletException
	 */
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {	
		return createDefaultStudyWithDesign();		         
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractWizardFormController#processFinish
	 * (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, 
	 * java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, 
			Object command, BindException errors) throws Exception {
		Study study = (Study) command;
		studyDao.save(study);
		
		ModelAndView modelAndView= new ModelAndView("study_confirmation");
    	modelAndView.addAllObjects(errors.getModel());
    	response.sendRedirect("view?studyName=" + study.getShortTitle() + "&type=confirm");
    	return null;
	}
	
	@Override
	protected void postProcessPage(HttpServletRequest request, Object command,
			Errors arg2, int pageNo) throws Exception {
		
		switch (pageNo)
		{
			case 1:
				handleIdentifierAction((Study)command,
						request.getParameter("_action"),
						request.getParameter("_selected"));		
				break;
			case 2:
				handleStudySiteAction((Study)command, request.getParameter("_action"),
						request.getParameter("_selected"));
				break;
			case 3:
				handleStudyAgentAction((Study)command, request.getParameter("_action"),
						request.getParameter("_selected"));
				break;
			case 4:
				handleStudyDiseaseAction((Study)command, request.getParameter("_action"),
						request.getParameter("_selected"));
				break;
			default:
				//do nothing						
		}		
	}
	
	private void handleIdentifierAction(Study study, String action, String selected)
	{				
		if ("addIdentifier".equals(action))
		{	
			Identifier id = new Identifier();
			id.setValue("<enter value>");
			study.addIdentifier(id);		
		}
		else if ("removeIdentifier".equals(action))
		{				
			study.getIdentifiers().remove(Integer.parseInt(selected));
		}					
	}
	
	private void handleStudySiteAction(Study study, String action, String selected)
	{				
		if ("addSite".equals(action))
		{	
			StudySite studySite = new StudySite();						
			study.addStudySite(studySite);		
		}
		else if ("removeSite".equals(action))
		{				
			study.getStudySites().remove(Integer.parseInt(selected));
		}					
	}
	
	private void handleStudyAgentAction(Study study, String action, String selected)
	{				
		if ("addStudyAgent".equals(action))
		{	
			StudyAgent studyAgent = new StudyAgent();
			studyAgent.setAgent(new Agent());
			study.addStudyAgent(studyAgent);		
		}
		else if ("removeStudyAgent".equals(action))
		{				
			study.getStudyAgents().remove(Integer.parseInt(selected));
		}					
	}
	
	private void handleStudyDiseaseAction(Study study, String action, String selected)
	{				
		if ("addStudyDisease".equals(action))
		{
			String[] diseases = study.getDiseaseTermIds();
			log.debug("Study Diseases Size : " + study.getStudyDiseases().size());
			for (String diseaseId : diseases){
				log.debug("Disease Id : " + diseaseId);
				StudyDisease studyDisease = new StudyDisease();
				studyDisease.setDiseaseTerm(diseaseTermDao.getById(Integer.parseInt(diseaseId)));
				study.addStudyDisease(studyDisease);
				
			}
		}
		else if ("removeStudyDisease".equals(action))
		{				
			study.getStudyDiseases().remove(Integer.parseInt(selected));
		}					
	}
	
	private Study createDefaultStudyWithDesign()
	{
		Study study = new Study(); 
			  
		StudySite studySite = new StudySite();
		study.addStudySite(studySite);
		
		StudyAgent studyAgent = new StudyAgent();
		studyAgent.setAgent(new Agent());
		study.addStudyAgent(studyAgent);
			
		List<Identifier> identifiers = new ArrayList<Identifier>();
		Identifier id = new Identifier(); 
		identifiers.add(id);
		study.setIdentifiers(identifiers);
		
		List<Site> sites = getSites();
		
		for (Site site : sites) {
				studySite.setSite(site);
		}
		
		return study;
	}
	
	public AgentDao getAgentDao() {
		return agentDao;
	}
	
	public void setAgentDao(AgentDao agentDao) {
		this.agentDao = agentDao;
	}

	public StudyDao getStudyDao() {
		return studyDao;
	}

	public void setStudyDao(StudyDao studyDao) {
		this.studyDao = studyDao;
	}
	
	
	public SiteDao getSiteDao() {
		return siteDao;
	}

	public void setSiteDao(SiteDao siteDao) {
		this.siteDao = siteDao;
	}
	
	public DiseaseTermDao getDiseaseTermDao() {
		return diseaseTermDao;
	}

	public void setDiseaseTermDao(DiseaseTermDao diseaseTermDao) {
		this.diseaseTermDao = diseaseTermDao;
	}

	private List<Site> getSites()
	{
		return siteDao.getAll();  	
	}
	
	private List<StringBean> getBooleanList(){
		List<StringBean> col = new ArrayList<StringBean>();		
    	col.add(new StringBean("YES"));
    	col.add(new StringBean("NO"));
    	return col;
	}
	
	public class StringBean {
		
		String str;
		
		StringBean(String str)
		{
			this.str=str;
		}
		
		public void setStr(String str){
			this.str=str;
		}
		
		public String getStr(){
			return str;
		}	
	}	

	public ConfigProperty getConfigurationProperty() {
		return configurationProperty;
	}

	public void setConfigurationProperty(ConfigProperty configurationProperty) {
		this.configurationProperty = configurationProperty;
	}        		
}