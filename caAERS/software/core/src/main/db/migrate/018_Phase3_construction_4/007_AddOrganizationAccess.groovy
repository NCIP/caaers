class AddOrganizationAccessPrivilege extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {

      //insert in 'csm_user_group_role_pg',

      insert('csm_user_group_role_pg',
        	 [user_group_role_pg_id: -8960,group_id: -13, role_id: -14, protection_group_id: -5],
        	 primaryKey: false);
      insert('csm_user_group_role_pg',
        	 [user_group_role_pg_id: -8961,group_id: -7943, role_id: -14, protection_group_id: -5],
        	 primaryKey: false);
      insert('csm_user_group_role_pg',
        	 [user_group_role_pg_id: -8962,group_id: -7942, role_id: -14, protection_group_id: -5],
        	 primaryKey: false);
      insert('csm_user_group_role_pg',
        	 [user_group_role_pg_id: -8963,group_id: -5, role_id: -14, protection_group_id: -5],
        	 primaryKey: false);
      insert('csm_user_group_role_pg',
        	 [user_group_role_pg_id: -8964,group_id: -8, role_id: -14, protection_group_id: -5],
        	 primaryKey: false);
      insert('csm_user_group_role_pg',
        	 [user_group_role_pg_id: -8965,group_id: -14, role_id: -14, protection_group_id: -5],
        	 primaryKey: false);
      insert('csm_user_group_role_pg',
        	 [user_group_role_pg_id: -8966,group_id: -4, role_id: -14, protection_group_id: -5],
        	 primaryKey: false);
      insert('csm_user_group_role_pg',
        	 [user_group_role_pg_id: -8967,group_id: -3, role_id: -14, protection_group_id: -5],
        	 primaryKey: false);
      insert('csm_user_group_role_pg',
        	 [user_group_role_pg_id: -8968,group_id: -2, role_id: -14, protection_group_id: -5],
        	 primaryKey: false);

    }

    void down(){
        execute("delete from csm_user_group_role_pg where user_group_role_pg_id in (-8960, -8961, -8962, -8963, -8964, -8965, -8966, -8967, -8968)");
    }
}