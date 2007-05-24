package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.SiteDao;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Site;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.ctms.editors.DaoBasedEditor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author Priyatam
 */
public class CreateResearchStaffController extends SimpleFormController {
		    	
	private ResearchStaffDao researchStaffDao;
	private SiteDao siteDao;	
	

	public CreateResearchStaffController() {		
        setCommandClass(ResearchStaff.class);   
        setFormView("admin/research_staff_details");
		setSuccessView("admin/research_staff_details");
	}

	 @Override
	 protected Map<String, Object> referenceData(HttpServletRequest request) throws Exception {
		 Map<String, Object> refdata = new HashMap<String, Object>(); 
		 refdata.put("sitesRefData", siteDao.getAll());
		 return refdata;
	 }
 
    public void validate(ResearchStaff command, Errors errors) {
        boolean firstName = command.getFirstName() == null || command.getFirstName().equals("");
        boolean lastName = command.getLastName() == null || command.getLastName().equals("");
        if (firstName) errors.rejectValue("firstName", "REQUIRED", "Missing First Name");
        if (lastName) errors.rejectValue("lastName", "REQUIRED", "Missing Last Name");
    }
            
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		super.initBinder(request, binder);
		binder.registerCustomEditor(Date.class, ControllerTools
				.getDateEditor(true));
		binder.registerCustomEditor(Site.class, new DaoBasedEditor(
				siteDao));		
	}
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {	
		return new ResearchStaff();		         
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, 
			Object command, BindException errors) throws Exception {
		
		ResearchStaff staff = (ResearchStaff) command;
		researchStaffDao.save(staff);
		return new ModelAndView(new RedirectView("createResearchStaff"));
	}
	
	public SiteDao getSiteDao() {
		return siteDao;
	}

	public void setSiteDao(SiteDao siteDao) {
		this.siteDao = siteDao;
	}

	public ResearchStaffDao getResearchStaffDao() {
		return researchStaffDao;
	}

	public void setResearchStaffDao(ResearchStaffDao researchStaffDao) {
		this.researchStaffDao = researchStaffDao;
	}        		
}