class LoadCoordinatingCenterWorkflowDef extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
	 if (databaseMatches('postgresql')){
         external("wf_def_reporting_period_basic.sql")
     }
	 	
	}
	void down(){
		
	}
}