class ModifyResearchStaffStudyPersonnel extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    
    	addColumn("research_staffs","street", "string")
    	addColumn("research_staffs","city", "string")
        addColumn("research_staffs","state", "string")
        addColumn("research_staffs","zip", "integer")
        addColumn("research_staffs","code", "integer", defaultValue: 0)	
		execute("update research_staffs set code = 0");
    	dropColumn("research_staffs", "site_id");
    	dropColumn("study_personnel", "research_staffs_id");
    }

    void down() {
    	addColumn("research_staffs","site_id", "integer")
    	dropColumn("research_staffs","street")
    	dropColumn("research_staffs","city")
        dropColumn("research_staffs","state")
        dropColumn("research_staffs","zip")
        dropColumn("research_staffs","code")
    	 
    	addColumn("study_personnel", "research_staffs_id", "integer")
    	 
    }
}