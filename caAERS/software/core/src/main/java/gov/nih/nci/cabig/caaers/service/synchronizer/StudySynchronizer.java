package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.CompositeMigrator;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author Monish Dombla
 *
 */
public class StudySynchronizer extends CompositeMigrator<Study>{
	

	@Override
	/**
	 * Will sync the basic properties of the source Study to destination Study.
	 */
	public void preMigrate(Study dbStudy, Study xmlStudy,
		DomainObjectImportOutcome<Study> outcome) {
		
		dbStudy.setShortTitle(StringUtils.isNotEmpty(xmlStudy.getShortTitle()) ? xmlStudy.getShortTitle() : "NA");
		dbStudy.setLongTitle(xmlStudy.getLongTitle());
		if(xmlStudy.getDescription() != null &&  StringUtils.isNotEmpty(xmlStudy.getDescription())){
			dbStudy.setDescription(xmlStudy.getDescription());
		}
		if(xmlStudy.getPrecis() != null &&  StringUtils.isNotEmpty(xmlStudy.getPrecis())){
			dbStudy.setPrecis(xmlStudy.getPrecis());
		}
		dbStudy.setPhaseCode(xmlStudy.getPhaseCode());
		dbStudy.setStatus(xmlStudy.getStatus());
		dbStudy.setMultiInstitutionIndicator(xmlStudy.getMultiInstitutionIndicator());
		dbStudy.setAdeersReporting(xmlStudy.getAdeersReporting());
		if(xmlStudy.getDesign() != null){
			dbStudy.setDesign(xmlStudy.getDesign());
		}
	}
}
