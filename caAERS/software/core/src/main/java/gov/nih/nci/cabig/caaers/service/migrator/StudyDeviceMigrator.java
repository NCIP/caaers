package gov.nih.nci.cabig.caaers.service.migrator;

import com.aparzev.util.CollectionUtils;
import gov.nih.nci.cabig.caaers.dao.DeviceDao;
import gov.nih.nci.cabig.caaers.dao.InvestigationalNewDrugDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.repository.DeviceRepository;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * @author Ion C. Olaru
 *
 * */
public class StudyDeviceMigrator implements Migrator<Study> {

	private DeviceRepository deviceRepository;
	private DeviceDao deviceDao;
	private DeviceMigrator deviceMigrator;
    private InvestigationalNewDrugDao investigationalNewDrugDao;
    private OrganizationDao organizationDao;
    private static Log log = LogFactory.getLog(StudyRepository.class);

	public void migrate(Study src, Study dest, DomainObjectImportOutcome<Study> outcome) {
		List<StudyDevice> l = src.getStudyDevices();
        log.debug("Migrating " + l.size() + " StudyDevices");

        for (StudyDevice sd : l) {
            StudyDevice newStudyDevice = new StudyDevice();
            Device d  = null;
            if (sd.getDevice() != null) {

                List<Device> ld;

                if (StringUtils.isNotBlank(sd.getDevice().getCtepDbIdentifier())) {
                    ld = deviceRepository.getByCtepDbIdentifier(sd.getDevice().getCtepDbIdentifier());
                } else {
                    ld = deviceRepository.getByCommonNameAndBrandName(sd.getDevice().getCommonName(),sd.getDevice().getBrandName());
                }

                if (ld.size() > 0) {
                    d = ld.get(0);
                    deviceMigrator.migrate(sd.getDevice(), d, null);
                } else {
                    // Create the device if needed
                    d = new Device();
                    d.setCommonName(sd.getDevice().getCommonName());
                    d.setBrandName(sd.getDevice().getBrandName());
                    d.setType(sd.getDevice().getType());
                }
                deviceDao.save(d);
            }

            newStudyDevice.setDevice(d);
            newStudyDevice.setStudy(dest);

            newStudyDevice.setCatalogNumber(sd.getCatalogNumber());
            newStudyDevice.setManufacturerCity(sd.getManufacturerCity());
            newStudyDevice.setManufacturerName(sd.getManufacturerName());
            newStudyDevice.setManufacturerState(sd.getManufacturerState());
            newStudyDevice.setModelNumber(sd.getModelNumber());

            if (d == null) {
                newStudyDevice.setOtherBrandName(sd.getOtherBrandName());
                newStudyDevice.setOtherCommonName(sd.getOtherCommonName());
                newStudyDevice.setOtherDeviceType(sd.getOtherDeviceType());
            }
            
            dest.getStudyDevices().add(newStudyDevice);
            migrate(sd, newStudyDevice, outcome);
        }
	}

    private InvestigationalNewDrug findOrCreateIND(String holderName, String number){
        //check - with assumption that holderName is NCI code
        List<InvestigationalNewDrug> inds = investigationalNewDrugDao.findOrganizationHeldIND(String.valueOf(number), String.valueOf(holderName));
        if(CollectionUtils.isEmpty(inds)){
            //check with assumption that holder name is Organization Name.
            inds = investigationalNewDrugDao.findByNumberAndHolderName(number, holderName);
        }


        if(CollectionUtils.isNotEmpty(inds)) return inds.get(0);

        //need to create IND
        InvestigationalNewDrug ind = new InvestigationalNewDrug();
        if(StringUtils.isNotEmpty(number)) ind.setIndNumber(Integer.parseInt(number));

        OrganizationHeldIND holder = new OrganizationHeldIND();
        Organization org = organizationDao.getByNCIcode(holderName);
        if(org == null) org = organizationDao.getByName(holderName);
        if(org == null) return null; //still null then cannot create IND?

        holder.setOrganization(org);
        holder.setInvestigationalNewDrug(ind);
        ind.setINDHolder(holder);
        investigationalNewDrugDao.save(ind);
        return  ind;

    }


    private void migrate(StudyDevice src, StudyDevice dest, DomainObjectImportOutcome<Study> outcome){
        if(src.getStudyDeviceINDAssociations() != null){
            for(StudyDeviceINDAssociation indAssociation : src.getStudyDeviceINDAssociations()){
                if(indAssociation == null) continue;
                StudyDeviceINDAssociation newSda = new StudyDeviceINDAssociation();
                InvestigationalNewDrug ind = null;
                if(indAssociation.getInvestigationalNewDrug() != null){
                    ind = findOrCreateIND(indAssociation.getInvestigationalNewDrug().getHolderName(), indAssociation.getInvestigationalNewDrug().getStrINDNo()) ;
                }
                newSda.setInvestigationalNewDrug(ind);
                dest.addStudyDeviceINDAssociation(newSda);
            }
        }
    }
    
    public DeviceRepository getDeviceRepository() {
        return deviceRepository;
    }

    public void setDeviceRepository(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public DeviceDao getDeviceDao() {
        return deviceDao;
    }

    public void setDeviceDao(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }

    public DeviceMigrator getDeviceMigrator() {
        return deviceMigrator;
    }

    public void setDeviceMigrator(DeviceMigrator deviceMigrator) {
        this.deviceMigrator = deviceMigrator;
    }

    public InvestigationalNewDrugDao getInvestigationalNewDrugDao() {
        return investigationalNewDrugDao;
    }

    public void setInvestigationalNewDrugDao(InvestigationalNewDrugDao investigationalNewDrugDao) {
        this.investigationalNewDrugDao = investigationalNewDrugDao;
    }

    public OrganizationDao getOrganizationDao() {
        return organizationDao;
    }

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }
}
