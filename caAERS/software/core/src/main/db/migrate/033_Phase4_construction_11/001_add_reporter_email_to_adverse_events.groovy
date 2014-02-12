class AddReporterEmailToAdverseEvent extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("adverse_events","reporter_email","string");
    }

    void down() {
        dropColumn("adverse_events","reporter_email");
    }
}