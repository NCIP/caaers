package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventCtcTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEventMeddraLowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.ajax.AdverseEventReportingPeriodAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.tools.ObjectTools;
import gov.nih.nci.cabig.caaers.web.dwr.AjaxOutput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

public class CaptureAdverseEventAjaxFacade  extends CreateAdverseEventAjaxFacade{
	
	 private static Class<?>[] CONTROLLERS = { 	CaptureAdverseEventController.class   };
	 private AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
	 private AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository;
	 
	 @Override
	public Class<?>[] controllers() {
		return CONTROLLERS;
	}
	 
	 public AdverseEventRoutingAndReviewRepository getAdverseEventRoutingAndReviewRepository() {
			return adverseEventRoutingAndReviewRepository;
	 }
	
	 public void setAdverseEventRoutingAndReviewRepository(AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository) {
			this.adverseEventRoutingAndReviewRepository = adverseEventRoutingAndReviewRepository;
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
        command.reassociate();
        int index = command.getAdverseEvents().size();
        
        List<Integer> filteredTermIDs = new ArrayList<Integer>();
        //filter off the terms that are already present
        for(int id : listOfTermIDs){
        	filteredTermIDs.add(id);
        }


        if(filteredTermIDs.isEmpty()) return ajaxOutput;
        
        boolean isMeddra = command.getAdverseEventReportingPeriod().getStudy().getAeTerminology().getTerm() == Term.MEDDRA;
        for (int id : filteredTermIDs) {
            AdverseEvent ae = new AdverseEvent();
            ae.setSolicited(false);
            ae.setRequiresReporting(false);

            if (isMeddra) {
                //populate MedDRA term
                LowLevelTerm llt = lowLevelTermDao.getById(id);
                AdverseEventMeddraLowLevelTerm aellt = new AdverseEventMeddraLowLevelTerm();
                aellt.setLowLevelTerm(llt);
                ae.setAdverseEventMeddraLowLevelTerm(aellt);
                aellt.setAdverseEvent(ae);
            } else {
                //properly set CTCterm
                CtcTerm ctc = ctcTermDao.getById(id);
                AdverseEventCtcTerm aeCtc = new AdverseEventCtcTerm();
                aeCtc.setCtcTerm(ctc);
                ae.setAdverseEventCtcTerm(aeCtc);
                aeCtc.setAdverseEvent(ae);
                if (command.getAdverseEventReportingPeriod().getStudy().hasCTCTerm(ctc)) {
                    ae.setExpected(new Boolean(Boolean.TRUE));
                }
            }

            ae.setReportingPeriod(command.getAdverseEventReportingPeriod());
            command.getAdverseEvents().add(ae);
        }
        
        Map<String, String> params = new LinkedHashMap<String, String>(); // preserve order for testing
    	params.put("adverseEventReportingPeriod", "" + command.getAdverseEventReportingPeriod());
    	params.put("index", Integer.toString(index));
    	 
    	ajaxOutput.setHtmlContent(renderAjaxView("observedAdverseEventSection", 0, params));
    	reportingPeriodDao.save(command.getAdverseEventReportingPeriod());
        return ajaxOutput;
    }
    
    public AjaxOutput deleteAdverseEvent(int index, String reportId){
    	CaptureAdverseEventInputCommand command = (CaptureAdverseEventInputCommand) extractCommand();
    	command.reassociate();
    	
    	AdverseEvent deletedAe = command.getAdverseEvents().get(index);
    	boolean reportsGotAmmended = false;
    	ExpeditedAdverseEventReport ammendedReport = null;
    	//Remove the AE from expedited report, if needed
    	if(deletedAe.getReport() != null){
    		
			for(ExpeditedAdverseEventReport aeReport: command.getAdverseEventReportingPeriod().getAeReports()){
				if(aeReport.getId().equals(deletedAe.getReport().getId())){
					ammendedReport = aeReport;
					aeReport.getAdverseEvents().remove(deletedAe);
					break;
				}
			}
			
			//ammend the report if needed
			if(StringUtils.isNotEmpty(reportId) && ammendedReport != null){
				Boolean useDefaultVersion = false;
				for(Report report: ammendedReport.getReports()){
					if(report.getReportDefinition().getAmendable()){
						reportRepository.amendReport(report, useDefaultVersion);
						reportsGotAmmended = true;
						// Set useDefaultVersion to true so that the reportVersionId is retained for all the reports 
						// and just incremented for the 1st one in the list.
						useDefaultVersion = true;
					}
				}
			}
			
			deletedAe.setReport(null);
		}
    
    	// Remove the adverseEvent from the list of AEs assosicated to the report which has id = deletedId
    	command.getAdverseEvents().remove(index);
    	deletedAe.setReportingPeriod(null);
    	
    	reportingPeriodDao.save(command.getAdverseEventReportingPeriod());
    	
    	//enable new expedited report workflow, if the reports are ammended
    	if(reportsGotAmmended){
    		adverseEventRoutingAndReviewRepository.enactReportWorkflow(ammendedReport);    		
    	}
    	
    	return new AjaxOutput();
    }
    
    public AjaxOutput addReviewComment(String comment){
    	CaptureAdverseEventInputCommand command = (CaptureAdverseEventInputCommand) extractCommand();
    	command.reassociate();
    	String userId = getUserId();
    	adverseEventRoutingAndReviewRepository.addReportingPeriodReviewComment(command.getAdverseEventReportingPeriod(), comment, userId);
    	
        return fetchPreviousComments(command.getAdverseEventReportingPeriod().getId(), userId);
    }
    
