class AlterTableExternalAEReportingPeriodsDropNotNullConstraints extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	if(databaseMatches('postgresql')){
    		execute("alter table ext_ae_reporting_prds alter column other_description drop not null");
    		execute("alter table ext_ae_reporting_prds alter column treatment_assignment_code drop not null");
    		execute("alter table ext_ae_reporting_prds alter column name drop not null");
    	}
    	
    	if(databaseMatches('oracle')){
    	 	execute("alter table ext_ae_reporting_prds modify other_description null");
    		execute("alter table ext_ae_reporting_prds modify treatment_assignment_code null");
    		execute("alter table ext_ae_reporting_prds modify name null");
    	}
    }

    void down() {
    }
}