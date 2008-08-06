class CreateSPAPreExistingConditions extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
 
         createTable("spa_pre_existing_conds") { t ->
            t.addVersionColumn()
            t.addColumn("grid_id", "string")
            t.addColumn("assignment_id", "integer", nullable: false)
            t.addColumn("pre_existing_condition_id", "integer")
            t.addColumn("other", "string")
            t.addColumn("list_index", "integer", nullable: false, defaultValue: 0)
        }
        execute("ALTER TABLE spa_pre_existing_conds ADD CONSTRAINT fk_pre_conds_assignment FOREIGN KEY (assignment_id) REFERENCES participant_assignments")
        execute("ALTER TABLE spa_pre_existing_conds ADD CONSTRAINT fk_pre_conds_conditions FOREIGN KEY (pre_existing_condition_id) REFERENCES pre_existing_conditions")
        
    }

    void down() {
        dropTable('spa_pre_existing_conds')
    }
}