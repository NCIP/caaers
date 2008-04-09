package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
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
import gov.nih.nci.cabig.caaers.service.ReportSubmittability;
import gov.nih.nci.cabig.caaers.service.SchedulerService;
import gov.nih.nci.cabig.ctms.lang.NowFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Transient;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Biju Joseph
 */
@Transactional(readOnly = false)
public class ReportRepositoryImpl implements ReportRepository {

    private static final Log log = LogFactory.getLog(ReportRepositoryImpl.class);
    private ReportDao reportDao;
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
        ReportVersion reportVersion = report.getLastVersion();
        if (reportVersion != null) reportVersion.setReportStatus(ReportStatus.WITHDRAWN);
        reportDao.save(report);
    }

    /**
     * {@inheritDoc}
     */

    @Transactional(readOnly = false)
    public Report createReport(ReportDefinition reportDefinition, ExpeditedAdverseEventReport aeReport) {
        Report report = reportFactory.createReport(reportDefinition, aeReport);

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
        if (report.getReportDefinition().getAttributionRequired()) {
            for (AdverseEvent ae : report.getAeReport().getAdverseEvents()) {
                Attribution max = null;
                for (AdverseEventAttribution<?> attribution : ae.getRequiredAttributions()) {
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
