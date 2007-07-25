package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedReportPerson;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
    @SuppressWarnings("unchecked")
    public Map<String, InputFieldGroup> createFieldGroups(ExpeditedAdverseEventInputCommand command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        map.addInputFieldGroup(createPersonGroup("reporter"));
        map.addInputFieldGroup(createPersonGroup("physician"));
        return map;
    }

    private InputFieldGroup createPersonGroup(String person) {
        InputFieldGroup group = new DefaultInputFieldGroup(person, StringUtils.capitalize(person) + " details");
        String base = "aeReport." + person  + '.';
        group.getFields().add(InputFieldFactory.createTextField(base + "firstName", "First name", true));
        group.getFields().add(InputFieldFactory.createTextField(base + "middleName", "Middle name", false));
        group.getFields().add(InputFieldFactory.createTextField(base + "lastName", "Last name", true));
        group.getFields().add(createContactField(base, ExpeditedReportPerson.EMAIL, "E-mail address", true));
        group.getFields().add(createContactField(base, ExpeditedReportPerson.PHONE));
        group.getFields().add(createContactField(base, ExpeditedReportPerson.FAX));
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
