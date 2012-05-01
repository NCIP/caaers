package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.Device;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

import java.util.Date;

/**
 * @author Ion C. Olaru
 */
public class DeviceMigrator implements Migrator<Device>{

	public void migrate(Device src, Device dest, DomainObjectImportOutcome<Device> outcome) {
		dest.setCommonName(src.getCommonName());
        dest.setBrandName(src.getBrandName());
        dest.setType(src.getType());
        dest.setRetiredIndicator(src.getRetiredIndicator());
        dest.setLastSynchedDate(new Date());
        dest.setCtepDbIdentifier(src.getCtepDbIdentifier());
        dest.setLastSynchedDate(new Date());
	}
}
