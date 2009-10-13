package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

import java.util.List;

import org.apache.commons.lang.StringUtils;
public class StudyMigrator extends CompositeMigrator<Study> {
	
	
	@Override
	/**
	 * Will copy the basic properties of the source Study to destination Study.
	 */
	public void preMigrate(Study src, Study dest,DomainObjectImportOutcome<Study> outcome) {
        dest.setShortTitle(StringUtils.isNotEmpty(src.getShortTitle()) ? src.getShortTitle() : "NA");
        dest.setLongTitle(src.getLongTitle());
        dest.setDescription(src.getDescription());
        dest.setPrecis(src.getPrecis());
        dest.setPhaseCode(src.getPhaseCode());
        dest.setStatus(src.getStatus());
        dest.setMultiInstitutionIndicator(src.getMultiInstitutionIndicator());
        dest.setAdeersReporting(src.getAdeersReporting());
        dest.setDesign(src.getDesign());

	}
}
