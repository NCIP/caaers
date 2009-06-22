package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

import org.springframework.beans.BeanUtils;

public class TreatmentAssignmentMigrator implements Migrator<gov.nih.nci.cabig.caaers.domain.Study> {
	
	/**
	 * Will migrate {@link TreatmentAssignment}s from source to destination
	 */
	public void migrate(Study source, Study destination, DomainObjectImportOutcome<Study> outcome) {

		//TreatmentAssignments
        if (source.getTreatmentAssignments() != null) {
            for (TreatmentAssignment treatmentAssignment : source.getTreatmentAssignments()) {
                TreatmentAssignment target = new TreatmentAssignment();
                BeanUtils.copyProperties(treatmentAssignment, target, new String[]{"study"});
                destination.addTreatmentAssignment(target);
            }
        }
	}
	
}
