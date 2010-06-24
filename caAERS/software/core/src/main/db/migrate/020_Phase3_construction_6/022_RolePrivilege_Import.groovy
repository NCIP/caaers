class RolePrivilegeImport extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {
      insert('role_privilege', [ version: '0',role_name : 'data_importer',object_id : 'gov.nih.nci.cabig.caaers.domain.Import',privilege :'ACCESS']);
      
    }
    void down(){
      //not required.
    }
}