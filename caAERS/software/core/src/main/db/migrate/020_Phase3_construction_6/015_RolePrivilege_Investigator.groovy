class RolePrivilegeSeedDataInvestigator extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {
      insert('role_privilege', [ version: '0',role_name : 'person_and_organization_information_manager',object_id : 'gov.nih.nci.cabig.caaers.domain.Investigator',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'data_reader',object_id : 'gov.nih.nci.cabig.caaers.domain.Investigator',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'user_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.Investigator',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'person_and_organization_information_manager',object_id : 'gov.nih.nci.cabig.caaers.domain.Investigator',privilege :'CREATE']);
      insert('role_privilege', [ version: '0',role_name : 'user_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.Investigator',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'person_and_organization_information_manager',object_id : 'gov.nih.nci.cabig.caaers.domain.Investigator',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'user_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.SiteInvestigatorRole',privilege :'UPDATE']);
    }
    void down(){
      //not required. 
    }
}