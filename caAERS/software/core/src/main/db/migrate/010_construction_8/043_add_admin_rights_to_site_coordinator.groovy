class UpdateCsmPrivileges extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){


		// GRANT PRIVILEGES TO GROUPS //


		// Gives caaers_site_coordinator group the
		// gov.nih.nci.cabig.caaers.Admin.ACCESS role on
		// gov.nih.nci.cabig.caaers.Admin protection group
		insert('csm_user_group_role_pg',
			[
				user_group_role_pg_id: -54,
				group_id: -14, role_id: -13, protection_group_id: -13
			],
			primaryKey: false
		)





	}
	void down(){
		execute("DELETE FROM csm_user_group_role_pg WHERE user_group_role_pg_id=-54");
		}
}