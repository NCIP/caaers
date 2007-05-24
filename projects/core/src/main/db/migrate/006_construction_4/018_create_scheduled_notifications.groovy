class CreateScheduledNotifications extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('scheduled_notifications') { t ->
            t.addColumn('scheduled_on', 'date', nullable:false)
            t.addColumn('created_on','date', nullable:false)
            t.addColumn('to_addr','string', nullable:true)
            t.addColumn('from_addr','string', nullable:true)
           // t.addColumn('body','oid', nullable:true)
            t.addColumn('delivery_status_code','integer', nullable:false)
            t.addVersionColumn()
            t.addColumn('dtype','string', nullable:false)
            t.addColumn('plnf_id','integer', nullable:true) //planned_notifications(id)
            t.addColumn('rpsh_id','integer', nullable:true) //report_schedules(id)
            t.addColumn('grid_id' , 'string' , nullable:true);
            
        }
	    execute('alter table scheduled_notifications add constraint fk_shnfs_rpsh_id foreign key (rpsh_id) references report_schedules (id) on delete cascade')
	    execute('alter table scheduled_notifications add constraint fk_shnfs_plnf_id foreign key (plnf_id) references planned_notifications (id) on delete cascade')

    //now add the constraints
	if (databaseMatches('postgres')) {
		execute('alter table scheduled_notifications add column body oid')
	} 
	if (databaseMatches('oracle')) {
	   execute('alter table scheduled_notifications add (body blob)')
	}  
        if(databaseMatches('hsql')){
	    execute('alter table scheduled_notifications add column body longvarbinary');
         }    
        
    }

    void down() {
        dropTable('scheduled_notifications')
    }
}