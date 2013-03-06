/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.DeviceDao;
import gov.nih.nci.cabig.caaers.domain.Device;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.integration.schema.common.DeviceType;
import gov.nih.nci.cabig.caaers.integration.schema.device.Devices;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ion C. Olaru
 *         Date: 4/6/12 -9:20 AM
 */
public class DevicesServiceImplTest extends DaoTestCase {

    DeviceDao deviceDao;
    DevicesServiceImpl devicesServiceImpl;
    Devices devicesType;

    public void testAddNew(){
        devicesType = new Devices();
        devicesType.setDevice(new ArrayList<DeviceType>(3));
        devicesType.getDevice().add(Fixtures.createDeviceType("cn1", "bn1", "dt1", "111"));
        devicesType.getDevice().add(Fixtures.createDeviceType("cn2", "bn2", "dt2", "222"));
        devicesType.getDevice().add(Fixtures.createDeviceType("cn3", "bn3", "dt3", "333"));
        {
            List<Device> l = (List<Device>) deviceDao.getAllDevices();
            assertEquals(2, l.size());
            Device d = findDevice(l, "111");
            assertEquals("cn1", d.getCommonName());
            devicesServiceImpl.createOrUpdateDevices(devicesType);
        }
        interruptSession();
        {
            List<Device> l = (List<Device>) deviceDao.getAllDevices();
            assertEquals(3, l.size());
            Device d = findDevice(l, "111");
            assertEquals("cn1", d.getCommonName());
            d = findDevice(l, "333");
            assertEquals("cn3", d.getCommonName());
        }


    }


    public void testAddNewAndUpdate(){
        devicesType = new Devices();
        devicesType.setDevice(new ArrayList<DeviceType>(3));
        devicesType.getDevice().add(Fixtures.createDeviceType("cn1", "bn1", "dt1", "111"));
        devicesType.getDevice().add(Fixtures.createDeviceType("c2", "b2", "d2", "222"));
        devicesType.getDevice().add(Fixtures.createDeviceType("cn3", "bn3", "dt3", "333"));
        {
            List<Device> l = (List<Device>) deviceDao.getAllDevices();
            assertEquals(2, l.size());
            Device d = findDevice(l, "111");
            assertEquals("cn1", d.getCommonName());
            d = findDevice(l, "222");
            devicesServiceImpl.createOrUpdateDevices(devicesType);
        }
        interruptSession();
        {
            List<Device> l = (List<Device>) deviceDao.getAllDevices();
            assertEquals(3, l.size());
            Device d = findDevice(l, "111");
            assertEquals("cn1", d.getCommonName());
            d = findDevice(l, "333");
            assertEquals("cn3", d.getCommonName());
            d = findDevice(l, "222");
            assertEquals("c2", d.getCommonName());
            assertEquals("b2", d.getBrandName());
            assertEquals("d2", d.getType());
        }
        
        
    }
    
    private Device findDevice(List<Device> l , String ctepId){
        for(Device d : l) if(d.getCtepDbIdentifier().equals(ctepId)) return d;
        return null;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        deviceDao = (DeviceDao)applicationContext.getBean("deviceDao");
        devicesServiceImpl = (DevicesServiceImpl)applicationContext.getBean("devicesServiceImpl");

    }

}
