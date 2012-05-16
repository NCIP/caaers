class AddLastSynchedDateToLabTerms extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
	    addColumn("lab_terms","last_synched_date","timestamp");
        dropColumn("labs","last_synched_date");
    }

    void down() {
    	dropColumn("lab_terms","last_synched_date");
    }
}