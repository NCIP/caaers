package gov.nih.nci.cabig.caaers.web.participant;

//java imports
import gov.nih.nci.cabig.caaers.web.ListValues;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.util.Map;

import org.springframework.validation.Errors;

public class SelectStudyForParticipantTab extends Tab<NewParticipantCommand> {

    public SelectStudyForParticipantTab() {
        super("Choose Study", "Choose Study", "par/par_choose_study");
    }

    private ListValues listValues;

    @Override
    public Map<String, Object> referenceData() {
        Map<String, Object> refdata = super.referenceData();
        refdata.put("searchType", listValues.getStudySearchType());
        return refdata;
    }

    @Override
    public void validate(final NewParticipantCommand command, final Errors errors) {
        // boolean studiesArray = command.getStudies() == null || command.getStudies().size() == 0;
        // if (studiesArray) {
        // errors.rejectValue("studySiteArray", "REQUIRED", "Please Select a Study to Continue");
        // }
        boolean studySiteArray = command.getStudySiteArray() == null
                        || command.getStudySiteArray().length == 0;
        if (studySiteArray) {
            errors.rejectValue("studySiteArray", "REQUIRED", "Please Select a Study to Continue");
        }
    }

    public void setListValues(final ListValues listValues) {
        this.listValues = listValues;
    }

}
