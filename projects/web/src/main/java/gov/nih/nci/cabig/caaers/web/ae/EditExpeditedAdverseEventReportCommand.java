package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ReportFormatType;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;

/**
 * @author Sameer Sawant
 */
public class EditExpeditedAdverseEventReportCommand extends AbstractExpeditedAdverseEventReportCommand {
    private StudyParticipantAssignmentDao assignmentDao;
    
    public EditExpeditedAdverseEventReportCommand(ExpeditedAdverseEventReportDao expeditedAeReportDao,
            ReportDefinitionDao reportDefinitionDao,
            StudyParticipantAssignmentDao assignmentDao,
            ExpeditedReportTree expeditedReportTree) {
    	super(expeditedAeReportDao, reportDefinitionDao, expeditedReportTree);
    	this.assignmentDao = assignmentDao;
    }
    
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
        return  getAssignment().getStudySite().getStudy();
    }
    
    public void save() {
        reportDao.save(getAeReport());
    }

}