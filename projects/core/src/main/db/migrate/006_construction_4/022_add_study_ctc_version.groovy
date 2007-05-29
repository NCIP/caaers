class AddStudyCtcVersion extends edu.northwestern.bioinformatics.bering.Migration {
	 void up() {
   	  	addColumn("studies", "ctc_id", "integer");
   	  	execute("UPDATE studies SET ctc_id=3 WHERE ctc_id IS NULL")  // make the default CTC v3
   	  	setNullable('studies', 'ctc_id', false)
     	execute("ALTER TABLE studies ADD CONSTRAINT fk_studies_ctc_version FOREIGN KEY (ctc_id) REFERENCES ctc_versions")
    }
    void down() {
    	removeColumn('studies','ctc_id');  
    }
}