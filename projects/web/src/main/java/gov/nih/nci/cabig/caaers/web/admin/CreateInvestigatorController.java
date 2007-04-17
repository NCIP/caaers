package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.SiteDao;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Site;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.tools.editors.DaoBasedEditor;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.web.ListValues;
import gov.nih.nci.cabig.ctms.web.tabs.AbstractTabbedFlowFormController;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

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
public class CreateInvestigatorController extends AbstractTabbedFlowFormController<Investigator> {
		    	
	private InvestigatorDao investigatorDao;
	private SiteDao siteDao;	
	private ListValues listValues;
	private ConfigProperty configurationProperty;
	
	public CreateInvestigatorController() {		
        setCommandClass(Investigator.class);        

        Flow<Investigator> flow = new Flow<Investigator>("Create Investigator");
                
        flow.addTab(new Tab<Investigator>("Enter Investigator Information", "New Investigator", "admin/investigator_details") {
            public Map<String, Object> referenceData() {
                Map<String, Object> refdata = super.referenceData();
                Map <String, List<Lov>> configMap = configurationProperty.getMap();
                
                refdata.put("sitesRefData", getSites());
                refdata.put("studySiteStatusRefData", configMap.get("studySiteStatusRefData"));
    	  		refdata.put("studySiteRoleCodeRefData",  configMap.get("studySiteRoleCodeRefData"));
    	  		
                refdata.put("genders", listValues.getParticipantGender());
                refdata.put("ethnicity", listValues.getParticipantEthnicity());
                refdata.put("sources", listValues.getParticipantIdentifierSource());
                refdata.put("race", listValues.getParticipantRace());
                refdata.put("action", "New");
                return refdata;
            }
            
            @Override
            public void validate(Investigator command, Errors errors) {
                boolean firstName = command.getFirstName() == null || command.getFirstName().equals("");
                boolean lastName = command.getLastName() == null || command.getLastName().equals("");
                boolean dateOfBirth = command.getDateOfBirth() == null;
                boolean gender = command.getGender().equals("---");
                boolean ethnicity = command.getEthnicity().equals("---");
                boolean race = command.getRace().equals("---");
                if (firstName) errors.rejectValue("firstName", "REQUIRED", "Missing First Name");
                if (lastName) errors.rejectValue("lastName", "REQUIRED", "Missing Last Name");
                if (dateOfBirth) errors.rejectValue("dateOfBirth", "REQUIRED", "Missing Date Of Birth");
                if (gender) errors.rejectValue("gender", "REQUIRED", "Please Specify a Gender");
                if (ethnicity) errors.rejectValue("ethnicity", "REQUIRED", "Please Specify the Ethnicity");
                if (race) errors.rejectValue("race", "REQUIRED", "Please specify the Race");
            }
            
            @Override
            public boolean isAllowDirtyForward() {
                return false;
            }
        });
                                           
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
	 * Create a nested object graph that Create Investigator Design needs
	 * 
	 * @param request -
	 *            HttpServletRequest
	 * @throws ServletException
	 */
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {	
		return createInvestigatorWithDesign();		         
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractWizardFormController#processFinish
	 * (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, 
	 * java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, 
			Object command, BindException errors) throws Exception {
		
		Investigator inv = (Investigator) command;
		investigatorDao.save(inv);
		
		//ModelAndView modelAndView= new ModelAndView("admin/investigator_details");		
    	//modelAndView.addAllObjects(errors.getModel());
		//response.sendRedirect("createInvestigator");
		response.sendRedirect("viewInvestigator?fullName=" + inv.getFullName() + "&type=confirm");
    	return null;
	}
	
	@Override
	protected void postProcessPage(HttpServletRequest request, Object command,
			Errors arg2, int pageNo) throws Exception {
		
		switch (pageNo)
		{
			case 0:
				handleSiteInvestigatorAction((Investigator)command, request.getParameter("_action"),
					request.getParameter("_selected"));
				break;			
				
			default:
				//do nothing						
		}		
	}
	
	private void handleSiteInvestigatorAction(Investigator investigator, String action, String selected)
	{				
		if ("addSite".equals(action))
		{	
			SiteInvestigator siteInvestigator = new SiteInvestigator();						
			investigator.addSiteInvestigator(siteInvestigator);		
		}
		else if ("removeSite".equals(action))
		{				
			investigator.getSiteInvestigators().remove(Integer.parseInt(selected));
		}			
					
	}
	
	private Investigator createInvestigatorWithDesign()
	{
		Investigator investigator = new Investigator(); 			  
		SiteInvestigator siteInvestigator = new SiteInvestigator();
		investigator.addSiteInvestigator(siteInvestigator);
		
		return investigator;
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
					
	public InvestigatorDao getInvestigatorDao() {
		return investigatorDao;
	}

	public void setInvestigatorDao(InvestigatorDao investigatorDao) {
		this.investigatorDao = investigatorDao;
	}

	public ListValues getListValues() {
		return listValues;
	}

	public void setListValues(ListValues listValues) {
		this.listValues = listValues;
	}

	public ConfigProperty getConfigurationProperty() {
		return configurationProperty;
	}

	public void setConfigurationProperty(ConfigProperty configurationProperty) {
		this.configurationProperty = configurationProperty;
	}        		
}