class UpdateStudyCoordinator extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        //view AE 
        insert(
            'csm_user_group_role_pg',
            [group_id: -4, role_id: -9, user_group_role_pg_id: -69, protection_group_id: -3],
            primaryKey: false
        )    
        //create study    
        insert(
            'csm_user_group_role_pg',
            [group_id: -4, role_id: -1, user_group_role_pg_id: -70, protection_group_id: -1],
            primaryKey: false
        ) 
        //view subject
        insert(
            'csm_user_group_role_pg',
            [group_id: -4, role_id: -6, user_group_role_pg_id: -71, protection_group_id: -2],
            primaryKey: false
        ) 
    }

    void down(){
        execute("delete from csm_user_group_role_pg where group_id=-4 and user_group_role_pg_id=-69");
		execute("delete from csm_user_group_role_pg where group_id=-4 and user_group_role_pg_id=-70");
		execute("delete from csm_user_group_role_pg where group_id=-4 and user_group_role_pg_id=-71");
    }
}