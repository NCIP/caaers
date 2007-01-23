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

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Kulasekaran
 */
public class CreateStudyController extends AbstractTabbedFlowFormController<Study> {
		    	
	private StudyDao studyDao;
	private SiteDao siteDao;	
	
	public CreateStudyController() {		
        setCommandClass(Study.class);        

        Flow<Study> flow = new Flow<Study>("Create Study");       
        
        flow.addTab(new Tab<Study>("Study Details", "Study Details", "study_details") {
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
        });
        flow.addTab(new Tab<Study>("Study Identifiers", "Study Identifiers", "study_identifiers"){
            
        	public Map<String, Object> referenceData() {
                Map<String, Object> refdata = super.referenceData();                             
                refdata.put("identifiersSourceRefData", getIdentifiersSourceList());
                refdata.put("identifiersTypeRefData", getIdentifiersTypeList());                    
    	  		return refdata;
        	}
        });                 
        flow.addTab(new Tab<Study>("Study Sites", "Study sites", "study_studysite") {
            
        	public Map<String, Object> referenceData() {
                Map<String, Object> refdata = super.referenceData();                    
                refdata.put("sitesRefData", getSites());
                return refdata;
           
        	}        	
        });
        flow.addTab(new Tab<Study>("Review and Submit", "Submit", "study_reviewsummary"));                
        
        setFlow(flow);        
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
		Lov lov1 = new Lov("Myelodysplastic syndrome NOS", "Myelodysplastic syndrome NOS");
		Lov lov2 = new Lov("Refractory anemia with ringed sideroblasts", "Refractory anemia with ringed sideroblasts");
		Lov lov3 = new Lov("Lip and/or oral cavity cancer stage 0", "Lip and/or oral cavity cancer stage 0");
		Lov lov4 = new Lov("Oropharyngeal cancer recurrent", "Oropharyngeal cancer recurrent");
		
		col.add(lov1);
    	col.add(lov2);
    	col.add(lov3);
    	col.add(lov4);
    	
    	return col;
	}
	
	private List<Lov> getMonitorCodeList(){
		List<Lov> col = new ArrayList<Lov>();
		Lov lov1 = new Lov("Monitor Code 1", "Monitor Code 1");
		Lov lov2 = new Lov("Monitor Code 2", "Monitor Code 2");
		Lov lov3 = new Lov("Monitor Code 3", "Monitor Code 3");
		Lov lov4 = new Lov("Monitor Code List 4", "Monitor Code List 4");
		
		col.add(lov1);
    	col.add(lov2);
    	col.add(lov3);
    	col.add(lov4);
    	
    	return col;
	}
	
	private List<Lov> getPhaseCodeList(){
		List<Lov> col = new ArrayList<Lov>();
		Lov lov1 = new Lov("Phase I", "Phase I");
		Lov lov2 = new Lov("Phase I/II", "Phase I/II");
		Lov lov3 = new Lov("Phase III", "Phase III");
		Lov lov4 = new Lov("NOT APPLICABLE", "NOT APPLICABLE");
		
		col.add(lov1);
    	col.add(lov2);
    	col.add(lov3);
    	col.add(lov4);
    	
    	return col;
	}
	
	
	private List<Lov> getSponsorCodeList(){
		List<Lov> col = new ArrayList<Lov>();
		Lov lov1 = new Lov("Sponsor 1 - Duke", "Sponsor 1 - Duke");
		Lov lov2 = new Lov("Sponsor 2 - Nci", "Sponsor 2 - Nci");
		Lov lov3 = new Lov("Sponsor 3 - FDA", "Sponsor 3 - FDA");
		
		col.add(lov1);
    	col.add(lov2);
    	col.add(lov3);
    		
    	return col;
	}
	
	private List<Lov> getStatusList(){
		List<Lov> col = new ArrayList<Lov>();
		Lov lov1 = new Lov("Closed", "Closed");
		Lov lov2 = new Lov("Open", "Open");
		Lov lov3 = new Lov("Suspended", "Suspended");
		Lov lov4 = new Lov("Terminated", "Terminated");
		Lov lov5 = new Lov("IRB Approved", "IRB Approved");
		
		col.add(lov1);
    	col.add(lov2);
    	col.add(lov3);
    	col.add(lov4);
    	col.add(lov5);
    	
    	return col;
	}

	private List<Lov> getTypeList(){
		List<Lov> col = new ArrayList<Lov>();
		Lov lov1 = new Lov("Diagnostic", "Diagnostic");
		Lov lov2 = new Lov("Genetic Non-therapeutic", "Genetic Non-therapeutic");		
		Lov lov4 = new Lov("Non-therapeutic", "Non-therapeutic");
		Lov lov5 = new Lov("Primary Treatment", "Primary Treatment");
		Lov lov6 = new Lov("Supportive", "Supportive");
		Lov lov7 = new Lov("Preventive", "Preventive");
		
		col.add(lov1);
    	col.add(lov2);    	
    	col.add(lov4);
    	col.add(lov5);
    	col.add(lov6);
    	col.add(lov7);
    	
    	return col;
	}
	
	private List<Lov> getIdentifiersSourceList(){
		List<Lov> col = new ArrayList<Lov>();
		Lov lov1 = new Lov("Duke University Comprehensive Cancer Center", "Duke University Comprehensive Cancer Center");
		Lov lov2 = new Lov("NCI Clinical Trials Unit", "NCI Clinical Trials Unit");
		Lov lov3 = new Lov("CalGB", "CalGB");
	
		col.add(lov1);
    	col.add(lov2);
    	col.add(lov3);
    	
    	return col;
	}
	
	private List<Lov> getIdentifiersTypeList(){
		List<Lov> col = new ArrayList<Lov>();
		Lov lov1 = new Lov("Protocol Authority", "Protocol Authority");
		Lov lov2 = new Lov("Co-ordinating Center", "Co-ordinating Center");
		Lov lov3 = new Lov("Site", "Site");
		Lov lov4 = new Lov("Site IRB", "Site IRB");
		
		col.add(lov1);
    	col.add(lov2);
    	col.add(lov3);
    	col.add(lov4);
    	
    	return col;
	}        		
}