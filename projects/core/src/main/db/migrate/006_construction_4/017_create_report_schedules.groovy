class CreateReportReportSchedules extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('report_schedules') { t ->
            t.addColumn('name','string', nullable:false)
            t.addColumn('due_on','date', nullable:false)
            t.addColumn('created_on', 'date', nullable:false) 
            t.addColumn('submitted_on','date', nullable:true) 
            t.addVersionColumn()
            t.addColumn('rct_id', 'integer', nullable:true) 
            t.addColumn('report_id', 'integer', nullable:true) 
            t.addColumn('grid_id' , 'string' , nullable:true);
            
        }
        //now add the constraints
	    execute('alter table report_schedules add constraint fk_rpshs_rct_id foreign key (rct_id) references report_calendar_templates (id)')
        
    }

    void down() {
        dropTable('report_schedules')
    }
}