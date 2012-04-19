package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.dao.DeviceDao;
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
	private DeviceDao deviceDao;
	private DeviceMigrator deviceMigrator;
    private static Log log = LogFactory.getLog(StudyRepository.class);

	public void migrate(Study src, Study dest, DomainObjectImportOutcome<Study> outcome) {
		List<StudyDevice> l = src.getStudyDevices();
        log.debug("Migrating " + l.size() + " StudyDevices");

        for (StudyDevice sd : l) {
            StudyDevice newStudyDevice = new StudyDevice();
            Device d  = null;
            if (sd.getDevice() != null) {
                List<Device> ld = deviceRepository.getByCommonName(sd.getDevice().getCommonName());
                if (ld.size() > 0) {
                    d = ld.get(0);
                    deviceMigrator.migrate(sd.getDevice(), d, null);
                } else {
                    // Create the device if needed
                    d = new Device();
                    d.setCommonName(sd.getDevice().getCommonName());
                    d.setBrandName(sd.getDevice().getBrandName());
                    d.setType(sd.getDevice().getType());
                }
                deviceDao.save(d);
            }

            newStudyDevice.setDevice(d);
            newStudyDevice.setStudy(dest);

            newStudyDevice.setCatalogNumber(sd.getCatalogNumber());
            newStudyDevice.setManufacturerCity(sd.getManufacturerCity());
            newStudyDevice.setManufacturerName(sd.getManufacturerName());
            newStudyDevice.setManufacturerState(sd.getManufacturerState());
            newStudyDevice.setModelNumber(sd.getModelNumber());

            if (d == null) {
                newStudyDevice.setOtherBrandName(sd.getOtherBrandName());
                newStudyDevice.setOtherCommonName(sd.getOtherCommonName());
                newStudyDevice.setOtherDeviceType(sd.getOtherDeviceType());
            }
            dest.getStudyDevices().add(newStudyDevice);
        }
	}

    public DeviceRepository getDeviceRepository() {
        return deviceRepository;
    }

    public void setDeviceRepository(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public DeviceDao getDeviceDao() {
        return deviceDao;
    }

    public void setDeviceDao(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }

    public DeviceMigrator getDeviceMigrator() {
        return deviceMigrator;
    }

    public void setDeviceMigrator(DeviceMigrator deviceMigrator) {
        this.deviceMigrator = deviceMigrator;
    }
}
