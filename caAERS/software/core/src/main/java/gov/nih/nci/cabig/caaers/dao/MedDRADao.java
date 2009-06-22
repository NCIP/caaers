package gov.nih.nci.cabig.caaers.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Krikor Krumlian
 */
public class MedDRADao {
    protected final Log log = LogFactory.getLog(getClass());

    private JdbcTemplate jdbcTemplate;
    
    private Properties properties;
    
    private static final String DB_NAME  = "databaseName";
    
    private static final String ORACLE_DB = "oracle";

    /**
     * This method populates the meddra_llt table. It uses the meddra_llt.asc file for loading the data into this table.
     * Different sqls are used for postgres and oracle db.
     *  
     * @param llts
     * @param startIndex
     * @return
     */
    public int[] insertLowLevelTerms(final List llts, final int startIndex, final int version_id, final Map<String, Integer> codeToIdMap) {

        String sql = "insert into meddra_llt (meddra_code,meddra_term,meddra_pt_id,version_id) "
                        + "values (?,?,?,?)";
        
        String dataBase = "";
    	if(properties.getProperty(DB_NAME) != null){
    		dataBase = properties.getProperty(DB_NAME);
    	}
    	if(dataBase.equals(ORACLE_DB))
    		sql = "insert into meddra_llt (id,meddra_code,meddra_term,meddra_pt_id,version_id) "
                + "values (SEQ_MEDDRA_LLT_ID.NEXTVAL,?,?,?,?)";
        
        BatchPreparedStatementSetter setter = null;
        setter = new BatchPreparedStatementSetter() {

            public int getBatchSize() {
                return llts.size();
            }

            public void setValues(PreparedStatement ps, int index) throws SQLException {
                String[] llt = (String[]) llts.get(index);
                
                ps.setString(1, llt[0]);
                ps.setString(2, llt[1]);
                if(codeToIdMap.containsKey(llt[2]))
                	ps.setInt(3, (codeToIdMap.get(llt[2]).intValue()));
                else
                	ps.setInt(3, 0);
                ps.setInt(4, version_id);
            }
        };

        return jdbcTemplate.batchUpdate(sql, setter);

    }
    
    /**
     * This method populats the meddra_pt table. It uses the file meddra_pt.asc to load data into this table. Different sqls are used
     * for postgres and oracle db.
     * 
     * @param llts
     * @param startIndex
     * @return
     */
    public int[] insertPreferredTerms(final List llts, final int startIndex, final int version_id, final Map<String, Integer> codeToIdMap) {

        String sql = "insert into meddra_pt (meddra_code,meddra_term,meddra_soc_id,version_id) "
                        + "values (?,?,?,?)";
        
        String dataBase = "";
    	if(properties.getProperty(DB_NAME) != null){
    		dataBase = properties.getProperty(DB_NAME);
    	}
    	if(dataBase.equals(ORACLE_DB))
    		sql = "insert into meddra_pt (id,meddra_code,meddra_term,meddra_soc_id,version_id) "
                + "values (SEQ_MEDDRA_PT_ID.NEXTVAL,?,?,?,?)";

        BatchPreparedStatementSetter setter = null;
        setter = new BatchPreparedStatementSetter() {

            public int getBatchSize() {
                return llts.size();
            }

            public void setValues(PreparedStatement ps, int index) throws SQLException {
                String[] llt = (String[]) llts.get(index);
                ps.setString(1, llt[0]);
                ps.setString(2, llt[1]);
                if(codeToIdMap.containsKey(llt[3]))
                	ps.setInt(3, (codeToIdMap.get(llt[3]).intValue()));
                else
                	ps.setInt(3, 0);
                ps.setInt(4, version_id);
            }
        };

        return jdbcTemplate.batchUpdate(sql, setter);
    }
    
