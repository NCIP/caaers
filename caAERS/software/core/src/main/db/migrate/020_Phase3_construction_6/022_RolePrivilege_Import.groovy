class RolePrivilegeImport extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {
      insert('role_privilege', [ version: '0',role_name : 'data_importer',object_id : 'gov.nih.nci.cabig.caaers.domain.Import',privilege :'ACCESS']);
      insert('role_privilege', [ version: '0',role_name : 'data_importer',object_id : 'gov.nih.nci.cabig.caaers.domain.Investigator',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'data_importer',object_id : 'gov.nih.nci.cabig.caaers.domain.ResearchStaff',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'data_importer',object_id : 'gov.nih.nci.cabig.caaers.domain.Organization',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'data_importer',object_id : 'gov.nih.nci.cabig.caaers.domain.Agent',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'data_importer',object_id : 'gov.nih.nci.cabig.caaers.domain.Study',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'data_importer',object_id : 'gov.nih.nci.cabig.caaers.domain.Participant',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'data_importer',object_id : 'gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'data_importer',object_id : 'gov.nih.nci.cabig.caaers.domain.MeddraVersion',privilege :'UPDATE']);
      
      
    }
    void down(){
      //not required.
    }
}