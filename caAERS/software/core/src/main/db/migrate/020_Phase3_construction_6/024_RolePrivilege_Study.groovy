class RolePrivilegeStudies extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {

      insert('role_privilege', [ version: '0',role_name : 'study_creator',object_id : 'gov.nih.nci.cabig.caaers.domain.Study',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'study_qa_manager',object_id : 'gov.nih.nci.cabig.caaers.domain.Study',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'supplemental_study_information_manager',object_id : 'gov.nih.nci.cabig.caaers.domain.Study',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'study_team_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.Study',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'study_site_participation_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.Study',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'data_reader',object_id : 'gov.nih.nci.cabig.caaers.domain.Study',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'study_creator',object_id : 'gov.nih.nci.cabig.caaers.domain.Study',privilege :'CREATE']);
      insert('role_privilege', [ version: '0',role_name : 'study_creator',object_id : 'gov.nih.nci.cabig.caaers.domain.Study',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'study_qa_manager',object_id : 'gov.nih.nci.cabig.caaers.domain.Study',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'supplemental_study_information_manager',object_id : 'gov.nih.nci.cabig.caaers.domain.Study',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'study_team_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.Study',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'study_site_participation_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.Study',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'study_site_participation_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.StudySite',privilege :'CREATE']);
      insert('role_privilege', [ version: '0',role_name : 'supplemental_study_information_manager',object_id : 'gov.nih.nci.cabig.caaers.domain.StudyTherapy',privilege :'CREATE']);
      insert('role_privilege', [ version: '0',role_name : 'supplemental_study_information_manager',object_id : 'gov.nih.nci.cabig.caaers.domain.StudyAgent',privilege :'CREATE']);
      insert('role_privilege', [ version: '0',role_name : 'supplemental_study_information_manager',object_id : 'gov.nih.nci.cabig.caaers.domain.TreatmentAssignment',privilege :'CREATE']);
      insert('role_privilege', [ version: '0',role_name : 'supplemental_study_information_manager',object_id : 'gov.nih.nci.cabig.caaers.domain.AbstractStudyDisease',privilege :'CREATE']);
      insert('role_privilege', [ version: '0',role_name : 'supplemental_study_information_manager',object_id : 'gov.nih.nci.cabig.caaers.domain.SolicitedAdverseEvent',privilege :'CREATE']);
      insert('role_privilege', [ version: '0',role_name : 'supplemental_study_information_manager',object_id : 'gov.nih.nci.cabig.caaers.domain.AbstractExpectedAE',privilege :'CREATE']);
      insert('role_privilege', [ version: '0',role_name : 'supplemental_study_information_manager',object_id : 'gov.nih.nci.cabig.caaers.domain.Identifier',privilege :'CREATE']);
      insert('role_privilege', [ version: '0',role_name : 'study_team_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.StudyInvestigator',privilege :'CREATE']);
      insert('role_privilege', [ version: '0',role_name : 'study_team_administrator',object_id : 'gov.nih.nci.cabig.caaers.domain.StudyPersonnel',privilege :'CREATE']);

    }
    void down(){
      //not required.
    }
}