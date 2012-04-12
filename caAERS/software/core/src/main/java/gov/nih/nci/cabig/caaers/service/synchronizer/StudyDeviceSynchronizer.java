package gov.nih.nci.cabig.caaers.service.synchronizer;

import edu.nwu.bioinformatics.commons.CollectionUtils;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyDevice;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.HashMap;

/**
 * @author Ion C. Olaru
 *         Date: 4/10/12 -4:37 PM
 */
public class StudyDeviceSynchronizer implements Migrator<Study> {

    /**
     * I know I know the parameters order is not the same as in the interface(1-src, 2-dest) which is very confusing, but I
     * could not refactor it since all the implementing classes ate following the same pattern, destination first,
     * source - second.
     * @param dest
     * @param src
     * @param studyDomainObjectImportOutcome
     */
    public void migrate(Study dest, Study src, DomainObjectImportOutcome<Study> studyDomainObjectImportOutcome) {
        if (CollectionUtils.isEmpty(src.getStudyDevices())) return;

		HashMap<String, StudyDevice> map = new HashMap<String, StudyDevice>();
		for(StudyDevice sd : dest.getActiveStudyDevices()) {
            String key = (sd.getDevice() != null ? sd.getDevice().getCommonName() : sd.getCommonName());
			map.put(key, sd);
		}

        for (StudyDevice sd : src.getStudyDevices()) {
            String key = (sd.getDevice() != null ? sd.getDevice().getCommonName() : sd.getCommonName());
            StudyDevice studyDevice = map.get(key);

            if (studyDevice == null) {
                // ADD NEW
                dest.addStudyDevice(sd);
            } else {
                // UPDATE
                studyDevice.setDevice(sd.getDevice());
                studyDevice.setCatalogNumber(sd.getCatalogNumber());
                studyDevice.setManufacturerCity(sd.getManufacturerCity());
                studyDevice.setManufacturerName(sd.getManufacturerName());
                studyDevice.setManufacturerState(sd.getManufacturerState());
                studyDevice.setModelNumber(sd.getModelNumber());
                studyDevice.setOtherBrandName(sd.getOtherBrandName());
                studyDevice.setOtherCommonName(sd.getOtherCommonName());
                studyDevice.setOtherBrandName(sd.getOtherBrandName());
                studyDevice.setOtherDeviceType(sd.getOtherDeviceType());
            }
        }
    }

}
