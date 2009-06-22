class CreateRecipients extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('recipients') { t ->
            t.addColumn('plnf_id', 'integer', nullable:true) //planned_notificaitons(id)
            t.addColumn('dtype','string', nullable:false)
            t.addColumn('role_name','string', nullable:true)
            t.addColumn('contact_name','string', nullable:true) //contact_mechanisms(id)
            t.addVersionColumn()
            t.addColumn('grid_id' , 'string' , nullable:true);
            
        }
        
	//now add the constraints
    execute('alter table recipients add constraint fk_rcpnts_plnf_id foreign key (plnf_id) references planned_notifications (id) on delete cascade')
        
    }

    void down() {
        dropTable('recipients')
    }
}