package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.SiteDao;
import gov.nih.nci.cabig.caaers.dao.SiteInvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.DiseaseTermDao;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Site;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyInvestigator;
import gov.nih.nci.cabig.caaers.domain.StudyPersonnel;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyDisease;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.tools.editors.DaoBasedEditor;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.web.tabbedflow.AbstractTabbedFlowFormController;
import gov.nih.nci.cabig.caaers.web.tabbedflow.Flow;
import gov.nih.nci.cabig.caaers.web.tabbedflow.Tab;

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
	private SiteInvestigatorDao siteInvestigatorDao;
	private ResearchStaffDao researchStaffDao;
	private ConfigProperty configurationProperty;
	
	public CreateStudyController() {		
        setCommandClass(Study.class);        

        Flow<Study> flow = new Flow<Study>("Create Study");       
        
        flow.addTab(new Tab<Study>("Study Details", "Study Details", "study/study_details") {
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
            
            @Override
            public void validate(Study command, Errors errors) {
                boolean longTitle = command.getLongTitle() == null || command.getLongTitle().equals("");                
                if (longTitle) errors.rejectValue("longTitle", "REQUIRED", "Missing Long Title");                
            }
            
            @Override
            public boolean isAllowDirtyForward() {
                return false;
            }
        });
        flow.addTab(new Tab<Study>("Study Identifiers", "Study Identifiers", "study/study_identifiers"){
            
        	public Map<String, Object> referenceData() {
                Map<String, Object> refdata = super.referenceData();
                Map <String, List<Lov>> configMap = configurationProperty.getMap();
                
                refdata.put("identifiersSourceRefData", getSites());
                refdata.put("identifiersTypeRefData", configMap.get("identifiersType"));                    
    	  		return refdata;
        	}
        });                 
        flow.addTab(new Tab<Study>("Study Sites", "Study Sites", "study/study_studysite") {
            
        	public Map<String, Object> referenceData() {
                Map<String, Object> refdata = super.referenceData();  
                Map <String, List<Lov>> configMap = configurationProperty.getMap();
                
                refdata.put("sitesRefData", getSites());
                refdata.put("studySiteStatusRefData", configMap.get("studySiteStatusRefData"));
    	  		refdata.put("studySiteRoleCodeRefData",  configMap.get("studySiteRoleCodeRefData"));
                return refdata;
           
        	}        	
        });

        flow.addTab(new Tab<Study>("Study Investigators", "Study Investigators", "study/study_investigator") {
            
        	public Map<String, Object> referenceData() {
                Map<String, Object> refdata = super.referenceData();
                Map <String, List<Lov>> configMap = configurationProperty.getMap();
                //List<StudySite> studySites = new ArrayList<StudySite>();
                //refdata.put("siteInvestigatorRefData", getSiteInvestigator());
                refdata.put("invRoleCodeRefData", configMap.get("invRoleCodeRefData"));
                refdata.put("invStatusCodeRefData", configMap.get("invStatusCodeRefData"));
                return refdata;           
        	}        	
        });
        
        flow.addTab(new Tab<Study>("Study Personnel", "Study Personnel", "study/study_personnels") {
            
        	public Map<String, Object> referenceData() {
                Map<String, Object> refdata = super.referenceData();  
                Map <String, List<Lov>> configMap = configurationProperty.getMap();
                //List<StudySite> studySites = new ArrayList<StudySite>();
                //refdata.put("researchStaffRefData", getResearchStaff());
                refdata.put("invRoleCodeRefData", configMap.get("invRoleCodeRefData"));
                refdata.put("invStatusCodeRefData", configMap.get("invStatusCodeRefData"));
                
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
		binder.registerCustomEditor(Agent.class, new DaoBasedEditor(
				agentDao));
		binder.registerCustomEditor(SiteInvestigator.class, new DaoBasedEditor(
				siteInvestigatorDao));
		binder.registerCustomEditor(ResearchStaff.class, new DaoBasedEditor(
				researchStaffDao));

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
				//if(request.getParameter("_action").equals("siteChange"))
				if("siteChange".equals(request.getParameter("_action")))
				{
					request.getSession().setAttribute("site_id", request.getParameter("_selected"));
					//Study st = (Study)command;										
					StudySite studySite = ((Study)command).getStudySites().get(Integer.parseInt(request.getParameter("_selected")));
					if(studySite.getStudyInvestigators().size() == 0 )
					{						
						StudyInvestigator studyInvestigator = new StudyInvestigator();	
						studyInvestigator.setSiteInvestigator(new SiteInvestigator());
						studySite.addStudyInvestigators(studyInvestigator);
					}
				}
				else {
					handleStudyInvestigatorAction((Study)command, request.getParameter("_action"),
							request.getParameter("_selected"), request.getParameter("_studysiteindex"));
				}					
				
				break;				

			case 4:				
				//if(request.getParameter("_action").equals("siteChange"))
				if("siteChange".equals(request.getParameter("_action")))
				{
					request.getSession().setAttribute("site_id_for_per", request.getParameter("_selected"));
					
					StudySite studySite = ((Study)command).getStudySites().get(Integer.parseInt(request.getParameter("_selected")));
					if(studySite.getStudyPersonnels().size() == 0 )
					{						
						StudyPersonnel studyPersonnel = new StudyPersonnel();						
						studySite.addStudyPersonnel(studyPersonnel);
					}										
				}
				else {
					handleStudyPersonnelAction((Study)command, request.getParameter("_action"),
							request.getParameter("_selected"), request.getParameter("_studysiteindex"));
				}					
				
				break;				
								
			case 5:
				handleStudyAgentAction((Study)command, request.getParameter("_action"),
						request.getParameter("_selected"));
				break;
			case 6:
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
	
	private void handleStudyInvestigatorAction(Study study, String action, String selected, String studysiteindex)
	{				
		if ("addInv".equals(action))
		{	
			StudyInvestigator studyInvestigator = new StudyInvestigator();
			studyInvestigator.setSiteInvestigator(new SiteInvestigator());
			StudySite studySite = study.getStudySites().get(Integer.parseInt(studysiteindex));
			studySite.addStudyInvestigators(studyInvestigator);														
		}
		else if ("removeInv".equals(action))
		{	
			study.getStudySites().get(Integer.parseInt(studysiteindex)).getStudyInvestigators().remove(Integer.parseInt(selected));
		}					
					
	}	
	
	private void handleStudyPersonnelAction(Study study, String action, String selected, String studysiteindex)
	{				
		if ("addInv".equals(action))
		{	
			StudyPersonnel studyPersonnel = new StudyPersonnel();
			StudySite studySite = study.getStudySites().get(Integer.parseInt(studysiteindex));
			studySite.addStudyPersonnel(studyPersonnel);														
		}
		else if ("removeInv".equals(action))
		{	
			study.getStudySites().get(Integer.parseInt(studysiteindex)).getStudyPersonnels().remove(Integer.parseInt(selected));
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
		// adding studyinvestigator.
		StudyInvestigator studyInvestigator = new StudyInvestigator();				
		studySite.addStudyInvestigators(studyInvestigator);			
		
		// adding studypersonnel
		StudyPersonnel studyPersonnel = new StudyPersonnel();
		studySite.addStudyPersonnel(studyPersonnel);
		
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

	public ResearchStaffDao getResearchStaffDao() {
		return researchStaffDao;
	}

	public void setResearchStaffDao(ResearchStaffDao researchStaffDao) {
		this.researchStaffDao = researchStaffDao;
	}

	public SiteInvestigatorDao getSiteInvestigatorDao() {
		return siteInvestigatorDao;
	}

	public void setSiteInvestigatorDao(SiteInvestigatorDao siteInvestigatorDao) {
		this.siteInvestigatorDao = siteInvestigatorDao;
	}        		
}