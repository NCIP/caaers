class ModifyTaskConfiguration extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("task_configuration", "message", "string")
        addColumn("task_configuration", "task_name", "string")
    }

    void down() {
        dropColumn("task_configuration", "message")
        dropColumn("task_configuration", "task_name")
    }
}