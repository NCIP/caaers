class updatePhysicianCsmRole extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        insert(
            'csm_user_group_role_pg',
            [group_id: -8, role_id: -7, user_group_role_pg_id: -8944, protection_group_id: -3],
            primaryKey: false
        )
    }

    void down(){
        execute("delete from csm_user_group_role_pg where group_id=-8 and user_group_role_pg_id=-8944");
    }
}
