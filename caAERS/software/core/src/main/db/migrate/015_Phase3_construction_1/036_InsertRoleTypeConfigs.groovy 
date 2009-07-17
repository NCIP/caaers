class InsertResearchStaffRoleTypeConfig extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
		insert("config_properties", [ code: 'caaers_study_cd' , 		version: '0',	name : 'Study coordinator', 		description: 'caAERS Study coordinator',														config_type:'2'])
		insert("config_properties", [ code: 'caaers_participant_cd' , 	version: '0',	name : 'Subject coordinator', 		description: 'caAERS Subject coordinator',													config_type:'2'])
		insert("config_properties", [ code: 'caaers_ae_cd' , 			version: '0',	name : 'Adverse event coordinator', 		description: 'caAERS Adverse event coordinator',															config_type:'2'])
		insert("config_properties", [ code: 'caaers_site_cd' , 			version: '0',	name : 'Site coordinator', description: 'caAERS Site coordinator', config_type:'2'])
		insert("config_properties", [ code: 'caaers_central_office_sae_cd' , 		version: '0',	name : 'Central office report reviewer', description: 'caAERS Central office report reviewer', config_type:'2'])
		insert("config_properties", [ code: 'caaers_data_cd' , 			version: '0',	name : 'Data coordinator', description: 'caAERS Data coordinator', config_type:'2'])		                            
	}
	void down(){
		execute("delete config_properties")
	}
}