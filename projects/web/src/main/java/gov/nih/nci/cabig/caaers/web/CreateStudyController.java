package gov.nih.nci.cabig.caaers.web;

import gov.nih.nci.cabig.caaers.dao.SiteDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Site;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.tools.editors.DaoBasedEditor;
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

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Kulasekaran
 *
 */
public class CreateStudyController extends AbstractTabbedFlowFormController<Study> {
		    	
	private StudyDao studyDao;
	private SiteDao siteDao;	
	
	public CreateStudyController() {
        setCommandClass(Study.class);
        setAllowDirtyForward(false);
        //TODO: this is a temp one. need to fix it.
        Flow<Study> flow = new Flow<Study>("Create Study");       
        
        Tab tab1 =  new Tab("Study Details", "Study Details", "study_details") {
            public Map<String, Object> referenceData() {
                Map<String, Object> refdata = super.referenceData();                    
                refdata.put("diseaseCodeRefData", getDiseaseCodeList());
    	  		refdata.put("monitorCodeRefData",  getMonitorCodeList());
    	  		refdata.put("phaseCodeRefData",  getPhaseCodeList());
    	  		refdata.put("sponsorCodeRefData",  getSponsorCodeList());
    	  		refdata.put("statusRefData",  getStatusList());;
    	  		refdata.put("typeRefData",  getTypeList());
    	  		refdata.put("multiInstitutionIndicator", getBooleanList());
    	  		refdata.put("randomizedIndicator", getBooleanList());
    	  		refdata.put("blindedIndicator", getBooleanList());
    	  		refdata.put("nciIdentifier", getBooleanList());
    	  		return refdata;
            }        	
        };
                
        flow.addTab(tab1);
        
        Tab tab2 = new Tab("Study Identifiers", "Study Identifiers", "study_identifiers"){
            
        	public Map<String, Object> referenceData() {
                Map<String, Object> refdata = super.referenceData();                             
                refdata.put("identifiersSourceRefData", getIdentifiersSourceList());
                refdata.put("identifiersTypeRefData", getIdentifiersTypeList());                    
    	  		return refdata;
        	}
        };
        
        tab2.isAllowDirtyForward();
        flow.addTab(tab2);
        
        Tab tab3 =  new Tab("Study Sites", "Study sites", "study_studysite") {
            
            	public Map<String, Object> referenceData() {
                    Map<String, Object> refdata = super.referenceData();                    
                    refdata.put("sitesRefData", getSites());
                    return refdata;
               
            }        	
        };
        flow.addTab(tab3);
        
        Tab tab4 = new Tab("Review and Submit", "Submit", "study_reviewsummary");
        
        flow.addTab(tab4);
        
        setFlow(flow);
        
        /*setFlow(new Flow("Create Study", Arrays.asList(            
            new Tab(0, "Study Details", "Details", "study_details") {
                public Map<String, Object> referenceData() {
                    Map<String, Object> refdata = super.referenceData();                    
                    refdata.put("diseaseCodeRefData", getDiseaseCodeList());
        	  		refdata.put("monitorCodeRefData",  getMonitorCodeList());
        	  		refdata.put("phaseCodeRefData",  getPhaseCodeList());
        	  		refdata.put("sponsorCodeRefData",  getSponsorCodeList());
        	  		refdata.put("statusRefData",  getStatusList());;
        	  		refdata.put("typeRefData",  getTypeList());
        	  		refdata.put("multiInstitutionIndicator", getBooleanList());
        	  		refdata.put("randomizedIndicator", getBooleanList());
        	  		refdata.put("blindedIndicator", getBooleanList());
        	  		refdata.put("nciIdentifier", getBooleanList());
        	  		return refdata;
                }
            },
            new Tab(1, "Study Indicator", "Indicator", "study_identifiers"),
            new Tab(2, "Study Sites", "sites", "study_studysite") {
                public Map<String, Object> referenceData() {
                    Map<String, Object> refdata = super.referenceData();                    
                    refdata.put("sitesRefData", getSites());                    
        	  		return refdata;
                }
            },            
            new Tab(3, "Review and Submit", "Review", "study_reviewsummary")                       
        ))); */
    }
	
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		super.initBinder(request, binder);
		binder.registerCustomEditor(Date.class, ControllerTools
				.getDateEditor(true));
		binder.registerCustomEditor(Site.class, new DaoBasedEditor(
				siteDao));
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
	
