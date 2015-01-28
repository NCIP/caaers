/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.ae;

import edu.emory.mathcs.backport.java.util.Collections;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.dto.ManageReportsRepotingPeriodDTO;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.repository.ReportValidationService;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.caaers.service.ReportSubmittability;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
public class ListAdverseEventsCommand {
    private StudyParticipantAssignment assignment;

    private Study study;

    private Participant participant;

    Map<Integer, Boolean> reportsSubmittable;
    
    Map<Integer, Boolean> dataEntryStatus;
    
    private boolean submitLinkRenderable;

    private boolean workflowEnabled = false;
    
    private ResearchStaffDao researchStaffDao;

    private ReportValidationService reportValidationService;
    
    private boolean amendAnOption;

    private Boolean userAEReviewer;
    
    private String inputDataEntryStatus;
    
    private String userId;
    
    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	// map for pagination
	private Map<String, LinkedList<ManageReportsRepotingPeriodDTO>> filteredResultMap = new HashMap<String, LinkedList<ManageReportsRepotingPeriodDTO>>();

	public Map<String, LinkedList<ManageReportsRepotingPeriodDTO>> getFilteredResultMap() {
		return filteredResultMap;
	}

	public void setFilteredResultMap(
			Map<String, LinkedList<ManageReportsRepotingPeriodDTO>> filteredResultMap) {
		this.filteredResultMap = filteredResultMap;
	}

	// map to store study/participant title as key and reporting period info as list
	private Map<String, LinkedList<ManageReportsRepotingPeriodDTO>> resultList = new HashMap<String, LinkedList<ManageReportsRepotingPeriodDTO>>();
    
	public Map<String, LinkedList<ManageReportsRepotingPeriodDTO>> getResultList() {
		return resultList;
	}

	public void setResultList(
			Map<String, LinkedList<ManageReportsRepotingPeriodDTO>> resultList) {
		this.resultList = resultList;
	}

	public String getInputDataEntryStatus() {
		return inputDataEntryStatus;
	}

	public void setInputDataEntryStatus(String inputDataEntryStatus) {
		this.inputDataEntryStatus = inputDataEntryStatus;
	}

    private List<Report> reports = new ArrayList<Report>();
    
	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	private HashMap<Object, Object> reportStatusOptionsMap = new LinkedHashMap<Object, Object>();
    
    public HashMap<Object, Object> getReportStatusOptionsMap() {
		return reportStatusOptionsMap;
	}

	public void setReportStatusOptionsMap(
			HashMap<Object, Object> reportStatusOptionsMap) {
		this.reportStatusOptionsMap = reportStatusOptionsMap;
	}

	protected final Collection<ReportStatus> REPORT_STATUS = new ArrayList<ReportStatus>(8);
    
    private ReportStatus reportStatus;
    
    private String searchIdentifier;
    
    private Integer maxResults;
    
