class CreatePlannedNotifications extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	        createTable('planned_notifications') { t ->
	            t.addColumn('index_on_time_scale', 'integer', nullable:false)
	            t.addColumn('from_addr','string', nullable:false)
	           // t.addColumn('body','oid', nullable:true)
	            t.addColumn('subject','string', nullable:false)
	            t.addColumn('dtype','string', nullable:false)
	            t.addColumn('rct_id','integer', nullable:true) //report_calendar_templates(id)
	            t.addVersionColumn()
	            t.addColumn('grid_id' , 'string' , nullable:true);
	            
        	}
 	//now add the constraints
 	execute('alter table planned_notifications add constraint fk_plnfs_rct_id foreign key (rct_id) references report_calendar_templates (id)')
	 	if (databaseMatches('postgres')) {
	 		 execute('alter table planned_notifications add column body oid')
	   
	 	} 
	 	if(databaseMatches('oracle')){
	 		execute('alter table planned_notifications add  (body blob)')
	 	}
        	if(databaseMatches('hsql')){
        	   execute('alter table planned_notifications add column body longvarbinary');
        	}
    }

    void down() {
        dropTable('planned_notifications')
    }
}