	/**
	* Method for custom validation logic for individual pages
	* @param command - form object with the current wizard state
	* @param errors - validation errors holder
	* @param page - number of page to validate
	*/
	
	/*protected void validatePage(Object command, Errors errors, int page) {
		Study study = (Study) command;
		StudyValidator validator = (StudyValidator) getValidator();
		switch (page) {
		case 0:
			validator.validatePage0(study, errors);
		break;
		}
	}*/

	/*protected Map<String, Object> referenceData(HttpServletRequest httpServletRequest, int page) 
  		throws Exception {
		// Currently the static data is a hack for an Lov this will be replaced with 
		// LovDao to get the static data from individual tables
		Map<String, Object> refdata = new HashMap<String, Object>();
		//Map <String, List<Lov>> configMap = configurationProperty.getMap();
		
	  	if (page == 0) {
	  		refdata.put("diseaseCodeRefData", getDiseaseCodeList());
	  		refdata.put("monitorCodeRefData",  getMonitorCodeList());
	  		refdata.put("phaseCodeRefData",  getPhaseCodeList());
	  		refdata.put("sponsorCodeRefData",  getSponsorCodeList());
	  		refdata.put("statusRefData",  getStatusList());;
	  		refdata.put("typeRefData",  getTypeList());
	  		refdata.put("multiInstitutionIndicator", getBooleanList());
	  		refdata.put("randomizedIndicator", getBooleanList());
	  		refdata.put("blindedIndicator", getBooleanList());
	  		refdata.put("nciIdentifier", getBooleanList());
	  		return refdata;
	  	}
	  	if (page == 2) {
	  		refdata.put("healthCareSitesRefData", getHealthcareSites());	  			  	
	  		return refdata;
	  	}
	  	
	  	return refdata;
	}*/
	
	
//	private void printLovs(String name, List<Lov> list)
//	{
//		System.out.println(name);
//		for (Lov lov : list) {
//			System.out.println("code - "+lov.getCode()+"\t"+"desc - "+lov.getDesc());
//		}
//	}
	
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
    	response.sendRedirect("createStudy");
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
	
