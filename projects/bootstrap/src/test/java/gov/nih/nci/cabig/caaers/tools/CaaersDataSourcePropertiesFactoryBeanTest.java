package gov.nih.nci.cabig.caaers.tools;

import gov.nih.nci.cabig.ctms.tools.DataSourceSelfDiscoveringPropertiesFactoryBean;

import java.io.File;
import java.net.URI;
import java.util.Properties;

import junit.framework.TestCase;

/**
 * @author Rhett Sutphin
 */
public class CaaersDataSourcePropertiesFactoryBeanTest extends TestCase {
    private CaaersDataSourcePropertiesFactoryBean factoryBean;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
	String thisPath = getClass().getResource("/conf/caaers/empty.properties").toURI().toString();
        File thisDir = new File(new URI(thisPath.substring(0, thisPath.indexOf("/conf/caaers/empty.properties"))));
        System.setProperty("catalina.home", thisDir.getCanonicalPath());

        factoryBean = new CaaersDataSourcePropertiesFactoryBean();
        factoryBean.setDatabaseConfigurationName("empty");
    }

    public void testDefaultApplicationNameIsCaaers() throws Exception {
        assertEquals("caaers", factoryBean.getApplicationDirectoryName());
    }

    private Properties getActualProperties() throws Exception {
        return (Properties) factoryBean.getObject();
    }

    // TODO: this isn't a test -- it's just a recapitulation of the select logic
    public void testSelectQuartzDelegateClass() throws Exception {
        Properties actual = getActualProperties();
        String dbProperty = String
                        .valueOf(actual
                                        .getProperty(DataSourceSelfDiscoveringPropertiesFactoryBean.DRIVER_PROPERTY_NAME))
                        + String
                                        .valueOf(actual
                                                        .getProperty(DataSourceSelfDiscoveringPropertiesFactoryBean.RDBMS_PROPERTY_NAME));
        String quartzDelegateClass = actual
                        .getProperty(CaaersDataSourcePropertiesFactoryBean.QUARTZ_DELEGATE_PROPERTY_NAME);
        assertNotNull("Quartz Delegate class empty", quartzDelegateClass);
        if (dbProperty.toLowerCase().contains("oracle")) {
            assertEquals("Expected org.quartz.impl.jdbcjobstore.oracle.OracleDelegate",
                            "org.quartz.impl.jdbcjobstore.oracle.OracleDelegate",
                            quartzDelegateClass);
            return;
        }
        if (dbProperty.toLowerCase().contains("postgres")) {
            assertEquals("Expected org.quartz.impl.jdbcjobstore.PostgreSQLDelegate",
                            "org.quartz.impl.jdbcjobstore.PostgreSQLDelegate", quartzDelegateClass);
            return;
        }
        if (dbProperty.toLowerCase().contains("hsql")) {
            assertEquals("Expected org.quartz.impl.jdbcjobstore.StdJDBCDelegate(Hibernate)",
                            "org.quartz.impl.jdbcjobstore.StdJDBCDelegate", quartzDelegateClass);
            return;
        }
        // assertTrue("datasource.properties has wrong configuation", false);
    }
    
    public void testSelectRulesDBConfig() throws Exception {
    	Properties props = getActualProperties();
    	assertEquals(props.get("rules.dbfile"), "classpath:db/jackrabbit-repo-oracle.xml");
    }
}
