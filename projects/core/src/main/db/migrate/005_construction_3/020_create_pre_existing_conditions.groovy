class CreatePreExistingConditions extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("pre_existing_conditions") { t ->
            t.addVersionColumn()
            t.addColumn("condition_text", "string")
            t.addColumn("meddra_llt_code", "string")
            t.addColumn("meddra_llt", "string")
            t.addColumn("meddra_hlgt", "string")
        }
    }

    void down() {
        dropTable("pre_existing_conditions")
    }
}