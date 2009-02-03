package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepositoryImpl;
import gov.nih.nci.cabig.caaers.web.fields.*;
import org.drools.util.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

/**
 * @author Biju Joseph
 */
public class AdverseEventCaptureTab extends AdverseEventTab {

    private static final String MAIN_FIELD_GROUP = "main";
    
    public AdverseEventCaptureTab() {
        super("Enter Adverse Events", "Adverse Events", "ae/captureAdverseEvents");
    }


    /**
     * This method will create the fields to be displayed on the screen.
     * Notes<br>
     * 1. For solicited adverse events, the "Notes/Verbatim", "Other Meddra" will added to the fields.
     * 2. If Study is MedDRA, the "Other MedDRA", will not be added in the fields.
     * 3. We should run the adverse events against the index fixed list, since that list will have null items in it, we should skip if 'AdverseEvent' is null.
     */

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(CaptureAdverseEventInputCommand cmd) {

        InputFieldGroupMap map = new InputFieldGroupMap();
        MultipleFieldGroupFactory mainFieldFactory;
        List<SolicitedAdverseEvent> saeList;

        // Creating the field groups for the first section of the page
        // which collects the general information from the user (eg., TAC, TAC Description, Start date of first course etc.
        InputFieldGroup reportingPeriodFieldGroup = new DefaultInputFieldGroup("reportingPeriodFG");
        List<InputField> fields = reportingPeriodFieldGroup.getFields();
        InputField reportingPeriodsField = InputFieldFactory.createSelectField("adverseEventReportingPeriod", "Evaluation period", true, fetchReportingPeriodsOptions(cmd));
        fields.add(reportingPeriodsField);
        map.addInputFieldGroup(reportingPeriodFieldGroup);

        //create the fields, consisting of reporting period details.
        if (cmd.getAdverseEventReportingPeriod() != null) {

            InputFieldGroup treatmentAssignmentFieldGroup = new DefaultInputFieldGroup("treatmentAssignmentFG");
            InputFieldGroup reportingPeriodDetailsFieldGroup = new DefaultInputFieldGroup("reportingPeriodDetailsFG");

            //TAC fields groups
            InputField treatmentAssignmentField = InputFieldFactory.createLabelField("adverseEventReportingPeriod.treatmentAssignment.code", "Treatment assignment");
            InputField treatmentAssignmentDescField = InputFieldFactory.createLabelField("adverseEventReportingPeriod.treatmentAssignment.description", "Treatment description");

            //startDateOfFirstCourse - TextField, if it is empty in assignment
            InputField firstCourseDateField = null;
            if (cmd.getAdverseEventReportingPeriod().getAssignment().getStartDateOfFirstCourse() == null) {
                firstCourseDateField = InputFieldFactory.createPastDateField("adverseEventReportingPeriod.assignment.startDateOfFirstCourse", "Start date of first course", false);
            } else {
                firstCourseDateField = InputFieldFactory.createLabelField("adverseEventReportingPeriod.assignment.startDateOfFirstCourse", "Start date of first course");
            }

            treatmentAssignmentFieldGroup.getFields().add(treatmentAssignmentField);
            treatmentAssignmentFieldGroup.getFields().add(treatmentAssignmentDescField);
            treatmentAssignmentFieldGroup.getFields().add(firstCourseDateField);

            // add reportingPeriod details group
            reportingPeriodDetailsFieldGroup.getFields().add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.startDate", "Start date"));
            reportingPeriodDetailsFieldGroup.getFields().add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.endDate", "End date"));
            reportingPeriodDetailsFieldGroup.getFields().add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.epoch.name", "Type"));
            reportingPeriodDetailsFieldGroup.getFields().add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.cycleNumber", "Cycle number"));

            // add the reportingPeriodFieldGroup to the map.
            map.addInputFieldGroup(treatmentAssignmentFieldGroup);
            map.addInputFieldGroup(reportingPeriodDetailsFieldGroup);


            /*
                * AdversEvent related field groups,
                *  the fields are different for Meddra study, Ctc study and Observed AEs
                */

            Study study = cmd.getAdverseEventReportingPeriod().getStudy();

            mainFieldFactory = new MultipleFieldGroupFactory(MAIN_FIELD_GROUP, "adverseEvents");
            boolean isMeddraStudy = study.getAeTerminology().getTerm() == Term.MEDDRA;

            int size = cmd.getAdverseEvents().size();
            for (int i = 0; i < size; i++) {
                AdverseEvent ae = cmd.getAdverseEvents().get(i);
                if (ae == null) continue;

                if (ae.getExpected() == null) if (study.hasCTCTerm(ae.getAdverseEventCtcTerm().getCtcTerm())) ae.setExpected(true);
                mainFieldFactory.addField(InputFieldFactory.createLabelField("adverseEventTerm.universalTerm", "Term", true)); //Term

                if (!ae.getSolicited()) {
                    if (!isMeddraStudy && ae.getAdverseEventTerm().isOtherRequired()) {//only if other is requrired
                    	InputField otherMeddraField = InputFieldFactory.createAutocompleterField("lowLevelTerm", "Other(MedDRA)", false);
                    	InputFieldAttributes.setSize(otherMeddraField, 25);
                    	mainFieldFactory.addField(otherMeddraField);
                        //mainFieldFactory.addField(InputFieldFactory.createAutocompleterField("lowLevelTerm", "Other(MedDRA)", false));
                    }
                }else{
                	if(!isMeddraStudy && ae.getAdverseEventTerm().isOtherRequired()){
                		mainFieldFactory.addField(InputFieldFactory.createLabelField("lowLevelTerm.meddraTerm", "Other (MedDRA)", false));
                	}
                }
                InputField notesField = InputFieldFactory.createTextField("detailsForOther", "Verbatim");
                InputFieldAttributes.setSize(notesField, 25);
                mainFieldFactory.addField(notesField); //Notes

                //grade
                if (isMeddraStudy) {
                    mainFieldFactory.addField(InputFieldFactory.createSelectField("grade", "Grade", false, createGradeOptions(ae, "Meddra")));
                } else {
                    mainFieldFactory.addField(InputFieldFactory.createSelectField("grade", "Grade", false, createGradeOptions(ae, "Ctc")));
                }

                mainFieldFactory.addField(InputFieldFactory.createSelectField("attributionSummary", "Attribution to study", false, createAttributionOptions()));
                mainFieldFactory.addField(InputFieldFactory.createSelectField("hospitalization", "Hospitalization", false, createHospitalizationOptions()));
                mainFieldFactory.addField(InputFieldFactory.createSelectField("expected", "Expected", false, createExpectedOptions()));
                mainFieldFactory.addField(InputFieldFactory.createSelectField("serious", "Serious", false, createSeriousOptions()));
                InputFieldGroup fieldGroup = mainFieldFactory.createGroup(i);
                mainFieldFactory.addFieldGroup(fieldGroup);
                mainFieldFactory.clearFields();

            }
            map.addMultipleFieldGroupFactory(mainFieldFactory);
        }

        return map;
    }

