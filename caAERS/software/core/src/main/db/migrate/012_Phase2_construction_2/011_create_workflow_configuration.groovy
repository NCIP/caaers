class CreateWorkflowConfiguration extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("workflow_configuration") { t ->
        	t.addColumn("grid_id", "string")
            t.addVersionColumn()
            t.addColumn("workflow_definition_name", "string")
            t.addColumn("default_assignee", "string")
            t.addColumn("enabled", "boolean")
        }
    }

    void down() {
        dropTable("workflow_configuration")
    }
}