package gov.nih.nci.cabig.caaers.dao.index;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import gov.nih.nci.cabig.caaers.domain.index.ExpeditedAdverseEventReportIndex;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.transaction.annotation.Transactional;

public class ExpeditedAdverseEventReportIndexDao extends AbstractIndexDao {
	@Override
	public Class<ExpeditedAdverseEventReportIndex> domainClass() {
		// TODO Auto-generated method stub
		return null;
	}
	
    @Transactional(readOnly = false)
    public void save(final ExpeditedAdverseEventReportIndex expeditedAdverseEventReportIndex) {
        getHibernateTemplate().saveOrUpdate(expeditedAdverseEventReportIndex);
    }
    
    @Override
    @Transactional(readOnly = false)
    public int[] updateIndex(final List pIds , final String userName){
    	String sql = "insert into expedited_ae_index (login_id,expedited_ae_id) "
            + "values (?,?)";
    	
        String dataBase = "";
    	if(this.getProperties().getProperty(DB_NAME) != null){
    		dataBase = getProperties().getProperty(DB_NAME);
    	}
    	if(dataBase.equals(ORACLE_DB))
    		sql = "insert into expedited_ae_index (id,login_id,expedited_ae_id) "
                + "values (seq_expedited_ae_index_id.NEXTVAL,?,?,?,?)";
    	
    	
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
    	String sql = "delete from expedited_ae_index where login_id = '"+userName+"'";
    	getJdbcTemplate().update(sql);

    }
}
