class UpdateCsmPrivileges extends edu.northwestern.bioinformatics.bering.Migration {

void up() {

 insert('csm_user_group_role_pg',[group_id: -14,role_id: -10,user_group_role_pg_id: -35,protection_group_id: -4],primaryKey: false)

 insert('csm_user_group_role_pg',[group_id: -14,role_id: -11,user_group_role_pg_id: -36,protection_group_id: -4],primaryKey: false)

 insert('csm_user_group_role_pg',[group_id: -14,role_id: -12,user_group_role_pg_id: -37,protection_group_id: -4],primaryKey: false)



}


	void down(){
        execute("delete from csm_user_group_role_pg where group_id=-14 and user_group_role_pg_id=-35");
        execute("delete from csm_user_group_role_pg where group_id=-14 and user_group_role_pg_id=-36");
        execute("delete from csm_user_group_role_pg where group_id=-14 and user_group_role_pg_id=-37");
        
	}


}