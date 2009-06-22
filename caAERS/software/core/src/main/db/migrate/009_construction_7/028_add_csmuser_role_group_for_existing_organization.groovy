class UpdateCsmPrivileges extends edu.northwestern.bioinformatics.bering.Migration {

void up() {

        insert('csm_user_group_role_pg',[USER_GROUP_ROLE_PG_ID: -38,GROUP_ID: -6, PROTECTION_GROUP_ID: -6, ROLE_ID: -14], primaryKey: false);
         insert('csm_user_group_role_pg',[USER_GROUP_ROLE_PG_ID: -39,GROUP_ID: -7, PROTECTION_GROUP_ID: -7, ROLE_ID: -14], primaryKey: false);
         insert('csm_user_group_role_pg',[USER_GROUP_ROLE_PG_ID: -40,GROUP_ID: -8, PROTECTION_GROUP_ID: -8, ROLE_ID: -14], primaryKey: false);
         insert('csm_user_group_role_pg',[USER_GROUP_ROLE_PG_ID: -41,GROUP_ID: -9, PROTECTION_GROUP_ID: -9, ROLE_ID: -14], primaryKey: false);
         insert('csm_user_group_role_pg',[USER_GROUP_ROLE_PG_ID: -42,GROUP_ID: -10, PROTECTION_GROUP_ID: -10, ROLE_ID: -14], primaryKey: false);
         insert('csm_user_group_role_pg',[USER_GROUP_ROLE_PG_ID: -43,GROUP_ID: -11, PROTECTION_GROUP_ID: -11, ROLE_ID: -14], primaryKey: false);

	}


	void down(){
	    execute("delete from csm_user_group_role_pg where USER_GROUP_ROLE_PG_ID IN (-28,-39,-40,-41,-42,-43)");

	}


}