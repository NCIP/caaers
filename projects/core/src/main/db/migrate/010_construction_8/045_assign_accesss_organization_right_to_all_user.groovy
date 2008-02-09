class updateStudyDcpDesignColumn  extends edu.northwestern.bioinformatics.bering.Migration {


    public void up(){
   insert('csm_user_group_role_pg',
   [USER_GROUP_ROLE_PG_ID: -57,GROUP_ID: -4, PROTECTION_GROUP_ID: -5, ROLE_ID: -14], primaryKey: false);


       insert('csm_user_group_role_pg',
       [USER_GROUP_ROLE_PG_ID: -58,GROUP_ID: -5, PROTECTION_GROUP_ID: -5, ROLE_ID: -14], primaryKey: false);


         insert('csm_user_group_role_pg',
         [USER_GROUP_ROLE_PG_ID: -59,GROUP_ID: -13, PROTECTION_GROUP_ID: -5, ROLE_ID: -14], primaryKey: false);


           insert('csm_user_group_role_pg',
           [USER_GROUP_ROLE_PG_ID: -60,GROUP_ID: -14, PROTECTION_GROUP_ID: -5, ROLE_ID: -14], primaryKey: false);



    }


    public void down(){

    }

  }
