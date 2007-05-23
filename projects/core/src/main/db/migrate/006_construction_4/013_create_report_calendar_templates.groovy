class CreateReportCalendarTemplates extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('report_calendar_templates') { t ->
            t.addColumn('name','string', nullable:false)
            t.addColumn('description','string', nullable:true)
            t.addColumn('duration', 'integer', nullable:false) 
            t.addColumn('org_id','integer', nullable:true) //the organization id
            t.addColumn('time_scale_unit_code', 'integer', nullable:false) 
            t.addVersionColumn()
            t.addColumn('grid_id' , 'string' , nullable:true);
            
        }
        
    }

    void down() {
        dropTable('report_calendar_templates')
    }
}