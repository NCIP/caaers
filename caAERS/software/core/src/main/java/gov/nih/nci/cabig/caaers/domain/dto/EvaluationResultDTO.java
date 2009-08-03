package gov.nih.nci.cabig.caaers.domain.dto;

import java.util.Collections;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.comparator.AdverseEventComprator;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author Biju Joseph
 *
 */
public class EvaluationResultDTO {
	
	
	//stores aeReportId and list of report definitions
	Map<Integer, Set<ReportDefinition>> aeReportIndexMap = new HashMap<Integer, Set<ReportDefinition>>();
	//stores aeReportId, and list of aes evaluated
	Map<Integer, List<AdverseEvent>> evaluatedAeMap = new HashMap<Integer, List<AdverseEvent>>();
	
	//stores aeReportId and actual set of adverse events (including the new ones). 
	Map<Integer, List<AdverseEvent>> allAeMap = new HashMap<Integer, List<AdverseEvent>>();
	
	//stores aeReportId, and alert needed 
	Map<Integer, Boolean> aeReportAlertMap = new HashMap<Integer, Boolean>();
	
	//will store the result of rules engine, as it is.[aeReportId - (adverseEvent - {ReportDefinitionNames} ]
	Map<Integer, Map<AdverseEvent, List<String>>> rulesEngineResultMap = new HashMap<Integer, Map<AdverseEvent, List<String>>>();
	Map<Integer, Map<AdverseEvent, Set<String>>> processedRulesEngineResultMap = new HashMap<Integer, Map<AdverseEvent,Set<String>>>();
	
	//will store [aeReportId - {adverseEventId - [ReportDefinitionId] }]
	Map<Integer, Map<AdverseEvent,Set<ReportDefinition>>> adverseEventIndexMap = new HashMap<Integer, Map<AdverseEvent,Set<ReportDefinition>>>();
	
	//stores report definition and aeReports associated
//	Map<ReportDefinition, Set<ExpeditedAdverseEventReport>> reportDefAeReportIndexMap = new HashMap<ReportDefinition, Set<ExpeditedAdverseEventReport>>();

	//stores adverseEventId - {report definition}, tells which ae got reported in which report.
	Map<Integer, List<ReportDefinition>> reportedAEIndexMap = new HashMap<Integer, List<ReportDefinition>>();
	
	//aeReportId - ReportDefinitions
	Map<Integer, Set<ReportDefinitionWrapper>> amendmentMap = new HashMap<Integer, Set<ReportDefinitionWrapper>>();
	Map<Integer, Set<ReportDefinitionWrapper>> withdrawalMap = new HashMap<Integer, Set<ReportDefinitionWrapper>>();
	Map<Integer, Set<ReportDefinitionWrapper>> editMap = new HashMap<Integer, Set<ReportDefinitionWrapper>>();
	Map<Integer, Set<ReportDefinitionWrapper>> createMap = new  HashMap<Integer, Set<ReportDefinitionWrapper>>();
	
	/**
	 * Will find the report definition, matching by name, from aeReportIndexMap.
	 * @param aeReportId
	 * @param reportDefinitionName
	 * @return
	 */
	private ReportDefinition findReportDefinition(Integer aeReportId, String reportDefinitionName){
		Set<ReportDefinition> reportDefinitions = aeReportIndexMap.get(aeReportId);
		
		if(reportDefinitions != null){
			for(ReportDefinition rd : reportDefinitions){
				if(rd.getName().equals(reportDefinitionName)) return rd;
			}
		}
		
		return null;
	}
	
