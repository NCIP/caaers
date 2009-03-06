package org.apache.jackrabbit.core.persistence.db;

import gov.nih.nci.cabig.caaers.tools.CaaersDataSourcePropertiesFactoryBean;

import java.util.Properties;

public class RulesVersioningDBPersistanceManager extends SimpleDbPersistenceManager {

    public RulesVersioningDBPersistanceManager() {

        CaaersDataSourcePropertiesFactoryBean b = CaaersDataSourcePropertiesFactoryBean.getLoadedInstance();

        Properties props = b.getProperties();

        this.driver = props.getProperty("datasource.driver");
        this.password = props.getProperty("datasource.password");
        this.user = props.getProperty("datasource.username");
        this.schema = props.getProperty("datasource.schema");
        this.schemaObjectPrefix = "rep_ver_";
        this.url = props.getProperty("datasource.url");
        this.externalBLOBs = false;
    }

}