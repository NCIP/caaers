package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.MeddraVersionDao;
import gov.nih.nci.cabig.caaers.domain.AeTerminology;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.MeddraVersion;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

public class StudyTerminologyMigrator implements Migrator<Study> {
	
	private MeddraVersionDao meddraVersionDao;
    private CtcDao ctcDao;
    
	/**
	 * Will copy the study terminology from source to destination
	 * @see {@link CtcTerm}
	 */
	public void migrate(Study source, Study destination, DomainObjectImportOutcome<Study> outcome) {
		AeTerminology srcAeTerminology = source.getAeTerminology();
		
        // AeTerminology and Version
        if (srcAeTerminology != null) {
        	
            if (srcAeTerminology.getCtcVersion() != null) {
            	AeTerminology aeTerminology = destination.getAeTerminology();
            	int ctcVersionId = Integer.parseInt(srcAeTerminology.getCtcVersion().getName());
                Ctc ctc = ctcDao.getById(ctcVersionId);
                aeTerminology.setTerm(Term.CTC);
                aeTerminology.setCtcVersion(ctc);
                outcome.ifNullObject(ctc, DomainObjectImportOutcome.Severity.ERROR, "CTC is either Empty or Not Valid");
                
            	if(source.getOtherMeddra() != null){
                	MeddraVersion otherMeddraVersion = meddraVersionDao.getMeddraByName(source.getOtherMeddra().getName()).get(0);
                	destination.setOtherMeddra(otherMeddraVersion);
                	outcome.ifNullObject(otherMeddraVersion, DomainObjectImportOutcome.Severity.ERROR, "otherMeddraVersion is either Empty or Not Valid");
                }
            }
            
            if (srcAeTerminology.getMeddraVersion() != null) {
            	AeTerminology aeTerminology = destination.getAeTerminology();
            	List<MeddraVersion> mvs = meddraVersionDao.getMeddraByName(srcAeTerminology.getMeddraVersion().getName());
            	MeddraVersion meddraVersion = null;
            	if(mvs != null && !mvs.isEmpty()){
            		meddraVersion = meddraVersionDao.getMeddraByName(srcAeTerminology.getMeddraVersion().getName()).get(0);
            	}
                aeTerminology.setTerm(Term.MEDDRA);
                aeTerminology.setMeddraVersion(meddraVersion);
                outcome.ifNullObject(meddraVersion, DomainObjectImportOutcome.Severity.ERROR, "MedDRA Version is either Empty or Not Valid");
            }
        }
        
        if(srcAeTerminology.getCtcVersion() == null && srcAeTerminology.getMeddraVersion() == null){
        	outcome.addErrorMessage("AeTerminology is either Empty or Not Valid", DomainObjectImportOutcome.Severity.ERROR);
        }

	}
	
	///BEAN PROPERTIES
	
	@Required
    public void setCtcDao(final CtcDao ctcDao) {
        this.ctcDao = ctcDao;
    }
	
	@Required
    public void setMeddraVersionDao(final MeddraVersionDao meddraVersionDao) {
        this.meddraVersionDao = meddraVersionDao;
    }

}
