package gov.nih.nci.cabig.caaers.rules.business.service;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.service.ReportSubmittability;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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

    /**
     * @return true if the given adverse event is severe in the context of the provided study, site,
     *         and participant
     */
    public boolean isSevere(StudyParticipantAssignment assignment, AdverseEvent adverseEvent) {
        boolean isSevere = false;

        try {
            String msg = adverseEventEvaluationService.assesAdverseEvent(adverseEvent, assignment
                            .getStudySite().getStudy());
            if ("SERIOUS_ADVERSE_EVENT".equals(msg)) {
                isSevere = true;
            }
        } catch (Exception e) {
            throw new CaaersSystemException("Could not assess the given AE", e);
        }

        return isSevere;
    }
    
    /**
     * This method will return a Map, whose keys are the {@link ReportDefinition} and values are the {@link AdverseEvent} that caused the {@link ReportDefinition}.
     * Note:- For every AdverseEvent, the list of report definition is obtained. A map is created with ReportDefinition as key and AdverseEvent as value. 
     * @param reportingPeriod
     * @return
     */
    public Map<ReportDefinition, List<AdverseEvent>> findRequiredReportDefinitions(AdverseEventReportingPeriod reportingPeriod){
    	Map<ReportDefinition, List<AdverseEvent>> map = new HashMap<ReportDefinition, List<AdverseEvent>>();
    		for(AdverseEvent ae : reportingPeriod.getReportableAdverseEvents()){
    			List<ReportDefinition> reportDefs = findRequiredReportDefinitions(null, Arrays.asList(ae), reportingPeriod.getStudy());
    			for(ReportDefinition reportDef : reportDefs){
    				if(map.containsKey(reportDef)){
    					map.get(reportDef).add(ae);
    				}else {
    					List<AdverseEvent> aeList = new ArrayList<AdverseEvent>();
    					aeList.add(ae);
    					map.put(reportDef, aeList);
    				}
    			}
    		}
    		
    		// Now we will filter out the amenable ReportDefinitions. 
    		// Prepare the Map needed for filterAmenableReportDefinitions method in EvaluationServiceImpl
    		Map<String, List<String>> repDefinitionNamesMap = new HashMap<String,List<String>>();
    		List<String> repDefnNames = new ArrayList<String>();
    		for(ReportDefinition rDef: map.keySet()){
    			if(rDef != null)
    				repDefnNames.add(rDef.getName());
    		}
    		repDefinitionNamesMap.put("RepDefnNames", repDefnNames);
    		
    		// Call filterAmenableReportDefinitions in EvaluationServiceImpl passing repDefinitionNamesMap
    		// The List returned is a list of ReportDefinitions with the earliest amenable reportDefinition
    		List<ReportDefinition> amenableFilterRepDefn = filterAmenableReportDefinitions(repDefinitionNamesMap);
    		Map<ReportDefinition, List<AdverseEvent>> filteredRepDefnToAeMap = updateReportDefnAesMap(map, 
    				amenableFilterRepDefn);  
    	return map;
    }
    /**
     * The report definitions that are marked as mandatory at rules engine. Bug fix : 12770, will
     * filter already instantiated reports (that are not in WITHDRAWN) status.
     * 
     * @param expeditedData -
     *                The {@link ExpeditedAdverseEventReport}
     * @return - The list of {@link ReportDefinition} objects, that are associated to this report.
     */
    public List<ReportDefinition> findRequiredReportDefinitions(
                    ExpeditedAdverseEventReport expeditedData, List<AdverseEvent> aeList, Study study) {
        Map<String, List<String>> map;
        List<ReportDefinition> defList = new ArrayList<ReportDefinition>();

        try {
            map = adverseEventEvaluationService.evaluateSAEReportSchedule(expeditedData, aeList, study);
        } catch (Exception e) {
            throw new CaaersSystemException(
                            "Could not determine the reports necessary for the given expedited adverse event data",
                            e);
        }
        
        defList = filterAmenableReportDefinitions(map);
        List<ReportDefinition> filterdReportDefs = new ArrayList<ReportDefinition>();
    	
        // Bug fix - 12770
        if(expeditedData != null){
        	if (defList.isEmpty() || expeditedData.getNonWithdrawnReports().isEmpty()) return defList;
        	// Will filter already instantiated reports.
        	for (Report report : expeditedData.getNonWithdrawnReports()) {
        		for (ReportDefinition def : defList) {
        			if (!def.getId().equals(report.getReportDefinition().getId())) {
        				filterdReportDefs.add(def);
        			}
        		}

        	}
        	return filterdReportDefs;
        }

        return defList;
    }
    
    /**
     * This method received a map of the reportDefinitions that are triggered by the rules for aes provided.
     * It in turns filters out the amenable reportDefinitions retaining the one with shortest time-frame.
     * It returns a list of reportDefinitions.
     * @params Map<String, List<String>>
     * @return
     */
    public List<ReportDefinition> filterAmenableReportDefinitions(Map<String,List<String>> map){
    	List<ReportDefinition> defList = new ArrayList<ReportDefinition>();
    	// this comparator is used to find the highest ranked report definition
        Comparator<ReportDefinition> c = new ReportDefinitionComparator();

        Set<String> keys = map.keySet();
        for (String key : keys) {
            List<String> reportDefNames = map.get(key);
            if (reportDefNames == null) continue;

            TreeSet<ReportDefinition> reportDefTreeSet = new TreeSet<ReportDefinition>(c);
            for (String reportDefName : reportDefNames) {
                ReportDefinition reportDef = reportDefinitionDao.getByName(reportDefName);
                if (reportDef.getAmendable()) {
                    reportDefTreeSet.add(reportDef);
                } else {
                    defList.add(reportDef);
                }
            }

            if (!reportDefTreeSet.isEmpty()) {
                defList.add(reportDefTreeSet.last());
            }
        }
        return defList;
    }
    
    /**
	 * Filter the ReportDefinition - Ae Map so that only the earliest Amendabe ReportDefinition is retained in the map.
	 * 
	 * @param map, the initial ReportDefinition to AdverseEvent List Map.
	 * @param repDefnList, the list containing the ReportDefinition to be retained.
	 * @return map, the final ReportDefinition to AdverseEvent List Map which has only one (earliest) amendable ReportDefinition.
	 */
	public Map<ReportDefinition, List<AdverseEvent>>updateReportDefnAesMap(Map<ReportDefinition, List<AdverseEvent>> map 
						, List<ReportDefinition> repDefnList){
		Map<ReportDefinition, Boolean> retainedDefns = new HashMap<ReportDefinition, Boolean>();
		for(ReportDefinition rd: repDefnList){
			retainedDefns.put(rd, Boolean.TRUE);
		}
		// Create a list of AdverseEvents that will be migrated to the only amenable reportDefinition retained in the result map
		List<AdverseEvent> aeList = new ArrayList<AdverseEvent>();
		List<ReportDefinition> removeRepDefn = new ArrayList<ReportDefinition>(); // This will have the list of reportDefinition 
		// that are to be removed from the map.
		
		for(ReportDefinition rd: map.keySet()){
			if(!retainedDefns.containsKey(rd)){
				for(AdverseEvent ae: map.get(rd))
					aeList.add(ae);
				removeRepDefn.add(rd);
			}
		}
		
		// Now remove the ReportDefinitions in the list removeRepDefn from the map
		for(ReportDefinition rd: removeRepDefn){
			map.remove(rd);
		}
		
		// Now the adverseEvents in aeList are appended to the list of adverseEvents associated to the only amenable ReportDefinition
		// in the filteredMap 
		Map<AdverseEvent, Boolean> existingAeMap = new HashMap<AdverseEvent, Boolean>();
		for(ReportDefinition rd: map.keySet()){
			if(rd.getAmendable()){
				for(AdverseEvent ae: map.get(rd))
					existingAeMap.put(ae, Boolean.TRUE);
				// Now add the AdverseEvents from aeList to this list
				for(AdverseEvent ae: aeList)
					if(!existingAeMap.containsKey(ae))
						map.get(rd).add(ae);
			}
		}
		return map;
	}

    /**
     * Evaluates the provided data and associates new {@link Report} instances with the given
     * {@link ExpeditedAdverseEventReport}.
     * <p>
     * This method may be called multiple times for the same expedited data. Implementors must be
     * sure not to add multiple {@link Report}s for the same {@link ReportDefinition}.
     * Implementors must also <em>not</em> remove
     * {@link gov.nih.nci.cabig.caaers.domain.report.Report}s if they don't evaluate as required
     * (e.g., some reports may have been directly selected by the user). Instead, implementors
     * should update the {@link Report#setRequired} flag.
     * 
     * @param expeditedData
     * @return the report definitions which the evaluation indicated were required.
     */
    /*
     * @Transactional(readOnly=false) public void addRequiredReports(ExpeditedAdverseEventReport
     * expeditedData) { Map<String,List<String>> map; List<String> reportDefinitionNames = new
     * ArrayList<String>(); try { map =
     * adverseEventEvaluationService.evaluateSAEReportSchedule(expeditedData); } catch (Exception e) {
     * throw new CaaersSystemException( "Could not determine the reports necessary for the given
     * expedited adverse event data", e); }
     * 
     * Set<String> keys = map.keySet(); for (String key : keys) { List<String> reportDefNames =
     * map.get(key); // TO-DO need to clarify this ranking incase of multi actions in rules if
     * (reportDefNames.size() != 0) { String reportDefName =
     * extractTopPriorityReportDefintionName(reportDefNames); System.out.println("adding ..." +
     * reportDefName); reportDefinitionNames.add(reportDefName); }
     * 
     * //uncomment the above part and comment the below code after figuring oout ranking. //for
     * (String reportDefName : reportDefNames) { //reportDefinitionNames.add(reportDefName); //} }
     * 
     * for (Object reportDefinitionName : reportDefinitionNames) {
     * 
     * ReportDefinition def = reportDefinitionDao.getByName(reportDefinitionName.toString()); Report
     * report = existingReportWithDef(expeditedData, def);
     * 
     * if (report == null) { report = reportService.createReport(def, expeditedData);
     * //expeditedAdverseEventReportDao.save(expeditedData); } report.setRequired(true); }
     *  }
     */
    /**
     * Will create the Report by calling ReportService, then saves the ExpeditedAdverseEventReport
     */
    @Transactional(readOnly = false)
    public void addOptionalReports(ExpeditedAdverseEventReport expeditedData,
                    Collection<ReportDefinition> reportDefs, Boolean useDefaultVersion) {
        for (ReportDefinition def : reportDefs) {
            reportRepository.createReport(def, expeditedData, useDefaultVersion);
        }
        expeditedAdverseEventReportDao.save(expeditedData);
    }

    private Report existingReportWithDef(ExpeditedAdverseEventReport expeditedData,
                    ReportDefinition def) {
        for (Report report : expeditedData.getReports()) {
            log.debug("Examining Report with def " + report.getReportDefinition().getName()
                            + " (id: " + report.getReportDefinition().getId() + "; hash: "
                            + Integer.toHexString(report.getReportDefinition().hashCode()) + ')');
            if (report.getReportDefinition().equals(def)) {
                log.debug("Matched");
                return report;
            }
        }
        log.debug("No Report with def matching " + def.getName() + " (id: " + def.getId()
                        + "; hash: " + Integer.toHexString(def.hashCode()) + ") found in EAER "
                        + expeditedData.getId());
        return null;
    }

    /**
     * @return All the report definitions which might apply to the given study, site, and
     *         participant
     */
    // TODO: it might more sense for this to go in ReportService
    public List<ReportDefinition> applicableReportDefinitions(StudyParticipantAssignment assignment) {
        List<ReportDefinition> reportDefinitions = new ArrayList<ReportDefinition>();
        // Same organization play multiple roles.
        Set<Integer> orgIdSet = new HashSet<Integer>();
        for (StudyOrganization studyOrganization : assignment.getStudySite().getStudy()
                        .getStudyOrganizations()) {
            orgIdSet.add(studyOrganization.getOrganization().getId());
        }
        for (Integer orgId : orgIdSet) {
            reportDefinitions.addAll(reportDefinitionDao.getAll(orgId));
        }
        
        /**
         * Get REport definitions of CTEP for DCP studies , because DCP uses CTEP 
         * report definitions also . TEMP fix
         */
        if (assignment.getStudySite().getStudy().getPrimaryFundingSponsor().getOrganization().getName().equals("Division of Cancer Prevention")) {
        	reportDefinitions.addAll(reportDefinitionDao.getAll(this.organizationDao.getByName("Cancer Therapy Evaluation Program").getId()));
        }
        
        return reportDefinitions;
    }

    @Deprecated
    private String extractTopPriorityReportDefinition(List<ReportDefinition> reportDefs) {

        // XXX: You could do this without the array conversion using Collections.sort
        Comparator<ReportDefinition> c = new ReportDefinitionComparator();
        ReportDefinition[] reportDefArray = new ReportDefinition[reportDefs.size()];
        java.util.Arrays.sort(reportDefs.toArray(reportDefArray), c);

        ReportDefinition reportDefinition = reportDefArray[reportDefArray.length - 1];
        return reportDefinition.getName();
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
