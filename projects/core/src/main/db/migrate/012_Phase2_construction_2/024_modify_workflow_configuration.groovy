class ModifyWorkflowConfiguration extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("workflow_configuration", "domain_object", "string")
    }

    void down() {
        dropColumn("workflow_configuration", "domain_object")
    }
}