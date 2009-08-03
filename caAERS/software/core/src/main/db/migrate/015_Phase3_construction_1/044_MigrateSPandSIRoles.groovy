class MigrateSPandSIRoles extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
	    
		execute("update study_investigators set role_code = 'PI' where role_code = 'Principal Investigator' ");
		execute("update study_investigators set role_code = 'SI' where role_code = 'Site Investigator' ");
		execute("update study_investigators set role_code = 'SPI' where role_code = 'Site Principal Investigator' ");
		
		execute("update study_personnel set role_code = 'caaers_study_cd' where role_code = 'Study Coordinator' ");
		execute("update study_personnel set role_code = 'caaers_participant_cd' where role_code = 'Participant Coordinator' ");
		execute("update study_personnel set role_code = 'caaers_ae_cd' where role_code = 'Adverse Event Coordinator' ");
		execute("update study_personnel set role_code = 'caaers_site_cd' where role_code = 'Site Coordinator' ");
		execute("update study_personnel set role_code = 'caaers_central_office_sae_cd' where role_code = 'Central Office Report Reviewer' ");
		execute("update study_personnel set role_code = 'caaers_data_cd' where role_code = 'Data Coordinator' ");
		
		if (databaseMatches('oracle')) {
        	execute("update study_personnel set start_date = '2008-01-01' ");
        	execute("update study_investigators set start_date = '2008-01-01' ");
        }else{
         	execute("update study_personnel set start_date = to_date('2008-01-01', 'yyyy/mm/dd') ");
         	execute("update study_investigators set start_date = to_date('2008-01-01', 'yyyy/mm/dd') ");
        }
	}
	void down(){
		//not required
	}
}