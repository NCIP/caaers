package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.query.ReportDefinitionQuery;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.attribution.AdverseEventAttribution;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.UnsatisfiedProperty;
import gov.nih.nci.cabig.caaers.domain.factory.ReportFactory;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportDelivery;
import gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportType;
import gov.nih.nci.cabig.caaers.service.ReportSubmittability;
import gov.nih.nci.cabig.caaers.service.ReportWithdrawalService;
import gov.nih.nci.cabig.caaers.service.SchedulerService;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.utils.RoleUtils;
import gov.nih.nci.cabig.ctms.lang.NowFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Biju Joseph
 */
@Transactional(readOnly = false)
public class ReportRepositoryImpl implements ReportRepository {

    private static final Log log = LogFactory.getLog(ReportRepositoryImpl.class);
    private ReportDao reportDao;
    private ReportDefinitionDao reportDefinitionDao;
    private StudyDao studyDao;
    private SchedulerService schedulerService;
    private ReportWithdrawalService reportWithdrawalService;

    private ReportFactory reportFactory;
    private NowFactory nowFactory;
    private Configuration configuration;
    private AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository;


	/**
	 * This method will amend/unamend/withdraw/create the reports. 
	 * @param aeReport- The expedited report
	 * @param toAmendList - The list of reports to amend
	 * @param toUnAmendList - The list of reports to unamend
	 * @param toWithdrawList - The list of reports to withdraw
	 * @param toCreateList - The list of reports to create
	 */
    @Transactional(readOnly = false)
    public void processReports(ExpeditedAdverseEventReport aeReport,List<Report> toAmendList,List<Report> toUnAmendList,
    		List<Report> toWithdrawList, List<ReportDefinition> toCreateList) {
    	
    	//amend report to amend
    	if(CollectionUtils.isNotEmpty(toAmendList)){
    		for(Report report : toAmendList){
    			Report reportToAmend = aeReport.findReportById(report.getId()); 
    			amendReport(reportToAmend);
    		}
    	}
    	
    	//un amend the reports
    	if(CollectionUtils.isNotEmpty(toUnAmendList)){
    		for(Report report : toUnAmendList){
    			Report reportToUnAmend = aeReport.findReportById(report.getId());
    			unAmendReport(reportToUnAmend);
    		}
    	}
    	
    	//figure out the reports that are getting only withdrawn 
    	List<Report> beingWithdrawnList = new ArrayList<Report>();
    	if(CollectionUtils.isNotEmpty(toWithdrawList)){
    		for(Report report : toWithdrawList){
        		Report reportToWithdraw = aeReport.findReportById(report.getId());
        		if(!isGettingReplaced(reportToWithdraw, toCreateList)){
        			beingWithdrawnList.add(reportToWithdraw);
        		}
        		withdrawReport(reportToWithdraw);
        	}
    	}
    	
    	//create new reports
    	if(CollectionUtils.isNotEmpty(toCreateList)){
    		for(ReportDefinition reportDefinition : toCreateList){
    			Report report = createReport(reportDefinition, aeReport);
    		}
    	}
    	

    	//withdraw reports from external agency if needed. 
    	if(CollectionUtils.isNotEmpty(beingWithdrawnList)){
    		for(Report report : beingWithdrawnList){
    			withdrawExternalReport(aeReport, report);
    		}
    	}
    	
    }
    
    /**
     * This method will return true, if same category (group-organization) report definition is 
     * available in the report definitions list
     */
    protected boolean isGettingReplaced(Report report, List<ReportDefinition> reportDefinitionList){
    	if(CollectionUtils.isEmpty(reportDefinitionList)) return false;
    	for(ReportDefinition reportDefinition : reportDefinitionList){
    		if(reportDefinition.isOfSameReportTypeAndOrganization(report.getReportDefinition())) return true;
    	}
    	
    	return false;
    }
    
    /**
     * Will find the external report to be withdrawn, and will withdraw that report from the system.
     * @param aeReport
     * @param report
     */
    @Transactional(readOnly = false)
    public void withdrawExternalReport(ExpeditedAdverseEventReport aeReport, Report report) {
    	Report reportToWithdraw = aeReport.findLastSubmittedReport(report.getReportDefinition());
		if(reportToWithdraw != null && reportToWithdraw.getReportDefinition().getReportType().equals(ReportType.NOTIFICATION)){
			reportWithdrawalService.withdrawExternalReport(aeReport,reportToWithdraw);
		}
    }
    
