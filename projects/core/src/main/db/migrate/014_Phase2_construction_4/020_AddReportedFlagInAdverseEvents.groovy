class AddReportedFlagToAdverseEvents extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("adverse_events","reported", "boolean");
    }

    void down() {
        dropColumn("adverse_events","reported");
    }
}