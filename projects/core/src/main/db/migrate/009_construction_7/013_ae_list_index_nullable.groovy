class AeListIndexNullable extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        setNullable("adverse_events", "list_index", true)
    }

    void down() {
        setNullable("adverse_events", "list_index", false)
    }
}
