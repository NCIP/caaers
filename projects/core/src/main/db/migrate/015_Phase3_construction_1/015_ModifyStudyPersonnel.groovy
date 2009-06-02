class ModifyStudyPersonnel2 extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	dropColumn("study_personnel", "status_code");
      	addColumn("study_personnel","start_date", "timestamp");
      	addColumn("study_personnel","end_date", "timestamp");
    }

    void down() {
    	 addColumn("study_personnel","status_code", "string");
    	 dropColumn("study_personnel", "start_date");
    	 dropColumn("study_personnel", "end_date");
    }
}