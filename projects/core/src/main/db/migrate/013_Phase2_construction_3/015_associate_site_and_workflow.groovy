class AssociateSiteAndWorkflow extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
	 if (databaseMatches('postgresql')){
         external("wf_associate_site_and_workflow_postgres.sql")
     }else if (databaseMatches('oracle')){
         external("wf_associate_site_and_workflow_oracle.sql")
     }
	 	
	}
	void down(){
		//not required
	}
}