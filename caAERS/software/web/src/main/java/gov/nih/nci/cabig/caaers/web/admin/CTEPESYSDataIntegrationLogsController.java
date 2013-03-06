/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class CTEPESYSDataIntegrationLogsController extends SimpleFormController {
	
    private InputFieldGroupMap fieldMap;
    
    private Map<Object, Object> statusMap = new LinkedHashMap<Object, Object>();
    
    private Map<Object, Object> serviceMap = new LinkedHashMap<Object, Object>();
    
    private Configuration configuration;
    
  	
    @Required
    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

	public CTEPESYSDataIntegrationLogsController() {
		setCommandClass(CTEPESYSDataIntegrationLogsCommand.class);
		
		setBindOnNewForm(true);
		setFormView("admin/ctepesys_integration_logs");
        setSuccessView("admin/ctepesys_integration_logs");
        
        
        statusMap.put("", "Please select");
        statusMap.put("Success", "Success");
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
        serviceMap.put("GetLabsLOV", "GetLabsLOV");
        serviceMap.put("GetMergedOrganization", "GetMergedOrganization");
        serviceMap.put("GetAgentDoseUOMLOV", "GetAgentDoseUOMLOV");

        fieldMap = new InputFieldGroupMap();
        InputFieldGroup fieldGroup = new DefaultInputFieldGroup("main");

        //0
        InputField entityField = InputFieldFactory.createSelectField("service", "Service", false, serviceMap);
        fieldGroup.getFields().add(entityField);
        
        //1
        InputField synchStatusField = InputFieldFactory.createSelectField("status", "Status", false, statusMap);
        fieldGroup.getFields().add(synchStatusField);
        
        //2
        InputField startDateField = InputFieldFactory.createPastDateField("startDate", "From", false);
        fieldGroup.getFields().add(startDateField);
        
        //3
        InputField endDateField = InputFieldFactory.createPastDateField("endDate", "To", false);
        fieldGroup.getFields().add(endDateField);
        
        fieldMap.addInputFieldGroup(fieldGroup);
	}
	
    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
    	CTEPESYSDataIntegrationLogsCommand cmd =  new CTEPESYSDataIntegrationLogsCommand();
    	if(configuration.get(Configuration.ESB_LOG_LOCATION) != null ){
    		cmd.setEsbLogsLocationExists(true);
    	}
    	
    	return cmd;
    }
    
    @Override
	protected void initBinder(final HttpServletRequest request,final ServletRequestDataBinder binder) throws Exception {
		super.initBinder(request, binder);
		binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(false));
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

    @Override
    protected Map referenceData(final HttpServletRequest request, final Object command,
                    final Errors errors) throws Exception {

        Map<Object, Object> refDataMap = new LinkedHashMap<Object, Object>();
        refDataMap.put("fieldGroups", fieldMap);

        return refDataMap;

    }
    
}
