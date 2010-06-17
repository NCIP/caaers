class RolePrivilegeSeedDataConfigCaaersPage extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {
      
      insert('role_privilege', [ version: '0',role_name : 'system_administrator',object_id : 'gov.nih.nci.cabig.caaers.tools.configuration.Configuration',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'system_administrator',object_id : 'gov.nih.nci.cabig.caaers.tools.configuration.Configuration',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'data_reader',object_id : 'gov.nih.nci.cabig.caaers.tools.configuration.Configuration',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'system_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'system_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'data_reader',object_id : 'gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'business_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.CaaersFieldDefinition',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'business_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.CaaersFieldDefinition',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'data_reader',object_id : 'gov.nih.nci.cabig.caaers.domain.CaaersFieldDefinition',privilege :'READ']);

    }
    void down(){
      //not required. 
    }
}