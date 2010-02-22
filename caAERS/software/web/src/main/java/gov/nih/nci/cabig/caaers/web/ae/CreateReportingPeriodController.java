package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.*;
import gov.nih.nci.cabig.caaers.dao.workflow.WorkflowConfigDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.caaers.web.CaaersFieldConfigurationManager;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.web.RenderDecisionManager;
import gov.nih.nci.cabig.caaers.web.RenderDecisionManagerFactoryBean;
import gov.nih.nci.cabig.caaers.web.fields.*;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import gov.nih.nci.cabig.caaers.accesscontrol.SiteSecurityAfterInvocationCollectionFilteringProvider;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * The create flow of Reporting Period is handled by this controller.
 *
 * @author Sameer Sawant
 * @author Biju Joseph
 */

public class CreateReportingPeriodController extends SimpleFormController {

    private Logger log = Logger.getLogger(CreateReportingPeriodController.class);

    private static final String REPORTINGPERIOD_FIELD_GROUP = "ReportingPeriod";
    private InputFieldGroup reportingPeriodFieldGroup;
    private AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
    private StudyParticipantAssignmentDao assignmentDao;
    private ParticipantDao participantDao;
    private TreatmentAssignmentDao treatmentAssignmentDao;
    private StudyDao studyDao;
    private EpochDao epochDao;
    private AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository;
    private WorkflowConfigDao workflowConfigDao;
    private UserDao userDao;

    private Configuration configuration;
    private static final String viewName = "ae/createReportingPeriod";

    private RenderDecisionManagerFactoryBean renderDecisionManagerFactoryBean;
    private CaaersFieldConfigurationManager caaersFieldConfigurationManager;

    private String TAB_NAME = "gov.nih.nci.cabig.caaers.web.ae.CourseCycleTab";

    public CreateReportingPeriodController() {
        setFormView(viewName);
    }

    /**
     * Will return the ReportingPeriodCommand, after properly initializing {@link StudyParticipantAssignment}, {@link Study}, {@link AdverseEventReportingPeriod}
     */
    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {

        //obtain assignmentId and  id from request parameters
        int reportingPeriodId = NumberUtils.toInt(request.getParameter("id"), 0);
        int studyId = NumberUtils.toInt(request.getParameter("studyId"), 0);
        int participantId = NumberUtils.toInt(request.getParameter("participantId"), 0);

        //load assignment
        Study study = (studyId > 0) ? studyDao.getById(studyId) : null;
        Participant participant = (participantId > 0) ? participantDao.getById(participantId) : null;
        StudyParticipantAssignment assignment = null;
        if (study != null && participant != null)
            assignment = assignmentDao.getAssignment(participant, study);

        AdverseEventReportingPeriod reportingPeriod = (reportingPeriodId > 0) ? adverseEventReportingPeriodDao.getById(reportingPeriodId) : null;
        String mode = "";
        if (reportingPeriod != null)
            mode = "edit";
        else
            mode = "create";

        ReportingPeriodCommand command = new ReportingPeriodCommand(assignment, reportingPeriod, mode);
        command.setWorkflowEnabled(configuration.get(Configuration.ENABLE_WORKFLOW));
        //set the last selected treatment assignment in create mode.
        if (command.getReportingPeriod().getId() == null) {
            List<AdverseEventReportingPeriod> existingReportingPeriods = assignment.getReportingPeriods();
            if (CollectionUtils.isNotEmpty(existingReportingPeriods)) {
                TreatmentAssignment treatmentAssignment = existingReportingPeriods.get(0).getTreatmentAssignment();
                command.getReportingPeriod().setTreatmentAssignment(treatmentAssignment);
            }
        }

        return command;
    }

