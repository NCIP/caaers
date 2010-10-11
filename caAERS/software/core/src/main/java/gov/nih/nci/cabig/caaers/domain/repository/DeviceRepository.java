package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.DeviceDao;
import gov.nih.nci.cabig.caaers.dao.query.DeviceQuery;
import gov.nih.nci.cabig.caaers.domain.Device;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public Device getById(int id) {
        return deviceDao.getById(id);
    }

    public List<? extends Object> search(final DeviceQuery query) {
        return deviceDao.search(query);
    }

    public List<Device> getAllDevices() {
        return deviceDao.getAllDevices();
    }
    
}