package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.domain.repository.ParticipantRepository;
import gov.nih.nci.cabig.caaers.web.ListValues;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

public class AssignParticipantTab extends TabWithFields<AssignParticipantStudyCommand> {

    private static final Log log = LogFactory.getLog(AssignParticipantTab.class);
    private ParticipantRepository participantRepository;
    protected ListValues listValues;

    public AssignParticipantTab() {
        super("Search for subject", "Search Subject", "par/reg_participant_search");
    }

    @Override
    public void postProcess(HttpServletRequest request, AssignParticipantStudyCommand command, Errors errors) {
        super.postProcess(request, command, errors);
    }

    protected void validate(AssignParticipantStudyCommand command, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {
        super.validate(command, commandBean, fieldGroups, errors);
        if (command.getParticipant() == null || command.getParticipant().getId() == null)
            errors.rejectValue("participant", "REQUIRED", "Subject not selected");
    }

    public Map<String, InputFieldGroup> createFieldGroups(AssignParticipantStudyCommand command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        return map;
    }


    @Required
    public void setParticipantRepository(final ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public ModelAndView searchSubjects(HttpServletRequest request, Object cmd, Errors error) {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        return new ModelAndView(getAjaxViewName(request), map);
    }

    public ListValues getListValues() {
        return listValues;
    }

    public void setListValues(ListValues listValues) {
        this.listValues = listValues;
    }

}