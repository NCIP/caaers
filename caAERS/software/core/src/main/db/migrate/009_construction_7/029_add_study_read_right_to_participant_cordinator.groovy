class UpdateCsmPrivileges extends edu.northwestern.bioinformatics.bering.Migration {

void up() {

        insert('csm_user_group_role_pg',[USER_GROUP_ROLE_PG_ID: -44,GROUP_ID: -5, PROTECTION_GROUP_ID: -1, ROLE_ID: -3], primaryKey: false);

	}


	void down(){
	    execute("delete from csm_user_group_role_pg where USER_GROUP_ROLE_PG_ID IN (-44)");

	}


}