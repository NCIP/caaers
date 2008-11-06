class UpdateAdverseEventCoordinator extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        //cannot view study , delete extra record for read study 
        execute("delete from csm_user_group_role_pg where group_id=-13 and user_group_role_pg_id=-56");
        execute("delete from csm_user_group_role_pg where group_id=-13 and user_group_role_pg_id=-67");
        
        //create ae
        insert(
            'csm_user_group_role_pg',
            [group_id: -13, role_id: -7, user_group_role_pg_id: -68, protection_group_id: -3],
            primaryKey: false
        )        
    }

    void down(){
        execute("delete from csm_user_group_role_pg where group_id=-13 and user_group_role_pg_id=-68");
        execute("delete from csm_group where group_id=-13 and group_name='caaers_ae_cd'");
    }
}