class UpdateAuditEvents extends edu.northwestern.bioinformatics.bering.Migration {
	
    void up() {
    	if (databaseMatches('oracle')) {
            execute('alter table audit_events add (object_id_temp integer)')
            execute('update audit_events set object_id_temp=object_id')
            execute('alter table audit_events drop column object_id')
            execute('alter table audit_events rename column object_id_temp to object_id')
            execute('alter table audit_events modify (object_id not null)')
        } else if (databaseMatches('postgresql')){
			execute('alter table audit_events alter column object_id type integer using object_id::integer')
        }
    }

    void down() {
    }
}