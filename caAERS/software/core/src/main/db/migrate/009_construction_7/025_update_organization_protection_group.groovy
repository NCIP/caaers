class UpdateCsmPrivileges extends edu.northwestern.bioinformatics.bering.Migration {

void up() {

        execute("update csm_user_group_role_pg set group_id=-1 where group_id=-12");
        execute("delete from csm_group where group_id=-12");
}


	void down(){

	}


}