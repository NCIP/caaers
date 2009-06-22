class AddAttributionSummary extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("ae_reports", "attribution_summary", "integer")
    }

    void down() {
        dropColumn("ae_reports", "attribution_summary")
    }
}