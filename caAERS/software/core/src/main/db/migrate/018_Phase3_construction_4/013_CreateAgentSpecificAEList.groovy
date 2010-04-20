class CreateAgentSpecificAEList extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("agent_terms") { t ->
            t.addVersionColumn()

            t.addColumn("agent_id", "integer")
        	t.addColumn('term_id', 'integer', nullable:true)
        	t.addColumn('term_type', 'string', nullable:true)
        	t.addColumn('meddra_code', 'string', nullable:true)
        	t.addColumn('low_level_term_id', 'integer', nullable:true)

            t.addColumn("grid_id", "string")
        }
    }

    void down() {
        dropTable("agent_terms")
    }
}