	/**
	 * Will re-index the adverseEventIndexMap.
	 */
	public void refreshAdverseEventIndexMap(){
		adverseEventIndexMap.clear();
		
		for(Integer aeReportId : processedRulesEngineResultMap.keySet()){
			
			Map<AdverseEvent , Set<ReportDefinition>> aeMap = new HashMap<AdverseEvent, Set<ReportDefinition>>();
			adverseEventIndexMap.put(aeReportId, aeMap);
			
			Map<AdverseEvent, Set<String>> reportNameMap = processedRulesEngineResultMap.get(aeReportId);
			if(reportNameMap != null){
				for(AdverseEvent adverseEvent : reportNameMap.keySet()){
					Set<ReportDefinition> rdSet = new HashSet<ReportDefinition>();
					aeMap.put(adverseEvent, rdSet);
					
					Set<String> reportNameSet = reportNameMap.get(adverseEvent);
					if(reportNameSet != null){
						for(String reportDefinitionName : reportNameSet){
							ReportDefinition rd = findReportDefinition(aeReportId, reportDefinitionName);
							if(rd != null){
								rdSet.add(rd);
							}
						}//for reportDefinitionName
					}
				}//for adverseEvent
			}
		}//for aeReportId
	}
	
	public void addRulesEngineResult(Integer aeReportId, Map<AdverseEvent, List<String>> map){
		
		Map<AdverseEvent, List<String>> suggestionMap = new HashMap<AdverseEvent, List<String>>();
		Map<AdverseEvent, Set<String>> processedMap = new HashMap<AdverseEvent, Set<String>>();
		for(AdverseEvent adverseEvent : map.keySet()){
			suggestionMap.put(adverseEvent, new ArrayList<String>(map.get(adverseEvent)));
			processedMap.put(adverseEvent, new HashSet<String>(map.get(adverseEvent)));
		}
		
		rulesEngineResultMap.put(aeReportId, suggestionMap);
		processedRulesEngineResultMap.put(aeReportId, processedMap);
	}

	
	public void addEvaluatedAdverseEvents(Integer aeReportId, List<AdverseEvent> events){
		List<AdverseEvent> sortedEvents = new ArrayList<AdverseEvent>(events);
		Collections.sort(sortedEvents, AdverseEventComprator.DEFAULT_ADVERSE_EVENT_COMPARATOR);
		evaluatedAeMap.put(aeReportId, sortedEvents);
	}
	
	public void addAllAdverseEvents(Integer aeReportId, List<AdverseEvent> events){
		List<AdverseEvent> sortedEvents = new ArrayList<AdverseEvent>(events);
		Collections.sort(sortedEvents, AdverseEventComprator.DEFAULT_ADVERSE_EVENT_COMPARATOR);
		allAeMap.put(aeReportId, sortedEvents);
	}
	
	public void addResult(ExpeditedAdverseEventReport aeReport, List<ReportDefinition> reportDefintions){
		Integer aeReportId = (aeReport == null) ? new Integer(0) : aeReport.getId();
		
		aeReportIndexMap.put(aeReportId, new HashSet<ReportDefinition>(reportDefintions));
		
//		for(ReportDefinition reportDefinition : reportDefintions){
//			Set<ExpeditedAdverseEventReport> aeReports = reportDefAeReportIndexMap.get(reportDefinition);
//			if(aeReports == null){
//				aeReports = new HashSet<ExpeditedAdverseEventReport>();
//				reportDefAeReportIndexMap.put(reportDefinition, aeReports);
//			}
//			aeReports.add(aeReport);
//		}
	}
	
//	
//	public void addResult(List<AdverseEvent> adverseEvents , List<ReportDefinition> reportDefinitions){
//		for(AdverseEvent ae: adverseEvents){
//			Set<ReportDefinition> reportDefs = aeIndexMap.get(ae);
//			if(reportDefs == null){
//				reportDefs = new HashSet<ReportDefinition>();
//				aeIndexMap.put(ae, reportDefs);
//			}
//			reportDefs.addAll(reportDefinitions);
//		}
//		for(ReportDefinition reportDefinition : reportDefinitions){
//			Set<AdverseEvent> aes = reportDefAeIndexMap.get(reportDefinition);
//			if(aes == null){
//				aes = new HashSet<AdverseEvent>();
//				reportDefAeIndexMap.put(reportDefinition, aes);
//			}
//			aes.addAll(adverseEvents);
//		}
//	}
	
