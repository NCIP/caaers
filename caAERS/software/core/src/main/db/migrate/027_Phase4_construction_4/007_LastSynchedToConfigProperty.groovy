class AddLastSynchedToConfigProperty extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("config_properties","last_synched_date","timestamp");
    }

    void down() {
        dropColumn("config_properties","last_synched_date");
    }
}