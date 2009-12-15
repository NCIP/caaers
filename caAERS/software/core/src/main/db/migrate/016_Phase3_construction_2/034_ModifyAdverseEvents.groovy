class ModifyAdverseEvents extends edu.northwestern.bioinformatics.bering.Migration {
	
    void up() {
    	if (databaseMatches('postgresql')){
            execute('ALTER TABLE adverse_events ALTER post_submission_updated_date TYPE timestamp')
    	}
       
    }

    void down() {
        //not needed
    }
}