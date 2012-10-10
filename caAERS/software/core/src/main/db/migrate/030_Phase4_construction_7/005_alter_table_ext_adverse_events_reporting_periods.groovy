class AlterExternalAdverseEventReportingPeriod extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	if(databaseMatches('postgresql')){
    		execute("alter table ext_ae_reporting_prds alter column treatment_assignment_desc type text");
    		execute("alter table ext_adverse_events alter column end_date drop not null");
    		execute("alter table ext_ae_reporting_prds alter column external_id drop not null");
    	}
    	
    	if(databaseMatches('oracle')){
    		execute("alter table ext_ae_reporting_prds modify treatment_assignment_desc varchar2(4000)");
    		execute("alter table ext_adverse_events modify end_date null");
    		execute("alter table ext_ae_reporting_prds modify (external_id null)");
    	}
    }

    void down() {
    }
}