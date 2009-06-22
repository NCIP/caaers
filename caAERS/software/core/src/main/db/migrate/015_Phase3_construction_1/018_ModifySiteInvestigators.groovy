class ModifySiteInvestigators extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	dropColumn("site_investigators", "status_code");
    	dropColumn("site_investigators", "status_date");
      	addColumn("site_investigators","start_date", "timestamp");
      	addColumn("site_investigators","end_date", "timestamp");
    }

    void down() {
    	 addColumn("study_investigators","status_code", "string");
    	 addColumn("study_investigators","status_date", "timestamp");
    	 dropColumn("site_investigators", "start_date");
    	 dropColumn("site_investigators", "end_date");
    }
}