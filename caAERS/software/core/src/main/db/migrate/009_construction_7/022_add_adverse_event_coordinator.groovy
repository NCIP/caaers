class AddAdverseEventCoordinator extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        insert(
            'csm_group',
            [group_id: -13, group_name:'caaers_ae_cd', group_desc: 'caaers adverse event coordinator', application_id: -1],
            primaryKey: false
        )
        insert(
            'csm_user_group_role_pg',
            [group_id: -13, role_id: -8, user_group_role_pg_id: -26, protection_group_id: -3],
            primaryKey: false
        )
        insert(
            'csm_user_group_role_pg',
            [group_id: -13, role_id: -9, user_group_role_pg_id: -27, protection_group_id: -3],
            primaryKey: false
        )
    }

    void down(){
        execute("delete from csm_user_group_role_pg where group_id=-13 and user_group_role_pg_id=-27");
        execute("delete from csm_user_group_role_pg where group_id=-13 and user_group_role_pg_id=-26");
        // updated ae coordinator in 012_Phase2_construction_2/005_update_adverse_event_coordinator.groovy , moved the following csm_group delete to that file.
        // execute("delete from csm_group where group_id=-13 and group_name='caaers_ae_cd'");
    }
}