class AddToDoseCode extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("ext_adverse_events","grid_id","string");
        addColumn("ext_adverse_events","adverse_event_term_code","string");
        addColumn("ext_adverse_events","adverse_event_term_other_value","string");
        addColumn("ext_adverse_events","how_serious","string");
        addColumn("ext_ae_reporting_prds","grid_id","string");
        renameColumn("ext_adverse_events","version_id","version");
        renameColumn("ext_ae_reporting_prds","version_id","version");
    }

    void down() {
    	dropColumn("ext_adverse_events","grid_id");
        dropColumn("ext_adverse_events","adverse_event_term_code");
        dropColumn("ext_adverse_events","adverse_event_term_other_value");
        dropColumn("ext_ae_reporting_prds","grid_id");
        renameColumn("ext_adverse_events","version",version_id);
        renameColumn("ext_ae_reporting_prds","version",version_id);
    }
}