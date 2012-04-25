package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.api.ProcessingOutcome;
import gov.nih.nci.cabig.caaers.dao.DeviceDao;
import gov.nih.nci.cabig.caaers.dao.query.DeviceQuery;
import gov.nih.nci.cabig.caaers.domain.Device;
import gov.nih.nci.cabig.caaers.integration.schema.common.*;
import gov.nih.nci.cabig.caaers.integration.schema.device.*;
import gov.nih.nci.cabig.caaers.service.migrator.DeviceConverter;
import gov.nih.nci.cabig.caaers.service.migrator.DeviceMigrator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * @author Ion C. Olaru
 *         Date: 4/3/12 -10:03 AM
 * @author Biju Joseph
 */
public class DevicesServiceImpl{

    private static Log log = LogFactory.getLog(DevicesServiceImpl.class);

    private DeviceDao deviceDao;
    private DeviceConverter deviceConverter;
    private DeviceMigrator deviceMigrator;

    public CaaersServiceResponse createOrUpdateDevices(Devices devices) {
        CaaersServiceResponse response = Helper.createResponse();
        for (DeviceType deviceType : devices.getDevice()) {
            boolean failed = false;
            Device inputDevice = deviceConverter.convert(deviceType);
            try {

            
                DeviceQuery dq = new DeviceQuery();
                if(StringUtils.isNotEmpty(inputDevice.getCtepDbIdentifier())){
                    dq.filterByCtepDbIdentifier(inputDevice.getCtepDbIdentifier());
                }else{
                    dq.filterByCtepDbIdentifier(null);
                    dq.filterByCommonName(inputDevice.getCommonName());
                    dq.filterByBrandName(inputDevice.getBrandName());
                }
                Device dbDevice = loadPersistentDevice(dq);

                if(dbDevice == null){
                    log.info("Could not find device with [commonName : " + inputDevice.getCommonName() + ", ctepDbIdentifier : " + inputDevice.getCtepDbIdentifier() +"], so creating new device");
                    dbDevice = inputDevice;
                }
                //BJ: this is an overkill for new-device,
                // but it is okay, (it will put the last sync date) and we are not loosing much processing power anyway.
                deviceMigrator.migrate(inputDevice, dbDevice, null);


                deviceDao.save(dbDevice);
            } catch (Exception e) {
                log.error("Error while saving a device [commonName : " + inputDevice.getCommonName() + "]");
                failed = true;
            }

            String businessIdentifier = (inputDevice.getCtepDbIdentifier() != null ? inputDevice.getCtepDbIdentifier() : inputDevice.getCommonName());
            String message = "Device '" + inputDevice.getCommonName() + "' " + (failed ? "failed to create" :"created");
            ProcessingOutcome outcome = Helper.createOutcome(Device.class, businessIdentifier, failed, message);
            Helper.populateProcessingOutcome(response, outcome);
        }
        
        return response;
    }

    private Device loadPersistentDevice(DeviceQuery dq){
        List<Device> deviceList = (List<Device>) deviceDao.search(dq);
        return CollectionUtils.isEmpty(deviceList) ? null : deviceList.get(0);
    }


    public DeviceDao getDeviceDao() {
        return deviceDao;
    }

    public void setDeviceDao(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }

    public DeviceConverter getDeviceConverter() {
        return deviceConverter;
    }

    public void setDeviceConverter(DeviceConverter deviceConverter) {
        this.deviceConverter = deviceConverter;
    }

    public DeviceMigrator getDeviceMigrator() {
        return deviceMigrator;
    }

    public void setDeviceMigrator(DeviceMigrator deviceMigrator) {
        this.deviceMigrator = deviceMigrator;
    }
}
