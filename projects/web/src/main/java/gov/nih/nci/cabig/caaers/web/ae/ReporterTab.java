package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ReportPerson;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kulasekaran
 * @author Rhett Sutphin
 */
public class ReporterTab extends AeTab {
    private EvaluationService evaluationService;

    public ReporterTab() {
        super("Reporter info", "Reporter", "ae/reporter");
    }

    @Override
    public ExpeditedReportSection section() {
    	return ExpeditedReportSection.REPORTER_INFO_SECTION;
    }

    @Override
    @SuppressWarnings("unchecked")
    public InputFieldGroupMap createFieldGroups(ExpeditedAdverseEventInputCommand command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        map.addInputFieldGroup(createPersonGroup("reporter"));
        map.addInputFieldGroup(createPersonGroup("physician"));
        return map;
    }

    private InputFieldGroup createPersonGroup(String person) {
        InputFieldGroup group = new DefaultInputFieldGroup(person, StringUtils.capitalize(person) + " details");
        String base = "aeReport." + person  + '.';
        InputField firstNameField = InputFieldFactory.createTextField(base + "firstName", "First name", true);
        //InputFieldAttributes.setSize(firstNameField, 50);
        group.getFields().add(firstNameField);
        InputField middleNameField = InputFieldFactory.createTextField(base + "middleName", "Middle name", false);
        //InputFieldAttributes.setSize(middleNameField, 50);
        group.getFields().add(middleNameField);
        InputField lastNameField = InputFieldFactory.createTextField(base + "lastName", "Last name", true);
        //InputFieldAttributes.setSize(lastNameField, 50);
        group.getFields().add(lastNameField);
        InputField emailField = createContactField(base, ReportPerson.EMAIL, "E-mail address", true);
        InputFieldAttributes.setSize(emailField, 50);
        group.getFields().add(emailField);
        group.getFields().add(createContactField(base, ReportPerson.PHONE));
        group.getFields().add(createContactField(base, ReportPerson.FAX));
        return group;
    }

    private InputField createContactField(String base, String contactType) {
        return createContactField(base, contactType, StringUtils.capitalize(contactType), false);
    }

    private InputField createContactField(
        String base, String contactType, String displayName, boolean required
    ) {
        return InputFieldFactory.createTextField(
            base + "contactMechanisms[" + contactType + ']', displayName, required);
    }

    @Override
    public void onDisplay(HttpServletRequest request, ExpeditedAdverseEventInputCommand command) {
        super.onDisplay(request,command);
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
