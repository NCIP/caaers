class CreateReportDeliveryDefs extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('report_delivery_defs') { t ->
            t.addColumn('format', 'string', nullable:false) 
            t.addColumn('entity_name','string', nullable:false)
            t.addColumn('entity_description','string', nullable:true)
            t.addColumn('end_point','string', nullable:false) 
            t.addColumn('end_point_type','string', nullable:false) 
            t.addColumn('entity_type','integer', nullable:false)  
            t.addColumn('rct_id','integer', nullable:true) //report_calendar_templates(id)       
            t.addVersionColumn()
            t.addColumn('grid_id' , 'string' , nullable:true);
        }
        execute('alter table report_delivery_defs add constraint fk_rd_rct_id foreign key (rct_id) references report_calendar_templates (id)')
    }

    void down() {
        dropTable('report_delivery_defs')
    }
}