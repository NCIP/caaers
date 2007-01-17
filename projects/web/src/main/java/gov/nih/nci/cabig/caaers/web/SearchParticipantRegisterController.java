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

//import SearchAndRegisterController.LOV;

import gov.nih.nci.cabig.caaers.domain.Participant;
//import gov.nih.nci.cabig.caaers.domain.ParticipantIdentifier;
import gov.nih.nci.cabig.caaers.service.ParticipantService;
//import gov.nih.nci.cabig.caaers.web.SearchParticipantController.LOV;


public class SearchParticipantRegisterController extends SimpleFormController{
	
	private static Log log = LogFactory.getLog(SearchParticipantRegisterController.class);
		
		private ParticipantService participantService;
		
		public SearchParticipantRegisterController(){
			setCommandClass(SearchParticipantCommand.class);
			setFormView("reg_enroll_patient");
			setSuccessView("reg_patient_search");
		}
		
		public ParticipantService getParticipantService() {
			return participantService;
		}

		public void setParticipantService(ParticipantService participantService) {
			this.participantService = participantService;
		}
		
		protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object oCommand, BindException errors) throws Exception {
	    	SearchParticipantCommand searchParticipantCommand = (SearchParticipantCommand) oCommand;
	    	Participant participant = new Participant();
	    	String text = searchParticipantCommand.getSearchText();
	    	String type = searchParticipantCommand.getSearchType();
	    	
	    	log.debug("search string = " +text+"; type = "+type);
	    	
//	    	Populate the Participant Object with properties
			if ("fn".equals(type))
				participant.setFirstName(text);
			else if ("ln".equals(type))
				participant.setLastName(text);
			else if ("g".equals(type))
				participant.setGender(text);
			else if ("r".equals(type))
				participant.setRace(text);
			else if ("e".equals(type))
				participant.setEthnicity(text);
	    	
	    	
	    	/*
	    	if ("MRN".equals(type)) {
				ParticipantIdentifier participantIdentifier = new ParticipantIdentifier();
				participantIdentifier.setMedicalRecordNumber(text);
				//FIXME:
				//participant.addParticipantIdentifier(participantIdentifier);
			}    	
	    	*/		
	    	List<Participant> participants = participantService.search(participant); 
	    	
	    	Iterator<Participant> participantIter = participants.iterator(); 
	        while(participantIter.hasNext()){
	        	participant = participantIter.next();
	        	System.out.println("Id for participant is "+participant.getId());
	        	System.out.println("LastName of participant is "+participant.getLastName());
	        	System.out.println("FirstName of participant is "+participant.getFirstName());
	        //	System.out.println(" D.O.B of participant is " + participant.getBirthDate());
	        	
	        }
	    	
	    	log.debug("Search results size " +participants.size());
	    	Map map =errors.getModel();
	    	map.put("participants", participants);
	    	map.put("studySiteId", request.getParameter("studySiteId"));
	    	map.put("searchType", getSearchType());
	    	ModelAndView modelAndView= new ModelAndView(getSuccessView(), map);
	    	return modelAndView;
	    }
		
		 protected Map<String, Object> referenceData(HttpServletRequest httpServletRequest) throws Exception {
	 		Map<String, Object> refdata = new HashMap<String, Object>();
	 	
	 	refdata.put("searchType", getSearchType());
	 	refdata.put("studySiteId", httpServletRequest.getParameter("studySiteId"));
	     return refdata;
	 }
		 
		 private List<LOV> getSearchType(){
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
		 
		 public class LOV {
				
				private String code;
				private String desc;
				
				LOV(String code, String desc)
				{
					this.code=code;
					this.desc=desc;
					
				}
				
				public String getCode() {
					return code;
				}

				public void setCode(String code) {
					this.code = code;
				}
				
				public String getDesc(){
					return desc;
				}
					
				public void setDesc(String desc){
					this.desc=desc;
				}
			}
		
		

	}