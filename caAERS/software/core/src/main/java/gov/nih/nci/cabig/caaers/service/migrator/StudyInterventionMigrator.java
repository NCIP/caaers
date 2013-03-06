/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
        if(src.getOtherInterventions() == null || src.getOtherInterventions().isEmpty()) return;
        for(OtherIntervention oi : src.getOtherInterventions()){
            dest.addOtherIntervention(oi);
        }
    }

}
