package gov.nih.nci.cabig.caaers.rules.db;

import gov.nih.nci.cabig.caaers.tools.DataSourceSelfDiscoveringPropertiesFactoryBean;

import java.util.Properties;

public class DbPropertyConfigurator {

	public static Properties getProperties() {
		
		DataSourceSelfDiscoveringPropertiesFactoryBean b = new DataSourceSelfDiscoveringPropertiesFactoryBean();
		
		Properties props = b.getProperties();
		
		return props;
	}

	
}
