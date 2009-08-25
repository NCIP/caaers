class MigrateReportDeliveryRecipients extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
	    
		execute("update report_delivery_defs set end_point = 'caaers_study_cd' where end_point = 'SC' ");
		execute("update report_delivery_defs set end_point = 'caaers_participant_cd' where end_point = 'PC' ");
		execute("update report_delivery_defs set end_point = 'caaers_ae_cd' where end_point = 'AEC' ");
		execute("update report_delivery_defs set end_point = 'caaers_site_cd' where end_point = 'SIC' ");
		execute("update report_delivery_defs set end_point = 'caaers_central_office_sae_cd' where end_point = 'CSC' ");
		execute("update report_delivery_defs set end_point = 'caaers_data_cd' where end_point = 'CDC' ");
	}
	void down(){
		//not required
	}
}