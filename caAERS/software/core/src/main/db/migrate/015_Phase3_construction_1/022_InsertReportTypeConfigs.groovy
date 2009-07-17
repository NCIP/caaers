class InsertReportTypeConfig extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
		insert("config_properties", [ code: 'RT_AdEERS' , version: '0',name : 'AdEERS', description: 'AdEERS Expedited Report',config_type:'1'])
		insert("config_properties", [ code: 'RT_IRB' , version: '0',name : 'Local IRB', description: 'Local IRB Report',config_type:'1'])
		insert("config_properties", [ code: 'RT_FDA' , version: '0',name : 'FDA Report', description: 'FDA Report',config_type:'1'])
		insert("config_properties", [ code: 'RT_INST' , version: '0',name : 'Local Institution', description: 'Local Institution Report', config_type:'1'])		                            
	}
	void down(){
		execute("delete config_properties")
	}
}