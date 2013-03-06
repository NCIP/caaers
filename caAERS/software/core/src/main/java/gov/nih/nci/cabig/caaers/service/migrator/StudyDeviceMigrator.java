/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.Device;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyDevice;
import gov.nih.nci.cabig.caaers.domain.repository.DeviceRepository;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * @author Ion C. Olaru
 *
 * */
public class StudyDeviceMigrator implements Migrator<Study> {

	private DeviceRepository deviceRepository;
    private static Log log = LogFactory.getLog(StudyRepository.class);

	public void migrate(Study src, Study dest, DomainObjectImportOutcome<Study> outcome) {
		List<StudyDevice> l = src.getStudyDevices();
        log.debug("Migrating " + l.size() + " StudyDevices");

        for (StudyDevice sd : l) {
            StudyDevice newSD = new StudyDevice();
            Device d  = null;
            if (sd.getDevice() != null) {
                List<Device> ld = deviceRepository.getByNames(sd.getDevice().getCommonName(), sd.getDevice().getBrandName(), sd.getDevice().getType());
                if (ld.size() > 0) d = ld.get(0);
            }
            newSD.setDevice(d);
            newSD.setStudy(dest);

            newSD.setCatalogNumber(sd.getCatalogNumber());
            newSD.setManufacturerCity(sd.getManufacturerCity());
            newSD.setManufacturerName(sd.getManufacturerName());
            newSD.setManufacturerState(sd.getManufacturerState());
            newSD.setModelNumber(sd.getModelNumber());

            if (d == null) {
                newSD.setOtherBrandName(sd.getOtherBrandName());
                newSD.setOtherCommonName(sd.getOtherCommonName());
                newSD.setOtherDeviceType(sd.getOtherDeviceType());
            }
            dest.getStudyDevices().add(newSD);
        }
	}

    public DeviceRepository getDeviceRepository() {
        return deviceRepository;
    }

    public void setDeviceRepository(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }
}
