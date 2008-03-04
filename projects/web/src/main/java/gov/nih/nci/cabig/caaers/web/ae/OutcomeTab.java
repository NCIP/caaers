package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Outcome;
import gov.nih.nci.cabig.caaers.domain.OutcomeType;

import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

import java.util.Iterator;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

/**
 * @author Krikor Krumlian
 */
public class OutcomeTab extends AeTab {
    private static final Log log = LogFactory.getLog(OutcomeTab.class);

    public OutcomeTab() {
        super("Outcomes Attributed to Adverse Event", ExpeditedReportSection.OUTCOME_SECTION
                        .getDisplayName(), "ae/outcome");
    }

    @Override
    public ExpeditedReportSection section() {
        return ExpeditedReportSection.OUTCOME_SECTION;
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator,
                    ExpeditedAdverseEventInputCommand command) {
        InputFieldGroup optional = new DefaultInputFieldGroup("optionalReports");
        for (String code : command.getOutcomes().keySet()) {
            OutcomeType outcomeType = OutcomeType.getByCode(Integer.parseInt(code));
            optional.getFields().add(
                            InputFieldFactory.createCheckboxField("outcomes[" + code + ']',
                                            outcomeType.getDisplayName()));
            if (outcomeType == OutcomeType.DEATH) {
                optional.getFields().add(
                                InputFieldFactory.createDateField("outcomeDate", "date", false));
            }
            if (outcomeType == OutcomeType.OTHER_SERIOUS) {
                optional.getFields().add(
                                InputFieldFactory.createTextField("otherOutcome", "", false));
            }
        }

        creator.addUnprocessedFieldGroup(optional);
    }

    @Override
    public void onDisplay(HttpServletRequest request, ExpeditedAdverseEventInputCommand command) {
        command.updateOutcomes();

        for (String outcome : command.getOutcomes().keySet()) {
            System.out.println(outcome + "  :   " + command.getOutcomes().get(outcome));
        }
    }

    @Override
    /**
     * 
     */
    public void postProcess(HttpServletRequest request, ExpeditedAdverseEventInputCommand command,
                    Errors errors) {

        // override disabled checkboxes on the UI - better way to do this is using images. I prefer
        // not
        // to use JS manipulation as that would allow hacking if someone wants.
        Grade grade = command.getAeReport().getAdverseEvents().size() > 0 ? command.getAeReport()
                        .getAdverseEvents().get(0).getGrade() : null;
        Hospitalization hospitalization = command.getAeReport().getAdverseEvents().size() > 0 ? command
                        .getAeReport().getAdverseEvents().get(0).getHospitalization()
                        : null;

        boolean isHospitalization = (hospitalization != null && hospitalization.getName().contains(
                        OutcomeType.HOSPITALIZATION.getName())) ? true : false;
        boolean isGrade4 = (grade != null && grade.getName().equals(
                        OutcomeType.LIFE_THREATENING.getName())) ? true : false;
        boolean isGrade5 = (grade != null && grade.getName().equals(OutcomeType.DEATH.getName())) ? true
                        : false;

        command.getOutcomes().put("1", isGrade5);
        command.getOutcomes().put("2", isGrade4);
        command.getOutcomes().put("3", isHospitalization);

        ArrayList<String> codes = new ArrayList<String>();
        // remove
        for (Iterator<Outcome> outcomes = command.getAeReport().getOutcomes().iterator(); outcomes
                        .hasNext();) {
            Outcome outcome = outcomes.next();
            String code = outcome.getOutcomeType().getCode().toString();
            codes.add(code);
            boolean x = command.getOutcomes().get(code);
            if (x == false) {
                outcomes.remove();
            }
        }
        // add
        for (String code : command.getOutcomes().keySet()) {
            if (!codes.contains(code) && command.getOutcomes().get(code) == true) {
                System.out.println("The code : " + code);
                Outcome outcome = new Outcome();
                outcome.setOutcomeType(OutcomeType.getByCode(Integer.parseInt(code)));
                command.getAeReport().addOutcomes(outcome);
            }
        }

        // Add the date & other if exist
        for (Outcome outcome : command.getAeReport().getOutcomes()) {
            if (outcome.getOutcomeType() == OutcomeType.DEATH) {
                outcome.setDate(command.getOutcomeDate());
            }
            if (outcome.getOutcomeType() == OutcomeType.OTHER_SERIOUS) {
                outcome.setOther(command.getOtherOutcome());
            }

        }

    }
}
