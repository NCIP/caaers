class CreateConditions extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("conditions") { t ->
            t.addVersionColumn()
            t.addColumn("condition_name", "string")
            t.addColumn("grid_id", "string")
        }
    }

    void down() {
        dropTable("conditions")
    }
}