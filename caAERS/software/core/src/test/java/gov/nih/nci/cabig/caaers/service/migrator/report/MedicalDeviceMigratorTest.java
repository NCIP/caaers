package gov.nih.nci.cabig.caaers.service.migrator.report;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Availability;
import gov.nih.nci.cabig.caaers.domain.BehavioralIntervention;
import gov.nih.nci.cabig.caaers.domain.BiologicalIntervention;
import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.DeviceOperator;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.MedicalDevice;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OtherIntervention;
import gov.nih.nci.cabig.caaers.domain.ReprocessedDevice;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import junit.framework.TestCase;

/**
 * User: medav
 * Date: 1/28/13
 */
public class MedicalDeviceMigratorTest extends TestCase {

	MedicalDeviceMigrator migrator;
    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;

    public void setUp() throws Exception {
        migrator = new MedicalDeviceMigrator();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        
        Study study = Fixtures.createStudy("CTEP");
        Organization org = Fixtures.createOrganization("Mayo Clinic");
        
        StudySite site = Fixtures.createStudySite(org, 26);
        study.addStudySite(site);
        
        AdverseEventReportingPeriod period = Fixtures.createReportingPeriod();
        StudyParticipantAssignment assignment = Fixtures.createAssignment();
        period.setAssignment(assignment);
        dest.setReportingPeriod(period);
     
        
        MedicalDevice medicalDevice = new MedicalDevice(Fixtures.createStudyDevice());

        String brandName = "brand name";
        medicalDevice.getStudyDevice().getDevice().setBrandName(brandName);
        String catalogNumber = "catalong number";
        medicalDevice.getStudyDevice().setCatalogNumber(catalogNumber);
        String commonName = "common name";
        medicalDevice.getStudyDevice().getDevice().setCommonName(commonName);
        DeviceOperator other = DeviceOperator.OTHER;
        medicalDevice.setDeviceOperator(other);
        Availability returned = Availability.RETURNED;

        medicalDevice.setDeviceReprocessed(ReprocessedDevice.YES);
        String deviceType = "device type";
        medicalDevice.getStudyDevice().getDevice().setType(deviceType);
        Availability evaluationAvailability = Availability.YES;
        medicalDevice.setEvaluationAvailability(evaluationAvailability);
        Date expirationDate = new Date();
        medicalDevice.setExpirationDate(expirationDate);
        Date explantedDate = new Date();
        medicalDevice.setExplantedDate(explantedDate);
        medicalDevice.setGridId("grid id");
        medicalDevice.setId(1);
        
        src.getMedicalDevices().add(medicalDevice);
               
    }


    public void testMigrateWithValues() throws Exception {

    	migrator.migrate(src,dest,new DomainObjectImportOutcome<ExpeditedAdverseEventReport>());
    	assertEquals(1, dest.getMedicalDevices().size());
    	assertEquals(Availability.YES, dest.getMedicalDevices().get(0).getEvaluationAvailability());
    	assertEquals("common name", dest.getMedicalDevices().get(0).getCommonName());
    	
    }
}
