package gov.nih.nci.cabig.caaers.web;

import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.StudyService;
import gov.nih.nci.cabig.caaers.web.tabbedflow.AbstractTabbedFlowFormController;
import gov.nih.nci.cabig.caaers.web.tabbedflow.Flow;
import gov.nih.nci.cabig.caaers.web.tabbedflow.Tab;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.core.TableModelImpl;
import org.springframework.orm.hibernate3.HibernateSystemException;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Kulasekaran
 */
public class SearchStudyController extends AbstractTabbedFlowFormController<SearchStudyCommand> {
		    	
	private StudyService studyService;
	private ListValues listValues;
	
	public SearchStudyController() {		             
		setCommandClass(SearchStudyCommand.class);
		
        Flow<SearchStudyCommand> flow = new Flow<SearchStudyCommand>("Search Study");       
        
        flow.addTab(new Tab<SearchStudyCommand>("Study Search Results", "", "study/study_search") {
            public Map<String, Object> referenceData() {
            	            	
                Map<String, Object> refdata = super.referenceData();
                refdata.put("studySearchType", listValues.getStudySearchType());               
    	  		return refdata;
            }        	
        });
                                        
        setFlow(flow);        
    }
		
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		super.initBinder(request, binder);				
		
		
		if(request.getMethod().equals(METHOD_GET))
		{
			SearchStudyAjaxFacade studyFacade = new SearchStudyAjaxFacade();
			Context context = null;
			context = new HttpServletRequestContext(request);
        
			TableModel model = new TableModelImpl(context);
			Object viewData = null;
			try {
				viewData = studyFacade.build(model, new ArrayList());	          
			} catch (Exception e) {
				e.printStackTrace();
			} 			
			
			request.setAttribute("assembler", viewData);
		} 		
	}
	
	/**
	 * Create a nested object graph that search Study Design needs
	 * 
	 * @param request -
	 *            HttpServletRequest
	 * @throws ServletException
	 */
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {	
		SearchStudyCommand searchStudyCommand = new SearchStudyCommand();
		ArrayList<SearchCommand> commands = new ArrayList<SearchCommand>();
		commands.add(new SearchCommand());
		searchStudyCommand.setSearchCommand(commands);
		return searchStudyCommand;
	}	

	@Override
	protected void postProcessPage(HttpServletRequest request, Object command,
			Errors arg2, int pageNo) throws Exception 
	{		
		SearchStudyAjaxFacade studyFacade = new SearchStudyAjaxFacade();
		Context context = null;
		context = new HttpServletRequestContext(request);
    
		TableModel model = new TableModelImpl(context);
		Object viewData = null;
		try {
			viewData = studyFacade.build(model, new ArrayList());	          
		} catch (Exception e) {
			e.printStackTrace();
		} 			
		
		request.setAttribute("assembler", viewData);
		
		int index = Integer.parseInt(request.getParameter("_selected"));
		
		if(index == -1)
		{
			((SearchStudyCommand)command).getSearchCommand().add(new SearchCommand());
		}
		else
		{
			((SearchStudyCommand)command).getSearchCommand().remove(index);
		}							
	}			
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractWizardFormController#processFinish
	 * (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, 
	 * java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, 
			Object command, BindException errors) throws Exception {
    	return null;
	}
	
	/* protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    ModelAndView mv = new ModelAndView("study_search");
	    SearchStudyAjaxFacade studyFacade = new SearchStudyAjaxFacade();
	    
	    Context context = null;
        context = new HttpServletRequestContext(request);
        
        TableModel model = new TableModelImpl(context);
        Object viewData = null;
        try {
          viewData = studyFacade.build(model, new ArrayList());	          
        } catch (Exception e) {
          e.printStackTrace();
        }        
	    		    	    
	    request.setAttribute("assembler", viewData);
	    return mv;
	} */
	
	public StudyService getStudyService() {
		return studyService;
	}

	public void setStudyService(StudyService studyService) {
		this.studyService = studyService;
	}
	
	public ListValues getListValues() {
		return listValues;
	}
    
    public void setListValues(ListValues listValues) {
		this.listValues = listValues;
	}
}

	
/*	
	protected void onBind(HttpServletRequest request, Object command,
			BindException errors) throws Exception {
		log.debug("Entering onBind...");
		AssignParticipantStudyCommand apsCommand = (AssignParticipantStudyCommand) command;
		String searchtext = apsCommand.getStudyText();
		String type = apsCommand.getStudyType();
		String psearchtext = apsCommand.getParticipantText();
		String ptype = apsCommand.getParticipantType();
		List<StudySite> studySites = new ArrayList<StudySite>();

		// PAGE 1
		if (searchtext != null && type != null && !searchtext.equals("")) {
			log.debug("Search text : " + searchtext + "Type : " + type);
			Study study = new Study();
			apsCommand.setStudies(new ArrayList<Study>());
			if ("st".equals(type))
				study.setShortTitle(searchtext);
			else if ("lt".equals(type))
				study.setLongTitle(searchtext);
			else if ("d".equals(type))
				study.setDescription(searchtext);
			else if ("psc".equals(type))
				study.setPrimarySponsorCode(searchtext);
			else if ("pc".equals(type))
				study.setPhaseCode(searchtext);

			List<Study> studies = studyService.search(study);
			apsCommand.setStudies(studies);
			apsCommand.setStudyText("");
		}

		// PAGE 2
		if (apsCommand.getStudyId() != null
				&& !apsCommand.getStudyId().equals("")) {
			log.debug("The StudyId is : " + apsCommand.getStudyId());
			Study study = studyDao.getById(Integer.parseInt(apsCommand
					.getStudyId()));
			studySites.add(study.getStudySites().get(0));
			apsCommand.setStudySites(studySites);
			//apsCommand.setStudyId("");

		}
		// PAGE 2
		if (psearchtext != null && ptype != null && !psearchtext.equals("")) {

			log.debug("Search text : " + searchtext + "Type : " + type);
			Participant participant = new Participant();

			if ("fn".equals(ptype))
				participant.setFirstName(psearchtext);
			else if ("ln".equals(ptype))
				participant.setLastName(psearchtext);
			else if ("g".equals(ptype))
				participant.setGender(psearchtext);
			else if ("r".equals(ptype))
				participant.setRace(psearchtext);
			else if ("e".equals(ptype))
				participant.setEthnicity(psearchtext);

			List<Participant> participants = participantService
					.search(participant);
			apsCommand.setParticipantSearchResults(participants);
			apsCommand.setParticipantText("");
		}
		// PAGE 3
		if (apsCommand.getParticipantId() != null
				&& !apsCommand.getParticipantId().equals("")) {
			log
					.debug("The ParticipantId is : "
							+ apsCommand.getParticipantId());
			Participant participant = participantDao.getById(Integer
					.parseInt(apsCommand.getParticipantId()));
			participant.getAssignments().size();
			List<Participant> participants = new ArrayList<Participant>();
			participants.add(participant);
			apsCommand.setParticipants(participants);
			//apsCommand.setParticipantId("");

		}
	}
 */
