class UpdateCentralOfficeSAECoordinator extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        insert(
            'csm_user_group_role_pg',
            [group_id: -7943, role_id: -8, user_group_role_pg_id: -8949, protection_group_id: -3],
            primaryKey: false
        )
        
    }

    void down(){
        execute("delete from csm_user_group_role_pg where group_id=-7943 and user_group_role_pg_id=-8949");
    }
}