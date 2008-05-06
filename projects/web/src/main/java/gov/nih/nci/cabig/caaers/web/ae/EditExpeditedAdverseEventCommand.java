package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.ReportFormatType;
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

    // //// LOGIC

    public EditExpeditedAdverseEventCommand(ExpeditedAdverseEventReportDao expeditedAeReportDao,
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
        Study study = getAssignment().getStudySite().getStudy();

        if (study.getReportFormat(ReportFormatType.ADEERSPDF) != null) {
            study.setAdeersPDFType(Boolean.TRUE);
        }
        if (study.getReportFormat(ReportFormatType.CAAERSXML) != null) {
            study.setCaaersXMLType(Boolean.TRUE);
        }
        if (study.getReportFormat(ReportFormatType.CIOMSFORM) != null) {
            study.setCiomsPDFType(Boolean.TRUE);
        }
        if (study.getReportFormat(ReportFormatType.CIOMSSAEFORM) != null) {
            study.setCiomsSaePDFType(Boolean.TRUE);
        }
        if (study.getReportFormat(ReportFormatType.DCPSAEFORM) != null) {
            study.setDcpSAEPDFType(Boolean.TRUE);
        }
        if (study.getReportFormat(ReportFormatType.MEDWATCHPDF) != null) {
            study.setMedwatchPDFType(Boolean.TRUE);
        }

        return study;
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
    
    @Override
    public void flush() {
    	reportDao.flush();
    }
}
