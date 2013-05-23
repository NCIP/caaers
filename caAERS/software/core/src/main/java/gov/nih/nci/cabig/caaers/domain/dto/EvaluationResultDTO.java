/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.dto;

import java.util.Collections;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.comparator.AdverseEventComprator;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import gov.nih.nci.cabig.caaers.rules.common.AdverseEventEvaluationResult;
import org.apache.commons.collections.CollectionUtils;

 
/**
 * The Class EvaluationResultDTO.
 *
 * @author Biju Joseph
 */
public class EvaluationResultDTO {
	
	
	//stores aeReportId and list of report definitions
	/** The ae report index map. */
	Map<Integer, Set<ReportDefinition>> aeReportIndexMap = new HashMap<Integer, Set<ReportDefinition>>();
	//stores aeReportId, and list of aes evaluated
	/** The evaluated ae map. */
	Map<Integer, List<AdverseEvent>> evaluatedAeMap = new HashMap<Integer, List<AdverseEvent>>();
	
	//stores aeReportId and actual set of adverse events (including the new ones). 
	/** The all ae map. */
	Map<Integer, List<AdverseEvent>> allAeMap = new HashMap<Integer, List<AdverseEvent>>();
	
	//stores aeReportId, and alert needed 
	/** The ae report alert map. */
	Map<Integer, Boolean> aeReportAlertMap = new HashMap<Integer, Boolean>();
	
	//will store the result of rules engine, as it is.[aeReportId - (adverseEvent - {ReportDefinitionNames} ]
	/** The rules engine result map. */
	Map<Integer, Map<AdverseEvent, List<String>>> rulesEngineResultMap = new HashMap<Integer, Map<AdverseEvent, List<String>>>();
    
    Map<Integer, Map<AdverseEvent, List<AdverseEventEvaluationResult>>> rulesEngineRawResultMap = new HashMap<Integer, Map<AdverseEvent, List<AdverseEventEvaluationResult>>>();
	
    Map<Integer, List<String>> processingSteps = new HashMap<Integer, List<String>>();
    
	/** The processed rules engine result map. */
	Map<Integer, Map<AdverseEvent, Set<String>>> processedRulesEngineResultMap = new HashMap<Integer, Map<AdverseEvent,Set<String>>>();
	
	//will store [aeReportId - {adverseEventId - [ReportDefinitionId] }]
	/** The adverse event index map. */
	Map<Integer, Map<AdverseEvent,Set<ReportDefinition>>> adverseEventIndexMap = new HashMap<Integer, Map<AdverseEvent,Set<ReportDefinition>>>();
	
	//stores report definition and aeReports associated
//	Map<ReportDefinition, Set<ExpeditedAdverseEventReport>> reportDefAeReportIndexMap = new HashMap<ReportDefinition, Set<ExpeditedAdverseEventReport>>();

	//stores adverseEventId - {report definition}, tells which ae got reported in which report.
	/** The reported ae index map. */
	Map<Integer, List<ReportDefinition>> reportedAEIndexMap = new HashMap<Integer, List<ReportDefinition>>();
	
	//aeReportId - ReportDefinitions
	/** The amendment map. */
	Map<Integer, Set<ReportDefinitionWrapper>> amendmentMap = new HashMap<Integer, Set<ReportDefinitionWrapper>>();
	
	/** The withdrawal map. */
	Map<Integer, Set<ReportDefinitionWrapper>> withdrawalMap = new HashMap<Integer, Set<ReportDefinitionWrapper>>();
	
	/** The edit map. */
	Map<Integer, Set<ReportDefinitionWrapper>> editMap = new HashMap<Integer, Set<ReportDefinitionWrapper>>();
	
	/** The create map. */
	Map<Integer, Set<ReportDefinitionWrapper>> createMap = new  HashMap<Integer, Set<ReportDefinitionWrapper>>();
	
