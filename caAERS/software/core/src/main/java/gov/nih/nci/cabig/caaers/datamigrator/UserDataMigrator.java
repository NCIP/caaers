package gov.nih.nci.cabig.caaers.datamigrator;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * This class will migrate existing users to new groups.
 *
 * @see gov.nih.nci.cabig.caaers.datamigrator.UserPrivilegeMigrator
 * @author Monish Dombla
 * @author Biju Joseph (Refactored - made use of template)
 *
 */

public class UserDataMigrator extends CaaersDataMigratorTemplate  {

    public static final List<String> EXISTING_GROUPS = Arrays.asList("caaers_study_cd",
    																"caaers_participant_cd",
    																"caaers_central_office_sae_cd",
    																"caaers_data_cd",
    																"caaers_ae_cd",
    																"caaers_site_cd",
    																"caaers_physician");

    public MigraorType migratorType() {
        return MigraorType.USER; 
    }

    /**
     * Migrates users,groups,siteresearchstaffrole,studypersonnel
     */
    public void migrate(CaaersDataMigrationContext context){
    	log.debug("Migration required performing migration ......");
        //Migrate SuperUsers
        migrateSuperUsers(context);
        //Migrate Normal users
        migrateUsers(context);
        deleteUnusedRecords(context);
        //Migrate SiteResearchStaffRoles
        migrateSiteResearchStaffRole(context);
        //Migrate SP role codes
        migrateStudyPersonnelRole(context);
        deleteUnusedStudyPersonnel(context);

        //NOTE : - The user data provisioning is moved to another UserPrivilegeMigrator migrator. 
        
        log.debug("Migration complete");
          
    }

	
    /**
     * This method will migrate all users assigned to 
     * caaers_super_user group. (SYSTEM_ADMIN) SYSTEM & cctsdemo1@nci.nih.gov users are migrated through groovy.
     */
    @SuppressWarnings("unchecked")
	protected void migrateSuperUsers(CaaersDataMigrationContext context){
    	
    	List<Map> csmUsers = getCsmUsers("caaers_super_user");
        if(CollectionUtils.isEmpty(csmUsers)) return;

        
        insertProtectionGroupsAndProtectionElements("HealthcareSite",context.isOracle());
        insertProtectionGroupsAndProtectionElements("Study",context.isOracle());
        
        List<String> groupNames = Arrays.asList("system_administrator",
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
                                                "ae_expedited_report_reviewer",
                                                "ae_study_data_reviewer",
                                                "lab_impact_calendar_notifier",
                                                "lab_data_user",
                                                "data_reader",
                                                "data_analyst");

        List<String> siteScopedGroupNames = Arrays.asList(
                                                "person_and_organization_information_manager",
                                                "user_administrator",
                                                "study_qa_manager",
                                                "study_creator",
                                                "supplemental_study_information_manager",
                                                "study_team_administrator",
                                                "study_site_participation_administrator",
                                                "registration_qa_manager",
                                                "subject_manager");


        List<String> studyScopedGroupNames = Arrays.asList(
                                                "study_calendar_template_builder",
                                                "study_subject_calendar_manager",
                                                "registrar",
                                                "ae_reporter",
                                                "ae_expedited_report_reviewer",
                                                "ae_study_data_reviewer",
                                                "lab_impact_calendar_notifier",
                                                "lab_data_user",
                                                "data_reader",
                                                "data_analyst");

        
    	for (Map map : csmUsers) {
			String loginName = map.get("login_name").toString();
			String userId = map.get("user_id").toString();
			log.debug("LoginName" + "::" + loginName + " -- User Id :: " + userId);
			
			insertIntoCsmUserGroup(userId, groupNames, context.isOracle());

            relateSuperUserGroupsToProtectionElements(loginName, "HealthcareSite", siteScopedGroupNames, context.isOracle());
            relateSuperUserGroupsToProtectionElements(loginName, "HealthcareSite", studyScopedGroupNames, context.isOracle());
            relateSuperUserGroupsToProtectionElements(loginName, "Study", studyScopedGroupNames, context.isOracle());
			
			String deleteSql = "delete from csm_user_group where user_id IN (-1,-7,-9) and group_id < 0";
			getJdbcTemplate().execute(deleteSql);
			
			log.debug("Moved "+loginName+" from caaers_super_user");
			
    	}
    }
    
