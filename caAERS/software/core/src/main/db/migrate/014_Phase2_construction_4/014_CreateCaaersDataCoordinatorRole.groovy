class AddDataCoordinator extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        insert(
            'csm_group',
            [group_id: -7942, group_name:'caaers_data_cd', group_desc: 'caaers data coordinator', application_id: -1],
            primaryKey: false
        )
        insert(
            'csm_user_group_role_pg',
            [group_id: -7942, role_id: -15, user_group_role_pg_id: -8945, protection_group_id: -12],
            primaryKey: false
        )
        insert(
            'csm_user_group_role_pg',
            [group_id: -7942, role_id: -9, user_group_role_pg_id: -8946, protection_group_id: -3],
            primaryKey: false
        )
        
    }

    void down(){
        execute("delete from csm_user_group_role_pg where group_id=-7942 and user_group_role_pg_id=-8945");
        execute("delete from csm_user_group_role_pg where group_id=-7942 and user_group_role_pg_id=-8946");
        execute("delete from csm_group where group_id=-7942 and group_name='caaers_data_cd'");
    }
}