package gov.nih.nci.cabig.caaers.tools;

import static gov.nih.nci.cabig.caaers.tools.DataSourceSelfDiscoveringPropertiesFactoryBean.*;

import java.io.File;
import java.util.Properties;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * @author Rhett Sutphin
 */
public class DataSourceSelfDiscoveringPropertiesFactoryBeanTest extends TestCase {
    private DataSourceSelfDiscoveringPropertiesFactoryBean factoryBean;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        File thisDir = new File(getClass().getResource("/").toURI());
        System.setProperty("catalina.home", thisDir.getCanonicalPath());

        factoryBean = new DataSourceSelfDiscoveringPropertiesFactoryBean();
        factoryBean.setDatabaseConfigurationName("empty");
    }

    public void testDefaultPropertiesFoundAndBound() throws Exception {
        factoryBean.setDatabaseConfigurationName(null);
        assertDefaultLoadedProperties();
    }

    public void testEmptyConfNameUsesDefault() throws Exception {
        factoryBean.setDatabaseConfigurationName(" \t");
        assertDefaultLoadedProperties();
    }

    public void testDbConfigNameUsed() throws Exception {
        factoryBean.setDatabaseConfigurationName("alternate");
        assertLoadedProperties("jdbc:db:alternate", "java.lang.Long", "alt", "tla");
    }

    public void testDefaultDialectForPostgreSQLFromRDBMSProperty() throws Exception {
        factoryBean.getDefaults().setProperty(RDBMS_PROPERTY_NAME, "PostgreSQL");
        assertEquals(DEFAULT_POSTGRESQL_DIALECT,
            getActualProperties().getProperty(HIBERNATE_DIALECT_PROPERTY_NAME));
    }

    public void testDefaultDialectForPostgreSQLFromDriver() throws Exception {
        factoryBean.getDefaults().setProperty(DRIVER_PROPERTY_NAME, "org.postgresql.Driver");
        assertEquals(DEFAULT_POSTGRESQL_DIALECT,
            getActualProperties().getProperty(HIBERNATE_DIALECT_PROPERTY_NAME));
    }

    public void testExplicitHibernateDialectTrumps() throws Exception {
        String expectedDialect = "org.hibernate.dialect.PostgreSQLDialect";
        factoryBean.getDefaults().setProperty(RDBMS_PROPERTY_NAME, "PostgreSQL");
        factoryBean.getDefaults().setProperty(HIBERNATE_DIALECT_PROPERTY_NAME, expectedDialect);
        Assert.assertEquals(expectedDialect, getActualProperties().getProperty(HIBERNATE_DIALECT_PROPERTY_NAME));
    }

    private void assertLoadedProperties(
        String expectedUrl, String expectedDriver, String expectedUser, String expectedPass
    ) throws Exception {
        Properties actual = getActualProperties();
        Assert.assertEquals("Wrong URL", expectedUrl, actual.getProperty(URL_PROPERTY_NAME));
        Assert.assertEquals("Wrong driver", expectedDriver, actual.getProperty(DRIVER_PROPERTY_NAME));
        Assert.assertEquals("Wrong user", expectedUser, actual.getProperty(USERNAME_PROPERTY_NAME));
        Assert.assertEquals("Wrong password", expectedPass, actual.getProperty(PASSWORD_PROPERTY_NAME));
    }

    private void assertDefaultLoadedProperties() throws Exception {
        // these values are in datasource.properties
        assertLoadedProperties("jdbc:db:default", "java.lang.String", "default-user", "pass-default");
    }

    private Properties getActualProperties() throws Exception {
        return (Properties) factoryBean.getObject();
    }
}
