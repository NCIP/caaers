class CreateCaaersFieldDefs extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('caaers_field_defs') { t ->
        	t.addColumn('tab_name', 'string', nullable:false)
            t.addColumn('field_path', 'string', nullable:false) 
            t.addColumn('mandatory','integer', nullable:false, defaultValue: 0)
            t.addVersionColumn()
            t.addColumn('grid_id' , 'string' , nullable:true);
        }
    }

    void down() {
        dropTable('caaers_field_defs')
    }
}