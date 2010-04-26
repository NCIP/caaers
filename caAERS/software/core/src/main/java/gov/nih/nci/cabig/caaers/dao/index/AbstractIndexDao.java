package gov.nih.nci.cabig.caaers.dao.index;

import gov.nih.nci.cabig.caaers.dao.CaaersDao;

import java.util.Properties;

import org.springframework.jdbc.core.JdbcTemplate;

public class AbstractIndexDao extends CaaersDao{

    private JdbcTemplate jdbcTemplate;
    
    private Properties properties;
    
    public static final String DB_NAME  = "databaseName";
    
    public static final String ORACLE_DB = "oracle";
    
	@Override
	public Class domainClass() {
		// TODO Auto-generated method stub
		return null;
	}


	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public Properties getProperties() {
		return properties;
	}
	

}