    /**
     * This method populates the meddra_hlt table. It uses the meddra_hlt.asc file to load data into this table. Different sqls are 
     * used for postgres and oracle db.
     * 
     * @param llts
     * @param startIndex
     * @return
     */
    public int[] insertHighLevelTerms(final List llts, final int startIndex, final int version_id) {

        String sql = "insert into meddra_hlt (meddra_code,meddra_term,version_id) " + "values (?,?,?)";

        String dataBase = "";
    	if(properties.getProperty(DB_NAME) != null){
    		dataBase = properties.getProperty(DB_NAME);
    	}
    	if(dataBase.equals(ORACLE_DB))
    		sql = "insert into meddra_hlt (id,meddra_code,meddra_term,version_id) " + "values (SEQ_MEDDRA_HLT_ID.NEXTVAL,?,?,?)";
    	
        BatchPreparedStatementSetter setter = null;
        setter = new BatchPreparedStatementSetter() {

            public int getBatchSize() {
                return llts.size();
            }

            public void setValues(PreparedStatement ps, int index) throws SQLException {
                String[] llt = (String[]) llts.get(index);
  
                ps.setString(1, llt[0]);
                ps.setString(2, llt[1]);
                ps.setInt(3, version_id);
            }
        };

        return jdbcTemplate.batchUpdate(sql, setter);
    }

    /**
     * This method populates the meddra_hlgt table. It uses meddra_hlgt.asc file to load data into this table. Different sqls are 
     * used for postgres and oracle db.
     * 
     * @param llts
     * @param startIndex
     * @return
     */
    public int[] insertHighLevelGroupTerms(final List llts, final int startIndex, final int version_id) {

        String sql = "insert into meddra_hlgt (meddra_code,meddra_term, version_id) " + "values (?,?,?)";

        String dataBase = "";
    	if(properties.getProperty(DB_NAME) != null){
    		dataBase = properties.getProperty(DB_NAME);
    	}
    	if(dataBase.equals(ORACLE_DB))
    		sql = "insert into meddra_hlgt (id,meddra_code,meddra_term, version_id) " + "values (SEQ_MEDDRA_HLGT_ID.NEXTVAL,?,?,?)";
    	
        BatchPreparedStatementSetter setter = null;
        setter = new BatchPreparedStatementSetter() {

            public int getBatchSize() {
                return llts.size();
            }

            public void setValues(PreparedStatement ps, int index) throws SQLException {
                String[] llt = (String[]) llts.get(index);
  
                ps.setString(1, llt[0]);
                ps.setString(2, llt[1]);
                ps.setInt(3, version_id);
            }
        };

        return jdbcTemplate.batchUpdate(sql, setter);
    }

    /**
     * This method is to populate codeToIdMap and keep it ready before loading PT.
     * 
     * @param version_id
     * @return
     */
    public Map<String,Integer> populateCodeToIdMap(final String table_name, final int version_id){
    	Map<String, Integer> resultMap = new HashMap<String, Integer>();
    	String sql = "select meddra_code, id from " + table_name + " where version_id = " + version_id;
    	
    	List<Map> preResult = jdbcTemplate.queryForList(sql);
		
		for (Map map : preResult) {
			String meddra_code = map.get("meddra_code").toString();
			String id = map.get("id").toString();
			if(!resultMap.containsKey(meddra_code)){
				resultMap.put(meddra_code, Integer.decode(id));
			}
		}
    	return resultMap;
    }
    
    /**
     * This method populates the meddra_soc table. It uses the meddra_soc.asc file to load data into this table. Different sqls 
     * are used for postgres and oracle db.
     * 
     * @param llts
     * @param startIndex
     * @return
     */
    public int[] insertSystemOrganClasses(final List llts, final int startIndex, final int version_id) {

        String sql = "insert into meddra_soc (meddra_code,meddra_term,version_id) " + "values (?,?,?)";

        String dataBase = "";
    	if(properties.getProperty(DB_NAME) != null){
    		dataBase = properties.getProperty(DB_NAME);
    	}
    	if(dataBase.equals(ORACLE_DB))
    		sql = "insert into meddra_soc (id,meddra_code,meddra_term,version_id) " + "values (SEQ_MEDDRA_SOC_ID.NEXTVAL,?,?,?)";
    	
    	BatchPreparedStatementSetter setter = null;
        setter = new BatchPreparedStatementSetter() {

            public int getBatchSize() {
                return llts.size();
            }

            public void setValues(PreparedStatement ps, int index) throws SQLException {
                String[] llt = (String[]) llts.get(index);
  
                ps.setString(1, llt[0]);
                ps.setString(2, llt[1]);
                ps.setInt(3, version_id);
            }
        };

        return jdbcTemplate.batchUpdate(sql, setter);
    }

