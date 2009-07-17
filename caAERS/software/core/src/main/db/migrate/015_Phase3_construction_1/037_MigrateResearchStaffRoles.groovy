class MigrateResearchStaffs extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
	    
		if (databaseMatches('postgresql')){
	        external("migrate_research_staff_roles_postgres.sql")
	    }else if (databaseMatches('oracle')){
	        external("migrate_research_staff_roles_oracle.sql")
	    }
	}
	void down(){
		//not required
	}
}