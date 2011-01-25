class ModifySiteResearchStaff extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	
    	addColumn("site_research_staffs", "start_date", "timestamp")
    	addColumn("site_research_staffs", "end_date", "timestamp")
        
    }

    void down() {
    }
}