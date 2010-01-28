package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.api.impl.StudyProcessorImpl;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Severity;
import gov.nih.nci.cabig.caaers.validation.validator.DomainObjectValidator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;

/**
 * @author Sameer Sawant
 */
public class StudyImporter extends Importer{

	private static Logger logger = Logger.getLogger(StudyImporter.class);
	private DomainObjectValidator domainObjectValidator;
	private StudyProcessorImpl studyProcessorImpl;
	private StudyRepository studyRepository;
	
	public void StudyImporter(){
	}
	
	public void setStudyRepository(StudyRepository studyRepository){
		this.studyRepository = studyRepository;
	}
	
	public DomainObjectValidator getDomainObjectValidator(){
		return domainObjectValidator;
	}
	
	public void setDomainObjectValidator(DomainObjectValidator domainObjectValidator){
		this.domainObjectValidator = domainObjectValidator;
	}
	
	public StudyProcessorImpl getStudyProcessorImpl(){
		return studyProcessorImpl;
	}
	
	public void setStudyProcessorImpl(StudyProcessorImpl studyProcessorImpl){
		this.studyProcessorImpl = studyProcessorImpl;
	}
	
	public void processEntities(File xmlFile,ImportCommand command){
		boolean valid = validateAgainstSchema(xmlFile , command, getXSDLocation(STUDY_IMPORT));
        if (!valid) {
        	return;
        }
		gov.nih.nci.cabig.caaers.webservice.Studies studies;
    	try {
			JAXBContext jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.webservice");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				
			Object importObject = unmarshaller.unmarshal(xmlFile);
			if(!validRootElement(importObject, STUDY_IMPORT, command))
				return;
			
			studies = (gov.nih.nci.cabig.caaers.webservice.Studies) importObject;
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
	
	public void save(ImportCommand command, HttpServletRequest request){
		for (DomainObjectImportOutcome<Study> importOutcome : command.getImportableStudies()) {
        	studyRepository.save(importOutcome.getImportedDomainObject());
        }
	}
}