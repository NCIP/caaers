class RolePrivilegeImport extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {
      insert('role_privilege', [ version: '0',role_name : 'data_reader',object_id : 'gov.nih.nci.cabig.caaers.domain.Participant',privilege :'READ']);

      insert('role_privilege', [ version: '0',role_name : 'subject_manager',object_id : 'gov.nih.nci.cabig.caaers.domain.Participant',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'subject_manager',object_id : 'gov.nih.nci.cabig.caaers.domain.Participant',privilege :'CREATE']);

      insert('role_privilege', [ version: '0',role_name : 'registrar',object_id : 'gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment',privilege :'CREATE']);
      insert('role_privilege', [ version: '0',role_name : 'registrar',object_id : 'gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'registrar',object_id : 'gov.nih.nci.cabig.caaers.domain.Participant',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'registrar',object_id : 'gov.nih.nci.cabig.caaers.domain.Participant',privilege :'READ']);

      insert('role_privilege', [ version: '0',role_name : 'registration_qa_manager',object_id : 'gov.nih.nci.cabig.caaers.domain.Participant',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'registration_qa_manager',object_id : 'gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment',privilege :'READ']);
      insert('role_privilege', [ version: '0',role_name : 'registration_qa_manager',object_id : 'gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment',privilege :'UPDATE']);
      insert('role_privilege', [ version: '0',role_name : 'registration_qa_manager',object_id : 'gov.nih.nci.cabig.caaers.domain.Participant',privilege :'READ']);
    }

    void down(){
    }
}