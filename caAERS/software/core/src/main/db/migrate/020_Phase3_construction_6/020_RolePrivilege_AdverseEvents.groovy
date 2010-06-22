class RolePrivilegeSeedDataOrganization extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {

      insert('role_privilege', [ version: '0',role_name : 'ae_reporter',object_id : 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'data_reader',object_id : 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'ae_reporter',object_id : 'gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod',privilege :'CREATE']);
      insert('role_privilege', [ version: '0',role_name : 'ae_reporter',object_id : 'gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'ae_reporter',object_id : 'gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'ae_reporter',object_id : 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',privilege :'CREATE']);
      insert('role_privilege', [ version: '0',role_name : 'ae_reporter',object_id : 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'ae_study_data_reviewer',object_id : 'gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'ae_study_data_reviewer',object_id : 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'ae_expedited_report_reviewer',object_id : 'gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'ae_expedited_report_reviewer',object_id : 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'ae_reporter',object_id : 'gov.nih.nci.cabig.caaers.domain.workflow.ReportReviewComment',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'ae_expedited_report_reviewer',object_id : 'gov.nih.nci.cabig.caaers.domain.workflow.ReportReviewComment',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'ae_study_data_reviewer',object_id : 'gov.nih.nci.cabig.caaers.domain.workflow.ReportingPeriodReviewComment',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'ae_reporter',object_id : 'gov.nih.nci.cabig.caaers.domain.workflow.ReportingPeriodReviewComment',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'ae_expedited_report_reviewer',object_id : 'gov.nih.nci.cabig.caaers.domain.workflow.ReportReviewComment',privilege :'CREATE']);
      insert('role_privilege', [ version: '0',role_name : 'ae_reporter',object_id : 'gov.nih.nci.cabig.caaers.domain.workflow.ReportReviewComment',privilege :'CREATE']);
      insert('role_privilege', [ version: '0',role_name : 'ae_study_data_reviewer',object_id : 'gov.nih.nci.cabig.caaers.domain.workflow.ReportingPeriodReviewComment',privilege :'CREATE']);
      insert('role_privilege', [ version: '0',role_name : 'ae_reporter',object_id : 'gov.nih.nci.cabig.caaers.domain.workflow.ReportingPeriodReviewComment',privilege :'CREATE']);
      insert('role_privilege', [ version: '0',role_name : 'ae_expedited_report_reviewer',object_id : 'gov.nih.nci.cabig.caaers.domain.workflow.ReportReviewComment',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'ae_reporter',object_id : 'gov.nih.nci.cabig.caaers.domain.workflow.ReportReviewComment',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'ae_study_data_reviewer',object_id : 'gov.nih.nci.cabig.caaers.domain.workflow.ReportingPeriodReviewComment',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'ae_reporter',object_id : 'gov.nih.nci.cabig.caaers.domain.workflow.ReportingPeriodReviewComment',privilege :'UPDATE']);

    }
    void down(){
      //not required. 
    }
}