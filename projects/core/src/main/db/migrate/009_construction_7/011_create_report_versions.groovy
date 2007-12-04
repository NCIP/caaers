class CreateStudyTherapy extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('report_versions') { t ->
            t.addColumn('created_on', 'date', nullable:true)
            t.addColumn('due_on', 'date', nullable:true)
            t.addColumn('submitted_on', 'date', nullable:true)
            t.addColumn('withdrawn_on', 'date', nullable:true)    
            t.addColumn('status_code','integer', nullable:true)
            t.addColumn('physician_signoff','boolean', nullable:true)
            t.addColumn('email','string', nullable:true)	            	
            t.addColumn('report_id','integer', nullable:true)
            t.addColumn('list_index','integer', nullable:true)
            t.addVersionColumn()
            t.addColumn('grid_id' , 'string' , nullable:true)
        }
                
        // USE this for data migration 
        if (databaseMatches('postgres')) {
            execute("INSERT INTO report_versions SELECT nextval('report_versions_id_seq'), due_on,created_on, submitted_on,null,status_code,physician_signoff,email,id,0,0,id FROM report_schedules")
        }
        
        if (databaseMatches('oracle')) {
            execute("INSERT INTO report_versions SELECT seq_report_versions_id.NEXTVAL,due_on, created_on, submitted_on,null,status_code,physician_signoff,email,id,0,0,id FROM report_schedules")
        }
                
        //  drop constraint with report_schedules table , 
        // rename column , add new constraint
        execute("ALTER TABLE ae_report_people DROP CONSTRAINT fk_people_aers");
        renameColumn('ae_report_people', 'report_schedule_id', 'report_version_id')
        execute("ALTER TABLE ae_report_people ADD CONSTRAINT fk_people_aers FOREIGN KEY (report_version_id) REFERENCES report_versions");        
    }
    
    void down() {        
        execute("ALTER TABLE ae_report_people DROP CONSTRAINT fk_people_aers");
    	renameColumn('ae_report_people', 'report_version_id', 'report_schedule_id')
    	execute("ALTER TABLE ae_report_people ADD CONSTRAINT fk_people_aers FOREIGN KEY (report_schedule_id) REFERENCES report_schedules");        
        dropTable('report_versions')
    }
}
