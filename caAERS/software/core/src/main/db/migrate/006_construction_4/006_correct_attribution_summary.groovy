class AddAttributionSummary extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        dropColumn("ae_reports", "attribution_summary")
        addColumn("adverse_events", "attribution_summary_code", "integer")
    }

    void down() {
        dropColumn("adverse_events", "attribution_summary_code")
        addColumn("ae_reports", "attribution_summary", "integer")
    }
}