	/**
	 * Will find the report definition, matching by name, from aeReportIndexMap.
	 *
	 * @param aeReportId the ae report id
	 * @param reportDefinitionName the report definition name
	 * @return the report definition
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
	
	/**
	 * Adds the rules engine result.
	 *
	 * @param aeReportId the ae report id
	 * @param map the map
	 */
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

	
	/**
	 * Adds the evaluated adverse events.
	 *
	 * @param aeReportId the ae report id
	 * @param events the events
	 */
	public void addEvaluatedAdverseEvents(Integer aeReportId, List<AdverseEvent> events){
		List<AdverseEvent> sortedEvents = new ArrayList<AdverseEvent>(events);
		Collections.sort(sortedEvents, AdverseEventComprator.DEFAULT_ADVERSE_EVENT_COMPARATOR);
		evaluatedAeMap.put(aeReportId, sortedEvents);
	}
	
	/**
	 * Adds the all adverse events.
	 *
	 * @param aeReportId the ae report id
	 * @param events the events
	 */
	public void addAllAdverseEvents(Integer aeReportId, List<AdverseEvent> events){
		List<AdverseEvent> sortedEvents = new ArrayList<AdverseEvent>(events);
		Collections.sort(sortedEvents, AdverseEventComprator.DEFAULT_ADVERSE_EVENT_COMPARATOR);
		allAeMap.put(aeReportId, sortedEvents);
	}
	
	/**
	 * Adds the result.
	 *
	 * @param aeReport the ae report
	 * @param reportDefintions the report defintions
	 */
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
	
    public void addProcessingStep(Integer aeReportId, String step, String details){
        List<String> steps = processingSteps.get(aeReportId);
        if(steps == null){
            steps = new ArrayList<String>();
            processingSteps.put(aeReportId, steps);
        }
        String s = step;
        if(details != null) s = s + " " +details;

        steps.add(s);
    }
	/**
 * Replace report definition name.
 *
 * @param aeReportId the ae report id
 * @param theOne the the one
 * @param anotherOne the another one
 */
public void replaceReportDefinitionName(Integer aeReportId, String theOne, String anotherOne){
		Map<AdverseEvent, Set<String>> map = processedRulesEngineResultMap.get(aeReportId);
		for(Map.Entry<AdverseEvent, Set<String>> entry : map.entrySet()){
			Set<String> reportDefinitionNames = entry.getValue();
			if(reportDefinitionNames.remove(theOne)){
				reportDefinitionNames.add(anotherOne);
			}
		}
	}
	
	/**
	 * Removes the report definition name.
	 *
	 * @param aeReportId the ae report id
	 * @param theOne the the one
	 */
	public void removeReportDefinitionName(Integer aeReportId, String theOne){
		Map<AdverseEvent, Set<String>> map = processedRulesEngineResultMap.get(aeReportId);
		for(Map.Entry<AdverseEvent, Set<String>> entry : map.entrySet()){
			Set<String> reportDefinitionNames = entry.getValue();
			reportDefinitionNames.remove(theOne);
		}
	}
	
	/**
	 * Removes the report definition name.
	 *
	 * @param aeReportId the ae report id
	 * @param adverseEvent the adverse event
	 * @param theOne the the one
	 */
	public void removeReportDefinitionName(Integer aeReportId, AdverseEvent adverseEvent, String theOne){
		Map<AdverseEvent, Set<String>> map = processedRulesEngineResultMap.get(aeReportId);
		map.get(adverseEvent).remove(theOne);
	}
	
	/**
	 * Adds the report definition name.
	 *
	 * @param aeReportId the ae report id
	 * @param theOne the the one
	 */
	public void addReportDefinitionName(Integer aeReportId, String theOne){
		Map<AdverseEvent, Set<String>> map = processedRulesEngineResultMap.get(aeReportId);
		for(Map.Entry<AdverseEvent, Set<String>> entry : map.entrySet()){
			Set<String> reportDefinitionNames = entry.getValue();
            reportDefinitionNames.add(theOne);
		}
	}
	
	/**
	 * Adds the report definition name.
	 *
	 * @param aeReportId the ae report id
	 * @param adverseEvent the adverse event
	 * @param theOne the the one
	 */
	public void addReportDefinitionName(Integer aeReportId, AdverseEvent adverseEvent, String theOne){
		Map<AdverseEvent, Set<String>> map = processedRulesEngineResultMap.get(aeReportId);
        if(adverseEvent.isRetired() && !map.containsKey(adverseEvent)) map.put(adverseEvent, new HashSet<String>());
		map.get(adverseEvent).add(theOne);
	}
	
//	public Collection<ReportDefinition> fetchReportDefinitions(){
//		return reportDefAeIndexMap.keySet();
//	}
//	
//	public Collection<AdverseEvent> getAdverseEvents(){
//		return aeIndexMap.keySet();
//	}


