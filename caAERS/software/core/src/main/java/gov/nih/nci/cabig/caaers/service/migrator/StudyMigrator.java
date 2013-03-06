/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
public class StudyMigrator extends CompositeMigrator<Study> {
	
	
	@Override
	/**
	 * Will copy the basic properties of the source Study to destination Study.
	 */
	public void preMigrate(Study src, Study dest, DomainObjectImportOutcome<Study> outcome) {
        dest.setShortTitle(StringUtils.isNotEmpty(src.getShortTitle()) ? src.getShortTitle() : "NA");
        dest.setPhaseCode(src.getPhaseCode());
        dest.setAeTermUnique(src.getAeTermUnique() == null ? false : src.getAeTermUnique());
        dest.setVerbatimFirst(src.getVerbatimFirst() == null ? false : src.getVerbatimFirst());
        dest.setStudyPurpose(src.getStudyPurpose());
        dest.setParticipationType(src.getParticipationType());
        dest.setLastSynchedDate(new Date());
	}
}
