/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import gov.nih.nci.cabig.caaers.domain.ConfigProperty;
import gov.nih.nci.cabig.caaers.domain.ConfigPropertyType;

/**
 * Will perform the CURD operations on SafetyReport ID.
 * 
 * @author vinodh.rc
 * 
 */
@Transactional
public class SafetyReportIDDao {
	
    private JdbcTemplate jdbcTemplate;
    
    private Properties properties;
    
    private Map<String, String> queries;
    
    private static final String DB_NAME  = "databaseName";

	/**
	 * Lists all ConfigProperty available against the {@link ConfigPropertyType}
	 * 
	 * @param type
	 *            - A ConfigPropertyType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getNewID() {
		
    	String dataBase = "postgres";
    	if(properties.getProperty(DB_NAME) != null){
    		dataBase = properties.getProperty(DB_NAME);
    	}
    	
		List<String> id = (List<String>) getJdbcTemplate().queryForList(queries.get(dataBase), String.class);
		if (id != null && !id.isEmpty()) {
			return id.get(0);
		}
		return null;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Map<String, String> getQueries() {
		return queries;
	}

	public void setQueries(Map<String, String> queries) {
		this.queries = queries;
	}
	
}
