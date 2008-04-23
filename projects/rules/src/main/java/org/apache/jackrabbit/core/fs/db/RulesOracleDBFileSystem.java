package org.apache.jackrabbit.core.fs.db;

import gov.nih.nci.cabig.caaers.tools.CaaersDataSourcePropertiesFactoryBean;

import java.util.Properties;

public class RulesOracleDBFileSystem extends OracleFileSystem {

	public RulesOracleDBFileSystem() {

        CaaersDataSourcePropertiesFactoryBean b = new CaaersDataSourcePropertiesFactoryBean();

        Properties props = b.getProperties();

        this.driver = props.getProperty("datasource.driver");
        this.password = props.getProperty("datasource.password");
        this.user = props.getProperty("datasource.username");
        this.schema = props.getProperty("datasource.schema");
        this.schemaObjectPrefix = "rep_";
        this.url = props.getProperty("datasource.url");
    }
}
