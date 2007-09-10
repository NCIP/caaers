class CreateAuditEventValues extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('audit_event_values') { t ->
          t.addColumn('audit_event_id', 'integer', nullable:false)
          t.addColumn('attribute_name', 'string', nullable:false)
	    t.addColumn('previous_value', 'string', nullable:true)
	    t.addColumn('new_value', 'string', nullable:true)
 	    t.addColumn('version', 'integer', nullable:false)
        }
    }

    void down() {
        dropTable('audit_event_values')
    }
}