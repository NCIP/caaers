package gov.nih.nci.cabig.caaers.datamigrator;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * This class will migrate existing users to new groups.
 * 
 * @author Monish Dombla
 *
 */

public class CaaersDataMigrator {

	protected final Log log = LogFactory.getLog(getClass());
    private JdbcTemplate jdbcTemplate;
    private Properties properties;
    private static final String DB_NAME  = "databaseName";
    private static final String ORACLE_DB = "oracle";
    public static final List<String> EXISTING_GROUPS = Arrays.asList("caaers_study_cd", 
    																"caaers_participant_cd",
    																"caaers_central_office_sae_cd",
    																"caaers_data_cd",
    																"caaers_ae_cd",
    																"caaers_site_cd",
    																"caaers_physician");
    
    
    /**
     * 
     */
    public void migrateData(){
    	
    }
	
    /**
     * This method will migrate all users assigned to 
     * caaers_super_user group. (SYSTEM_ADMIN) SYSTEM & cctsdemo1@nci.nih.gov users are migrated through groovy.
     */
    @SuppressWarnings("unchecked")
	public void migrateSuperUsers(){
    	
    	List<Map> csmUsers = getCsmUsers("caaers_super_user");
    	for (Map map : csmUsers) {
			String loginName = map.get("login_name").toString();
			String userId = map.get("user_id").toString();
			log.debug("LoginName" + "::" + loginName + " -- User Id :: " + userId);
			
			insertIntoCsmUserGroup(userId, Arrays.asList("system_administrator",
														 	"business_administrator",
														 	"person_and_organization_information_manager",
														 	"data_importer",
														 	"user_administrator",
														 	"study_qa_manager",
														 	"study_creator",
														 	"supplemental_study_information_manager",														 	
														 	"study_team_administrator",
														 	"study_site_participation_administrator",
														 	"ae_rule_and_report_manager",
														 	"study_calendar_template_builder",			
														 	"registration_qa_manager",
														 	"subject_manager",
														 	"study_subject_calendar_manager",
														 	"registrar",
														 	"ae_reporter",
														 	"expedited_report_reviewer",
														 	"adverse_event_study_data_reviewer",
														 	"lab_impact_calendar_notifier",
														 	"lab_data_user",
														 	"data_reader",
														 	"data_analyst"));
			
			String deleteSql = "delete from csm_user_group where user_id IN (-1,-7,-9) and group_id < 0";
			jdbcTemplate.execute(deleteSql);
			
			log.debug("Moved "+loginName+" from caaers_super_user");
			
    	}
    }
    
