class RolePrivilegeSeedDataResearchStaff extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {
      insert('role_privilege', [ version: '0',role_name : 'person_and_organization_information_manager',object_id : 'gov.nih.nci.cabig.caaers.domain.ResearchStaff',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'data_reader',object_id : 'gov.nih.nci.cabig.caaers.domain.ResearchStaff',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'user_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.ResearchStaff',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'person_and_organization_information_manager',object_id : 'gov.nih.nci.cabig.caaers.domain.ResearchStaff',privilege :'CREATE']);
      insert('role_privilege', [ version: '0',role_name : 'user_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.ResearchStaff',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'person_and_organization_information_manager',object_id : 'gov.nih.nci.cabig.caaers.domain.ResearchStaff',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'user_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.SiteResearchStaffRole',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'user_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.SiteResearchStaffRole',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'user_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.SiteResearchStaffRole',privilege :'CREATE']);
      insert('role_privilege', [ version: '0',role_name : 'data_reader',object_id : 'gov.nih.nci.cabig.caaers.domain.SiteResearchStaffRole',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'person_and_organization_information_manager',object_id : 'gov.nih.nci.cabig.caaers.domain.SiteResearchStaffRole',privilege :'READ']);

      
    }
    void down(){
      //not required. 
    }
}