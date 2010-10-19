package gov.nih.nci.cabig.caaers.datamigrator;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 * RadiationMigrator Tester.
 *
 * @author Biju Joseph
 * @since <pre>10/19/2010</pre>
 * 
 */
public class RadiationMigratorTest  extends CaaersDbTestCase {

    RadiationMigrator migrator;
    JdbcTemplate jdbcTemplate;
    
    public void setUp() throws Exception {
        super.setUp();
        migrator = (RadiationMigrator) getDeployedApplicationContext().getBean("radiationDataMigrator");
        jdbcTemplate = (JdbcTemplate) getApplicationContext().getBean("jdbcTemplate");
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    
    public void testGetCode() throws Exception {
        assertSame(3, migrator.migratorType().getCode());
    }


    public void testMigrate(){
        //assumed migrator ran when the context loaded.
       int j = jdbcTemplate.queryForInt("select status_code from caaers_bootstrap_log where operation_code = 3");
       assertEquals(1, j);
    }


}