    /**
     * This method will migrate all users who belong to 
     * caaers_study_cd -- (study_creator","supplemental_study_information_manager","study_team_administrator","study_site_participation_administrator")
     * caaers_participant_cd -- ("subject_manager","registrar")
     * caaers_central_office_sae_cd -- ("expedited_report_reviewer")
     * caaers_data_cd -- ("adverse_event_study_data_reviewer")
     * caaers_ae_cd -- ("ae_reporter")
     * caaers_site_cd -- ("business_administrator","system_administrator","person_and_organization_information_manager","data_importer","user_administrator",
     * 						"ae_rule_and_report_manager","data_analyst")
     * caaers_physician -- ("ae_reporter") 
     */
    @SuppressWarnings("unchecked")
	public void migrateUsers(){
		
    	for(String groupName : EXISTING_GROUPS){
    		
    		List<Map> csmUsers = getCsmUsers(groupName);
    		for (Map map : csmUsers) {
    			String loginName = map.get("login_name").toString();
    			String userId = map.get("user_id").toString();
    			log.debug("LoginName" + "::" + loginName + " -- User Id :: " + userId);
    			
    			if(StringUtils.equals("caaers_study_cd", groupName)){
    				insertIntoCsmUserGroup(userId, Arrays.asList("study_creator",
    															 	"supplemental_study_information_manager",
    															 	"study_team_administrator",
    															 	"study_site_participation_administrator"));
    				log.debug("Moved "+loginName+" from caaers_study_cd");
    			}else if(StringUtils.equals("caaers_participant_cd", groupName)){
    				insertIntoCsmUserGroup(userId, Arrays.asList("subject_manager",
    															 	"registrar"));
    				log.debug("Moved users from caaers_participant_cd");
    			}else if(StringUtils.equals("caaers_central_office_sae_cd", groupName)){
    				insertIntoCsmUserGroup(userId, Arrays.asList("expedited_report_reviewer"));
    				log.debug("Moved "+loginName+" from caaers_central_office_sae_cd");
    			}else if(StringUtils.equals("caaers_data_cd", groupName)){
    				insertIntoCsmUserGroup(userId, Arrays.asList("adverse_event_study_data_reviewer"));
    				log.debug("Moved "+loginName+" from caaers_data_cd");
    			}else if(StringUtils.equals("caaers_ae_cd", groupName)){
    				insertIntoCsmUserGroup(userId, Arrays.asList("ae_reporter"));
    				log.debug("Moved "+loginName+" from caaers_ae_cd");
    			}else if(StringUtils.equals("caaers_physician", groupName)){
    				insertIntoCsmUserGroup(userId, Arrays.asList("ae_reporter"));
    				log.debug("Moved "+loginName+" from caaers_physician");
    			}else if(StringUtils.equals("caaers_site_cd", groupName)){
    				insertIntoCsmUserGroup(userId, Arrays.asList("business_administrator",
    																"system_administrator",
    																"person_and_organization_information_manager",
    																"data_importer",
    																"user_administrator",
    																"ae_rule_and_report_manager",
    																"data_analyst"));
    				log.debug("Moved "+loginName+" from caaers_physician");
    			}
    		}
    	}
    }
    
    
    /**
     * This method will return all the users in the provided group.
     * @param groupName
     * @return
     */
    @SuppressWarnings("unchecked")
	protected List<Map> getCsmUsers(String groupName){
    	
    	String usersSql = "select cu.login_name,cu.user_id " +
							"from " +
							"csm_user cu, csm_user_group cug, csm_group cg" +
							" where " +
							"cu.user_id = cug.user_id and " +
							"cg.group_id = cug.group_id and " +
							"cg.group_name = '" + groupName + "'";

		List<Map> csmUsers = jdbcTemplate.queryForList(usersSql);
		return csmUsers;
    }
    
    /**
     * This method returns the proper sql string for the DB being used.
     * @return
     */
    protected String getInsertCsmUserGroupSql(){
    	String postgresInsertSql = "INSERT INTO csm_user_group(user_group_id, user_id, group_id) " +
    								"VALUES ((select nextval('csm_user_grou_user_group_i_seq')), ? , (select group_id from csm_group where group_name = ?))";
    	
    	String oracleInsertSql = "INSERT INTO csm_user_group(user_group_id, user_id, group_id) " +
    								"VALUES (csm_user_grou_user_group_i_seq.nextval, ? , (select group_id from csm_group where group_name = ?))";
    	
    	if(StringUtils.equals(ORACLE_DB, properties.getProperty(DB_NAME))){
    		return oracleInsertSql;
    	}else{
    		return postgresInsertSql;
    	}
    }
    
    /**
     * This method inserts records into CSM_USER_GROUP table
     * @param userId
     * @param groups
     */
    @SuppressWarnings("unchecked")
	protected void insertIntoCsmUserGroup(final String userId, final List groups){
    	
    	String sql = getInsertCsmUserGroupSql();
    	BatchPreparedStatementSetter setter = null;
        setter = new BatchPreparedStatementSetter() {

            public int getBatchSize() {
                return groups.size();
            }

            public void setValues(PreparedStatement ps, int index) throws SQLException {
            	ps.setInt(1, Integer.parseInt(userId));
            	ps.setString(2,groups.get(index).toString());
            }
        };
        jdbcTemplate.batchUpdate(sql, setter);
    }
    