    /**
     * This method loads the meddra_hlt_pt mapping table. New ids that were generated while loading data into the meddra_hlt and
     * meddra_pt tables are used. Earlier meddra_code were used as ids and supporting multiple versions was not possible in that case.
     * 
     * @param llts
     * @param startIndex
     * @param hltCodeToIdMap
     * @param ptCodeToIdMap
     * @return
     */
    public int[] insertHLTxPT(final List llts, final int startIndex, final int version_id, final Map<String, Integer> hltCodeToIdMap, final Map<String, Integer> ptCodeToIdMap) {
    	
    	String sql = "insert into meddra_hlt_pt (meddra_hlt_id, meddra_pt_id, version_id) values (?,?,?)";
    	
    	String dataBase = "";
    	if(properties.getProperty(DB_NAME) != null){
    		dataBase = properties.getProperty(DB_NAME);
    	}
    	if(dataBase.equals(ORACLE_DB))
    		sql = "insert into meddra_hlt_pt (id,meddra_hlt_id, meddra_pt_id, version_id) values (SEQ_MEDDRA_HLT_ID.NEXTVAL,?,?,?)";
    	
    	BatchPreparedStatementSetter setter = null;
        setter = new BatchPreparedStatementSetter() {

            int stIndex = startIndex;

            public int getBatchSize() {
                return llts.size();
            }

            public void setValues(PreparedStatement ps, int index) throws SQLException {
            	String[] llt = (String[]) llts.get(index);
  
            	ps.setInt(1, (hltCodeToIdMap.get(llt[0]).intValue()));
            	ps.setInt(2, (ptCodeToIdMap.get(llt[1]).intValue()));
            	ps.setInt(3, version_id);
            }
        };

        return jdbcTemplate.batchUpdate(sql, setter);
    }

    /**
     * This method loads the meddra_hlgt_hlt mapping table. New ids that were generated while loading data into meddra_hlgt and meddra_hlt
     * tables are used. Earlier meddra_code were used and supporting multiple versions was not possible in that case.
     *  
     * @param llts
     * @param startIndex
     * @param hlgtCodeToIdMap
     * @param hltCodeToIdMap
     * @return
     */
    public int[] insertHLGTxHLT(final List llts, final int startIndex, final int version_id, final Map<String,Integer> hlgtCodeToIdMap, final Map<String,Integer> hltCodeToIdMap){
    	
    	String sql = "insert into meddra_hlgt_hlt (meddra_hlgt_id, meddra_hlt_id, version_id) values (?,?,?)";
    	
    	String dataBase = "";
    	if(properties.getProperty(DB_NAME) != null){
    		dataBase = properties.getProperty(DB_NAME);
    	}
    	if(dataBase.equals(ORACLE_DB))
    		sql = "insert into meddra_hlgt_hlt (id,meddra_hlgt_id, meddra_hlt_id, version_id) values (SEQ_MEDDRA_HLGT_HLT_ID.NEXTVAL,?,?,?)";
    	
    	BatchPreparedStatementSetter setter = null;
        setter = new BatchPreparedStatementSetter() {

            int stIndex = startIndex;

            public int getBatchSize() {
                return llts.size();
            }

            public void setValues(PreparedStatement ps, int index) throws SQLException {
            	String[] llt = (String[]) llts.get(index);
  
            	ps.setInt(1, (hlgtCodeToIdMap.get(llt[0]).intValue()));
            	ps.setInt(2, (hltCodeToIdMap.get(llt[1]).intValue()));
            	ps.setInt(3, version_id);
            }
        };

        return jdbcTemplate.batchUpdate(sql, setter);
    }

