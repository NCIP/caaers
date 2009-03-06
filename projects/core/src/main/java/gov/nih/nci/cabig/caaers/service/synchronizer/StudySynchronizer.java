package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.CompositeMigrator;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author Monish Dombla
 *
 */
public class StudySynchronizer extends CompositeMigrator<Study>{

	public StudySynchronizer(List<Migrator<Study>> synchronizers) {
		super(synchronizers);
	}

	@Override
	/**
	 * Will sync the basic properties of the source Study to destination Study.
	 */
	public void preMigrate(Study dbStudy, Study xmlStudy,
		DomainObjectImportOutcome<Study> outcome) {
		
		dbStudy.setShortTitle(StringUtils.isNotEmpty(xmlStudy.getShortTitle()) ? xmlStudy.getShortTitle() : "NA");
		dbStudy.setLongTitle(xmlStudy.getLongTitle());
		dbStudy.setDescription(xmlStudy.getDescription());
		dbStudy.setPrecis(xmlStudy.getPrecis());
		dbStudy.setPhaseCode(xmlStudy.getPhaseCode());
		dbStudy.setStatus(xmlStudy.getStatus());
		dbStudy.setMultiInstitutionIndicator(xmlStudy.getMultiInstitutionIndicator());
		dbStudy.setAdeersReporting(xmlStudy.getAdeersReporting());
		dbStudy.setDesign(xmlStudy.getDesign());

		dbStudy.setDrugAdministrationTherapyType(xmlStudy.getDrugAdministrationTherapyType());
		dbStudy.setRadiationTherapyType(xmlStudy.getRadiationTherapyType());
		dbStudy.setDeviceTherapyType(xmlStudy.getDeviceTherapyType());
		dbStudy.setSurgeryTherapyType(xmlStudy.getSurgeryTherapyType());
		dbStudy.setBehavioralTherapyType(xmlStudy.getBehavioralTherapyType());
	}
}
