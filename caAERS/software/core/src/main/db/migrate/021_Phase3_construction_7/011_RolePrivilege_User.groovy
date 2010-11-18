class RolePrivilegeSeedDataUser extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {
      insert('role_privilege', [ version: '0',role_name : 'user_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.CSMUser',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'user_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.CSMUser',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'user_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.CSMUser',privilege :'CREATE']);
    }
    void down(){
      //not required. 
    }
}