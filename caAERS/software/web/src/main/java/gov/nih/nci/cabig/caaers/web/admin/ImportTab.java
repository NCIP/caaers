package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.api.impl.DefaultInvestigatorMigratorService;
import gov.nih.nci.cabig.caaers.api.impl.DefaultResearchStaffMigratorService;
import gov.nih.nci.cabig.caaers.api.impl.ParticipantServiceImpl;
import gov.nih.nci.cabig.caaers.api.impl.StudyProcessorImpl;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.integration.schema.investigator.InvestigatorType;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.ResearchStaffType;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Severity;
import gov.nih.nci.cabig.caaers.validation.validator.DomainObjectValidator;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ImportTab extends Tab<ImportCommand>{
	
	private static final Log logger = LogFactory.getLog(ImportTab.class);
	private DomainObjectValidator domainObjectValidator;
	private StudyProcessorImpl studyProcessorImpl;
	private DefaultInvestigatorMigratorService investigatorMigratorService;
	private DefaultResearchStaffMigratorService researchStaffMigratorService;
	private ParticipantServiceImpl participantServiceImpl;
	
	public ImportTab(String longTitle, String shortTitle, String viewName){
		super(longTitle, shortTitle, viewName);
	}
	
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
        
        logger.debug("Are files empty : " + participantFile + ":" + studyFile + " : "
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

    private void handleLoad(ImportCommand command, String type) {

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

        } catch (EOFException ex) {
            System.out.println("EndOfFile Reached");
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

            logger.debug("Study List size " + command.getImportableStudies().size());
            logger.debug("Participant List size " + command.getImportableParticipants().size());
        }
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


    public String getXSDLocation(String type) {
        if ("study".equals(type)) {
        	return "classpath:gov/nih/nci/cabig/caaers/StudySchema.xsd";
        }
        if ("participant".equals(type)) {
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
					List<String> errors = domainObjectValidator.validate(participantImportOutcome.getImportedDomainObject());
					if (participantImportOutcome.isSavable() && errors.size() == 0) {
						command.addImportableParticipant(participantImportOutcome);
			        } else {
			        	for(String errMsg : errors){
			        		participantImportOutcome.addErrorMessage(errMsg, Severity.ERROR);
			        	}
			            command.addNonImportableParticipant(participantImportOutcome);
			        }
				}
				
				//Remove Duplicate Participants from the ImportableParticipants  List.
				List<DomainObjectImportOutcome<Participant>> dupList = new ArrayList<DomainObjectImportOutcome<Participant>>();
				for(int k=0 ; k < command.getImportableParticipants().size()-1 ; k++){
					Participant par1 = command.getImportableParticipants().get(k).getImportedDomainObject();
					for(int l=k+1 ; l < command.getImportableParticipants().size() ; l++){
						Participant par2 = command.getImportableParticipants().get(l).getImportedDomainObject();
						if(par1.equals(par2)){
							command.getImportableParticipants().get(l).addErrorMessage("Participant Identifier already used", Severity.ERROR);
							command.addNonImportableParticipant(command.getImportableParticipants().get(l));
							dupList.add(command.getImportableParticipants().get(l));
							logger.debug("Duplicate Participant :: " + par2.getFullName());
							break;
						}
					}
				}
				for(DomainObjectImportOutcome<Participant> obj : dupList){
					command.getImportableParticipants().remove(obj);
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
					for(int j=i+1 ; j < command.getImportableStudies().size() ; j++){
						Study study2 = command.getImportableStudies().get(j).getImportedDomainObject();
						if(study1.equals(study2)){
							command.getImportableStudies().get(j).addErrorMessage("Study Identifier already used in a different Study", Severity.ERROR);
							command.addNonImportableStudy(command.getImportableStudies().get(j));
							dupList.add(command.getImportableStudies().get(j));
							logger.debug("Duplicate Study :: " + study2.getShortTitle());
							break;
						}
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
    
	public Resource[] getResources(String pattern) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        if (logger.isDebugEnabled()) logger.debug("Looking for resources matching " + pattern);
        Resource[] resources = resolver.getResources(pattern);
        return resources;
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
            	//DefaultInvestigatorMigratorService svc = (DefaultInvestigatorMigratorService) getApplicationContext().getBean("investigatorMigratorService");
            	JAXBContext jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.integration.schema.investigator");
    			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
    		    gov.nih.nci.cabig.caaers.integration.schema.investigator.Staff staff = (gov.nih.nci.cabig.caaers.integration.schema.investigator.Staff )unmarshaller.unmarshal(xmlFile);
    		    for(InvestigatorType xmlInvestigator : staff.getInvestigator()){
    		    	DomainObjectImportOutcome<Investigator> investigatorOutcome = investigatorMigratorService.processInvestigator(xmlInvestigator);
    		    	List<String> errors = domainObjectValidator.validate(investigatorOutcome.getImportedDomainObject());
    		    	if(investigatorOutcome.isSavable() && errors.size() == 0){
    		    		command.addImportableInvestigator(investigatorOutcome);
    		    	}else{
    		    		for(String errMsg : errors){
    		    			investigatorOutcome.addErrorMessage(errMsg, Severity.ERROR);
			        	}
    		    		command.addNonImportableInvestigator(investigatorOutcome);
    		    	}
    		    }
    		    //Remove Duplicate Investigators from the ImportableInvestigators List.
    		    List<DomainObjectImportOutcome<Investigator>> dupList = new ArrayList<DomainObjectImportOutcome<Investigator>>();
    		    for(int k=0 ; k < command.getImportableInvestigators().size()-1 ; k++){
    		    	Investigator inv1 = command.getImportableInvestigators().get(k).getImportedDomainObject();
					for(int l=k+1 ; l < command.getImportableInvestigators().size() ; l++){
						Investigator inv2 = command.getImportableInvestigators().get(l).getImportedDomainObject();
						if(inv1.equals(inv2)){
							command.getImportableInvestigators().get(l).addErrorMessage("Duplicate Investigator", Severity.ERROR);
							command.addNonImportableInvestigator(command.getImportableInvestigators().get(l));
							dupList.add(command.getImportableInvestigators().get(l));
							logger.debug("Duplicate Investigator :: " + inv2.getFullName());
							break;
						}
					}
				}
				for(DomainObjectImportOutcome<Investigator> obj : dupList){
					command.getImportableInvestigators().remove(obj);
				}
            }

            if ("researchStaff".equals(type)) {
            	//DefaultResearchStaffMigratorService svc = (DefaultResearchStaffMigratorService) getApplicationContext().getBean("researchStaffMigratorService");
            	JAXBContext jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.integration.schema.researchstaff");
    			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
    			gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff  staff = (gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff )unmarshaller.unmarshal(xmlFile);
    			for(ResearchStaffType researchStaff : staff.getResearchStaff()){
    				DomainObjectImportOutcome<ResearchStaff> researchStaffOutcome = researchStaffMigratorService.processResearchStaff(researchStaff);
    				List<String> errors = domainObjectValidator.validate(researchStaffOutcome.getImportedDomainObject());
    				if(researchStaffOutcome.isSavable() && errors.size() == 0){
    					command.addImportableResearchStaff(researchStaffOutcome);
    				}else{
    					for(String errMsg : errors){
    						researchStaffOutcome.addErrorMessage(errMsg, Severity.ERROR);
			        	}
    					command.addNonImportableResearchStaff(researchStaffOutcome);
    				}
    			}
    			//Remove Duplicate Investigators from the ImportableInvestigators List.
    		    List<DomainObjectImportOutcome<ResearchStaff>> dupList = new ArrayList<DomainObjectImportOutcome<ResearchStaff>>();
    		    for(int k=0 ; k < command.getImportableResearchStaff().size()-1 ; k++){
    		    	ResearchStaff rStaff1 = command.getImportableResearchStaff().get(k).getImportedDomainObject();
					for(int l=k+1 ; l < command.getImportableResearchStaff().size() ; l++){
						ResearchStaff rStaff2 = command.getImportableResearchStaff().get(l).getImportedDomainObject();
						if(rStaff1.equals(rStaff2)){
							command.getImportableResearchStaff().get(l).addErrorMessage("Duplicate ResearchStaff", Severity.ERROR);
							command.addNonImportableResearchStaff(command.getImportableResearchStaff().get(l));
							dupList.add(command.getImportableResearchStaff().get(l));
							logger.debug("Duplicate Investigator :: " + rStaff2.getFullName());
							break;
						}
					}
				}
				for(DomainObjectImportOutcome<ResearchStaff> obj : dupList){
					command.getImportableResearchStaff().remove(obj);
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

            logger.debug("Study List size " + command.getImportableStudies().size());
            logger.debug("Participant List size " + command.getImportableParticipants().size());
        }   	
    }
    
    

	public void setParticipantServiceImpl(
			ParticipantServiceImpl participantServiceImpl) {
		this.participantServiceImpl = participantServiceImpl;
	}

	public void setDomainObjectValidator(DomainObjectValidator domainObjectValidator) {
		this.domainObjectValidator = domainObjectValidator;
	}
	
	public StudyProcessorImpl getStudyProcessorImpl() {
		return studyProcessorImpl;
	}

	public void setStudyProcessorImpl(StudyProcessorImpl studyProcessorImpl) {
		this.studyProcessorImpl = studyProcessorImpl;
	}

	public void setInvestigatorMigratorService(
			DefaultInvestigatorMigratorService investigatorMigratorService) {
		this.investigatorMigratorService = investigatorMigratorService;
	}

	public void setResearchStaffMigratorService(
			DefaultResearchStaffMigratorService researchStaffMigratorService) {
		this.researchStaffMigratorService = researchStaffMigratorService;
	}

}
