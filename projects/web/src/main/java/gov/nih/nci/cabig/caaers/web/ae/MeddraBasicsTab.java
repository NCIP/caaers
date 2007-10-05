package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;

/**
 * @author Krikor Krumlian
 */
public class MeddraBasicsTab extends BasicsTab {
    // Why?
    private static final String CTC_TERM_FIELD_GROUP = "ctcTerm";

    public MeddraBasicsTab() {
        super("Enter basic AE information", "AEs", "ae/enterBasicMeddra");
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command) {
        super.createFieldGroups(creator, command);
        creator.createRepeatingFieldGroup(CTC_TERM_FIELD_GROUP, "adverseEvents",
            InputFieldFactory.createAutocompleterField(
                "adverseEventMeddraLowLevelTerm.lowLevelTerm", "MedDRA code", true)
        );
    }
}
