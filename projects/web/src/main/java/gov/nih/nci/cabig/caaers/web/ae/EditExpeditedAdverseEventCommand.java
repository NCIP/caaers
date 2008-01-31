package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;

/**
 * @author Rhett Sutphin
 */
public class EditExpeditedAdverseEventCommand extends AbstractExpeditedAdverseEventInputCommand {
    private StudyParticipantAssignmentDao assignmentDao;

    ////// LOGIC

    public EditExpeditedAdverseEventCommand(
        ExpeditedAdverseEventReportDao expeditedAeReportDao,
        ReportDefinitionDao reportDefinitionDao,
        StudyParticipantAssignmentDao assignmentDao,
        ExpeditedReportTree expeditedReportTree
    ) {
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
        return getAssignment().getStudySite().getStudy();
    }

    @Override
    public void save() {
        reportDao.save(getAeReport());
    }

    @Override
    public void reassociate() {
        super.reassociate();
        assignmentDao.reassociate(getAssignment());
    }
}
