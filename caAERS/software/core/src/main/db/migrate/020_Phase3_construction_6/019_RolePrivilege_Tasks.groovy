class RolePrivilegeSeedDataOrganization extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {

      insert('role_privilege', [ version: '0',role_name : 'system_administrator',object_id : 'gov.nih.nci.cabig.caaers.Task',privilege :'ACCESS']);
      insert('role_privilege', [ version: '0',role_name : 'business_administrator',object_id : 'gov.nih.nci.cabig.caaers.Task',privilege :'ACCESS']);
      insert('role_privilege', [ version: '0',role_name : 'person_and_organization_information_manager',object_id : 'gov.nih.nci.cabig.caaers.Task',privilege :'ACCESS']);
      insert('role_privilege', [ version: '0',role_name : 'data_importer',object_id : 'gov.nih.nci.cabig.caaers.Task',privilege :'ACCESS']);
      insert('role_privilege', [ version: '0',role_name : 'user_administrator',object_id : 'gov.nih.nci.cabig.caaers.Task',privilege :'ACCESS']);
      insert('role_privilege', [ version: '0',role_name : 'study_qa_manager',object_id : 'gov.nih.nci.cabig.caaers.Task',privilege :'ACCESS']);
      insert('role_privilege', [ version: '0',role_name : 'study_creator',object_id : 'gov.nih.nci.cabig.caaers.Task',privilege :'ACCESS']);
      insert('role_privilege', [ version: '0',role_name : 'supplemental_study_information_manager',object_id : 'gov.nih.nci.cabig.caaers.Task',privilege :'ACCESS']);
      insert('role_privilege', [ version: '0',role_name : 'study_team_administrator',object_id : 'gov.nih.nci.cabig.caaers.Task',privilege :'ACCESS']);
      insert('role_privilege', [ version: '0',role_name : 'study_site_participation_administrator',object_id : 'gov.nih.nci.cabig.caaers.Task',privilege :'ACCESS']);
      insert('role_privilege', [ version: '0',role_name : 'ae_rule_and_report_manager',object_id : 'gov.nih.nci.cabig.caaers.Task',privilege :'ACCESS']);
      insert('role_privilege', [ version: '0',role_name : 'study_calendar_template_builder',object_id : 'gov.nih.nci.cabig.caaers.Task',privilege :'ACCESS']);
      insert('role_privilege', [ version: '0',role_name : 'registration_qa_manager',object_id : 'gov.nih.nci.cabig.caaers.Task',privilege :'ACCESS']);
      insert('role_privilege', [ version: '0',role_name : 'subject_manager',object_id : 'gov.nih.nci.cabig.caaers.Task',privilege :'ACCESS']);
      insert('role_privilege', [ version: '0',role_name : 'study_subject_calendar_manager',object_id : 'gov.nih.nci.cabig.caaers.Task',privilege :'ACCESS']);
      insert('role_privilege', [ version: '0',role_name : 'registrar',object_id : 'gov.nih.nci.cabig.caaers.Task',privilege :'ACCESS']);
      insert('role_privilege', [ version: '0',role_name : 'ae_reporter',object_id : 'gov.nih.nci.cabig.caaers.Task',privilege :'ACCESS']);
      insert('role_privilege', [ version: '0',role_name : 'ae_expedited_report_reviewer',object_id : 'gov.nih.nci.cabig.caaers.Task',privilege :'ACCESS']);
      insert('role_privilege', [ version: '0',role_name : 'ae_study_data_reviewer',object_id : 'gov.nih.nci.cabig.caaers.Task',privilege :'ACCESS']);
      insert('role_privilege', [ version: '0',role_name : 'lab_impact_calendar_notifier',object_id : 'gov.nih.nci.cabig.caaers.Task',privilege :'ACCESS']);
      insert('role_privilege', [ version: '0',role_name : 'lab_data_user',object_id : 'gov.nih.nci.cabig.caaers.Task',privilege :'ACCESS']);
      insert('role_privilege', [ version: '0',role_name : 'data_reader',object_id : 'gov.nih.nci.cabig.caaers.Task',privilege :'ACCESS']);
      insert('role_privilege', [ version: '0',role_name : 'data_analyst',object_id : 'gov.nih.nci.cabig.caaers.Task',privilege :'ACCESS']);
      

    }
    void down(){
      //not required. 
    }
}