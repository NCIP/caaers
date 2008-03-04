package gov.nih.nci.cabig.caaers.tools;

import gov.nih.nci.cabig.ctms.tools.DataSourceSelfDiscoveringPropertiesFactoryBean;

/**
 * This class searches through the following directories, looking for a file named
 * ${databaseConfigurationName}.properties:
 * <ul>
 * <li><kbd>${catalina.home}/conf/caaers</kbd></li>
 * <li><kbd>${user.home}/.caaers</kbd></li>
 * <li><kbd>/etc/caaers</kbd></li>
 * </ul>
 * 
 * It loads this file and returns it when {@link #getObject} is called.
 * 
 * @author Rhett Sutphin
 */
public class CaaersDataSourcePropertiesFactoryBean extends
                DataSourceSelfDiscoveringPropertiesFactoryBean {
    public static final String QUARTZ_DELEGATE_PROPERTY_NAME = "jdbc.quartz.delegateClassName";

    public static final String SCHEMA_PROPERTY_NAME = "datasource.schema";

    public static final String AUTH_MODE_PROPERTY_NAME = "authenticationMode";

    public static final String WEBSSO_BASE_URL = "webSSO.server.baseUrl";

    public static final String WEBSSO_SERVER_TRUST_CERTIFICATE = "webSSO.server.trustCert";

    public static final String WEBSSO_HOST_CERTIFICATE = "hostCertificate";

    public static final String WEBSSO_HOST_KEY = "hostKey";

    public static final String WEBSSO_CAS_ACEGI_SECURITY_URL = "webSSO.cas.acegi.security.url";

    public static final String GRID_STUDYCONSUMER_ROLLBACK_INTERVAL = "grid.studyconsumer.rollbackInterval";

    public static final String GRID_REGISTRATIONCONSUMER_ROLLBACK_INTERVAL = "grid.registrationconsumer.rollbackInterval";

    public CaaersDataSourcePropertiesFactoryBean() {
        setApplicationDirectoryName("caaers");
    }

    /**
     * Sets reasonable defaults for caAERS-specific properties
     */
    @Override
    protected void computeProperties() {
        String quartzDelegateClass = selectQuartzDelegateClass();
        if (quartzDelegateClass != null) properties.setProperty(QUARTZ_DELEGATE_PROPERTY_NAME,
                        quartzDelegateClass);
        String schema = selectSchema();
        if (schema != null) properties.setProperty(SCHEMA_PROPERTY_NAME, schema);
        if (properties.getProperty(AUTH_MODE_PROPERTY_NAME) == null) properties.setProperty(
                        AUTH_MODE_PROPERTY_NAME, "local");
        if (properties.getProperty(WEBSSO_BASE_URL) == null) properties.setProperty(
                        WEBSSO_BASE_URL, "http://dummyurl.com/url");
        if (properties.getProperty(WEBSSO_SERVER_TRUST_CERTIFICATE) == null) properties
                        .setProperty(WEBSSO_SERVER_TRUST_CERTIFICATE, "dummyTrustCerts");
        if (properties.getProperty(WEBSSO_HOST_CERTIFICATE) == null) properties.setProperty(
                        WEBSSO_HOST_CERTIFICATE, "dummyHostCert");
        if (properties.getProperty(WEBSSO_HOST_KEY) == null) properties.setProperty(
                        WEBSSO_HOST_KEY, "dummykey");
        if (properties.getProperty(WEBSSO_CAS_ACEGI_SECURITY_URL) == null) properties.setProperty(
                        WEBSSO_CAS_ACEGI_SECURITY_URL, "http://dummy.com/casurl/");
        if (properties.getProperty(GRID_STUDYCONSUMER_ROLLBACK_INTERVAL) == null) properties
                        .setProperty(GRID_STUDYCONSUMER_ROLLBACK_INTERVAL, "1");
        if (properties.getProperty(GRID_REGISTRATIONCONSUMER_ROLLBACK_INTERVAL) == null) properties
                        .setProperty(GRID_REGISTRATIONCONSUMER_ROLLBACK_INTERVAL, "1");
    }

    /**
     * To determin the quartz delegate class to use
     * 
     * @return
     */
    private String selectQuartzDelegateClass() {
        // //hsql for time being will use default (http://jira.opensymphony.com/browse/QUARTZ-560)
        String defaultClass = "org.quartz.impl.jdbcjobstore.StdJDBCDelegate";
        String postgresClass = "org.quartz.impl.jdbcjobstore.PostgreSQLDelegate";
        String oracleClass = "org.quartz.impl.jdbcjobstore.oracle.OracleDelegate";
        String rdbms = properties.getProperty(RDBMS_PROPERTY_NAME);
        String driver = properties.getProperty(DRIVER_PROPERTY_NAME);

        String db = (rdbms != null) ? rdbms : driver;
        if (db != null) {
            if (db.toLowerCase().contains("postgres")) return postgresClass;
            if (db.toLowerCase().contains("oracle")) return oracleClass;
        }
        return defaultClass;

    }

    /**
     * Jackrabbit calls database vendor as schema , jackrabbit needs this variable
     * 
     * @return
     */
    private String selectSchema() {
        String rdbms = properties.getProperty(RDBMS_PROPERTY_NAME);
        String driver = properties.getProperty(DRIVER_PROPERTY_NAME);

        String db = (rdbms != null) ? rdbms : driver;
        if (db != null) {
            if (db.toLowerCase().contains("postgres")) return "postgresql";
            if (db.toLowerCase().contains("oracle")) return "oracle";
        }
        return db;

    }
}
