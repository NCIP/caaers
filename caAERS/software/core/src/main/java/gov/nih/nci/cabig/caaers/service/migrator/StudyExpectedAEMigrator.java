package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.ExpectedAECtcTerm;
import gov.nih.nci.cabig.caaers.domain.ExpectedAEMeddraLowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

import java.util.List;

public class StudyExpectedAEMigrator implements Migrator<Study> {
	
	private CtcTermDao ctcTermDao;
	private LowLevelTermDao lowLevelTermDao;
	
	public void migrate(Study src, Study dest,
			DomainObjectImportOutcome<Study> outcome) {

		List<ExpectedAEMeddraLowLevelTerm> expectedAEMeddraTerms = src.getExpectedAEMeddraLowLevelTerms();
		if(expectedAEMeddraTerms != null && !expectedAEMeddraTerms.isEmpty()){
			ExpectedAEMeddraLowLevelTerm domainExpectedAEMeddraLowLevelTerm = null;
			for(ExpectedAEMeddraLowLevelTerm xmlExpectedAEMeddraLowLevelTerm : expectedAEMeddraTerms){
				List<LowLevelTerm> lowLevelTerms = lowLevelTermDao.getByMeddraCodeandVersion(xmlExpectedAEMeddraLowLevelTerm.getTerm().getMeddraCode(), dest.getAeTerminology().getMeddraVersion().getId());
				if(lowLevelTerms != null && !lowLevelTerms.isEmpty()){
					domainExpectedAEMeddraLowLevelTerm = new ExpectedAEMeddraLowLevelTerm();
					domainExpectedAEMeddraLowLevelTerm.setLowLevelTerm(lowLevelTerms.get(0));
					dest.getExpectedAEMeddraLowLevelTerms().add(domainExpectedAEMeddraLowLevelTerm);
				}
			}
		}else{
			List<ExpectedAECtcTerm> expectedAECTCTerms = src.getExpectedAECtcTerms();
			if(expectedAECTCTerms != null && !expectedAECTCTerms.isEmpty()){
				ExpectedAECtcTerm domainExpectedAECtcTerm = null;
				for(ExpectedAECtcTerm xmlExpectedAECtcTerm : expectedAECTCTerms){
					
					if(xmlExpectedAECtcTerm.getOtherMeddraTerm() != null && xmlExpectedAECtcTerm.getCtcTerm() == null){
						outcome.addErrorMessage("ExpectedAECtcTerm cannot contain only otherMeddraCode", DomainObjectImportOutcome.Severity.ERROR);
						break;
					}
					if(xmlExpectedAECtcTerm.getOtherMeddraTerm() != null && dest.getOtherMeddra() == null){
						outcome.addErrorMessage("otherMeddra is either Empty or InValid", DomainObjectImportOutcome.Severity.ERROR);
						break;
					}
					
					if(xmlExpectedAECtcTerm.getCtcTerm() != null){
						List<CtcTerm> ctcTerms = ctcTermDao.getByCtepCodeandVersion(xmlExpectedAECtcTerm.getCtcTerm().getCtepCode(), dest.getAeTerminology().getCtcVersion().getId());
						if(ctcTerms != null && !ctcTerms.isEmpty()){
							domainExpectedAECtcTerm = new ExpectedAECtcTerm();
							domainExpectedAECtcTerm.setCtcTerm(ctcTerms.get(0));
						}
					}
					if(xmlExpectedAECtcTerm.getOtherMeddraTerm() != null){
						List<LowLevelTerm> lowLevelTerms = lowLevelTermDao.getByMeddraCodeandVersion(xmlExpectedAECtcTerm.getOtherMeddraTerm().getMeddraCode(), dest.getOtherMeddra().getId());
						if(lowLevelTerms != null && !lowLevelTerms.isEmpty()){
							domainExpectedAECtcTerm.setOtherMeddraTerm(lowLevelTerms.get(0));
						}
					}
					if(domainExpectedAECtcTerm != null){
						dest.getExpectedAECtcTerms().add(domainExpectedAECtcTerm);
					}
				}
			}
		}
	}

	public void setCtcTermDao(CtcTermDao ctcTermDao) {
		this.ctcTermDao = ctcTermDao;
	}

	public void setLowLevelTermDao(LowLevelTermDao lowLevelTermDao) {
		this.lowLevelTermDao = lowLevelTermDao;
	}
	
	
}
