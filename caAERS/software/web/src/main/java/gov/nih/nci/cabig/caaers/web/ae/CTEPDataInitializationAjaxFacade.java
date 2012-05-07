package gov.nih.nci.cabig.caaers.web.ae;

import edu.emory.mathcs.backport.java.util.Collections;
import gov.nih.nci.cabig.caaers.dao.IntegrationLogDao;
import gov.nih.nci.cabig.caaers.dao.query.IntegrationLogQuery;
import gov.nih.nci.cabig.caaers.domain.IntegrationLog;
import gov.nih.nci.cabig.caaers.domain.IntegrationLogDetail;
import gov.nih.nci.cabig.caaers.domain.SynchStatus;
import gov.nih.nci.cabig.caaers.domain.ajax.IntegrationLogAjaxableDomainObect;
import gov.nih.nci.cabig.caaers.service.ProxyWebServiceFacade;
import gov.nih.nci.cabig.caaers.web.AbstractAjaxFacade;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.semanticbits.coppasimulator.util.StringUtilities;


public class CTEPDataInitializationAjaxFacade extends AbstractAjaxFacade{
	private ProxyWebServiceFacade proxyWebServiceFacade;
	private IntegrationLogDao integrationLogDao;
	private static Class<?>[] CONTROLLERS = {CTEPESYSDataImportController.class};
	
	private static final String htmlSuccessString = "<font color='#008000'>Success</font>";
	private static final String htmlFailureString = "<font color='#D94444'>Failed</font>";

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
        
        if (!StringUtilities.isBlank(service)) {
        	String entity = extractEntity(service);
        	String operation = extractOperation(service);
    		query.filterByEntity(entity);
    		query.filterByOperation(operation);
        }
        // get all results that match service, start date and end date. Then do post filtering based on status
        results = integrationLogDao.searchIntegrationLogs(query);
        
        // group results by correlation id 
        Map<String,List<IntegrationLog>> map = groupIntegrationLogsBasedOnCorrelationId(results);
        
        Iterator<Entry<String, List<IntegrationLog>>> mapIterator = map.entrySet().iterator();
        while(mapIterator.hasNext()){
        	Map.Entry<String, List<IntegrationLog>> entry = (Map.Entry<String, List<IntegrationLog>>)mapIterator.next();
        	IntegrationLogAjaxableDomainObect ajaxIntLog = new IntegrationLogAjaxableDomainObect();
        	ajaxIntLog.setLoggedOn(getEarliestLogTime(entry.getValue()));
        	
        	// need to sort the grouped integration logs by id to display the synch status in work flow order
        	Collections.sort(entry.getValue(), new Comparator<IntegrationLog>() {
				public int compare(IntegrationLog o1, IntegrationLog o2) {
					return o1.getId().compareTo(o2.getId());
				}
			});
        
        	ajaxIntLog.setOverallStatus(determineIfOverallStatusIsFailed(entry.getValue()));
        	ajaxIntLog.setService(getServiceNameFromEntityAndOperation(entry.getValue().get(0).getEntity(), entry.getValue().get(0).getOperation()));
        	
        	for(IntegrationLog intLog:entry.getValue()){
        			ajaxIntLog.getSteps().put(intLog.getSynchStatus().getName(),intLog.getIfSuccess());
        	}
        	
        	if(StringUtilities.isBlank(status)) {
        		filteredResults.add(ajaxIntLog);
        	} else  if(status.equalsIgnoreCase("Failed") && isIncomplete(entry.getValue())) {
        		// if incomplete ones are wanted the status should be incomplete
        		filteredResults.add(ajaxIntLog);
        	} else  if(status.equalsIgnoreCase("Success") && !isIncomplete(entry.getValue())) {
        		// if complete ones are wanted the status should be complete. It should also check integration log details for instance level issues
        		filteredResults.add(ajaxIntLog);
        	} 
        }
        
        return filteredResults;
    }
    
    // if SynchStatus.REQUEST_COMPLETION is not present the messages with this correlation id are incomplete
    public boolean isIncomplete(List<IntegrationLog> integrationLogs){
    	for(IntegrationLog intLog : integrationLogs){
    		if(intLog.getSynchStatus() == SynchStatus.REQUEST_COMPLETION){
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    public Map<String,List<IntegrationLog>> groupIntegrationLogsBasedOnCorrelationId(List<IntegrationLog> integreationLogs){
    	
    	Map<String,List<IntegrationLog>> map = new LinkedHashMap<String,List<IntegrationLog>>();
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
    	String outcome = htmlFailureString;
    	for(IntegrationLog log:integrationLogs){
    		if(log.getSynchStatus() == SynchStatus.REQUEST_COMPLETION){
    			outcome = htmlSuccessString;
    		}
    		// if one of the integration log detail has failed, mark the overall outcome as failed
    		if(log.getIntegrationLogDetails().size()> 0){
    			for(IntegrationLogDetail intLogDetail : log.getIntegrationLogDetails()){
    				if(intLogDetail.isFailed()) {
    					return htmlSuccessString;
    				}
    			}
    		}
    	}
    	
    	return outcome;
    }
    
    
    public String extractEntity(String serviceName){
    	
    	if(serviceName.equalsIgnoreCase("GetStudyDiseases")) return "study";
    	if(serviceName.equalsIgnoreCase("GetStudyTreatmentAssignments")) return "treatmentAssignment";
    	if(serviceName.equalsIgnoreCase("GetStudyAgents")) return "studyAgent";
    	if(serviceName.equalsIgnoreCase("GetStudyOrganizations")) return "studyOrganization";
    	if(serviceName.equalsIgnoreCase("GetCTCAELOV")) return "ctcae";
    	if(serviceName.equalsIgnoreCase("GetAgentDoseUOMLOV")) return "agentDose";
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
    	
    	if(serviceName.equalsIgnoreCase("GetStudyDiseases")) return "getStudyDiseases";
    	if(serviceName.equalsIgnoreCase("GetStudyTreatmentAssignments")) return "getStudyTreatmentAssignments";
    	if(serviceName.equalsIgnoreCase("GetStudyAgents")) return "getStudyAgents";
    	if(serviceName.equalsIgnoreCase("GetStudyOrganizations")) return "getStudyOrganizations";
    	if(serviceName.equalsIgnoreCase("GetCTCAELOV")) return "getCTCAELOV";
    	if(serviceName.equalsIgnoreCase("GetAgentDoseUOMLOV")) return "getAgentDoseUOMLOV";
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
