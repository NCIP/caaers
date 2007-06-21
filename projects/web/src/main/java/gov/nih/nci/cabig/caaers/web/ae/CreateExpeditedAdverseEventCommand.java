package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.rules.domain.AdverseEventSDO;
import gov.nih.nci.cabig.caaers.rules.domain.StudySDO;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionService;
import gov.nih.nci.cabig.ctms.lang.NowFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Rhett Sutphin
 */
public class CreateExpeditedAdverseEventCommand implements ExpeditedAdverseEventInputCommand {
    private static final Log log = LogFactory.getLog(CreateExpeditedAdverseEventCommand.class);

    private ExpeditedAdverseEventReport aeReport;

    private Participant participant;
    private Study study;

    private ExpeditedAdverseEventReportDao reportDao;
    private StudyParticipantAssignmentDao assignmentDao;

    private Map<String, List<List<Attribution>>> attributionMap;

    public CreateExpeditedAdverseEventCommand(
        StudyParticipantAssignmentDao assignmentDao, ExpeditedAdverseEventReportDao reportDao,
        NowFactory nowFactory
    ) {
        this.assignmentDao = assignmentDao;
        this.reportDao = reportDao;
        this.aeReport = new ExpeditedAdverseEventReport();
        this.aeReport.setCreatedAt(nowFactory.getNowTimestamp());
        // ensure there's at least one before the fields are generated
        this.aeReport.addAdverseEvent(new AdverseEvent());

        this.attributionMap = new AttributionMap(aeReport);
    }

    ////// LOGIC

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

    public void save() {
        getAssignment().addReport(getAeReport());
        reportDao.save(getAeReport());
    }

    ////// BOUND PROPERTIES

    public ExpeditedAdverseEventReport getAeReport() {
        return aeReport;
    }

    public Map<String, List<List<Attribution>>> getAttributionMap() {
        return attributionMap;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
        updateReportAssignmentLink();
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
        // TODO: this is temporary -- need a cleaner way to force this to load
        // in same session as study is loaded and/or reassociate study with hib session later
        if (study != null) {
            this.study.getStudyAgents().size();
            this.study.getCtepStudyDiseases().size();
            this.study.getStudySites().size();
            for(StudySite site : study.getStudySites()){
                site.getStudyPersonnels().size();
            }
        }
        updateReportAssignmentLink();
    }

    public void setAeReport(ExpeditedAdverseEventReport aeReport) {
        this.aeReport = aeReport;
    }
}
