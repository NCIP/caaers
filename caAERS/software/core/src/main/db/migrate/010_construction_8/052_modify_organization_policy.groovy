class MigratePrimaryFundingSponsor extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	if (databaseMatches('postgres')) {
          execute("insert into csm_user_group_role_pg(user_group_role_pg_id, group_id,protection_group_id,role_id) select group_id-1000,group_id,group_id,-14 from csm_group where group_id <= -15 and group_id >= -7941");
        }
        if (databaseMatches('oracle')) {
          execute("insert into csm_user_group_role_pg(user_group_role_pg_id, group_id,protection_group_id,role_id) select group_id-1000,group_id,group_id,-14 from csm_group where group_id <= -15 and group_id >= -7941");
        }
    }

    void down() {
    	execute("DELETE FROM csm_user_group_role_pg WHERE user_group_role_pg_id<=-15");
        
    }
}