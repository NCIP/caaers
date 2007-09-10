class CreateAuditEvents extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('audit_events') { t ->
          t.addColumn('ip_address', 'string', nullable:false)
	    t.addColumn('user_name', 'string', nullable:false)
	    t.addColumn('time', 'timestamp', nullable:false)
	    t.addColumn('class_name', 'string', nullable:false)
	    t.addColumn('object_id', 'string', nullable:false)
	    t.addColumn('operation', 'string', nullable:false)
	    t.addColumn('url', 'string', nullable:false)
 	    t.addColumn('version', 'integer', nullable:false)
        }
    }

    void down() {
        dropTable('audit_events')
    }
}