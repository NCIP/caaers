package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.dao.query.ParticipantQuery;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.repository.ParticipantRepository;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AssignParticipantTab extends TabWithFields<AssignParticipantStudyCommand> {

    private static final Log log = LogFactory.getLog(AssignParticipantTab.class);

    private ParticipantRepository participantRepository;
   
    public AssignParticipantTab() {
        super("Search for subject", "Search Subject", "par/reg_participant_search");
    }

    @Override
    public void onDisplay(HttpServletRequest request, AssignParticipantStudyCommand command) {
        super.onDisplay(request, command);

        String searchText = command.getParticipantText();
        String searchType = command.getParticipantType();
        log.debug("Search text : " + searchText + "Type : " + searchType);
        if (StringUtils.isEmpty(searchText)) return; // no search keyword

        ParticipantQuery participantQuery = new ParticipantQuery();
        if ("fn".equals(searchType)) {
            participantQuery.filterByFirstName(searchText);
        } else if ("ln".equals(searchType)) {
            participantQuery.filterByLastName(searchText);
        } else if ("idtf".equals(searchType)) {
            participantQuery.leftJoinFetchOnIdentifiers();
            participantQuery.filterByIdentifierValue(searchText);
        } else if ("g".equals(searchType)) {
            participantQuery.excludeHavingGender(searchText);
        } else if ("r".equals(searchType)) {
            participantQuery.excludeHavingRace(searchText);
        } else if ("e".equals(searchType)) {
            participantQuery.excludeHavingEthnicity(searchText);

        }

        final Integer studySiteId = command.getStudySiteId();
        participantQuery.filterByNotMachingStudySiteId(studySiteId);
        List<Participant> participants = null;
        try {
            participants = participantRepository.searchParticipant(participantQuery);
        } catch (Exception e) {
            log.error("Error while searching participants", e);
        }
        command.setParticipantSearchResults(participants);
        command.setParticipantText("");

    }

    @Override
    public void postProcess(HttpServletRequest request, AssignParticipantStudyCommand command,
                    Errors errors) {
        super.postProcess(request, command, errors);
        List<Participant> participants = new ArrayList<Participant>();
        for (Participant participant : command.getParticipantSearchResults()) {
            if (participant.getId().equals(command.getParticipantId())) {
                participants.add(participant);
                command.setParticipants(participants);
                break;
            }
        }
    }

/*
    @Override
    public void validate(AssignParticipantStudyCommand command, Errors errors) {
        super.validate(command, errors);
        if (command.getParticipantId() == null) errors.rejectValue("participantId", "REQUIRED",
                        "Participant not selected");
    }
*/

    public Map<String, InputFieldGroup> createFieldGroups(AssignParticipantStudyCommand command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        return map;
    }

    @Required
    public void setParticipantRepository(final ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }
}