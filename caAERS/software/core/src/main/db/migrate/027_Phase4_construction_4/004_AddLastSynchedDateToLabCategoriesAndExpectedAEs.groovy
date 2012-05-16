class AddLastSynchedDateToLabCategoriesAndExpectedAEs extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
	    addColumn("lab_categories","last_synched_date","timestamp");
	    addColumn("expected_aes","last_synched_date","timestamp");
    }

    void down() {
    	dropColumn("expected_aes","last_synched_date");
    	dropColumn("lab_categories","last_synched_date");
    }
}