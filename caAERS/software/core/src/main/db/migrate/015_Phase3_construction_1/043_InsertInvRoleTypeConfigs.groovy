class InsertInvestigatorRoleTypeConfig extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
		insert("config_properties", [ code: 'PI' , 		version: '0',	name : 'Principal Investigator', 		description: 'caAERS Principal Investigator',								config_type:'3'])
		insert("config_properties", [ code: 'SI' , 		version: '0',	name : 'Site Investigator', 			description: 'caAERS Site Investigator',													config_type:'3'])
		insert("config_properties", [ code: 'SPI' , 	version: '0',	name : 'Site Principal Investigator', 	description: 'caAERS Site Principal Investigator',			config_type:'3'])
	}
	void down(){
		execute("delete config_properties where config_type=3")
	}
}