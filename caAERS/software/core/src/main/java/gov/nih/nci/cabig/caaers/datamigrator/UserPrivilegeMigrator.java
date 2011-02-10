package gov.nih.nci.cabig.caaers.datamigrator;

import static gov.nih.nci.cabig.caaers.domain.UserGroupType.*;

import gov.nih.nci.cabig.caaers.RoleMembership;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain._User;
import gov.nih.nci.cabig.caaers.domain.repository.UserRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * This migrator will do the provisiong of User, based on the data available ResearchStaff role table.
 * Note:- Previously this finctionality was done at UserDataMigrator.
 * @see gov.nih.nci.cabig.caaers.datamigrator.UserDataMigrator
 *
 * @author: Biju Joseph
 */
public class UserPrivilegeMigrator extends CaaersDataMigratorTemplate {

    private String authenticationMode="local";
    private UserRepository userRepository;
    private Map<Long, _User> userDataMap = new HashMap<Long, _User>();


    //SQLs
    StringBuilder rsSitePGSQL = new StringBuilder();
    StringBuilder rsSiteOracleSQL = new StringBuilder();
    StringBuilder rsStudyPGSQL = new StringBuilder();
    StringBuilder rsStudyOracleSQL = new StringBuilder();

    StringBuilder invSitePGSQL = new StringBuilder();
    StringBuilder invSiteOracleSQL = new StringBuilder();
    StringBuilder invStudyOracleSQL = new StringBuilder();
    StringBuilder invStudyPGSQL = new StringBuilder();


    public UserPrivilegeMigrator(){
        
       rsSitePGSQL.append("select c.user_id,srr.role_code,o.nci_institute_code from csm_user  c ")
                .append("join research_staffs r on r.login_id = c.login_name ")
                .append("join site_research_staffs sr on sr.researchstaff_id = r.id ")
                .append("join site_rs_staff_roles srr on srr.site_research_staffs_id = sr.id ")
                .append("join organizations o on o.id = sr.site_id ")
                .append("where srr.start_date < now() ")
                .append("and (srr.end_date is null or srr.end_date > now()) ")
                .append("order by c.user_id, role_code");

       rsSiteOracleSQL.append("select c.user_id,srr.role_code,o.nci_institute_code from csm_user  c ")
                .append("join research_staffs r on r.login_id = c.login_name ")
                .append("join site_research_staffs sr on sr.researchstaff_id = r.id ")
                .append("join site_rs_staff_roles srr on srr.site_research_staffs_id = sr.id ")
                .append("join organizations o on o.id = sr.site_id ")
                .append("where srr.start_date < sysdate ")
                .append("and (srr.end_date is null or srr.end_date > sysdate) ")
                .append("order by c.user_id, role_code");
       
       rsStudyPGSQL.append("select c.user_id, sp.role_code, (select i.value  ")
               .append("   from identifiers i  ")
               .append("   where i.stu_id = s.id  ")
               .append("   and i.type = 'Coordinating Center Identifier' ) as identifier_value  ")
               .append("from study_personnel sp ")
               .append("join study_organizations so on so.id = sp.study_sites_id ")
               .append("join studies s on s.id = so.study_id ")
               .append("join site_research_staffs sr on sr.id = sp.site_research_staffs_id ")
               .append("join research_staffs r on r.id = sr.researchstaff_id ")
               .append("join csm_user c on r.login_id = c.login_name ")
               .append("where sp.start_date < now()  ")
               .append("and (sp.end_date is null or sp.end_date > now() ) ")
               .append("and sp.retired_indicator = false");

       rsStudyOracleSQL.append("select c.user_id, sp.role_code, (select i.value  ")
               .append("   from identifiers i  ")
               .append("   where i.stu_id = s.id  ")
               .append("   and i.type = 'Coordinating Center Identifier' ) as identifier_value  ")
               .append("from study_personnel sp ")
               .append("join study_organizations so on so.id = sp.study_sites_id ")
               .append("join studies s on s.id = so.study_id ")
               .append("join site_research_staffs sr on sr.id = sp.site_research_staffs_id ")
               .append("join research_staffs r on r.id = sr.researchstaff_id ")
               .append("join csm_user c on r.login_id = c.login_name ")
               .append("where sp.start_date < sysdate  ")
               .append("and (sp.end_date is null or sp.end_date > sysdate ) ")
               .append("and sp.retired_indicator = 0");
       
       invSitePGSQL.append("select c.user_id,'ae_reporter', o.nci_institute_code  from csm_user  c ")
               .append("join investigators inv on inv.login_id = c.login_name ")
               .append("join site_investigators si on si.investigator_id = inv.id ")
               .append("join organizations o on o.id = si.site_id ")
               .append("where si.start_date < now() ")
               .append("and (si.end_date is null or si.end_date > now()) ")
               .append("order by c.user_id");

       invSiteOracleSQL.append("select c.user_id,'ae_reporter', o.nci_institute_code  from csm_user  c ")
               .append("join investigators inv on inv.login_id = c.login_name ")
               .append("join site_investigators si on si.investigator_id = inv.id ")
               .append("join organizations o on o.id = si.site_id ")
               .append("where si.start_date < SYSDATE ")
               .append("and (si.end_date is null or si.end_date > SYSDATE ) ")
               .append("order by c.user_id");

       invStudyPGSQL.append("select c.user_id,'ae_reporter' , (select i.value  ")
               .append("      from identifiers i  ")
               .append("      where i.stu_id = so.study_id  ")
               .append("      and i.type = 'Coordinating Center Identifier') from csm_user  c ")
               .append("join investigators inv on inv.login_id = c.login_name ")
               .append("join site_investigators si on si.investigator_id = inv.id ")
               .append("join study_investigators sti on sti.site_investigators_id = si.id ")
               .append("join study_organizations so on so.id = sti.study_sites_id ")
               .append("join organizations o on o.id = si.site_id ")
               .append("where si.start_date < now() ")
               .append("and (si.end_date is null or si.end_date > now()) ")
               .append("order by c.user_id");

       invStudyOracleSQL.append("select c.user_id,'ae_reporter' , (select i.value  ")
               .append("      from identifiers i  ")
               .append("      where i.stu_id = so.study_id  ")
               .append("      and i.type = 'Coordinating Center Identifier') from csm_user  c ")
               .append("join investigators inv on inv.login_id = c.login_name ")
               .append("join site_investigators si on si.investigator_id = inv.id ")
               .append("join study_investigators sti on sti.site_investigators_id = si.id ")
               .append("join study_organizations so on so.id = sti.study_sites_id ")
               .append("join organizations o on o.id = si.site_id ")
               .append("where si.start_date < SYSDATE ")
               .append("and (si.end_date is null or si.end_date > SYSDATE) ")
               .append("order by c.user_id");


    }

    
    public MigraorType migratorType() {
        return MigraorType.USER_PRIVILEGE;  
    }

