package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.dao.MeddraVersionDao;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerminology;
import gov.nih.nci.cabig.caaers.domain.MeddraVersion;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.webservice.DiseaseCodeType;

public class StudyDiseaseTerminologyMigrator implements Migrator<Study> {
	
	private MeddraVersionDao meddraVersionDao;
	
	/**
	 */
	public void migrate(Study src, Study dest,
			DomainObjectImportOutcome<Study> outcome) {
		
		
		if(src.getDiseaseTerminology() != null){
			DiseaseTerminology diseaseTerminology = src.getDiseaseTerminology();
			
			if(DiseaseCodeType.CTEP.equals(src.getDiseaseTerminology().getDiseaseCodeTerm())){
				dest.setDiseaseTerminology(diseaseTerminology);
			}
			if(DiseaseCodeType.MEDDRA.equals(src.getDiseaseTerminology().getDiseaseCodeTerm())){
				
				outcome.ifNullObject(src.getDiseaseTerminology().getMeddraVersion(), DomainObjectImportOutcome.Severity.ERROR, "MedDRA Version is either Empty or Not Valid");
				if(src.getDiseaseTerminology().getMeddraVersion() != null){
					int meddraVersionId = Integer.parseInt(src.getDiseaseTerminology().getMeddraVersion().getName());
			        MeddraVersion meddraVersion = meddraVersionDao.getById(meddraVersionId);
			        outcome.ifNullObject(meddraVersion, DomainObjectImportOutcome.Severity.ERROR, "MedDRA Version is either Empty or Not Valid");
					diseaseTerminology.setMeddraVersion(meddraVersion);
				}
				dest.setDiseaseTerminology(diseaseTerminology);
			}
		}
		
		outcome.ifNullObject(dest.getDiseaseTerminology(), DomainObjectImportOutcome.Severity.ERROR, 
		"DiseaseTerminology is either Empty or Not Valid");
		if (dest.getDiseaseTerminology() != null) {
			dest.getDiseaseTerminology().setStudy(dest);
			outcome.ifNullObject(dest.getDiseaseTerminology().getDiseaseCodeTerm(), DomainObjectImportOutcome.Severity.ERROR, "Disease Code Term is either Empty or Not Valid");
		}
		
	}

	public MeddraVersionDao getMeddraVersionDao() {
		return meddraVersionDao;
	}

	public void setMeddraVersionDao(MeddraVersionDao meddraVersionDao) {
		this.meddraVersionDao = meddraVersionDao;
	}
}
