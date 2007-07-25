package gov.nih.nci.cabig.caaers.rules.db;

import java.util.Properties;

import gov.nih.nci.cabig.caaers.tools.DataSourceSelfDiscoveringPropertiesFactoryBean;

import org.apache.jackrabbit.core.state.db.SimpleDbPersistenceManager;

@SuppressWarnings("deprecation")
public class RulesDBPersistanceManager extends SimpleDbPersistenceManager {


	
	public RulesDBPersistanceManager() {

		Properties props = DbPropertyConfigurator.getProperties();

		this.driver = props.getProperty("datasource.driver");
		this.password = props.getProperty( "datasource.password");
		this.user = props.getProperty( "datasource.username");
		this.schema = props.getProperty( "datasource.schema");
		this.schemaObjectPrefix = "rep_";
		this.url = props.getProperty( "datasource.url");
		this.externalBLOBs = false;
	}


	
	
	
	
}
