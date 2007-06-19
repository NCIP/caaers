package gov.nih.nci.cabig.caaers.web.ae;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

import java.util.Map;
import java.util.ListIterator;

import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.web.fields.DefaultTextField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.AutocompleterField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;

/**
 * @author Rhett Sutphin
 */
public class ConcomitantMedicationsTab extends AeTab {
    private RepeatingFieldGroupFactory fieldFactory;

    public ConcomitantMedicationsTab() {
        super("Concomitant medications", "Con. meds", "ae/conMed");
        fieldFactory = new RepeatingFieldGroupFactory("conmed", "aeReport.concomitantMedications");
        fieldFactory.setDisplayNameCreator(new RepeatingFieldGroupFactory.DisplayNameCreator() {
            public String createDisplayName(int index) {
                return "Medication " + (index + 1);
            }
        });
        fieldFactory.addField(new AutocompleterField("agent", "Known medication", false));
        fieldFactory.addField(new DefaultTextField("other", "Other", false));
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, InputFieldGroup> createFieldGroups(ExpeditedAdverseEventInputCommand command) {
        InputFieldGroupMap groups = new InputFieldGroupMap();
        groups.addRepeatingFieldGroupFactory(fieldFactory, command.getAeReport().getLabs().size());
        return groups;
    }

    @Override
    public boolean isAllowDirtyForward() {
        return false;
    }

    @Override
    protected void validate(
        ExpeditedAdverseEventInputCommand command, BeanWrapper commandBean,
        Map<String, InputFieldGroup> fieldGroups, Errors errors
    ) {
        for (ListIterator<ConcomitantMedication> it = command.getAeReport().getConcomitantMedications().listIterator(); it.hasNext();) {
            ConcomitantMedication conMed = it.next();
            validateConcomitantMedication(conMed, it.previousIndex(), errors);
        }
    }

    private void validateConcomitantMedication(ConcomitantMedication conMed, int index, Errors errors) {
        if (conMed.getAgent() == null && conMed.getOther() == null) {
            errors.rejectValue(
                String.format("aeReport.concomitantMedications[%d]", index),
                "REQUIRED",
                "Either a known medication or other is required"
            );
        }
    }
}
