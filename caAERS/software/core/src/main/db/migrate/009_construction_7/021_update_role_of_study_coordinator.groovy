class UpdateCsmPrivileges extends edu.northwestern.bioinformatics.bering.Migration {

void up() {
        execute("delete from csm_user_group_role_pg where group_id=-4 and role_id=-1");
}


	void down(){
          insert(
      			'csm_user_group_role_pg',
      			[
      				group_id: -4,
      				role_id: -1,
      				user_group_role_pg_id: -1,
      				protection_group_id: -1
      			],
      			primaryKey: false
      		)

	}


}