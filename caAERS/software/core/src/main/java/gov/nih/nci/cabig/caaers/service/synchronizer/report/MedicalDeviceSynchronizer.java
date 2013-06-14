package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;
import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.Predicate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class MedicalDeviceSynchronizer implements Migrator<ExpeditedAdverseEventReport> {

    public void migrate(ExpeditedAdverseEventReport src, ExpeditedAdverseEventReport dest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
        List<MedicalDevice> newlyAddedDevices = new ArrayList<MedicalDevice>();
        List<MedicalDevice> existingDevices = new ArrayList<MedicalDevice>();
        if(dest.getMedicalDevices() != null) existingDevices.addAll(dest.getMedicalDevices());
        if(src.getMedicalDevices() != null){
            for(MedicalDevice md : src.getMedicalDevices()){
                final MedicalDevice xmlMd = md;
                MedicalDevice found = CollectionUtils.find(existingDevices, new Predicate<MedicalDevice>(){
                    public boolean evaluate(MedicalDevice medicalDevice) {
                        return medicalDevice.getStudyDevice().getId().equals(xmlMd.getStudyDevice().getId());
                    }
                });
                if(found != null){
                    synchronizeMedicalDevice(xmlMd, found);
                    existingDevices.remove(found);
                }else {
                    newlyAddedDevices.add(xmlMd);
                }

            }
        }

        //delete unwanted
        for(MedicalDevice md : existingDevices){
            dest.cascaeDeleteToAttributions(md);
            dest.getMedicalDevices().remove(md);
        }

        //add newly added devices
        for(MedicalDevice md : newlyAddedDevices){
            dest.addMedicalDevice(md);
        }
    }

    public void synchronizeMedicalDevice(MedicalDevice xmlMd, MedicalDevice dbMd){
        dbMd.setBrandName(xmlMd.getBrandName());
        dbMd.setCommonName(xmlMd.getCommonName());
        dbMd.setDeviceType(xmlMd.getDeviceType());
        dbMd.setManufacturerName(xmlMd.getManufacturerName());
        dbMd.setManufacturerCity(xmlMd.getManufacturerCity());
        dbMd.setManufacturerState(xmlMd.getManufacturerState());
        dbMd.setModelNumber(xmlMd.getModelNumber());
        dbMd.setLotNumber(xmlMd.getLotNumber());
        dbMd.setCatalogNumber(xmlMd.getCatalogNumber());
        dbMd.setExpirationDate(xmlMd.getExpirationDate());
        dbMd.setSerialNumber(xmlMd.getSerialNumber());
        dbMd.setOtherNumber(xmlMd.getOtherNumber());
        dbMd.setDeviceOperator(xmlMd.getDeviceOperator());
        dbMd.setDeviceReprocessed(xmlMd.getDeviceReprocessed());
        dbMd.setOtherDeviceOperator(xmlMd.getOtherDeviceOperator());
        dbMd.setImplantedDate(xmlMd.getImplantedDate());
        dbMd.setExplantedDate(xmlMd.getExplantedDate());
        dbMd.setReprocessorName(xmlMd.getReprocessorName());
        dbMd.setReprocessorAddress(xmlMd.getReprocessorAddress());
        dbMd.setEvaluationAvailability(xmlMd.getEvaluationAvailability());
        dbMd.setReturnedDate(xmlMd.getReturnedDate());

    }
}
