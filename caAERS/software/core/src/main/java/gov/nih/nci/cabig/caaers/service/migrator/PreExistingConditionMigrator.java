/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;
import gov.nih.nci.cabig.caaers.domain.PriorTherapy;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

import java.util.Date;

/**
 * @author Biju Joseph
 */
public class PreExistingConditionMigrator implements Migrator<PreExistingCondition>  {
    public void migrate(PreExistingCondition src, PreExistingCondition dest, DomainObjectImportOutcome<PreExistingCondition> preExistingConditionDomainObjectImportOutcome) {
        dest.setMeddraHlgt(src.getMeddraHlgt());
        dest.setMeddraLlt(src.getMeddraLlt());
        dest.setMeddraLltCode(src.getMeddraLltCode());
        dest.setText(src.getText());
        dest.setLastSynchedDate(new Date());
        dest.setRetiredIndicator(src.getRetiredIndicator());
    }
}
