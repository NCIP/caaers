package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.api.impl.DefaultInvestigatorMigratorService;
import gov.nih.nci.cabig.caaers.api.impl.DefaultResearchStaffMigratorService;
import gov.nih.nci.cabig.caaers.api.impl.ParticipantServiceImpl;
import gov.nih.nci.cabig.caaers.api.impl.StudyProcessorImpl;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.MedDRADao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.RoutineAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.RoutineAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.integration.schema.investigator.InvestigatorType;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.ResearchStaffType;
import gov.nih.nci.cabig.caaers.rules.business.service.AdverseEventEvaluationService;
import gov.nih.nci.cabig.caaers.rules.business.service.AdverseEventEvaluationServiceImpl;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.RoutineAdverseEventReportServiceImpl;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Severity;
import gov.nih.nci.cabig.caaers.validation.validator.DomainObjectValidator;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.ctms.lang.NowFactory;
import gov.nih.nci.cabig.ctms.web.tabs.AbstractTabbedFlowFormController;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.converters.javabean.JavaBeanConverter;

/**
 * @author Krikor Krumlian
 */
public class ImportController extends AbstractTabbedFlowFormController<ImportCommand> {

    private static Log log = LogFactory.getLog(ImportController.class);

    private NowFactory nowFactory;

    private StudyDao studyDao;

    private OrganizationDao organizationDao;

    private RoutineAdverseEventReportDao routineAdverseEventReportDao;

    private ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;

    private AgentDao agentDao;

    private MedDRADao meddraDao;

    private CtcDao ctcDao;
    
    private DomainObjectValidator domainObjectValidator;
   
   //added by Monish Dombla
   private StudyProcessorImpl studyProcessorImpl;
   
   private ParticipantServiceImpl participantServiceImpl;

    private RoutineAdverseEventReportServiceImpl routineAdverseEventReportServiceImpl;

