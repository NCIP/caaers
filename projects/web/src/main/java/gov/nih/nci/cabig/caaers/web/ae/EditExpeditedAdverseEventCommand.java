package gov.nih.nci.cabig.caaers.web.ae;

import java.util.ArrayList;
import java.util.HashMap;
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
import gov.nih.nci.cabig.caaers.domain.report.Mandatory;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
import gov.nih.nci.cabig.caaers.dao.AdverseEventDao;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.web.RenderDecisionManager;
import gov.nih.nci.cabig.ctms.lang.NowFactory;

/**
 * @author Rhett Sutphin
 */
public class EditExpeditedAdverseEventCommand extends AbstractExpeditedAdverseEventInputCommand {
    private StudyParticipantAssignmentDao assignmentDao;
    private RenderDecisionManager renderDecisionManager;
    
    // //// LOGIC


    public void initialize(){
    	getAeReport().getAssignment().getLabLoads();
    	getAeReport().getParticipant().getIdentifiers();
    }
    
    public EditExpeditedAdverseEventCommand(ExpeditedAdverseEventReportDao expeditedAeReportDao,
            ReportDefinitionDao reportDefinitionDao,
            StudyParticipantAssignmentDao assignmentDao,
            AdverseEventReportingPeriodDao reportingPeriodDao,
            ExpeditedReportTree expeditedReportTree, 
            RenderDecisionManager renderDecisionManager) {
    	super(expeditedAeReportDao, reportDefinitionDao, reportingPeriodDao, expeditedReportTree);
    	this.assignmentDao = assignmentDao;
    	this.renderDecisionManager = renderDecisionManager;
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
    
    /**
     * This method will intialize the render decision manager, with the field display status.
     * @param reportDefs
     */
    public void initializeNotApplicableFields() {
    	//find the list of report definitions associated to the existing AE report, and the ones that are newly selected.
    	//Note:- Since there is a potential to throw LazyInit exception, we will use HashMap based logic to find the unique ReportDefinition.
    	HashMap<Integer , ReportDefinition> map = new HashMap<Integer, ReportDefinition>();
    	for(Report r : getAeReport().getNonWithdrawnReports()){
    		ReportDefinition rd = r.getReportDefinition();
    		map.put(rd.getId(), rd);
    	}
    	if(getSelectedReportDefinitions() != null){
    		for(ReportDefinition rd : getSelectedReportDefinitions()){
    			map.put(rd.getId(), rd);
    		}
    	}
    	//reassociate them with current running session
    	for(ReportDefinition rd : map.values()){
    		reportDefinitionDao.reassociate(rd);
    	}
    	
    	//Now call conceal or reveal on RenderDecisionManager
		for (ReportDefinition reportDefinition : map.values()) {
			for (ReportMandatoryFieldDefinition mandatoryField : reportDefinition.getMandatoryFields()) {
				if (mandatoryField.getMandatory().equals(Mandatory.NA)) {					
					renderDecisionManager.conceal("aeReport."+mandatoryField.getFieldPath());
				} 
			}
		}		
	}
    
	/**
	 * This method will check if the study selected is a DCP sponsored study and is AdEERS submittable.
	 * @return
	 */
	public boolean isDCPNonAdeersStudy(){
		if(getStudy() == null) return false;
		return (!getStudy().getAdeersReporting()) && getStudy().getPrimaryFundingSponsorOrganization().getNciInstituteCode().equals("DCP");
	}
}
