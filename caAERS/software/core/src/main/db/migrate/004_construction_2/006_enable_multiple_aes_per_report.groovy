class EnableMultipleAesPerReport extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("adverse_events", "list_index", "integer", nullable: false, defaultValue: 0);
    }

    void down() {
        dropColumn("adverse_events", "list_index");
    }
}