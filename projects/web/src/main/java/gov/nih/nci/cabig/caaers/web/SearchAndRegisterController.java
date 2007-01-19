package gov.nih.nci.cabig.caaers.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import gov.nih.nci.cabig.caaers.domain.Participant;
//import gov.nih.nci.cabig.caaers.domain.ParticipantIdentifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.ParticipantService;
import gov.nih.nci.cabig.caaers.service.StudyService;


public class SearchAndRegisterController extends SimpleFormController {
	private static Log log = LogFactory
			.getLog(SearchAndRegisterController.class);
	
	public SearchAndRegisterController() {
		setCommandClass(SearchRegisterCommand.class);
		setFormView("home");
		setSuccessView("reg_protocol_search");
	}

	private StudyService studyService;
	private ParticipantService participantService;

	public StudyService getStudyService() {
		return studyService;
	}

	public void setStudyService(StudyService studyService) {
		this.studyService = studyService;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object oCommand, BindException errors)
			throws Exception {
		SearchRegisterCommand searchRegisterCommand = (SearchRegisterCommand) oCommand;
		String category = searchRegisterCommand.getSearchCategory();
		// USER IS SEARCHING for participants
		if (category !=null && category.equalsIgnoreCase("participant")) {
			String searchTextPart=searchRegisterCommand.getSearchTypeTextPart();
			String searchType=searchRegisterCommand.getSearchTypePart();
			log.debug("search string = " + searchTextPart + "; type = " + searchType);
			Participant participant=new Participant();
			
			//Populate the Participant Object with properties
			if ("fn".equals(searchType))
				participant.setFirstName(searchTextPart);
			else if ("ln".equals(searchType))
				participant.setLastName(searchTextPart);
			else if ("g".equals(searchType))
				participant.setGender(searchTextPart);
			else if ("r".equals(searchType))
				participant.setRace(searchTextPart);
			else if ("e".equals(searchType))
				participant.setEthnicity(searchTextPart);
			
			/*
	    	if ("MRN".equals(searchType)) {
				ParticipantIdentifier participantIdentifier = new ParticipantIdentifier();
				participantIdentifier.setMedicalRecordNumber(searchTextPart);
				participant.addParticipantIdentifier(participantIdentifier);
			}*/
			
			List<Participant> participants = participantService
					.search(participant);

			Iterator<Participant> participantIter = participants.iterator();
			while (participantIter.hasNext()) {
				participant = participantIter.next();
				System.out.println("Id for participant is "
						+ participant.getId());
				System.out.println("LastName of participant is "
						+ participant.getLastName());
				System.out.println("FirstName of participant is "
						+ participant.getFirstName());
				// System.out.println(" D.O.B of participant is " +
				// participant.getBirthDate());

			}
			String type = searchRegisterCommand.getSearchType();
			String searchtext = searchRegisterCommand.getSearchTypeText();

			log.debug("Search results size " + participants.size());
			Map map = errors.getModel();
			map.put("participants", participants);
			map.put("studySiteId", request.getParameter("studySiteId"));
			map.put("searchTypeParticipant", getSearchTypeParticipant());			
			ModelAndView modelAndView = new ModelAndView("reg_participant_search", map);
			return modelAndView;

		}
		
		// THE USER IS SEARCHING FOR STUDIES
		String type=searchRegisterCommand.getSearchType();
		String searchtext=searchRegisterCommand.getSearchTypeText();
		Study study = new Study();
		log.debug("search string = " + searchtext + "; type = " + type);
		
		// Populate the Study Object with properties
		if ("st".equals(type))
			study.setShortTitle(searchtext);
		else if ("lt".equals(type))
			study.setLongTitle(searchtext);
		else if ("d".equals(type))
			study.setDescription(searchtext);
//		else if ("pic".equals(type))
//			study.setPrincipalInvestigatorCode(searchtext);
//		else if ("pin".equals(type))
//			study.setPrincipalInvestigatorName(searchtext);
		else if ("psc".equals(type))
			study.setPrimarySponsorCode(searchtext);
//		else if ("psn".equals(type))
//			study.setPrimarySponsorName(searchtext);
		else if ("pc".equals(type))
			study.setPhaseCode(searchtext);

		List<Study> studies = studyService.search(study);
		if (studies == null || studies.size() == 0) {
			log.debug("NO STUDIES FOUND !!!!");
			studies = new ArrayList<Study>();
			Study temp = new Study();
			temp.setId(0);
			temp.setShortTitle("hey");
			studies.add(temp);
		} 
		log.debug("Search results size " + studies.size());
		
		// Remove Study if NO site associated with it
		for (int i = 0; i < studies.size(); i++) {
			if (studies.get(i).getStudySites() == null
					|| studies.get(i).getStudySites().size() == 0) {
				System.out.println("removing study[" + i + "] from studies");
				studies.remove(i);
				i--;
			}
		}
		log.debug("Search results size after filtering " + studies.size());
		
		Map map = errors.getModel();
		map.put("studies", studies);
		map.put("searchType", getSearchType());	
		ModelAndView modelAndView = new ModelAndView(getSuccessView(), map);
		return modelAndView;
	}

	protected Map<String, Object> referenceData(
			HttpServletRequest httpServletRequest) throws Exception {
		Map<String, Object> refdata = new HashMap<String, Object>();

		refdata.put("searchType", getSearchType());
		refdata.put("searchTypePart", getSearchTypeParticipant());
		return refdata;
	}
	private List<LOV> getSearchTypeParticipant(){
			List<LOV> col = new ArrayList<LOV>();
			LOV lov1 = new LOV("fn", "First Name");
			LOV lov2 = new LOV("ln", "Last Name");
			LOV lov3 = new LOV("g" , "gender");
			LOV lov4 = new LOV("r" , "race" );
			LOV lov5 = new LOV("e" , "ethnicity");
			col.add(lov1);
	    	col.add(lov2);
	    	col.add(lov3);
	    	col.add(lov4);
	    	col.add(lov5);
	    	return col;
	}
	private List<LOV> getSearchType() {
		List<LOV> col = new ArrayList<LOV>();
		LOV lov1 = new LOV("st",  "Short Title");
		LOV lov2 = new LOV("lt",  "Long Title");
		LOV lov3 = new LOV("d",   "Description");
		LOV lov4 = new LOV("pic", "Principal Investigator Code");
		LOV lov5 = new LOV("pin", "Principal Investigator Name");
		LOV lov6 = new LOV("psc", "Primary Sponsor Code");
		LOV lov7 = new LOV("psn", "Primary Sponsor Name");
		LOV lov8 = new LOV("pc",  "Phase Code");
		
		col.add(lov1);
		col.add(lov2);
		col.add(lov3);
		col.add(lov4);
		col.add(lov5);
		col.add(lov6);
		col.add(lov7);
		col.add(lov8);

		return col;
	}

	public class LOV {

		private String code;

		private String desc;

		LOV(String code, String desc) {
			this.code = code;
			this.desc = desc;

		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}
	}	
	public ParticipantService getParticipantService() {
		return participantService;
	}

	public void setParticipantService(ParticipantService participantService) {
		this.participantService = participantService;
	}
}