	/**
 * Gets the ae report index map.
 *
 * @return the ae report index map
 */
public Map<Integer, Set<ReportDefinition>> getAeReportIndexMap() {
		return aeReportIndexMap;
	}


	/**
	 * Sets the ae report index map.
	 *
	 * @param aeReportIndexMap the ae report index map
	 */
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
	
	/**
 * Gets the amendment map.
 *
 * @return the amendment map
 */
public Map<Integer, Set<ReportDefinitionWrapper>> getAmendmentMap() {
		return amendmentMap;
	}
	
	/**
	 * Sets the amendment map.
	 *
	 * @param amendmentMap the amendment map
	 */
	public void setAmendmentMap(Map<Integer, Set<ReportDefinitionWrapper>> amendmentMap) {
		this.amendmentMap = amendmentMap;
	}
	
	/**
	 * Gets the withdrawal map.
	 *
	 * @return the withdrawal map
	 */
	public Map<Integer, Set<ReportDefinitionWrapper>> getWithdrawalMap() {
		return withdrawalMap;
	}
	
	/**
	 * Sets the withdrawal map.
	 *
	 * @param withdrawalMap the withdrawal map
	 */
	public void setWithdrawalMap(
			Map<Integer, Set<ReportDefinitionWrapper>> withdrawalMap) {
		this.withdrawalMap = withdrawalMap;
	}
	
	/**
	 * Gets the creates the map.
	 *
	 * @return the creates the map
	 */
	public Map<Integer, Set<ReportDefinitionWrapper>> getCreateMap() {
		return createMap;
	}
	
	/**
	 * Sets the create map.
	 *
	 * @param createMap the create map
	 */
	public void setCreateMap(Map<Integer, Set<ReportDefinitionWrapper>> createMap) {
		this.createMap = createMap;
	}
	
	/**
	 * Gets the edits the map.
	 *
	 * @return the edits the map
	 */
	public Map<Integer, Set<ReportDefinitionWrapper>> getEditMap() {
		return editMap;
	}
	
	/**
	 * Sets the edit map.
	 *
	 * @param editMap the edit map
	 */
	public void setEditMap(Map<Integer, Set<ReportDefinitionWrapper>> editMap) {
		this.editMap = editMap;
	}
	
	/**
	 * Gets the evaluated ae map.
	 *
	 * @return the evaluated ae map
	 */
	public Map<Integer, List<AdverseEvent>> getEvaluatedAeMap() {
		return evaluatedAeMap;
	}
	
	/**
	 * Sets the evaluated ae map.
	 *
	 * @param evaluatedAeMap the evaluated ae map
	 */
	public void setEvaluatedAeMap(
			Map<Integer, List<AdverseEvent>> evaluatedAeMap) {
		this.evaluatedAeMap = evaluatedAeMap;
	}
	
	/**
	 * Gets the ae report alert map.
	 *
	 * @return the ae report alert map
	 */
	public Map<Integer, Boolean> getAeReportAlertMap() {
		return aeReportAlertMap;
	}

	/**
	 * Sets the ae report alert map.
	 *
	 * @param aeReportAlertMap the ae report alert map
	 */
	public void setAeReportAlertMap(Map<Integer, Boolean> aeReportAlertMap) {
		this.aeReportAlertMap = aeReportAlertMap;
	}
	
	/**
	 * Gets the all ae map.
	 *
	 * @return the all ae map
	 */
	public Map<Integer, List<AdverseEvent>> getAllAeMap() {
		return allAeMap;
	}
	
	/**
	 * Sets the all ae map.
	 *
	 * @param allAeMap the all ae map
	 */
	public void setAllAeMap(Map<Integer, List<AdverseEvent>> allAeMap) {
		this.allAeMap = allAeMap;
	}
	
