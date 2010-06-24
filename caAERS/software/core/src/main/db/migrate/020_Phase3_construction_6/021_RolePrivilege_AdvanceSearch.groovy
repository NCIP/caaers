class RolePrivilegeAdvanceSearch extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {
      insert('role_privilege', [ version: '0',role_name : 'data_reader',object_id : 'gov.nih.nci.cabig.caaers.domain.AdvanceSearch',privilege :'ACCESS']);
      insert('role_privilege', [ version: '0',role_name : 'data_analyst',object_id : 'gov.nih.nci.cabig.caaers.domain.AdvanceSearch',privilege :'ACCESS']);
      insert('role_privilege', [ version: '0',role_name : 'data_analyst',object_id : 'gov.nih.nci.cabig.caaers.domain.AdvanceSearch',privilege :'CREATE']);
      
    }
    void down(){
      //not required. 
    }
}