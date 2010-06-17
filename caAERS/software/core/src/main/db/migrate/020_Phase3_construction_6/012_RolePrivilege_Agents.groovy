class RolePrivilegeSeedDataConfigCaaersPage extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {
      
          insert('role_privilege', [ version: '0',role_name : 'business_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.Agent',privilege :'READ']);
          insert('role_privilege', [ version: '0',role_name : 'data_reader',object_id : 'gov.nih.nci.cabig.caaers.domain.Agent',privilege :'READ']);
          insert('role_privilege', [ version: '0',role_name : 'business_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.Agent',privilege :'CREATE']);
          insert('role_privilege', [ version: '0',role_name : 'business_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.Agent',privilege :'UPDATE']);

    }
    void down(){
      //not required. 
    }
}