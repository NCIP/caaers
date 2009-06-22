class ModifyStudyOrganizationsUpdate extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	addColumn("study_organizations","status_date", "timestamp");
    	
     	if (databaseMatches('postgresql')){
			execute("ALTER TABLE study_organizations ALTER COLUMN status_code SET DEFAULT 'Inactive'")
		}else if(databaseMatches('oracle')){
			execute("ALTER TABLE study_organizations modify status_code default 'Inactive'")
		}
		
      	execute("update study_organizations set status_code = 'Active'");
      	
    }

    void down() {
    	dropColumn("study_organizations", "status_date")
    }
}