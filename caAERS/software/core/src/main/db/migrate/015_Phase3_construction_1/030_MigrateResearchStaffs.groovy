class MigrateResearchStaffs extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
	    
		if (databaseMatches('postgresql')){
	        external("migrate_research_staffs_postgres.sql")
	    }else if (databaseMatches('oracle')){
	        external("migrate_research_staffs_oracle.sql")
	    }
	}
	void down(){
		//not required
	}
}