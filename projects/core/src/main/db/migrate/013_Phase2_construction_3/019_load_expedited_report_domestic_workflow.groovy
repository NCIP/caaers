class LoadExpeditedDomesticWorkflowDef extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
	 if (databaseMatches('postgresql')){
         external('wf_def_expedited_report_domestic_postgres.sql')
     }else if(databaseMatches('oracle')){
     	external('wf_def_expedited_report_domestic_oracle.sql')
     }
	 	
	}
	void down(){
		//not required
	}
}