package gov.nih.nci.cabig.caaers.datamigrator;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * SurgeryMigrator Tester.
 *
 * @author Biju Joseph
 * @since <pre>10/19/2010</pre>
 * 
 */
public class SurgeryMigratorTest extends CaaersDbTestCase {

    SurgeryMigrator migrator;
    JdbcTemplate jdbcTemplate;

    public void setUp() throws Exception {
        super.setUp();
        migrator = (SurgeryMigrator) getDeployedApplicationContext().getBean("surgeryDataMigrator");
        jdbcTemplate = (JdbcTemplate) getApplicationContext().getBean("jdbcTemplate");
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }


    public void testGetCode() throws Exception {
        assertSame(4, migrator.migratorType().getCode());
    }


    public void testMigrate(){
        //assumed migrator ran when the context loaded.
       int j = jdbcTemplate.queryForInt("select status_code from caaers_bootstrap_log where operation_code = 4");
       assertEquals(1, j);
    }


}
