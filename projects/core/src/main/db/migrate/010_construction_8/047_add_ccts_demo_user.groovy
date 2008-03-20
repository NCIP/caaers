class UpdateCsmPrivileges extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){

           if (databaseMatches('oracle')) {
              insert('CSM_USER',
                [
                    USER_ID: -7,LOGIN_NAME: 'cctsdemo1@nci.nih.gov', FIRST_NAME: 'cctsdemo ', LAST_NAME: 'user',
                       PASSWORD: 'yDq1c1nU4E7almNpMZNexg==',EMAIL_ID: 'cctsdemo1@nci.nih.gov', UPDATE_DATE: '27-JAN-2007'

                ],
                primaryKey: false
            )

        } else if (databaseMatches('postgresql')){
             insert('CSM_USER',
                        [
                            USER_ID: -7,LOGIN_NAME: 'cctsdemo1@nci.nih.gov', FIRST_NAME: 'cctsdemo ', LAST_NAME: 'user',
                             PASSWORD: 'yDq1c1nU4E7almNpMZNexg==',EMAIL_ID: 'cctsdemo1@nci.nih.gov', UPDATE_DATE: '27-JAN-2007'

                        ],
                        primaryKey: false
                    )

        } else if (databaseMatches('hsqldb')){
        insert('CSM_USER',
            			[
            				USER_ID: -7,LOGIN_NAME: 'cctsdemo1@nci.nih.gov', FIRST_NAME: 'cctsdemo ', LAST_NAME: 'user',
                            PASSWORD: 'yDq1c1nU4E7almNpMZNexg==',EMAIL_ID: 'cctsdemo1@nci.nih.gov', UPDATE_DATE: '2007-01-27'

            			],
            			primaryKey: false
            		)


       } else {
        insert('CSM_USER',
        			[
        				USER_ID: -7,LOGIN_NAME: 'cctsdemo1@nci.nih.gov', FIRST_NAME: 'cctsdemo ', LAST_NAME: 'user',
                        PASSWORD: 'yDq1c1nU4E7almNpMZNexg==',EMAIL_ID: 'cctsdemo1@nci.nih.gov', UPDATE_DATE: '27-JAN-2007'

        			],
        			primaryKey: false
        		)
                                 
        }

		   insert('CSM_USER_GROUP',
            			[USER_GROUP_ID: -11,group_id: -1, USER_ID: -7],primaryKey: false
            	   )

            insert('CSM_USER_GROUP',
            			[USER_GROUP_ID: -12,group_id: -2, USER_ID: -7],primaryKey: false
            	   )


            insert('CSM_USER_GROUP',
            			[USER_GROUP_ID: -13,group_id: -3, USER_ID: -7],primaryKey: false
            	   )


            insert('CSM_USER_GROUP',
            			[USER_GROUP_ID: -14,group_id: -4, USER_ID: -7],primaryKey: false
            	   )


            insert('CSM_USER_GROUP',
            			[USER_GROUP_ID: -15,group_id: -5, USER_ID: -7],primaryKey: false
            	   )





	}
	void down(){
		execute("DELETE FROM CSM_USER_GROUP WHERE USER_ID=-7");
        execute("DELETE FROM CSM_USER WHERE USER_ID=-7");
        				
		}
}