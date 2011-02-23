package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.dao.query.DeviceQuery;
import gov.nih.nci.cabig.caaers.domain.Device;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This class implements the Data access related operations for the Device domain object.
 * @author Ion C. Olaru
 */
@Transactional(readOnly = true)
public class DeviceDao extends GridIdentifiableDao<Device> implements MutableDomainObjectDao<Device> {

    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * @return Class representation of the domain object that this DAO is 
     */
    @Override
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<Device> domainClass() {
        return Device.class;
    }

	public List<? extends Object> search(final DeviceQuery query) {
		return super.search(query);
	}

    /**
     * Get the list of all devices
     * @return return the list of studies.
     */
    @SuppressWarnings("unchecked")
    public List<Device> getAllDevices() {
        return getHibernateTemplate().find("from Device");
    }

    /**
     * Delete a device
     * @param d The device to be deleted.
     */
    @Transactional(readOnly = false)
    public void delete(Device d) {
        getHibernateTemplate().delete(d);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(Device o) {
        getHibernateTemplate().saveOrUpdate(o);
    }
}
