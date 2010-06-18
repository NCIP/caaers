class RolePrivilegeSeedDataOrganization extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {
      insert('role_privilege', [ version: '0',role_name : 'person_and_organization_information_manager',object_id : 'gov.nih.nci.cabig.caaers.domain.Organization',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'data_reader',object_id : 'gov.nih.nci.cabig.caaers.domain.Organization',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'person_and_organization_information_manager',object_id : 'gov.nih.nci.cabig.caaers.domain.Organization',privilege :'CREATE']);
      insert('role_privilege', [ version: '0',role_name : 'person_and_organization_information_manager',object_id : 'gov.nih.nci.cabig.caaers.domain.Organization',privilege :'UPDATE']);
      
    }
    void down(){
      //not required. 
    }
}