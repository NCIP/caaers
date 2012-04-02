class CreateObservedAEProfiles extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("observed_ae_profiles") { t ->
			t.addColumn("grid_id", "string", nullable:true)
            t.addVersionColumn()
            t.addColumn("expected_frequency", "float", nullable:false)
            t.addColumn("observed_frequency", "float", nullable:true)
            t.addColumn("total_no_ofae", "integer", nullable:true)
            t.addColumn("observed_no_ofae", "integer", nullable:true)
            t.addColumn("standard_deviation", "float", nullable:true)
            t.addColumn("p_value", "float", nullable:true)
            t.addColumn("observed_significance", "float", nullable:true)
            t.addColumn("notification_status", "string", nullable:true)
			t.addColumn("grade", "string", nullable:true)
			t.addColumn("treatment_assignment_id", "integer", nullable:false)
			t.addColumn("term_id", "integer", nullable:true)
			t.addColumn("meddra_llt_id", "integer", nullable:true)
        }
		
		execute('ALTER TABLE observed_ae_profiles ADD CONSTRAINT fk_oep_ta_id FOREIGN KEY (treatment_assignment_id) REFERENCES treatment_assignment (id)')
		execute('ALTER TABLE observed_ae_profiles ADD CONSTRAINT fk_oep_trm_id FOREIGN KEY (term_id) REFERENCES ctc_terms (id)')
		execute('ALTER TABLE observed_ae_profiles ADD CONSTRAINT fk_oep_mddra_id FOREIGN KEY (meddra_llt_id) REFERENCES meddra_llt (id)')
    }
    
    void down() {
        dropTable("observed_ae_profiles")
    }
}