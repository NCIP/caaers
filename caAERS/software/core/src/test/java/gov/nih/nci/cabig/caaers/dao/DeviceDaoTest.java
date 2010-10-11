package gov.nih.nci.cabig.caaers.dao;

import static edu.nwu.bioinformatics.commons.testing.CoreTestCase.assertContains;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_STUDY;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.IMPORT_STUDIES;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.STUDY_ABSTRACTION;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.query.DeviceQuery;
import gov.nih.nci.cabig.caaers.dao.query.StudyQuery;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.StatementCallback;

/**
     * @author Ion C. Olaru
 */
public class DeviceDaoTest extends DaoNoSecurityTestCase<DeviceDao> {
    private DeviceDao deviceDao = (DeviceDao) getApplicationContext().getBean("deviceDao");

    public void testNotNull() {
        assertNotNull(deviceDao);
    }

    public void testGetAll() {
        List all = deviceDao.getAllDevices();
        assertEquals(3, all.size()); 
    }

    public void testGetById() {
        Device d = deviceDao.getById(-1);
        assertNotNull(d); 
        assertEquals("Device-01 common name", d.getCommonName());
        assertEquals("Device-01 brand name", d.getBrandName());
        assertEquals("Type A", d.getType()); 
    }

    public void testGetALLFilterByType() {
        DeviceQuery dq = new DeviceQuery();
        dq.filterByType("Type A");
        List l = deviceDao.search(dq);
        assertNotNull(l);
        assertEquals(2, l.size());
    }

}
