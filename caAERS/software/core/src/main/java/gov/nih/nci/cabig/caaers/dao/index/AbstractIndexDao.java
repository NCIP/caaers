package gov.nih.nci.cabig.caaers.dao.index;

import java.util.List;
import java.util.Properties;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class AbstractIndexDao extends JdbcDaoSupport {


    private Properties properties;
    
    public static final String DB_NAME  = "databaseName";
    
    public static final String ORACLE_DB = "oracle";
    

	public Properties getProperties() {
		return properties;
	}

    public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public int[] updateIndex(final List pIds , final String userName, final Integer roleCode){
		return null;
	}
	public void clearIndex(String userName) {

	}

}
