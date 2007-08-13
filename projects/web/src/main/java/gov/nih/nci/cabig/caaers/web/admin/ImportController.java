package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.MedDRADao;
import gov.nih.nci.cabig.caaers.domain.AbstractIdentifiableDomainObject;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.StudyFundingSponsor;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.Terminology;

import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.web.participant.CreateParticipantController;
import gov.nih.nci.cabig.caaers.web.participant.NewParticipantCommand;
import gov.nih.nci.cabig.ctms.web.tabs.AbstractTabbedFlowFormController;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.converters.javabean.JavaBeanConverter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.EOFException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.util.FileCopyUtils;

/**
 * @author Krikor Krumlian
 */
public class ImportController extends AbstractTabbedFlowFormController<ImportCommand> {
	
	private static Log log = LogFactory.getLog(CreateParticipantController.class);
	
	private StudyDao studyDao;
	private ParticipantDao participantDao;
	private OrganizationDao organizationDao;
	private AgentDao agentDao; 
	private MedDRADao meddraDao;
	private CtcDao ctcDao;
	
	public ImportController() {		
        setCommandClass(ImportCommand.class);
        setAllowDirtyForward(false);
        setAllowDirtyBack(false);

        Flow<ImportCommand> flow = new Flow<ImportCommand>("Import Data");       
        
        flow.addTab(new Tab<ImportCommand>("Import ", "Import ", "admin/import") {
            public Map<String, Object> referenceData() {
                Map<String, Object> refdata = super.referenceData();
                refdata.put("action", "New");
                return refdata;
            }
            
            @Override
            public void validate(ImportCommand command, Errors errors) {
                boolean participantFile = command.getParticipantFile().isEmpty();
                boolean studyFile = command.getStudyFile().isEmpty() ;
                log.debug("Are files empty : " + participantFile + ":" + studyFile);
                if (participantFile && studyFile) errors.rejectValue("participantFile", "REQUIRED", "Please choose either a stuy or a participant file.");
            }
            
            @Override
            public void postProcess(HttpServletRequest request,
                    				ImportCommand command,
                    				Errors errors)
             {
            	// TODO: see why the command variable type has a comma attached to it
            	handleLoad(command, command.getType().replace(',', ' ').trim());
             }
            
            
        });
        
        flow.addTab(new Tab<ImportCommand>("Review & Submit", "Review & Submit", "admin/import_review_submit") {
            public Map<String, Object> referenceData() {
                Map<String, Object> refdata = super.referenceData();
                //refdata.put("action", "New");
                return refdata;
            }
        });
                                           
        setFlow(flow);        
    }
	
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		super.initBinder(request, binder);
		binder.registerCustomEditor(Date.class, ControllerTools
				.getDateEditor(true));	
	}
	
	/**
	 * 
	 * @param request -
	 *            HttpServletRequest
	 * @throws ServletException
	 */
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {	
		return createCommandObject();		         
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractWizardFormController#processFinish
	 * (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, 
	 * java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, 
			Object command, BindException errors) throws Exception {
		
		ImportCommand cObject = (ImportCommand)command;
		for ( int i=0; i < cObject.getStudies().size(); i ++)
		{
			Study s = cObject.getStudies().get(i);
			studyDao.save(s);
		}
		
		for ( int j=0; j < cObject.getParticipants().size(); j++)
		{
			Participant p = cObject.getParticipants().get(j);
			participantDao.save(p);
		}
		
		response.sendRedirect("/caaers/pages/study/search");
    	return null;
	}
	
	private void handleLoad(ImportCommand command, String type){
		
		XStream xstream = new XStream();
		xstream.registerConverter(new JavaBeanConverter(xstream.getMapper(),"class"),-20);
    	
    	// common
    	xstream.alias("study", gov.nih.nci.cabig.caaers.domain.Study.class);
    	xstream.alias("identifier", gov.nih.nci.cabig.caaers.domain.Identifier.class);
    	xstream.alias("organizationAssignedIdentifier", gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier.class);
    	xstream.alias("systemAssignedIdentifier", gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier.class);
    	xstream.alias("site", gov.nih.nci.cabig.caaers.domain.Organization.class);
    	xstream.alias("studySite", gov.nih.nci.cabig.caaers.domain.StudySite.class);
    	xstream.alias("studyFundingSponsor", gov.nih.nci.cabig.caaers.domain.StudyFundingSponsor.class);
    	xstream.alias("studyOrganization", gov.nih.nci.cabig.caaers.domain.StudyOrganization.class);
    	xstream.alias("organization", gov.nih.nci.cabig.caaers.domain.Organization.class);
    	xstream.alias("assignment", gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment.class);
    	xstream.registerConverter(new DateConverter("yyyy-MM-dd",new String[]{}));
    	// study specific
    	xstream.alias("studyAgent", gov.nih.nci.cabig.caaers.domain.StudyAgent.class);
    	xstream.alias("agent", gov.nih.nci.cabig.caaers.domain.Agent.class);
    	xstream.alias("ctepStudyDisease", gov.nih.nci.cabig.caaers.domain.CtepStudyDisease.class);
    	xstream.alias("diseaseTerm", gov.nih.nci.cabig.caaers.domain.DiseaseTerm.class);
    	xstream.alias("category", gov.nih.nci.cabig.caaers.domain.DiseaseCategory.class);
    	xstream.alias("ctcVersion" , gov.nih.nci.cabig.caaers.domain.Ctc.class);
    	xstream.alias("terminology" , gov.nih.nci.cabig.caaers.domain.Terminology.class);
    	// participant specific
    	xstream.alias("participant", gov.nih.nci.cabig.caaers.domain.Participant.class);
    	
        BufferedReader input = null;
        try {
        	
        	File xmlFile = File.createTempFile("file","uploaded");
        	FileCopyUtils.copy(type.equals("participant") ? 
        			command.getParticipantFile().getInputStream() : 
        				command.getStudyFile().getInputStream(),
        			new FileOutputStream(xmlFile));
        	
        	input = new BufferedReader( new FileReader(xmlFile) );
            ObjectInputStream in = xstream.createObjectInputStream(input);
        	
          if (type.equals("participant")){
        	  while (true)
              {
              Participant participant = (Participant)in.readObject();
              createParticipantObjects(participant, command);
              }  
          }
          
          if (type.equals("study")){
        	  while (true)
              {
              Study study = (Study)in.readObject();
              createStudyObjects(study, command);
              }  
          }
        }
        catch (EOFException ex){
            System.out.println("EndOfFile Reached");
          }
        catch (ClassNotFoundException ex) {
            throw new RuntimeException("Class Not found Exception", ex);
          }
        catch (FileNotFoundException ex) {
        	throw new RuntimeException("File Not found Exception", ex);
        }
        catch (IOException ex){
        	throw new RuntimeException("IO Exception", ex);
        }
        finally {
          try {
            if (input!= null) {
              //flush and close both "input" and its underlying FileReader
              input.close();
            }
          }
          catch (IOException ex) {
        	  throw new RuntimeException("IO Exception", ex);
          }
          
          log.debug("Study List size "  + command.getStudies().size());
          log.debug("Participant List size "  + command.getParticipants().size());   
        }
	}
	
	
	private void createParticipantObjects(Participant xstreamParticipant,
			ImportCommand command) {
		
		Participant participant = new Participant();
		participant.setFirstName(xstreamParticipant.getFirstName());
		participant.setLastName(xstreamParticipant.getLastName());
		participant.setMiddleName(xstreamParticipant.getMiddleName());
		participant.setMaidenName(xstreamParticipant.getMaidenName());
		participant.setDateOfBirth(xstreamParticipant.getDateOfBirth());
		participant.setGender(xstreamParticipant.getGender());
		participant.setRace(xstreamParticipant.getRace());
		participant.setEthnicity(xstreamParticipant.getRace());
		
		migrateIdentifiers(participant,xstreamParticipant);
		
		// Check for study and site association
		if (xstreamParticipant.getAssignments() != null) {
			for (int i = 0; i < xstreamParticipant.getAssignments().size(); i++) {
				StudyParticipantAssignment studyParticipantAssignment = xstreamParticipant
						.getAssignments().get(i);
				StudySite studySite = null;
				
				for (Identifier identifier : studyParticipantAssignment.getStudySite().getStudy().getIdentifiers()) 
				{	
					Study study = studyDao.getByIdentifier(identifier);
					if (study != null) {
						studySite = study.getStudySites().get(0);
						participant.getAssignments().add(
								new StudyParticipantAssignment(participant,studySite));
						break;
					} 		
				}
			}
				
			if (participantUniquenessCheck(command,participant) && participantAssignmentCheck(command,participant) )
			{
				command.getParticipants().add(participant);
			}
		}	
	}
	

	/*
	 * If participant that we are trying to import has the same firstName
	 * and lastName as a participant in the system then fail
	 * 
	 */
	private boolean participantUniquenessCheck(ImportCommand command, Participant participant){
		
		
		String[] s = {participant.getFirstName(),participant.getLastName() };
		List<Participant> pars = participantDao.getByUniqueIdentifiers(s);
		boolean result = true;
		if (pars != null && pars.size() >= 1){
			//pars.get(0).getAssignments().get(0).getStudySite().getStudy().getLongTitle()
			command.addParticipantErros(participant, "This participant already exists in caAERS.");
			log.debug("We have a validation error");
			result= false;
		}
		return result;
	}
	
	/*
	 * If the participant that we are trying to import has no assignments
	 * then it has no studies attached to it => fail.
	 */
	
	private boolean participantAssignmentCheck(ImportCommand command, Participant participant){
		
		if (participant.getAssignments().size() == 0 ) {
			command.addParticipantErros(participant, "This participant is not associated to any Study.");
			log.debug("We have a validation error");
			return false;
		} else {
			return true;
		}
	}
	
	/*
	 * 
	 */
	private boolean studyUniquenessCheck(ImportCommand command, Study study){
		
		for (Identifier identifier : study.getIdentifiers()) 
		{	
			Study tempStudy = studyDao.getByIdentifier(identifier);
			if (tempStudy != null) {
				command.addStudyErros(study, "This Study/Protocol contains the same identifier as a study already in caAERS." );
				//log.debug("Validation Error");
				return false;
			} 		
		}
		return true;	
	}
	
	private void createStudyObjects(Study xstreamStudy, ImportCommand command)
	{
		Study st = new Study();
		st.setShortTitle(xstreamStudy.getShortTitle());
		st.setLongTitle(xstreamStudy.getLongTitle());
		st.setDescription(xstreamStudy.getDescription());
		st.setPrecis(xstreamStudy.getPrecis());
		st.setDiseaseCode(xstreamStudy.getDiseaseCode());
		st.setMonitorCode(xstreamStudy.getMonitorCode());
		st.setPhaseCode(xstreamStudy.getPhaseCode());
		//st.setPrimarySponsorCode(xstreamStudy.getPrimarySponsorCode());
		st.setStatus(xstreamStudy.getStatus());
		// Integer
		st.setTargetAccrualNumber(xstreamStudy.getTargetAccrualNumber());
		// Boolean
		st.setBlindedIndicator(xstreamStudy.getBlindedIndicator());
		st.setMultiInstitutionIndicator(xstreamStudy.getMultiInstitutionIndicator());
		st.setRandomizedIndicator(xstreamStudy.getRandomizedIndicator());
		
		migrateTerminology(st,xstreamStudy);
		migrateIdentifiers(st,xstreamStudy);
		migrateStudyOrganizations(st,xstreamStudy);
		
		
		
		// StudyAgents
		if (xstreamStudy.getStudyAgents() != null) {
			for (int i = 0; i < xstreamStudy.getStudyAgents().size(); i++) {
				StudyAgent studyAgent = xstreamStudy.getStudyAgents().get(i);
				Agent agent = null;
				if ( studyAgent.getAgent().getName() != null ){
					agent = agentDao.getByName(studyAgent.getAgent().getName()) ;
				}
				if ( studyAgent.getAgent().getNscNumber() != null && agent == null ){
					agent = agentDao.getByNscNumber(studyAgent.getAgent().getNscNumber()) ;
				}
				if ( agent != null ){
					st.addStudyAgent(createStudyAgent(agent));
				}
				// TODO: ADD error handling with user interaction
				
			}
		}
		
		if (studyUniquenessCheck(command,st)) {
			command.getStudies().add(st);
		}
		
	}
	
	
	private void migrateIdentifiers(AbstractIdentifiableDomainObject destination, AbstractIdentifiableDomainObject source) {

		// Identifiers
		if (source.getIdentifiers() != null) {
			for (int i = 0; i < source.getIdentifiers().size(); i++) {
				Identifier identifier = (Identifier) source.getIdentifiers()
						.get(i);
				if (identifier instanceof OrganizationAssignedIdentifier) {
					Organization organization = organizationDao
							.getByName(((OrganizationAssignedIdentifier) identifier).getOrganization().getName());
					((OrganizationAssignedIdentifier) identifier).setOrganization(organization);
				}

				if (identifier instanceof SystemAssignedIdentifier) {
					// I don't need to do anything i think
				}
				destination.getIdentifiers().add(identifier);
			}
		}
	}
	
	private void migrateStudyOrganizations(Study destination,Study source){
		
		if (source.getStudyOrganizations() != null) {
			for (int i = 0; i < source.getStudyOrganizations().size(); i++) {
				StudyOrganization studyOrganization = source.getStudyOrganizations().get(i);
				if (studyOrganization instanceof StudySite) {
					StudySite studySite = (StudySite) studyOrganization;
					Organization organization = organizationDao.getByName(studySite.getOrganization().getName());
					studySite.setOrganization(organization);
					destination.addStudySite(studySite);	
				} 
				if (studyOrganization instanceof StudyFundingSponsor) {
					StudyFundingSponsor studyFundingSponsor = (StudyFundingSponsor) studyOrganization;
					Organization organization = organizationDao.getByName(studyFundingSponsor.getOrganization().getName());
					studyFundingSponsor.setOrganization(organization);
					destination.addStudyFundingSponsor(studyFundingSponsor);	
				} 
				
			}
		}
		
	}
	
	private void migrateTerminology(Study destination,Study source){
		
		// Terminology and Version 
		if (source.getTerminology() != null){
			if(source.getTerminology().getCtcVersion() != null){
				Ctc ctc = ctcDao.getById(Integer.parseInt(source.getTerminology().getCtcVersion().getName()));
				Terminology t = destination.getTerminology();
				t.setTerm(Term.CTC);
				t.setCtcVersion(ctc);
			}
			if(source.getTerminology().getMeddraVersion() != null){
				Terminology t = destination.getTerminology();
				t.setTerm(Term.MEDDRA);
			}
		}
		
	}
	
	/* Phased out
	private StudySite createStudyOrganization(Organization organization){
		
		StudySite studySite = new StudySite();
		studySite.setRoleCode("Site");
		studySite.setOrganization(organization == null ? organizationDao.getDefaultOrganization() : organization );
		return studySite;
	}
	
	private StudyFundingSponsor createStudyFundingSponsor(Organization organization){
		
		StudyFundingSponsor studyFundingSponsor = new StudyFundingSponsor();
		studyFundingSponsor.setOrganization(organization == null ? organizationDao.getDefaultOrganization() : organization );
		return studyFundingSponsor;
	}*/
	
	private StudyAgent createStudyAgent(Agent agent){
		
		StudyAgent studyAgent = new StudyAgent();
		studyAgent.setAgent(agent);
		return studyAgent;
	}
	
	
	private ImportCommand createCommandObject()
	{
		ImportCommand ic = new ImportCommand();
		return ic;
	}

	public StudyDao getStudyDao() {
		return studyDao;
	}

	public void setStudyDao(StudyDao studyDao) {
		this.studyDao = studyDao;
	}

	public OrganizationDao getOrganizationDao() {
		return organizationDao;
	}

	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	public AgentDao getAgentDao() {
		return agentDao;
	}

	public void setAgentDao(AgentDao agentDao) {
		this.agentDao = agentDao;
	}

	public ParticipantDao getParticipantDao() {
		return participantDao;
	}

	public void setParticipantDao(ParticipantDao participantDao) {
		this.participantDao = participantDao;
	}

	public MedDRADao getMeddraDao() {
		return meddraDao;
	}

	public void setMeddraDao(MedDRADao meddraDao) {
		this.meddraDao = meddraDao;
	}

	public CtcDao getCtcDao() {
		return ctcDao;
	}

	public void setCtcDao(CtcDao ctcDao) {
		this.ctcDao = ctcDao;
	}
	
	
	
	
	
	
	
	
	
	
	
}