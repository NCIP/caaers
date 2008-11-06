class UpdateSiteCoordinator extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	//cannot update AE
    	execute("delete from csm_user_group_role_pg where group_id=-14 and user_group_role_pg_id=-31");
    	

        //create subject
        insert(
            'csm_user_group_role_pg',
            [group_id: -14, role_id: -4, user_group_role_pg_id: -72, protection_group_id: -2],
            primaryKey: false
        ) 
    }

    void down(){
        execute("delete from csm_user_group_role_pg where group_id=-14 and user_group_role_pg_id=-72");
    	execute("delete from csm_group where group_id=-14 and group_name='caaers_site_cd'");
    }
}	