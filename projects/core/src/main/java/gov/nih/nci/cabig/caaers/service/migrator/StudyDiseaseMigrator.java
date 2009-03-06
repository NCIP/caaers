package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.dao.DiseaseTermDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.CtepStudyDisease;
import gov.nih.nci.cabig.caaers.domain.DiseaseCodeTerm;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerm;
import gov.nih.nci.cabig.caaers.domain.MeddraStudyDisease;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

import java.util.List;

public class StudyDiseaseMigrator implements Migrator<gov.nih.nci.cabig.caaers.domain.Study> {
	
    private DiseaseTermDao diseaseTermDao;
    private LowLevelTermDao lowLevelTermDao;

    /**
	 * Will migrate {@link CtepStudyDisease} and {@link MeddraStudyDisease} from source to destination
	 * @param source
	 * @param destination
	 * @param outcome
	 */
	public void migrate(Study source, Study destination, DomainObjectImportOutcome<Study> outcome) {
		

        //CtepStudyDiseases
        if (source.getCtepStudyDiseases() != null && source.getCtepStudyDiseases().size() > 0) {

            if (destination.getDiseaseTerminology().getDiseaseCodeTerm() == DiseaseCodeTerm.CTEP) {

                for (CtepStudyDisease ctepStudyDisease : source.getCtepStudyDiseases()) {
                    CtepStudyDisease destinationCtepStudyDisease = new CtepStudyDisease();
                    DiseaseTerm diseaseTerm = null;
                    String term = null;
                    if (ctepStudyDisease.getTerm() != null && ctepStudyDisease.getTerm().getTerm() != null) {
                        term = ctepStudyDisease.getTerm().getTerm();
                        diseaseTerm = diseaseTermDao.getByTermName(ctepStudyDisease.getTerm().getTerm());
                    }
                    if (ctepStudyDisease.getTerm() != null && diseaseTerm == null && ctepStudyDisease.getTerm().getMedraCode() != null) {
                        term = ctepStudyDisease.getTerm().getMedraCode();
                        diseaseTerm = diseaseTermDao.getByMeddra(ctepStudyDisease.getTerm().getMedraCode());
                    }

                    outcome.ifNullObject(diseaseTerm, DomainObjectImportOutcome.Severity.ERROR, "The selected disease Term " +
                            term + " is not Valid ");

                    destinationCtepStudyDisease.setTerm(diseaseTerm);
                    destinationCtepStudyDisease.setLeadDisease(ctepStudyDisease.getLeadDisease() == null ? Boolean.FALSE : ctepStudyDisease.getLeadDisease());
                    destination.addCtepStudyDisease(destinationCtepStudyDisease);
                }
            } else {
                outcome.ifNullObject(null, DomainObjectImportOutcome.Severity.ERROR, " Selected terminology is not CTEP ");
            }
        }
        

        //MeddraStudyDiseases
        if (source.getMeddraStudyDiseases() != null && source.getMeddraStudyDiseases().size() > 0) {
            if (destination.getDiseaseTerminology().getDiseaseCodeTerm() == DiseaseCodeTerm.MEDDRA) {
                for (MeddraStudyDisease meddraStudyDisease : source.getMeddraStudyDiseases()) {
                    MeddraStudyDisease destinationMeddraStudyDisease = new MeddraStudyDisease();
                    LowLevelTerm lowLevelTerm = null;
                    if (meddraStudyDisease.getMeddraCode() != null) {
                    	List<LowLevelTerm> termsList = lowLevelTermDao.getByMeddraCode(meddraStudyDisease.getMeddraCode());
                    	lowLevelTerm = (termsList.size() > 0) ? termsList.get(0) : null;
                    }

                    outcome.ifNullObject(lowLevelTerm, DomainObjectImportOutcome.Severity.ERROR, "The selected MedDRA code " +
                            meddraStudyDisease.getMeddraCode() + " is not Valid ");

                    destinationMeddraStudyDisease.setTerm(lowLevelTerm == null ? null : lowLevelTerm);
                    destinationMeddraStudyDisease.setMeddraCode(meddraStudyDisease.getMeddraCode());
                    destination.addMeddraStudyDisease(destinationMeddraStudyDisease);
                }
            } else {
                outcome.ifNullObject(null, DomainObjectImportOutcome.Severity.ERROR, " Selected terminology is not MedDRA ");
            }
        }
		
	}

	public void setDiseaseTermDao(DiseaseTermDao diseaseTermDao) {
		this.diseaseTermDao = diseaseTermDao;
	}

	public void setLowLevelTermDao(LowLevelTermDao lowLevelTermDao) {
		this.lowLevelTermDao = lowLevelTermDao;
	}
	
	
}
