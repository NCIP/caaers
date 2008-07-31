package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Physician;
import gov.nih.nci.cabig.caaers.domain.ReportPerson;
import gov.nih.nci.cabig.caaers.domain.Reporter;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.validators.FieldValidator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;

/**
 * @author Kulasekaran
 * @author Rhett Sutphin
 */
public class ReporterTab extends AeTab {
    private static final Log log = LogFactory.getLog(ReporterTab.class);

    private EvaluationService evaluationService;

    public ReporterTab() {
        super(ExpeditedReportSection.REPORTER_INFO_SECTION.getDisplayName(), "Reporter",
                        "ae/reporter");
    }

    @Override
    public ExpeditedReportSection section() {
        return ExpeditedReportSection.REPORTER_INFO_SECTION;
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator,
                    ExpeditedAdverseEventInputCommand command) {
        createPersonGroup(creator, "reporter");
        createPersonGroup(creator, "physician");
    }

    private void createPersonGroup(AeInputFieldCreator creator, String person) {
        String base = person + '.';
        InputField firstNameField = InputFieldFactory.createTextField(base + "firstName",
                        "First name", true);
        InputField middleNameField = InputFieldFactory.createTextField(base + "middleName",
                        "Middle name", false);
        InputField lastNameField = InputFieldFactory.createTextField(base + "lastName",
                        "Last name", true);
        InputField emailField = createContactField(base, ReportPerson.EMAIL, "E-mail address", FieldValidator.NOT_NULL_VALIDATOR);
        InputFieldAttributes.setSize(emailField, 50);

        
        InputField phoneField =createContactField(base, ReportPerson.PHONE, FieldValidator.PHONE_VALIDATOR);
        phoneField.getAttributes().put(InputField.EXTRA_VALUE_PARAMS, "phone-number");
        
        InputField faxField = createContactField(base, ReportPerson.FAX, FieldValidator.PHONE_VALIDATOR);
        faxField.getAttributes().put(InputField.EXTRA_VALUE_PARAMS, "phone-number");
        
        creator.createFieldGroup(person, StringUtils.capitalize(person) + " details",
                        firstNameField, middleNameField, lastNameField, emailField,
                        phoneField, faxField);
    }

    private InputField createContactField(String base, String contactType, FieldValidator... validators) {
        return createContactField(base, contactType, StringUtils.capitalize(contactType));
    }

    private InputField createContactField(String base, String contactType, String displayName,FieldValidator... validators) {
        return InputFieldFactory.createTextField(base + "contactMechanisms[" + contactType + ']',
                        displayName, validators);
    }

    @Override
    public void onDisplay(HttpServletRequest request, ExpeditedAdverseEventInputCommand command) {
        super.onDisplay(request, command);
        
        boolean severe = false;
        for (AdverseEvent event : command.getAeReport().getAdverseEvents()) {
            severe |= evaluationService.isSevere(command.getAssignment(), event);
        }
        request.setAttribute("oneOrMoreSevere", severe);
    }

    @Required
    public void setEvaluationService(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }
}
