class CreateSolicitedAEs extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("solicited_events") { t ->
            t.addVersionColumn()
            t.addColumn("arm_id", "integer", nullable:false)
            t.addColumn("ctc_term_id", "integer", nullable:true)
            t.addColumn("lowlevel_term_id", "integer", nullable:true)
            t.addColumn("grid_id" , "string" , nullable:true)
            
        }
        execute("ALTER TABLE solicited_events ADD CONSTRAINT fk_solicited_event_arm_id FOREIGN KEY (arm_id) REFERENCES arms");
        execute("ALTER TABLE solicited_events ADD CONSTRAINT fk_solicited_ctc_term_id FOREIGN KEY (ctc_term_id) REFERENCES ctc_terms");
        execute("ALTER TABLE solicited_events ADD CONSTRAINT fk_solicited_lowlevel_term_id FOREIGN KEY (lowlevel_term_id) REFERENCES meddra_llt");
        execute("ALTER TABLE solicited_events ADD CONSTRAINT ck_solicited_term_id_not_null CHECK( ctc_term_id is not null or lowlevel_term_id is not null )");
        
    }
    
    void down() {
        dropTable("solicited_events")
    }
}