    private AdverseEventEvaluationService adverseEventEvaluationService = new AdverseEventEvaluationServiceImpl();

    
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
                // refdata.put("willSave", false);
                return refdata;
            }

            @Override
            public void validate(ImportCommand command, Errors errors) {
                System.out.println("Validating");
                boolean participantFile = command.getParticipantFile().isEmpty();
                boolean studyFile = command.getStudyFile().isEmpty();
                boolean routineAdverseEventReportFile = command.getRoutineAdverseEventReportFile()
                                .isEmpty();
                boolean investigatorFile = command.getInvestigatorFile().isEmpty();
                boolean researchStaffFile = command.getResearchStaffFile().isEmpty();
                
                log.debug("Are files empty : " + participantFile + ":" + studyFile + " : "
                                + routineAdverseEventReportFile + " : " + investigatorFile + " : " + researchStaffFile);
                if (participantFile && studyFile && routineAdverseEventReportFile && investigatorFile && researchStaffFile) errors
                                .rejectValue("participantFile", "REQUIRED",
                                                "Please choose either a study or a participant file.");

            }


            @Override
            public void postProcess(HttpServletRequest request, ImportCommand command, Errors errors) {
                // TODO: see why the command variable type has a comma attached to it
                handleLoad(command, command.getType().replace(',', ' ').trim());
            }

        });

        flow.addTab(new Tab<ImportCommand>("Review & Submit", "Review & Submit",
                        "admin/import_review_submit") {
            @Override
            public Map<String, Object> referenceData() {
                Map<String, Object> refdata = super.referenceData();
                // refdata.put("action", "New");
                return refdata;
            }
        });

        setFlow(flow);
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
        binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(true));
    }

    /**
     * 
     * @param request -
     *                HttpServletRequest
     * @throws ServletException
     */
    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        return createCommandObject();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.AbstractWizardFormController#processFinish
     *      (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     *      java.lang.Object, org.springframework.validation.BindException)
     */
    @Override
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response,
                    Object command, BindException errors) throws Exception {

        String redirectTo = "redirectToSearchInStudyTab";
        ImportCommand cObject = (ImportCommand) command;
        
        //System.out.println("T Y P E : " + cObject.getType());
        if (cObject.getType().startsWith("study")) {
            redirectTo = "redirectToSearchInStudyTab";
        }

        if (cObject.getType().startsWith("participant")) {
            redirectTo = "redirectToSearchInParticipantTab";
        }

        if (cObject.getType().startsWith("routineAeReport")) {
            redirectTo = "redirectToAeList";
        }
        
        if (cObject.getType().startsWith("investigator")) {
            redirectTo = "redirectToSearchInvestigatorTab";
        }

        if (cObject.getType().startsWith("researchStaff")) {
            redirectTo = "redirectToSearchResearchStaffTab";
        }
        System.out.println("Redirecting to : " + redirectTo);
        return new ModelAndView(redirectTo);
    }

    // helper
    private MultipartFile getMultipartFile(String type, ImportCommand command) {
        if ("participant".equals(type)) {
            return command.getParticipantFile();
        }
        if ("study".equals(type)) {
            return command.getStudyFile();
        }
        if ("routineAeReport".equals(type)) {
            return command.getRoutineAdverseEventReportFile();
        }
        if ("investigator".equals(type)) {
        	return command.getInvestigatorFile();
        }
        if ("researchStaff".equals(type)) {
        	return command.getResearchStaffFile();
        }        
        return null;
    }


    private String getXSDLocation(String type) {
        if ("study".equals(type)) {
            //return "classpath:gov/nih/nci/cabig/caaers/studyXSD.xsd";
        	return "classpath:gov/nih/nci/cabig/caaers/StudySchema.xsd";
        }
        if ("participant".equals(type)) {
            //return "classpath:gov/nih/nci/cabig/caaers/participantXSD.xsd";
        	return "classpath:gov/nih/nci/cabig/caaers/ParticipantSchema.xsd";
        }
        if ("routineAeReport".equals(type)) {
            return "classpath:gov/nih/nci/cabig/caaers/routineAeXSD.xsd";
        }
        if ("investigator".equals(type)) {
            return "classpath:gov/nih/nci/cabig/caaers/Investigator.xsd";
        }  
        if ("researchStaff".equals(type)) {
            return "classpath:gov/nih/nci/cabig/caaers/ResearchStaff.xsd";
        }        
        return null;
    }

    private boolean validateAgainstSchema(File xmlFile, ImportCommand command, String xsdUrl) {
    	boolean validXml = false;
    	try {
            // parse an XML document into a DOM tree
        	
        	DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        	documentBuilderFactory.setValidating(false);
        	documentBuilderFactory.setNamespaceAware(true);
            DocumentBuilder parser = documentBuilderFactory.newDocumentBuilder();
            Document document = parser.parse(xmlFile);
            // create a SchemaFactory capable of understanding WXS schemas
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            // load a WXS schema, represented by a Schema instance

            // load a WXS schema, represented by a Schema instance
            //Source schemaFile = new StreamSource(getApplicationContext().getResource(xsdUrl).getFile());
            Source schemaFile = new StreamSource(getResources(xsdUrl)[0].getFile());
            Schema schema = schemaFactory.newSchema(schemaFile);

            // create a Validator instance, which can be used to validate an instance document
            Validator validator = schema.newValidator();

            // validate the DOM tree

            validator.validate(new DOMSource(document));
            validXml = true;
            // return xmlFile;
        } catch (FileNotFoundException ex) {
            throw new CaaersSystemException("File Not found Exception", ex);
        } catch (IOException ioe) {
            command.setSchemaValidationResult(ioe.getMessage());
            throw new CaaersSystemException(ioe);
        } catch (SAXParseException spe) {
            command.setSchemaValidationResult("Line : " + spe.getLineNumber() + " - "
                            + spe.getMessage());
        } catch (SAXException e) {
            command.setSchemaValidationResult(e.toString());
            throw new CaaersSystemException(e);
        } catch (ParserConfigurationException pce) {
            throw new CaaersSystemException("Parser configuration exception ", pce);
        }
        return validXml;
    }

    private void handleStaffLoad(ImportCommand command, String type) {
    	BufferedReader input = null;
        try {
            File xmlFile = File.createTempFile("file", "uploaded");
            FileCopyUtils.copy(getMultipartFile(type, command).getInputStream(), new FileOutputStream(xmlFile));
            command.setSchemaValidationResult(null);
            boolean valid = validateAgainstSchema(xmlFile , command, getXSDLocation(type));
            
            if (!valid) {
            	return;
            }
            
            if ("investigator".equals(type)) {
            	DefaultInvestigatorMigratorService svc = (DefaultInvestigatorMigratorService) getApplicationContext().getBean("investigatorMigratorService");
            	JAXBContext jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.integration.schema.investigator");
    			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
    		    gov.nih.nci.cabig.caaers.integration.schema.investigator.Staff staff = (gov.nih.nci.cabig.caaers.integration.schema.investigator.Staff )unmarshaller.unmarshal(xmlFile);
    		    for(InvestigatorType xmlInvestigator : staff.getInvestigator()){
    		    	DomainObjectImportOutcome<Investigator> investigatorOutcome = svc.processInvestigator(xmlInvestigator);
    		    	if(investigatorOutcome.isSavable()){
    		    		command.addImportableInvestigator(investigatorOutcome);
    		    	}else{
    		    		command.addNonImportableInvestigator(investigatorOutcome);
    		    	}
    		    }
            }

            if ("researchStaff".equals(type)) {
            	DefaultResearchStaffMigratorService svc = (DefaultResearchStaffMigratorService) getApplicationContext().getBean("researchStaffMigratorService");
            	JAXBContext jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.integration.schema.researchstaff");
    			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
    			gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff  staff = (gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff )unmarshaller.unmarshal(xmlFile);
    			for(ResearchStaffType researchStaff : staff.getResearchStaff()){
    				DomainObjectImportOutcome<ResearchStaff> researchStaffOutcome = svc.processResearchStaff(researchStaff);
    				if(researchStaffOutcome.isSavable()){
    					command.addImportableResearchStaff(researchStaffOutcome);
    				}else{
    					command.addNonImportableResearchStaff(researchStaffOutcome);
    				}
    			}
            }
        } catch (EOFException ex) {
            System.out.println("EndOfFile Reached");       
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("File Not found Exception", ex);
        } catch (IOException ex) {
            throw new RuntimeException("IO Exception", ex);
        } catch (JAXBException e) {
        	throw new RuntimeException("JAXB Exception", e);
		} finally {
            try {
                if (input != null) {
                    // flush and close both "input" and its underlying FileReader
                    input.close();
                }
            } catch (IOException ex) {
                throw new RuntimeException("IO Exception", ex);
            }

            log.debug("Study List size " + command.getImportableStudies().size());
            log.debug("Participant List size " + command.getImportableParticipants().size());
        }   	
    }
    
    private void handleLoad(ImportCommand command, String type) {

        XStream xstream = new XStream();
        xstream.registerConverter(new JavaBeanConverter(xstream.getMapper(), "class"), -20);

        // common
        xstream.alias("study", gov.nih.nci.cabig.caaers.domain.Study.class);
        xstream.alias("identifier", gov.nih.nci.cabig.caaers.domain.Identifier.class);
        xstream.alias("organizationAssignedIdentifier",
                        gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier.class);
        xstream.alias("systemAssignedIdentifier",
                        gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier.class);
        xstream.alias("site", gov.nih.nci.cabig.caaers.domain.Organization.class);
        xstream.alias("studySite", gov.nih.nci.cabig.caaers.domain.StudySite.class);
        xstream.alias("studyInvestigator", gov.nih.nci.cabig.caaers.domain.StudyInvestigator.class);
        xstream.alias("siteInvestigator", gov.nih.nci.cabig.caaers.domain.SiteInvestigator.class);
        xstream.alias("investigator", gov.nih.nci.cabig.caaers.domain.Investigator.class);
        xstream.alias("studyPersonnel", gov.nih.nci.cabig.caaers.domain.StudyPersonnel.class);
        xstream.alias("researchStaff", gov.nih.nci.cabig.caaers.domain.ResearchStaff.class);
        xstream.alias("studyFundingSponsor",
                        gov.nih.nci.cabig.caaers.domain.StudyFundingSponsor.class);
        xstream.alias("studyCoordinatingCenter",
                        gov.nih.nci.cabig.caaers.domain.StudyCoordinatingCenter.class);
        xstream.alias("studyOrganization", gov.nih.nci.cabig.caaers.domain.StudyOrganization.class);
        xstream.alias("organization", gov.nih.nci.cabig.caaers.domain.Organization.class);
        xstream.alias("assignment",
                        gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment.class);
        xstream.registerConverter(new DateConverter("yyyy-MM-dd", new String[] { "yyyy" }));
        xstream.registerConverter(new CustomStringConverter());
        // study specific
        xstream.alias("studyAgent", gov.nih.nci.cabig.caaers.domain.StudyAgent.class);
        xstream.alias("studyAgentINDAssociation",
                        gov.nih.nci.cabig.caaers.domain.StudyAgentINDAssociation.class);
        xstream.alias("investigationalNewDrug",
                        gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug.class);
        xstream.alias("agent", gov.nih.nci.cabig.caaers.domain.Agent.class);
        xstream.alias("ctepStudyDisease", gov.nih.nci.cabig.caaers.domain.CtepStudyDisease.class);
        xstream.alias("meddraStudyDisease",
                        gov.nih.nci.cabig.caaers.domain.MeddraStudyDisease.class);
        xstream.alias("treatmentAssignment",
                        gov.nih.nci.cabig.caaers.domain.TreatmentAssignment.class);
        xstream.alias("diseaseTerm", gov.nih.nci.cabig.caaers.domain.DiseaseTerm.class);
        xstream.alias("category", gov.nih.nci.cabig.caaers.domain.DiseaseCategory.class);
        xstream.alias("ctcVersion", gov.nih.nci.cabig.caaers.domain.Ctc.class);
        xstream.alias("aeTerminology", gov.nih.nci.cabig.caaers.domain.AeTerminology.class);
        xstream.alias("diseaseTerminology",
                        gov.nih.nci.cabig.caaers.domain.DiseaseTerminology.class);
        xstream.alias("diseaseCodeTerm", gov.nih.nci.cabig.caaers.domain.DiseaseCodeTerm.class);
        xstream.alias("fundingSponsor", gov.nih.nci.cabig.caaers.domain.FundingSponsor.class);
        xstream.alias("coordinatingCenter",
                        gov.nih.nci.cabig.caaers.domain.CoordinatingCenter.class);
        // participant specific
        xstream.alias("participant", gov.nih.nci.cabig.caaers.domain.Participant.class);
        // routineAdverseEventReport specific
        xstream.alias("routineAdverseEventCollection",
                        gov.nih.nci.cabig.caaers.domain.RoutineAdverseEventReport.class);
        xstream.alias("adverseEvent", gov.nih.nci.cabig.caaers.domain.AdverseEvent.class);
        xstream.alias("adverseEventCtcTerm",
                        gov.nih.nci.cabig.caaers.domain.AdverseEventCtcTerm.class);
        xstream.alias("adverseEventMeddraLowLevelTerm",
                        gov.nih.nci.cabig.caaers.domain.AdverseEventMeddraLowLevelTerm.class);
        xstream.alias("lowLevelTerm", gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm.class);
        xstream.alias("ctcTerm", gov.nih.nci.cabig.caaers.domain.CtcTerm.class);
        xstream.alias("grade", gov.nih.nci.cabig.caaers.domain.Grade.class);
        xstream.alias("hospitalization", gov.nih.nci.cabig.caaers.domain.Hospitalization.class);
        xstream.alias("attribution", gov.nih.nci.cabig.caaers.domain.Attribution.class);
        xstream.alias("status", gov.nih.nci.cabig.caaers.domain.Status.class);

        BufferedReader input = null;
        try {
            File xmlFile = File.createTempFile("file", "uploaded");
            FileCopyUtils.copy(getMultipartFile(type, command).getInputStream(),
                            new FileOutputStream(xmlFile));
            validateAgainstSchema(xmlFile, command, getXSDLocation(type));

            if((type.equals("participant")) && (command.getSchemaValidationResult() == null)){
            	processParticipant(xmlFile,command);
            }
            
            if((type.equals("study")) && (command.getSchemaValidationResult() == null)){
            	processStudy(xmlFile,command);
            }
            
            if ("investigator".equals(type) || "researchStaff".equals(type)) {    		
        		handleStaffLoad(command , type);
        		return;
        	}

            if (type.equals("routineAeReport")) {
                int maxNumberofRoutineReports = 1000;
                int currentNumberofRoutineReports = 1;
                input = new BufferedReader(new FileReader(xmlFile));
                ObjectInputStream in = xstream.createObjectInputStream(input);
                while (true && currentNumberofRoutineReports++ <= maxNumberofRoutineReports
                                && command.getSchemaValidationResult() == null) {
                    RoutineAdverseEventReport xstreamRoutineAdverseEventReport = (RoutineAdverseEventReport) in
                                    .readObject();
                    migrateRoutineAdverseEventReport(xstreamRoutineAdverseEventReport, command);
                }
            }
        } catch (EOFException ex) {
            System.out.println("EndOfFile Reached");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Class Not found Exception", ex);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("File Not found Exception", ex);
        } catch (IOException ex) {
            throw new RuntimeException("IO Exception", ex);
        } finally {
            try {
                if (input != null) {
                    // flush and close both "input" and its underlying FileReader
                    input.close();
                }
            } catch (IOException ex) {
                throw new RuntimeException("IO Exception", ex);
            }

            log.debug("Study List size " + command.getImportableStudies().size());
            log.debug("Participant List size " + command.getImportableParticipants().size());
        }
    }
    
    
    /**
     * This method is added to process create and update of Participant 
     * Monish Dombla
     */
    
    private void processParticipant(File xmlFile,ImportCommand command){
    	gov.nih.nci.cabig.caaers.webservice.participant.Participants participants;
    	try {
			JAXBContext jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.webservice.participant");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				
			participants = (gov.nih.nci.cabig.caaers.webservice.participant.Participants)unmarshaller.unmarshal(xmlFile);
			if(participants != null){
				for(gov.nih.nci.cabig.caaers.webservice.participant.ParticipantType participantDto : participants.getParticipant()){
					DomainObjectImportOutcome<Participant> participantImportOutcome  = participantServiceImpl.processParticipant(participantDto);
					if (participantImportOutcome.isSavable()) {
						command.addImportableParticipant(participantImportOutcome);
			        } else {
			            command.addNonImportableParticipant(participantImportOutcome);
			        }
				}
				
			}
		} catch (JAXBException e) {
			throw new CaaersSystemException("There was an error converting participant data transfer object to participant domain object", e);
		}
    }
    
    
    /**
     * This method is added to process create and update of Study 
     * Monish Dombla
     */
    
    private void processStudy(File xmlFile,ImportCommand command){
    	gov.nih.nci.cabig.caaers.webservice.Studies studies;
    	try {
			JAXBContext jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.webservice");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				
			studies = (gov.nih.nci.cabig.caaers.webservice.Studies)unmarshaller.unmarshal(xmlFile);
			if(studies != null){
				for(gov.nih.nci.cabig.caaers.webservice.Study studyDto : studies.getStudy()){
					DomainObjectImportOutcome<Study> studyImportOutcome  = studyProcessorImpl.processStudy(studyDto);
					List<String> errors = domainObjectValidator.validate(studyImportOutcome.getImportedDomainObject());
					if (studyImportOutcome.isSavable() && errors.size() == 0) {
			            command.addImportableStudy(studyImportOutcome);
			        } else {
			        	for(String errMsg : errors){
			    			studyImportOutcome.addErrorMessage(errMsg, Severity.ERROR);
			    		}
			            command.addNonImportableStudy(studyImportOutcome);
			        }
				}
				//Remove Duplicate Studies in the List.
				List<DomainObjectImportOutcome<Study>> dupList = new ArrayList<DomainObjectImportOutcome<Study>>();
				for(int i=0 ; i < command.getImportableStudies().size()-1 ; i++){
					Study study1 = command.getImportableStudies().get(i).getImportedDomainObject();
					Study study2 = command.getImportableStudies().get(i+1).getImportedDomainObject();
					if(study1.equals(study2)){
						command.getImportableStudies().get(i+1).addErrorMessage("Study Identifier already used in a different Study", Severity.ERROR);
						command.addNonImportableStudy(command.getImportableStudies().get(i+1));
						dupList.add(command.getImportableStudies().get(i+1));
						log.debug("Duplicate Study :: " + study2.getShortTitle());
					}
				}
				for(DomainObjectImportOutcome<Study> obj : dupList){
					command.getImportableStudies().remove(obj);
				}
			}
		} catch (JAXBException e) {
			throw new CaaersSystemException("There was an error converting study data transfer object to study domain object", e);
		}
    }