    @Override
    public void beforeBind(HttpServletRequest request, CaptureAdverseEventInputCommand command) {
        super.beforeBind(request, command);
    }


    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, CaptureAdverseEventInputCommand command) {
        // Setup the categories list for aeTermQuery tag.
        boolean isCTCStudy = command.getStudy().getAeTerminology().getTerm() == Term.CTC;

        // initialize lazy
        if (isCTCStudy) {
            command.getCtcCategories();
            if (command.getAdverseEvents() != null)
                for (AdverseEvent ae: command.getAdverseEvents()) {
                	if(ae == null) continue;
                    ae.getAdverseEventTerm().isOtherRequired();
                    ae.getAdverseEventCtcTerm().getCtcTerm().isOtherRequired();
                    ae.getAdverseEventCtcTerm().getCtcTerm().getContextualGrades();
                }
        }

        //initalize the seriousness outcome indicators
        command.initializeOutcome();
        
        return super.referenceData(request, command);

    }

    @Override
    public void postProcess(HttpServletRequest request, CaptureAdverseEventInputCommand command, Errors errors) {
        if (findInRequest(request, CaptureAdverseEventController.AJAX_SUBVIEW_PARAMETER) != null)
            return; //ignore if this is an ajax request
        //sync the seriousness outcomes
        command.synchronizeOutcome();
        
        command.initialize();
        // Amend the reports if AEs associated to it are modified and the user is OK with the amendment.
        String action = (String)findInRequest(request, "_action");
        if(action.equals("amendmentRequired")){
        	String reportIds = (String) findInRequest(request, "_amendReportIds");
        	StringTokenizer st = new StringTokenizer(reportIds, ",");
        	HashMap<Integer, Boolean> reportIdMap = new HashMap<Integer, Boolean>();
        	while(st.hasMoreTokens()){
        		String repId = st.nextToken();
        		if(!reportIdMap.containsKey(Integer.decode(repId)));
        			reportIdMap.put(Integer.parseInt(repId), Boolean.TRUE);
        	}
        	
        	for(ExpeditedAdverseEventReport aeReport: command.getAdverseEventReportingPeriod().getAeReports()){
        		if(reportIdMap.containsKey(aeReport.getId())){
        			Boolean useDefaultVersion = false;
        	    	for(Report report: aeReport.getReports()){
        	    		if(report.getReportDefinition().getAmendable() && report.getIsLatestVersion()){
        	    			reportRepository.amendReport(report, useDefaultVersion);
        	    			// Set useDefaultVersion to true so that the reportVersionId is retained for all the reports 
        	    			// and just incremented for the 1st one in the list.
        	    			useDefaultVersion = true;
        	    		}
        	    	}
        		}
        	}
        }
    }

    @Override
    public void onBind(HttpServletRequest request, CaptureAdverseEventInputCommand command, Errors errors) {
        String rpId = request.getParameter("adverseEventReportingPeriod");
        if (StringUtils.isEmpty(rpId)) {
            command.setAdverseEventReportingPeriod(null);
        }
        Set<String> paramNames = request.getParameterMap().keySet();
        boolean fromListPage = false;
        fromListPage = paramNames.contains("displayReportingPeriod");
        if (fromListPage)
            command.refreshAssignment(Integer.parseInt(rpId));
    }

    public AdverseEvent checkAEsUniqueness(CaptureAdverseEventInputCommand command) {
        List AEs = null;
        AEs = command.getAdverseEventReportingPeriod().getAdverseEvents();
        
        if (AEs == null || AEs.size() == 0) return null;

        Iterator it = AEs.iterator();
        List aes = new ArrayList();
        while (it.hasNext()) {
            AdverseEvent ae = (AdverseEvent)it.next();
            StringBuffer key = new StringBuffer(ae.getAdverseEventTerm().getTerm().getId().toString());
            if (ae.getAdverseEventTerm().isOtherRequired()) {
                if (ae.getLowLevelTerm() == null) continue;
                key.append(ae.getLowLevelTerm().getId().toString());
            }
            if (aes.contains(key.toString())) return ae;
            aes.add(key.toString());
        }

        return null;
    }
    
    @Override
    protected void validate(CaptureAdverseEventInputCommand command, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {

        // START -> AE VALIDATION //
        boolean isMeddraStudy = command.getStudy().getAeTerminology().getTerm() == Term.MEDDRA;
        AdverseEvent adverseEvent = checkAEsUniqueness(command);
        if (adverseEvent != null) {
            String name = null;
            name = adverseEvent.getAdverseEventTerm().getFullName();
            if (adverseEvent.getAdverseEventTerm().isOtherRequired()) name = name + ", " + adverseEvent.getLowLevelTerm().getMeddraTerm();
            errors.reject("DUPLICATE_EXPECTED_AE", new Object[]{name}, "ERR.");
        }
        // STOP -> AE VALIDATION //

        // If grade is greater than 2 then hospitalization cannot be null.
    	if(!command.getAdverseEventReportingPeriod().isBaselineReportingType()){
    		for (AdverseEvent ae : command.getAdverseEventReportingPeriod().getAdverseEvents()) {
    			if (!ae.getSolicited() && ae.getGrade() != null) {
    				if (ae.getGrade().getCode() > 2 && ae.getHospitalization() == null)
    					errors.reject("HOSPITALIZATION_NEEDED", "Hospitalization must be entered if grade is greater than 2");
    			}
    		}
    	}

        // If grade = "Please Select" then other attributes cannot be modified. They should be "Please Select"
/*        for (AdverseEvent ae : command.getAdverseEventReportingPeriod().getAdverseEvents()) {
            if (ae.getSolicited()) {
                if (ae.getGrade() == null || ae.getGrade().equals(Grade.NOT_EVALUATED)) {
                    if (ae.getAttributionSummary() != null || ae.getHospitalization() != null || ae.getExpected() != null) {
                        if (ae.getGrade() == null)
                            errors.reject("GRADE_NEEDED", "Attribution, Hospitalization or Expectedness cannot be selected when Grade is not selected.");
                        else
                            errors.reject("GRADE_NEEDED", "Attribution, Hospitalization or Expectedness cannot be selected when Grade is not evaluated.");
                    }


                }
            }
        }*/
    }
    
}