    /**
     * Delete's old user group association entries from csm_user_group table.
     * Delete's old groups from csm_group table
     * Delete's users who are not associated to any groups from csm_user table.
     */
    protected void deleteUnusedRecords(){
    	String deleteSql = "delete from csm_user_group where group_id < 0";
    	jdbcTemplate.execute(deleteSql);
    	deleteSql = "delete from csm_group where group_id < 0";
    	jdbcTemplate.execute(deleteSql);
    	deleteSql = "delete from csm_user where user_id NOT IN (select user_id from csm_user_group)";
    	jdbcTemplate.execute(deleteSql);
    }
    
    
    /**
     * This method will migrate all the existing site research staff roles to new roles.
     */
    public void migrateSiteResearchStaffRole(){
    	String insertSql = getSiteResearchStaffRoleSql();
    	jdbcTemplate.execute(insertSql);
    	String deleteSql = "delete from site_rs_staff_roles where role_code like 'caaers%'";
    	jdbcTemplate.execute(deleteSql);
    }
    
    /**
     * This method will return the right sql based in the database being used.
     * @return
     */
    protected String getSiteResearchStaffRoleSql(){
    	String postgresInsertSql = 
    			"insert into site_rs_staff_roles (id,role_code,site_research_staffs_id,start_date) " +
    			"select nextval('site_rs_staff_roles_id_seq'), cg.group_name, srs.id, '2009-01-01' " +
    			"from " +
    			"csm_user cu,csm_user_group cug,csm_group cg,research_staffs rs,site_research_staffs srs " +
    			"where " +
    			"cu.user_id = cug.user_id and " +
    			"cug.group_id = cg.group_id and " +
    			"rs.login_id = cu.login_name and " +
    			"rs.email_address = cu.email_id and " +
    			"rs.id = srs.researchstaff_id ";
    	
    	String oracleInsertSql = 
	    		"insert into site_rs_staff_roles (id,role_code,site_research_staffs_id,start_date) " +
	    		"select seq_site_rs_staff_roles_id.nextval, cg.group_name, srs.id, to_date('2009-01-01', 'yyyy/mm/dd') " +
	    		"from " +
	    		"csm_user cu,csm_user_group cug,csm_group cg,research_staffs rs,site_research_staffs srs " +
	    		"where " +
	    		"cu.user_id = cug.user_id and " +
	    		"cug.group_id = cg.group_id and " +
	    		"rs.login_id = cu.login_name and " +
	    		"rs.email_address = cu.email_id and " +
	    		"rs.id = srs.researchstaff_id ";
    	
    	if(StringUtils.equals(ORACLE_DB, properties.getProperty(DB_NAME))){
    		return oracleInsertSql;
    	}else{
    		return postgresInsertSql;
    	}
    }
    
    /**
     * This method will migrate all existing study personnel role code to new role code.
     */
    @SuppressWarnings("unchecked")
	protected void migrateStudyPersonnelRole(){
    	for(String groupName : EXISTING_GROUPS){
    		
    		List<Map> studyPersonnel = getStudyPersonnel(groupName);
    		for(Map map : studyPersonnel){

    			if(StringUtils.equals("caaers_study_cd", groupName)){
    				insertIntoStudyPersonnel(map, Arrays.asList("study_creator",
    															 	"supplemental_study_information_manager",
    															 	"study_team_administrator",
    															 	"study_site_participation_administrator"));
    			}else if(StringUtils.equals("caaers_participant_cd", groupName)){
    				insertIntoStudyPersonnel(map, Arrays.asList("subject_manager",
    															 	"registrar"));
    			}else if(StringUtils.equals("caaers_central_office_sae_cd", groupName)){
    				insertIntoStudyPersonnel(map, Arrays.asList("expedited_report_reviewer"));
    			}else if(StringUtils.equals("caaers_data_cd", groupName)){
    				insertIntoStudyPersonnel(map, Arrays.asList("adverse_event_study_data_reviewer"));
    			}else if(StringUtils.equals("caaers_ae_cd", groupName)){
    				insertIntoStudyPersonnel(map, Arrays.asList("ae_reporter"));
    			}else if(StringUtils.equals("caaers_physician", groupName)){
    				insertIntoStudyPersonnel(map, Arrays.asList("ae_reporter"));
    			}else if(StringUtils.equals("caaers_site_cd", groupName)){
    				insertIntoStudyPersonnel(map, Arrays.asList("business_administrator",
    																"system_administrator",
    																"person_and_organization_information_manager",
    																"data_importer",
    																"user_administrator",
    																"ae_rule_and_report_manager",
    																"data_analyst"));
    			}
    		}
    	}
    }
    
