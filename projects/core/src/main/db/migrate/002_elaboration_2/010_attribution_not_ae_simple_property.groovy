class AttributionNotSimpleAeProperty extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        dropColumn("adverse_events", "attribution_code")
    }

    void down() {
        addColumn("adverse_events", "attribution_code", "integer")
    }
}