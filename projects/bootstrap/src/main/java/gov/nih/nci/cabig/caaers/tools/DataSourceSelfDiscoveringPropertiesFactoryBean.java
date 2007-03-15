package gov.nih.nci.cabig.caaers.tools;

import gov.nih.nci.cabig.caaers.CaaersConfigurationException;
import gov.nih.nci.cabig.caaers.tools.hibernate.ImprovedPostgreSQLDialect;
import org.springframework.beans.factory.FactoryBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * This class searches through the following directories, looking for a file named
 * ${databaseConfigurationName}.properties:
 * <ul>
 *   <li><kbd>${catalina.home}/conf/caaers</kbd></li>
 *   <li><kbd>${user.home}/.caaers</kbd></li>
 *   <li><kbd>/etc/caaers</kbd></li>
 * </ul>
 *
 * It loads this file and returns it when {@link #getObject} is called.
 *
 * @author Rhett Sutphin
 */
public class DataSourceSelfDiscoveringPropertiesFactoryBean implements FactoryBean {
    private static final Log log = LogFactory.getLog(DataSourceSelfDiscoveringPropertiesFactoryBean.class);

    public static final String HIBERNATE_DIALECT_PROPERTY_NAME = "hibernate.dialect";
    public static final String RDBMS_PROPERTY_NAME = "datasource.rdbms";
    public static final String DRIVER_PROPERTY_NAME = "datasource.driver";
    public static final String URL_PROPERTY_NAME = "datasource.url";
    public static final String USERNAME_PROPERTY_NAME = "datasource.username";
    public static final String PASSWORD_PROPERTY_NAME = "datasource.password";
    
    public static final String DEFAULT_POSTGRESQL_DIALECT = ImprovedPostgreSQLDialect.class.getName();
    public static final String DEFAULT_DB_CONFIGURATION_NAME = "datasource";

    private String databaseConfigurationName;
    private Properties properties;
    private Properties defaults;

    public DataSourceSelfDiscoveringPropertiesFactoryBean() {
        defaults = new Properties();
    }

    ////// FACTORYBEAN IMPLEMENTATION

    public synchronized Object getObject() throws Exception {
        if (properties == null) {
            initProperties();
        }
        return properties;
    }

    private void initProperties() {
        findProperties();
        computeProperties();
    }

    protected List<File> searchDirectories() {
        String userHome = System.getProperty("user.home");
        String catalinaHome = System.getProperty("catalina.home");

        List<File> dirs = new LinkedList<File>();

        if (catalinaHome != null) {
            dirs.add(new File(catalinaHome, "conf/caaers"));
        } else {
            log.debug("catalina.home not set -- will not search");
        }

        if (userHome != null) {
            dirs.add(new File(userHome, ".caaers"));
        } else {
            log.debug("user.home not set -- will not search");
        }

        dirs.add(new File("/etc/caaers"));

        return dirs;
    }

    private void findProperties() {
        String propfileName = getDatabaseConfigurationName() + ".properties";
        List<String> searchedLocations = new LinkedList<String>();
        for (File dir : searchDirectories()) {
            File possiblePath = new File(dir, propfileName);
            String abspath = possiblePath.getAbsolutePath();
            if (log.isDebugEnabled()) log.debug("Looking in " + abspath);
            if (possiblePath.exists()) {
                readProperties(possiblePath);
                return;
            } else {
                searchedLocations.add(abspath);
                if (log.isDebugEnabled()) log.debug("Not found in " + abspath);
            }
        }
        throw new CaaersConfigurationException("Datasource configuration not found.  Looked in " + searchedLocations + '.');
    }

    private void readProperties(File path) {
        try {
            log.info("Loading datasource properties from " + path.getAbsolutePath());
            properties = new Properties(getDefaults());
            properties.load(new FileInputStream(path));
        } catch (IOException e) {
            throw new CaaersConfigurationException(
                "Could not read datasource configuration from " + path.getAbsolutePath()
                    + ". (" + e.getMessage() + ')', e);
        }
    }

    private void computeProperties() {
        String dialect = selectHibernateDialect();
        if (dialect != null) properties.setProperty(HIBERNATE_DIALECT_PROPERTY_NAME, dialect);
    }

    private String selectHibernateDialect() {
        String explicit = properties.getProperty(HIBERNATE_DIALECT_PROPERTY_NAME);
        if (explicit != null) return explicit;

        String rdbms = properties.getProperty(RDBMS_PROPERTY_NAME);
        if (rdbms != null) {
            if (rdbms.toLowerCase().contains("postgres")) return DEFAULT_POSTGRESQL_DIALECT;
            return null;
        }

        String driver = properties.getProperty(DRIVER_PROPERTY_NAME);
        if (driver != null) {
            if (driver.toLowerCase().contains("postgres")) return DEFAULT_POSTGRESQL_DIALECT;
            return null;
        }

        return null;
    }

    public Class getObjectType() {
        return Properties.class;
    }

    public boolean isSingleton() {
        return true;
    }

    ////// CONFIGURATION

    public String getDatabaseConfigurationName() {
        return StringUtils.isBlank(databaseConfigurationName)
            ? DEFAULT_DB_CONFIGURATION_NAME
            : databaseConfigurationName;
    }

    public void setDatabaseConfigurationName(String databaseConfigurationName) {
        this.databaseConfigurationName = databaseConfigurationName;
    }

    // defaults are intended mainly for testing dynamically guessed properties
    public Properties getDefaults() {
        return defaults;
    }

    public void setDefaults(Properties defaults) {
        this.defaults = defaults;
    }
}
