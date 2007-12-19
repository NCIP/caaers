class CreateChemoAgentTable extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        
        createTable("chemo_agents") { t ->
        	t.addColumn("name", "string")

        }
    }

    void down() {
        dropTable("chemo_agents")
    }
}
