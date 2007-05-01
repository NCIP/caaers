package gov.nih.nci.cabig.caaers.web.ae;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

import java.util.Map;
import java.util.ListIterator;

import gov.nih.nci.cabig.caaers.domain.AdverseEventPriorTherapy;
import gov.nih.nci.cabig.caaers.web.fields.DefaultTextField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultDateField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.AutocompleterField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;

/**
 * @author Rhett Sutphin
 */
public class PriorTherapyTab extends AeTab {
    private RepeatingFieldGroupFactory fieldFactory;

    public PriorTherapyTab() {
        super("Prior Therapies", "Prior Therapies", "ae/priorTherapies");
        fieldFactory = new RepeatingFieldGroupFactory("priorTherapy", "aeReport.adverseEventPriorTherapies");
        fieldFactory.setDisplayNameCreator(new RepeatingFieldGroupFactory.DisplayNameCreator() {
            public String createDisplayName(int index) {
                return "Medication " + (index + 1);
            }
        });
        fieldFactory.addField(new AutocompleterField("priorTherapy", "PriorTherapy", false));
        fieldFactory.addField(new DefaultTextField("other", "Other", false));
        fieldFactory.addField(new DefaultDateField("startDate", "startDate", false));
        fieldFactory.addField(new DefaultDateField("endDate", "endDate", false));
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, InputFieldGroup> createFieldGroups(AdverseEventInputCommand command) {
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
        AdverseEventInputCommand command, BeanWrapper commandBean,
        Map<String, InputFieldGroup> fieldGroups, Errors errors
    ) {
        for (ListIterator<AdverseEventPriorTherapy> it = command.getAeReport().getAdverseEventPriorTherapies().listIterator(); it.hasNext();) {
        	AdverseEventPriorTherapy aePriorTherapy = it.next();
            validatePriorTherapy(aePriorTherapy, it.previousIndex(), errors);
        }
    }
    
    private void validatePriorTherapy(AdverseEventPriorTherapy aePriorTherapy, int index, Errors errors) {
        if (aePriorTherapy.getPriorTherapy() == null && aePriorTherapy.getOther() == null) {
            errors.rejectValue(
                String.format("aeReport.adverseEventPriorTherapies[%d]", index),
                "REQUIRED",
                "Either Prior Therapy or Other is required"
            );
        }
    }
}
