class RolePrivilegeSeedDataConfigCaaersPage extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {

      insert('role_privilege', [ version: '0',role_name : 'business_administrator',object_id : 'gov.nih.nci.cabig.caaers.tools.configuration.Configuration',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'business_administrator',object_id : 'gov.nih.nci.cabig.caaers.tools.configuration.Configuration',privilege :'UPDATE']);

    }
    void down(){
      //not required.
    }
}