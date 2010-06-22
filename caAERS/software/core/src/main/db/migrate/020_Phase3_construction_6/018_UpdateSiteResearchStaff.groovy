class UpdateSiteResearchStaff extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {
		if (databaseMatches('oracle')) {
            execute(" update site_research_staffs set associate_all_studies = 0 ");
        } else if (databaseMatches('postgresql')){
            execute(" update site_research_staffs set associate_all_studies = false ");
        }      
    }
    void down(){
      //not required. 
    }
}