	/**
	 * Gets the adverse event index map.
	 *
	 * @return the adverse event index map
	 */
	public Map<Integer, Map<AdverseEvent, Set<ReportDefinition>>> getAdverseEventIndexMap() {
		return adverseEventIndexMap;
	}
	
	/**
	 * Gets the reported ae index map.
	 *
	 * @return the reported ae index map
	 */
	public Map<Integer, List<ReportDefinition>> getReportedAEIndexMap() {
		return reportedAEIndexMap;
	}

	/**
	 * Sets the reported ae index map.
	 *
	 * @param reportedAEIndexMap the reported ae index map
	 */
	public void setReportedAEIndexMap(
			Map<Integer, List<ReportDefinition>> reportedAEIndexMap) {
		this.reportedAEIndexMap = reportedAEIndexMap;
	}
	
	/**
	 * Gets the rules engine result map.
	 *
	 * @return the rules engine result map
	 */
	public Map<Integer, Map<AdverseEvent, List<String>>> getRulesEngineResultMap() {
		return rulesEngineResultMap;
	}
	
	/**
	 * Sets the rules engine result map.
	 *
	 * @param rulesEngineResultMap the rules engine result map
	 */
	public void setRulesEngineResultMap(Map<Integer, Map<AdverseEvent, List<String>>> rulesEngineResultMap) {
		this.rulesEngineResultMap = rulesEngineResultMap;
	}

    public Map<Integer, Map<AdverseEvent, List<AdverseEventEvaluationResult>>> getRulesEngineRawResultMap() {
        return rulesEngineRawResultMap;
    }

    public void setRulesEngineRawResultMap(Map<Integer, Map<AdverseEvent, List<AdverseEventEvaluationResult>>> rulesEngineRawResultMap) {
        this.rulesEngineRawResultMap = rulesEngineRawResultMap;
    }

    public Map<Integer, List<String>> getProcessingSteps() {
        return processingSteps;
    }

    public void setProcessingSteps(Map<Integer, List<String>> processingSteps) {
        this.processingSteps = processingSteps;
    }

    /**
	 * Checks if is alert recommended.
	 *
	 * @return true, if is alert recommended
	 */
	public boolean isAlertRecommended(){
		boolean retVal = false;
		for(Boolean b : aeReportAlertMap.values()){
			retVal |= b;
		}
		return retVal;
	}
	
	/**
	 * Gets the serious adverse events.
	 *
	 * @param aeReportId the ae report id
	 * @return the serious adverse events
	 */
	public List<AdverseEvent> getSeriousAdverseEvents(Integer aeReportId){
		List<AdverseEvent> seriousAdverseEvents = new ArrayList<AdverseEvent>();
		Map<AdverseEvent, Set<String>> aeMap = processedRulesEngineResultMap.get(aeReportId);
        if(aeMap != null)      {
            for(AdverseEvent adverseEvent : aeMap.keySet()){
                if(CollectionUtils.isNotEmpty(aeMap.get(adverseEvent))){
                    seriousAdverseEvents.add(adverseEvent);
                }
            }
        }

		return seriousAdverseEvents;
	}

    /**
     *   join the amend, withdraw, edit and create maps.
     *
     */

    public List<ReportDefinitionWrapper> getJoinAllMaps(Integer aeReportId) {

        List<ReportDefinitionWrapper> wrappers = new ArrayList<ReportDefinitionWrapper>();

        Set<ReportDefinitionWrapper> ammendWrappers = getAmendmentMap().get(aeReportId);
        if(ammendWrappers != null) wrappers.addAll(ammendWrappers);

        Set<ReportDefinitionWrapper> withdrawWrappers = getWithdrawalMap().get(aeReportId);
        if(withdrawWrappers != null) wrappers.addAll(withdrawWrappers);

        Set<ReportDefinitionWrapper> editWrappers = getEditMap().get(aeReportId);
        if(editWrappers != null) wrappers.addAll(editWrappers);

        Set<ReportDefinitionWrapper> createWrappers = getCreateMap().get(aeReportId);
        if(createWrappers != null) wrappers.addAll(createWrappers);

        return wrappers;
    }
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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
		.append("\n createMap : ").append(createMap.toString())
		.append("\n processingSteps : ").append(processingSteps.toString());
		sb.append("\n}");
		return sb.toString();
	}
	

}