//    private void migrateStudy(Study xstreamStudy, ImportCommand command) {
//
//        DomainObjectImportOutcome<Study> studyImportOutcome = studyImportService
//                        .importStudy(xstreamStudy);
//        if (studyImportOutcome.isSavable()) {
//            command.addImportableStudy(studyImportOutcome);
//        } else {
//            command.addNonImportableStudy(studyImportOutcome);
//        }
//    }
//
//    private void migrateParticipant(Participant xstreamParticipant, ImportCommand command) {
//
//        DomainObjectImportOutcome<Participant> participantImportOutcome = participantImportService
//                        .importParticipant(xstreamParticipant);
//        if (participantImportOutcome.isSavable()) {
//            command.addImportableParticipant(participantImportOutcome);
//        } else {
//            command.addNonImportableParticipant(participantImportOutcome);
//        }
//    }

    private void migrateRoutineAdverseEventReport(
                    RoutineAdverseEventReport xstreamRoutineAdverseEventReport,
                    ImportCommand command) {

        DomainObjectImportOutcome<RoutineAdverseEventReport> routineAdverseEventReportImportOutcome = routineAdverseEventReportServiceImpl
                        .createRoutineAdverseEventReportObjects(xstreamRoutineAdverseEventReport);
        if (routineAdverseEventReportImportOutcome.isSavable()) {
            command.addImportableRoutineAdverseEventReport(routineAdverseEventReportImportOutcome);
        } else {
            command.addNonImportableRoutineAdverseEventReport(routineAdverseEventReportImportOutcome);
        }
    }

    public ExpeditedAdverseEventReport getExpedited(RoutineAdverseEventReport raer) {
        log.debug("Checking for expedited AEs");
        Study study = raer.getStudy();

        // Create the expedited Report
        ExpeditedAdverseEventReport aeReport = new ExpeditedAdverseEventReport();
        aeReport.setAssignment(raer.getAssignment());
        aeReport.setCreatedAt(nowFactory.getNowTimestamp());

        try {
            for (AdverseEvent ae : raer.getAdverseEvents()) {

                String message = adverseEventEvaluationService.assesAdverseEvent(ae, study);
                if (message.equals("SERIOUS_ADVERSE_EVENT")) {
                    aeReport.addAdverseEvent(ae);
                }
            }
            return aeReport.getAdverseEvents().isEmpty() ? null : aeReport;
        } catch (Exception e) {
            throw new CaaersSystemException("There was an error evaluating Routine AEs", e);
        }
    }

    private ImportCommand createCommandObject() {
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

    public void setOrganizationDao(final OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public AgentDao getAgentDao() {
        return agentDao;
    }

    public void setAgentDao(AgentDao agentDao) {
        this.agentDao = agentDao;
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

    public RoutineAdverseEventReportDao getRoutineAdverseEventReportDao() {
        return routineAdverseEventReportDao;
    }

    public void setRoutineAdverseEventReportDao(
                    RoutineAdverseEventReportDao routineAdverseEventReportDao) {
        this.routineAdverseEventReportDao = routineAdverseEventReportDao;
    }

    public RoutineAdverseEventReportServiceImpl getRoutineAdverseEventReportServiceImpl() {
        return routineAdverseEventReportServiceImpl;
    }

    public void setRoutineAdverseEventReportServiceImpl(
                    RoutineAdverseEventReportServiceImpl routineAdverseEventReportServiceImpl) {
        this.routineAdverseEventReportServiceImpl = routineAdverseEventReportServiceImpl;
    }

    public NowFactory getNowFactory() {
        return nowFactory;
    }

    public void setNowFactory(NowFactory nowFactory) {
        this.nowFactory = nowFactory;
    }

    public ExpeditedAdverseEventReportDao getExpeditedAdverseEventReportDao() {
        return expeditedAdverseEventReportDao;
    }

    public void setExpeditedAdverseEventReportDao(
                    ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao) {
        this.expeditedAdverseEventReportDao = expeditedAdverseEventReportDao;
    }

    public class CustomStringConverter extends AbstractSingleValueConverter {

        public boolean canConvert(Class clazz) {
            return clazz.equals(String.class);
        }

        public Object fromString(String str) {
            return str.trim();
        }
    }

	public StudyProcessorImpl getStudyProcessorImpl() {
		return studyProcessorImpl;
	}

	public void setStudyProcessorImpl(StudyProcessorImpl studyProcessorImpl) {
		this.studyProcessorImpl = studyProcessorImpl;
	}
	
	public ParticipantServiceImpl getParticipantServiceImpl() {
		return participantServiceImpl;
	}

	public void setParticipantServiceImpl(
			ParticipantServiceImpl participantServiceImpl) {
		this.participantServiceImpl = participantServiceImpl;
	}

	public void setDomainObjectValidator(DomainObjectValidator domainObjectValidator) {
		this.domainObjectValidator = domainObjectValidator;
	}

	/**
	 * This method fetches the specified resource pattern from classpath.
	 * In this context used to fetch xsd files.
	 * @param pattern
	 * @return
	 * @throws IOException
	 */
	private Resource[] getResources(String pattern) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        if (logger.isDebugEnabled()) logger.debug("Looking for resources matching " + pattern);
        Resource[] resources = resolver.getResources(pattern);
        return resources;
    }

	
}
