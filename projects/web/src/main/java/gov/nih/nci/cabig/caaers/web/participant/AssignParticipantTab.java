package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.service.ParticipantService;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
public class AssignParticipantTab extends Tab<AssignParticipantStudyCommand>{
	
	private static final Log log = LogFactory.getLog(AssignParticipantTab.class);
	private ParticipantService participantService;
	
	public AssignParticipantTab(){
		super("Choose Study", "Search Subject", "par/reg_participant_search");
	}
	
	
	@Override
	public void onDisplay(HttpServletRequest request,AssignParticipantStudyCommand command) {
		super.onDisplay(request, command);
		
		String searchText = command.getParticipantText();
		String searchType = command.getParticipantType();
		log.debug("Search text : " + searchText + "Type : " + searchType);
		if(StringUtils.isEmpty(searchText)) return; //no search keyword
		
		Participant participant = new Participant();

		if ("fn".equals(searchType)) {
			participant.setFirstName(searchText);
		}else if ("ln".equals(searchType)) {
			participant.setLastName(searchText);
		}else if ("idtf".equals(searchType)) {
			Identifier identifier = new Identifier();
			identifier.setValue(searchText);
			participant.addIdentifier(identifier);
		}else if ("g".equals(searchType)) {
			participant.setGender(searchText);
		}else if ("r".equals(searchType)) {
			participant.setRace(searchText);
		}else if ("e".equals(searchType)) {
			participant.setEthnicity(searchText);
		}

		List<Participant> participants = null;
		try {
			participants = participantService.search(participant);
		} catch (Exception e) {
			log.error("Error while searching participants", e);
		}
		command.setParticipantSearchResults(participants);
		command.setParticipantText("");
		
	}
	
	@Override
	public void postProcess(HttpServletRequest request,	AssignParticipantStudyCommand command, Errors errors) {
		super.postProcess(request, command, errors);
		List<Participant> participants = new ArrayList<Participant>();
		for(Participant participant : command.getParticipantSearchResults()){
			if(participant.getId().equals(command.getParticipantId())){
				participants.add(participant);
				command.setParticipants(participants);
				break;
			}
		}
	}
	
	@Override
	public void validate(AssignParticipantStudyCommand command, Errors errors) {
		super.validate(command, errors);
		if(command.getParticipantId() == null) errors.rejectValue("participantId", "REQUIRED","Participant not selected");
	}
	
	public ParticipantService getParticipantService() {
		return participantService;
	}


	public void setParticipantService(ParticipantService participantService) {
		this.participantService = participantService;
	}
	
	
	
}