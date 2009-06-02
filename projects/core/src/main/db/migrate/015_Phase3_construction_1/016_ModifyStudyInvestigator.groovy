class ModifyStudyInvestigator2 extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	dropColumn("study_investigators", "status_code");
      	addColumn("study_investigators","start_date", "timestamp");
      	addColumn("study_investigators","end_date", "timestamp");
    }

    void down() {
    	 addColumn("study_investigators","status_code", "string");
    	 dropColumn("study_investigators", "start_date");
    	 dropColumn("study_investigators", "end_date");
    }
}