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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractIndexDao extends JdbcDaoSupport {

	private static final Log log = LogFactory.getLog(AbstractIndexDao.class);

    private Properties properties;
    
    public static final String DB_NAME  = "databaseName";
    
    public static final String ORACLE_DB = "oracle";
    
    public static final String TRUE ="1";
    

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
     *  Will return the Entity.
     */
    public abstract String entityTableName();
    /**
     * Will return the entity ID column
     * @return
     */
    public abstract String entityIdColumnName();
    
    /** 
     * Will return the
     */
    public abstract String getIdColumnFromEntity();
    
    /**
     * Will return the sequence name. 
     * @return
     */
    public abstract String sequenceName();


    /**
     * Lists out at what capacity a login have access to a particular entity.
     *
     * @param loginId   - The persons username
     * @param id   - The database id of the entity
     * @return   - a list of distinct rolenames
     */
    public List<String> findAssociatedRoleNames(String loginId, Integer id){
        List<String> roleNames = new ArrayList<String>();
        int  role = findAssociatedRole(loginId, id);
        if(role <= 0 ) return roleNames;
        List<UserGroupType> groups =  UserGroupType.roles(role);
        for(UserGroupType group : groups) roleNames.add(group.getCsmName());
        return roleNames;
    }

    public int findAssociatedRole(String loginId, Integer entityId){
        final int[] roleValue = new int[]{0};
        StringBuffer sb = new StringBuffer("select role from ")
                .append(indexTableName())
                .append(" where login_id = '").append(loginId)
                .append("' and (")
                .append(entityIdColumnName())
                .append(" = ")
                .append(String.valueOf(entityId));
        if(entityId > Integer.MIN_VALUE){
            sb.append(" or ").append(entityIdColumnName()).append(" = ").append(Integer.MIN_VALUE);
        }
        sb.append(")");

        getJdbcTemplate().query(sb.toString(), new RowMapper(){
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                roleValue[0] |= rs.getInt(1);
               return null;
            }
        });
        return roleValue[0];
    }

    public Map<String ,List<IndexEntry>> diff(final String userName, List<IndexEntry> newEntries){
        Map<String, List<IndexEntry>> map = new HashMap<String, List<IndexEntry>>();
        List<IndexEntry> entriesToUpdate = new ArrayList<IndexEntry>();
        List<IndexEntry> entriesToInsert = new ArrayList<IndexEntry>();
        List<IndexEntry> entriesToDelete = new ArrayList<IndexEntry>();
        map.put("insert", entriesToInsert);
        map.put("update", entriesToUpdate);
        map.put("delete", entriesToDelete);

        //find existing index entries
        List<IndexEntry> existingEntries = queryAllIndexEntries(userName);
        if(CollectionUtils.isEmpty(newEntries)){
              if(!CollectionUtils.isEmpty(existingEntries)) entriesToDelete.addAll(existingEntries);
        } else {
             if(CollectionUtils.isEmpty(existingEntries)) {
                 entriesToInsert.addAll(newEntries);
             } else {

                 Map<Integer, IndexEntry> newEntryMap =  toMap(newEntries);
                 Map<Integer, IndexEntry> existingEntryMap =  toMap(existingEntries);


                 List<Integer> toInsert = CollectionUtil.subtract(newEntryMap.keySet(), existingEntryMap.keySet());
                 for(Integer entityId : toInsert){
                     entriesToInsert.add(newEntryMap.get(entityId));
                 }
                 List<Integer> toDelete = CollectionUtil.subtract(existingEntryMap.keySet(), newEntryMap.keySet());
                 for(Integer entityId : toDelete){
                     entriesToDelete.add(existingEntryMap.get(entityId));
                 }

                 List<Integer> toCheck = CollectionUtil.subtract(newEntryMap.keySet(), toInsert);
                 for(Integer entityId : toCheck){
                     IndexEntry newEntry = newEntryMap.get(entityId);
                     IndexEntry existingEntry = existingEntryMap.get(entityId);
                     if(!newEntry.equals(existingEntry)){
                         entriesToUpdate.add(newEntry);
                     }
                 }
             }
        }

        return map;

    }

    /**
     * @param userName - the login name of the user
     * @param indexEntries - entries to insert into the database.
     */
    @Transactional(readOnly = false)
    public void updateIndex(final String userName, List<IndexEntry> indexEntries) {
        String dataBase = "";
        if(this.getProperties().getProperty(DB_NAME) != null){
            dataBase = getProperties().getProperty(DB_NAME);
        }
        boolean oracleDB = dataBase.equals(ORACLE_DB);

        Map<String, List<IndexEntry>> diffMap = diff(userName, indexEntries);

        final List<IndexEntry> toInsert = diffMap.get("insert");
        if(!CollectionUtils.isEmpty(toInsert)){

            BatchPreparedStatementSetter setter =  new BatchPreparedStatementSetter() {

                public void setValues(PreparedStatement ps, int index) throws SQLException {
                    IndexEntry entry = toInsert.get(index);
                    ps.setString(1, userName);
                    ps.setInt(2, entry.getEntityId());
                    ps.setInt(3, entry.getPrivilege());
                }

                public int getBatchSize() {
                    return toInsert.size();
                }
            };

            if(log.isInfoEnabled()){
                log.info("Inserting : " + toInsert.size() + " records");
                log.info(toInsert.toString());
            }

            getJdbcTemplate().batchUpdate(generateSQLInsertTemplate(oracleDB).toString(), setter);
        }

        final List<IndexEntry> toUpdate = diffMap.get("update");
        if(!CollectionUtils.isEmpty(toUpdate)){
            BatchPreparedStatementSetter setter =  new BatchPreparedStatementSetter() {

                public void setValues(PreparedStatement ps, int index) throws SQLException {
                    IndexEntry entry = toUpdate.get(index);
                    ps.setInt(1, entry.getPrivilege());
                    ps.setString(2, userName);
                    ps.setInt(3 , entry.getEntityId());
                }

                public int getBatchSize() {
                    return toUpdate.size();
                }
            };

            if(log.isInfoEnabled()){
                log.info("Updating : " + toUpdate.size() + " records");
                log.info(toUpdate.toString());
            }

            getJdbcTemplate().batchUpdate(generateSQLUpdateTemplate(oracleDB).toString(), setter);
        }



        List<String> deleteQueries = new ArrayList<String>();
    	List<IndexEntry> toDelete = diffMap.get("delete");
        if(!CollectionUtils.isEmpty(toDelete)){
            if(CollectionUtils.isEmpty(indexEntries)) {
                deleteQueries.add(generateSQL("delete-all",userName, null, oracleDB));
            } else {
                for(IndexEntry entry : toDelete) {
                    deleteQueries.add(generateSQL("delete",userName, entry, oracleDB));
                }
            }

            getJdbcTemplate().batchUpdate(deleteQueries.toArray(new String[]{}));
            if(log.isInfoEnabled()){
                log.info("Deleting : " + deleteQueries.size() + " records");
                log.info(deleteQueries.toString());
            }
        }


        
    }

    /**
     * Will return all the available index entries for a login-id
     * @param loginId
     * @return IndexEntry objects
     */

    public List<IndexEntry> queryAllIndexEntries(String loginId){
        StringBuffer sb = new StringBuffer("select ")
                .append(entityIdColumnName()).append(", role")
                .append(" from ")
                .append(indexTableName())
                .append(" where login_id = '")
                .append(loginId).append("'") ;

        final List<IndexEntry> entries = new ArrayList<IndexEntry>();
        getJdbcTemplate().query(sb.toString(), new RowMapper(){
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                IndexEntry entry = new IndexEntry(rs.getInt(1), rs.getInt(2));
                entries.add(entry);
                return null;
            }

        });

        return entries;
    }


    private Map<Integer, IndexEntry> toMap(List<IndexEntry> entries){
        Map<Integer, IndexEntry> map = new HashMap<Integer, IndexEntry>();
        for(IndexEntry entry : entries){
            if(map.containsKey(entry.getEntityId()) ) {
               map.get(entry.getEntityId()).orPrivilege(entry.getPrivilege());
            } else {
                map.put(entry.getEntityId(), entry);
            }
        }
        return map;
    }

    public String generateSQLInsertTemplate(boolean  isOracle){
        StringBuilder sb = new StringBuilder("insert into ")
                .append(indexTableName())
                .append("(").append(isOracle ? "id, ": "")
                .append("login_id,")
                .append(entityIdColumnName()).append(",")
                .append("role").append(")")
                .append(" values (").append(isOracle ? sequenceName() + ".NEXTVAL," : "")
                .append("?,?,?)");
        return sb.toString();
    }

    public String generateSQLUpdateTemplate(boolean isOracle){
        StringBuilder sb = new StringBuilder("update ")
                .append(indexTableName())
                .append(" set role = ? ")
                .append(" where login_id = ? and ").append(entityIdColumnName()).append(" = ?");
        return sb.toString();
    }

    public String generateSQL(String operation, String userName, IndexEntry entry, boolean isOracle){
         if(operation.equals("delete-all")) {
             return "delete from " + indexTableName() + " where login_id ='" + userName + "'";
         }
         if(operation.equals("delete")){
             return "delete from " + indexTableName() + " where login_id = '" + userName + "' and " + entityIdColumnName() +  " = " + entry.getEntityId();
         }

        return null;
    }


}
