package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.IntegrationLogDao;
import gov.nih.nci.cabig.caaers.web.ControllerTools;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class CTEPESYSDataImportController extends SimpleFormController {
	
	private IntegrationLogDao integrationLogDao;
	
	
	public CTEPESYSDataImportController() {
		setCommandClass(CTEPDataInitializationCommand.class);
		
		setBindOnNewForm(true);
		setFormView("admin/ctepesysDataImport");
        setSuccessView("admin/ctepesysDataImport");
        
	}
	
    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
    	CTEPDataInitializationCommand cmd =  new CTEPDataInitializationCommand();
    	cmd.setAgentsLastUpdated(integrationLogDao.getLastUpdatedTime("agent", "getAgentsLOV"));
    	cmd.setDevicesLastUpdated(integrationLogDao.getLastUpdatedTime("device", "getDevicesLOV"));
    	cmd.setOrganizationsLastUpdated(integrationLogDao.getLastUpdatedTime("organization", "getOrganizationsLOV"));
    	cmd.setAsaelLastUpdated(integrationLogDao.getLastUpdatedTime("asael", "getASAEL"));
    	cmd.setAgentDoseMeasureLastUpdated(integrationLogDao.getLastUpdatedTime("aduom", "getAgentDoseUOMLOV"));
    	cmd.setLabLastUpdated(integrationLogDao.getLastUpdatedTime("lab", "getLabsLOV"));
    	cmd.setPreExistingConditionsLastUpdated(integrationLogDao.getLastUpdatedTime("preexistingcondition", "getPreExistingConditionsLOV"));
    	cmd.setTherapiesLastUpdated(integrationLogDao.getLastUpdatedTime("priortherapy", "getTherapiesLOV"));
    	
    	return cmd;
    }
    
    @Override
	protected void initBinder(final HttpServletRequest request,final ServletRequestDataBinder binder) throws Exception {
		super.initBinder(request, binder);
		binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(false));
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		//binder.registerCustomEditor(Integer.class, ControllerTools.getDateEditor(false));
	}

    
    @Override
    protected Map referenceData(final HttpServletRequest request, final Object command,
                    final Errors errors) throws Exception {

    	CTEPDataInitializationCommand cmd = (CTEPDataInitializationCommand) command;
        Map<Object, Object> refDataMap = new LinkedHashMap<Object, Object>();
        
        Map<String,Boolean> itemsChecked = new LinkedHashMap<String, Boolean>();
        itemsChecked.put(CTEPDataInitializationCommand.ADUOM, cmd.isCtcaeChecked());
        itemsChecked.put(CTEPDataInitializationCommand.AGENTS, cmd.isAgentsChecked());
        itemsChecked.put(CTEPDataInitializationCommand.ASAEL, cmd.isAsaelChecked());
        itemsChecked.put(CTEPDataInitializationCommand.CTCAE, cmd.isCtcaeChecked());
        itemsChecked.put(CTEPDataInitializationCommand.DEVICES, cmd.isDevicesChecked());
        itemsChecked.put(CTEPDataInitializationCommand.LAB, cmd.isLabChecked());
        itemsChecked.put(CTEPDataInitializationCommand.ORGANIZATIONS, cmd.isOrganizationsChecked());
        itemsChecked.put(CTEPDataInitializationCommand.PRE_EXISTING_CONDITIONS, cmd.isPreExistingConditinosChecked());
        itemsChecked.put(CTEPDataInitializationCommand.THERAPIES, cmd.isTherapiesChecked());
        
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
    

	public void setIntegrationLogDao(IntegrationLogDao integrationLogDao) {
		this.integrationLogDao = integrationLogDao;
	}

}
