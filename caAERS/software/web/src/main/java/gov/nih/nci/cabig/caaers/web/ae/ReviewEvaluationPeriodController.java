package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.SolicitedAdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.MultipleFieldGroupFactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.mvc.SimpleFormController;



public class ReviewEvaluationPeriodController extends SimpleFormController{

	private static final String MAIN_FIELD_GROUP = "main";
	private AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
	
	public ReviewEvaluationPeriodController(){
		setCommandClass(ReviewEvaluationPeriodCommand.class);
		setSessionForm(true);
		setFormView("ae/reviewEvaluationPeriodDetails");
	}
	
	@Override
	public boolean isFormSubmission(HttpServletRequest request){
		return false;
	}
	
	@Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
		ReviewEvaluationPeriodCommand command = new ReviewEvaluationPeriodCommand(adverseEventReportingPeriodDao);
		String reportingPeriodId = request.getParameter("adverseEventReportingPeriod");
		AdverseEventReportingPeriod reportingPeriod = adverseEventReportingPeriodDao.getById(Integer.parseInt(reportingPeriodId));
		command.setAdverseEventReportingPeriod(reportingPeriod);
		return command;
	}
	
	@SuppressWarnings("unchecked")
    @Override
    protected Map referenceData(final HttpServletRequest request, final Object obj, final Errors errors) throws Exception {
        Map<Object, Object> refDataMap = new LinkedHashMap<Object, Object>();
        //CaptureAdverseEventInputCommand command = (CaptureAdverseEventInputCommand) obj;
        ReviewEvaluationPeriodCommand command = (ReviewEvaluationPeriodCommand) obj;
        refDataMap.put("fieldGroups", createFieldGroups(command.getAdverseEventReportingPeriod()));
        return refDataMap;
    }
	
	private Map<String, InputFieldGroup> createFieldGroups(AdverseEventReportingPeriod reportingPeriod){
        InputFieldGroupMap map = new InputFieldGroupMap();
        MultipleFieldGroupFactory mainFieldFactory;
        List<SolicitedAdverseEvent> saeList;

        // Creating the field groups for the first section of the page
        // which collects the general information from the user (eg., TAC, TAC Description, Start date of first course etc.
        InputFieldGroup reportingPeriodFieldGroup = new DefaultInputFieldGroup("reportingPeriodFG");
        List<InputField> fields = reportingPeriodFieldGroup.getFields();
        //InputField reportingPeriodsField = InputFieldFactory.createSelectField("adverseEventReportingPeriod", "Evaluation period", true, fetchReportingPeriodsOptions(cmd));
        InputField reportingPeriodsField = InputFieldFactory.createLabelField("adverseEventReportingPeriod.name", "Evaluation Period", false);
        fields.add(reportingPeriodsField);
        map.addInputFieldGroup(reportingPeriodFieldGroup);

        //create the fields, consisting of reporting period details.
        if (reportingPeriod != null) {

            InputFieldGroup treatmentAssignmentFieldGroup = new DefaultInputFieldGroup("treatmentAssignmentFG");
            InputFieldGroup reportingPeriodDetailsFieldGroup = new DefaultInputFieldGroup("reportingPeriodDetailsFG");

            //TAC fields groups
            InputField treatmentAssignmentField = InputFieldFactory.createLabelField("adverseEventReportingPeriod.treatmentAssignment.code", "Treatment assignment");
            InputField treatmentAssignmentDescField = InputFieldFactory.createLabelField("adverseEventReportingPeriod.treatmentAssignment.description", "Treatment description");

            //startDateOfFirstCourse - TextField, if it is empty in assignment
            InputField firstCourseDateField = InputFieldFactory.createLabelField("adverseEventReportingPeriod.assignment.startDateOfFirstCourse", "Start date of first course");

            treatmentAssignmentFieldGroup.getFields().add(treatmentAssignmentField);
            treatmentAssignmentFieldGroup.getFields().add(treatmentAssignmentDescField);
            treatmentAssignmentFieldGroup.getFields().add(firstCourseDateField);

            // add reportingPeriod details group
            reportingPeriodDetailsFieldGroup.getFields().add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.epoch.name", "Type"));
            reportingPeriodDetailsFieldGroup.getFields().add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.cycleNumber", "Cycle number"));
            reportingPeriodDetailsFieldGroup.getFields().add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.description", "Description"));

            // add the reportingPeriodFieldGroup to the map.
            map.addInputFieldGroup(treatmentAssignmentFieldGroup);
            map.addInputFieldGroup(reportingPeriodDetailsFieldGroup);


              // AdversEvent related field groups,
              // the fields are different for Meddra study, Ctc study and Observed AEs

            Study study = reportingPeriod.getStudy();

            mainFieldFactory = new MultipleFieldGroupFactory(MAIN_FIELD_GROUP, "adverseEvents");
            boolean isMeddraStudy = study.getAeTerminology().getTerm() == Term.MEDDRA;

            int size = reportingPeriod.getAdverseEvents().size();
            for (int i = 0; i < size; i++) {
                AdverseEvent ae = reportingPeriod.getAdverseEvents().get(i);
                if (ae == null) continue;

                if (ae.getExpected() == null) if (study.isExpectedAdverseEventTerm(ae.getAdverseEventCtcTerm().getCtcTerm())) ae.setExpected(true);
                mainFieldFactory.addField(InputFieldFactory.createLabelField("adverseEventTerm.universalTerm", "Term")); //Term

                if (!ae.getSolicited()) {
                    if (!isMeddraStudy && ae.getAdverseEventTerm().isOtherRequired()) {//only if other is requrired
                    	InputField otherMeddraField = InputFieldFactory.createLabelField("lowLevelTerm.meddraTerm", "Other(MedDRA)");
                    	InputFieldAttributes.setSize(otherMeddraField, 25);
                    	mainFieldFactory.addField(otherMeddraField);
                    }
                }else{
                	if(!isMeddraStudy && ae.getAdverseEventTerm().isOtherRequired()){
                		mainFieldFactory.addField(InputFieldFactory.createLabelField("lowLevelTerm.meddraTerm", "Other (MedDRA)"));
                	}
                }

                //grade
                mainFieldFactory.addField(InputFieldFactory.createLabelField("grade", "Grade"));

                mainFieldFactory.addField(InputFieldFactory.createLabelField("attributionSummary", "Attribution to study"));
                mainFieldFactory.addField(InputFieldFactory.createLabelField("hospitalization", "Hospitalization"));
                mainFieldFactory.addField(InputFieldFactory.createLabelField("expected", "Expected"));
                mainFieldFactory.addField(InputFieldFactory.createLabelField("serious", "Serious"));
                
                InputFieldGroup fieldGroup = mainFieldFactory.createGroup(i);
                mainFieldFactory.addFieldGroup(fieldGroup);
                mainFieldFactory.clearFields();

            }
            map.addMultipleFieldGroupFactory(mainFieldFactory);  
        }     
		return map;
	}
	
	public AdverseEventReportingPeriodDao getAdverseEventReportingPeriodDao(){
		return adverseEventReportingPeriodDao;
	}
	
	public void setAdverseEventReportingPeriodDao(AdverseEventReportingPeriodDao adverseEventReportingPeriodDao){
		this.adverseEventReportingPeriodDao = adverseEventReportingPeriodDao;
	}
}