    @Transactional(readOnly = false)
    public void withdrawReport(Report report) {
        assert !report.getStatus().equals(ReportStatus.WITHDRAWN) : "Cannot withdraw a report that is already withdrawn";
        report.setStatus(ReportStatus.WITHDRAWN);
        report.setWithdrawnOn(nowFactory.getNow());
        report.setDueOn(null);
        schedulerService.unScheduleNotification(report);
        reportDao.save(report);
    }
    

    /**
     * {@inheritDoc}
     */

    @Transactional(readOnly = false)
    public Report createReport(ReportDefinition reportDefinition, ExpeditedAdverseEventReport aeReport) {
    	
    	//reassociate all the study orgs
    	studyDao.reassociateStudyOrganizations(aeReport.getStudy().getStudyOrganizations());
    	
//    	reportDefinitionDao.lock(reportDefinition);
    	
        Report report = reportFactory.createReport(reportDefinition, aeReport, reportDefinition.getBaseDate());
        
        //update report version, based on latest amendment. 
        Report lastSubmittedReport = aeReport.findLastSubmittedReport(reportDefinition);
        if(lastSubmittedReport != null){
        	report.getLastVersion().copySubmissionDetails(lastSubmittedReport.getLastVersion());
        	
        	String strLastVersionNumber = lastSubmittedReport.getLastVersion().getReportVersionId();
        	report.getLastVersion().setAmendmentNumber(Integer.parseInt(strLastVersionNumber));
        	report.getLastVersion().setReportVersionId(strLastVersionNumber);
        	
            if(lastSubmittedReport.getReportDefinition().getReportType().equals(ReportType.REPORT) ){
            	//increase the amendment number.
            	report.getLastVersion().incrementAmendmentNumber();
            	report.getLastVersion().incrementReportVersion();
            }
           
        }
        
        //set the manually selected flag.
        report.setManuallySelected(reportDefinition.isManuallySelected());
       
        //save the report
        reportDao.save(report);
        

        //schedule the report, if there are scheduled notifications.
        if (report.hasScheduledNotifications()) schedulerService.scheduleNotification(report);
        
        //Check if workflow needs to be instantiated for this report and instantiate one if needed.
        if(configuration.get(Configuration.ENABLE_WORKFLOW) && report.getReportDefinition().getWorkflowEnabled())
        	adverseEventRoutingAndReviewRepository.enactReportWorkflow(report);

        return report;
    }
    
    public List<Report> createChildReports(Report report) {
    	
    	List<Report> instantiatedReports = null;
    	//check if there is children
    	ReportDefinitionQuery query = new ReportDefinitionQuery();
    	query.filterByParent(report.getReportDefinition().getId());
    	List<ReportDefinition> rdChildren = (List<ReportDefinition>) reportDefinitionDao.search(query);
    	if(CollectionUtils.isNotEmpty(rdChildren)){
    		if(BooleanUtils.isTrue(report.isAmendable())){
    			amendReport(report);
    		}
    		
    		instantiatedReports = new ArrayList<Report>();
    		for(ReportDefinition rdChild : rdChildren){
    			Report childReport = createReport(rdChild, report.getAeReport());
    			instantiatedReports.add(childReport);
    		}
    	}
    	
    	return instantiatedReports;
    }

    /**
     * {@inheritDoc}
     * This method will amend the toAmend report,by making its status to {@link ReportStatus#AMENDED}.
     * Then creates a new report, based on the {@link ReportDefinition}, also copies the external ticket number
     * from toAmend to the newly created report.
     */
    public void createAndAmendReport(ReportDefinition repDef, Report toAmend,Boolean useDefaultVersion) {
//    	------ BJ : throw away this method --------------
//    	 Report report = reportFactory.createReport(repDef, toAmend.getAeReport(), null);
//    	 
//    	 //copy the assigned identifier (in case of AdEERS the ticket number)
//         //report.copySubmissionDetails(toAmend);
//         
//         //amend the toAmend Report, by updating its status
//         toAmend.setStatus(ReportStatus.AMENDED);
//         toAmend.getLastVersion().setAmendedOn(nowFactory.getNow());
//         
//         //save the amended report
//         reportDao.save(toAmend);
//         
//         //save the report
//         reportDao.save(report);
//
//         //schedule the report, if there are scheduled notifications.
//         if (report.hasScheduledNotifications()) schedulerService.scheduleNotification(report);
    	
    }
    
