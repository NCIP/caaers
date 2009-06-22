class UpdateCaaersPhysician extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        //view home page (task page)
        insert(
            'csm_user_group_role_pg',
            [group_id: -8, role_id: -15, user_group_role_pg_id: -73, protection_group_id: -12],
            primaryKey: false
        )        
    }

    void down(){
        execute("delete from csm_user_group_role_pg where group_id=-8 and user_group_role_pg_id=-73");
    }
}