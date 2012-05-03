package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.IntegrationLogDao;
import gov.nih.nci.cabig.caaers.dao.query.IntegrationLogQuery;
import gov.nih.nci.cabig.caaers.domain.IntegrationLog;
import gov.nih.nci.cabig.caaers.domain.SynchStatus;
import gov.nih.nci.cabig.caaers.domain.ajax.IntegrationLogAjaxableDomainObect;
import gov.nih.nci.cabig.caaers.service.ProxyWebServiceFacade;
import gov.nih.nci.cabig.caaers.web.AbstractAjaxFacade;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.semanticbits.coppasimulator.util.StringUtilities;

public class CTEPDataInitializationAjaxFacade extends AbstractAjaxFacade{
	private ProxyWebServiceFacade proxyWebServiceFacade;
	private IntegrationLogDao integrationLogDao;
	 private static Class<?>[] CONTROLLERS = {CTEPESYSDataImportController.class};

	public void setProxyWebServiceFacade(
			ProxyWebServiceFacade proxyWebServiceFacade) {
		this.proxyWebServiceFacade = proxyWebServiceFacade;
	}

	public void setIntegrationLogDao(IntegrationLogDao integrationLogDao) {
		this.integrationLogDao = integrationLogDao;
	}

	public int importCTEPData(boolean ctcaeChecked, boolean devicesChecked, boolean preExistingConditionsChecked, 
			boolean therapiesChecked, boolean agentDoseMeasureChecked, boolean lobChecked,
			boolean agentsChecked, boolean asaelChecked, boolean organizationsChecked) {
		
		if(devicesChecked) proxyWebServiceFacade.syncDevices();
		if(preExistingConditionsChecked) proxyWebServiceFacade.syncPreExistingConditionLOV();
		if(therapiesChecked) proxyWebServiceFacade.syncPriorTherapyLOV();
		if(agentsChecked) proxyWebServiceFacade.syncAgents();
		if(asaelChecked) proxyWebServiceFacade.syncASAEL();
		if(organizationsChecked) proxyWebServiceFacade.syncOrganizations();
		
		return 1;

	}
	
	// display integration logs
    public  List<IntegrationLogAjaxableDomainObect> searchIntegrationLogs(Date startDate, Date endDate, String status, String service) {

        List<IntegrationLogAjaxableDomainObect> filteredResults = new ArrayList<IntegrationLogAjaxableDomainObect>(); 
        List<IntegrationLog> results = new ArrayList<IntegrationLog>();
        
        IntegrationLogQuery query = new IntegrationLogQuery();
        
        query.filterByLoggedOnStartDateAndEndDate(startDate, endDate);
        
        if (!StringUtilities.isBlank(status)) {
        	if(status.equalsIgnoreCase("Failed")){
        		query.filterByFailed();
        	} else if(status.equalsIgnoreCase("Success")){
        		query.filterBySuccess();
        	}
    	}
        
        if (!StringUtilities.isBlank("service")) {
        	String entity = extractEntity(service);
        	String operation = extractOperation(service);
    		query.filterByEntity(entity);
    		query.filterByOperation(operation);
        }
        
        
        results = integrationLogDao.searchIntegrationLogs(query);
        Map<String,List<IntegrationLog>> map = groupIntegrationLogsBasedOnCorrelationId(results);
        
        Iterator<Entry<String, List<IntegrationLog>>> mapIterator = map.entrySet().iterator();
        while(mapIterator.hasNext()){
        	Map.Entry<String, List<IntegrationLog>> entry = (Map.Entry<String, List<IntegrationLog>>)mapIterator.next();
        	IntegrationLogAjaxableDomainObect ajaxIntLog = new IntegrationLogAjaxableDomainObect();
        	ajaxIntLog.setLoggedOn(getEarliestLogTime(entry.getValue()));
        	ajaxIntLog.setOverallStatus(determineIfOverallStatusIsFailed(entry.getValue()));
        	ajaxIntLog.setService(getServiceNameFromEntityAndOperation(entry.getValue().get(0).getEntity(), entry.getValue().get(0).getOperation()));
        	for(IntegrationLog intLog:entry.getValue()){
        		if (!intLog.getIfSuccess().equalsIgnoreCase("Success") && !StringUtilities.isBlank(intLog.getNotes())) {
        			ajaxIntLog.getSteps().put(intLog.getSynchStatus().getName(),intLog.getIfSuccess() + " " + intLog.getNotes());
        		} else {
        			ajaxIntLog.getSteps().put(intLog.getSynchStatus().getName(),intLog.getIfSuccess());
        		}
        		
        	}
        	
        	filteredResults.add(ajaxIntLog);
        }
        
        return filteredResults;
    }
    
