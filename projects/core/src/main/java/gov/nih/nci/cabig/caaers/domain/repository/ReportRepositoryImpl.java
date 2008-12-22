package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.CourseAgent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.attribution.AdverseEventAttribution;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.UnsatisfiedProperty;
import gov.nih.nci.cabig.caaers.domain.factory.ReportFactory;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.service.ReportSubmittability;
import gov.nih.nci.cabig.caaers.service.SchedulerService;
import gov.nih.nci.cabig.caaers.service.workflow.WorkflowService;
import gov.nih.nci.cabig.ctms.lang.NowFactory;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Transient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.graph.exe.ProcessInstance;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Biju Joseph
 */
@Transactional(readOnly = false)
public class ReportRepositoryImpl implements ReportRepository {

    private static final Log log = LogFactory.getLog(ReportRepositoryImpl.class);
    private ReportDao reportDao;
    private ParticipantDao participantDao;
    private StudyDao studyDao;
    private ExpeditedReportTree expeditedReportTree;
    private SchedulerService schedulerService;

    private ReportFactory reportFactory;
    private NowFactory nowFactory;

    /**
     * {@inheritDoc}
     */
    @Transient
    public void withdrawLastReportVersion(Report report) {

        ReportVersion reportVersion = report.getLastVersion();
        reportVersion.setReportStatus(ReportStatus.WITHDRAWN);
        reportVersion.setWithdrawnOn(nowFactory.getNow());
        reportVersion.setDueOn(null);
    }

    @Transactional(readOnly = false)
    public void deleteReport(Report report) {
        assert !report.getStatus().equals(ReportStatus.WITHDRAWN) : "Cannot withdraw a report that is already withdrawn";
        schedulerService.unScheduleNotification(report);
        report.setStatus(ReportStatus.WITHDRAWN);
        withdrawLastReportVersion(report);
        reportDao.save(report);
    }

    /**
     * {@inheritDoc}
     */

    @Transactional(readOnly = false)
    public Report createReport(ReportDefinition reportDefinition, ExpeditedAdverseEventReport aeReport, Boolean useDefaultVersion) {
        Report report = reportFactory.createReport(reportDefinition, aeReport, useDefaultVersion);
        
        //save the report
        reportDao.save(report);
        

        //schedule the report, if there are scheduled notificaitons.
        if (report.hasScheduledNotifications()) schedulerService.scheduleNotification(report);

        return report;
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
                    unsatisfiedNode.getDisplayName(),
                    uProp.getBeanPropertyName());
        }
    }

    /**
     * This method has the logic needed to a report. Basically a new ReportVersion is created and added to the ReportVersions of th
     * report. The reportVersionId of the lastReportVersion is incremented by 1 and assigned to this reportVersion.
     * @return void
     * @author- Sameer Sawant
     */
    public void amendReport(Report report, Boolean useDefaultVersion){
    	
    	reportDao.reassociate(report);
    	participantDao.lock(report.getAeReport().getParticipant());
    	studyDao.lock(report.getAeReport().getStudy());
    	
    	ReportVersion reportVersion = new ReportVersion();
        reportVersion.setCreatedOn(nowFactory.getNow());
        reportVersion.setReportStatus(ReportStatus.PENDING);
        report.setStatus(ReportStatus.PENDING);
        
        // Set report due date
        Calendar cal = GregorianCalendar.getInstance();
        Date now = nowFactory.getNow();
        cal.setTime(now);
        cal.add(report.getReportDefinition().getTimeScaleUnitType().getCalendarTypeCode(), report.getReportDefinition().getDuration());
        report.setDueOn(cal.getTime());
        reportVersion.setDueOn(cal.getTime());
        
        // Logic to update the reportVersionId
        //ReportVersion lastVersion = report.getLastVersion();
        //Integer currentVersionId = Integer.parseInt(lastVersion.getReportVersionId());
        //currentVersionId++;
        //reportVersion.setReportVersionId(currentVersionId.toString());
        
        String nciInstituteCode = report.getAeReport().getStudy().getPrimaryFundingSponsorOrganization().getNciInstituteCode();
        Integer currentBaseVersion = Integer.parseInt(report.getAeReport().getCurrentVersionForSponsorReport(nciInstituteCode));
        Integer newVersionId = currentBaseVersion + 1;
        if(useDefaultVersion)
        	reportVersion.setReportVersionId(currentBaseVersion.toString());
        else
        	reportVersion.setReportVersionId(newVersionId.toString());
        
        report.addReportVersion(reportVersion);
        
        // Add notifications to the report object
        reportFactory.addScheduledNotifications(report.getReportDefinition(), report);
        
        // Save the report to save the scheduled notifications
        reportDao.merge(report);
        //reportDao.initialize(report.getScheduledNotifications());
        schedulerService.scheduleNotification(report);
    }
    

    /**
     * The repeating fields available in the mandatory sections will be pre-initialized here.
     */

    @SuppressWarnings("unchecked")
    public void initializeMandatorySectionFields(ExpeditedAdverseEventReport aeReport, Collection<ExpeditedReportSection> mandatorySections) {
        if (mandatorySections == null || mandatorySections.isEmpty()) {
            log.info("No mandatory sections available, so no fields will be pre initialized");
            return;
        }

        // pre-initialize lazy fields in mandatory sections.
        BeanWrapper wrapper = new BeanWrapperImpl(aeReport);
        for (ExpeditedReportSection section : mandatorySections) {
            assert (section != null) : "A section is null in command.getManatorySections()";

            TreeNode sectionNode = expeditedReportTree.getNodeForSection(section);
            if (sectionNode == null) log.warn("Unable to fetch TreeNode for section"  + section.name());

            assert (sectionNode != null) : section.toString()  + ", is not available in ExpeditedReportTree.";
            if (sectionNode.getChildren() == null) continue;

            for (TreeNode node : sectionNode.getChildren()) {
                if (node.isList()) {
                    log.info("Initialized '" + node.getPropertyName() + "' in section "  + section.name());
                    wrapper.getPropertyValue(node.getPropertyName() + "[0]");
                }
            }

            // special case, when TreatmentInformation (course&agents tab) is mandatory.
            // All StudyAgents associated with lead IND should be pre-initialized.
            if (ExpeditedReportSection.TREATMENT_INFO_SECTION.equals(section)) {
                List<CourseAgent> courseAgents = (List<CourseAgent>) wrapper.getPropertyValue(sectionNode.getChildren().get(0).getPropertyName() + ".courseAgents");
                if (courseAgents.size() <= 0) {
                    // first time, the user did not override system pre selection.
                    int i = 0;
                    for (StudyAgent agent : aeReport.getStudy().getStudyAgents()) {
                        if (agent.getPartOfLeadIND() != null && agent.getPartOfLeadIND()) {
                            CourseAgent courseAgent = courseAgents.get(i);
                            courseAgent.setStudyAgent(agent);
                            i++;
                        }
                    }
                }
            }

        }

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
   
  
}
