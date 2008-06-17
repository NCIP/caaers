package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.StudyImportServiceImpl;
import gov.nih.nci.cabig.caaers.service.synchronizer.StudySynchronizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class is intended save/update or delete studies.
 * 
 * @author Monish Dombla
 *
 */
public class StudyProcessor{
	
	private static Log logger = LogFactory.getLog(StudyProcessor.class);
	
	//Injected through spring
	private StudyImportServiceImpl studyImportService;
	private StudyDao studyDao;
	private StudyConverter studyConverter;
	private StudySynchronizer studySynchronizer;
	
	public void setStudyImportService(final StudyImportServiceImpl studyImportService) {
        this.studyImportService = studyImportService;
    }
	
	public void setStudyDao(StudyDao studyDao){
		this.studyDao = studyDao;
	}
	
	public void setStudyConverter(StudyConverter studyConverter){
		this.studyConverter = studyConverter;
	}
	
	public StudySynchronizer getStudySynchronizer() {
		return studySynchronizer;
	}

	public void setStudySynchronizer(StudySynchronizer studySynchronizer) {
		this.studySynchronizer = studySynchronizer;
	}
	
	public DomainObjectImportOutcome<Study> syncStudy(gov.nih.nci.cabig.caaers.webservice.Study studyDto) {
		logger.info("Entering createStudy() in StudyProcessor");
		
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
				}
				studyDao.save(studyImportOutcome.getImportedDomainObject());
			}
		}else{
			//TODO parse studyImportOutcome for errormessages and 
			//propogate appropriate error message to the caller 
		}
		
		logger.info("Leaving createStudy() in StudyProcessor");
		return studyImportOutcome;
	}
	
	public DomainObjectImportOutcome<Study> updateStudy(Study study){
		logger.info("Entering updateStudy() in StudyProcessor");
		// TODO
		//Call needs to be made Study Object Graph walker to get the id's
		//Sync with 
		logger.info("Leaving updateStudy() in StudyProcessor");
		return null;
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
