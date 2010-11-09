class SolicitedAEVerbatim extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("solicited_events", "verbatim", "string")
    }

    void down() {
        dropColumn("solicited_events", "verbatim")  
    }
}