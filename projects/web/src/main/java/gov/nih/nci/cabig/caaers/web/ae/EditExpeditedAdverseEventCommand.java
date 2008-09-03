package gov.nih.nci.cabig.caaers.web.ae;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ReportFormatType;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.dao.AdverseEventDao;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.ctms.lang.NowFactory;

/**
 * @author Rhett Sutphin
 */
public class EditExpeditedAdverseEventCommand extends AbstractExpeditedAdverseEventInputCommand {
    private StudyParticipantAssignmentDao assignmentDao;
    private static final String ACTION_PARAMETER = "action";
    private static final String AE_LIST_PARAMETER = "adverseEventList";
    private static final String REPORT_ID_PARAMETER = "aeReportId";


    // //// LOGIC

/*    public EditExpeditedAdverseEventCommand(String action, List<AdverseEvent> aeList, AdverseEventReportingPeriod reportingPeriod,
    				ExpeditedAdverseEventReportDao expeditedAeReportDao,
                    ReportDefinitionDao reportDefinitionDao, AdverseEventReportingPeriodDao reportingPeriodDao,
                    StudyParticipantAssignmentDao assignmentDao,
                    ExpeditedReportTree expeditedReportTree, NowFactory nowFactory) {
        super(expeditedAeReportDao, reportDefinitionDao, reportingPeriodDao, expeditedReportTree);
        this.assignmentDao = assignmentDao;
        
        // Logic to createNew or Edit existing aeReport based on session attributes.
        
    } 
    */
    public void initialize(){
    	getAeReport().getAssignment().getLabLoads();
    	getAeReport().getParticipant().getIdentifiers();
    }
    
    public EditExpeditedAdverseEventCommand(ExpeditedAdverseEventReportDao expeditedAeReportDao,
            ReportDefinitionDao reportDefinitionDao,
            StudyParticipantAssignmentDao assignmentDao,
            AdverseEventReportingPeriodDao reportingPeriodDao,
            ExpeditedReportTree expeditedReportTree) {
    	//super(expeditedAeReportDao, reportDefinitionDao, expeditedReportTree);
    	super(expeditedAeReportDao, reportDefinitionDao, reportingPeriodDao, expeditedReportTree);
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
    
    @Override
    public void flush() {
    	reportDao.flush();
    }
}
