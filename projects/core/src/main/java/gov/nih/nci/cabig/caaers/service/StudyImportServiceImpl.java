package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.migrator.StudyMigrator;

import org.springframework.beans.factory.annotation.Required;

/**
 * @author Biju Joseph
 */
public class StudyImportServiceImpl{
	
	private StudyMigrator studyMigrator;
	private StudyDao studyDao;
	
    /**
     * Given a study object which has been serialized from an xml format is recreated here and made ready to be saved.
     *
     * @param xstreamStudy - The serialized xml stream
     * @return An outcome object containing the {@link Study
     */
    public DomainObjectImportOutcome<Study> importStudy(Study xstreamStudy) {
        Study study = new Study();
        
        DomainObjectImportOutcome<Study> studyImportOutcome = new DomainObjectImportOutcome<Study>();
        studyMigrator.migrate(xstreamStudy, study, studyImportOutcome);
        studyImportOutcome.setImportedDomainObject(study);
        studyUniquenessCheck(study, studyImportOutcome, DomainObjectImportOutcome.Severity.ERROR);

        return studyImportOutcome;
    }
    
    private void studyUniquenessCheck(Study study, DomainObjectImportOutcome<Study> studyImportOutcome, DomainObjectImportOutcome.Severity severity) {
    	
        for (Identifier identifier : study.getIdentifiers()) {
            Study tempStudy = studyDao.getByIdentifier(identifier);
            if (tempStudy != null) {
                studyImportOutcome.addErrorMessage(study.getClass().getSimpleName() + " identifier already exists. ", severity);
                break;
            }
        }
    }
    
    @Required
    public void setStudyMigrator(StudyMigrator studyMigrator) {
		this.studyMigrator = studyMigrator;
	}
    
    @Required
    public void setStudyDao(StudyDao studyDao) {
		this.studyDao = studyDao;
	}

}
