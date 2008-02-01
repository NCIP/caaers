class UpdateCsmPrivileges extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){

		// PROTECTION GROUPS //

    insert('csm_protection_group',
			[
				protection_group_id: -12,
				protection_group_name: 'gov.nih.nci.cabig.caaers.Task',
				protection_group_description: 'Task Page Protection Group',
				application_id: -1, large_element_count_flag: 0
			],
			primaryKey: false
    )
	insert('csm_protection_element',
			[
				protection_element_id: -12,
				protection_element_name: 'gov.nih.nci.cabig.caaers.Task',
				protection_element_description: 'Task page protection element',
				object_id: 'gov.nih.nci.cabig.caaers.Task', attribute: '',
				application_id: -1
			],
			primaryKey: false
	)

		// PROTECTION_GROUP TO PROTECTION_ELEMENT MAPPINGS //
	insert(	'csm_pg_pe',
			[
				protection_group_id: -12,
				protection_element_id: -12,
			],
			primaryKey: false
	)
	// ROLES //
	insert(	'csm_role',
			[
				role_id: -15, role_name: 'gov.nih.nci.cabig.caaers.Task.ACCESS',
				role_description: 'Role to view the Task page', application_id: -1, active_flag: 1
			],
			primaryKey: false
		)

		// ROLE TO PRIVILEGE MAPPINGS //

		// Maps gov.nih.nci.cabig.caaers.TASK.ACCESS to ACCESS
		insert(
			'csm_role_privilege',
			[
				role_id: -15, privilege_id: -2
			],
			primaryKey: false
		)

		// GRANT PRIVILEGES TO GROUPS //


		// Gives caaers_super_user group the
		// gov.nih.nci.cabig.caaers.TASK.ACCESS role on
		// gov.nih.nci.cabig.caaers.TASK protection group
		insert('csm_user_group_role_pg',
			[
				user_group_role_pg_id: -46,
				group_id: -3, role_id: -15, protection_group_id: -12
			],
			primaryKey: false
		)


                        
          // Gives caaers_user group the
          // gov.nih.nci.cabig.caaers.TASK.ACCESS role on
          // gov.nih.nci.cabig.caaers.TASK protection group
          		insert(
          			'csm_user_group_role_pg',
          			[
          				user_group_role_pg_id: -48,
          				group_id: -2, role_id: -15, protection_group_id: -12
          			],
          			primaryKey: false
          		)
            // Gives caaers_study_cd group the
            // gov.nih.nci.cabig.caaers.TASK.ACCESS role on
            // gov.nih.nci.cabig.caaers.TASK protection group
            		insert(
            			'csm_user_group_role_pg',
            			[
            				user_group_role_pg_id: -49,
            				group_id: -4, role_id: -15, protection_group_id: -12
            			],
            			primaryKey: false
            		)
              // Gives caaers_participant_cd group the
              // gov.nih.nci.cabig.caaers.TASK.ACCESS role on
              // gov.nih.nci.cabig.caaers.TASK protection group
              		insert(
              			'csm_user_group_role_pg',
              			[
              				user_group_role_pg_id: -50,
              				group_id: -5, role_id: -15, protection_group_id: -12
              			],
              			primaryKey: false
              		)
                // Gives caaers_ae_cd group the
                // gov.nih.nci.cabig.caaers.TASK.ACCESS role on
                // gov.nih.nci.cabig.caaers.TASK protection group
                		insert(
                			'csm_user_group_role_pg',
                			[
                				user_group_role_pg_id: -51,
                				group_id: -13, role_id: -15, protection_group_id: -12
                			],
                			primaryKey: false
                		)

                     // Gives caaers_site_cd group the
                                    		// gov.nih.nci.cabig.caaers.TASK.ACCESS role on
                                    		// gov.nih.nci.cabig.caaers.TASK protection group
                                    		insert(
                                    			'csm_user_group_role_pg',
                                    			[
                                    				user_group_role_pg_id: -52,
                                    				group_id: -14, role_id: -15, protection_group_id: -12
                                    			],
                                    			primaryKey: false
                                    		)

	}
	void down(){
		execute("DELETE FROM csm_user_group_role_pg WHERE protection_group_id=-12");
		execute("DELETE FROM csm_pg_pe where protection_group_id=-12");
		execute("DELETE FROM csm_role_privilege where role_id=-15");
		execute("DELETE FROM csm_role where role_id=-15 ");
		execute("DELETE FROM csm_protection_element where protection_element_name='gov.nih.nci.cabig.caaers.Task'");
		execute("DELETE FROM csm_protection_group where protection_group_id ='gov.nih.nci.cabig.caaers.Task'");
	}
}