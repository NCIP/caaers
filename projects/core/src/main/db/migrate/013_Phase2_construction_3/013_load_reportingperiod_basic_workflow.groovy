class LoadCoordinatingCenterWorkflowDef extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
	 if (databaseMatches('postgresql')){
         external('wf_def_reporting_period_basic_postgres.sql')
     }else if(databaseMatches('oracle')){
     	external('wf_def_reporting_period_basic_oracle.sql')
     }
	 	
	}
	void down(){
		//not required
	}
}