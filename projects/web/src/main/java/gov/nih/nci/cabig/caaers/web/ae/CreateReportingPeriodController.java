package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.*;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.web.fields.*;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The create flow of Reporting Period is handled by this controller.
 *
 * @author Sameer Sawant
 * @author Biju Joseph
 */

public class CreateReportingPeriodController extends SimpleFormController {

    private static final String REPORTINGPERIOD_FIELD_GROUP = "ReportingPeriod";
    private InputFieldGroup reportingPeriodFieldGroup;


    private AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
    private StudyParticipantAssignmentDao assignmentDao;
    private ParticipantDao participantDao;
    private TreatmentAssignmentDao treatmentAssignmentDao;
    private StudyDao studyDao;
    private EpochDao epochDao;

    public CreateReportingPeriodController() {
        setFormView("ae/createReportingPeriod");
    }

    /**
     * Will return the ReportingPeriodCommand, after properly initializing {@link StudyParticipantAssignment}, {@link Study}, {@link AdverseEventReportingPeriod}
     */
    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {

        //obtain assignmentId and  id from request parameters
        int assignmentId = NumberUtils.toInt(request.getParameter("assignmentId"), 0);
        int reportingPeriodId = NumberUtils.toInt(request.getParameter("id"), 0);

        //load assignment
        StudyParticipantAssignment assignment = (assignmentId > 0) ? assignmentDao.getById(assignmentId) : null;
        AdverseEventReportingPeriod reportingPeriod = (reportingPeriodId > 0) ? adverseEventReportingPeriodDao.getById(reportingPeriodId) : null;

        return new ReportingPeriodCommand(assignment, reportingPeriod);

    }

