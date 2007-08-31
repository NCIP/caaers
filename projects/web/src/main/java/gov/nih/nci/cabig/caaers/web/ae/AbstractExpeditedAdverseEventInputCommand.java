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
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.HashSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Rhett Sutphin
 */
public abstract class AbstractExpeditedAdverseEventInputCommand implements ExpeditedAdverseEventInputCommand {
    private static final Log log = LogFactory.getLog(AbstractExpeditedAdverseEventInputCommand.class);

    private ExpeditedAdverseEventReport aeReport;
    private Map<String, List<List<Attribution>>> attributionMap;
    private Map<ReportDefinition, Boolean> optionalReportDefinitionsMap;

    protected ExpeditedAdverseEventReportDao reportDao;
    protected ReportDefinitionDao reportDefinitionDao;

    protected List<String> mandatorySections;
    protected Map<String, Boolean> mandatoryFieldMap = new HashMap<String, Boolean>();

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
            ExpeditedAdverseEventReport merged = reportDao.merge(getAeReport());
            setAeReport(merged);
        }
    }

    public void setOptionalReportDefinitions(List<ReportDefinition> defs) {
        for (ReportDefinition def : defs) {
            if (!optionalReportDefinitionsMap.containsKey(def)) {
                optionalReportDefinitionsMap.put(def, false);
            }
        }
        // Deliberately not removing entries from the map that aren't in defs.
        // This is so that the user may still remove Reports whose ReportDefinitions
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
        Set<ReportDefinition> defsInAeReport = new HashSet<ReportDefinition>();
        for (Report report : this.getAeReport().getReports()) {
            log.debug("Found Report in new aeReport: "
                + report.getReportDefinition().getName() + " " + report.getId());
            log.debug("Report def hashCode is " + Integer.toHexString(report.getReportDefinition().hashCode()));
            if (!report.isRequired()) {
                defsInAeReport.add(report.getReportDefinition());
                optionalReportDefinitionsMap.put(report.getReportDefinition(), true);
                log.debug("Report is not required, so added to optional reports map: " + optionalReportDefinitionsMap);
            }
        }
        // deselect any definitions which were already in the map but not in the aeReport
        for (ReportDefinition inMap : optionalReportDefinitionsMap.keySet()) {
            if (!defsInAeReport.contains(inMap)) optionalReportDefinitionsMap.put(inMap, false);
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

    public List<String> getMandatorySections() {
    	return mandatorySections;
    }

    public void setMandatorySections(List<String> sections) {
    	this.mandatorySections = sections;
    }

    public void refreshMandatoryFieldMap(){
    	if(aeReport.getReports() == null) return;
    	for(Report report : aeReport.getReports()){
    		if(report.getReportDefinition().getMandatoryFields() == null) continue;
    		for(ReportMandatoryFieldDefinition field : report.getReportDefinition().getMandatoryFields()){
    			mandatoryFieldMap.put(field.getFieldPath(), field.getMandatory());
    		}
    	}
    }

	public Map<String, Boolean> getMandatoryFieldMap() {
		return mandatoryFieldMap;
	}

	@Override
    public String toString() {
        return new StringBuilder(getClass().getName())
            .append("[\n    aeReport: ").append(getAeReport())
            .append("\n    optionalReportDefinitionsMap: ").append(getOptionalReportDefinitionsMap())
            // TODO: This line is throwing an NPE sometimes.  Figure out why.
            // .append("\n    attributionMap: ").append(getAttributionMap())
            .append("\n]").toString();
    }
}
