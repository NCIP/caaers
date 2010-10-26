package gov.nih.nci.cabig.caaers.datamigrator;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 * MedicalDeviceDataMigrator Tester.
 *
 * @author Biju Joseph
 * @since <pre>10/18/2010</pre>
 * 
 */
public class MedicalDeviceDataMigratorTest extends CaaersDbTestCase {

    MedicalDeviceDataMigrator migrator;
    JdbcTemplate jdbcTemplate;

    public void setUp() throws Exception {
        super.setUp();
        migrator = (MedicalDeviceDataMigrator) getDeployedApplicationContext().getBean("medicalDeviceDataMigrator");
        jdbcTemplate = (JdbcTemplate) getApplicationContext().getBean("jdbcTemplate");
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetCode() throws Exception {
        assertSame(2, migrator.migratorType().getCode());
    }

    public void testMigrate(){
        //assumed migrator ran when the context loaded.
       SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from ae_medical_devices");
       int i = rowSet.getMetaData().getColumnCount();

       assertEquals(27, i);

       int j = jdbcTemplate.queryForInt("select status_code from caaers_bootstrap_log where operation_code = 2");
       assertEquals(1, j);
    }

}