    /**
     * This method loads the meddra_soc_hlgt mapping table. New ids that were generated while loading data into meddra_soc and meddra_hlgt
     * tables are used. Earlier meddra_code were used as ids and supporting multiple versions was not possible in that case.
     * 
     * @param llts
     * @param startIndex
     * @param socCodeToIdMap
     * @param hlgtCodeToIdMap
     * @return
     */
    public int[] insertSOCxHLGT(final List llts, final int startIndex, final int version_id, final Map<String,Integer> socCodeToIdMap, final Map<String,Integer> hlgtCodeToIdMap){
    	
    	String sql = "insert into meddra_soc_hlgt (meddra_soc_id, meddra_hlgt_id, version_id) values (?,?,?)";
    	
    	String dataBase = "";
    	if(properties.getProperty(DB_NAME) != null){
    		dataBase = properties.getProperty(DB_NAME);
    	}
    	if(dataBase.equals(ORACLE_DB))
    		sql = "insert into meddra_soc_hlgt (id,meddra_soc_id, meddra_hlgt_id, version_id) values (SEQ_MEDDRA_SOC_HLGT_ID.NEXTVAL,?,?,?)";
    	
    	BatchPreparedStatementSetter setter = null;
        setter = new BatchPreparedStatementSetter() {

            int stIndex = startIndex;

            public int getBatchSize() {
                return llts.size();
            }

            public void setValues(PreparedStatement ps, int index) throws SQLException {
            	String[] llt = (String[]) llts.get(index);
    
            	ps.setInt(1, (socCodeToIdMap.get(llt[0]).intValue()));
            	ps.setInt(2, (hlgtCodeToIdMap.get(llt[1]).intValue()));
            	ps.setInt(3, version_id);
            }
        };

        return jdbcTemplate.batchUpdate(sql, setter);
        
    }
    
    /**
     *  This method deletes SOC, HLGT, HLT, PT, LLT and the joining tables
     *  corresponding to a particular version
     *  @param version_id
     *  			The version_id to be deleted.
     *  @author Sameer Sawant.
     */
    public void deleteMeddraConcepts(int version_id){
    	// First, the entries from the joining tables are deleted to avoid the conflict of referential integrity.
    	// meddra_soc_hlgt table
    	String sql = "delete from meddra_soc_hlgt where version_id = " + version_id;
    	jdbcTemplate.execute(sql);
    	
    	// meddra_hlgt_hlt table
    	sql = "delete from meddra_hlgt_hlt where version_id = " + version_id;
    	jdbcTemplate.execute(sql);
    	
    	// meddra_hlt_pt table
    	sql = "delete from meddra_hlt_pt where version_id = " + version_id;
    	jdbcTemplate.execute(sql);
    	
    	// meddra_soc table
    	sql = "delete from meddra_soc where version_id = " + version_id;
    	jdbcTemplate.execute(sql);
    	
    	// meddra_hlgt table
    	sql = "delete from meddra_hlgt where version_id = " + version_id;
    	jdbcTemplate.execute(sql);
    	
    	// meddra_hlt table
    	sql = "delete from meddra_hlt where version_id = " + version_id;
    	jdbcTemplate.execute(sql);
    	
    	// meddra_pt table
    	sql = "delete from meddra_pt where version_id = " + version_id;
    	jdbcTemplate.execute(sql);
    	
    	// meddra_llt table
    	sql = "delete from meddra_llt where version_id = " + version_id;
    	jdbcTemplate.execute(sql);
    	
    	sql = "delete from meddra_versions where id = " + version_id;
    	jdbcTemplate.execute(sql);
    }
    
    /**
     *  This method adds a new meddra_version to the meddra_version table.
     *  The name of the new version is provided by the administrator through UI
     *  The Id is generated from the sequence meddra_versions_id_seq
     *  
     *  @param meddra_name
     *  			This is the name of the new meddra_version
     *  @author Sameer Sawant.
     */
    public void createMeddraVersion(String meddra_name){
    	
    	String sql = "INSERT INTO meddra_versions VALUES (nextval('meddra_versions_id_seq'), '" + meddra_name + "')";
    	String dataBase = "";
    	if(properties.getProperty(DB_NAME) != null){
    		dataBase = properties.getProperty(DB_NAME);
    	}
    	if(dataBase.equals(ORACLE_DB))
    		sql = "INSERT INTO meddra_versions VALUES (meddra_versions_id_seq.NEXTVAL, '" + meddra_name + "')"; 
 
    	jdbcTemplate.execute(sql);
    }
    
    
    /**
     * Getter for JDBC template.
     * 
     * @return The JDBC template.
     */
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    /**
     * Setter for JDBC template.
     * 
     * @param jdbcTemplate
     *                The JDBC template.
     */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    /**
     * Getter for properties
     * @return dataSourceFactoryBean
     */
    public Properties getProperties(){
    	return properties;
    }
    
    /**
     * Setter for dataSourceFactoryBean
     * 
     * @param CaaersDataSourcePropertiesFactoryBean
     * 
     * @author Sameer Sawant
     */
    public void setProperties(Properties properties){
    	this.properties = properties;
    }
}
