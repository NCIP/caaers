package gov.nih.nci.cabig.caaers.web.ae;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.TreatmentInformation;
import gov.nih.nci.cabig.caaers.rules.domain.AdverseEventSDO;
import gov.nih.nci.cabig.caaers.rules.domain.StudySDO;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionService;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;

/**
 * @author Rhett Sutphin
 */
public class EditExpeditedAdverseEventCommand implements ExpeditedAdverseEventInputCommand {
    private ExpeditedAdverseEventReport aeReport;
    private Map<String, List<List<Attribution>>> attributionMap;

    private ExpeditedAdverseEventReportDao expeditedAeReportDao;

    ////// LOGIC

    public EditExpeditedAdverseEventCommand(ExpeditedAdverseEventReportDao expeditedAeReportDao) {
        this.expeditedAeReportDao = expeditedAeReportDao;
    }

    public StudyParticipantAssignment getAssignment() {
        return aeReport.getAssignment();
    }

    public Participant getParticipant() {
        return getAssignment().getParticipant();
    }

    public Study getStudy() {
        return getAssignment().getStudySite().getStudy();
    }

    public void save() {
        expeditedAeReportDao.save(getAeReport());
    }

    ////// BEAN PROPERTIES

    public ExpeditedAdverseEventReport getAeReport() {
        return aeReport;
    }

    public Map<String, List<List<Attribution>>> getAttributionMap() {
        return attributionMap;
    }

    public void setAeReport(ExpeditedAdverseEventReport aeReport) {
        this.aeReport = aeReport;
        if (aeReport.getAdverseEvents().size() == 0) {
            aeReport.addAdverseEvent(new AdverseEvent());
        }
        if (aeReport.getTreatmentInformation() == null) {
            aeReport.setTreatmentInformation(new TreatmentInformation());
        }
        this.attributionMap = new AttributionMap(aeReport);
    }
}
