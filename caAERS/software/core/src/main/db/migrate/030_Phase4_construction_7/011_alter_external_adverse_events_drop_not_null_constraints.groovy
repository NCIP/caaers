class AlterExternalAdverseEventsDropNotNullConstraints extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	if(databaseMatches('postgresql')){
    		execute("alter table ext_adverse_events alter column  adverse_event_term drop not null");
    		execute("alter table ext_adverse_events alter column attribution drop not null");
    		execute("alter table ext_adverse_events alter column start_date drop not null");
    		execute("alter table ext_adverse_events alter column verbatim drop not null");
    		execute("alter table ext_adverse_events alter column grade drop not null");
    		execute("alter table ext_adverse_events alter column external_id drop not null");
    	}
    	
    	if(databaseMatches('oracle')){
    	 	execute("alter table ext_adverse_events modify  adverse_event_term null");
    		execute("alter table ext_adverse_events modify attribution null");
    		execute("alter table ext_adverse_events modify start_date null");
    		execute("alter table ext_adverse_events modify verbatim null");
    		execute("alter table ext_adverse_events modify grade null");
    		execute("alter table ext_adverse_events modify external_id null");
    	}
    }

    void down() {
    }
}