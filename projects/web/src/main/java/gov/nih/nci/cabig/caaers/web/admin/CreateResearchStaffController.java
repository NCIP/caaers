package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.web.ListValues;
import gov.nih.nci.cabig.caaers.web.tabbedflow.AbstractTabbedFlowFormController;
import gov.nih.nci.cabig.caaers.web.tabbedflow.Flow;
import gov.nih.nci.cabig.caaers.web.tabbedflow.Tab;

import java.util.Date;
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
public class CreateResearchStaffController extends AbstractTabbedFlowFormController<ResearchStaff> {
		    	
	private ResearchStaffDao researchStaffDao;
	private ListValues listValues;
	private ConfigProperty configurationProperty;
	
	public CreateResearchStaffController() {		
        setCommandClass(ResearchStaff.class);        

        Flow<ResearchStaff> flow = new Flow<ResearchStaff>("Create Research Staff");       
                
        flow.addTab(new Tab<ResearchStaff>("Enter Research Staff Information", "New Research Staff", "admin/research_staff_details") {
            public Map<String, Object> referenceData() {
                Map<String, Object> refdata = super.referenceData();
                                        	
                refdata.put("genders", listValues.getParticipantGender());
                refdata.put("ethnicity", listValues.getParticipantEthnicity());
                refdata.put("sources", listValues.getParticipantIdentifierSource());
                refdata.put("race", listValues.getParticipantRace());
                refdata.put("action", "New");
                return refdata;
            }
            
            @Override
            public void validate(ResearchStaff command, Errors errors) {
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
				
	}
	
	/**
	 * Create a nested object graph that Create Investigator Design needs
	 * 
	 * @param request -
	 *            HttpServletRequest
	 * @throws ServletException
	 */
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {	
		return new ResearchStaff();		         
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractWizardFormController#processFinish
	 * (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, 
	 * java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, 
			Object command, BindException errors) throws Exception {
		
		ResearchStaff researchStaff = (ResearchStaff) command;
		researchStaffDao.save(researchStaff);
		
		//ModelAndView modelAndView= new ModelAndView("admin/investigator_details");		
    	//modelAndView.addAllObjects(errors.getModel());
		//response.sendRedirect("createInvestigator");
		response.sendRedirect("viewResearchStaff?fullName=" + researchStaff.getFullName() + "&type=confirm");
    	return null;
	}
	
	@Override
	protected void postProcessPage(HttpServletRequest request, Object command,
			Errors arg2, int pageNo) throws Exception {
		
			
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

	public ResearchStaffDao getResearchStaffDao() {
		return researchStaffDao;
	}

	public void setResearchStaffDao(ResearchStaffDao researchStaffDao) {
		this.researchStaffDao = researchStaffDao;
	}        		
}