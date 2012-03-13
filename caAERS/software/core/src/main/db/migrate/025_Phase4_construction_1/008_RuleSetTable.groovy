class RuleSet extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("rule_sets") { t ->
            t.addVersionColumn()

            t.addColumn("study_id", "integer")
            t.addColumn('staged_version', 'integer', nullable:false)
            t.addColumn('deployed_version', 'integer', nullable:false)
            t.addColumn('organization_id', 'integer', nullable:true)
            t.addColumn('rule_type_name', 'string', nullable:false)
            t.addColumn('rule_level_name', 'string', nullable:true)
            t.addColumn('status', 'string', nullable:false)
            t.addColumn('rule_binduri', 'string', nullable:false)
            t.addColumn("grid_id", "string")
        }
    }

    void down() {
        dropTable("rule_sets")
    }
}
