package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.DeviceDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ion C. Olaru
 */
@Transactional(readOnly = true)
public class DeviceRepository {

	private static Log log = LogFactory.getLog(StudyRepository.class);
    private DeviceDao deviceDao;

    public DeviceDao getDeviceDao() {
        return deviceDao;
    }

    public void setDeviceDao(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }
}