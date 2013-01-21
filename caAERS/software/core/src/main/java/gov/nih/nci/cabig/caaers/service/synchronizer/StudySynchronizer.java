package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.MeddraVersionDao;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.DiseaseCodeTerm;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerminology;
import gov.nih.nci.cabig.caaers.domain.MeddraVersion;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.integration.schema.study.DiseaseCodeType;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.CompositeMigrator;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author Monish Dombla
 *
 */
public class StudySynchronizer extends CompositeMigrator<Study>{
	
	private CtcDao ctcDao;
	private MeddraVersionDao meddraVersionDao;
	
	public void setCtcDao(CtcDao ctcDao) {
		this.ctcDao = ctcDao;
	}
	
	public void setMeddraVersionDao(MeddraVersionDao meddraVersionDao) {
		this.meddraVersionDao = meddraVersionDao;
	}
	
	 private void populateAeTerminology(Study dbStudy,Study xmlStudy,
				DomainObjectImportOutcome<Study> outcome) {

			if(xmlStudy.getAeTerminology() != null){

				if(xmlStudy.getAeTerminology().getCtcVersion() != null && xmlStudy.getAeTerminology().getCtcVersion().getName() != null){
					Ctc ctcVersion =ctcDao.getByName(xmlStudy.getAeTerminology().getCtcVersion().getName());
					if(ctcVersion != null){
						dbStudy.getAeTerminology().setCtcVersion(ctcVersion);
					}
				}
				if (xmlStudy.getAeTerminology().getMeddraVersion() != null && xmlStudy.getAeTerminology().getMeddraVersion().getName() != null) {
					List<MeddraVersion> meddraVersions = meddraVersionDao.getMeddraByName(xmlStudy.getAeTerminology().getMeddraVersion().getName());
					if (meddraVersions != null && !meddraVersions.isEmpty()){
						dbStudy.getAeTerminology().setMeddraVersion(meddraVersions.get(0));
					}
	            }
			}

		}


	@Override
	/**
	 * Will sync the basic properties of the source Study to destination Study.
	 */
	public void preMigrate(Study dbStudy, Study xmlStudy,
		DomainObjectImportOutcome<Study> outcome) {
		
		dbStudy.setShortTitle(StringUtils.isNotEmpty(xmlStudy.getShortTitle()) ? xmlStudy.getShortTitle() : "NA");
        dbStudy.setAeTermUnique(xmlStudy.getAeTermUnique());
        dbStudy.setVerbatimFirst(xmlStudy.getVerbatimFirst());
        dbStudy.setParticipationType(xmlStudy.getParticipationType());
        dbStudy.setStudyPurpose(xmlStudy.getStudyPurpose());
        dbStudy.setPhaseCode(xmlStudy.getPhaseCode());
        dbStudy.setLastSynchedDate(new Date());
        
        populateAeTerminology(dbStudy, xmlStudy, outcome);
        
        populateDiseaseTerminology(dbStudy, xmlStudy, outcome);

		dbStudy.setLongTitle(xmlStudy.getLongTitle());
		if(xmlStudy.getDescription() != null &&  StringUtils.isNotEmpty(xmlStudy.getDescription())){
			dbStudy.setDescription(xmlStudy.getDescription());
		}
		if(xmlStudy.getPrecis() != null &&  StringUtils.isNotEmpty(xmlStudy.getPrecis())){
			dbStudy.setPrecis(xmlStudy.getPrecis());
		}

		dbStudy.setStatus(xmlStudy.getStatus());
		dbStudy.setMultiInstitutionIndicator(xmlStudy.getMultiInstitutionIndicator());
		if(xmlStudy.getDesign() != null){
			dbStudy.setDesign(xmlStudy.getDesign());
		}

	}
	
	
	private void populateDiseaseTerminology(Study dbStudy,Study xmlStudy,
			DomainObjectImportOutcome<Study> outcome) {

		if(xmlStudy.getDiseaseTerminology() != null){
			DiseaseTerminology diseaseTerminology = dbStudy.getDiseaseTerminology();

			if(DiseaseCodeType.CTEP.equals(xmlStudy.getDiseaseTerminology().getDiseaseCodeTerm())){
				diseaseTerminology.setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
			}
			if(DiseaseCodeType.MEDDRA.equals(xmlStudy.getDiseaseTerminology().getDiseaseCodeTerm())){
				diseaseTerminology.setDiseaseCodeTerm(DiseaseCodeTerm.MEDDRA);
			}
		}

	}
}
