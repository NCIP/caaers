package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.MedDRADao;
import gov.nih.nci.cabig.caaers.domain.AbstractIdentifiableDomainObject;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.StudyServiceImpl;
import gov.nih.nci.cabig.caaers.service.ParticipantServiceImpl;

import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.web.admin.ImportCommand;
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

	private static Log log = LogFactory.getLog(ImportController.class);

	private StudyDao studyDao;
	private ParticipantDao participantDao;
	private OrganizationDao organizationDao;
	private AgentDao agentDao;
	private MedDRADao meddraDao;
	private CtcDao ctcDao;
	private StudyServiceImpl studyServiceImpl;
	private ParticipantServiceImpl participantServiceImpl;
	
	public ImportController() {		

        setCommandClass(ImportCommand.class);
        setAllowDirtyForward(false);
        setAllowDirtyBack(false);

        Flow<ImportCommand> flow = new Flow<ImportCommand>("Import Data");

        flow.addTab(new Tab<ImportCommand>("Import ", "Import ", "admin/import") {
            @Override
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
            @Override
			public Map<String, Object> referenceData() {
                Map<String, Object> refdata = super.referenceData();
                //refdata.put("action", "New");
                return refdata;
            }
        });

        setFlow(flow);
    }

	@Override
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
	@Override
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

		String redirectTo = "redirectToSearchInStudyTab";
		ImportCommand cObject = (ImportCommand)command;
		if (cObject.getImportableStudies().size() > 0){
			studyDao.batchSave(cObject.getImportableStudies());
			redirectTo = "redirectToSearchInStudyTab";
		}
		
		if (cObject.getImportableParticipants().size() > 0){
			participantDao.batchSave(cObject.getImportableParticipants());
			redirectTo = "redirectToSearchInParticipantTab";
		}
		
		return new ModelAndView(redirectTo);
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
    	xstream.alias("studyInvestigator" , gov.nih.nci.cabig.caaers.domain.StudyInvestigator.class);
    	xstream.alias("siteInvestigator" , gov.nih.nci.cabig.caaers.domain.SiteInvestigator.class);
    	xstream.alias("investigator" , gov.nih.nci.cabig.caaers.domain.Investigator.class);
    	xstream.alias("studyPersonnel", gov.nih.nci.cabig.caaers.domain.StudyPersonnel.class);
    	xstream.alias("researchStaff", gov.nih.nci.cabig.caaers.domain.ResearchStaff.class);
    	xstream.alias("studyFundingSponsor", gov.nih.nci.cabig.caaers.domain.StudyFundingSponsor.class);
    	xstream.alias("studyOrganization", gov.nih.nci.cabig.caaers.domain.StudyOrganization.class);
    	xstream.alias("organization", gov.nih.nci.cabig.caaers.domain.Organization.class);
    	xstream.alias("assignment", gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment.class);
    	xstream.registerConverter(new DateConverter("yyyy-MM-dd",new String[]{}));
    	// study specific
    	xstream.alias("studyAgent", gov.nih.nci.cabig.caaers.domain.StudyAgent.class);
    	xstream.alias("studyAgentINDAssociation", gov.nih.nci.cabig.caaers.domain.StudyAgentINDAssociation.class);
    	xstream.alias("investigationalNewDrug", gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug.class);
    	xstream.alias("agent", gov.nih.nci.cabig.caaers.domain.Agent.class);
    	xstream.alias("ctepStudyDisease", gov.nih.nci.cabig.caaers.domain.CtepStudyDisease.class);
    	xstream.alias("meddraStudyDisease", gov.nih.nci.cabig.caaers.domain.MeddraStudyDisease.class);
    	xstream.alias("treatmentAssignment", gov.nih.nci.cabig.caaers.domain.TreatmentAssignment.class);
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
              Participant xstreamParticipant = (Participant)in.readObject();
              migrateParticipant(xstreamParticipant,command);
              }
          }

          if (type.equals("study")){
        	  while (true)
              {
              Study xstreamStudy = (Study)in.readObject();
              migrateStudy(xstreamStudy, command);
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

          log.debug("Study List size "  + command.getImportableStudies().size());
          log.debug("Participant List size "  + command.getImportableParticipants().size());
        }
	}
	
	private void migrateStudy(Study xstreamStudy, ImportCommand command){
		
		DomainObjectImportOutcome<Study> studyImportOutcome = studyServiceImpl.createStudyObjects(xstreamStudy);
		if (studyImportOutcome.isSavable()){
			command.addImportableStudy(studyImportOutcome);
		}else{
			command.addNonImportableStudy(studyImportOutcome);
		}
	}
	
	private void migrateParticipant(Participant xstreamParticipant, ImportCommand command){
		
		DomainObjectImportOutcome<Participant> participantImportOutcome = participantServiceImpl.createParticipantObjects(xstreamParticipant);
		if (participantImportOutcome.isSavable()){
			command.addImportableParticipant(participantImportOutcome);
		}else{
			command.addNonImportableParticipant(participantImportOutcome);
		}
	}
	
	/*
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
	*/
	


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

	public StudyServiceImpl getStudyServiceImpl() {
		return studyServiceImpl;
	}

	public void setStudyServiceImpl(StudyServiceImpl studyServiceImpl) {
		this.studyServiceImpl = studyServiceImpl;
	}

	public ParticipantServiceImpl getParticipantServiceImpl() {
		return participantServiceImpl;
	}

	public void setParticipantServiceImpl(
			ParticipantServiceImpl participantServiceImpl) {
		this.participantServiceImpl = participantServiceImpl;
	}	
	
	
	
}