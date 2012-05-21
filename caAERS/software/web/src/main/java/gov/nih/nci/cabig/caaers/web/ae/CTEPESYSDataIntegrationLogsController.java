package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.IntegrationLogDao;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class CTEPESYSDataIntegrationLogsController extends SimpleFormController {
	
	private IntegrationLogDao integrationLogDao;
	
	public void setIntegrationLogDao(IntegrationLogDao integrationLogDao) {
		this.integrationLogDao = integrationLogDao;
	}

	private static final String PAGINATION_ACTION = "paginationAction";
    
    private static final String CURRENT_PAGE_NUMBER = "currentPageNumber";
    
    private InputFieldGroupMap fieldMap;
    
    private Map<Object, Object> statusMap = new LinkedHashMap<Object, Object>();
    
    private Map<Object, Object> serviceMap = new LinkedHashMap<Object, Object>();
    
	
	public CTEPESYSDataIntegrationLogsController() {
		setCommandClass(CTEPESYSDataIntegrationLogsCommand.class);
		
		setBindOnNewForm(true);
		setFormView("admin/ctepesys_integration_logs");
        setSuccessView("admin/ctepesys_integration_logs");
        
        
        statusMap.put("", "Please select");
        statusMap.put("Success", "Success");
   //     statusMap.put("In Progress", "In Progress");
        statusMap.put("Failed", "Failed");
        
        serviceMap.put("", "Please select");
        serviceMap.put("SearchStudy", "SearchStudy");
        serviceMap.put("GetStudyDetails", "GetStudyDetails");
        serviceMap.put("GetStudyDiseases", "GetStudyDiseases");
        serviceMap.put("GetStudyTreatmentAssignments", "GetStudyTreatmentAssignments");
        serviceMap.put("GetStudyAgents", "GetStudyAgents");
        serviceMap.put("GetStudyOrganizations", "GetStudyOrganizations");
        serviceMap.put("GetASAEL", "GetASAEL");
        serviceMap.put("GetOrganizationLOV", "GetOrganizationLOV");
        serviceMap.put("GetAgentsLOV", "GetAgentsLOV");
        serviceMap.put("GetCTCAELOV", "GetCTCAELOV");
        serviceMap.put("GetDeviceLOV", "GetDeviceLOV");
        serviceMap.put("GetPre-ExistingConditionsLOV", "GetPre-ExistingConditionsLOV");
        serviceMap.put("GetTherapiesLOV", "GetTherapiesLOV");
        serviceMap.put("GetAgentDoseUOMLOV", "GetAgentDoseUOMLOV");
        serviceMap.put("GetLabLOV", "GetLabLOV");
        serviceMap.put("GetMergedOrganization", "GetMergedOrganization");
        serviceMap.put("GetAgentDoseUOMLOV", "GetAgentDoseUOMLOV");

        fieldMap = new InputFieldGroupMap();
        InputFieldGroup fieldGroup = new DefaultInputFieldGroup("main");

        //0
        InputField attributionField = InputFieldFactory.createSelectField("actions", "Filter on", false, createFilterOptions());
        fieldGroup.getFields().add(attributionField);
        
        //1
        InputField entityField = InputFieldFactory.createSelectField("service", "Service", false, serviceMap);
        fieldGroup.getFields().add(entityField);
        
        //2
        InputField synchStatusField = InputFieldFactory.createSelectField("status", "Status", false, statusMap);
        fieldGroup.getFields().add(synchStatusField);
        
        //3
        InputField startDateField = InputFieldFactory.createPastDateField("startDate", "From", false);
        fieldGroup.getFields().add(startDateField);
        
        //4
        InputField endDateField = InputFieldFactory.createPastDateField("endDate", "To", false);
        fieldGroup.getFields().add(endDateField);
        
        fieldMap.addInputFieldGroup(fieldGroup);
	}
	
	protected Map<Object, Object> createFilterOptions() {
        Map<Object, Object> filterOptions = new LinkedHashMap<Object, Object>();
        filterOptions.put("week", "Data synched in last 7 days");
        filterOptions.put("service", "Service");
        filterOptions.put("status", "Status");
        filterOptions.put("daterange", "Date Range");
        return filterOptions;
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
    	CTEPESYSDataIntegrationLogsCommand cmd =  new CTEPESYSDataIntegrationLogsCommand();
    	
    	return cmd;
    }
    
    @Override
	protected void initBinder(final HttpServletRequest request,final ServletRequestDataBinder binder) throws Exception {
		super.initBinder(request, binder);
		binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(false));
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		//binder.registerCustomEditor(Integer.class, ControllerTools.getDateEditor(false));
	}

    
    /**
     * It is a form submission, if participant, or study is available 
     */
    @Override
    @SuppressWarnings("unchecked")
    protected boolean isFormSubmission(HttpServletRequest request) {

    	Set<String> paramNames = request.getParameterMap().keySet();
    	boolean hasActions = paramNames.contains("actions");
    	String paginationAction = (String)findInRequest(request, PAGINATION_ACTION);
    	
    	return hasActions || paginationAction != null;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    protected Map referenceData(final HttpServletRequest request, final Object command,
                    final Errors errors) throws Exception {

        Map<Object, Object> refDataMap = new LinkedHashMap<Object, Object>();
        refDataMap.put("fieldGroups", fieldMap);

        return refDataMap;

    }
    
	/**
     * Returns the value associated with the <code>attributeName</code>, if present in
     * HttpRequest parameter, if not available, will check in HttpRequest attribute map.
     */
    protected Object findInRequest(final ServletRequest request, final String attributName) {

        Object attr = request.getParameter(attributName);
        if (attr == null) {
            attr = request.getAttribute(attributName);
        }
        return attr;
    }

}
