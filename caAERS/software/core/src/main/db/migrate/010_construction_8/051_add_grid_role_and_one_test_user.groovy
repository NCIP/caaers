class UpdateCsmPrivileges extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
       insert('csm_group',
       [GROUP_ID: -12,GROUP_NAME:
       "caaers_grid_user",GROUP_DESC:"caAERS grid user",application_id: -1],
        primaryKey: false);


        // Gives caaers_grid_user group the
         //gov.nih.nci.cabig.caaers.domain.Participant:READ role on
        //gov.nih.nci.cabig.caaers.domain.Participant protection group
        		insert('csm_user_group_role_pg',
        			[
        				user_group_role_pg_id: -61,
        				group_id: -12, role_id: -6, protection_group_id: -2
        			],
        			primaryKey: false
        		)

        // Gives caaers_grid_user group the
                 //gov.nih.nci.cabig.caaers.domain.Study:READ role on
                //gov.nih.nci.cabig.caaers.domain.Study protection group
                		insert('csm_user_group_role_pg',
                			[
                				user_group_role_pg_id: -62,
                				group_id: -12, role_id: -3, protection_group_id: -1
                			],
                			primaryKey: false
                		)

      // Gives caaers_grid_user group the
               //gov.nih.nci.cabig.caaers.domain.Rule:READ role on
              //gov.nih.nci.cabig.caaers.domain.Rule protection group
              		insert('csm_user_group_role_pg',
              			[
              				user_group_role_pg_id: -63,
              				group_id: -12, role_id: -12, protection_group_id: -4
              			],
              			primaryKey: false
              		)

    // Gives caaers_grid_user group the
             //gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport:READ role on
            //gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport protection group
            		insert('csm_user_group_role_pg',
            			[
            				user_group_role_pg_id: -64,
            				group_id: -12, role_id: -9, protection_group_id: -3
            			],
            			primaryKey: false
            		)

     // Gives caaers_site_cd group the
                                         		// gov.nih.nci.cabig.caaers.TASK.ACCESS role on
                                         		// gov.nih.nci.cabig.caaers.TASK protection group
                                         		insert(
                                         			'csm_user_group_role_pg',
                                         			[
                                         				user_group_role_pg_id: -65,
                                         				group_id: -12, role_id: -15, protection_group_id: -12
                                         			],
                                         			primaryKey: false
                                         		)
// Gives caaers_grid_user group the
                                    		// gov.nih.nci.cabig.caaers.Organization.ACCESS role on
                                    		// gov.nih.nci.cabig.caaers.organization protection group

       insert('csm_user_group_role_pg',
       [USER_GROUP_ROLE_PG_ID: -66,GROUP_ID: -12, PROTECTION_GROUP_ID: -5, ROLE_ID: -14], primaryKey: false);

     // now insert the csm_user

		insert('CSM_USER',
			[
				USER_ID: -8,LOGIN_NAME: 'grid_user1', FIRST_NAME: 'grid_user1 ', LAST_NAME: 'grid_user1',
                PASSWORD: 'yDq1c1nU4E7almNpMZNexg==',EMAIL_ID: 'grid_user1@nci.nih.gov'
			],
			primaryKey: false
		)
            insert('CSM_USER_GROUP',
            			[USER_GROUP_ID: -16,group_id: -12, USER_ID: -8],primaryKey: false
            	   )


	}
	void down(){
		execute("DELETE FROM CSM_USER_GROUP WHERE USER_ID=-8");
        execute("DELETE FROM CSM_USER WHERE USER_ID=-8");
         execute("DELETE FROM csm_user_group_role_pg WHERE group_id=-12");
         execute("DELETE FROM csm_group WHERE group_id=-12");
        				
		}
}