    public AjaxOutput editReviewComment(String comment, Integer commentId){
    	CaptureAdverseEventInputCommand command = (CaptureAdverseEventInputCommand) extractCommand();
    	command.reassociate();
    	String userId = getUserId();
    	adverseEventRoutingAndReviewRepository.editReportingPeriodReviewComment(command.getAdverseEventReportingPeriod(), comment, userId, commentId);
    	
    	return fetchPreviousComments(command.getAdverseEventReportingPeriod().getId(), userId);
    }
    
    public AjaxOutput fetchPreviousComments(Integer entityId, String userId){
		Map params = new HashMap<String, String>();
		params.put(RoutingAndReviewCommentController.AJAX_ENTITY, "reportingPeriod");
        params.put(RoutingAndReviewCommentController.AJAX_ENTITY_ID, entityId.toString());
        params.put("userId", userId);
        params.put(RoutingAndReviewCommentController.AJAX_ACTION, "fetchComments");
        params.put(CaptureAdverseEventController.AJAX_SUBVIEW_PARAMETER, "reviewCommentsList");
        
		String html = renderCommentsAjaxView(params);
		AjaxOutput output = new AjaxOutput();
		output.setHtmlContent(html);
		return output;
	}
    
    public AjaxOutput retrieveReviewComments(){
    	CaptureAdverseEventInputCommand command = (CaptureAdverseEventInputCommand) extractCommand();
    	command.reassociate();
    	return fetchPreviousComments(command.getAdverseEventReportingPeriod().getId(), getUserId());
    }
    
    public AjaxOutput retrieveNextTransitions(){
    	CaptureAdverseEventInputCommand command = (CaptureAdverseEventInputCommand) extractCommand();
    	List<String> transitions = new ArrayList<String>();
    	if(command.getAdverseEventReportingPeriod().getWorkflowId() != null){
    		transitions = adverseEventRoutingAndReviewRepository.nextTransitionNames(command.getAdverseEventReportingPeriod().getWorkflowId(), getUserId());
    	}
    	AjaxOutput output = new AjaxOutput();
    	output.setObjectContent(transitions.toArray());
    	return output;
    }
    
    public AjaxOutput advanceWorkflow(String transitionToTake){
    	CaptureAdverseEventInputCommand command = (CaptureAdverseEventInputCommand) extractCommand();
    	command.reassociate();
    	List<String> transitions = adverseEventRoutingAndReviewRepository.advanceReportingPeriodWorkflow(command.getAdverseEventReportingPeriod().getWorkflowId(), transitionToTake, command.getAdverseEventReportingPeriod(), getUserId());
    	AjaxOutput output = new AjaxOutput();
    	output.setObjectContent(transitions.toArray());
    	return output;
    }
    
    @Required
    public void setAdverseEventReportingPeriodDao(AdverseEventReportingPeriodDao adverseEventReportingPeriodDao) {
		this.adverseEventReportingPeriodDao = adverseEventReportingPeriodDao;
	}
    
    public AjaxOutput fetchCourses(Integer studyId, Integer participantId){
    	Study study = studyDao.getById(studyId);
    	Participant participant = participantDao.getById(participantId);
    	StudyParticipantAssignment assignment = assignmentDao.getAssignment(participant, study);
    	List<AdverseEventReportingPeriodAjaxableDomainObject> courses = new ArrayList<AdverseEventReportingPeriodAjaxableDomainObject>();
    	AdverseEventReportingPeriodAjaxableDomainObject rpAjaxable;
    	if(assignment.getReportingPeriods() != null){
    		for(AdverseEventReportingPeriod rp: assignment.getReportingPeriods()){
    			rpAjaxable = new AdverseEventReportingPeriodAjaxableDomainObject();
    			rpAjaxable.setId(rp.getId());
    			rpAjaxable.setName(rp.getName());
    			rpAjaxable.setStartDate(rp.getStartDate());
    			rpAjaxable.setEndDate(rp.getEndDate());
    			rpAjaxable.setEpochName(rp.getEpoch().getName());
    			rpAjaxable.setTacCode(rp.getTreatmentAssignment().getCode());
    			rpAjaxable.setTacDescription(rp.getTreatmentAssignment().getDescription());
    			courses.add(rpAjaxable);
    		}
    	}
    	AjaxOutput output = new AjaxOutput();
    	output.setObjectContent(courses.toArray());
    	return output;
    }
    
    public AjaxOutput fetchCourseDetails(Integer id){
    	AdverseEventReportingPeriod rp = adverseEventReportingPeriodDao.getById(id);
    	AdverseEventReportingPeriodAjaxableDomainObject rpAjaxable = new AdverseEventReportingPeriodAjaxableDomainObject();
    	rpAjaxable.setId(rp.getId());
    	rpAjaxable.setStartDate(rp.getStartDate());
    	rpAjaxable.setEndDate(rp.getEndDate());
    	rpAjaxable.setEpochName(rp.getEpoch().getName());
    	rpAjaxable.setCycleNumber(rp.getCycleNumber());
    	rpAjaxable.setTacCode(rp.getTreatmentAssignment().getCode());
    	rpAjaxable.setTacDescription(rp.getTreatmentAssignment().getDescription());
    	
    	AjaxOutput output = new AjaxOutput();
    	output.setObjectContent(rpAjaxable);
    	return output;
    }
}
