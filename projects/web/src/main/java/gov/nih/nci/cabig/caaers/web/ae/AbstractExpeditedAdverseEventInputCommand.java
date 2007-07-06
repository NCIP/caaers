package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.TreatmentInformation;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.HashSet;

/**
 * @author Rhett Sutphin
 */
public abstract class AbstractExpeditedAdverseEventInputCommand implements ExpeditedAdverseEventInputCommand {
    private ExpeditedAdverseEventReport aeReport;
    private Map<String, List<List<Attribution>>> attributionMap;
    private Map<ReportDefinition, Boolean> optionalReportDefinitionsMap;

    protected ExpeditedAdverseEventReportDao reportDao;
    private ReportDefinitionDao reportDefinitionDao;

    public AbstractExpeditedAdverseEventInputCommand(ExpeditedAdverseEventReportDao reportDao, ReportDefinitionDao reportDefinitionDao) {
        this.reportDao = reportDao;
        this.reportDefinitionDao = reportDefinitionDao;
        this.optionalReportDefinitionsMap = new LinkedHashMap<ReportDefinition, Boolean>();
    }

    public abstract StudyParticipantAssignment getAssignment();

    public abstract Participant getParticipant();

    public abstract Study getStudy();

    public abstract void save();

    public void reassociate() {
        for (ReportDefinition definition : getOptionalReportDefinitionsMap().keySet()) {
            reportDefinitionDao.reassociate(definition);
        }
        if (getAeReport().getId() != null) {
            reportDao.reassociate(getAeReport());
        }
    }

    public void setOptionalReportDefinitions(List<ReportDefinition> defs) {
        for (ReportDefinition def : defs) {
            if (!optionalReportDefinitionsMap.containsKey(def)) {
                optionalReportDefinitionsMap.put(def, false);
            }
        }
        // Deliberately not removing entries from the map that aren't in defs.
        // This is so that the use may still remove Reports whose ReportDefinitions
        // are no longer associated with the study.
    }

    public Map<ReportDefinition, Boolean> getOptionalReportDefinitionsMap() {
        return optionalReportDefinitionsMap;
    }

    public void setAeReport(ExpeditedAdverseEventReport aeReport) {
        if (aeReport.getAdverseEvents().size() == 0) {
            aeReport.addAdverseEvent(new AdverseEvent());
        }
        if (aeReport.getTreatmentInformation() == null) {
            aeReport.setTreatmentInformation(new TreatmentInformation());
        }
        this.aeReport = aeReport;
        this.attributionMap = new AttributionMap(aeReport);
        updateOptionalReportDefinitionsMapFromAeReport();
    }

    private void updateOptionalReportDefinitionsMapFromAeReport() {
        Set<ReportDefinition> defs = new HashSet<ReportDefinition>();
        for (Report report : this.getAeReport().getReports()) {
            if (!report.isRequired()) {
                defs.add(report.getReportDefinition());
                optionalReportDefinitionsMap.put(report.getReportDefinition(), true);
            }
        }
        for (ReportDefinition inMap : optionalReportDefinitionsMap.keySet()) {
            if (!defs.contains(inMap)) optionalReportDefinitionsMap.put(inMap, false);
        }
    }

    public ExpeditedAdverseEventReport getAeReport() {
        return aeReport;
    }

    public Map<String, List<List<Attribution>>> getAttributionMap() {
        return attributionMap;
    }

    public void setAttributionMap(AttributionMap attributionMap) {
        this.attributionMap = attributionMap;
    }
}
