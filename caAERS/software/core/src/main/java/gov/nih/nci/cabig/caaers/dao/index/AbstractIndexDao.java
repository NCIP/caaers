/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.index;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import gov.nih.nci.cabig.caaers.CollectionUtil;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractIndexDao extends JdbcDaoSupport {


    private Properties properties;
    
    public static final String DB_NAME  = "databaseName";
    
    public static final String ORACLE_DB = "oracle";
    

	public Properties getProperties() {
		return properties;
	}

    public void setProperties(Properties properties) {
		this.properties = properties;
	}

    /**
     * Will return the index table name
     * @return
     */
    public abstract String indexTableName();

    /**
     * Will return the entity ID column
     * @return
     */
    public abstract String entityIdColumnName();

    /**
     * Will return the sequence name. 
     * @return
     */
    public abstract String sequenceName();
    
    public void deleteByEntityIdColumn(String id) {
    	String sql = "delete from "+indexTableName() + " where "+entityIdColumnName() + " = " + id;
    	//System.out.println(sql);
    	getJdbcTemplate().execute(sql);
    }

    /**
     * Will return all the available index entries for a login-id
     * @param loginId
     * @return IndexEntry objects
     */

    public List<IndexEntry> queryAllIndexEntries(String loginId){
        StringBuffer sb = new StringBuffer("select ")
                .append(entityIdColumnName()).append(", role_code")
                .append(" from ")
                .append(indexTableName())
                .append(" where login_id = '")
                .append(loginId)
                .append("' order by role_code");
        final HashMap<UserGroupType, IndexEntry> entryMap = new HashMap<UserGroupType, IndexEntry>();
        getJdbcTemplate().query(sb.toString(), new RowMapper(){
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                int roleCode = rs.getInt(2);
                UserGroupType ug = UserGroupType.getByCode(roleCode);
                IndexEntry entry = entryMap.get(ug);
                if(entry == null){
                   entry = new IndexEntry(ug);
                   entryMap.put(ug, entry);
                }
                entry.addEntityId(rs.getInt(1));
                return null;
            }

            public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
                int roleCode = rs.getInt(2);
                UserGroupType ug = UserGroupType.getByCode(roleCode);
                IndexEntry entry = entryMap.get(ug);
                if(entry == null){
                   entry = new IndexEntry(ug);
                   entryMap.put(ug, entry);
                }
                entry.addEntityId(rs.getInt(1));
                return null;
            }
        });
       
        return new ArrayList<IndexEntry>(entryMap.values());
    }


    /**
     * Will figureout the entries to delete
     * Will figureout the entries to add
     * @param userName - the login name of the user
     * @param roleCode - The role code from UserGroupType
     * @param nEntry   - New index entry fetched
     * @param oEntry   - Availabe index entry
     */
    @Transactional(readOnly = false)
    public void updateIndex(final String userName, final Integer roleCode, IndexEntry nEntry, IndexEntry oEntry) {

        boolean emptyNewEntries = nEntry == null || CollectionUtils.isEmpty(nEntry.getEntityIds());
        boolean emptyOldEntries = oEntry == null || CollectionUtils.isEmpty(oEntry.getEntityIds());

        if(emptyNewEntries && emptyOldEntries) return; //nothing to add or remove so return


        String dataBase = "";
    	if(this.getProperties().getProperty(DB_NAME) != null){
    		dataBase = getProperties().getProperty(DB_NAME);
    	}
        boolean oracleDB = dataBase.equals(ORACLE_DB);

        List<Integer> insertList = null;

        if(!emptyNewEntries){
            if(emptyOldEntries){
                insertList = nEntry.getEntityIds();
            }else{
                insertList = CollectionUtil.subtract(nEntry.getEntityIds(), oEntry.getEntityIds());
            }
        }

        StringBuffer insertBaseQuery = new StringBuffer("insert into ").append(indexTableName()).append(" ( ")
                .append(oracleDB ? "id, ": "").append("login_id,").append(entityIdColumnName()).append(",")
                .append("role_code) values (").append(oracleDB ? sequenceName() + ".NEXTVAL," : "").append("?,?,? )");



    	//insert if needed
        if(CollectionUtils.isNotEmpty(insertList)){
            final List<Integer> toAdd = insertList;
            BatchPreparedStatementSetter setter =  new BatchPreparedStatementSetter() {

                public int getBatchSize() {
                    return toAdd.size();
                }

                public void setValues(PreparedStatement ps, int index) throws SQLException {
                    ps.setString(1, userName);
                    ps.setInt(2, (Integer) toAdd.get(index));
                    ps.setInt(3, roleCode);
                }


            };

            getJdbcTemplate().batchUpdate(insertBaseQuery.toString(), setter);
        }


        StringBuffer deleteBaseQuery = new StringBuffer("delete from ").append(indexTableName())
                .append(" where ").append("login_id = '").append(userName).append("' and role_code = ").append(roleCode);


        if(!emptyOldEntries){
            if(!emptyNewEntries){
                List<Integer> deleteList = CollectionUtil.subtract(oEntry.getEntityIds(), nEntry.getEntityIds());
                //delete specific entries ?
                if(CollectionUtils.isNotEmpty(deleteList)){
                    deleteBaseQuery.append(" and ").append(entityIdColumnName()).append(" in ( 0");
                    for(Integer i : deleteList) {
                        deleteBaseQuery.append(",").append(i);
                    }
                    deleteBaseQuery.append(")");
                    getJdbcTemplate().update(deleteBaseQuery.toString());
                }
            }else{
               getJdbcTemplate().update(deleteBaseQuery.toString());
            }
        }

        
    }



}