    @Override
    protected void initBinder(final HttpServletRequest request, final ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(false));
        binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, NumberFormat.getInstance(), true));
        ControllerTools.registerDomainObjectEditor(binder, epochDao);
        ControllerTools.registerDomainObjectEditor(binder, treatmentAssignmentDao);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Map referenceData(final HttpServletRequest request, final Object command, final Errors errors) throws Exception {
        Map<Object, Object> refDataMap = new LinkedHashMap<Object, Object>();
        ReportingPeriodCommand rpCommand = (ReportingPeriodCommand) command;
        Map<String, InputFieldGroup> groupMap = createFieldGroups(command);
        populateHelpAttributeOnFields(groupMap);

        RenderDecisionManager renderDecisionManager = renderDecisionManagerFactoryBean.getRenderDecisionManager();
        renderDecisionManager.reveal(caaersFieldConfigurationManager.getListOfApplicableFields(TAB_NAME));
        renderDecisionManager.conceal(caaersFieldConfigurationManager.getListOfNotApplicableFields(TAB_NAME));
        
        refDataMap.put("fieldGroups", groupMap);
        refDataMap.put("reportingPeriod_treatmentAssignment_required", isFieldRequired("reportingPeriod.treatmentAssignment"));
        return refDataMap;
    }

    /**
     * Creates the fields that are displayed.
     *
     * @param command
     */
    protected Map<String, InputFieldGroup> createFieldGroups(Object command) {
        InputFieldGroupMap fieldMap = new InputFieldGroupMap();
        reportingPeriodFieldGroup = new DefaultInputFieldGroup(REPORTINGPERIOD_FIELD_GROUP);

        reportingPeriodFieldGroup.getFields().add(InputFieldFactory.createDateField("assignment.startDateOfFirstCourse", "Start date of first course/cycle", isFieldRequired("assignment.startDateOfFirstCourse")));
        reportingPeriodFieldGroup.getFields().add(InputFieldFactory.createDateField("reportingPeriod.startDate", "Start date of this course/cycle", isFieldRequired("reportingPeriod.startDate")));
        InputField endDateField = InputFieldFactory.createDateField("reportingPeriod.endDate", "End date of this course/cycle", isFieldRequired("reportingPeriod.endDate"));
        reportingPeriodFieldGroup.getFields().add(endDateField);
        reportingPeriodFieldGroup.getFields().add(InputFieldFactory.createSelectField("reportingPeriod.epoch", "Treatment type", isFieldRequired("reportingPeriod.epoch"), createEpochOptions(command)));
        InputField cycleNumberField = InputFieldFactory.createNumberField("reportingPeriod.cycleNumber", "Course/cycle #", isFieldRequired("reportingPeriod.cycleNumber"));
        InputFieldAttributes.setSize(cycleNumberField, 2);
        reportingPeriodFieldGroup.getFields().add(cycleNumberField);

        fieldMap.addInputFieldGroup(reportingPeriodFieldGroup);
        return fieldMap;
    }


    private boolean isFieldRequired(String fieldPath){
        return caaersFieldConfigurationManager.isFieldMandatory(TAB_NAME, fieldPath);
    }

    /**
     * Validates the reporting period create/edit business rules.
     * 1. Should have all the field level validations met
     * 2. Should be associated to Treatment assignment
     * 3. Only one basline reporting period is allowed
     * 4. Validation on Datest @see {@link CreateReportingPeriodController#validateRepPeriodDates(AdverseEventReportingPeriod, List, Errors)}
     */
    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors) throws Exception {
        super.onBindAndValidate(request, command, errors);
        BeanWrapper commandBean = new BeanWrapperImpl(command);
        Map<String, InputFieldGroup> fieldGroups = createFieldGroups(command);

        //do all field level validation
        for (InputFieldGroup fieldGroup : fieldGroups.values()) {
            for (InputField field : fieldGroup.getFields()) {
                field.validate(commandBean, errors);
            }
        }

        ReportingPeriodCommand rpCommand = (ReportingPeriodCommand) command;
        AdverseEventReportingPeriod rPeriod = rpCommand.getReportingPeriod();
        List<AdverseEventReportingPeriod> rPeriodList = rpCommand.getAssignment().getReportingPeriods();

        //check the treatment assignment.
        if (isFieldRequired("reportingPeriod.treatmentAssignment")) {
            if (rPeriod.getTreatmentAssignment() == null || rPeriod.getTreatmentAssignment().getId() == null) {
                if (StringUtils.isEmpty(rPeriod.getTreatmentAssignmentDescription())) {
                    errors.reject("CRP_001", "Select the Treatment Assignment.");
                    return;
                }
            } else {
                if (rPeriod.getTreatmentAssignment().isRetired()) {
                    errors.reject("CRP_009", "Treatment assignment should be active");
                    return;
                }
            }
        }


        // Check for duplicate baseline Reporting Periods.
        if (rPeriod.getEpoch() != null && rPeriod.getEpoch().getName().equals("Baseline")) {
            for (AdverseEventReportingPeriod aerp : rPeriodList) {

                if (!aerp.getId().equals(rPeriod.getId()) && aerp.getEpoch() != null && aerp.getEpoch().getName().equals("Baseline")) {
                    InputField epochField = fieldGroups.get(REPORTINGPERIOD_FIELD_GROUP).getFields().get(3);
                    errors.rejectValue(epochField.getPropertyName(), "CRP_002", "A Baseline treatment type already exists");
                    return;
                }
            }
        }

        //validate the date related logic.
        if (!errors.hasErrors())
            validateRepPeriodDates(rpCommand.getReportingPeriod(), rpCommand.getAssignment().getReportingPeriods(), rpCommand.getAssignment().getStartDateOfFirstCourse(), errors);
    }

    /**
     * Validate the form,if no errors found, save the Reporting period object. Then return to
     * the success view.
     */
    @SuppressWarnings("unchecked")
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object cmd, BindException errors) throws Exception {
        ReportingPeriodCommand command = (ReportingPeriodCommand) cmd;

        AdverseEventReportingPeriod reportingPeriod = command.getReportingPeriod();
        reportingPeriod.setAssignment(command.getAssignment());

        // Here is the logic to allow treatment type to be optional:
        // Step 1. All the solicited adverse events which are not graded are removed from the reporting period.
        // Step 2. All the solicited aes belonging to the epoch chosen in the form are added to the reporting period as 
        // adverse events only if they are not already present in the reporting period.

        Map<Integer, Boolean> existingAeTermsIdMap = new HashMap<Integer, Boolean>();
        for (AdverseEvent ae : reportingPeriod.getAdverseEvents())
            if (!ae.getSolicited() || (ae.getSolicited() && ae.getGrade() != null)) {
                if (command.getStudy().getAeTerminology().getTerm() == Term.MEDDRA) {
                    existingAeTermsIdMap.put(ae.getAdverseEventMeddraLowLevelTerm().getLowLevelTerm().getId(), true);
                } else {
                    existingAeTermsIdMap.put(ae.getAdverseEventCtcTerm().getTerm().getId(), true);
                }
            } else
                ae.retire();

        adverseEventReportingPeriodDao.save(reportingPeriod);

        //initialize the solicited AEs
        if (reportingPeriod.getEpoch() != null) {
            for (SolicitedAdverseEvent sae : reportingPeriod.getEpoch().getArms().get(0).getSolicitedAdverseEvents()) {
                AdverseEvent adverseEvent = new AdverseEvent();
                adverseEvent.setSolicited(true);
                adverseEvent.setRequiresReporting(false);

                if (command.getStudy().getAeTerminology().getTerm() == Term.MEDDRA) {
                    if (!existingAeTermsIdMap.containsKey(sae.getLowLevelTerm().getId())) {
                        AdverseEventMeddraLowLevelTerm aellt = new AdverseEventMeddraLowLevelTerm();
                        aellt.setLowLevelTerm(sae.getLowLevelTerm());
                        adverseEvent.setAdverseEventMeddraLowLevelTerm(aellt);
                        aellt.setAdverseEvent(adverseEvent);
                    }
                } else {
                    if (!existingAeTermsIdMap.containsKey(sae.getCtcterm().getId())) {
                        AdverseEventCtcTerm aeCtcTerm = new AdverseEventCtcTerm();
                        aeCtcTerm.setCtcTerm(sae.getCtcterm());
                        adverseEvent.setAdverseEventTerm(aeCtcTerm);
                        if (sae.getOtherTerm() != null)
                            adverseEvent.setLowLevelTerm(sae.getOtherTerm());
                        aeCtcTerm.setAdverseEvent(adverseEvent);
                        if (command.getStudy().isExpectedAdverseEventTerm(sae.getCtcterm()))
                            adverseEvent.setExpected(true);
                    }
                }
                reportingPeriod.addAdverseEvent(adverseEvent);
            }
        }

        adverseEventReportingPeriodDao.save(reportingPeriod);

        //call workflow, to enact
        if (command.isWorkflowEnabled())
            adverseEventRoutingAndReviewRepository.enactReportingPeriodWorkflow(reportingPeriod);

        Map map = new LinkedHashMap();
        map.putAll(createFieldGroups(command));

        map.putAll(errors.getModel());
        ModelAndView modelAndView = new ModelAndView("ae/confirmReportingPeriod", map);

        return modelAndView;
    }

    /**
     * This method validates the dates of the reporting period created/edited.
     * 1. EndDate cannot be earlier than start date.
     * 2. For Non-Baseline reporting period, startdate and enddate must not be same.
     * 3. No other existing reporting period start date should fall within the start date and end date of the new reporting period.
     * 4. Newly created start date should not fall within any of the existing reporting period start and end dates.
     *
     * @return
     */
    protected void validateRepPeriodDates(AdverseEventReportingPeriod rPeriod, List<AdverseEventReportingPeriod> rPeriodList, Errors errors) {
        validateRepPeriodDates(rPeriod, rPeriodList, null, errors);
    }

    protected void validateRepPeriodDates(AdverseEventReportingPeriod rPeriod, List<AdverseEventReportingPeriod> rPeriodList, Date firstCourseDate, Errors errors) {

        Date startDate = rPeriod.getStartDate();
        Date endDate = rPeriod.getEndDate();

        // Check if the start date is equal to or before the end date.
        if (firstCourseDate != null && startDate != null && (firstCourseDate.getTime() - startDate.getTime() > 0)) {
            errors.rejectValue("reportingPeriod.startDate", "CRP_008", "Start date of this course/cycle cannot be earlier than the Start date of first course/cycle");
        }

        if (startDate != null && endDate != null && (endDate.getTime() - startDate.getTime() < 0)) {
            errors.rejectValue("reportingPeriod.endDate", "CRP_003", "End date cannot be earlier than Start date");
        }

        // Check if the start date is equal to end date.
        // This is allowed only for Baseline reportingPeriods and not for other reporting periods.
        if (rPeriod.getEpoch() != null && !rPeriod.getEpoch().getName().equals("Baseline")) {
            if (endDate != null && startDate.equals(endDate)) {
                errors.rejectValue("reportingPeriod.startDate", "CRP_004", "For Non-Baseline treatment type Start date cannot be equal to End date");
            }

        }

        // Check if the start date - end date for the reporting Period overlaps with the
        // date range of an existing Reporting Period.
        for (AdverseEventReportingPeriod aerp : rPeriodList) {
            Date sDate = aerp.getStartDate();
            Date eDate = aerp.getEndDate();

            if (!aerp.getId().equals(rPeriod.getId())) {

                //we should make sure that no existing Reporting Period, start date falls, in-between these dates.
                if (startDate != null && endDate != null) {
                    if (DateUtils.compareDate(sDate, startDate) >= 0 && DateUtils.compareDate(sDate, endDate) < 0) {
                        errors.rejectValue("reportingPeriod.endDate", "CRP_005", "Course/cycle cannot overlap with an existing course/cycle.");
                        break;
                    }
                } else if (startDate != null && DateUtils.compareDate(sDate, startDate) == 0) {
                    errors.rejectValue("reportingPeriod.startDate", "CRP_005", "Course/cycle cannot overlap with an existing course/cycle.");
                    break;
                }

                //newly created reporting period start date, should not fall within any other existing reporting periods
                if (sDate != null && eDate != null) {
                    if (DateUtils.compareDate(sDate, startDate) <= 0 && DateUtils.compareDate(startDate, eDate) < 0) {
                        errors.rejectValue("reportingPeriod.endDate", "CRP_005", "Course/cycle cannot overlap with an existing course/cycle.");
                        break;
                    }
                } else if (sDate != null && DateUtils.compareDate(sDate, startDate) == 0) {
                    errors.rejectValue("reportingPeriod.startDate", "CRP_005", "Course/cycle cannot overlap with an existing course/cycle.");
                    break;
                }
            }

            // If the epoch of reportingPeriod is not - Baseline , then it cannot be earlier than a Baseline
            if (rPeriod.getEpoch() != null && rPeriod.getEpoch().getName().equals("Baseline")) {
                if (aerp.getEpoch() != null && !aerp.getEpoch().getName().equals("Baseline")) {
                    if (DateUtils.compareDate(sDate, startDate) < 0) {
                        errors.rejectValue("reportingPeriod.startDate", "CRP_006", "Baseline treatment type cannot start after an existing Non-Baseline treatment type.");
                        return;
                    }
                }
            } else {
                if (aerp.getEpoch() != null && aerp.getEpoch().getName().equals("Baseline")) {
                    if (DateUtils.compareDate(startDate, sDate) < 0) {
                        errors.rejectValue("reportingPeriod.startDate", "CRP_007", "Non-Baseline treatment type cannot start before an existing Baseline treatment type.");
                        return;
                    }
                }
            }

        }


    }


    protected Map<Object, Object> createEpochOptions(final Object command) {
        Map<Object, Object> epochMap = new LinkedHashMap<Object, Object>();
        epochMap.put("", "Please select");
        ReportingPeriodCommand rpCommand = (ReportingPeriodCommand) command;
        Study study = rpCommand.getAssignment().getStudySite().getStudy();
        List<Epoch> epochList = study.getEpochs();
        for (Epoch epoch : epochList) {
            epochMap.put(epoch.getId(), epoch.getName());
        }
        return epochMap;
    }


    ///OBJECT PROPERTIES

    public AdverseEventReportingPeriodDao getAdverseEventReportingPeriodDao() {
        return adverseEventReportingPeriodDao;
    }

    @Required
    public void setAdverseEventReportingPeriodDao(AdverseEventReportingPeriodDao adverseEventReportingPeriodDao) {
        this.adverseEventReportingPeriodDao = adverseEventReportingPeriodDao;
    }

    public StudyParticipantAssignmentDao getAssignmentDao() {
        return assignmentDao;
    }

    @Required
    public void setAssignmentDao(StudyParticipantAssignmentDao assignmentDao) {
        this.assignmentDao = assignmentDao;
    }

    public StudyDao getStudyDao() {
        return studyDao;
    }

    @Required
    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    @Required
    public void setEpochDao(EpochDao epochDao) {
        this.epochDao = epochDao;
    }

    public ParticipantDao getParticipantDao() {
        return participantDao;
    }

    @Required
    public void setParticipantDao(ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }

    public TreatmentAssignmentDao getTreatmentAssignmentDao() {
        return treatmentAssignmentDao;
    }

    @Required
    public void setTreatmentAssignmentDao(TreatmentAssignmentDao treatmentAssignmentDao) {
        this.treatmentAssignmentDao = treatmentAssignmentDao;
    }

    public AdverseEventRoutingAndReviewRepository getAdverseEventRoutingAndReviewRepository() {
        return adverseEventRoutingAndReviewRepository;
    }

    public void setAdverseEventRoutingAndReviewRepository(
            AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository) {
        this.adverseEventRoutingAndReviewRepository = adverseEventRoutingAndReviewRepository;
    }

    public void setWorkflowConfigDao(WorkflowConfigDao workflowConfigDao) {
        this.workflowConfigDao = workflowConfigDao;
    }

    public WorkflowConfigDao getWorkflowConfigDao() {
        return workflowConfigDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Required
    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    // 
    protected void populateHelpAttributeOnFields(Map<String, InputFieldGroup> groupMap) {

        if (groupMap == null || groupMap.isEmpty()) return;
        for (InputFieldGroup group : groupMap.values()) {
            for (InputField field : group.getFields()) {
                setHelpKeyAttribute(field);
            }
        }
    }

    final protected void setHelpKeyAttribute(InputField field) {
        String helpKeyPrefix = (getViewName() != null) ? getViewName().replaceAll("/", ".") : "";
        String[] nameSubset = null;
        nameSubset = field.getPropertyName().split("\\.");
        field.getAttributes().put(InputField.HELP, helpKeyPrefix + "." + field.getPropertyName().replaceAll("(\\[\\d+\\])", ""));
    }

    public String getViewName() {
        return viewName;
    }

    @Required
    public void setCaaersFieldConfigurationManager(CaaersFieldConfigurationManager caaersFieldConfigurationManager){
        this.caaersFieldConfigurationManager = caaersFieldConfigurationManager;
    }

    public CaaersFieldConfigurationManager getCaaersFieldConfigurationManager() {
        return caaersFieldConfigurationManager;
    }

    public RenderDecisionManagerFactoryBean getRenderDecisionManagerFactoryBean() {
        return renderDecisionManagerFactoryBean;
    }

    public void setRenderDecisionManagerFactoryBean(RenderDecisionManagerFactoryBean renderDecisionManagerFactoryBean) {
        this.renderDecisionManagerFactoryBean = renderDecisionManagerFactoryBean;
    }
}
