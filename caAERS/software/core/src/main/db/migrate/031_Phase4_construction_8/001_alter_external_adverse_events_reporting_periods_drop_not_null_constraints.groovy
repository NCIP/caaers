class AlterExternalAdverseEventReportingPeriodsDropNotNullConstraints extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	if(databaseMatches('postgresql')){
    		execute("alter table ext_ae_reporting_prds alter column start_date drop not null");
    	}
    	
    	if(databaseMatches('oracle')){
    	 	execute("alter table ext_ae_reporting_prds modify start_date null");
    	}
    }

    void down() {
    }
}