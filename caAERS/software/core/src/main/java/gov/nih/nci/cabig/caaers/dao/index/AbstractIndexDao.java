package gov.nih.nci.cabig.caaers.dao.index;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import gov.nih.nci.cabig.caaers.CollectionUtil;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
    
    public void deleteByEntityIdColumn(String id) {
    	String sql = "delete from "+indexTableName() + " where "+entityIdColumnName() + " = " + id;
    	//System.out.println(sql);
    	getJdbcTemplate().execute(sql);
    }

    /**
     * Lists out at what capacity a login have access to a particular entity.
     *
     * @param loginId   - The persons username
     * @param id   - The database id of the entity
     * @return   - a list of distinct rolenames
     */
    public List<String> findAssociatedRoleNames(String loginId, Integer id){
    	final String columns = UserGroupType.getAllRoleColumns();
        final String[] columnsArray = UserGroupType.getAllRoleColumnsArray();

        StringBuffer sb = new StringBuffer("select " +  columns + " from ")
                .append(indexTableName())
                .append(" where login_id = '").append(loginId)
                .append("' and ")
                .append(entityIdColumnName())
                .append(" = ")
                .append(String.valueOf(id));
        final List<String> roleNames = new ArrayList<String>();
        getJdbcTemplate().query(sb.toString(), new RowMapper(){
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            	for ( String column : columnsArray) {
            		boolean val = rs.getBoolean(column);
            		if ( val ) {
            			roleNames.add(UserGroupType.getByColumnName(column).getCsmName());
            		}
            	}
                return null;
            }
        });
        
        return roleNames;
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
        }   else {
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
        }

        List<IndexEntry> toInsert = diffMap.get("insert");
        List<String> insertQueries = new ArrayList<String>();
        if(!CollectionUtils.isEmpty(toInsert)){
            for(IndexEntry entry : toInsert) {
                insertQueries.add(generateSQL("insert",userName, entry, oracleDB));
            }
        }


        List<IndexEntry> toUpdate = diffMap.get("update");
        List<String> updateQueries = new ArrayList<String>();
        if(!CollectionUtils.isEmpty(toUpdate)){
            for(IndexEntry entry : toUpdate) {
                updateQueries.add(generateSQL("update",userName, entry, oracleDB));
            }
        }

        for(String sql : deleteQueries)  getJdbcTemplate().execute(sql);
        for(String sql : insertQueries)  getJdbcTemplate().execute(sql);
        getJdbcTemplate().batchUpdate(updateQueries.toArray(new String[]{}));

        
    }

    /**
     * Will return all the available index entries for a login-id
     * @param loginId
     * @return IndexEntry objects
     */

    public List<IndexEntry> queryAllIndexEntries(String loginId){
        StringBuffer sb = new StringBuffer("select ")
                .append(entityIdColumnName()).append(",").append(UserGroupType.getAllRoleColumns())
                .append(" from ")
                .append(indexTableName())
                .append(" where login_id = '")
                .append(loginId)
                .append("' order by ").append(entityIdColumnName());

        final List<IndexEntry> entries = new ArrayList<IndexEntry>();
        final String[] roleColumnNames = UserGroupType.getAllRoleColumnsArray();
        getJdbcTemplate().query(sb.toString(), new RowMapper(){
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                IndexEntry entry = new IndexEntry(rs.getInt(1));
                for(String roleColumnName : roleColumnNames){
                    boolean state = rs.getBoolean(roleColumnName);
                    if(state) entry.addRole(UserGroupType.getByColumnName(roleColumnName));
                }
                if(entry.hasRoles()) entries.add(entry);
                return null;
            }

        });

        return entries;
    }


    private Map<Integer, IndexEntry> toMap(List<IndexEntry> entries){
        Map<Integer, IndexEntry> map = new HashMap<Integer, IndexEntry>();
        for(IndexEntry entry : entries){
            if(map.containsKey(entry.getEntityId()) ) {
               map.get(entry.getEntityId()).addRoles(entry.getRoles());
            } else {
                map.put(entry.getEntityId(), entry);
            }
        }
        return map;
    }
    public String generateSQL(String operation, String userName, IndexEntry entry, boolean isOracle){
         if(operation.equals("delete-all")) {
             return "delete from " + indexTableName() + " where login_id ='" + userName + "'";
         }
         if(operation.equals("delete")){
             return "delete from " + indexTableName() + " where login_id = '" + userName + "' and " + entityIdColumnName() +  " = " + entry.getEntityId();
         }
        if(operation.equals("insert")){

            StringBuilder sb = new StringBuilder("insert into ")
                    .append(indexTableName())
                    .append("(").append(isOracle ? "id, ": "")
                    .append("login_id,")
                    .append(UserGroupType.getAllRoleColumns()).append(")")
                    .append(" values (").append(isOracle ? sequenceName() + ".NEXTVAL," : "").append(userName);

                for(int i = 0; i < UserGroupType.getAllRoleColumnsArray().length; i++){
                    sb.append(",");
                    UserGroupType role = UserGroupType.getByColumnName(UserGroupType.getAllRoleColumnsArray()[i]);
                    boolean checked = entry.hasRole(role);
                    if(checked) sb.append(isOracle? "1" : "true"); else sb.append(isOracle ? "0" : "false");
                }
               return sb.toString();
        }

        if(operation.equals("update")){

            StringBuilder sb = new StringBuilder("update ")
                    .append(indexTableName())
                    .append(" set ");

            for(int i = 0; i < UserGroupType.getAllRoleColumnsArray().length; i++){
                if(i > 0 ) sb.append(" ,");
                UserGroupType role = UserGroupType.getByColumnName(UserGroupType.getAllRoleColumnsArray()[i]);
                boolean checked = entry.hasRole(role);
                if(checked) sb.append(role.dbAlias()).append("=").append(isOracle? "1" : "true"); else sb.append(isOracle ? "0" : "false");
            }

            return sb.toString();
        }

        return null;
    }

}
