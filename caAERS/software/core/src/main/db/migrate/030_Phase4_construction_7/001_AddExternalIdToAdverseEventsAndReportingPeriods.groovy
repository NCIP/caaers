class AddToDoseCode extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("adverse_events","external_id","string");
        addColumn("ae_reporting_periods","external_id","string");
    }

    void down() {
        dropColumn("adverse_events","external_id");
        dropColumn("ae_reporting_periods","external_id");
    }
}