    /**
     * This method will migrate all users who belong to 
     * caaers_study_cd -- (study_creator","supplemental_study_information_manager","study_team_administrator","study_site_participation_administrator")
     * caaers_participant_cd -- ("subject_manager","registrar")
     * caaers_central_office_sae_cd -- ("ae_expedited_report_reviewer")
     * caaers_data_cd -- ("ae_study_data_reviewer")
     * caaers_ae_cd -- ("ae_reporter")
     * caaers_site_cd -- ("business_administrator","system_administrator","person_and_organization_information_manager","data_importer","user_administrator",
     * 						"ae_rule_and_report_manager","data_analyst")
     * caaers_physician -- ("ae_reporter") 
     */
    @SuppressWarnings("unchecked")
	protected void migrateUsers(CaaersDataMigrationContext context){
		
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
    															 	"study_site_participation_administrator"), context.isOracle());
    				log.debug("Moved "+loginName+" from caaers_study_cd");
    			}else if(StringUtils.equals("caaers_participant_cd", groupName)){
    				insertIntoCsmUserGroup(userId, Arrays.asList("subject_manager",
    															 	"registrar"), context.isOracle());
    				log.debug("Moved users from caaers_participant_cd");
    			}else if(StringUtils.equals("caaers_central_office_sae_cd", groupName)){
    				insertIntoCsmUserGroup(userId, Arrays.asList("ae_expedited_report_reviewer"), context.isOracle());
    				log.debug("Moved "+loginName+" from caaers_central_office_sae_cd");
    			}else if(StringUtils.equals("caaers_data_cd", groupName)){
    				insertIntoCsmUserGroup(userId, Arrays.asList("ae_study_data_reviewer"), context.isOracle());
    				log.debug("Moved "+loginName+" from caaers_data_cd");
    			}else if(StringUtils.equals("caaers_ae_cd", groupName)){
    				insertIntoCsmUserGroup(userId, Arrays.asList("ae_reporter"), context.isOracle());
    				log.debug("Moved "+loginName+" from caaers_ae_cd");
    			}else if(StringUtils.equals("caaers_physician", groupName)){
    				insertIntoCsmUserGroup(userId, Arrays.asList("ae_reporter"), context.isOracle());
    				log.debug("Moved "+loginName+" from caaers_physician");
    			}else if(StringUtils.equals("caaers_site_cd", groupName)){
    				insertIntoCsmUserGroup(userId, Arrays.asList("business_administrator",
    																"system_administrator",
    																"person_and_organization_information_manager",
    																"data_importer",
    																"user_administrator",
    																"ae_rule_and_report_manager",
    																"data_analyst"), context.isOracle());
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

		List<Map> csmUsers = getJdbcTemplate().queryForList(usersSql);
		return csmUsers;
    }
    
    /**
     * This methos will return all the users in CSM_USER table.
     * @return
     */
    @SuppressWarnings("unchecked")
	protected List<Map> getAllCsmUsers(){
    	String usersSql = "select login_name,user_id from csm_user ";
    	List<Map> csmUsers = getJdbcTemplate().queryForList(usersSql);
    	return csmUsers;
    }
    
    /**
     * This method returns the proper sql string for the DB being used.
     * @return
     */
    protected String getInsertCsmUserGroupSql(boolean forOracle){
    	String postgresInsertSql = "INSERT INTO csm_user_group(user_group_id, user_id, group_id) " +
    								"VALUES ((select nextval('csm_user_grou_user_group_i_seq')), ? , (select group_id from csm_group where group_name = ?))";
    	
    	String oracleInsertSql = "INSERT INTO csm_user_group(user_group_id, user_id, group_id) " +
    								"VALUES (csm_user_grou_user_group_i_seq.nextval, ? , (select group_id from csm_group where group_name = ?))";

        return (forOracle) ? oracleInsertSql : postgresInsertSql;
        
    }
    
    /**
     * This method inserts records into CSM_USER_GROUP table
     * @param userId
     * @param groups
     */
    @SuppressWarnings("unchecked")
	protected void insertIntoCsmUserGroup(final String userId, final List groups, boolean onOracleDB){
    	
    	String sql = getInsertCsmUserGroupSql(onOracleDB);
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
        getJdbcTemplate().batchUpdate(sql, setter);
    }

    /**
     * Will related a user group to protection group. 
     * @param userName - Username
     * @param protectionGroupName - protectionGroup name
     * @param groups   - The user groups to associate
     * @param onOracleDB
     */
    protected void relateSuperUserGroupsToProtectionElements(final String userName, final String protectionGroupName,  final List groups, boolean onOracleDB){
        String pgSQL = "INSERT INTO csm_user_group_role_pg(user_group_role_pg_id, user_id, protection_group_id, role_id,update_date) " +
                "VALUES ((select nextval('csm_user_grou_user_group_r_seq')), " +
                "(select user_id from csm_user where login_name = ?), " +
                "(select protection_group_id from csm_protection_group where protection_group_name = ?), " +
                "(select role_id from csm_role where role_name = ?), " +
                "now())";
        String oracleSQL = "INSERT INTO csm_user_group_role_pg(user_group_role_pg_id, user_id, protection_group_id, role_id,update_date) " +
                "VALUES (csm_user_grou_user_group_r_seq.nextval, " +
                "(select user_id from csm_user where login_name = ?), " +
                "(select protection_group_id from csm_protection_group where protection_group_name = ?), " +
                "(select role_id from csm_role where role_name = ?), " +
                "sysdate)";

        String sql = onOracleDB ? oracleSQL : pgSQL;
        getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter(){
            public int getBatchSize() {
                return groups.size();
            }

            public void setValues(PreparedStatement ps, int i) throws SQLException {
               ps.setString(1, userName);
               ps.setString(2, protectionGroupName);
               ps.setString(3, groups.get(i).toString());
            }
        });

    }

    /**
     * Will insert the protection group and protection elements
     * @param onOracleDB
     */
    protected void insertProtectionGroupsAndProtectionElements(String protectionGroupName, boolean onOracleDB){

       int pgCount =  getJdbcTemplate().queryForInt("select count(*) from csm_protection_group where protection_group_name = '" + protectionGroupName + "'");
       int peCount =  getJdbcTemplate().queryForInt("select count(*) from csm_protection_element where protection_element_name = '" + protectionGroupName + "'");

        if(onOracleDB){
            
            //insert the protection group - HealthcareSite
            if(pgCount <=0){
                getJdbcTemplate().execute("INSERT INTO csm_protection_group(protection_group_id, protection_group_name, protection_group_description, application_id,update_date,large_element_count_flag) " +
                                   "  VALUES (csm_protectio_protection_g_seq.nextval,'" + protectionGroupName + "','Implies All" + protectionGroupName + "', " +
                                   "  (select application_id from csm_application where application_name = 'CTMS_SUITE'),sysdate,0)");

            }

            //insert the protection element - HealthcareSite
            if(peCount <= 0) {
                getJdbcTemplate().execute("INSERT INTO csm_protection_element(protection_element_id, protection_element_name, protection_element_description, object_id, application_id, update_date) " +
                                    "  VALUES (csm_protectio_protection_e_seq.nextval,'" + protectionGroupName + "','Implies All " + protectionGroupName + "','" + protectionGroupName + "', " +
                                    "  (select application_id from csm_application where application_name = 'CTMS_SUITE'),sysdate)");

                //relate protection group and protection element.
                getJdbcTemplate().execute("INSERT INTO csm_pg_pe(pg_pe_id, protection_group_id, protection_element_id, update_date) " +
                        "    VALUES (csm_pg_pe_pg_pe_id_seq.nextval, " +
                        " (select protection_group_id from csm_protection_group where protection_group_name = '" + protectionGroupName + "'), " +
                        " (select protection_element_id from csm_protection_element where protection_element_name = '" + protectionGroupName + "'), sysdate)");


            }


        } else {

             //insert the protection group - HealthcareSite
            if(pgCount <= 0){
               getJdbcTemplate().execute("INSERT INTO csm_protection_group(protection_group_id, protection_group_name, protection_group_description, application_id,update_date,large_element_count_flag) " +
                    "    VALUES ((select nextval('csm_protectio_protection_g_seq')),'" + protectionGroupName + "','Implies All " + protectionGroupName + "', " +
                    "    (select application_id from csm_application where application_name = 'CTMS_SUITE'),now(),0)");
            }

            
            //insert the protection element - HealthcareSite
            if(peCount <= 0){
               getJdbcTemplate().execute("INSERT INTO csm_protection_element(protection_element_id, protection_element_name, protection_element_description, object_id, application_id, update_date) " +
                    "    VALUES ((select nextval('csm_protectio_protection_e_seq')),'" + protectionGroupName + "','Implies All " + protectionGroupName + "','" + protectionGroupName + "', " +
                    "     (select application_id from csm_application where application_name = 'CTMS_SUITE'),now())");

               //relate protection group and protection element.
               getJdbcTemplate().execute("INSERT INTO csm_pg_pe(pg_pe_id, protection_group_id, protection_element_id, update_date) " +
                    "    VALUES ((select nextval('csm_pg_pe_pg_pe_id_seq')), " +
                    " (select protection_group_id from csm_protection_group where protection_group_name = '" + protectionGroupName + "'), " +
                    " (select protection_element_id from csm_protection_element where protection_element_name = '" + protectionGroupName + "'), now())");
            }

        }



    }
    
    /**
     * Delete's old user group association entries from csm_user_group table.
     * Delete's old groups from csm_group table
     * Delete's users who are not associated to any groups from csm_user table.
     */
    protected void deleteUnusedRecords(CaaersDataMigrationContext context){
    	String deleteSql = "delete from csm_user_group where group_id < 0";
    	getJdbcTemplate().execute(deleteSql);
    	deleteSql = "delete from csm_group where group_id < 0";
    	getJdbcTemplate().execute(deleteSql);
    	deleteSql = "delete from csm_user where user_id NOT IN (select user_id from csm_user_group)";
    	getJdbcTemplate().execute(deleteSql);
    }
    
    
    /**
     * This method will migrate all the existing site research staff roles to new roles.
     */
    @SuppressWarnings("unchecked")
	protected void migrateSiteResearchStaffRole(CaaersDataMigrationContext context){

    	for(String groupName : EXISTING_GROUPS){
    		List<Map> siteResearchStaffRoles = getSiteResearchStaffRoles(groupName);
    		for(Map map : siteResearchStaffRoles){

    			if(StringUtils.equals("caaers_study_cd", groupName)){
    				insertIntoSiteResearchStaffRoles(map, Arrays.asList("study_creator",
    															 	"supplemental_study_information_manager",
    															 	"study_team_administrator",
    															 	"study_site_participation_administrator"), context.isOracle());
    			}else if(StringUtils.equals("caaers_participant_cd", groupName)){
    				insertIntoSiteResearchStaffRoles(map, Arrays.asList("subject_manager",
    															 	"registrar"),context.isOracle());
    			}else if(StringUtils.equals("caaers_central_office_sae_cd", groupName)){
    				insertIntoSiteResearchStaffRoles(map, Arrays.asList("ae_expedited_report_reviewer"),context.isOracle());
    			}else if(StringUtils.equals("caaers_data_cd", groupName)){
    				insertIntoSiteResearchStaffRoles(map, Arrays.asList("ae_study_data_reviewer"),context.isOracle());
    			}else if(StringUtils.equals("caaers_ae_cd", groupName)){
    				insertIntoSiteResearchStaffRoles(map, Arrays.asList("ae_reporter"),context.isOracle());
    			}else if(StringUtils.equals("caaers_physician", groupName)){
    				insertIntoSiteResearchStaffRoles(map, Arrays.asList("ae_reporter"),context.isOracle());
    			}else if(StringUtils.equals("caaers_site_cd", groupName)){
    				insertIntoSiteResearchStaffRoles(map, Arrays.asList("business_administrator",
    																"system_administrator",
    																"person_and_organization_information_manager",
    																"data_importer",
    																"user_administrator",
    																"ae_rule_and_report_manager",
    																"data_analyst"),context.isOracle());
    			}
    		}
    		
    	}
    	String deleteSql = "delete from site_rs_staff_roles where role_code like 'caaers%'";
    	getJdbcTemplate().execute(deleteSql);
    }
    
    /**
     * This method returns all the siteResearchStaffRoles for a given roleCode.
     * @param roleCode
     * @return
     */
    @SuppressWarnings("unchecked")
	protected List<Map> getSiteResearchStaffRoles(String roleCode){
    	String siteResearchStaffRoleSql = "select start_date,end_date,site_research_staffs_id from site_rs_staff_roles " +
											"where " +
											"role_code = '"+roleCode+"'";

		List<Map> siteResearchStaffRoles = getJdbcTemplate().queryForList(siteResearchStaffRoleSql);
		return siteResearchStaffRoles;
    }
    
    
    /**
     * Return the right sql based on database used.
     * @return
     */
    protected String getInsertSiteResearchStaffRoleSql(boolean forOracle){
    	String postgresInsertSql = "INSERT INTO site_rs_staff_roles(id, role_code, site_research_staffs_id, start_date,end_date) " +
    									"VALUES ((select nextval('site_rs_staff_roles_id_seq')), ?, ?, ?, ?)";

    	String oracleInsertSql = "INSERT INTO site_rs_staff_roles(id, role_code, site_research_staffs_id, start_date, end_date) " +
    									"VALUES (seq_site_rs_staff_roles_id.nextval, ?, ?, ?, ?)";
        
    	return forOracle ? oracleInsertSql : postgresInsertSql;
        
    }
    
    /**
     * This method inserts appropriate records into site_rs_staff_roles table based on existing role_code.
     * @param map
     * @param groups
     */
    @SuppressWarnings("unchecked")
	protected void insertIntoSiteResearchStaffRoles(final Map map, final List groups, final boolean onOracleDB){
    	String sql = getInsertSiteResearchStaffRoleSql(onOracleDB);
    	BatchPreparedStatementSetter setter = null;
        setter = new BatchPreparedStatementSetter() {

            public int getBatchSize() {
                return groups.size();
            }

            public void setValues(PreparedStatement ps, int index) throws SQLException {
    			
            	
            	java.sql.Timestamp startDate = (java.sql.Timestamp)map.get("start_date");
            	java.sql.Timestamp endDate = (java.sql.Timestamp)map.get("end_date");
    			
            	ps.setString(1, groups.get(index).toString());
                
            	if(onOracleDB){
            		BigDecimal siteResearchStaffId = (BigDecimal)map.get("site_research_staffs_id");
            		ps.setBigDecimal(2, siteResearchStaffId);
            	} else {
            		int siteResearchStaffId = ((Integer)map.get("site_research_staffs_id")).intValue();
            		ps.setInt(2, siteResearchStaffId);
            	}
    			ps.setTimestamp(3, startDate);
    			ps.setTimestamp(4, endDate);
            }
        };
        getJdbcTemplate().batchUpdate(sql, setter);
    }
    
    /**
     * This method will migrate all existing study personnel role code to new role code.
     */
    @SuppressWarnings("unchecked")
	protected void migrateStudyPersonnelRole(CaaersDataMigrationContext context){
    	for(String groupName : EXISTING_GROUPS){
    		
    		List<Map> studyPersonnel = getStudyPersonnel(groupName);
    		for(Map map : studyPersonnel){

    			if(StringUtils.equals("caaers_study_cd", groupName)){
    				insertIntoStudyPersonnel(map, Arrays.asList("study_creator",
    															 	"supplemental_study_information_manager",
    															 	"study_team_administrator",
    															 	"study_site_participation_administrator"),context.isOracle());
    			}else if(StringUtils.equals("caaers_participant_cd", groupName)){
    				insertIntoStudyPersonnel(map, Arrays.asList("subject_manager",
    															 	"registrar"),context.isOracle());
    			}else if(StringUtils.equals("caaers_central_office_sae_cd", groupName)){
    				insertIntoStudyPersonnel(map, Arrays.asList("ae_expedited_report_reviewer"),context.isOracle());
    			}else if(StringUtils.equals("caaers_data_cd", groupName)){
    				insertIntoStudyPersonnel(map, Arrays.asList("ae_study_data_reviewer"),context.isOracle());
    			}else if(StringUtils.equals("caaers_ae_cd", groupName)){
    				insertIntoStudyPersonnel(map, Arrays.asList("ae_reporter"),context.isOracle());
    			}else if(StringUtils.equals("caaers_physician", groupName)){
    				insertIntoStudyPersonnel(map, Arrays.asList("ae_reporter"),context.isOracle());
    			}else if(StringUtils.equals("caaers_site_cd", groupName)){
    				insertIntoStudyPersonnel(map, Arrays.asList("business_administrator",
    																"system_administrator",
    																"person_and_organization_information_manager",
    																"data_importer",
    																"user_administrator",
    																"ae_rule_and_report_manager",
    																"data_analyst"),context.isOracle());
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

    	List<Map> studyPersonnel = getJdbcTemplate().queryForList(studyPersonnelSql);
    	return studyPersonnel;
    }
    
    /**
     * Return the right sql based on database used.
     * @return
     */
    protected String getInsertStudyPersonnelSql(boolean onOracleDB){
    	String postgresInsertSql = "INSERT INTO study_personnel (id, study_sites_id, " +
    															"role_code, retired_indicator, start_date, end_date, site_research_staffs_id) " +
    															"VALUES ((select nextval('study_personnel_id_seq')),?, ?, ?, ?, ?, ?)";

    	String oracleInsertSql = "INSERT INTO study_personnel (id, study_sites_id, " +
																"role_code, retired_indicator, start_date, end_date, site_research_staffs_id) " +
																"VALUES (seq_study_personnel_id.nextval,?, ?, ?, ?, ?, ?)";

		if(onOracleDB){
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
	protected void insertIntoStudyPersonnel(final Map map,final List groups, final boolean onOracleDB){
    	
    	String sql = getInsertStudyPersonnelSql(onOracleDB);
    	BatchPreparedStatementSetter setter = null;
        setter = new BatchPreparedStatementSetter() {

            public int getBatchSize() {
                return groups.size();
            }

            public void setValues(PreparedStatement ps, int index) throws SQLException {
    			
    			
    			
            	java.sql.Timestamp startDate = (java.sql.Timestamp)map.get("start_date");
            	java.sql.Timestamp endDate = (java.sql.Timestamp)map.get("end_date");
    			
    			if(onOracleDB){
    				BigDecimal studySiteId = (BigDecimal)map.get("study_sites_id");
    				ps.setBigDecimal(1, studySiteId);    				
    			} else {
    				int studySiteId = ((Integer)map.get("study_sites_id")).intValue();
    				ps.setInt(1, studySiteId);
    			}    			
    			ps.setString(2, groups.get(index).toString());
    			if(onOracleDB){
    				BigDecimal retiredIndicator = (BigDecimal)map.get("retired_indicator");
        			ps.setBigDecimal(3, retiredIndicator);    				
    			} else {
        			Boolean retiredIndicator = (Boolean)map.get("retired_indicator");
        			ps.setBoolean(3, retiredIndicator);   				
    			}

    			ps.setTimestamp(4, startDate);
    			ps.setTimestamp(5, endDate);
    			
    			if(onOracleDB){
    				BigDecimal siteResearchStaffId = (BigDecimal)map.get("site_research_staffs_id");
    				ps.setBigDecimal(6, siteResearchStaffId);
    			} else {
    				int siteResearchStaffId = ((Integer)map.get("site_research_staffs_id")).intValue();
    				ps.setInt(6, siteResearchStaffId);
    			}
    			
            }
        };
        getJdbcTemplate().batchUpdate(sql, setter);
    }
   
    /**
     * Deletes all the Study Personnel records with old roleCode.
     */
    protected void deleteUnusedStudyPersonnel(CaaersDataMigrationContext context){
    	String sql = "delete from study_personnel where role_code like 'caaers%'";
    	getJdbcTemplate().execute(sql);
    }


}