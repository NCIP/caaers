class MigrateResearchStaff extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
        execute("UPDATE research_staffs set login_id=email_address");
	}
	
	void down(){
		
	}
}