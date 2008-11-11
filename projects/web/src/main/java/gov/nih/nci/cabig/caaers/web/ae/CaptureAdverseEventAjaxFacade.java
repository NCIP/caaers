package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventCtcTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEventMeddraLowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.tools.ObjectTools;
import gov.nih.nci.cabig.caaers.web.dwr.AjaxOutput;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

public class CaptureAdverseEventAjaxFacade  extends CreateAdverseEventAjaxFacade{
	
	 private static Class<?>[] CONTROLLERS = { 	CaptureAdverseEventController.class   };
	 
	 @Override
	public Class<?>[] controllers() {
		return CONTROLLERS;
	}
	 
    /**
     * This function is called to fetch the content associated to a reporting period
     *   -  after we create a new reporting period
     *   -  after we select a reporting period from the combo box.
     *   
     *   A little bit on the working, 
     *     - Will refresh the assignment object, (to support newly added Reporting period ordering)
     *     - Will fetch the content associated to the reporting period by calling captureAdverseEventDetailSection.jsp
     *   - fetchDetailsOnly - true, 
     *     - Will refresh the reporting period
     *     - Will fetch only the details section.
     * @param reportingPeriodId
     * @return
     */
    
    public AjaxOutput refreshReportingPeriodAndGetDetails(int reportingPeriodId, boolean fetchOnlyDetails){
    	CaptureAdverseEventInputCommand command = (CaptureAdverseEventInputCommand)extractCommand();
    	AjaxOutput output = new AjaxOutput();
    	if(!fetchOnlyDetails){
    		command.refreshAssignment(reportingPeriodId);
        	List<AdverseEventReportingPeriod> rpList = ObjectTools.reduceAll(command.getAssignment().getReportingPeriods(), "id", "startDate" , "endDate", "name");
        	output.setObjectContent(rpList);
    	}else{
    		command.refreshReportingPeriod(reportingPeriodId);
    	}
    	
    	//get the content for the below html section. 
    	Map<String, String> params = new LinkedHashMap<String, String>(); // preserve order for testing
    	params.put("adverseEventReportingPeriod", "" + reportingPeriodId);
    	String html = renderAjaxView("captureAdverseEventDetailSection", 0, params);
    	output.setHtmlContent(html);
    	return output;
    }
    /**
     * Create AdverseEvent objects corresponding to the terms(listOfTermIDs).
     *  Add the following parameters to request :- 
     *     1. "index" - corresponds to begin (of AE).
     *     2. "ajaxView" - 'observedAdverseEventSection'
     *  
     * @param listOfTermIDs
     * @return
     */
    public AjaxOutput addObservedAE(int[] listOfTermIDs) {
        
    	AjaxOutput ajaxOutput = new AjaxOutput();
    	
    	
        CaptureAdverseEventInputCommand command = (CaptureAdverseEventInputCommand) extractCommand();
        int index = command.getAdverseEvents().size();
        
        List<Integer> filteredTermIDs = new ArrayList<Integer>();
        List<String> removedTerms = new ArrayList<String>();
        //filter off the terms that are already present
        for(int id : listOfTermIDs){
        	filteredTermIDs.add(id);
        }
        //remove from filteredTermIds, the ones that are avaliable in AE
        for(AdverseEvent ae : command.getAdverseEventReportingPeriod().getAdverseEvents()){
        	boolean removed = filteredTermIDs.remove(ae.getAdverseEventTerm().getTerm().getId());
        	if(removed) removedTerms.add(ae.getAdverseEventTerm().getFullName());
        }
        if(!removedTerms.isEmpty()){
        	String[] removedTermsArray = removedTerms.toArray(new String[]{});
        	ajaxOutput.setObjectContent(removedTermsArray);
        }
        if(filteredTermIDs.isEmpty()) return ajaxOutput;
        
        boolean isMeddra = command.getStudy().getAeTerminology().getTerm() == Term.MEDDRA;
        for(int id: filteredTermIDs){
        	AdverseEvent ae = new AdverseEvent();
        	ae.setSolicited(false);
        	ae.setRequiresReporting(false);
        	
        	if(isMeddra){
        		//populate MedDRA term
        		LowLevelTerm llt = lowLevelTermDao.getById(id);
        		AdverseEventMeddraLowLevelTerm aellt = new AdverseEventMeddraLowLevelTerm();
        		aellt.setLowLevelTerm(llt);
        		ae.setAdverseEventMeddraLowLevelTerm(aellt);
        		aellt.setAdverseEvent(ae);
        	}else{
        		//properly set CTCterm
        		CtcTerm ctc =ctcTermDao.getById(id);
        		AdverseEventCtcTerm aeCtc = new AdverseEventCtcTerm();
        		aeCtc.setCtcTerm(ctc);
        		ae.setAdverseEventCtcTerm(aeCtc);
        		aeCtc.setAdverseEvent(ae);
        	}
        	
        	ae.setReportingPeriod(command.getAdverseEventReportingPeriod());
        	command.getAdverseEvents().add(ae);
        }
        Map<String, String> params = new LinkedHashMap<String, String>(); // preserve order for testing
    	params.put("adverseEventReportingPeriod", "" + command.getAdverseEventReportingPeriod());
    	params.put("index", Integer.toString(index));
    	 
    	ajaxOutput.setHtmlContent(renderAjaxView("observedAdverseEventSection", 0, params));
        return ajaxOutput;
    }
    
    public AjaxOutput deleteAdverseEvent(int index, String reportId){
    	CaptureAdverseEventInputCommand command = (CaptureAdverseEventInputCommand) extractCommand();
    	AdverseEvent deletedAe = command.getAdverseEvents().get(index);
    	// Remove the adverseEvent from the list of AEs assosicated to the report which has id = deletedId
    	command.getAdverseEvents().remove(index);
    	
    	if(!reportId.equals("")){
    		Integer repId = Integer.decode(reportId);
    		for(ExpeditedAdverseEventReport aeReport: command.getAdverseEventReportingPeriod().getAeReports()){
    			if(repId.equals(aeReport.getId())){
    				//Delete the adverseEvent
    				aeReport.getAdverseEvents().remove(deletedAe);
    				Boolean useDefaultVersion = false;
    				for(Report report: aeReport.getReports()){
    					if(report.getReportDefinition().getAmendable()){
    						reportRepository.amendReport(report, useDefaultVersion);
    						// Set useDefaultVersion to true so that the reportVersionId is retained for all the reports 
    						// and just incremented for the 1st one in the list.
    						useDefaultVersion = true;
    					}
    				}
    			}
    		}
    	}
    	return new AjaxOutput();
    }
}