    @Override
    protected void initBinder(final HttpServletRequest request, final ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(false));
        ControllerTools.registerDomainObjectEditor(binder, epochDao);
        ControllerTools.registerDomainObjectEditor(binder, treatmentAssignmentDao);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Map referenceData(final HttpServletRequest request, final Object command, final Errors errors) throws Exception {
        Map<Object, Object> refDataMap = new LinkedHashMap<Object, Object>();
        ReportingPeriodCommand rpCommand = (ReportingPeriodCommand) command;
        refDataMap.put("fieldGroups", createFieldGroups(command));
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

        reportingPeriodFieldGroup.getFields().add(InputFieldFactory.createDateField("reportingPeriod.startDate", "Start date", true));
        reportingPeriodFieldGroup.getFields().add(InputFieldFactory.createDateField("reportingPeriod.endDate", "End date", true));
        reportingPeriodFieldGroup.getFields().add(InputFieldFactory.createSelectField("reportingPeriod.epoch", "Evaluation Period Type", true, createEpochOptions(command)));

        InputField descriptionField = InputFieldFactory.createTextArea("reportingPeriod.description", "Description", false);
        InputFieldAttributes.setColumns(descriptionField, 45);
        reportingPeriodFieldGroup.getFields().add(descriptionField);

        InputField cycleNumberField = InputFieldFactory.createNumberField("reportingPeriod.cycleNumber", "Cycle number", false);
        InputFieldAttributes.setSize(cycleNumberField, 2);
        reportingPeriodFieldGroup.getFields().add(cycleNumberField);

        reportingPeriodFieldGroup.getFields().add(InputFieldFactory.createSelectField("reportingPeriod.treatmentAssignment", "Treatment assignment", true, fetchTreatmentAssignmentOptions(command)));

        InputField tacDescriptionField = InputFieldFactory.createTextArea("reportingPeriod.treatmentAssignment.description", "Treatment description");
        InputFieldAttributes.setColumns(tacDescriptionField, 45);
        reportingPeriodFieldGroup.getFields().add(tacDescriptionField);

        fieldMap.addInputFieldGroup(reportingPeriodFieldGroup);
        return fieldMap;
    }


    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors) throws Exception {
        super.onBindAndValidate(request, command, errors);
        BeanWrapper commandBean = new BeanWrapperImpl(command);
        Map<String, InputFieldGroup> fieldGroups = createFieldGroups(command);
        for (InputFieldGroup fieldGroup : fieldGroups.values()) {
            for (InputField field : fieldGroup.getFields()) {
                field.validate(commandBean, errors);
            }
        }
        validateRepPeriodDates((ReportingPeriodCommand) command, fieldGroups, errors);
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

        //initialize the solicited AEs
        if (reportingPeriod.getAdverseEvents().isEmpty()) {
            for (SolicitedAdverseEvent sae : reportingPeriod.getEpoch().getArms().get(0).getSolicitedAdverseEvents()) {
                AdverseEvent adverseEvent = new AdverseEvent();
                adverseEvent.setSolicited(true);
                adverseEvent.setRequiresReporting(false);

                if (command.getStudy().getAeTerminology().getTerm() == Term.MEDDRA) {
                    adverseEvent.setLowLevelTerm(sae.getLowLevelTerm());
                } else {
                    AdverseEventCtcTerm aeCtcTerm = new AdverseEventCtcTerm();
                    aeCtcTerm.setCtcTerm(sae.getCtcterm());
                    adverseEvent.setAdverseEventTerm(aeCtcTerm);
                    aeCtcTerm.setAdverseEvent(adverseEvent);
                }
                reportingPeriod.addAdverseEvent(adverseEvent);
            }
        }
        adverseEventReportingPeriodDao.save(reportingPeriod);

        Map map = new LinkedHashMap();
        map.putAll(createFieldGroups(command));

        map.putAll(errors.getModel());
        ModelAndView modelAndView = new ModelAndView("ae/confirmReportingPeriod", map);

        return modelAndView;
    }

    /**
     * This method validates the dates of the reporting period created/edited.
     *
     * @param cmd
     * @return
     */
    protected void validateRepPeriodDates(ReportingPeriodCommand command, Map<String, InputFieldGroup> groups, BindException errors) {

        AdverseEventReportingPeriod rPeriod = command.getReportingPeriod();
        List<AdverseEventReportingPeriod> rPeriodList = command.getAssignment().getReportingPeriods();
        Date startDate = rPeriod.getStartDate();
        Date endDate = rPeriod.getEndDate();
        InputField field = groups.get(REPORTINGPERIOD_FIELD_GROUP).getFields().get(1);

        // Check for duplicate baseline Reporting Periods.
        if (rPeriod.getEpoch().getName().equals("Baseline")) {
            for (AdverseEventReportingPeriod aerp : rPeriodList) {
                if (!aerp.getId().equals(rPeriod.getId()) && aerp.getEpoch().getName().equals("Baseline")) {
                    InputField epochField = groups.get(REPORTINGPERIOD_FIELD_GROUP).getFields().get(2);
                    errors.rejectValue(epochField.getPropertyName(), "REQUIRED",
                            "A Baseline Evaluation Period already exists");
                    return;
                }
            }
        }

        // Check if the start date is equal to or before the end date.
        if (startDate != null && endDate != null && (endDate.getTime() - startDate.getTime() < 0)) {
            errors.rejectValue(field.getPropertyName(), "REQUIRED",
                    "End date cannot be earlier than Start date");
        }

        // Check if the start date is equal to end date.
        // This is allowed only for Baseline reportingPeriods and not for other reporting periods.
        if (!rPeriod.getEpoch().getName().equals("Baseline")) {
            if (startDate.equals(endDate)) {
                errors.rejectValue(field.getPropertyName(), "REQUIRED",
                        "For Non-Baseline Evaluation Period Start date cannot be equal to End date");
            }

        }

        // Check if the start date - end date for the reporting Period overlaps with the
        // date range of an existing Reporting Period.
        for (AdverseEventReportingPeriod aerp : rPeriodList) {
            if (!aerp.getId().equals(rPeriod.getId())) {
                Date sDate = aerp.getStartDate();
                Date eDate = aerp.getEndDate();
                if (((sDate.getTime() - startDate.getTime() < 0) && startDate.getTime() - eDate.getTime() < 0) ||
                        ((sDate.getTime() - endDate.getTime() < 0) && (endDate.getTime() - eDate.getTime() < 0)) ||
                        ((startDate.getTime() - sDate.getTime() < 0) && (eDate.getTime() - endDate.getTime() < 0)) ||
                        (sDate.compareTo(startDate) == 0 && eDate.compareTo(endDate) == 0)) {
                    errors.rejectValue(field.getPropertyName(), "REQUIRED",
                            "Evaluation Period cannot overlap with an existing Evaluation Period.");
                    break;
                }
            }
        }

        // If the epoch of reportingPeriod is not - Baseline , then it cannot be earlier than a Baseline
        // reportingPeriod of
        for (AdverseEventReportingPeriod aerp : rPeriodList) {
            Date sDate = aerp.getStartDate();
            Date eDate = aerp.getEndDate();
            if (rPeriod.getEpoch().getName().equals("Baseline")) {
                if (!aerp.getEpoch().getName().equals("Baseline")) {
                    if (sDate.getTime() - startDate.getTime() < 0) {
                        errors.rejectValue(field.getPropertyName(), "REQUIRED",
                                "Baseline Evaluation Period cannot start after an existing Non-Baseline Evaluation Period.");
                        return;
                    }
                }
            } else {
                if (aerp.getEpoch().getName().equals("Baseline")) {
                    if (startDate.getTime() - sDate.getTime() < 0) {
                        errors.rejectValue(field.getPropertyName(), "REQUIRED",
                                "Non-Baseline Evaluation Period cannot start before an existing Baseline Evaluation Period.");
                        return;
                    }
                }
            }
        }

    }


    public Map<Object, Object> fetchTreatmentAssignmentOptions(final Object cmd) {
        ReportingPeriodCommand rpCommand = (ReportingPeriodCommand) cmd;
        return WebUtils.collectOptions(rpCommand.getStudy().getTreatmentAssignments(), "id", "code", "Please select");
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

}
