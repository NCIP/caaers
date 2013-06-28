/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.report;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import gov.nih.nci.cabig.caaers.domain.*;
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
        assignment.setStudySite(site);
        assignment.getStudySite().setStudy(study);

        
        //Set the destination.
        dest.setReportingPeriod(period);

        StudyDevice  studyDevice = new StudyDevice();
        studyDevice.setRetiredIndicator(false);
        Device d =new Device();
        d.setBrandName("Brad");
        d.setCommonName("Brad Common Name");
        d.setType("device type");
        studyDevice.setDevice(d);
        d.setId(-100);
        d.setVersion(1);

       study.addStudyDevice(studyDevice);


        MedicalDevice medicalDevice = new MedicalDevice(Fixtures.createStudyDevice());

        String brandName = "Brad";
        medicalDevice.getStudyDevice().getDevice().setBrandName(brandName);
        String catalogNumber = "catalong number";
        medicalDevice.getStudyDevice().setCatalogNumber(catalogNumber);
        String commonName = "Brad Common Name";
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

        
        src.getMedicalDevices().add(medicalDevice);
               
    }


    public void testMigrateWithValues() throws Exception {

    	migrator.migrate(src,dest,new DomainObjectImportOutcome<ExpeditedAdverseEventReport>());
    	assertEquals(1, dest.getMedicalDevices().size());
    	assertEquals(Availability.YES, dest.getMedicalDevices().get(0).getEvaluationAvailability());
    	assertEquals("Brad Common Name", dest.getMedicalDevices().get(0).getCommonName());
    	
    }
}
