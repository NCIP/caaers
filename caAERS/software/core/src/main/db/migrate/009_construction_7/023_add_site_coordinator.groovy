class AddSiteCoordinator extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        insert('csm_group',[group_id: -14,group_name:'caaers_site_cd',group_desc: 'caaers site  coordinator',application_id: -1],primaryKey: false)
        insert('csm_user_group_role_pg',[group_id: -14,role_id: -1,user_group_role_pg_id: -28,protection_group_id: -1],primaryKey: false)
        insert('csm_user_group_role_pg',[group_id: -14,role_id: -2,user_group_role_pg_id: -29,protection_group_id: -1],primaryKey: false)
        insert('csm_user_group_role_pg',[group_id: -14,role_id: -3,user_group_role_pg_id: -30,protection_group_id: -1],primaryKey: false)
        insert('csm_user_group_role_pg',[group_id: -14,role_id: -8,user_group_role_pg_id: -31,protection_group_id: -3],primaryKey: false)
        insert('csm_user_group_role_pg',[group_id: -14,role_id: -9,user_group_role_pg_id: -32,protection_group_id: -3],primaryKey: false)
        insert('csm_user_group_role_pg',[group_id: -14,role_id: -5,user_group_role_pg_id: -33,protection_group_id: -2],primaryKey: false)
        insert('csm_user_group_role_pg',[group_id: -14,role_id: -6,user_group_role_pg_id: -34,protection_group_id: -2],primaryKey: false)
    }

    void down(){
        execute("delete from csm_user_group_role_pg where group_id=-14 and user_group_role_pg_id=-28");
        execute("delete from csm_user_group_role_pg where group_id=-14 and user_group_role_pg_id=-29");
        execute("delete from csm_user_group_role_pg where group_id=-14 and user_group_role_pg_id=-30");
        execute("delete from csm_user_group_role_pg where group_id=-14 and user_group_role_pg_id=-31");
        execute("delete from csm_user_group_role_pg where group_id=-14 and user_group_role_pg_id=-32");
        execute("delete from csm_user_group_role_pg where group_id=-14 and user_group_role_pg_id=-33");
        execute("delete from csm_user_group_role_pg where group_id=-14 and user_group_role_pg_id=-34");
        // updated site coordinator in 012_Phase2_construction_2/007_update_site_coordinator.groovy , moved the following csm_group delete to that file.
        // execute("delete from csm_group where group_id=-14 and group_name='caaers_site_cd'");
    }
}