package gov.nih.nci.cabig.caaers.web.ae;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.EpochDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.TreatmentAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Epoch;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.digester.SetPropertiesRule;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;


public class CreateReportingPeriodController extends SimpleFormController {
	private static final String REPORTINGPERIOD_FIELD_GROUP = "ReportingPeriod";
	private AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
    private StudyParticipantAssignmentDao assignmentDao;
    private ParticipantDao participantDao;
    private StudyDao studyDao;
    private EpochDao epochDao;
    private InputFieldGroup reportingPeriodFieldGroup;
    private InputFieldGroupMap fieldMap;
    
	public CreateReportingPeriodController() {
        setFormView("ae/create_reporting_period");
        setBindOnNewForm(true);
    }
	
	@Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String idString = request.getParameter("id");
		String studyString = request.getParameter("studyId");
		String participantString = request.getParameter("participantId");
		if(idString != null)
			return new ReportingPeriodCommand(adverseEventReportingPeriodDao, assignmentDao, studyDao, participantDao, idString);
		else if(studyString != null && participantString != null)
			return new ReportingPeriodCommand(adverseEventReportingPeriodDao, assignmentDao, studyDao, participantDao, studyString, participantString);
		else
			return new ReportingPeriodCommand(adverseEventReportingPeriodDao, assignmentDao, studyDao, participantDao); 
			
    }
	
	@Override
    protected void initBinder(final HttpServletRequest request,
                    final ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(false));
        ControllerTools.registerDomainObjectEditor(binder, epochDao);
    }
	
	@SuppressWarnings("unchecked")
    @Override
    protected Map referenceData(final HttpServletRequest request, final Object command,
                    final Errors errors) throws Exception {
        Map<Object, Object> refDataMap = new LinkedHashMap<Object, Object>();
        ReportingPeriodCommand rpCommand = (ReportingPeriodCommand) command;
        createFieldGroup(command);
        refDataMap.put("fieldGroups", fieldMap);
        //refDataMap.put("treatmentAssignments", rpCommand.getAssignment().getStudySite().getStudy().getTreatmentAssignments());
        return refDataMap;
    }
	
	protected void createFieldGroup(Object command){
		fieldMap = new InputFieldGroupMap();
		reportingPeriodFieldGroup = new DefaultInputFieldGroup(REPORTINGPERIOD_FIELD_GROUP);
		
		reportingPeriodFieldGroup.getFields().add(
                        InputFieldFactory.createDateField(
                                        "reportingPeriod.startDate", "From", true));
		reportingPeriodFieldGroup.getFields().add(
						InputFieldFactory.createDateField(
										"reportingPeriod.endDate", "To", true));
		reportingPeriodFieldGroup.getFields().add(InputFieldFactory.createSelectField("reportingPeriod.epoch", "Epoch", true,
                createEpochOptions(command)));
		reportingPeriodFieldGroup.getFields().add(
						InputFieldFactory.createTextField(
								"reportingPeriod.description", "Description", false));
		reportingPeriodFieldGroup.getFields().add(
						InputFieldFactory.createTextField("reportingPeriod.cycleNumber", "Cycle number", false));
		
		fieldMap.addInputFieldGroup(reportingPeriodFieldGroup);
	}
	
	protected Map<Object, Object> createEpochOptions(final Object command){
		Map<Object,Object> epochMap = new LinkedHashMap<Object, Object>();
		epochMap.put("", "Please select");
		ReportingPeriodCommand rpCommand = (ReportingPeriodCommand) command;
		Study study = rpCommand.getAssignment().getStudySite().getStudy();
		List<Epoch> epochList = study.getEpochs();
		for(Epoch epoch: epochList){
			epochMap.put(epoch.getId(), epoch.getName());
		}
		return epochMap;
	}
	
	/**
     * Validate the form,if no errors found, save the InvestigationalNewDrug object. Then return to
     * the success view.
     */
    @SuppressWarnings("unchecked")
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
                    Object cmd, BindException errors) throws Exception {
    	ReportingPeriodCommand command = (ReportingPeriodCommand)cmd;
        AdverseEventReportingPeriod reportingPeriod = command.getReportingPeriod();
        reportingPeriod.setAssignment(command.getAssignment());
        adverseEventReportingPeriodDao.save(reportingPeriod);
        Map map = new LinkedHashMap();
        map.putAll(errors.getModel());
        ModelAndView modelAndView = new ModelAndView("ae/reporting_period_confirm", map);
        
        return modelAndView;
    }
    
    public AdverseEventReportingPeriodDao getAdverseEventReportingPeriodDao(){
    	return adverseEventReportingPeriodDao;
    }
    
    public void setAdverseEventReportingPeriodDao(AdverseEventReportingPeriodDao adverseEventReportingPeriodDao){
    	this.adverseEventReportingPeriodDao = adverseEventReportingPeriodDao;
    }
    
    public StudyParticipantAssignmentDao getAssignmentDao(){
    	return assignmentDao;
    }
    
    public void setAssignmentDao(StudyParticipantAssignmentDao assignmentDao){
    	this.assignmentDao = assignmentDao;
    }
    
    public StudyDao getStudyDao(){
    	return studyDao;
    }
    
    public void setStudyDao(StudyDao studyDao){
    	this.studyDao = studyDao;
    }
    
    public void setEpochDao(EpochDao epochDao) {
		this.epochDao = epochDao;
	}
    
    public ParticipantDao getParticipantDao() {
		return participantDao;
	}
    
    public void setParticipantDao(ParticipantDao participantDao) {
		this.participantDao = participantDao;
	}
    
}