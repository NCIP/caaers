class CreatePriorTherapies extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("prior_therapies") { t ->
            t.addVersionColumn()
            t.addColumn("therapy_text", "string")
            t.addColumn("meddra_code", "string")
            t.addColumn("meddra_term", "string")
        }
    }

    void down() {
        dropTable("prior_therapies")
    }
}