class CreateNotificationAttachments extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('attachments') { t ->
        	//t.addColumn('content', 'oid', nullable:true)
        	t.addColumn('plnf_id', 'integer', nullable:true)
	    	t.addVersionColumn()
	    	t.addColumn('grid_id','string', nullable:true)
        }
    	
    	    
        //now add the constraints
         execute('alter table attachments add constraint fk_athmts_plnfs_id foreign key (plnf_id) references planned_notifications (id) on delete cascade')
    	
         if (databaseMatches('postgres')) {
        	 execute('alter table attachments  add column content oid')
	 	}
	 if(databaseMatches('oracle')){
	 		   execute('alter table attachments  add (content blob)')
	 }
	 if(databaseMatches('hsql')){
	         	   execute('alter table attachments  add column content longvarbinary');
         }
    }

    void down() {
        dropTable('attachments')
    }
}