	public Integer getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}

	public String getSearchIdentifier() {
		return searchIdentifier;
	}

	public void setSearchIdentifier(String searchIdentifier) {
		this.searchIdentifier = searchIdentifier;
	}

	public ReportStatus getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(ReportStatus reportStatus) {
		this.reportStatus = reportStatus;
	}
	
	private Boolean participantCentric = false;
	
	public Boolean getParticipantCentric() {
		return participantCentric;
	}

	public void setParticipantCentric(Boolean participantCentric) {
		this.participantCentric = participantCentric;
	}

	// flag to group results based on study or participant
	private Boolean studyCentric = false;
	
	public Boolean getStudyCentric() {
		return studyCentric;
	}

	public void setStudyCentric(Boolean studyCentric) {
		this.studyCentric = studyCentric;
	}

	/**
	 * Populate results.
	 *
	 * @param list the list
	 */
	public void populateResults(List<AdverseEventReportingPeriod> list){
		this.resultList.clear();
		for(AdverseEventReportingPeriod rp : list){
			ManageReportsRepotingPeriodDTO manageReportsRepotingPeriodDTO = new ManageReportsRepotingPeriodDTO(rp);
			int numberOfReports = getNumberOfReports(manageReportsRepotingPeriodDTO);
			if(numberOfReports > 0){
				manageReportsRepotingPeriodDTO.setNumberOfReports(numberOfReports);
				if(studyCentric){
					if(resultList.get(manageReportsRepotingPeriodDTO.getAdverseEventReportingPeriod().getParticipantSummaryLine()) != null){
						resultList.get(manageReportsRepotingPeriodDTO.getAdverseEventReportingPeriod().getParticipantSummaryLine()).add(manageReportsRepotingPeriodDTO);
					} else {
						LinkedList<ManageReportsRepotingPeriodDTO> reportsList = new LinkedList<ManageReportsRepotingPeriodDTO>();
						reportsList.add(manageReportsRepotingPeriodDTO);
						resultList.put(manageReportsRepotingPeriodDTO.getAdverseEventReportingPeriod().getParticipantSummaryLine(),reportsList);
					}
				} else if(participantCentric) {
					if(resultList.get(manageReportsRepotingPeriodDTO.getAdverseEventReportingPeriod().getStudySummaryLine()) != null){
						resultList.get(manageReportsRepotingPeriodDTO.getAdverseEventReportingPeriod().getStudySummaryLine()).add(manageReportsRepotingPeriodDTO);
					} else {
						LinkedList<ManageReportsRepotingPeriodDTO> reportsList = new LinkedList<ManageReportsRepotingPeriodDTO>();
						reportsList.add(manageReportsRepotingPeriodDTO);
						resultList.put(manageReportsRepotingPeriodDTO.getAdverseEventReportingPeriod().getStudySummaryLine(),reportsList);
					}
				} else {
					if(resultList.get(manageReportsRepotingPeriodDTO.getAdverseEventReportingPeriod().getId().toString()) != null){
						resultList.get(manageReportsRepotingPeriodDTO.getAdverseEventReportingPeriod().getId().toString()).add(manageReportsRepotingPeriodDTO);
					} else {
						LinkedList<ManageReportsRepotingPeriodDTO> reportsList = new LinkedList<ManageReportsRepotingPeriodDTO>();
						reportsList.add(manageReportsRepotingPeriodDTO);
						resultList.put(manageReportsRepotingPeriodDTO.getAdverseEventReportingPeriod().getId().toString(),reportsList);
					}
				}
				
			}
		}
	}
	
	protected Integer getNumberOfReports(ManageReportsRepotingPeriodDTO manageReportsRepotingPeriodDTO){
		manageReportsRepotingPeriodDTO.getReports().clear();
		Integer numberOfFilteredReportsInReportingPeriod = 0;
		for(Report report : this.getReports()){
			if(report.getAeReport().getReportingPeriod().getId().equals(manageReportsRepotingPeriodDTO.getAdverseEventReportingPeriod().getId())){
				if(! StringUtils.isBlank(inputDataEntryStatus)){
					if(dataEntryStatus.get(report.getId())!= null && (dataEntryStatus.get(report.getId()) ? "Complete" : "In-progress").equals(inputDataEntryStatus)){
						manageReportsRepotingPeriodDTO.getReports().add(report);
						++numberOfFilteredReportsInReportingPeriod;
					}
				} else{
					manageReportsRepotingPeriodDTO.getReports().add(report);
					++numberOfFilteredReportsInReportingPeriod;
				}
				
			}
		}
			
		return numberOfFilteredReportsInReportingPeriod;
	}
	
	public ListAdverseEventsCommand(ReportValidationService reportValidationService, ResearchStaffDao researchStaffDao) {
        this.reportValidationService = reportValidationService;
        this.researchStaffDao = researchStaffDao;
        reportsSubmittable = new HashMap<Integer, Boolean>();
        dataEntryStatus = new HashMap<Integer, Boolean>();
        
        REPORT_STATUS.addAll(Arrays.asList(ReportStatus.values()));        
        REPORT_STATUS.remove(ReportStatus.AMENDED);
        REPORT_STATUS.remove(ReportStatus.WITHDRAWN);
        REPORT_STATUS.remove(ReportStatus.REPLACED);
        REPORT_STATUS.remove(ReportStatus.WITHDRAW_FAILED);
        
        Collections.sort((List<ReportStatus>)REPORT_STATUS, new Comparator<ReportStatus>() {
			public int compare(ReportStatus o1, ReportStatus o2) {
				return o1.getDisplayName().compareTo(o2.getDisplayName());
			}
		});
        //reportStatusOptionsMap.putAll(WebUtils.collectCustomOptions(REPORT_STATUS, "name", "code", "displayName", ":  "));
        reportStatusOptionsMap.putAll(WebUtils.collectOptions(REPORT_STATUS, "name", "displayName"));
    }

    // //// LOGIC

	public void updateSubmittabilityBasedOnReportStatus(){
		for(Report report : reports){
			Boolean currentSubmittability = reportsSubmittable.get(report.getId());
			reportsSubmittable.put(report.getId(), currentSubmittability && (report.getStatus().equals(ReportStatus.PENDING) || report.getStatus().equals(ReportStatus.FAILED)));
		}
	}

    public void updateSubmittability() {
        reportsSubmittable.clear();
        dataEntryStatus.clear();
        for (Report report : reports) {
            if(report.getStatus().equals(ReportStatus.AMENDED) || report.getStatus().equals(ReportStatus.COMPLETED)){
                reportsSubmittable.put(report.getId(), true);
            }else{
                ReportSubmittability errorMessages = reportValidationService.isSubmittable(report);
                reportsSubmittable.put(report.getId(), errorMessages.isSubmittable());
            }

        }
        dataEntryStatus.putAll(reportsSubmittable);
    }
    
    public void updateSubmittabilityBasedOnWorkflow() {

    	boolean isSuperUser = SecurityUtils.checkAuthorization(UserGroupType.caaers_super_user);
    	if(isSuperUser) return; //super user can submit always, but based on data entry status. 
    	
    	boolean canLoggedInUserSubmit = SecurityUtils.checkAuthorization(UserGroupType.ae_reporter,
				UserGroupType.ae_expedited_report_reviewer);

        //now check if the workflow is enabled on the report ?
        for(Report report : reports){
            boolean canSubmit = reportsSubmittable.get(report.getId()) == null ? false :reportsSubmittable.get(report.getId());  //only AEReporter and AEReportReviewer can submit
            reportsSubmittable.put(report.getId(), canSubmit && canLoggedInUserSubmit);
            if(report.isWorkflowEnabled()){
               reportsSubmittable.put(report.getId(), canSubmit && isUserAEReviewer()); //only AEReportReviewer can submit
            }
        }
    	
    }
    
    public void updateOptions() {
    	boolean isSuperUser = SecurityUtils.checkAuthorization(UserGroupType.caaers_super_user);
    	if(isSuperUser || SecurityUtils.checkAuthorization(UserGroupType.ae_study_data_reviewer ,UserGroupType.ae_reporter)){
    		setAmendAnOption(true);
    	} else {
    		setAmendAnOption(false);
    	}
    	
    }

    /**
     * Will return true if the user is an Expedited Report Reviewer at the Cooordinating Center.
     * @return
     */
    private Boolean isUserAEReviewer(){
       if(userAEReviewer != null) return userAEReviewer;

        userAEReviewer = false;
        String loginId = SecurityUtils.getUserLoginName();
        boolean isReportReviewer = SecurityUtils.checkAuthorization(UserGroupType.ae_expedited_report_reviewer);

        //now check if the sae coordinator is associated to the coordinaoting center
        if(isReportReviewer && getStudy() != null){
            Organization ccOrg = getStudy().getStudyCoordinatingCenter().getOrganization();
            ResearchStaff researchStaff = researchStaffDao.getByLoginId(loginId);
            if(researchStaff != null && ccOrg != null){
                for(SiteResearchStaff siteRs : researchStaff.getSiteResearchStaffsInternal()){
                    if(siteRs.getOrganization().getId().equals(ccOrg.getId())){
                        userAEReviewer = true;
                        break;
                    }
                }
            }
        }

       return userAEReviewer;
    }
    
    public Map<Integer, Boolean> getReportsSubmittable() {
        return reportsSubmittable;
    }
    
    public Map<Integer, Boolean> getDataEntryStatus(){
    	return dataEntryStatus;
    }

    // //// BOUND PROPERTIES
    public StudyParticipantAssignment getAssignment() {
		return assignment;
	}
    public void setAssignment(StudyParticipantAssignment assignment) {
		this.assignment = assignment;
	}
    
    public Study getStudy() {
		return study;
	}
    public void setStudy(Study study) {
		this.study = study;
	}
    public Participant getParticipant() {
		return participant;
	}
    public void setParticipant(Participant participant) {
		this.participant = participant;
	}
    
    public boolean getIgnoreCompletedStudy() {
        return false;
    }
    
    public boolean isSubmitLinkRenderable() {
		return submitLinkRenderable;
	}
    public void setSubmitLinkRenderable(boolean submitLinkRenderable) {
		this.submitLinkRenderable = submitLinkRenderable;
	}
    
    public boolean getWorkflowEnabled(){
    	return workflowEnabled;
    }
    
    public void setWorkflowEnabled(boolean workflowEnabled){
    	this.workflowEnabled = workflowEnabled;
    }

	public boolean isAmendAnOption() {
		return amendAnOption;
	}

	public void setAmendAnOption(boolean amendAnOption) {
		this.amendAnOption = amendAnOption;
	}

	public void setResearchStaffDao(ResearchStaffDao researchStaffDao){
		this.researchStaffDao = researchStaffDao;
	}
	
	public int getTotalResultsCount(){
		int count = 0;
		Set<String> keySet = resultList.keySet();
		for(String key : keySet){
			count = count + resultList.get(key).size();
		}
		return count;
	}
}
