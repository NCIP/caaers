package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;

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
    
}
