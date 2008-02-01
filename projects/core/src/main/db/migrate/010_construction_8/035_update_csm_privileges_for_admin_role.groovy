class UpdateCsmPrivileges extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){

		// PROTECTION GROUPS //

    insert('csm_protection_group',
			[
				protection_group_id: -13,
				protection_group_name: 'gov.nih.nci.cabig.caaers.Admin',
				protection_group_description: 'Admin Protection Group',
				application_id: -1, large_element_count_flag: 0
			],
			primaryKey: false
    )
	insert('csm_protection_element',
			[
				protection_element_id: -13,
				protection_element_name: 'gov.nih.nci.cabig.caaers.Admin',
				protection_element_description: 'Admin page protection element',
				object_id: 'gov.nih.nci.cabig.caaers.Admin', attribute: '',
				application_id: -1
			],
			primaryKey: false
	)

		// PROTECTION_GROUP TO PROTECTION_ELEMENT MAPPINGS //
	insert(	'csm_pg_pe',
			[
				protection_group_id: -13,
				protection_element_id: -13,
			],
			primaryKey: false
	)

	// ROLES //
	insert(	'csm_role',
			[
				role_id: -13, role_name: 'gov.nih.nci.cabig.caaers.Admin.ACCESS',
				role_description: 'Role to view the Admin page', application_id: -1, active_flag: 1
			],
			primaryKey: false
		)

		// ROLE TO PRIVILEGE MAPPINGS //

		// Maps gov.nih.nci.cabig.caaers.Admin.ACCESS to ACCESS
		insert(
			'csm_role_privilege',
			[
				role_id: -13, privilege_id: -2
			],
			primaryKey: false
		)

		// GRANT PRIVILEGES TO GROUPS //


		// Gives caaers_super_user group the
		// gov.nih.nci.cabig.caaers.Admin.ACCESS role on
		// gov.nih.nci.cabig.caaers.Admin protection group
		insert('csm_user_group_role_pg',
			[
				user_group_role_pg_id: -53,
				group_id: -3, role_id: -13, protection_group_id: -13
			],
			primaryKey: false
		)





	}
	void down(){
		execute("DELETE FROM csm_user_group_role_pg WHERE protection_group_id=-13");
		execute("DELETE FROM csm_pg_pe where protection_group_id=-13");
		execute("DELETE FROM csm_role_privilege where role_id=-13");
		execute("DELETE FROM csm_role where role_id=-13 ");
		execute("DELETE FROM csm_protection_element where protection_element_name='gov.nih.nci.cabig.caaers.Admin'");
		execute("DELETE FROM csm_protection_group where protection_group_id ='gov.nih.nci.cabig.caaers.Admin'");
	}
}