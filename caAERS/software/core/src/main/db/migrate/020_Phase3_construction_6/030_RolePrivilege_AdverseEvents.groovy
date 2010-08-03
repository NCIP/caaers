class RolePrivilegeSeedDataOrganization2 extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {
	  insert('role_privilege', [ version: '0',role_name : 'ae_study_data_reviewer',object_id : 'gov.nih.nci.cabig.caaers.domain.workflow.ReportReviewComment',privilege :'READ']);
	  
    }
    void down(){
      //not required. 
    }
}