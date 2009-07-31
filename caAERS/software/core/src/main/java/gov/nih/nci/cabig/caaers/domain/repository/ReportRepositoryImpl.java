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
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.reportdefinition.ReportType;
import gov.nih.nci.cabig.caaers.service.ReportSubmittability;
import gov.nih.nci.cabig.caaers.service.SchedulerService;
import gov.nih.nci.cabig.ctms.lang.NowFactory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Transient;

import org.apache.commons.collections.CollectionUtils;
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
    private ParticipantDao participantDao;
    private StudyDao studyDao;
    private ExpeditedReportTree expeditedReportTree;
    private SchedulerService schedulerService;

    private ReportFactory reportFactory;
    private NowFactory nowFactory;


    public void withdrawOrReplaceReport(Report report){
    	schedulerService.unScheduleNotification(report);
        reportDao.save(report);
    }
    
    @Transactional(readOnly = false)
    public void withdrawReport(Report report) {
        assert !report.getStatus().equals(ReportStatus.WITHDRAWN) : "Cannot withdraw a report that is already withdrawn";
        report.setStatus(ReportStatus.WITHDRAWN);
        report.setWithdrawnOn(nowFactory.getNow());
        report.setDueOn(null);
        
        withdrawOrReplaceReport(report);
    }
    
    @Transactional(readOnly = false)
    public void replaceReport(Report report){
    	assert !report.getStatus().equals(ReportStatus.REPLACED) : "Cannot replace a report that is already replaced";
    	assert !report.getStatus().equals(ReportStatus.WITHDRAWN): "Cannot replace a report that is already withdrawn";
    	report.setStatus(ReportStatus.REPLACED);
    	report.getLastVersion().setReportStatus(ReportStatus.REPLACED);
    	report.setWithdrawnOn(nowFactory.getNow());
        report.setDueOn(null);
    	withdrawOrReplaceReport(report);
    }

    /**
     * {@inheritDoc}
     */

    @Transactional(readOnly = false)
    public Report createReport(ReportDefinition reportDefinition, ExpeditedAdverseEventReport aeReport, Date baseDate) {
    	
    	//reassociate all the study orgs
    	studyDao.reassociateStudyOrganizations(aeReport.getStudy().getStudyOrganizations());
    	
        Report report = reportFactory.createReport(reportDefinition, aeReport, baseDate);
        
        //update report version, based on latest amendment. 
        Report lastSubmittedReport = aeReport.findLastSubmittedReport(reportDefinition);
        if(lastSubmittedReport != null){
        	report.getLastVersion().copySubmissionDetails(lastSubmittedReport.getLastVersion());
        	
        	String strLastVersionNumber = lastSubmittedReport.getLastVersion().getReportVersionId();
            if(lastSubmittedReport.getReportDefinition().getReportType().equals(ReportType.REPORT) ){
            	//increase the amendment number.
            	int n = Integer.parseInt(strLastVersionNumber) + 1;
            	report.getLastVersion().setReportVersionId("" + n);
            }else{
            	//use the same number
            	report.getLastVersion().setReportVersionId(strLastVersionNumber);
            }
           
        }
        
       
        //save the report
        reportDao.save(report);
        

        //schedule the report, if there are scheduled notifications.
        if (report.hasScheduledNotifications()) schedulerService.scheduleNotification(report);

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
    			Report childReport = createReport(rdChild, report.getAeReport(), nowFactory.getNow());
    			instantiatedReports.add(childReport);
    		}
    	}
    	
    	return instantiatedReports;
    }

    /**
     * Will tell whether all the mandatory field for this report is duly filled.
     * Internally this will call the validate method for each element having children in the {@link ExpeditedReportTree}
     *
     * @param mandatorySections
     * @return ErrorMessages
     */
    public ReportSubmittability validate(Report report, Collection<ExpeditedReportSection> mandatorySections) {
        // TODO: should validate against complex rules

        ReportSubmittability messages = new ReportSubmittability();
        List<String> mandatoryFields = report.getMandatoryFieldList();

        for (ExpeditedReportSection section : mandatorySections) {
            if (section == null)
                throw new NullPointerException("The mandatory sections collection must not contain nulls");
            validate(report.getAeReport(), mandatoryFields, section, messages);
        }

        //biz rule - Attribution validation should be done if the ReportDefinition says that it is attributable
        if( report.getReportDefinition().getAttributionRequired()){
        	ExpeditedAdverseEventReport aeReport = report.getAeReport();
        	for (AdverseEvent ae : aeReport.getAdverseEvents()) {
        		Attribution max = null;
        		for (AdverseEventAttribution<?> attribution : ae.getAdverseEventAttributions()) {
        			if(attribution.getAttribution() == null) {max = null; break;} //special case when people click save again (after an error).
        			if (max == null || attribution.getAttribution().getCode() > max.getCode()) {
        				max = attribution.getAttribution();
		    		}
		    	}
        		if (max == null || max.getCode() < Attribution.POSSIBLE.getCode()) {
        			messages.addValidityMessage(ExpeditedReportSection.ATTRIBUTION_SECTION,
		    		String.format(
		    			"The adverse event, '%s, ' is not attributed to a cause. " +
		    			"An attribution of possible or higher must be selected for at least one of the causes.",
		    			ae.getAdverseEventTerm().getUniversalTerm()));
        		}
		    }
        }
        
        //biz rule - Physician Sign-Off should be true if the ReportDefinition says that Physician Sign-Off is needed.
        if(report.getReportDefinition().getPhysicianSignOff()){
        	if(report.getPhysicianSignoff() == null || !report.getPhysicianSignoff()){
        		messages.addValidityMessage(ExpeditedReportSection.SUBMIT_REPORT_SECTION, 
        				"Physician sign-off is mandatory for this report.");
        	}
        }
       return messages;
    }

    public ReportSubmittability validate(Report report) {
        return validate(report, Arrays.asList(ExpeditedReportSection.values()));
    }

    @SuppressWarnings("unchecked")
    private void validate(
            ExpeditedAdverseEventReport aeReport, List<String> mandatoryFields, ExpeditedReportSection section,
            ReportSubmittability messages
    ) {
        TreeNode sectionNode = expeditedReportTree.getNodeForSection(section);
        if (sectionNode == null)
            throw new CaaersSystemException("There is no section node in the report tree for " + section.name() + ".  This shouldn't be possible.");

        List<String> applicableFields = new LinkedList<String>();
        for (String field : mandatoryFields) {
            TreeNode n = sectionNode.find(field);
            if (n == null) continue;
            applicableFields.add(field);
        }
        List<UnsatisfiedProperty> unsatisfied = expeditedReportTree.verifyPropertiesPresent(
                applicableFields, aeReport);
        for (UnsatisfiedProperty uProp : unsatisfied) {
            TreeNode unsatisfiedNode = uProp.getTreeNode();

            messages.addMissingField(
                    section,
                    uProp.getDisplayName(),
                    uProp.getBeanPropertyName());
        }
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
     * This method will un-amend, an amended report.
     * @param report
     */
    public void unAmendReport(Report report){
    	
    	assert report.getStatus() == ReportStatus.AMENDED;
    	
    	report.setStatus(ReportStatus.COMPLETED);
    	report.setAmendedOn(null);
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
    public void setParticipantDao(ParticipantDao participantDao) {
		this.participantDao = participantDao;
	}
    
    @Required
    public void setReportDao(final ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    @Required
    public void setExpeditedReportTree(final ExpeditedReportTree expeditedReportTree) {
        this.expeditedReportTree = expeditedReportTree;
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
  
}
