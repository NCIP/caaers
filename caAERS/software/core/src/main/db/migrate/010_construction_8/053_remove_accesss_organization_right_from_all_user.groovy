class updateOrganizationRoles  extends edu.northwestern.bioinformatics.bering.Migration {


    public void up(){

     // Gives caaers_ae_cd group the
                     //gov.nih.nci.cabig.caaers.domain.Study:READ role on
                    //gov.nih.nci.cabig.caaers.domain.Study protection group
                    		insert('csm_user_group_role_pg',
                    			[
                    				user_group_role_pg_id: -67,
                    				group_id: -13, role_id: -3, protection_group_id: -1
                    			],
                    			primaryKey: false
                    		)


execute("DELETE FROM csm_user_group_role_pg WHERE user_group_role_pg_id=-57");

   execute("DELETE FROM csm_user_group_role_pg WHERE user_group_role_pg_id=-58");

execute("DELETE FROM csm_user_group_role_pg WHERE user_group_role_pg_id=-59");

execute("DELETE FROM csm_user_group_role_pg WHERE user_group_role_pg_id=-60");






    }


    public void down(){
    execute("DELETE FROM csm_user_group_role_pg WHERE user_group_role_pg_id=-67");


    }

  }
