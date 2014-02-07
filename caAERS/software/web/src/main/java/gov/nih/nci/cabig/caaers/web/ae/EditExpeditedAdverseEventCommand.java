/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Person;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.domain.repository.PersonRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.domain.repository.UserRepository;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.web.RenderDecisionManager;

import org.apache.commons.lang.StringUtils;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
public class EditExpeditedAdverseEventCommand extends AbstractExpeditedAdverseEventInputCommand {
	
    private String currentItem; //currentItem - corresponds to the item that we are working on now (eg: conmed, priorTherapy). 
    private String task; // will tell the action we perform on the current item.

    
    // //// LOGIC

    public EditExpeditedAdverseEventCommand(ExpeditedAdverseEventReportDao reportDao){
    	this.reportDao = reportDao;
    }
    
    public EditExpeditedAdverseEventCommand(ExpeditedAdverseEventReportDao expeditedAeReportDao, StudyDao studyDao,
            ReportDefinitionDao reportDefinitionDao,
            StudyParticipantAssignmentDao assignmentDao,
            AdverseEventReportingPeriodDao reportingPeriodDao,
            ExpeditedReportTree expeditedReportTree, 
            RenderDecisionManager renderDecisionManager, ReportRepository reportRepository,
            AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository,
            EvaluationService evaluationService) {
    	super(expeditedAeReportDao, reportDefinitionDao, reportingPeriodDao, expeditedReportTree , renderDecisionManager, reportRepository,
    			assignmentDao, adverseEventRoutingAndReviewRepository, null, null);
    		this.evaluationService = evaluationService;
    }
    
    public EditExpeditedAdverseEventCommand(ExpeditedAdverseEventReportDao expeditedAeReportDao, StudyDao studyDao,
            ReportDefinitionDao reportDefinitionDao,
            StudyParticipantAssignmentDao assignmentDao,
            AdverseEventReportingPeriodDao reportingPeriodDao,
            ExpeditedReportTree expeditedReportTree, 
            RenderDecisionManager renderDecisionManager, ReportRepository reportRepository,
            AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository,
            EvaluationService evaluationService,
            PersonRepository personRepository,
            UserRepository userRepository) {
    	super(expeditedAeReportDao, reportDefinitionDao, reportingPeriodDao, expeditedReportTree , renderDecisionManager, reportRepository, 
    			assignmentDao, adverseEventRoutingAndReviewRepository, personRepository, userRepository);
    		this.evaluationService = evaluationService;
    }

    
    public void saveReportingPeriod() {
    	reportingPeriodDao.save(aeReport.getReportingPeriod());
    }
    

    /**
     * This method returns the type of the command object (aeReport)
     */
    public String getCommandType(){
    	return "aeReport";
    }
    

	
	///BEAN METHODS

    @Override
    public StudyParticipantAssignment getAssignment() {
        return getAeReport().getAssignment();
    }

    @Override
    public Participant getParticipant() {
        return getAssignment().getParticipant();
    }

    @Override
    public Study getStudy() {
        return getAssignment().getStudySite().getStudy();
    }
    
    /**
     * Will tell which subitem that we are dealing with. 
     * @return
     */
    public String getCurrentItem() {
		return currentItem;
	}
    /**
     * Which tell which subitem that we are dealing with. 
     * @param currentItem
     */
    public void setCurrentItem(String currentItem) {
		this.currentItem = currentItem;
	}
    
    public String getTask() {
		return task;
	}
    public void setTask(String task) {
		this.task = task;
	}
    
   
	public AdverseEventReportingPeriod getAdverseEventReportingPeriod(){
		if(getAeReport() != null)
			return getAeReport().getReportingPeriod();
		return null;
	}

	public String fetchLoggedInUserEmail() {
		
		if ( !StringUtils.isBlank(loggedInUserEmail)){
				return loggedInUserEmail;
		}
		//set the default reporter as the logged-in person
		String loginId = SecurityUtils.getUserLoginName();
        if(loginId != null){
           Person loggedInPerson = getPersonRepository().getByLoginId(loginId);
           if(loggedInPerson != null && !StringUtils.isBlank(loggedInPerson.getEmailAddress())){
        	   loggedInUserEmail =  loggedInPerson.getEmailAddress();
           } else {
               User loggedInUser = getUserRepository().getUserByLoginName(loginId);
               if(loggedInUser != null){
            	   loggedInUserEmail = loggedInUser.getEmailAddress();
               }
           }
        }
        
        return loggedInUserEmail;
	}

}
