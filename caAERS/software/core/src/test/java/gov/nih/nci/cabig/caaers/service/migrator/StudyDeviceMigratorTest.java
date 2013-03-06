/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.DeviceDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Device;
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyDevice;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import org.dbunit.operation.DatabaseOperation;

import java.util.ArrayList;

/**
 * @author Ion C. Olaru
 *         Date: 4/9/12 -2:38 PM
 */
public class StudyDeviceMigratorTest extends DaoTestCase {

    StudyDeviceMigrator sdm;
    DeviceDao deviceDao;
    StudyDao studyDao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        sdm = (StudyDeviceMigrator)applicationContext.getBean("studyDeviceMigrator");
        deviceDao = (DeviceDao)applicationContext.getBean("deviceDao");
        studyDao = (StudyDao)applicationContext.getBean("studyDao");
    }

    public void testAddNewDevice() {

        Study dbStudy = studyDao.getStudyDesignById(-2);
        assertEquals(3, deviceDao.getAllDevices().size());
        assertEquals(2, dbStudy.getStudyDevices().size());

        //
        Study s = new LocalStudy();
        s.setStudyDevices(new ArrayList<StudyDevice>(1));
        StudyDevice sd = new StudyDevice();

        //
        Device d = new Device();
        d.setCommonName("NOT IN DB DEVICE");
        d.setBrandName("B");
        d.setType("T");

        sd.setStudy(dbStudy);
        sd.setDevice(d);
        s.getStudyDevices().add(sd);

        DomainObjectImportOutcome outcome = new DomainObjectImportOutcome<Study>();
        sdm.migrate(s, dbStudy, outcome);

        assertEquals(4, deviceDao.getAllDevices().size());
        assertEquals(3, dbStudy.getStudyDevices().size());
    }

    public void testUpdateDevice() {

        Study dbStudy = studyDao.getStudyDesignById(-2);
        assertEquals(3, deviceDao.getAllDevices().size());
        assertEquals(2, dbStudy.getStudyDevices().size());
        assertEquals("Type 01", deviceDao.getById(-1).getType());
        assertEquals(-1, deviceDao.getById(-1).getId().intValue());

        //
        Study s = new LocalStudy();
        s.setStudyDevices(new ArrayList<StudyDevice>(1));
        StudyDevice sd = new StudyDevice();

        //
        Device d = new Device();
        d.setCommonName("Common name 01");
        d.setBrandName("Brand name 01");
        d.setType("T");

        sd.setStudy(dbStudy);
        sd.setDevice(d);
        s.getStudyDevices().add(sd);

        DomainObjectImportOutcome outcome = new DomainObjectImportOutcome<Study>();
        sdm.migrate(s, dbStudy, outcome);

        assertEquals(3, deviceDao.getAllDevices().size());
        assertEquals(3, dbStudy.getStudyDevices().size());
        Device loadedDevice = deviceDao.getById(-1);
        assertEquals("T", loadedDevice.getType());
        assertEquals("Brand name 01", loadedDevice.getBrandName());
        assertEquals("Common name 01, Brand name 01, T", loadedDevice.getDisplayName());
        assertEquals(-1, loadedDevice.getId().intValue());

    }

/*
    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.REFRESH;
    }
*/
}
