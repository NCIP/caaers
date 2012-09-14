class ChangeTableExternalAdverseEventReportingPeriod extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    
    	  renameColumn("ext_ae_reporting_prds","treatment_assignment_desc","other_description");
    	  addColumn("ext_adverse_events","creation_date","date",nullable: false);
    	  addColumn("ext_adverse_events","reviewed_date","date");
    	  addColumn("ext_adverse_events","status","string",nullable: false);
    	  addColumn("ext_adverse_events","ext_rep_prd_id","integer");
    	 
       	  addColumn("ext_ae_reporting_prds","creation_date","date",nullable: false);
       	  addColumn("ext_ae_reporting_prds","reporting_period_id","integer");
       	  dropColumn("ext_ae_reporting_prds","assignment_id");
       	  
       	  execute('ALTER TABLE ext_adverse_events ADD CONSTRAINT fk_ext_adv_evts_ext_ae_rep_prds FOREIGN KEY (ext_rep_prd_id) REFERENCES ext_ae_reporting_prds(id)')
       	  execute('ALTER TABLE ext_ae_reporting_prds ADD CONSTRAINT fk_ext_rep_per_ae_rep_prds FOREIGN KEY (reporting_period_id) REFERENCES ae_reporting_periods(id)')
    }

    void down() {
    }
}