package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.ctms.lang.NowFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Rhett Sutphin
 */
public class CreateExpeditedAdverseEventCommand extends AbstractExpeditedAdverseEventInputCommand {
    private static final Log log = LogFactory.getLog(CreateExpeditedAdverseEventCommand.class);

    private Participant participant;
    private Study study;

    private ParticipantDao participantDao;
    private StudyDao studyDao;
    private StudyParticipantAssignmentDao assignmentDao;

    public CreateExpeditedAdverseEventCommand(
        StudyParticipantAssignmentDao assignmentDao, ExpeditedAdverseEventReportDao reportDao,
        ReportDefinitionDao reportDefinitionDao, StudyDao studyDao, ParticipantDao participantDao,
        NowFactory nowFactory, ExpeditedReportTree expeditedReportTree
    ) {
        super(reportDao, reportDefinitionDao, expeditedReportTree);
        this.assignmentDao = assignmentDao;
        this.studyDao = studyDao;
        this.participantDao = participantDao;
        setAeReport(new ExpeditedAdverseEventReport());
        getAeReport().setCreatedAt(nowFactory.getNowTimestamp());
    }

    ////// LOGIC

    @Override
    public StudyParticipantAssignment getAssignment() {
        if (getParticipant() != null && getStudy() != null) {
            return assignmentDao.getAssignment(getParticipant(), getStudy());
        } else {
            return null;
        }
    }

    // This method deliberately sets only one side of the bidirectional link.
    // This is so hibernate will not discover the link from the persistent side
    // (assignment) and try to save the report before we want it to.
    private void updateReportAssignmentLink() {
        getAeReport().setAssignment(getAssignment());
    }

    @Override
    public void save() {
        getAssignment().addReport(getAeReport());
        reportDao.save(getAeReport());
    }

    @Override
    public void reassociate() {
        super.reassociate();
        if (getStudy() != null) {
            // full reload instead of reassoc. to work around problem when
            // multiple copies of the same Organization are loaded
            setStudy(studyDao.getById(getStudy().getId()));
        }
        if (getParticipant() != null) participantDao.reassociate(getParticipant());
    }

    ////// BOUND PROPERTIES

    @Override
    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
        updateReportAssignmentLink();
    }

    @Override
    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
//        // TODO: this is temporary -- need a cleaner way to force this to load
//        // in same session as study is loaded and/or reassociate study with hib session later
//        if (study != null) {
//            this.study.getStudyAgents().size();
//            this.study.getCtepStudyDiseases().size();
//            this.study.getStudySites().size();
//            for(StudySite site : study.getStudySites()){
//                site.getStudyPersonnels().size();
//            }
//        }
        updateReportAssignmentLink();
    }
}
