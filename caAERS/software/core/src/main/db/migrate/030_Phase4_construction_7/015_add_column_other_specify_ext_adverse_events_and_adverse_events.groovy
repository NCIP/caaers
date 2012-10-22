class AddColumnOtherSpecifyExtAdverseEventsAndAdverseEvents extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("ext_adverse_events","other_specify","string");
        addColumn("adverse_events","other_specify","string");
    }

    void down() {
    	dropColumn("ext_adverse_events","other_specify");
        dropColumn("adverse_events","other_specify");
    }
}