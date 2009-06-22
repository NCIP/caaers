class ModifyStudyOrganizationsUpdate2 extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	dropColumn("study_organizations", "status_date")
    	dropColumn("study_organizations", "status_code")
      	
    }

    void down() {
    }
}