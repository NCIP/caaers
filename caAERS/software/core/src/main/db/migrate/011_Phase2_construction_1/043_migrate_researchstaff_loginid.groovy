class MigrateResearchStaff extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
	
	    if (databaseMatches('oracle')) {
            execute("update research_staffs set login_id=(select login_name from csm_user cu where login_id=cu.user_id)");
        } else if (databaseMatches('postgresql')){
            execute("update research_staffs set login_id=(select login_name from csm_user cu where login_id=text(cu.user_id))");
        } 
	}
	
	void down(){
		
	}
}