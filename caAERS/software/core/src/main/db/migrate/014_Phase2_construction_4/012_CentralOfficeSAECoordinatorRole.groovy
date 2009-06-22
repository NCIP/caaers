class AddCentralOfficeSAECoordinator extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        insert(
            'csm_group',
            [group_id: -7943, group_name:'caaers_central_office_sae_cd', group_desc: 'caaers central office SAE coordinator', application_id: -1],
            primaryKey: false
        )
        insert(
            'csm_user_group_role_pg',
            [group_id: -7943, role_id: -15, user_group_role_pg_id: -8947, protection_group_id: -12],
            primaryKey: false
        )
        insert(
            'csm_user_group_role_pg',
            [group_id: -7943, role_id: -9, user_group_role_pg_id: -8948, protection_group_id: -3],
            primaryKey: false
        )
        
    }

    void down(){
        execute("delete from csm_user_group_role_pg where group_id=-7943 and user_group_role_pg_id=-8947");
        execute("delete from csm_user_group_role_pg where group_id=-7943 and user_group_role_pg_id=-8948");
        execute("delete from csm_group where group_id=-7943 and group_name='caaers_central_office_sae_cd'");
    }
}