package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.ParticipantIndex;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class ParticipantIndexDao extends CaaersDao<ParticipantIndex> {

    private JdbcTemplate jdbcTemplate;
    
    private Properties properties;
    
    private static final String DB_NAME  = "databaseName";
    
    private static final String ORACLE_DB = "oracle";
    
	@Override
	public Class<ParticipantIndex> domainClass() {
		// TODO Auto-generated method stub
		return null;
	}
	
    @Transactional(readOnly = false)
    public void save(final ParticipantIndex participantIndex) {
        getHibernateTemplate().saveOrUpdate(participantIndex);
    }
    
    @Override
    @Transactional(readOnly = false)
    public int[] updateIndex(final List pIds , final String userName){
    	String sql = "insert into participant_index (login_id,participant_id) "
            + "values (?,?)";
    	
        String dataBase = "";
    	if(properties.getProperty(DB_NAME) != null){
    		dataBase = properties.getProperty(DB_NAME);
    	}
    	if(dataBase.equals(ORACLE_DB))
    		sql = "insert into participant_index (id,login_id,participant_id) "
                + "values (seq_participant_index_id.NEXTVAL,?,?,?,?)";
    	
    	
		BatchPreparedStatementSetter setter = null;
        setter = new BatchPreparedStatementSetter() {

            public int getBatchSize() {
                return pIds.size();
            }

            public void setValues(PreparedStatement ps, int index) throws SQLException {
            	Integer pId = (Integer) pIds.get(index);
            	ps.setString(1, userName);
                ps.setInt(2, pId);
            }


        };
        return jdbcTemplate.batchUpdate(sql, setter);
    	
    }
 
    @Override
    @Transactional(readOnly = false)
    public void clearIndex(String userName) {
    	String sql = "delete from participant_index where login_id = '"+userName+"'";
    	jdbcTemplate.update(sql);

    }

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}
