package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.DeviceDao;
import gov.nih.nci.cabig.caaers.dao.query.DeviceQuery;
import gov.nih.nci.cabig.caaers.domain.Device;
import gov.nih.nci.cabig.caaers.integration.schema.common.ActiveInactiveStatusType;
import gov.nih.nci.cabig.caaers.integration.schema.common.DeviceType;
import gov.nih.nci.cabig.caaers.integration.schema.common.EntityErrorMessageType;
import gov.nih.nci.cabig.caaers.integration.schema.device.DevicesType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ion C. Olaru
 *         Date: 4/6/12 -9:20 AM
 */
public class DevicesServiceImplTest extends DaoTestCase {

    DeviceDao deviceDao;
    DevicesServiceImpl devicesServiceImpl;
    DevicesType devicesType;

    public void testAddNew() {
        List l = deviceDao.getAllDevices();
        assertEquals(2, l.size());

        devicesType.getDevice().get(0).setCommonName("NEW 1");
        devicesType.getDevice().get(1).setCommonName("NEW 2");
        devicesType.getDevice().get(2).setCommonName("NEW 3");
        List<EntityErrorMessageType> errors = devicesServiceImpl.execute(devicesType);
        assertEquals(3, errors.size());
        assertEquals("", errors.get(0).getMessage().get(0));
        assertEquals("", errors.get(1).getMessage().get(0));
        assertEquals("", errors.get(2).getMessage().get(0));

        l = deviceDao.getAllDevices();
        assertEquals(5, l.size());
    }

    public void testUpdate() {

        DeviceQuery q = new DeviceQuery();
        q.filterByCommonName("Common name 02");

        // CHECK DB STATUS

        List allDevices = deviceDao.getAllDevices();
        assertEquals(2, allDevices.size());

        List oneDevice = deviceDao.search(q);
        assertEquals(1, oneDevice.size());
        Device loadedDevice = (Device)oneDevice.get(0);
        assertEquals("Common name 02", loadedDevice.getCommonName());
        assertEquals("Brand name 02", loadedDevice.getBrandName());
        assertEquals("Type 02", loadedDevice.getType());

        // PREPARE FOR UPDATE

        devicesType.getDevice().remove(0);
        devicesType.getDevice().remove(0);

        devicesType.getDevice().get(0).setCommonName("Common name 02");
        devicesType.getDevice().get(0).setBrandName("Updated brand name for device 02");
        devicesType.getDevice().get(0).setType("Updated type for device 02");

        // UPDATE

        List<EntityErrorMessageType> errors = devicesServiceImpl.execute(devicesType);
        assertEquals(1, errors.size());
        assertEquals("", errors.get(0).getMessage().get(0));

        allDevices = deviceDao.getAllDevices();
        assertEquals(2, allDevices.size());

        oneDevice = deviceDao.search(q);
        assertEquals(1, oneDevice.size());
        loadedDevice = (Device)oneDevice.get(0);
        assertEquals("Common name 02", loadedDevice.getCommonName());
        assertEquals("Updated brand name for device 02", loadedDevice.getBrandName());
        assertEquals("Updated type for device 02", loadedDevice.getType());
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        deviceDao = (DeviceDao)applicationContext.getBean("deviceDao");
        devicesServiceImpl = (DevicesServiceImpl)applicationContext.getBean("devicesServiceImpl");
        devicesType = new DevicesType();
        devicesType.setDevice(new ArrayList<DeviceType>(3));
        devicesType.getDevice().add(createADevice());
        devicesType.getDevice().add(createADevice());
        devicesType.getDevice().add(createADevice());
    }

    private DeviceType createADevice() {
        DeviceType d= new DeviceType();
        d.setCommonName("C");
        d.setBrandName("B");
        d.setType("T");
        d.setStatus(ActiveInactiveStatusType.AC);
        return d;
    }
}
