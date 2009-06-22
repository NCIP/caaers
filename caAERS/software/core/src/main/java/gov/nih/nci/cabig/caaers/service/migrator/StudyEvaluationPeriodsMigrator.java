package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.Arm;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Epoch;
import gov.nih.nci.cabig.caaers.domain.SolicitedAdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

import java.util.List;

public class StudyEvaluationPeriodsMigrator implements Migrator<Study> {
	
	private CtcTermDao ctcTermDao;
	private LowLevelTermDao lowLevelTermDao;
	
	public void migrate(Study src, Study dest,
			DomainObjectImportOutcome<Study> outcome) {
		
		boolean isBaseLineEpochPresent = false;
		boolean onlyOtherMeddraTermPresent = false; 
		
		List<Epoch> xmlEpochs = src.getEpochs();
		if(xmlEpochs != null && !xmlEpochs.isEmpty()){
			Epoch domainEpoch = null;
			for(Epoch xmlEpoch : xmlEpochs){
				if("Baseline".equals(xmlEpoch.getName())){
					isBaseLineEpochPresent = true;
				}
				domainEpoch = new Epoch();
				domainEpoch.setName(xmlEpoch.getName());
				domainEpoch.setDescriptionText(xmlEpoch.getDescriptionText());
				domainEpoch.setEpochOrder(xmlEpoch.getEpochOrder());
				if(xmlEpoch.getArms() != null && !xmlEpoch.getArms().isEmpty()){
					Arm domainArm = null;
					for(Arm xmlArm : xmlEpoch.getArms()){
						domainArm = new Arm();
						domainArm.setName(xmlArm.getName());
						domainArm.setDescriptionText(xmlArm.getDescriptionText());
						if(xmlArm.getSolicitedAdverseEvents() != null && !xmlArm.getSolicitedAdverseEvents().isEmpty()){
							SolicitedAdverseEvent domainSolicitedAdverseEvent = null;
							for(SolicitedAdverseEvent xmlSolicitedAdverseEvent : xmlArm.getSolicitedAdverseEvents()){
								if(xmlSolicitedAdverseEvent.getOtherTerm() != null){
									if(xmlSolicitedAdverseEvent.getLowLevelTerm() == null && xmlSolicitedAdverseEvent.getCtcterm() == null){
										onlyOtherMeddraTermPresent = true;
										break;
									}
								}
								domainSolicitedAdverseEvent = new SolicitedAdverseEvent();
								if(xmlSolicitedAdverseEvent.getCtcterm() != null && !"".equals(xmlSolicitedAdverseEvent.getCtcterm().getCtepCode())){
									if(dest.getAeTerminology().getCtcVersion() != null){
										List<CtcTerm> ctcTerms = ctcTermDao.getByCtepCodeandVersion(xmlSolicitedAdverseEvent.getCtcterm().getCtepCode(), dest.getAeTerminology().getCtcVersion().getId());
										if(ctcTerms != null && !ctcTerms.isEmpty()){
											domainSolicitedAdverseEvent.setCtcterm(ctcTerms.get(0));
										}
									}
								}
								if(xmlSolicitedAdverseEvent.getLowLevelTerm() != null && !"".equals(xmlSolicitedAdverseEvent.getLowLevelTerm().getMeddraCode())){
									if(dest.getAeTerminology().getMeddraVersion() != null){
										List<LowLevelTerm> lowLevelTerms = lowLevelTermDao.getByMeddraCodeandVersion(xmlSolicitedAdverseEvent.getLowLevelTerm().getMeddraCode(), dest.getAeTerminology().getMeddraVersion().getId());
										if(lowLevelTerms != null && !lowLevelTerms.isEmpty()){
											domainSolicitedAdverseEvent.setLowLevelTerm(lowLevelTerms.get(0));
										}
									}
								}
								if(xmlSolicitedAdverseEvent.getOtherTerm() != null && !"".equals(xmlSolicitedAdverseEvent.getOtherTerm().getMeddraCode())){
									if(dest.getOtherMeddra() != null){
										List<LowLevelTerm> lowLevelTerms = lowLevelTermDao.getByMeddraCodeandVersion(xmlSolicitedAdverseEvent.getOtherTerm().getMeddraCode(), dest.getOtherMeddra().getId());
										if(lowLevelTerms != null && !lowLevelTerms.isEmpty()){
											domainSolicitedAdverseEvent.setOtherTerm(lowLevelTerms.get(0));
										}
									}else{
										outcome.addErrorMessage("otherMeddra is either Empty or InValid", DomainObjectImportOutcome.Severity.ERROR);
										break;
									}
								}
								domainArm.getSolicitedAdverseEvents().add(domainSolicitedAdverseEvent);
							}
						}
						domainEpoch.getArms().add(domainArm);
					}
				}
				dest.getEpochs().add(domainEpoch);
			}
		}
	
		
		if(dest.getEpochs()!= null && !dest.getEpochs().isEmpty() && !isBaseLineEpochPresent){
			outcome.addErrorMessage("One EvaluationPeriod with name as \"Baseline\" is mandatory", DomainObjectImportOutcome.Severity.ERROR);
		}
		if(dest.getEpochs()!= null && !dest.getEpochs().isEmpty() && onlyOtherMeddraTermPresent){
			outcome.addErrorMessage("SolicitedAdverseEvent with only otherMeddraCode is not allowed", DomainObjectImportOutcome.Severity.ERROR);
		}
	}

	public void setCtcTermDao(CtcTermDao ctcTermDao) {
		this.ctcTermDao = ctcTermDao;
	}

	public void setLowLevelTermDao(LowLevelTermDao lowLevelTermDao) {
		this.lowLevelTermDao = lowLevelTermDao;
	}
	
}


