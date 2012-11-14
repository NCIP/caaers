package gov.nih.nci.cabig.caaers.dao.index;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;

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
    	final String columns = "R_101,R_102,R_103,R_104,R_105,R_106,R_107,R_108,R_109,R_110,R_111,R_112,R_113,R_114,R_115,R_116,R_117,R_118,R_119,R_120,R_121,R_122,R_123,R_7942,R_7943";
    	
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
            	for ( String column : columns.split(",")) {
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


    /**
     * @param userName - the login name of the user
     * @param indexEntry - entries to insert into the database.
     */
    @Transactional(readOnly = false)
    public void updateIndex(final String userName, List<IndexEntry> indexEntry) {
    	
        String dataBase = "";
    	if(this.getProperties().getProperty(DB_NAME) != null){
    		dataBase = getProperties().getProperty(DB_NAME);
    	}
        boolean oracleDB = dataBase.equals(ORACLE_DB);
        
        // First clearup the records owned by this user.
        StringBuffer deleteBaseQuery = new StringBuffer("delete from ").append(indexTableName())
                .append(" where ").append("login_id = '").append(userName).append("'");
        
        getJdbcTemplate().execute(deleteBaseQuery.toString());
        
        
        boolean isAll = false;
        
        for (IndexEntry entry:indexEntry) {
        	
        	if ( entry.isAllSiteOrAllStudy()) {
        		
        		StringBuffer columns = new StringBuffer("");
        		StringBuffer values = new StringBuffer("");
        		
        		// Populate the corresponding columns to true
        		for (UserGroupType type: entry.getRoles()) { 
        			columns = columns.append(type.dbAlias()).append(",");
        			if ( oracleDB) {
        				values = values.append(TRUE).append(",");
        			} else {
        				values = values.append(Boolean.TRUE).append(",");
        			}
        			
        		}
        		
        		if ( columns.toString().endsWith(",")) {
        			columns = columns.deleteCharAt(columns.toString().length() -1 );
        		}
        		
        		if ( values.toString().endsWith(",")) {
        			values = values.deleteCharAt(values.toString().length() -1 );
        		}
        			        		
        		StringBuffer isAllInsertBaseQuery = new StringBuffer("insert into ").append(indexTableName()).append(" (")
        	                .append(oracleDB ? "id, ": "").append("login_id,").append(entityIdColumnName()).append(",")
        	                .append(columns + ") ").append(" select ").append(oracleDB ? sequenceName() + ".NEXTVAL," : "").append("'"+userName+"'," + getIdColumnFromEntity() + ",")
        	                .append(values)
        					.append(" from " + entityTableName()); 
        					
        					
                getJdbcTemplate().execute(isAllInsertBaseQuery.toString());
        		
        		isAll = true;
        	} else {
        		if ( isAll ) { // issue update
        			
        			StringBuffer setCondition = new StringBuffer("");
            		
            		// Populate the corresponding columns to true
            		for (UserGroupType type: entry.getRoles()) { 
            			if ( oracleDB) {
            				setCondition = setCondition.append(type.dbAlias() + " = " + TRUE);
            			} else {
            				setCondition = setCondition.append(type.dbAlias() + " = " + Boolean.TRUE);
            			}
            			
            			setCondition.append(",");
            			
            		}
        			
        			if ( setCondition.toString().endsWith(",")) {
        				setCondition.deleteCharAt(setCondition.toString().length() -1);
        			}
        		
        			StringBuffer updateBaseQuery = new StringBuffer ( " Update " + indexTableName()  + " Set ")
        											.append(setCondition)
        											.append(" where login_id = '" + userName + "'" )
        											.append(" and " + entityIdColumnName() + " = " + entry.getEntityId() );
        			 getJdbcTemplate().execute(updateBaseQuery.toString());
        		} else { // Insert Query
        			        			
        			StringBuffer columns = new StringBuffer("");
            		StringBuffer values = new StringBuffer("");
            		
            		// Populate the corresponding columns to true
            		for (UserGroupType type: entry.getRoles()) { 
            			columns = columns.append(type.dbAlias()).append(",");
            			if ( oracleDB) {
            				values = values.append(TRUE).append(",");
            			} else {
            				values = values.append(Boolean.TRUE).append(",");
            			}
            			
            		}
            		
            		if ( columns.toString().endsWith(",")) {
            			columns = columns.deleteCharAt(columns.toString().length() -1 );
            		}
            		
            		if ( values.toString().endsWith(",")) {
            			values = values.deleteCharAt(values.toString().length() -1 );
            		}
            			        		
            		StringBuffer insertBaseQuery = new StringBuffer("insert into ").append(indexTableName()).append(" ( ")
            	                .append(oracleDB ? "id, ": "").append("login_id,").append(entityIdColumnName()).append(",")
            	                .append(columns + ") ").append(" values (").append(oracleDB ? sequenceName() + ".NEXTVAL," : "").append("'" + userName+"'," +  entry.getEntityId()  +",")
            	                .append(values)
            					.append(")"); 
            		
        			 getJdbcTemplate().execute(insertBaseQuery.toString());
        		}
        		
        	}
        	
        }

        
    }

}
