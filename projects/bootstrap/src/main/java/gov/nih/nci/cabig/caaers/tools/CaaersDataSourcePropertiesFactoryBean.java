package gov.nih.nci.cabig.caaers.tools;

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
public class CaaersDataSourcePropertiesFactoryBean extends DataSourceSelfDiscoveringPropertiesFactoryBean {
    public static final String QUARTZ_DELEGATE_PROPERTY_NAME= "jdbc.quartz.delegateClassName";
    public static final String SCHEMA_PROPERTY_NAME = "datasource.schema";

    public CaaersDataSourcePropertiesFactoryBean() {
        setApplicationDirectoryName("caaers");
    }

    /**
     * Sets reasonable defaults for caAERS-specific properties
     */
    @Override
    protected void computeProperties() {
        String quartzDelegateClass = selectQuartzDelegateClass();
        if(quartzDelegateClass != null) properties.setProperty(QUARTZ_DELEGATE_PROPERTY_NAME, quartzDelegateClass);
        String schema = selectSchema();
        if(schema != null) properties.setProperty(SCHEMA_PROPERTY_NAME, schema);
        //properties.setProperty(RULES_REPO_PROPERTY_NAME, selectRepository());
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
