class UpdateCsmPrivileges extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){


        insert(
			'csm_protection_group',
			[
				protection_group_id: -5,
				protection_group_name: 'gov.nih.nci.cabig.caaers.domain.Organization',
				protection_group_description: 'Rule Extent Protection Group',
				application_id: -1, large_element_count_flag: 0
			],
			primaryKey: false
		)

		insert(
			'csm_role',
			[
				role_id: -14, role_name: 'gov.nih.nci.cabig.caaers.domain.Organization.ACCESS',
				role_description: '', application_id: -1, active_flag: 1
			],
			primaryKey: false
		)

		////////////////////////////////
		// ROLE TO PRIVILEGE MAPPINGS //
		////////////////////////////////

		// Maps gov.nih.nci.cabig.caaers.domain.Organization.ACCESS to ACCESS
		insert(
			'csm_role_privilege',
			[
				role_id: -14, privilege_id: -2
			],
			primaryKey: false
		)

        //assign to c3pr_admin group
        // insert('csm_user_group_role_pg',[USER_GROUP_ROLE_PG_ID: 33, GROUP_ID: 1, ROLE_ID: 19, PROTECTION_GROUP_ID: 6], primaryKey: false);


	}
	void down(){
		execute("DELETE FROM csm_role_privilege  where role_id=-14 ");
		execute("DELETE FROM csm_role where role_id=-14");
		execute("DELETE FROM csm_protection_group where parent_protection_group_id=-5");
		execute("DELETE FROM csm_protection_group where protection_group_id=-5");

	}
}