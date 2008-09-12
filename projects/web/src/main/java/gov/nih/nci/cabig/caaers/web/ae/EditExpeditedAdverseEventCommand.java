package gov.nih.nci.cabig.caaers.web.ae;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections15.ListUtils;

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
    
    private List<ReportDefinition> newlySelectedSponsorReports = new ArrayList<ReportDefinition>();
    private List<ReportDefinition> otherSelectedReports = new ArrayList<ReportDefinition>(); 
    List<ReportDefinition> newlySelectedDefs = new ArrayList<ReportDefinition>();
    
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
    
    /*public void initializeNewlySelectedReportDefinitions() {
		//List<ReportDefinition> instantiatedReportDefs = getInstantiatedReportDefinitions();
		//List<ReportDefinition> difference = ListUtils.subtract(getSelectedReportDefinitions(),instantiatedReportDefs);
		//return difference;
    	//TODO: Verify this. Newly selected should be all that are selected and not the difference with the 
    	// instantiated ones.
    	newlySelectedDefs.clear();
    	newlySelectedDefs.addAll(getSelectedReportDefinitions());
    }*/
    
    
    
    /**
     * This method classifies the newly selected reportDefinitions into 2 lists.
     * list1  - newlySelectedSponsorReports ( that has all the report definitions defined for the sponsor and are amendable and expedited)
     * list2 - otherSelectedReports (remaining reportDefinitions)
     * @param newlySelectedDefs
     * @param command
     */
    public void classifyNewlySelectedReportsDefinitons(){
    	String nciInstituteCode = getAeReport().getStudy().getPrimaryFundingSponsorOrganization().getNciInstituteCode();
    	for(ReportDefinition reportDefinition: newlySelectedDefs){
    		if(reportDefinition.getOrganization().getNciInstituteCode().equals(nciInstituteCode) && reportDefinition.getAmendable()
    													&& reportDefinition.getExpedited())
    			newlySelectedSponsorReports.add(reportDefinition);
    		else
    			otherSelectedReports.add(reportDefinition);
    	}
    }
    
    /**
     * This method returns a boolean. Its true if the earliest sponsor/amendable report selected is expected to be scheduled before the 
     * earliest pending sponsor-amendable-expedited report and returns false otherwise.
     * @param command
     * @return
     */
    public Boolean isNewlySelectedReportEarlier(){
    	Boolean isSelectedReportEarlier = false;
    	Report earliestPendingSponsorReport = getAeReport().getEarliestPendingSponsorReport();
    	for(ReportDefinition reportDefinition: newlySelectedSponsorReports){
    		if(reportDefinition.getExpectedDueDate().compareTo(earliestPendingSponsorReport.getDueOn()) < 0){
    			isSelectedReportEarlier = true;
    			break;
    		}
    	}
    		
    	return isSelectedReportEarlier;
    }

    
    /**
     * This method amends the reports in the list passed as a parameter to this method.
     */
    public void amendReports(List<Report> amendReportList){
    	for(Report report: amendReportList){
    		reportRepository.amendReport(report);
    	}
    }
    
    /**
     * This method withdraws the reports in the list passed as a parameter to this method.
     */
    public void withdrawReports(List<Report> withdrawReportList){
    	for(Report report: withdrawReportList){
    		reportRepository.deleteReport(report);
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
	
	public void setNewlySelectedSponsorReports(
			ArrayList<ReportDefinition> newlySelectedSponsorReports) {
		this.newlySelectedSponsorReports = newlySelectedSponsorReports;
	}
	
	public List<ReportDefinition> getNewlySelectedSponsorReports() {
		return newlySelectedSponsorReports;
	}
	
	public void setOtherSelectedReports(
			ArrayList<ReportDefinition> otherSelectedReports) {
		this.otherSelectedReports = otherSelectedReports;
	}
	
	public List<ReportDefinition> getOtherSelectedReports() {
		return otherSelectedReports;
	}
	
	public List<ReportDefinition> getNewlySelectedDefs() {
		return newlySelectedDefs;
	}
	
	public void setNewlySelectedDefs(List<ReportDefinition> newlySelectedDefs) {
		this.newlySelectedDefs = newlySelectedDefs;
	}
}
