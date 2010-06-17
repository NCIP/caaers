class RolePrivilegeSeedDataTrackReports extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {
      insert('role_privilege', [ version: '0',role_name : 'system_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.ReportStatus',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'data_reader',object_id : 'gov.nih.nci.cabig.caaers.domain.ReportStatus',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'business_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.ReportStatus',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'system_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.Notification',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'data_reader',object_id : 'gov.nih.nci.cabig.caaers.domain.Notification',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'business_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.Notification',privilege :'READ']);

    }
    void down(){
      //not required. 
    }
}