    @Override
    public void migrate(CaaersDataMigrationContext ctx) {
      log.debug("User Privilege data migration started......");
        
        //only do it for local mode
         if(StringUtils.equals("local", getAuthenticationMode())){
             log.debug("User Privilege data migration : mode is local ");
             //ResearchStaff - find all the site specific roles
             String query = ctx.isOracle() ? rsSiteOracleSQL.toString() : rsSitePGSQL.toString();
             getJdbcTemplate().query(query.toString(), new UserRowMapper(true));

             //ResearchStaff - find all the study specific roles
             query = ctx.isOracle() ? rsStudyOracleSQL.toString() : rsStudyPGSQL.toString();
             getJdbcTemplate().query(query.toString(), new UserRowMapper(false));

             //Investigator - find all the site specific roles
             query = ctx.isOracle() ? invSiteOracleSQL.toString() : invSitePGSQL.toString();
             getJdbcTemplate().query(query.toString(), new UserRowMapper(true));

             //Investigator - find all the study specific roles
             query = ctx.isOracle() ? invStudyOracleSQL.toString() : invStudyPGSQL.toString();
             getJdbcTemplate().query(query.toString(), new UserRowMapper(false));

             if(!userDataMap.isEmpty()){
                 for(_User user : userDataMap.values()){
                     log.debug("provisioning csm_user : " + user.getCsmUser().getUserId());
                     userRepository.provisionUser(user);
                 }
             }
         }

        //threw off the content from Site Research Staff Roles
         getJdbcTemplate().execute("delete from site_rs_staff_roles ");
        
        log.debug("User Privilege data migration done......");
    }
    
    class UserRowMapper implements RowMapper {
       private boolean forSite = false;

       public UserRowMapper(boolean forSite){
          this.forSite = forSite;
       }
       public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
             long userId = rs.getLong(1);
             String roleCode = rs.getString(2);
             String entityIdentifierValue = rs.getString(3);

             UserGroupType role = valueOf(roleCode);
             _User user = userDataMap.get(userId);
             if(user == null){
                 user = new _User();
                 user.getCsmUser().setUserId(userId);
                 userDataMap.put(userId, user);
             }
             RoleMembership roleMembership = user.findRoleMembership(role);
             //NOTE - USER_ADMINISTRATOR AND PO_MANAGER roles needs to be all site.
             if(role == user_administrator || role == person_and_organization_information_manager){
                 if(forSite) {
                     roleMembership.setAllSite(true);
                 }else{
                     roleMembership.setAllStudy(true);
                 }
             }else{
                if(forSite){
                   roleMembership.addOrganizationNCICode(entityIdentifierValue);
                }else{
                    roleMembership.addStudyIdentifier(entityIdentifierValue);
                }

             }

             return null;
       }
    }

    public String getAuthenticationMode() {
        return authenticationMode;
    }

    public void setAuthenticationMode(String authenticationMode) {
        this.authenticationMode = authenticationMode;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
