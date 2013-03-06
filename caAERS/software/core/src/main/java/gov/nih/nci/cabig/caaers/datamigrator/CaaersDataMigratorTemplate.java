/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.datamigrator;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class defines the basic workflow in the data migration. 
 *
 *
 * @author: Biju Joseph
 */
public abstract class CaaersDataMigratorTemplate implements  CaaersDataMigrator{
    protected final Log log = LogFactory.getLog(getClass());

    private JdbcTemplate jdbcTemplate;
    private static String DB_PRODUCT_VERSION;

    private Integer statusCodeComplete = 1;

    //finds the vendor of the Database
    private String getDBName(){
        if(DB_PRODUCT_VERSION == null){
          DB_PRODUCT_VERSION = (String) getJdbcTemplate().execute(new ConnectionCallback(){
                public Object doInConnection(Connection con) throws SQLException, DataAccessException {
                    return con.getMetaData().getDatabaseProductVersion();
                }
            });  
        }

        
        return DB_PRODUCT_VERSION;
    }


    /**
     * Returns true if DB is Oracle
     * @return
     */
    private boolean isOralceDB(){
        return StringUtils.containsIgnoreCase(getDBName(), "oracle");
    }

    /**
     * Returns true if DB is PostgreSQL
     * @return
     */
    private boolean isPostgresDB(){
        return StringUtils.containsIgnoreCase(getDBName(), "postgresql");
    }


    //will get the migator id
    private int migratorId(){
        return migratorType().getCode();
    }

    @Transactional
    public final void migrateData(){

        Integer statusCode = 0;

        //check the status_code
        String query = "SELECT STATUS_CODE, RUNDATE FROM caaers_bootstrap_log WHERE OPERATION_CODE = " + migratorId();

        List<Map<String, Object>> l = getJdbcTemplate().queryForList(query);
        boolean noDataFound = CollectionUtils.isEmpty(l);
        CaaersDataMigrationContext context = new CaaersDataMigrationContext( isOralceDB(), isPostgresDB());

        if(!noDataFound){
           Map map = l.get(0);
           statusCode = ((Number) map.get("STATUS_CODE")).intValue();
           String ranDate = String.valueOf( map.get("RUNDATE") );
            
           //arleady ran this migration sucessfully ?
           if(statusCodeComplete.equals(statusCode)){
               log.info(String.format("Skipping the processing as the migrator[%s] already ran on %s.",
                       new Object[]{getClass().getName(), ranDate}) );
               return;
           }
        }


        //do the template steps
        try{

            beforeMigrate(context);
            migrate(context);
            afterMigrate(context);
            statusCode = statusCodeComplete;

            //update the bootstrap 
           if(noDataFound){
               getJdbcTemplate().update("insert into caaers_bootstrap_log(id, operation_code, status_code, rundate) values(?,?,?, ?)",
                       new Object[]{migratorId(), migratorId(), statusCode, new Date()},
                       new int[]{Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.DATE});
           }else{
                getJdbcTemplate().update("update caaers_bootstrap_log set status_code = ? , rundate = ? where operation_code = ?",
                       new Object[]{ statusCode, new Date(), migratorId()},
                       new int[]{Types.INTEGER, Types.DATE, Types.INTEGER});
           }

        }catch(Exception e){
             log.error(String.format("Error while running the migrator %s", getClass().getName()), e );
        }
        
    }

    public void migrate(CaaersDataMigrationContext ctx){

    }
    
    public void beforeMigrate(CaaersDataMigrationContext ctx){

    }

    public void afterMigrate(CaaersDataMigrationContext ctx){
        
    }


    @Required
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

    @Required
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

    //an object that can be used to share data accorss method calls.
    public class CaaersDataMigrationContext {
       private boolean oracle;
       private boolean postgres;
       Map<Object,Object> map;

        public CaaersDataMigrationContext( boolean oracle, boolean postgres) {
            this.oracle = oracle;
            this.postgres = postgres;
            map = new HashMap<Object, Object>();
        }

        public boolean isOracle() {
            return oracle;
        }


        public boolean isPostgres() {
            return postgres;
        }

    }
}

