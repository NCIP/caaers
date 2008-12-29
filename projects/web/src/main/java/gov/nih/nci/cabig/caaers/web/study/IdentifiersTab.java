package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 */
public class IdentifiersTab extends StudyTab {

    private RepeatingFieldGroupFactory rfgFactory;

    public IdentifiersTab() {
        super("Identifiers", "Identifiers", "study/study_identifiers");
    }

    @Override
    public Map<String, Object> referenceData(final HttpServletRequest request, final StudyCommand command) {
        Map<String, Object> refdata = super.referenceData(request, command);
        Map<String, List<Lov>> configMap = getConfigurationProperty().getMap();

        refdata.put("identifiersTypeRefData", configMap.get("identifiersType"));
        return refdata;
    }

    @Override
    public void postProcess(final HttpServletRequest request, final StudyCommand command, final Errors errors) {
        String action = request.getParameter("_action");
        String selected = request.getParameter("_selected");
        if ("removeIdentifier".equals(action)) {
            Study study = command.getStudy();
            Identifier identifier = study.getIdentifiersLazy().get(Integer.parseInt(selected));
            study.getIdentifiersLazy().remove(Integer.parseInt(selected));
            study.getIdentifiers().remove(identifier);
        }
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(final StudyCommand command) {
        if (rfgFactory == null) {
            rfgFactory = new RepeatingFieldGroupFactory("main", "study.identifiersLazy");
            InputField idField = InputFieldFactory.createTextField("value", "Identifier", true);
            InputFieldAttributes.setSize(idField, 20);
            rfgFactory.addField(idField);

            rfgFactory.addField(InputFieldFactory.createSelectField("type", "Identifier Type", true, collectOptionsFromConfig("identifiersType", "desc", "desc")));
            InputField sysNameField = InputFieldFactory.createTextField("systemName", "System Name", false);
            InputFieldAttributes.setSize(sysNameField, 50);
            rfgFactory.addField(sysNameField);
            InputField orgNameField = InputFieldFactory.createAutocompleterField("organization", "Organization", false);
            orgNameField.getAttributes().put(InputField.ENABLE_CLEAR, true);
            rfgFactory.addField(orgNameField);

            rfgFactory.addField(InputFieldFactory.createCheckboxField("primaryIndicator", "Primary Indicator"));

        }
        Study study = command.getStudy();
        InputFieldGroupMap map = new InputFieldGroupMap();
        map.addRepeatingFieldGroupFactory(rfgFactory, study.getIdentifiersLazy().size());

        return map;
    }

    @Override
    protected void validate(final StudyCommand command, final BeanWrapper commandBean, final Map<String, InputFieldGroup> fieldGroups, final Errors errors) {
        super.validate(command, commandBean, fieldGroups, errors);
        HashSet<String> set = new HashSet<String>();
        List<Identifier> identifiers = command.getStudy().getIdentifiersLazy();
        
        for (int i = 0; i < identifiers.size(); i++) {
            Identifier identifier = identifiers.get(i);
            if (!set.add(identifier.getType())) {
                errors.rejectValue("study.identifiersLazy[" + i + "].type", "REQUIRED", "Duplicate, already an identifier of this type is present");
            }

            if (identifier instanceof OrganizationAssignedIdentifier && ((OrganizationAssignedIdentifier) identifier).getOrganization() == null) {
                errors.rejectValue("study.identifiersLazy[" + i + "].organization", "REQUIRED", "Organization is required..!");

            } else if (identifier instanceof SystemAssignedIdentifier && (((SystemAssignedIdentifier) identifier).getSystemName() == null || ((SystemAssignedIdentifier) identifier).getSystemName().equals(""))) {
                errors.rejectValue("study.identifiersLazy[" + i + "].systemName", "REQUIRED","System Name is required..!");
            }
        }

    }

}