    /**
     * This method returns all the records for the given roleCode.
     * @param roleCode
     * @return
     */
    @SuppressWarnings("unchecked")
	protected List<Map> getStudyPersonnel(String roleCode){
    	String studyPersonnelSql = "select study_sites_id,role_code,retired_indicator,start_date,end_date,site_research_staffs_id " +
					    			"from study_personnel " +
					    			"where " +
					    			"role_code = '"+roleCode+"'";

    	List<Map> studyPersonnel = jdbcTemplate.queryForList(studyPersonnelSql);
    	return studyPersonnel;
    }
    
    /**
     * Return the right sql based on database used.
     * @return
     */
    protected String getInsertStudyPersonnelSql(){
    	String postgresInsertSql = "INSERT INTO study_personnel (id, study_sites_id, " +
    															"role_code, retired_indicator, start_date, " +
    															"site_research_staffs_id) " +
    															"VALUES ((select nextval('study_personnel_id_seq')),?, ?, ?, '2009-01-01', ?)";

    	String oracleInsertSql = "INSERT INTO study_personnel (id, study_sites_id, " +
																"role_code, retired_indicator, start_date, " +
																"site_research_staffs_id) " +
																"VALUES (seq_study_personnel_id_seq.nextval,?, ?, ?, to_date('2009-01-01', 'yyyy/mm/dd'), ?)";

		if(StringUtils.equals(ORACLE_DB, properties.getProperty(DB_NAME))){
			return oracleInsertSql;
		}else{
			return postgresInsertSql;
		}
    }
    
    /**
     * This method inserts appropriate records into study personnel table based on existing role_code.
     * @param map
     * @param groups
     */
    @SuppressWarnings("unchecked")
	protected void insertIntoStudyPersonnel(final Map map,final List groups){
    	
    	String sql = getInsertStudyPersonnelSql();
    	BatchPreparedStatementSetter setter = null;
        setter = new BatchPreparedStatementSetter() {

            public int getBatchSize() {
                return groups.size();
            }

            public void setValues(PreparedStatement ps, int index) throws SQLException {
    			int studySiteId = ((Integer)map.get("study_sites_id")).intValue();
    			Boolean retiredIndicator = (Boolean)map.get("retired_indicator");
    			int siteResearchStaffId = ((Integer)map.get("site_research_staffs_id")).intValue();
            	
    			ps.setInt(1, studySiteId);
    			ps.setString(2, groups.get(index).toString());
    			ps.setBoolean(3, retiredIndicator);
    			ps.setInt(4, siteResearchStaffId);
            }
        };
        jdbcTemplate.batchUpdate(sql, setter);
    }
    
    /**
     * Deletes all the Study Personnel records with old roleCode.
     */
    protected void deleteUnusedStudyPersonnel(){
    	String sql = "delete from study_personnel where role_code like 'caaers%'";
    	jdbcTemplate.execute(sql);
    }
    
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Required
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Properties getProperties() {
		return properties;
	}

	@Required
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}