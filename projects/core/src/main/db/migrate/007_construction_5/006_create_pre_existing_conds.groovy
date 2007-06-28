class CreateRecipients extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
 
         createTable("ae_pre_existing_conds") { t ->
            t.addVersionColumn()
            t.addColumn("grid_id", "string")
            t.addColumn("report_id", "integer", nullable: false)
            t.addColumn("pre_existing_condition_id", "integer")
            t.addColumn("other", "string")
            t.addColumn("list_index", "integer", nullable: false, defaultValue: 0)
        }
        execute("ALTER TABLE ae_pre_existing_conds ADD CONSTRAINT fk_pre_conds_report FOREIGN KEY (report_id) REFERENCES ae_reports")
        execute("ALTER TABLE ae_pre_existing_conds ADD CONSTRAINT fk_pre_conds_conds FOREIGN KEY (pre_existing_condition_id) REFERENCES pre_existing_conditions")
        
    }

    void down() {
        dropTable('ae_pre_existing_conds')
    }
}