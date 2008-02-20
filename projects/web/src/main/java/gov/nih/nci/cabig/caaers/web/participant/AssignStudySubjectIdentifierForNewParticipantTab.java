package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.util.Map;

/**
 * @author Biju Joseph
 */
public class AssignStudySubjectIdentifierForNewParticipantTab extends Tab<NewParticipantCommand> {

    private static final Log log = LogFactory.getLog(AssignStudySubjectIdentifierForNewParticipantTab.class);
    private final String STUDY_SUBJECT_IDENTIFIER_FIELD_GROUP = "studySubjectIdentifier";
    private final String STUDY_SUBJECT_IDENTIFIER_FIELD = "studySubjectIdentifier";

    public AssignStudySubjectIdentifierForNewParticipantTab() {
        super("Choose Study Subject Identifier ", "Study Subject Identifier", "par/par_study_subject_identifier");
    }


    @Override
    public Map<String, Object> referenceData(final NewParticipantCommand command) {
        Map<String, Object> refdata = referenceData();
        Map<String, InputFieldGroup> groupMap = createFieldGroups(command);
        refdata.put("fieldGroups", groupMap);

        return refdata;
    }

    private Map<String, InputFieldGroup> createFieldGroups(final NewParticipantCommand command) {

        InputFieldGroup studySubjectIdentifierFieldGroup = new DefaultInputFieldGroup(STUDY_SUBJECT_IDENTIFIER_FIELD_GROUP);
        studySubjectIdentifierFieldGroup.getFields().add(
                InputFieldFactory.createTextField(STUDY_SUBJECT_IDENTIFIER_FIELD, "Study subject identifier", true));

        InputFieldGroupMap map = new InputFieldGroupMap();
        map.addInputFieldGroup(studySubjectIdentifierFieldGroup);
        return map;
    }

    @Override
    public void validate(NewParticipantCommand command, Errors errors) {
        super.validate(command, errors);
        if (command.getStudySubjectIdentifier() == null
                || command.getStudySubjectIdentifier().trim().equalsIgnoreCase(""))
            errors.rejectValue(STUDY_SUBJECT_IDENTIFIER_FIELD, "REQUIRED", "Missing study subject identifier");
    }


}
