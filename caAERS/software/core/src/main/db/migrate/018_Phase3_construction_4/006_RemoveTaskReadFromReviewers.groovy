class RemoveReportRead extends edu.northwestern.bioinformatics.bering.Migration {
  void up() {
     execute("delete from csm_user_group_role_pg where group_id=-7943 and user_group_role_pg_id in (-8948,-8946)");
  }
  void down(){
     insert('csm_user_group_role_pg',
        	 [user_group_role_pg_id: -8948,group_id: -7943, role_id: -9, protection_group_id: -3],
        	 primaryKey: false);
      insert('csm_user_group_role_pg',
        	 [user_group_role_pg_id: -8946,group_id: -7942, role_id: -9, protection_group_id: -3],
        	 primaryKey: false);
  }
}