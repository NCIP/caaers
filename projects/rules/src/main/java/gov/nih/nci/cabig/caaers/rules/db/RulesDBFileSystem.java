package gov.nih.nci.cabig.caaers.rules.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class RulesDBFileSystem extends org.apache.jackrabbit.core.fs.db.DbFileSystem{
	


	public RulesDBFileSystem() {
		
		
		Properties props = DbPropertyConfigurator.getProperties();

		this.driver = props.getProperty("datasource.driver");
		this.password = props.getProperty( "datasource.password");
		this.user = props.getProperty( "datasource.username");
		this.schema = props.getProperty( "datasource.schema");
		this.schemaObjectPrefix = "rep_";
		this.url = props.getProperty( "datasource.url");
	}
	
	
	
	/*
	@Override
	public void setDriver(String driver) {
		this.driver = props.getProperty("datasource.driver");
	}
	
	@Override
	public void setPassword(String password) {
		this.password = props.getProperty( "datasource.password");
	}
	
	@Override
	public void setUser(String user) {
		this.user = props.getProperty( "datasource.username");
	}

	@Override
	public void setSchema(String schema) {
		this.schema = props.getProperty( "datasource.schema");
	}

	@Override
	public void setSchemaObjectPrefix(String schemaObjectPrefix) {
		this.schemaObjectPrefix = "rep_";
	}

	@Override
	public void setUrl(String url) {
		this.url = props.getProperty( "datasource.url");
	}
	*/
}
