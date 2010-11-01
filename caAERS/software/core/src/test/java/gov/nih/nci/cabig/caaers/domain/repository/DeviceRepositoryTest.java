package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.query.DeviceQuery;
import gov.nih.nci.cabig.caaers.domain.Device;

import java.util.List;

/**
 * @author Ion C. Olaru
 * 
 */
public class DeviceRepositoryTest extends DaoNoSecurityTestCase<OrganizationDao> {

	private DeviceRepository deviceRepository;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		deviceRepository = (DeviceRepository)getDeployedApplicationContext().getBean("deviceRepository");
	}

    public void testNotNull() {
        assertNotNull(deviceRepository);
        assertNotNull(deviceRepository.getDeviceDao());
    }
    public void testGetAll() {
        List all = deviceRepository.getAllDevices();
        assertEquals(3, all.size());
    }

    public void testGetById() {
        Device d = deviceRepository.getById(-1);
        assertNotNull(d);
        assertEquals("Device-01 common name", d.getCommonName());
        assertEquals("Device-01 brand name", d.getBrandName());
        assertEquals("Type A", d.getType());
    }

    public void testGetALLFilterByType() {
        DeviceQuery dq = new DeviceQuery();
        dq.filterByType("Type A");
        List l = deviceRepository.search(dq);
        assertNotNull(l);
        assertEquals(2, l.size());
    }

    
}