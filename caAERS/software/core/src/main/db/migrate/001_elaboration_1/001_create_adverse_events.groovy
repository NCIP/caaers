class CreateAdverseEvents extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("adverse_events") { t ->
            t.addVersionColumn()
            t.addColumn("time", "timestamp", nullable: false)
        }
    }
    
    void down() {
        dropTable("adverse_events")
    }
}