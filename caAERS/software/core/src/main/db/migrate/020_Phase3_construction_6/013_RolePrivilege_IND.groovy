class RolePrivilegeSeedDataConfigCaaersPage extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {
      insert('role_privilege', [ version: '0',role_name : 'business_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'data_reader',object_id : 'gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'business_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug',privilege :'CREATE']);
      insert('role_privilege', [ version: '0',role_name : 'business_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug',privilege :'UPDATE']);
      
    }
    void down(){
      //not required. 
    }
}