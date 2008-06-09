package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventDao;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.TreatmentAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ReportingPeriodCommand {
	private static final Log log = LogFactory.getLog(ReportingPeriodCommand.class);
    
    private AdverseEventReportingPeriod reportingPeriod;
    private Participant participant;
    private Study study;
    private StudyParticipantAssignment assignment;
    private AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
    private StudyParticipantAssignmentDao assignmentDao;
    private StudyDao studyDao;
    
    public ReportingPeriodCommand(AdverseEventReportingPeriodDao adverseEventReportingPeriodDao,
    		StudyParticipantAssignmentDao assignmentDao, StudyDao studyDao){
    	this.adverseEventReportingPeriodDao = adverseEventReportingPeriodDao;
    	this.assignmentDao = assignmentDao;
    	this.studyDao = studyDao;
    	this.reportingPeriod = new AdverseEventReportingPeriod();
    }
    
    public ReportingPeriodCommand(AdverseEventReportingPeriodDao adverseEventReportingPeriodDao,
    		StudyParticipantAssignmentDao assignmentDao, StudyDao studyDao, String idString){
    	this.adverseEventReportingPeriodDao = adverseEventReportingPeriodDao;
    	this.assignmentDao = assignmentDao;
    	this.studyDao = studyDao;
    	this.reportingPeriod = adverseEventReportingPeriodDao.getById(Integer.parseInt(idString));
    }
    
    public StudyParticipantAssignment getAssignment() {
    	if (assignment != null) {
            return assignment;
        } else if (getParticipant() != null && getStudy() != null) {
            assignment = assignmentDao.getAssignment(getParticipant(), getStudy());
            return assignment;
        } else {
        	// Currently hardcoded. Needs to be removed later.
        	assignment = assignmentDao.getById(30); 
            return assignment;
        }
    }
    
    public Participant getParticipant(){
    	if (participant != null) {
            return participant;
        } else if (assignment != null) {
            return assignment.getParticipant();
        }
    	return participant;
    }
    
    public AdverseEventReportingPeriod getReportingPeriod(){
    	return reportingPeriod;
    }
    
    public void setParticipant(Participant participant) {
        this.participant = participant;
    }
    
    public Study getStudy(){
    	if (study != null) {
            return study;
        } else if (assignment != null) {
            study = assignment.getStudySite().getStudy();
        }
    	// Currently Hard-coded. Needs to be removed.
    	study = studyDao.getById(9);
    	return study;
    }
    
    public void setStudy(Study study) {
        this.study = study;
    }
    
    public AdverseEventReportingPeriodDao getAdverseEventReportingPeriodDao(){
    	return adverseEventReportingPeriodDao;
    }
    
    public void setAdverseEventReportingPeriodDao(AdverseEventReportingPeriodDao adverseEventReportingPeriodDao){
    	this.adverseEventReportingPeriodDao = adverseEventReportingPeriodDao;
    }
    
    public StudyParticipantAssignmentDao getAssignmentDao(){
    	return assignmentDao;
    }
    
    public void setAssignmentDao(StudyParticipantAssignmentDao assignmentDao){
    	this.assignmentDao = assignmentDao;
    }
    
    public StudyDao getStudyDao(){
    	return studyDao;
    }
    
    public void setStudyDao(StudyDao studyDao){
    	this.studyDao = studyDao;
    }
}