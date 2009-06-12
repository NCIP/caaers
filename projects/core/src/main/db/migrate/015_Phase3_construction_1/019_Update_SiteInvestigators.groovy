class UpdateSiteInvestigators extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
	 if (databaseMatches('postgresql')){
         external("update_site_investigators_startdate_postgres.sql")
     }else if (databaseMatches('oracle')){
         external("update_site_investigators_startdate_oracle.sql")
     }
	}
	void down(){
		//not required
	}
}