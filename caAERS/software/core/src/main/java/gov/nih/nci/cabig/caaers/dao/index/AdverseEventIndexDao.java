package gov.nih.nci.cabig.caaers.dao.index;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import gov.nih.nci.cabig.caaers.domain.index.AdverseEventIndex;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.transaction.annotation.Transactional;

public class AdverseEventIndexDao extends AbstractIndexDao {
	@Override
	public Class<AdverseEventIndex> domainClass() {
		// TODO Auto-generated method stub
		return null;
	}
	
    @Transactional(readOnly = false)
    public void save(final AdverseEventIndex adverseEventIndex) {
        getHibernateTemplate().saveOrUpdate(adverseEventIndex);
    }
    
    @Override
    @Transactional(readOnly = false)
    public int[] updateIndex(final List pIds , final String userName){
    	String sql = "insert into adverseevent_index (login_id,adverseevent_id) "
            + "values (?,?)";
    	
        String dataBase = "";
    	if(this.getProperties().getProperty(DB_NAME) != null){
    		dataBase = getProperties().getProperty(DB_NAME);
    	}
    	if(dataBase.equals(ORACLE_DB))
    		sql = "insert into adverseevent_index (id,login_id,adverseevent_id) "
                + "values (seq_adverseevent_index_id.NEXTVAL,?,?,?,?)";
    	
    	
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
        return this.getJdbcTemplate().batchUpdate(sql, setter);
    	
    }
    
	
    @Override
    @Transactional(readOnly = false)
    public void clearIndex(String userName) {
    	String sql = "delete from adverseevent_index where login_id = '"+userName+"'";
    	getJdbcTemplate().update(sql);

    }


}
