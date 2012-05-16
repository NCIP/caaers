package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.Device;
import gov.nih.nci.cabig.caaers.domain.PriorTherapy;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

import java.util.Date;

/**
 *@author Biju Joseph
 */
public class PriorTherapyMigrator implements Migrator<PriorTherapy> {
    public void migrate(PriorTherapy src, PriorTherapy dest, DomainObjectImportOutcome<PriorTherapy> priorTherapyDomainObjectImportOutcome) {
        dest.setMeddraCode(src.getMeddraCode());
        dest.setLastSynchedDate(new Date());
        dest.setMeddraTerm(src.getMeddraTerm());
        dest.setText(src.getText());
        dest.setTherapyType(src.getTherapyType());
        dest.setRetiredIndicator(src.getRetiredIndicator());
    }
}
