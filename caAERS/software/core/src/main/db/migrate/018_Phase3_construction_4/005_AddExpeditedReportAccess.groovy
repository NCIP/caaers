class AddExpeditedReportAccessPrivilege extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {

      //add role
      insert('csm_role',
			 [role_id: -16, role_name: 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport.ACCESS',
              role_description: 'Can access expedited report flow', application_id: -1, active_flag: 1],
			 primaryKey: false);

      //map role to privilege
      insert('csm_role_privilege',
              [role_id: -16, privilege_id: -2],
			  primaryKey: false);

      //insert in 'csm_user_group_role_pg',

      insert('csm_user_group_role_pg',
        	 [user_group_role_pg_id: -8950,group_id: -5, role_id: -16, protection_group_id: -3],
        	 primaryKey: false);
      insert('csm_user_group_role_pg',
        	 [user_group_role_pg_id: -8951,group_id: -3, role_id: -16, protection_group_id: -3],
        	 primaryKey: false);
      insert('csm_user_group_role_pg',
        	 [user_group_role_pg_id: -8952,group_id: -2, role_id: -16, protection_group_id: -3],
        	 primaryKey: false);
      insert('csm_user_group_role_pg',
        	 [user_group_role_pg_id: -8953,group_id: -13, role_id: -16, protection_group_id: -3],
        	 primaryKey: false);
      insert('csm_user_group_role_pg',
        	 [user_group_role_pg_id: -8954,group_id: -14, role_id: -16, protection_group_id: -3],
        	 primaryKey: false);
      insert('csm_user_group_role_pg',
        	 [user_group_role_pg_id: -8955,group_id: -12, role_id: -16, protection_group_id: -3],
        	 primaryKey: false);
      insert('csm_user_group_role_pg',
        	 [user_group_role_pg_id: -8956,group_id: -4, role_id: -16, protection_group_id: -3],
        	 primaryKey: false);
      insert('csm_user_group_role_pg',
        	 [user_group_role_pg_id: -8957,group_id: -8, role_id: -16, protection_group_id: -3],
        	 primaryKey: false);

      insert('csm_user_group_role_pg',
        	 [user_group_role_pg_id: -8958,group_id: -7943, role_id: -16, protection_group_id: -3],
        	 primaryKey: false);

      insert('csm_user_group_role_pg',
        	 [user_group_role_pg_id: -8959,group_id: -7942, role_id: -16, protection_group_id: -3],
        	 primaryKey: false);

    }

    void down(){
        execute("delete from csm_user_group_role_pg where user_group_role_pg_id in (-8950, -8951, -8952, -8953, -8954, -8955, -8956, -8957, -8958, -8959)");
        execute("DELETE FROM csm_role_privilege  where role_id=-16 ");
        execute("delete from csm_role where role_id=-16")
    }
}