package gov.nih.nci.cabig.caaers.web.ae;

import edu.emory.mathcs.backport.java.util.Collections;
import gov.nih.nci.cabig.caaers.dao.IntegrationLogDao;
import gov.nih.nci.cabig.caaers.dao.IntegrationLogDetailDao;
import gov.nih.nci.cabig.caaers.dao.query.IntegrationLogDetailQuery;
import gov.nih.nci.cabig.caaers.dao.query.IntegrationLogQuery;
import gov.nih.nci.cabig.caaers.domain.IntegrationLog;
import gov.nih.nci.cabig.caaers.domain.IntegrationLogDetail;
import gov.nih.nci.cabig.caaers.domain.SynchStatus;
import gov.nih.nci.cabig.caaers.domain.ajax.IntegrationLogAjaxableDomainObect;
import gov.nih.nci.cabig.caaers.integration.schema.common.Status;
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

import org.apache.commons.lang.StringUtils;

import com.semanticbits.coppasimulator.util.StringUtilities;


public class CTEPDataInitializationAjaxFacade extends AbstractAjaxFacade{
	private ProxyWebServiceFacade proxyWebServiceFacade;
	public void setIntegrationLogDetailDao(
			IntegrationLogDetailDao integrationLogDetailDao) {
		this.integrationLogDetailDao = integrationLogDetailDao;
	}

	private IntegrationLogDao integrationLogDao;
	private IntegrationLogDetailDao integrationLogDetailDao;
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
			boolean therapiesChecked, boolean agentDoseMeasureChecked, boolean labChecked,
			boolean agentsChecked, boolean asaelChecked, boolean organizationsChecked) {
		
		if(devicesChecked) proxyWebServiceFacade.syncDevices();
		if(preExistingConditionsChecked) proxyWebServiceFacade.syncPreExistingConditionLOV();
		if(therapiesChecked) proxyWebServiceFacade.syncPriorTherapyLOV();
		if(agentsChecked) proxyWebServiceFacade.syncAgents();
		if(asaelChecked) proxyWebServiceFacade.syncASAEL();
		if(organizationsChecked) proxyWebServiceFacade.syncOrganizations();
		if(agentDoseMeasureChecked) proxyWebServiceFacade.syncAgentUOM();
		if(labChecked)proxyWebServiceFacade.syncLabs();

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
        		query.filterByEntity(entity);
	        	if(service.equalsIgnoreCase("GetStudyDetails")) {
	        		query.filterByOperation("updateStudy","createStudy");
	        	} else {
		        	String operation = extractOperation(service);
		    		query.filterByOperation(operation);
	        	}
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
        
        	ajaxIntLog.setOverallStatus(getIfIncompleteOrFailed(entry.getValue())?htmlFailureString:htmlSuccessString);
        	ajaxIntLog.setService(getServiceNameFromEntityAndOperation(entry.getValue().get(0).getEntity(), entry.getValue().get(0).getOperation()));
        	
        	for(IntegrationLog intLog:entry.getValue()){
        			ajaxIntLog.getSteps().put(intLog.getSynchStatus().getName(),intLog.getIfSuccess());
        	}
        	
        	if(StringUtilities.isBlank(status)) {
        		filteredResults.add(ajaxIntLog);
        	} else  if(status.equalsIgnoreCase("Failed") && getIfIncompleteOrFailed(entry.getValue())) {
        		filteredResults.add(ajaxIntLog);
        	} else  if(status.equalsIgnoreCase("Success") && !getIfIncompleteOrFailed(entry.getValue())) {
        		filteredResults.add(ajaxIntLog);
        	} 
        }
        
        return filteredResults;
    }
    
 // get integration log details based on correlation id
    public  List<IntegrationLogDetail> getIntegrationLogDetailsBasedOnCorrelationId(String correlationId) {

    	List<IntegrationLogDetail> integrationLogDetails = new ArrayList<IntegrationLogDetail>();
    	IntegrationLogDetailQuery query = new IntegrationLogDetailQuery();
    	query.filterByCorrelationId(correlationId);
    	integrationLogDetails = integrationLogDetailDao.searchIntegrationLogDetails(query);
        
        return integrationLogDetails;
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
    
    
    // if SynchStatus.REQUEST_COMPLETION is not present or partially processed is present in the notes, the messages with this correlation id are incomplete/failed
    public boolean getIfIncompleteOrFailed(List<IntegrationLog> integrationLogs){
    	boolean failed = true;
    	for(IntegrationLog intLog : integrationLogs){
    		// if it is partially processed, it is considered to be incomplete
    		if(!StringUtils.isBlank(intLog.getNotes()) && intLog.getNotes().contains(Status.PARTIALLY_PROCESSED.value()))
    			return true;
    		if(intLog.getSynchStatus() == SynchStatus.REQUEST_COMPLETION){
    			failed = false;
    		}
    	}
    	
    	return failed;
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
    	if(serviceName.equalsIgnoreCase("GetDeviceLOV")) return "device";
    	if(serviceName.equalsIgnoreCase("GetPre-ExistingConditionsLOV")) return "preexistingcondition";
    	if(serviceName.equalsIgnoreCase("GetTherapiesLOV")) return "priortherapy";
    	if(serviceName.equalsIgnoreCase("GetAgentsLOV")) return "agent";
    	if(serviceName.equalsIgnoreCase("GetLabLOV")) return "lab";
    	if(serviceName.equalsIgnoreCase("SearchStudy")) return "study";
    	if(serviceName.equalsIgnoreCase("GetStudyDetails")) return "study";
    	if(serviceName.equalsIgnoreCase("GetMergedOrganization")) return "organization";
    	if(serviceName.equalsIgnoreCase("GetAgentDoseUOMLOV")) return "agentDoseUOM";

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
    	if(serviceName.equalsIgnoreCase("GetStudyDetails")) return "updateStudy";
    	if(serviceName.equalsIgnoreCase("GetMergedOrganization")) return "getMergedOrganization";
    	if(serviceName.equalsIgnoreCase("GetAgentDoseUOMLOV")) return "getAgentDoseUOMLOV";

        return null;
    }
    
    // currently gets the service name entirely from operation name
    private String getServiceNameFromEntityAndOperation(String entity, String operation){
    	if(operation.equalsIgnoreCase("updateStudy") || operation.equalsIgnoreCase("createStudy")){
    		return "GetStudyDetails";
    	}
    	StringBuffer serviceName = new StringBuffer(Character.toString((Character.toUpperCase(operation.charAt(0)))));
    	serviceName.append(operation.substring(1));
    	return serviceName.toString();
    }
    

	@Override
	public Class<?>[] controllers() {
		return CONTROLLERS;
	}
}
