package gov.nih.nci.cabig.caaers.service.migrator.report;

import java.util.ArrayList;
import java.util.List;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

/**
 * User:medaV
 * Date: 1/22/13
 */
public class MedicalDeviceMigrator implements Migrator<ExpeditedAdverseEventReport> {
    
	public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
    
		List<MedicalDevice> srcMedicalDevices = aeReportSrc.getMedicalDevices();
		List<MedicalDevice> destMedicalDevices = aeReportDest.getMedicalDevices();
    	
    	if ( srcMedicalDevices == null || srcMedicalDevices.size() == 0) {
    		outcome.addWarning("WR-MDM-1", "Input doesn't contain any Lab Values.");
    		return;
    	}
		
    	if ( destMedicalDevices == null ) {
    		destMedicalDevices = new ArrayList<MedicalDevice>();
    	}
    	// Copy the Labs Information from Source to Destination.
    	for ( MedicalDevice dev : srcMedicalDevices) {
    		MedicalDevice destDev = new MedicalDevice();
    		copyFromSource(dev, destDev);
    		destDev.setReport(aeReportDest);
    		destMedicalDevices.add(destDev);
    	}
	}
	
	private void copyFromSource(MedicalDevice src, MedicalDevice dest) {
		dest.setStudyDevice(src.getStudyDevice());
		dest.setCatalogNumber(src.getCatalogNumber());
		dest.setCommonName(src.getCommonName());
		dest.setDeviceReprocessed(src.getDeviceReprocessed());
		dest.setDeviceOperator(src.getDeviceOperator());
		dest.setDeviceType(src.getDeviceType());
		dest.setEvaluationAvailability(src.getEvaluationAvailability());
		dest.setExpirationDate(src.getExpirationDate());
		dest.setExplantedDate(src.getExplantedDate());
		dest.setImplantedDate(src.getImplantedDate());
		dest.setLotNumber(src.getLotNumber());
		dest.setManufacturerCity(src.getManufacturerCity());
		dest.setManufacturerName(src.getManufacturerName());
		dest.setManufacturerState(src.getManufacturerState());
		dest.setModelNumber(src.getModelNumber());
		dest.setOtherNumber(src.getOtherNumber());
		dest.setReprocessorAddress(src.getReprocessorAddress());
		dest.setReprocessorName(src.getReprocessorName());
		dest.setReturnedDate(src.getReturnedDate());
		dest.setSerialNumber(src.getSerialNumber());
		dest.setOtherDeviceOperator(src.getOtherDeviceOperator());		
		dest.setStudyDevice(src.getStudyDevice());
		dest.setCatalogNumber(src.getCatalogNumber());
		dest.setCommonName(src.getCommonName());
		dest.setDeviceReprocessed(src.getDeviceReprocessed());
		dest.setDeviceOperator(src.getDeviceOperator());
		dest.setDeviceType(src.getDeviceType());
		dest.setEvaluationAvailability(src.getEvaluationAvailability());
		dest.setExpirationDate(src.getExpirationDate());
		dest.setExplantedDate(src.getExplantedDate());
		dest.setImplantedDate(src.getImplantedDate());
		dest.setLotNumber(src.getLotNumber());
		dest.setManufacturerCity(src.getManufacturerCity());
		dest.setManufacturerName(src.getManufacturerName());
		dest.setManufacturerState(src.getManufacturerState());
		dest.setModelNumber(src.getModelNumber());
		dest.setOtherNumber(src.getOtherNumber());
		dest.setReprocessorAddress(src.getReprocessorAddress());
		dest.setReprocessorName(src.getReprocessorName());
		dest.setReturnedDate(src.getReturnedDate());
		dest.setSerialNumber(src.getSerialNumber());
		dest.setOtherDeviceOperator(src.getOtherDeviceOperator());
	}
}