class CreateReportMandatoryFieldDefs extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('mandatory_field_defs') { t ->
            t.addColumn('field_path', 'string', nullable:false) 
            t.addColumn('mandatory','boolean', nullable:false)
            t.addColumn('rct_id','integer', nullable:true) //report_calendar_templates(id)       
            t.addVersionColumn()
            t.addColumn('grid_id' , 'string' , nullable:true);
        }
        execute('alter table mandatory_field_defs add constraint fk_rmf_rct_id foreign key (rct_id) references report_calendar_templates (id)')
    }

    void down() {
        dropTable('mandatory_field_defs')
    }
}