	public void replaceReportDefinitionName(Integer aeReportId, String theOne, String anotherOne){
		Map<AdverseEvent, Set<String>> map = processedRulesEngineResultMap.get(aeReportId);
		for(Map.Entry<AdverseEvent, Set<String>> entry : map.entrySet()){
			Set<String> reportDefinitionNames = entry.getValue();
			if(reportDefinitionNames.remove(theOne)){
				reportDefinitionNames.add(anotherOne);
			}
		}
	}
	
	public void removeReportDefinitionName(Integer aeReportId, String theOne){
		Map<AdverseEvent, Set<String>> map = processedRulesEngineResultMap.get(aeReportId);
		for(Map.Entry<AdverseEvent, Set<String>> entry : map.entrySet()){
			Set<String> reportDefinitionNames = entry.getValue();
			reportDefinitionNames.remove(theOne);
		}
	}
	
	public void removeReportDefinitionName(Integer aeReportId, AdverseEvent adverseEvent, String theOne){
		Map<AdverseEvent, Set<String>> map = processedRulesEngineResultMap.get(aeReportId);
		map.get(adverseEvent).remove(theOne);
	}
	
	public void addReportDefinitionName(Integer aeReportId, String theOne){
		Map<AdverseEvent, Set<String>> map = processedRulesEngineResultMap.get(aeReportId);
		for(Map.Entry<AdverseEvent, Set<String>> entry : map.entrySet()){
			Set<String> reportDefinitionNames = entry.getValue();
			if(!reportDefinitionNames.isEmpty()){
				reportDefinitionNames.remove(theOne);
			}
		}
	}
	
	public void addReportDefinitionName(Integer aeReportId, AdverseEvent adverseEvent, String theOne){
		Map<AdverseEvent, Set<String>> map = processedRulesEngineResultMap.get(aeReportId);
		map.get(adverseEvent).add(theOne);
	}
	
//	public Collection<ReportDefinition> getReportDefinitions(){
//		return reportDefAeIndexMap.keySet();
//	}
//	
//	public Collection<AdverseEvent> getAdverseEvents(){
//		return aeIndexMap.keySet();
//	}


	public Map<Integer, Set<ReportDefinition>> getAeReportIndexMap() {
		return aeReportIndexMap;
	}


	public void setAeReportIndexMap(Map<Integer, Set<ReportDefinition>> aeReportIndexMap) {
		this.aeReportIndexMap = aeReportIndexMap;
	}


//	public Map<ReportDefinition, Set<ExpeditedAdverseEventReport>> getReportDefAeReportIndexMap() {
//		return reportDefAeReportIndexMap;
//	}
//
//
//	public void setReportDefAeReportIndexMap(
//			Map<ReportDefinition, Set<ExpeditedAdverseEventReport>> reportDefAeReportIndexMap) {
//		this.reportDefAeReportIndexMap = reportDefAeReportIndexMap;
//	}

//
//	public Map<AdverseEvent, Set<ReportDefinition>> getAeIndexMap() {
//		return aeIndexMap;
//	}
//
//
//	public void setAeIndexMap(Map<AdverseEvent, Set<ReportDefinition>> aeIndexMap) {
//		this.aeIndexMap = aeIndexMap;
//	}
//
//
//	public Map<ReportDefinition, Set<AdverseEvent>> getReportDefAeIndexMap() {
//		return reportDefAeIndexMap;
//	}
//
//
//	public void setReportDefAeIndexMap(
//			Map<ReportDefinition, Set<AdverseEvent>> reportDefAeIndexMap) {
//		this.reportDefAeIndexMap = reportDefAeIndexMap;
//	}
	
	public Map<Integer, Set<ReportDefinitionWrapper>> getAmendmentMap() {
		return amendmentMap;
	}
	public void setAmendmentMap(Map<Integer, Set<ReportDefinitionWrapper>> amendmentMap) {
		this.amendmentMap = amendmentMap;
	}
	public Map<Integer, Set<ReportDefinitionWrapper>> getWithdrawalMap() {
		return withdrawalMap;
	}
	public void setWithdrawalMap(
			Map<Integer, Set<ReportDefinitionWrapper>> withdrawalMap) {
		this.withdrawalMap = withdrawalMap;
	}
	public Map<Integer, Set<ReportDefinitionWrapper>> getCreateMap() {
		return createMap;
	}
	public void setCreateMap(Map<Integer, Set<ReportDefinitionWrapper>> createMap) {
		this.createMap = createMap;
	}
	public Map<Integer, Set<ReportDefinitionWrapper>> getEditMap() {
		return editMap;
	}
	public void setEditMap(Map<Integer, Set<ReportDefinitionWrapper>> editMap) {
		this.editMap = editMap;
	}
	
