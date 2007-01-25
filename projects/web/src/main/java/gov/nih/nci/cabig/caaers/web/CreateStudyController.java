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
                refdata.put("identifiersSourceRefData", getSites());
                refdata.put("identifiersTypeRefData", getIdentifiersTypeList());                    
    	  		return refdata;
        	}
        });                 
        flow.addTab(new Tab<Study>("Study Sites", "Study Sites", "study_studysite") {
            
        	public Map<String, Object> referenceData() {
                Map<String, Object> refdata = super.referenceData();                    
                refdata.put("sitesRefData", getSites());
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
		Lov lov1 = new Lov("AIDS", "AIDS");
		Lov lov2 = new Lov("Benign", "Benign");
		Lov lov3 = new Lov("Cancer", "Cancer");		
		
		col.add(lov1);
    	col.add(lov2);
    	col.add(lov3);
    	
    	return col;
	}
	
	
	private List<Lov> getMonitorCodeList(){
		List<Lov> col = new ArrayList<Lov>();
		Lov lov1 = new Lov("Cancer Therapy Evaluation Program", "Cancer Therapy Evaluation Program");
		Lov lov2 = new Lov("CTEP - Clinical Data Update System Complete", "CTEP - Clinical Data Update System Complete");
		Lov lov3 = new Lov("CTEP - Clinical Data Update System Abbreviated", "CTEP - Clinical Data Update System Abbreviated");		
		
		col.add(lov1);
    	col.add(lov2);
    	col.add(lov3);
    	
    	return col;
	}
	
	private List<Lov> getPhaseCodeList(){
		List<Lov> col = new ArrayList<Lov>();
		Lov lov1 = new Lov("Phase I Trial", "Phase I Trial");
		Lov lov2 = new Lov("Phase I/II Trail", "Phase I/II Trail");
		Lov lov3 = new Lov("Phase II Trial", "Phase II Trial");
		Lov lov4 = new Lov("Phase III Trial", "Phase III Trial");
		Lov lov5 = new Lov("Phase IV Trial", "Phase IV Trial");
		Lov lov6 = new Lov("Phase 0 Trial", "Phase 0 Trial");
		
		col.add(lov1);
    	col.add(lov2);
    	col.add(lov3);
    	col.add(lov4);
    	col.add(lov5);
    	col.add(lov6);
    	
    	return col;
	}
		
	private List<Lov> getSponsorCodeList(){
		List<Lov> col = new ArrayList<Lov>();
		Lov lov1 = new Lov("National Cancer Institute", "National Cancer Institute");		
		
		col.add(lov1);
    		
    	return col;
	}
	
	private List<Lov> getStatusList(){
		List<Lov> col = new ArrayList<Lov>();
		Lov lov1 = new Lov("Active - Trial is open to accrual", "Active - Trial is open to accrual");
		Lov lov2 = new Lov("Administratively Complete", "Administratively Complete");
		Lov lov3 = new Lov("Approved - Trial has official CTEP approval", "Approved - Trial has official CTEP approval");
		Lov lov4 = new Lov("Closed to Accrual &amp; Treatment", "Closed to Accrual &amp; Treatment");
		Lov lov5 = new Lov("Closed to Accrual", "Closed to Accrual");
		Lov lov6 = new Lov("Temporarily Closed to Accrual &amp; Treatment", "Temporarily Closed to Accrual &amp; Treatment");
		Lov lov7 = new Lov("Temporarily Closed to Accrual", "Temporarily Closed to Accrual");
		
		col.add(lov1);
    	col.add(lov2);
    	col.add(lov3);
    	col.add(lov4);
    	col.add(lov5);
    	col.add(lov6);
    	col.add(lov7);
    	
    	return col;
	}

	private List<Lov> getTypeList(){
		List<Lov> col = new ArrayList<Lov>();
		Lov lov1 = new Lov("Diagnostic", "Diagnostic");
		Lov lov2 = new Lov("Genetic Non-therapeutic", "Genetic Non-therapeutic");		
		Lov lov4 = new Lov("Genetic Therapeutic", "Genetic Therapeutic");
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