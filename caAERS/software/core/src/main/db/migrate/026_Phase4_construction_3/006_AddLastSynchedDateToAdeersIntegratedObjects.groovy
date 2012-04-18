class AddLastSynchedDateToAdeersIntegratedObjects extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
	    addColumn("organizations","last_synched_date","timestamp");
	    addColumn("devices","last_synched_date","timestamp");
	    addColumn("agents","last_synched_date","timestamp");
	    addColumn("agent_terms","last_synched_date","timestamp");
        addColumn("labs","last_synched_date","timestamp");
        addColumn("prior_therapies","last_synched_date","timestamp");
        addColumn("pre_existing_conditions","last_synched_date","timestamp");
        addColumn("studies","last_synched_date","timestamp");
    }

    void down() {
    	dropColumn("studies","last_synched_date");
    	dropColumn("pre_existing_conditions","last_synched_date");
    	dropColumn("prior_therapies","last_synched_date");
    	dropColumn("labs","last_synched_date");
    	dropColumn("agent_terms","last_synched_date");
    	dropColumn("agents","last_synched_date");
    	dropColumn("devices","last_synched_date");
    	dropColumn("organizations","last_synched_date");
    }
}