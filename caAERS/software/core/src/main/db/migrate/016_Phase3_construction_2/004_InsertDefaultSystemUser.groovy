class UpdateCsmPrivileges extends edu.northwestern.bioinformatics.bering.Migration {
	
	void up(){
		
		 insert('CSM_USER',[USER_ID: -9,
		                    LOGIN_NAME: 'SYSTEM', 
		                    FIRST_NAME: 'System ', 
		                    LAST_NAME: 'System',
		                    PASSWORD: 'yDq1c1nU4E7almNpMZNexg==', 
		                    EMAIL_ID: 'caaers-system@semanticbits.com', 
		                    UPDATE_DATE: '27-JAN-2007' ], primaryKey: false )


		   insert('CSM_USER_GROUP',
            			[USER_GROUP_ID: -17,group_id: -1, USER_ID: -9],primaryKey: false )

            insert('CSM_USER_GROUP',
            			[USER_GROUP_ID: -18,group_id: -2, USER_ID: -9],primaryKey: false )


            insert('CSM_USER_GROUP',
            			[USER_GROUP_ID: -19,group_id: -3, USER_ID: -9],primaryKey: false )


            insert('CSM_USER_GROUP',
            			[USER_GROUP_ID: -20,group_id: -4, USER_ID: -9],primaryKey: false )


            insert('CSM_USER_GROUP',
            			[USER_GROUP_ID: -21,group_id: -5, USER_ID: -9],primaryKey: false )

	}
	
	void down(){
		execute("DELETE FROM CSM_USER_GROUP WHERE USER_ID=-9");
        execute("DELETE FROM CSM_USER WHERE USER_ID=-9");
        				
	}
}