package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.AnatomicSite;
import gov.nih.nci.cabig.caaers.domain.ChemoAgent;
import gov.nih.nci.cabig.caaers.domain.DiseaseCodeTerm;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;
import gov.nih.nci.cabig.caaers.domain.PriorTherapy;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.TreatmentInformation;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.report.Mandatory;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.web.RenderDecisionManager;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections15.FactoryUtils;
import org.apache.commons.collections15.list.LazyList;

/**
 * @author Rhett Sutphin
 */
public class EditExpeditedAdverseEventCommand extends AbstractExpeditedAdverseEventInputCommand {
    private StudyParticipantAssignmentDao assignmentDao;
    private RenderDecisionManager renderDecisionManager;
	
    private String currentItem; //currentItem - corresponds to the item that we are working on now (eg: conmed, priorTherapy). 
    private String task; // will tell the action we perform on the current item.
    private Integer index; //corresponds to the index of the item (eg: conmed[3])
    private Integer parentIndex; // corresponds to the index of the parent item (eg: priorTherapy[parentIndex].agents[index])
    
    private Map<Object, Object> studyDiseasesMap;
    
    private List<ReportDefinition> newlySelectedSponsorReports = new ArrayList<ReportDefinition>();
    private List<ReportDefinition> otherSelectedReports = new ArrayList<ReportDefinition>();
    List<ReportDefinition> newlySelectedDefs = new ArrayList<ReportDefinition>();
    
    private AnatomicSite metastaticDiseaseSite;
    private PreExistingCondition preExistingCondition;
    private PriorTherapy priorTherapy;
    private List<String> chemoAgents;
    private ChemoAgent chemoAgent;
    private String concomitantMedication;
    
    private Term studyTerminologyTerm;
    private AdverseEventReportingPeriod adverseEventReportingPeriod;
    

    // //// LOGIC


    public void initialize(){
    	Study study = getStudy();
    	if(study != null) study.getAeTerminology().getTerm();
    }
    
    public void initializeTreatmentInformation(){
    	ExpeditedAdverseEventReport aeReport = getAeReport();
    	TreatmentInformation treatmentInformation = aeReport.getTreatmentInformation();
    	treatmentInformation.setTreatmentAssignment(aeReport.getReportingPeriod().getTreatmentAssignment());
    	treatmentInformation.setFirstCourseDate(aeReport.getAssignment().getStartDateOfFirstCourse());
    	treatmentInformation.getAdverseEventCourse().setDate(aeReport.getReportingPeriod().getStartDate());
    	treatmentInformation.getAdverseEventCourse().setNumber(aeReport.getReportingPeriod().getCycleNumber());
    	treatmentInformation.setTotalCourses(aeReport.getAssignment().getMaxCycleNumber());
    }
    
    public EditExpeditedAdverseEventCommand(StudyParticipantAssignmentDao assignmentDao){
    	this.assignmentDao = assignmentDao;
    }
    
