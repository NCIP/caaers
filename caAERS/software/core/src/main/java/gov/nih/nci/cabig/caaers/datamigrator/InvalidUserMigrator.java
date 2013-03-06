/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.datamigrator;

import gov.nih.nci.security.UserProvisioningManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Biju Joseph
 */
public class InvalidUserMigrator extends CaaersDataMigratorTemplate {

    private UserProvisioningManager userProvisioningManager;

    public MigraorType migratorType() {
        return MigraorType.INVALID_USER;
    }

    /**
     * Will correct the corrupted user records.
     * https://tracker.nci.nih.gov/browse/CAAERS-4678
     * @param ctx
     */
    @Override
    public void migrate(CaaersDataMigrationContext ctx) {
        List<String> unwantedLogins = new ArrayList<String>();
        List<String> loginNames = getJdbcTemplate().queryForList("select login_name from caaers_users", String.class);
        if(loginNames != null){
            for(String loginName : loginNames){
               try{
                   Object o = userProvisioningManager.getUser(loginName);
                   if(o != null) continue;
               }catch(Exception ignore){

               }
               unwantedLogins.add(loginName);
            }

            if(!unwantedLogins.isEmpty()){

                log.info("The following logins do not have equivalent CSM entries : " + unwantedLogins.toString());

                for(String loginName : unwantedLogins){
                    //remove the reference to caAERS user
                    getJdbcTemplate().update("update research_staffs set user_id = null where user_id in (select id from caaers_users where login_name = '" + loginName + "')");
                    getJdbcTemplate().update("update investigators set user_id = null where user_id in (select id from caaers_users where login_name = '" + loginName + "')");

                    //remove the caAERS user
                    getJdbcTemplate().update("delete from caaers_users where login_name = '" + loginName + "'");

                }

            }
        }
    }

    public UserProvisioningManager getUserProvisioningManager() {
        return userProvisioningManager;
    }

    public void setUserProvisioningManager(UserProvisioningManager userProvisioningManager) {
        this.userProvisioningManager = userProvisioningManager;
    }
}
