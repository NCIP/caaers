class InsertReportRoles extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
		insert("config_properties", [ code: 'REP' , 		version: '0',	name : 'Reporter', 		description: 'Expedited Adverse Event reporter',														config_type:'4'])
		insert("config_properties", [ code: 'PHY' , 		version: '0',	name : 'Physician', 	description: 'Expedited Adverse Event physician',														config_type:'4'])
		insert("config_properties", [ code: 'SUB' , 		version: '0',	name : 'Submitter', 	description: 'Expedited Adverse Event submitter',														config_type:'4'])
		
	}
	void down(){
		execute("delete config_properties where config_type=4")
	}
}