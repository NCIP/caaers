package gov.nih.nci.cabig.caaers.api.impl;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.api.StudyProcessor;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.StudyImportServiceImpl;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Severity;
import gov.nih.nci.cabig.caaers.service.migrator.StudyConverter;
import gov.nih.nci.cabig.caaers.service.synchronizer.StudySynchronizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@WebService(endpointInterface="gov.nih.nci.cabig.caaers.api.StudyProcessor", serviceName="StudyService")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)
public class StudyProcessorImpl implements StudyProcessor {
	
	
private static Log logger = LogFactory.getLog(StudyProcessor.class);
	
	//Injected through spring
	private StudyImportServiceImpl studyImportService;
	private StudyDao studyDao;
	private StudyConverter studyConverter;
	private StudySynchronizer studySynchronizer;
	
	public StudyImportServiceImpl getStudyImportService() {
		return studyImportService;
	}

	public void setStudyImportService(StudyImportServiceImpl studyImportService) {
		this.studyImportService = studyImportService;
	}

	public StudyDao getStudyDao() {
		return studyDao;
	}

	public void setStudyDao(StudyDao studyDao) {
		this.studyDao = studyDao;
	}

	public StudyConverter getStudyConverter() {
		return studyConverter;
	}

	public void setStudyConverter(StudyConverter studyConverter) {
		this.studyConverter = studyConverter;
	}

	public StudySynchronizer getStudySynchronizer() {
		return studySynchronizer;
	}

	public void setStudySynchronizer(StudySynchronizer studySynchronizer) {
		this.studySynchronizer = studySynchronizer;
	}

	/**
	 * Method exisits only to be called from ImportController for testing
	 * until the Webservice is up and running 
	 * @param studyDto
	 */
	public DomainObjectImportOutcome<Study> processStudy(gov.nih.nci.cabig.caaers.webservice.Study studyDto){
		logger.info("Entering createStudy() in StudyProcessorImpl");
		
		DomainObjectImportOutcome<Study> studyImportOutcome = null;
		Study study = new Study();
		
		//Convert JAXB StudyType to Domain Study
		try{
			studyConverter.convertStudyDtoToStudyDomain(studyDto, study);
			logger.info("StudyDto converted to Study");
		}catch(CaaersSystemException caEX){
			studyImportOutcome = new DomainObjectImportOutcome<Study>();
			logger.error("StudyDto to StudyDomain Conversion Failed " , caEX);
			studyImportOutcome.addErrorMessage("StudyDto to StudyDomain Conversion Failed " , DomainObjectImportOutcome.Severity.ERROR);
		}
		
		if(studyImportOutcome == null){
			studyImportOutcome = studyImportService.importStudy(study);
			if(studyImportOutcome.isSavable()){
				//Check if Study Exists; If Exists then update
				Study dbStudy = fetchStudy(studyImportOutcome.getImportedDomainObject());
				if(dbStudy != null){
					logger.info("Study with Long Title -- "+ dbStudy.getLongTitle() + " -- Exists in caAERS trying to Update");
					studySynchronizer.migrate(dbStudy, studyImportOutcome.getImportedDomainObject(), studyImportOutcome);
					studyImportOutcome.setImportedDomainObject(dbStudy);
					logger.info("Study "+ dbStudy.getLongTitle() + " in caAERS Updated");
				}else{
					logger.info("New Study with Long Title -- "+ studyImportOutcome.getImportedDomainObject().getLongTitle() + " -- being Created");
				}
			}
		}
		logger.info("Leaving createStudy() in StudyProcessorImpl");
		return studyImportOutcome;
	}
	
	public void createStudy(gov.nih.nci.cabig.caaers.webservice.Study studyDto) {
		DomainObjectImportOutcome<Study> studyImportOutcome = null;
		Study study = new Study();
		
		//Convert JAXB StudyType to Domain Study
		try{
			studyConverter.convertStudyDtoToStudyDomain(studyDto, study);
			logger.info("StudyDto converted to Study");
		}catch(CaaersSystemException caEX){
			studyImportOutcome = new DomainObjectImportOutcome<Study>();
			logger.error("StudyDto to StudyDomain Conversion Failed " , caEX);
			studyImportOutcome.addErrorMessage("StudyDto to StudyDomain Conversion Failed " , DomainObjectImportOutcome.Severity.ERROR);
		}
		
		if(studyImportOutcome == null){
			studyImportOutcome = studyImportService.importStudy(study);
			//Check if Study Exists
			Study dbStudy = fetchStudy(studyImportOutcome.getImportedDomainObject());
			if(dbStudy != null){
				studyImportOutcome.addErrorMessage(study.getClass().getSimpleName() + " identifier already exists. ", Severity.ERROR);
			}
			if(studyImportOutcome.isSavable()){
				studyDao.save(studyImportOutcome.getImportedDomainObject());
			}
		}
		
		logger.info("Leaving createStudy() in StudyProcessorImpl");

	}

	public void updateStudy(gov.nih.nci.cabig.caaers.webservice.Study studyDto) {
		DomainObjectImportOutcome<Study> studyImportOutcome = null;
		Study study = new Study();
		
		//Convert JAXB StudyType to Domain Study
		try{
			studyConverter.convertStudyDtoToStudyDomain(studyDto, study);
			logger.info("StudyDto converted to Study");
		}catch(CaaersSystemException caEX){
			studyImportOutcome = new DomainObjectImportOutcome<Study>();
			logger.error("StudyDto to StudyDomain Conversion Failed " , caEX);
			studyImportOutcome.addErrorMessage("StudyDto to StudyDomain Conversion Failed " , DomainObjectImportOutcome.Severity.ERROR);
		}
		
		if(studyImportOutcome == null){
			studyImportOutcome = studyImportService.importStudy(study);
			if(studyImportOutcome.isSavable()){
				//Check if Study Exists
				Study dbStudy = fetchStudy(studyImportOutcome.getImportedDomainObject());
				if(dbStudy != null){
					studySynchronizer.migrate(dbStudy, studyImportOutcome.getImportedDomainObject(), studyImportOutcome);
					studyImportOutcome.setImportedDomainObject(dbStudy);
					studyDao.save(studyImportOutcome.getImportedDomainObject());
				}
			}
		}
		logger.info("Leaving updateStudy() in StudyProcessor");
	}
	
	/**
	 * This method fetches a Study from the DB based identifiers.
	 * @param importedStudy
	 * @return
	 */
	private Study fetchStudy(Study importedStudy){
		Study dbStudy = null;
		for (Identifier identifier : importedStudy.getIdentifiers()) {
            dbStudy = studyDao.getStudyDesignByIdentifier(identifier);
            studyDao.evict(dbStudy);
            if(dbStudy != null){
            	break;
            }
        }
		return dbStudy;
	}

}