	public Map<Integer, List<AdverseEvent>> getEvaluatedAeMap() {
		return evaluatedAeMap;
	}
	public void setEvaluatedAeMap(
			Map<Integer, List<AdverseEvent>> evaluatedAeMap) {
		this.evaluatedAeMap = evaluatedAeMap;
	}
	
	public Map<Integer, Boolean> getAeReportAlertMap() {
		return aeReportAlertMap;
	}

	public void setAeReportAlertMap(Map<Integer, Boolean> aeReportAlertMap) {
		this.aeReportAlertMap = aeReportAlertMap;
	}
	public Map<Integer, List<AdverseEvent>> getAllAeMap() {
		return allAeMap;
	}
	public void setAllAeMap(Map<Integer, List<AdverseEvent>> allAeMap) {
		this.allAeMap = allAeMap;
	}
	
	public Map<Integer, Map<AdverseEvent, Set<ReportDefinition>>> getAdverseEventIndexMap() {
		return adverseEventIndexMap;
	}
	
	public Map<Integer, List<ReportDefinition>> getReportedAEIndexMap() {
		return reportedAEIndexMap;
	}

	public void setReportedAEIndexMap(
			Map<Integer, List<ReportDefinition>> reportedAEIndexMap) {
		this.reportedAEIndexMap = reportedAEIndexMap;
	}
	
	public Map<Integer, Map<AdverseEvent, List<String>>> getRulesEngineResultMap() {
		return rulesEngineResultMap;
	}
	
	public void setRulesEngineResultMap(Map<Integer, Map<AdverseEvent, List<String>>> rulesEngineResultMap) {
		this.rulesEngineResultMap = rulesEngineResultMap;
	}
	
	public boolean isAlertRecommended(){
		boolean retVal = false;
		for(Boolean b : aeReportAlertMap.values()){
			retVal |= b;
		}
		return retVal;
	}
	
	public List<AdverseEvent> getSeriousAdverseEvents(Integer aeReportId){
		List<AdverseEvent> seriousAdverseEvents = new ArrayList<AdverseEvent>();
		Map<AdverseEvent, Set<String>> aeMap = processedRulesEngineResultMap.get(aeReportId);
		for(AdverseEvent adverseEvent : aeMap.keySet()){
			if(CollectionUtils.isNotEmpty(aeMap.get(adverseEvent))){
				seriousAdverseEvents.add(adverseEvent);
			}
		}
		return seriousAdverseEvents;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder("Evaluation Result {\n");
		sb.append("\n rulesEngineResultMap").append(String.valueOf(rulesEngineResultMap))
		.append("\n processedRulesEngineResultMap").append(String.valueOf(processedRulesEngineResultMap))
		.append("\n adverseEventIndexMap").append(String.valueOf(adverseEventIndexMap))
		.append("\n aeReportIndexMap : ").append(aeReportIndexMap.toString())
		.append("\n aeReportAlertMap :" ).append(aeReportAlertMap)
		.append("\n evaluatedAeMap").append(evaluatedAeMap.toString())
		.append("\n allAeMap").append(allAeMap.toString())
		.append("\n reportedAEIndexMap").append(reportedAEIndexMap.toString())
		.append("\n ammendmentMap : ").append(amendmentMap.toString())
		.append("\n withdrawMap : ").append(withdrawalMap.toString())
		.append("\n editMap : ").append(editMap.toString())
		.append("\n createMap : ").append(createMap.toString());
		sb.append("\n}");
		return sb.toString();
	}
	

}

