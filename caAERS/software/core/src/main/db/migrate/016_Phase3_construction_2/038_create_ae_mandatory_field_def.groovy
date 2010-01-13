class CreateAeReportMandatoryFieldDefs extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('ae_mandatory_field_defs') { t ->
            t.addColumn('field_path', 'string', nullable:false) 
            t.addColumn('mandatory','integer', nullable:false, defaultValue: 0)
            t.addColumn('study_id','integer', nullable:false) //study(id)       
            t.addVersionColumn()
            t.addColumn('grid_id' , 'string' , nullable:true);
        }
        execute('alter table ae_mandatory_field_defs add constraint fk_aermf_study_id foreign key (study_id) references studies (id)')
    }

    void down() {
        dropTable('ae_mandatory_field_defs')
    }
}