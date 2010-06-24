class RolePrivilegeImport extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {
      insert('role_privilege', [ version: '0',role_name : 'ae_rule_and_report_manager',object_id : 'gov.nih.nci.cabig.caaers.domain.Rule',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'data_reader',object_id : 'gov.nih.nci.cabig.caaers.domain.Rule',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'ae_rule_and_report_manager',object_id : 'gov.nih.nci.cabig.caaers.domain.Rule',privilege :'CREATE']);
      insert('role_privilege', [ version: '0',role_name : 'ae_rule_and_report_manager',object_id : 'gov.nih.nci.cabig.caaers.domain.Rule',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'ae_rule_and_report_manager',object_id : 'gov.nih.nci.cabig.caaers.domain.Rule',privilege :'DELETE']);
      
    }
    void down(){
      //not required.
    }
}