class ModifySiteResearchStaff extends edu.northwestern.bioinformatics.bering.Migration {
        void up() {
      	if (databaseMatches('oracle')) {
        	addColumn("site_research_staffs","associate_all_studies", "boolean",defaultValue: 0)
        }else{
         	addColumn("site_research_staffs","associate_all_studies", "boolean",defaultValue: false)
        }
    }
    void down() {
    	 dropColumn("site_research_staffs", "associate_all_studies");
    }
}