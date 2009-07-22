package gov.nih.nci.cabig.caaers.rules.business.service;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.dto.ApplicableReportDefinitionsDTO;
import gov.nih.nci.cabig.caaers.domain.dto.EvaluationResultDTO;
import gov.nih.nci.cabig.caaers.domain.dto.ReportDefinitionWrapper;
import gov.nih.nci.cabig.caaers.domain.dto.ReportDefinitionWrapper.ActionType;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.service.ReportSubmittability;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class EvaluationServiceImpl implements EvaluationService {
    private AdverseEventEvaluationService adverseEventEvaluationService;

    private static final Log log = LogFactory.getLog(EvaluationServiceImpl.class);

    private ReportDefinitionDao reportDefinitionDao;

    private ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;

    private ReportRepository reportRepository;
    
    private OrganizationDao organizationDao;
    
    ReportDefinitionFilter reportDefinitionFilter;
    
    public EvaluationServiceImpl() {
    	reportDefinitionFilter = new ReportDefinitionFilter();
	}

    /**
     * This method evaluates the SAE reporting rules on the reporting period. The output evaluation result will have the following
     *  - For new data collection , what are the suggestions
     *  - For existing data collection, what are the suggestions.
     *  - An index relating which AdverseEvent is evaluated for a data collection.
     *  - An index relating which AdverseEvent is associated to which completed reports
     *  - An index mapping which AdverseEvent is associated which suggested report definition.
     *  - Report definitions, getting amended, withdrawn, edited and created. 
     *  
     * @param reportingPeriod
     * @return
     */
    public EvaluationResultDTO evaluateSAERules(AdverseEventReportingPeriod reportingPeriod){
    	assert reportingPeriod != null : "Reporting period should not be null";
    	EvaluationResultDTO  result = new EvaluationResultDTO();
    	
    	List<ExpeditedAdverseEventReport> aeReports = reportingPeriod.getAeReports();
    	
    	//determine discrete set of AdverseEvents, against which the rules should be fired.
    	List<AdverseEvent> newlyAddedAdverseEvents = reportingPeriod.getNonExpeditedAdverseEvents();
    	
    	//find the evaluation for default (new data collection)
    	findRequiredReportDefinitions(null, newlyAddedAdverseEvents, reportingPeriod.getStudy(), result);
    	result.addAllAdverseEvents(new Integer(0), newlyAddedAdverseEvents);
    	
    	//for each data collection (existing) find the evaluation
    	if(CollectionUtils.isNotEmpty(aeReports)){
    		for(ExpeditedAdverseEventReport aeReport : aeReports){
    			List<AdverseEvent> evaluatableAdverseEvents = new ArrayList<AdverseEvent>(newlyAddedAdverseEvents);
    			List<AdverseEvent> existingAdverseEvents = aeReport.isActive() ? aeReport.getActiveAdverseEvents() : aeReport.getActiveModifiedAdverseEvents() ;
        		evaluatableAdverseEvents.addAll(existingAdverseEvents);
        		
        		List<AdverseEvent> allAdverseEvents = new ArrayList<AdverseEvent>(newlyAddedAdverseEvents);
        		allAdverseEvents.addAll(aeReport.getAdverseEvents());
        		findRequiredReportDefinitions(aeReport, evaluatableAdverseEvents, reportingPeriod.getStudy(), result);
        		result.addAllAdverseEvents(aeReport.getId(), allAdverseEvents);
        		
        		//populate the reported adverse event - report definition map.
        		List<Report> completedAmendableReports = aeReport.findCompletedAmendableReports();
        		for(AdverseEvent ae : aeReport.getAdverseEvents()){
        			List<ReportDefinition> rdList = new ArrayList<ReportDefinition>();
        			for(Report completedReport : completedAmendableReports){
            			if(completedReport.isReported(ae)){
            				rdList.add(completedReport.getReportDefinition());
            			}
            		}
        			result.getReportedAEIndexMap().put(ae.getId(), rdList);
    			}
        		
        		
    		}
    	}
    	
    	if(log.isInfoEnabled()){
    		log.info("============== Evaluation result =============");
    		log.info(result.toString());
    		log.info("==============================================");
    	}
    	
    	System.out.println("****************************************************");
    	System.out.println(result);
    	System.out.println("****************************************************");
    	
    	return result;
    }
    //TODO : BJ, to be removed
    public List<ReportDefinition> findRequiredReportDefinitions(ExpeditedAdverseEventReport expeditedData, List<AdverseEvent> aeList, Study study) {
    	EvaluationResultDTO evaluationResult = new EvaluationResultDTO();
    	findRequiredReportDefinitions(expeditedData, aeList, study, evaluationResult);
    	List<ReportDefinition> reportDefinitions = new ArrayList<ReportDefinition>();
    	if(expeditedData == null){
    		reportDefinitions.addAll(evaluationResult.getAeReportIndexMap().get(new Integer(0)));
    	}else{
    		reportDefinitions.addAll(evaluationResult.getAeReportIndexMap().get(expeditedData.getId()));
    	}
    	return reportDefinitions;
    }
    
    
    /**
     * This method invokes the {@link AdverseEventEvaluationService} to obtain the report definitions suggested. 
     * Then process that information, to get the adverse event result {@link EvaluationResultDTO}
     * 
     * Overview on extra processing
     *   1. If child report or a report of the same group is active , parent report suggested by rules is ignored. 
     *   2. All manually selected active reports are suggested by caAERS
     *   3. If there is a manual selection, ignore the others suggested by rules
     *   4. If there is an AE modified, which is part of submitted report, force amend it. 
     *   5. If any, Withdraw all active reports (non manually selected), that are not suggested.
     *   
     * @param expeditedData - The {@link ExpeditedAdverseEventReport}
     */
    public void findRequiredReportDefinitions(ExpeditedAdverseEventReport expeditedData, List<AdverseEvent> aeList, Study study, EvaluationResultDTO evaluationResult) {
        Map<AdverseEvent, List<String>> map;
        List<ReportDefinition> defList = new ArrayList<ReportDefinition>();
        boolean alertNeeded = false;
        Integer aeReportId = expeditedData == null ? new Integer(0) : expeditedData.getId();

        try {
        	//evaluate the SAE reporting rules
            map = adverseEventEvaluationService.evaluateSAEReportSchedule(expeditedData, aeList, study);
            
            //find out the unique report definition names, then load them. 
            Set<String> reportDefinitionNames = new  HashSet<String>();
            for(AdverseEvent ae : map.keySet()){
            	List<String> nameList = map.get(ae);
            	ae.setRequiresReporting(!nameList.isEmpty());
            	alertNeeded |= (!nameList.isEmpty());
            	reportDefinitionNames.addAll(nameList);
            	
            }
            
            //logging
            if(log.isDebugEnabled()){
            	log.debug("Rules Engine Result for : " + aeReportId + ", " + String.valueOf(map));
            }
            
            //set the alert needed
            evaluationResult.getAeReportAlertMap().put(aeReportId, alertNeeded);
            
            
            //load all report definitions
            for(String reportDefName : reportDefinitionNames){
            	ReportDefinition rd = reportDefinitionDao.getByName(reportDefName);
            	if(rd != null){
            		defList.add(rd);
            	}
            }
            
            //  - If child report is active, select that instead of parent. 
            // - If there is a manual selection, ignore rules engine suggestions from the same group
            // - If the manual selection is always a preferred one (ie. by default add active manual selected reports). 
            // - If there is an ae modified, which is part of completed report, force amending it.
            List<Report> activeReports = null;
            if(expeditedData != null){
            	activeReports = expeditedData.getActiveReports();
            	List<Report> manuallySelectedReports = expeditedData.getManuallySelectedReports();
            	
            	//a temporary list
            	List<ReportDefinition> tmplist = new ArrayList<ReportDefinition>(defList);
            	
            	//keep active child report instead of parent.
            	for(Report activeReport : activeReports){
            		ReportDefinition rdParent = activeReport.getReportDefinition().getParent();
            		ReportDefinition rdFound = findReportDefinition(tmplist, rdParent);
            		
            		if(rdFound != null){
            			//remove parent and keep child
            			defList.remove(rdFound);
            			defList.add(activeReport.getReportDefinition());
            		}
            	}
            	
            	//throw away all suggestions of rules engine, (if they belong to the same group as that of manually selected)
            	for(Report manualReport : manuallySelectedReports){
            		ReportDefinition rdManual = manualReport.getReportDefinition();
            		
            		for(ReportDefinition rdSuggested : tmplist){
            			if(rdSuggested.isOfSameReportTypeAndOrganization(rdManual) && manualReport.isActive() ){
            				//remove it from rules engine suggestions
            				defList.remove(rdSuggested);
            				
            			}
            		}
            		
            		//if manual selection, and active, add it.
            		if(manualReport.isActive()){
            			defList.add(rdManual);
            		}
            		
            	}
            	
            	List<AdverseEvent> modifiedAdverseEvents = expeditedData.getModifiedAdverseEvents();
            	
            	//modify the alert necessary flag, based on eventual set of report definitions
            	alertNeeded = (!defList.isEmpty() ) || (!modifiedAdverseEvents.isEmpty());
            	evaluationResult.getAeReportAlertMap().put(aeReportId, alertNeeded);
            	
            	//any ae modified/got completed reports ? add those report definitions.
            	if(CollectionUtils.isNotEmpty(modifiedAdverseEvents)){
            		List<Report> completedReports = expeditedData.listReportsHavingStatus(ReportStatus.COMPLETED);
                	//Any completed report, suggest amending it to proceed (but no alert).
                	for(Report report : completedReports){
         				
         				ReportDefinition rdCompleted = report.getReportDefinition();
         				
         				if(!rdCompleted.getAmendable()) continue;
         				
         				boolean sameGroupSuggested = false;
         				//do we have a report def suggested, that belongs to same category? Yes, then ignore
         				for(ReportDefinition rdSuggested : defList){
         					if(rdSuggested.isOfSameReportTypeAndOrganization(rdCompleted)){
         						sameGroupSuggested = true;
         						break;
         					}
         				}
         				
         				if(!sameGroupSuggested) defList.add(rdCompleted);
         				
         			}
            	}
            	
            }
            
            
           //logging 
           if(log.isDebugEnabled()){
        	 log.debug("Report Definitions before filtering for aeReportId: " + aeReportId + ", " + String.valueOf(defList));  
           }
           
           //filter the report definitions
           List<ReportDefinition> reportDefinitions =  reportDefinitionFilter.filter(defList);
           
           
           //logging 
           if(log.isDebugEnabled()){
        	 log.debug("Report Definitions after filtering for aeReportId: " + aeReportId + ", " + String.valueOf(reportDefinitions));  
           }

           //now go through each report definition and set amend/create edit/withdraw/create maps properly
           Set<ReportDefinitionWrapper> rdCreateSet = new HashSet<ReportDefinitionWrapper>();
           Set<ReportDefinitionWrapper> rdEditSet = new HashSet<ReportDefinitionWrapper>();
           Set<ReportDefinitionWrapper> rdWithdrawSet = new HashSet<ReportDefinitionWrapper>();
           Set<ReportDefinitionWrapper> rdAmmendSet = new HashSet<ReportDefinitionWrapper>();
           
           ReportDefinitionWrapper wrapper;
           for(ReportDefinition rd : reportDefinitions){
        	  
        	 if(expeditedData == null){  
        		  //all report definitions, should go in the createMap.
        		  wrapper = new ReportDefinitionWrapper(rd, null, ActionType.CREATE);
        		  wrapper.setStatus("Not started");
        		  rdCreateSet.add(wrapper);
        	 }else{
        		 
         	  	  //find reports getting amended
	        	  List<Report> reportsAmmended = expeditedData.findReportsToAmmend(rd); 
	        	  for(Report report : reportsAmmended){
	        		  wrapper = new ReportDefinitionWrapper(report.getReportDefinition(), rd, ActionType.AMEND);
	        		  wrapper.setStatus(report.getLastVersion().getStatusAsString());
	        		  wrapper.setSubmittedOn(report.getSubmittedOn());
	        		  rdAmmendSet.add(wrapper);
	        	  }
	        	  
	        	  //find reports getting withdrawn
	        	  List<Report> reportsWithdrawn = expeditedData.findReportsToWitdraw(rd);
	        	  for(Report report : reportsWithdrawn){
	        		  wrapper = new ReportDefinitionWrapper(report.getReportDefinition(), rd, ActionType.WITHDRAW);
	        		  wrapper.setStatus("In process");
	        		  wrapper.setDueOn(report.getDueOn());
	        		  rdWithdrawSet.add(wrapper);
	        	  }
	        	  

	        	  //find the reports getting edited
	        	  List<Report> reportsEdited = expeditedData.findReportsToEdit(rd);
	        	  for(Report report : reportsEdited){
	        		  wrapper = new ReportDefinitionWrapper(report.getReportDefinition(), rd, ActionType.EDIT);
	        		  wrapper.setStatus("In process");
	        		  wrapper.setDueOn(report.getDueOn());
	        		  rdEditSet.add(wrapper);
	        	  }
	        	  
	        	  //Nothing getting edited,  add in this report def in create list
	        	  if(CollectionUtils.isEmpty(reportsEdited)){
	        		 wrapper = new ReportDefinitionWrapper(rd, null, ActionType.CREATE);
	         		 wrapper.setStatus("Not started");
	         		 rdCreateSet.add(wrapper);
	        	  }
	        	  
              }//if expeditedData  
         	 
           }//for rd
           
           //Check if there is a need to withdraw any active report. 
           if(expeditedData != null && CollectionUtils.isNotEmpty(activeReports)){
        	   for(Report report : activeReports){
        		   ReportDefinition rdActive = report.getReportDefinition();
        		   if(report.isManuallySelected()) continue;
        		   boolean toBeWithdrawn = true;
        		   for(ReportDefinitionWrapper editWrapper : rdEditSet){
        			   if(editWrapper.getDef().equals(rdActive)){
        				   toBeWithdrawn = false;
        				   break;
        			   }
        		   }
        		   
        		   if(toBeWithdrawn){
        			   for(ReportDefinitionWrapper withdrawWrapper :rdWithdrawSet){
            			   if(withdrawWrapper.getDef().equals(rdActive)){
            				   toBeWithdrawn = false;
            				   break;
            			   }
            		   }  
        		   }
        		   
        		   if(toBeWithdrawn){
        			  wrapper = new ReportDefinitionWrapper(rdActive, null, ActionType.WITHDRAW);
        			  wrapper.setDueOn(report.getDueOn());
 	        		  wrapper.setStatus("In process");
 	        		  rdWithdrawSet.add(wrapper);
        		   }
        	   }
           }
           
           //add everything to the result.
           evaluationResult.getCreateMap().put(aeReportId, rdCreateSet);
           evaluationResult.getAmendmentMap().put(aeReportId, rdAmmendSet);
           evaluationResult.getEditMap().put(aeReportId, rdEditSet);
           evaluationResult.getWithdrawalMap().put(aeReportId, rdWithdrawSet);
           
           
           //update the result object
           evaluationResult.addEvaluatedAdverseEvents(aeReportId, aeList);
           evaluationResult.addResult(aeList, reportDefinitions);
           if(expeditedData != null){
        	   evaluationResult.addResult(expeditedData, reportDefinitions);
           }
            
        } catch (Exception e) {
            throw new CaaersSystemException("Could not determine the reports necessary for the given expedited adverse event data", e);
        }
        
    }
    /**
     * Will create the Report by calling ReportService, then saves the ExpeditedAdverseEventReport
     */
    @Transactional(readOnly = false)
    public List<Report> addOptionalReports(ExpeditedAdverseEventReport expeditedData,
                    Collection<ReportDefinition> reportDefs, Boolean useDefaultVersion) {
    	List<Report> reports = new ArrayList<Report>();
        for (ReportDefinition def : reportDefs) {
            Report r = reportRepository.createReport(def, expeditedData, null);
            // Set useDefaultVersion to true. So that incase there are multiple reports , the reportVersion is correctly set for the 
            // first report and the same reportVersion is used for the remaining reports in the list
            
            // eg. if 5 day and 24hr are selected, then the reportVersion becomes 1 for 5 day and 2 for 24 hr report.
            // rather it should be 1 for both the reports.
            useDefaultVersion = true;
            reports.add(r);
        }
        expeditedAdverseEventReportDao.save(expeditedData);
        return reports;
    }


    /**
     * This method will find all the report definitions belonging to the Study
     */
    public ApplicableReportDefinitionsDTO applicableReportDefinitions(Study study) {
    	
        List<ReportDefinition> reportDefinitions = new ArrayList<ReportDefinition>();
        // Same organization play multiple roles.
        Set<Integer> orgIdSet = new HashSet<Integer>();
        List<StudyOrganization> studyOrgs =  study.getStudyOrganizations();
        for (StudyOrganization studyOrganization : studyOrgs) {
        	if(orgIdSet.add(studyOrganization.getOrganization().getId()))
        		reportDefinitions.addAll(reportDefinitionDao.getAll(studyOrganization.getOrganization().getId()));
        }
        
        /**
         * Get REport definitions of CTEP for DCP studies , because DCP uses CTEP 
         * report definitions also . TEMP fix
         */
        Organization primarySponsor = study.getPrimaryFundingSponsorOrganization();
        if (primarySponsor.getName().equals("Division of Cancer Prevention")) {
        	reportDefinitions.addAll(reportDefinitionDao.getAll(this.organizationDao.getByName("Cancer Therapy Evaluation Program").getId()));
        }
        
        ApplicableReportDefinitionsDTO dto = new ApplicableReportDefinitionsDTO();
        for(ReportDefinition rd : reportDefinitions){
    	  dto.addReportDefinition(rd);
        }
        
       return dto;
    }

    public Collection<ExpeditedReportSection> mandatorySections( ExpeditedAdverseEventReport expeditedData, ReportDefinition... reportDefinitions) {
        try {
            Collection<ExpeditedReportSection> sections = adverseEventEvaluationService.mandatorySections(expeditedData, reportDefinitions);
            if (log.isDebugEnabled()) log.debug("Mandatory sections: " + sections);
            return sections;
        } catch (Exception e) {
            throw new CaaersSystemException("Could not get mandatory sections", e);
        }
    }

    /**
     * Checks whether all the mandatory fields, are duly filled. If the report is complete, the
     * ErrorMessages will be empty
     * 
     * @param report -
     *                {@link Report}
     * @return {@link ReportSubmittability}
     */
    // return type based on the method name, is misleading,need to find a better name.
    public ReportSubmittability isSubmittable(Report report) {

        return reportRepository.validate(report);

        /*
         * -- commented based on the new biz rule try { return reportService.validate(report,
         * adverseEventEvaluationService.mandatorySectionsForReport(report)); } catch
         * (RuntimeException re) { throw re; } catch (Exception e) { throw new
         * CaaersSystemException("Unable to determine mandatory sections", e); }
         */
    }

    public ValidationErrors validateReportingBusinessRules(ExpeditedAdverseEventReport aeReport, ExpeditedReportSection... sections) {
        try {
            return adverseEventEvaluationService.validateReportingBusinessRules(aeReport, sections);
        } catch (Exception e) {
            log.error("Error while evaluating business rules", e);
            throw new CaaersSystemException("Error while evaluating business rules", e);
        }
    }
    
    /////move this else where
    private ReportDefinition findReportDefinition(List<ReportDefinition> rdList, ReportDefinition toFind){
    	if(toFind == null) return null;
    	for(ReportDefinition rd : rdList){
    		if(rd.getId().equals( toFind.getId())) return rd;
    	}
    	return null;
    }

    // //// CONFIGURATION

    public void setReportDefinitionDao(ReportDefinitionDao reportDefinitionDao) {
        this.reportDefinitionDao = reportDefinitionDao;
    }

    public void setExpeditedAdverseEventReportDao(
                    ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao) {
        this.expeditedAdverseEventReportDao = expeditedAdverseEventReportDao;
    }

    public void setReportRepository(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public void setAdverseEventEvaluationService(
                    AdverseEventEvaluationService adverseEventEvaluationService) {
        this.adverseEventEvaluationService = adverseEventEvaluationService;
    }

    public AdverseEventEvaluationService getAdverseEventEvaluationService() {
        return adverseEventEvaluationService;
    }

	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}
}

