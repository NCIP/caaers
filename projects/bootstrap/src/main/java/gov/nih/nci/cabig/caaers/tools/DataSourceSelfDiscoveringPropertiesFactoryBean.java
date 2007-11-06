package gov.nih.nci.cabig.caaers.tools;

import gov.nih.nci.cabig.caaers.CaaersConfigurationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.FactoryBean;

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
public class DataSourceSelfDiscoveringPropertiesFactoryBean extends DatabaseConfigurationAccessor implements FactoryBean {
    private static final Log log = LogFactory.getLog(DataSourceSelfDiscoveringPropertiesFactoryBean.class);

    public static final String HIBERNATE_DIALECT_PROPERTY_NAME = "hibernate.dialect";
    public static final String RDBMS_PROPERTY_NAME = "datasource.rdbms";
    public static final String DRIVER_PROPERTY_NAME = "datasource.driver";
    public static final String URL_PROPERTY_NAME = "datasource.url";
    public static final String USERNAME_PROPERTY_NAME = "datasource.username";
    public static final String PASSWORD_PROPERTY_NAME = "datasource.password";
    public static final String QUARTZ_DELEGATE_PROPERTY_NAME= "jdbc.quartz.delegateClassName";
    public static final String DEFAULT_POSTGRESQL_DIALECT
        = "edu.northwestern.bioinformatics.bering.dialect.hibernate.ImprovedPostgreSQLDialect";
    public static final String SCHEMA_PROPERTY_NAME = "datasource.schema";

    private Properties properties;
    private Properties defaults;

    public DataSourceSelfDiscoveringPropertiesFactoryBean() {
        defaults = new Properties();
    }

    ////// FACTORYBEAN IMPLEMENTATION

    public synchronized Object getObject() throws Exception {
        return getProperties();
    }

    public synchronized Properties getProperties() {
        if (properties == null) {
            initProperties();
        }
        //properties.list(System.out);
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
        
        String quartzDelegateClass = selectQuartzDelegateClass();
        if(quartzDelegateClass != null) properties.setProperty(QUARTZ_DELEGATE_PROPERTY_NAME, quartzDelegateClass);
        String schema = selectSchema();
        if(schema != null) properties.setProperty(SCHEMA_PROPERTY_NAME, schema);
        //properties.setProperty(RULES_REPO_PROPERTY_NAME, selectRepository());
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

    // defaults are intended mainly for testing dynamically guessed properties
    public Properties getDefaults() {
        return defaults;
    }

    public void setDefaults(Properties defaults) {
        this.defaults = defaults;
    }
    
    /**
     * To determin the quartz delegate class to use
     * @return
     */
    private String selectQuartzDelegateClass(){
    	////hibernate for time being will use default (http://jira.opensymphony.com/browse/QUARTZ-560)
    	String defaultClass = "org.quartz.impl.jdbcjobstore.StdJDBCDelegate"; 
    	String postgresClass = "org.quartz.impl.jdbcjobstore.PostgreSQLDelegate";
    	String oracleClass = "org.quartz.impl.jdbcjobstore.oracle.OracleDelegate";
    	String rdbms = properties.getProperty(RDBMS_PROPERTY_NAME);
    	String driver = properties.getProperty(DRIVER_PROPERTY_NAME);
    	
    	String db = (rdbms != null) ? rdbms : driver;
    	if(db != null){ 
    		if(db.toLowerCase().contains("postgres")) return postgresClass;
    		if(db.toLowerCase().contains("oracle")) return oracleClass;
    	}
    	return defaultClass;
    	
    }
    /**
     * Jackrabbit calls database vendor as schema , jackrabbit needs this variable
     * @return
     */
    private String selectSchema() {
    	String rdbms = properties.getProperty(RDBMS_PROPERTY_NAME);
    	String driver = properties.getProperty(DRIVER_PROPERTY_NAME);
    	
    	String db = (rdbms != null) ? rdbms : driver;
    	if(db != null){ 
    		if(db.toLowerCase().contains("postgres")) return "postgresql";
    		if(db.toLowerCase().contains("oracle")) return "oracle";
    	}
    	return db;   	
    	
    }
}
