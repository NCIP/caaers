class AddFailedToIntegrationLogDetails extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
       if (databaseMatches('postgresql')){
	    	execute("update integration_log_details set failed = 'false' where failed is null");
		    execute("alter table integration_log_details alter column failed set not null");
		    execute("alter table integration_log_details alter column failed set default false");
	    }
	    
	    if (databaseMatches('oracle')) {
	    	execute("update integration_log_details set failed =  0 where failed is null");
		    execute("alter table integration_log_details modify failed not null");
		    execute("alter table integration_log_details modify failed default 0");
	    }
    }

    void down() {
    	dropColumn("integration_log_details","failed");
    }
}