class MigrateSiteResearchStaffDates extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	
    	execute("update site_research_staffs set start_date=(select min(start_date) from site_rs_staff_roles where site_research_staffs_id = site_research_staffs.id)")
		execute("update site_research_staffs set end_date=(select max(end_date) from site_rs_staff_roles where site_research_staffs_id = site_research_staffs.id)")
        
    }

    void down() {
    }
}