class CreateInterventionSitesTable extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("intervention_sites") { t ->
        	t.addColumn("name", "string")
        }
    }

    void down() {
        dropTable("intervention_sites")
    }
}
