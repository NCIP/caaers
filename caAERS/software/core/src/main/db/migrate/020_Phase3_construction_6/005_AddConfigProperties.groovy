class AddConfigProperties extends edu.northwestern.bioinformatics.bering.Migration {
	
	void up() {
	
		execute("delete from config_properties where config_type = 2");
	
		insert("config_properties", [ code:'system_administrator',	
										  version: '0', 
										  name :'System Administrator',
										  description:' ',
										  config_type:'2']);
		
		insert("config_properties", [ code:'business_administrator',	
										  version: '0',
										  name :	 'Business Administrator',	
										  description: ' ',	
										  config_type:'2']);
		
		insert("config_properties", [ code:'person_and_organization_information_manager',	
										  version: '0', 
										  name :	 'Person & Organization Information Manager',	
										  description: ' ',	
										  config_type:'2']);
		
		insert("config_properties", [ code:'data_importer',	
										  version: '0', 
										  name :	 'Data Importer',	
										  description: ' ',	
										  config_type:'2']);
									  
		insert("config_properties", [ code:'user_administrator',	
										  version: '0', 
										  name :	 'User Administrator',	
										  description: ' ',	
										  config_type:'2']);
									  
		insert("config_properties", [ code:'study_qa_manager',	
										  version: '0', 
										  name :	 'Study QA Manager',	
										  description: ' ',	
										  config_type:'2']);
									  
		insert("config_properties", [ code:'study_creator',	
										version: '0', 
										name :	 'Study Creator',	
										description: ' ',	
										config_type:'2']);
										
		insert("config_properties", [ code:'supplemental_study_information_manager',	
										version: '0',
										name :	 'Supplemental Study Information Manager',	
										description: ' ',	
										config_type:'2']);
										
		insert("config_properties", [ code:'study_team_administrator',	
										version: '0', 
										name :	 'Study Team Administrator',	
										description: ' ',	
										config_type:'2']);
		
		insert("config_properties", [ code:'study_site_participation_administrator',	
										version: '0', 
										name :	 'Study Site Participant Administrator',	
										description: ' ',	
										config_type:'2']);
		
		insert("config_properties", [ code:'ae_rule_and_report_manager',	
										version: '0', 
										name :	 'AE Rule & Report Manager',	
										description: ' ',	
										config_type:'2']);
		
		insert("config_properties", [ code:'study_calendar_template_builder',	
										version: '0', 
										name :	 'Study Calendar Template Builder',	
										description: ' ',	
										config_type:'2']);
		
		insert("config_properties", [ code:'registration_qa_manager',	
										version: '0', 
										name :	 'Registration QA Manager',	
										description: ' ',	
										config_type:'2']);
		
		insert("config_properties", [ code:'subject_manager',	
										version: '0', 
										name :	 'Subject Manager',	
										description: ' ',	
										config_type:'2']);
		
		insert("config_properties", [ code:'study_subject_calendar_manager',	
										version: '0', 
										name :	 'Study Subject Calendar Manager',	
										description: ' ',	
										config_type:'2']);
		
		insert("config_properties", [ code:'registrar',	
										version: '0', 
										name :	 'Registrar',	
										description: ' ',	
										config_type:'2']);
		
		insert("config_properties", [ code:'ae_reporter',	
										version: '0', 
										name :	 'AE Reporter',	
										description: ' ',	
										config_type:'2']);
		
		insert("config_properties", [ code:'ae_expedited_report_reviewer',	
										version: '0', 
										name :	 'AE Expedited Report Reviewer',	
										description: ' ',	
										config_type:'2']);
		
		insert("config_properties", [ code:'ae_study_data_reviewer',	
										version: '0', 
										name :	 'AE Study Data reviewer',	
										description: ' ',	
										config_type:'2']);
		
		insert("config_properties", [ code:'lab_impact_calendar_notifier',	
										version: '0', 
										name :	 'Lab Impact Calendar Notifier',	
										description: ' ',	
										config_type:'2']);
		
		insert("config_properties", [ code:'lab_data_user',	
										version: '0', 
										name :	 'Lab Data User',	
										description: ' ',	
										config_type:'2']);
		
		insert("config_properties", [ code:'data_reader',	
										version: '0', 
										name :	 'Data Reader',	
										description: ' ',	
										config_type:'2']);
		
		insert("config_properties", [ code:'data_analyst',	
										version: '0', 
										name :	 'Data Analyst',	
										description: ' ',	
										config_type:'2']);
	}
	
	void down() {
    }
	
}