package gov.nih.nci.cabig.caaers.web.participant;

//java imports
import gov.nih.nci.cabig.caaers.web.ListValues;
import gov.nih.nci.cabig.caaers.web.fields.*;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.util.Map;
import java.util.HashMap;

import org.springframework.validation.Errors;
import org.springframework.beans.BeanWrapper;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ion C. Olaru
 */

public class SelectStudyForParticipantTab extends TabWithFields<ParticipantInputCommand> {

    private final String STUDY_SUBJECT_IDENTIFIER_FIELD_GROUP = "studySubjectIdentifier";
    private final String STUDY_SUBJECT_IDENTIFIER_FIELD = "studySubjectIdentifier";

    public SelectStudyForParticipantTab() {
        super("Choose Study", "Choose Study 2", "par/par_choose_study");
    }

    private ListValues listValues;

    @Override
    public Map<String, Object> referenceData(ParticipantInputCommand command) {
        Map<String, Object> refdata = super.referenceData(command);
        refdata.put("searchType", listValues.getStudySearchType());
        return refdata;
    }

    @Override
    protected void validate(ParticipantInputCommand command, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {
        super.validate(command, commandBean, fieldGroups, errors);
        boolean studySiteArray = command.getStudySiteArray() == null || command.getStudySiteArray().length == 0;
        if (studySiteArray) {
            errors.rejectValue("studySiteArray", "REQUIRED", "Please Select a Study to Continue");
        }
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(ParticipantInputCommand command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        InputFieldGroup studySubjectIdentifierFieldGroup = new DefaultInputFieldGroup(STUDY_SUBJECT_IDENTIFIER_FIELD_GROUP);
        studySubjectIdentifierFieldGroup.getFields().add(InputFieldFactory.createTextField(STUDY_SUBJECT_IDENTIFIER_FIELD, "Study subject identifier", true));
        map.addInputFieldGroup(studySubjectIdentifierFieldGroup);
        return map;
    }

    public void setListValues(final ListValues listValues) {
        this.listValues = listValues;
    }

    /**
     *  this is an Ajaxable method called using <tags:tabMethod>
     */
    public ModelAndView searchStudies(HttpServletRequest request, Object cmd, Errors error) {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        return new ModelAndView(getAjaxViewName(request), map);
    }

}
