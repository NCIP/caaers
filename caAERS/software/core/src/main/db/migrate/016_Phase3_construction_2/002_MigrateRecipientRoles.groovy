class MigrateNotificationRecipients extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
	    
		execute("update recipients set role_name = 'caaers_study_cd' where role_name = 'SC' ");
		execute("update recipients set role_name = 'caaers_participant_cd' where role_name = 'PC' ");
		execute("update recipients set role_name = 'caaers_ae_cd' where role_name = 'AEC' ");
		execute("update recipients set role_name = 'caaers_site_cd' where role_name = 'SIC' ");
		execute("update recipients set role_name = 'caaers_central_office_sae_cd' where role_name = 'CSC' ");
		execute("update recipients set role_name = 'caaers_data_cd' where role_name = 'CDC' ");
	}
	void down(){
		//not required
	}
}