    public EditExpeditedAdverseEventCommand(ExpeditedAdverseEventReportDao expeditedAeReportDao,
            ReportDefinitionDao reportDefinitionDao,
            StudyParticipantAssignmentDao assignmentDao,
            AdverseEventReportingPeriodDao reportingPeriodDao,
            ExpeditedReportTree expeditedReportTree, 
            RenderDecisionManager renderDecisionManager, ReportRepository reportRepository) {
    	super(expeditedAeReportDao, reportDefinitionDao, reportingPeriodDao, expeditedReportTree);
    	this.assignmentDao = assignmentDao;
    	this.renderDecisionManager = renderDecisionManager;
    	this.chemoAgents = new ArrayList<String>(); // new ArrayList<ChemoAgent>();
    	this.reportRepository = reportRepository;
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
    
    public void deleteAttribution(DomainObject o){
    	reportDao.cascaeDeleteToAttributions(o, getAeReport());
    }
    
    public void synchronizeAndSaveAssignment(){
    	ExpeditedAdverseEventReport aeReport = getAeReport();
    	StudyParticipantAssignment assignment = aeReport.getAssignment();
    	assignment.synchronizeMedicalHistoryFromReportToAssignment(aeReport);
    	assignmentDao.save(assignment);
    }
    
    public Map<Object, Object> getStudyDiseasesOptions(DiseaseCodeTerm diseaseCodingTerm){
        if (studyDiseasesMap == null) {
            if (diseaseCodingTerm.equals(DiseaseCodeTerm.MEDDRA)) {
                studyDiseasesMap = WebUtils.collectOptions(getStudy().getMeddraStudyDiseases(), "id", "term.meddraTerm", "Please select");
            } else if (diseaseCodingTerm.equals(DiseaseCodeTerm.OTHER)) {
                studyDiseasesMap = WebUtils.collectOptions(getStudy().getStudyConditions(), "id", "term.conditionName", "Please select");
            } else {
                studyDiseasesMap = WebUtils.collectOptions(getStudy().getCtepStudyDiseases(), "id", "term.term", "Please select");
            }
        }
        return studyDiseasesMap;
    }
    
    
    /**
     * This method will intialize the render decision manager, with the field display status.
     * @param reportDefs
     */
    public void initializeNotApplicableFields() {
    	//find the list of report definitions associated to the existing AE report, and the ones that are newly selected.
    	//Note:- Since there is a potential to throw LazyInit exception, we will use HashMap based logic to find the unique ReportDefinition.
    	HashMap<Integer , ReportDefinition> map = new HashMap<Integer, ReportDefinition>();
    	
    	if(getSelectedReportDefinitions() != null){
    		for(ReportDefinition rd : getSelectedReportDefinitions()){
    			map.put(rd.getId(), rd);
    		}
    	}
    	
    	for(Report r : getAeReport().getReports()){
    		ReportDefinition rd = r.getReportDefinition();
    		map.put(rd.getId(), rd);
    	}
    	
    	//reassociate them with current running session
    	for(ReportDefinition rd : map.values()){
    		reportDefinitionDao.reassociate(rd);
    	}
    	
    	renderDecisionManager.updateRenderDecision(map.values());
    	
	}
       
    
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
    		if(reportDefinition.getOrganization().getNciInstituteCode().equals(nciInstituteCode) && reportDefinition.getAmendable() && reportDefinition.getExpedited())
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
    	// Set useDefaultVersion to false so that for the first report the reportVersionId is correctly incremented by 1.
    	Boolean useDefaultVersion = false;
    	for(Report report: amendReportList){
    		reportRepository.amendReport(report, useDefaultVersion);
    		// Set useDefaultVersion to true so that the reportVersionId is retained for all the reports 
    		// and just incremented for the 1st one in the list.
    		useDefaultVersion = true;
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
	
	
	///BEAN METHODS

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
    
    public Map<Object, Object> getStudyDiseasesMap() {
		return studyDiseasesMap;
	}
    public void setStudyDiseasesMap(Map<Object, Object> studyDiseasesMap) {
		this.studyDiseasesMap = studyDiseasesMap;
	}
    /**
     * Will tell which subitem that we are dealing with. 
     * @return
     */
    public String getCurrentItem() {
		return currentItem;
	}
    /**
     * Which tell which subitem that we are dealing with. 
     * @param currentItem
     */
    public void setCurrentItem(String currentItem) {
		this.currentItem = currentItem;
	}
    
    public String getTask() {
		return task;
	}
    public void setTask(String task) {
		this.task = task;
	}
    
    public Integer getIndex() {
		return index;
	}
    public void setIndex(Integer index) {
		this.index = index;
	}
    public void setParentIndex(Integer parentIndex) {
		this.parentIndex = parentIndex;
	}
    public Integer getParentIndex() {
		return parentIndex;
	}
    
    public AnatomicSite getMetastaticDiseaseSite() {
		return metastaticDiseaseSite;
	}
    public void setMetastaticDiseaseSite(AnatomicSite metastaticDiseaseSite) {
		this.metastaticDiseaseSite = metastaticDiseaseSite;
	}
    public PreExistingCondition getPreExistingCondition() {
		return preExistingCondition;
	}
    public void setPreExistingCondition(PreExistingCondition preExistingCondition) {
		this.preExistingCondition = preExistingCondition;
	}
    public PriorTherapy getPriorTherapy() {
		return priorTherapy;
	}
    public void setPriorTherapy(PriorTherapy priorTherapy) {
		this.priorTherapy = priorTherapy;
	}
    public List<String> getPriorTherapyAgents() {
		return LazyList.decorate(chemoAgents, FactoryUtils.nullFactory());
	}
    public void setPriorTherapyAgents(List<String> chemoAgents) {
		this.chemoAgents = chemoAgents;
	}
    
    public String getConcomitantMedication() {
		return concomitantMedication;
	}
    public void setConcomitantMedication(String concomitantMedication) {
		this.concomitantMedication = concomitantMedication;
	}
    
    public void setPriorTherapyAgent(ChemoAgent chemoAgent) {
		this.chemoAgent = chemoAgent;
	}
    public ChemoAgent getPriorTherapyAgent() {
		return chemoAgent;
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
	
	public Term getStudyTerminologyTerm() {
		if(studyTerminologyTerm == null){
			studyTerminologyTerm = getStudy().getAeTerminology().getTerm();
		}
		return studyTerminologyTerm;
	}
	
	public AdverseEventReportingPeriod getAdverseEventReportingPeriod(){
		if(getAeReport() != null)
			return getAeReport().getReportingPeriod();
		return null;
	}
	
}