	private Study createDefaultStudyWithDesign()
	{
		Study study = new Study(); 
			  
		StudySite studySite = new StudySite();
		study.addStudySite(studySite);				
			
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
	
	private List<Site> getSites()
	{
		/*List<HealthcareSite> h = new ArrayList<HealthcareSite>();		
		h.add(new HealthcareSite());
		h.add(new HealthcareSite());		
		return h;*/
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

	public class Lov {
		
		private String code;
		private String desc;	
		
		public Lov() {}

		public Lov(String code, String desc)
		{
			this.code=code;
			this.desc=desc;		
		}
				
		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}
		
		public String getDesc(){
			return desc;
		}
			
		public void setDesc(String desc){
			this.desc=desc;
		}
				
	}
	
	private List<Lov> getDiseaseCodeList(){
		List<Lov> col = new ArrayList<Lov>();
		Lov lov1 = new Lov("10028534", "Myelodysplastic syndrome NOS");
		Lov lov2 = new Lov("10038272", "Refractory anemia with ringed sideroblasts");
		Lov lov3 = new Lov("10024536", "Lip and/or oral cavity cancer stage 0");
		Lov lov4 = new Lov("10031098", "Oropharyngeal cancer recurrent");
		
		col.add(lov1);
    	col.add(lov2);
    	col.add(lov3);
    	col.add(lov4);
    	
    	return col;
	}
	
	private List<Lov> getMonitorCodeList(){
		List<Lov> col = new ArrayList<Lov>();
		Lov lov1 = new Lov("10028534", "Monitor Code 1");
		Lov lov2 = new Lov("10038272", "Monitor Code 2");
		Lov lov3 = new Lov("10024536", "Monitor Code 3");
		Lov lov4 = new Lov("10031098", "Monitor Code List 4");
		
		col.add(lov1);
    	col.add(lov2);
    	col.add(lov3);
    	col.add(lov4);
    	
    	return col;
	}
	
	private List<Lov> getPhaseCodeList(){
		List<Lov> col = new ArrayList<Lov>();
		Lov lov1 = new Lov("100", "Phase I");
		Lov lov2 = new Lov("101", "Phase I/II");
		Lov lov3 = new Lov("102", "Phase III");
		Lov lov4 = new Lov("103", "NOT APPLICABLE");
		
		col.add(lov1);
    	col.add(lov2);
    	col.add(lov3);
    	col.add(lov4);
    	
    	return col;
	}
	
	
	private List<Lov> getSponsorCodeList(){
		List<Lov> col = new ArrayList<Lov>();
		Lov lov1 = new Lov("200", "Sponsor 1 - Duke");
		Lov lov2 = new Lov("201", "Sponsor 2 - Nci");
		Lov lov3 = new Lov("202", "Sponsor 3 - FDA");
		
		col.add(lov1);
    	col.add(lov2);
    	col.add(lov3);
    		
    	return col;
	}
	
	private List<Lov> getStatusList(){
		List<Lov> col = new ArrayList<Lov>();
		Lov lov1 = new Lov("C", "Closed");
		Lov lov2 = new Lov("O", "Open");
		Lov lov3 = new Lov("S", "Suspended");
		Lov lov4 = new Lov("T", "Terminated");
		Lov lov5 = new Lov("I", "IRB Approved");
		
		col.add(lov1);
    	col.add(lov2);
    	col.add(lov3);
    	col.add(lov4);
    	col.add(lov5);
    	
    	return col;
	}

	private List<Lov> getTypeList(){
		List<Lov> col = new ArrayList<Lov>();
		Lov lov1 = new Lov("C", "Diagnostic");
		Lov lov2 = new Lov("GN", "Genetic Non-therapeutic");
		Lov lov3 = new Lov("GT", "Genetic Non-therapeutic");
		Lov lov4 = new Lov("N", "Non-therapeutic");
		Lov lov5 = new Lov("P", "Primary Treatment");
		Lov lov6 = new Lov("S", "Supportive");
		Lov lov7 = new Lov("P", "Preventive'");
		
		col.add(lov1);
    	col.add(lov2);
    	col.add(lov3);
    	col.add(lov4);
    	col.add(lov5);
    	col.add(lov6);
    	col.add(lov7);
    	
    	return col;
	}
	
	private List<Lov> getIdentifiersSourceList(){
		List<Lov> col = new ArrayList<Lov>();
		Lov lov1 = new Lov("D", "Duke University Comprehensive Cancer Center");
		Lov lov2 = new Lov("N", "NCI Clinical Trials Unit");
		Lov lov3 = new Lov("C", "CalGB");
	
		col.add(lov1);
    	col.add(lov2);
    	col.add(lov3);
    	
    	return col;
	}
	
	private List<Lov> getIdentifiersTypeList(){
		List<Lov> col = new ArrayList<Lov>();
		Lov lov1 = new Lov("PA", "Protocol Authority");
		Lov lov2 = new Lov("CC", "Co-ordinating Center");
		Lov lov3 = new Lov("S", "Site");
		Lov lov4 = new Lov("SI", "Site IRB");
		
		col.add(lov1);
    	col.add(lov2);
    	col.add(lov3);
    	col.add(lov4);
    	
    	return col;
	}        		
}