    /**
     * {@inheritDoc}
     */
    public List<ReportDelivery> findReportDeliveries(Report aReport) {
    	List<ReportDelivery> deliveries = new ArrayList<ReportDelivery>();
    	
    	//reload the report
    	Report report = reportDao.getById(aReport.getId());
    	ReportDefinition reportDefinition = report.getReportDefinition();
    	ExpeditedAdverseEventReport aeReport = report.getAeReport();
    	List<ReportDeliveryDefinition> deliveryDefinitions = reportDefinition.getDeliveryDefinitions();
    	
    	if(deliveryDefinitions != null){

            for (ReportDeliveryDefinition reportDeliveryDefinition : deliveryDefinitions) {
                //fetch the contact mechanism for role based entities.
                if (reportDeliveryDefinition.getEntityType() == ReportDeliveryDefinition.ENTITY_TYPE_ROLE) {
                	String roleName = reportDeliveryDefinition.getEndPoint();
                	List<String> addresses = null;
                	if(ArrayUtils.contains(RoleUtils.reportSpecificRoles, roleName)){
                		addresses = report.findEmailAddressByRole(roleName);
                	}else if(ArrayUtils.contains(RoleUtils.studySiteSpecificRoles, roleName)){
                		addresses = aeReport.getStudySite().findEmailAddressByRole(roleName);
                	}else{
                		addresses = aeReport.getStudy().findEmailAddressByRole(roleName);
                	}
                    for (String address : addresses) {
                        if (StringUtils.isNotEmpty(address)) {
                            ReportDelivery reportDelivery = reportDeliveryDefinition.createReportDelivery();
                            reportDelivery.setEndPoint(address);
                            deliveries.add(reportDelivery);
                        }
                    }
                } else {
                    if (StringUtils.isNotEmpty(reportDeliveryDefinition.getEndPoint())) {
                        ReportDelivery reportDelivery = reportDeliveryDefinition.createReportDelivery();
                        reportDelivery.setEndPoint(reportDeliveryDefinition.getEndPoint());
                        deliveries.add(reportDelivery);
                    }
                }

            }
        
    	}
    	
    	return deliveries;
    }
    
    /**
     * This method will un-amend, an amended report.
     * @param report
     */
    public void unAmendReport(Report report){
    	
    	assert report.getStatus() == ReportStatus.AMENDED;
    	
    	report.setStatus(ReportStatus.COMPLETED);
    	report.setAmendedOn(null);

    	//increment the reportVersionId - CAAERS-3016
    	report.getLastVersion().incrementReportVersion();
    	
    	reportDao.save(report);
    }
    
    
   /**
    * This method will amend the report, by setting the report status to {@link ReportStatus#AMENDED}
    */
    public void amendReport(Report report){
    	report.setDueOn(null);
    	report.setStatus(ReportStatus.AMENDED);
    	report.setAmendedOn(nowFactory.getNow());
    	
    	reportDao.save(report);
    }
    
    
    @Required
    public void setStudyDao(StudyDao studyDao) {
		this.studyDao = studyDao;
	}
    
    @Required
    public void setReportDao(final ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    @Required
    public void setSchedulerService(final SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @Required
    public void setReportFactory(final ReportFactory reportFactory) {
        this.reportFactory = reportFactory;
    }

    @Required
    public void setNowFactory(final NowFactory nowFactory) {
        this.nowFactory = nowFactory;
    }
    
    @Required
    public void setReportDefinitionDao(ReportDefinitionDao reportDefinitionDao) {
		this.reportDefinitionDao = reportDefinitionDao;
	}
  
    @Required
    public void setReportWithdrawalService(ReportWithdrawalService reportWithdrawalService) {
		this.reportWithdrawalService = reportWithdrawalService;
	}
    
    @Required
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
    
    @Required
    public void setAdverseEventRoutingAndReviewRepository(AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository){
    	this.adverseEventRoutingAndReviewRepository = adverseEventRoutingAndReviewRepository;
    }
}
