package gov.nih.nci.cabig.caaers.web.ae;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Physician;
import gov.nih.nci.cabig.caaers.domain.Reporter;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.TreatmentInformation;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Sameer Sawant
 */

public abstract class AbstractExpeditedAdverseEventReportCommand implements
		ExpeditedAdverseEventReportCommand {

	private static final Log log = LogFactory
    .getLog(AbstractExpeditedAdverseEventInputCommand.class);
	
	private ExpeditedAdverseEventReport aeReport;
	
	protected ExpeditedAdverseEventReportDao reportDao;

    protected ReportDefinitionDao reportDefinitionDao;

    protected ExpeditedReportTree expeditedReportTree;
    
    private String treatmentDescriptionType;
    
    private int nextPage;
    
    public AbstractExpeditedAdverseEventReportCommand(ExpeditedAdverseEventReportDao reportDao,
            ReportDefinitionDao reportDefinitionDao, ExpeditedReportTree expeditedReportTree) {
    	this.reportDao = reportDao;
    	this.reportDefinitionDao = reportDefinitionDao;
    	this.expeditedReportTree = expeditedReportTree;
    }
    
    public abstract StudyParticipantAssignment getAssignment();

    public abstract Participant getParticipant();

    public abstract Study getStudy();
    
    public ExpeditedAdverseEventReport getAeReport() {
        return aeReport;
    }
    
    public void setAeReport(ExpeditedAdverseEventReport aeReport) {
        if (aeReport.getAdverseEvents().size() == 0) {
            aeReport.addAdverseEvent(new AdverseEvent());
        }
        if (aeReport.getTreatmentInformation() == null) {
            aeReport.setTreatmentInformation(new TreatmentInformation());
        }
        if(aeReport.getReporter() == null) aeReport.setReporter(new Reporter());
        if(aeReport.getPhysician() == null) aeReport.setPhysician(new Physician());
        this.aeReport = aeReport;
    }

    public String getTreatmentDescriptionType() {
        return treatmentDescriptionType;
    }

    public void setTreatmentDescriptionType(String type) {
        this.treatmentDescriptionType = type;
    }
    
    public boolean getIgnoreCompletedStudy() {
        return true;
    }
    
    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int page) {
        this.nextPage = page;
    }
}