    public Map<String,List<IntegrationLog>> groupIntegrationLogsBasedOnCorrelationId(List<IntegrationLog> integreationLogs){
    	
    	Map<String,List<IntegrationLog>> map = new HashMap<String,List<IntegrationLog>>();
		for(IntegrationLog intlog:integreationLogs){
			if(!map.keySet().contains(intlog.getCorrelationId())){
				map.put(intlog.getCorrelationId(), new ArrayList<IntegrationLog>());
			} 
			map.get(intlog.getCorrelationId()).add(intlog);
		}
		
		return map;
    }
    
    
    public Date getEarliestLogTime(List<IntegrationLog> integrationLogs){
    	
    	if (integrationLogs == null || integrationLogs.size() == 0) { return null;};
    	
    	Date firstLogTime = new Date();
    	for(IntegrationLog log:integrationLogs){
    		if(log.getLoggedOn().before(firstLogTime)){
    			firstLogTime = log.getLoggedOn();
    		}
    	}
    	
    	return firstLogTime;
    }
    
    public String determineIfOverallStatusIsFailed(List<IntegrationLog> integrationLogs){
    	
    	for(IntegrationLog log:integrationLogs){
    		if(log.getSynchStatus() == SynchStatus.REQUST_PROCESSING_ERROR){
    			return "Failed";
    		}
    	}
    	
    	return "Success";
    }
    
    
    public String extractEntity(String serviceName){
    	
    	if(serviceName.equalsIgnoreCase("GetOrganizationLOV")) return "organization";
    	if(serviceName.equalsIgnoreCase("GetASAEL")) return "asael";
    	if(serviceName.equalsIgnoreCase("GetDeviceLOV")) return "device	";
    	if(serviceName.equalsIgnoreCase("GetPre-ExistingConditionsLOV")) return "preexistingcondition";
    	if(serviceName.equalsIgnoreCase("GetTherapiesLOV")) return "priortherapy";
    	if(serviceName.equalsIgnoreCase("GetAgentsLOV")) return "agent";
    	if(serviceName.equalsIgnoreCase("GetLabLOV")) return "lab";
    	if(serviceName.equalsIgnoreCase("SearchStudy")) return "study";
    	if(serviceName.equalsIgnoreCase("GetStudyDetails")) return "study";
    	
        return null;
    }
    
    public String extractOperation(String serviceName){
    	
    	if(serviceName.equalsIgnoreCase("GetOrganizationLOV")) return "getOrganizationsLOV";
    	if(serviceName.equalsIgnoreCase("GetASAEL")) return "getASAEL";
    	if(serviceName.equalsIgnoreCase("GetDeviceLOV")) return "getDevicesLOV";
    	if(serviceName.equalsIgnoreCase("GetPre-ExistingConditionsLOV")) return "getPreExistingConditionsLOV";
    	if(serviceName.equalsIgnoreCase("GetTherapiesLOV")) return "getTherapiesLOV";
    	if(serviceName.equalsIgnoreCase("GetAgentsLOV")) return "getAgentsLOV";
    	if(serviceName.equalsIgnoreCase("GetLabLOV")) return "getLabLOV";
    	if(serviceName.equalsIgnoreCase("SearchStudy")) return "searchStudy";
    	if(serviceName.equalsIgnoreCase("GetStudyDetails")) return "getStudyDetails";
    	
        return null;
    }
    
    // currently gets the service name entirely from operation name
    private String getServiceNameFromEntityAndOperation(String entity, String operation){
    	StringBuffer serviceName = new StringBuffer(Character.toString((Character.toUpperCase(operation.charAt(0)))));
    	serviceName.append(operation.substring(1));
    	return serviceName.toString();
    }
    

	@Override
	public Class<?>[] controllers() {
		return CONTROLLERS;
	}
}
