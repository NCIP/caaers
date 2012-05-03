package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.OtherIntervention;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Ion C. Olaru
 *         Date: 5/3/12 -10:18 AM
 */
public class StudyInterventionMigrator implements Migrator<Study> {

    public void migrate(Study src, Study dest, DomainObjectImportOutcome<Study> studyDomainObjectImportOutcome) {
        List<OtherIntervention> otherInterventions = src.getOtherInterventions();
        if (CollectionUtils.isEmpty(otherInterventions)) return;
        Set<String> destInterventionsSet = new HashSet<String>();

        for (OtherIntervention otherIntervention : dest.getOtherInterventions()) {
            destInterventionsSet.add(otherIntervention.getHashKey());
        }

        for (OtherIntervention otherIntervention : otherInterventions) {
            if (destInterventionsSet.add(otherIntervention.getHashKey())) {
                dest.getOtherInterventions().add(otherIntervention);
            }
        }
    }

}
