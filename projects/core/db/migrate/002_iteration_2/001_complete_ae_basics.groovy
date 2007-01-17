class CompleteAeBasics extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("adverse_events", "expected", "boolean")
        dropColumn("adverse_events", "hospitalization")
        addColumn("adverse_events", "hospitalization_code", "integer")
        addColumn("adverse_events", "comments", "string", limit: 2048)
    }

    void down() {
        dropColumn("adverse_events", "comments")
        dropColumn("adverse_events", "hospitalization_code")
        addColumn("adverse_events", "hospitalization", "boolean")
        dropColumn("adverse_events", "expected")
    }
}