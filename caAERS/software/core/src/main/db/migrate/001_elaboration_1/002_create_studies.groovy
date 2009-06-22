class CreateProtocols extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("studies") { t ->
            t.addVersionColumn()
            t.addColumn("short_title", "string", nullable:false)
            t.addColumn("long_title", "string", nullable:true)
            t.addColumn("description", "string", nullable:true)
            t.addColumn("multi_institution_indicator", "boolean", nullable:true)
            t.addColumn("principal_investigator_code", "string", nullable:false)
            t.addColumn("principal_investigator_name", "string", nullable:false)
            t.addColumn("primary_sponsor_code", "string", nullable:false)
            t.addColumn("primary_sponsor_name", "string", nullable:false)
	    t.addColumn("phase_code", "string", nullable:true)
	    t.addColumn("review_date", "date", nullable:true)
        }
    }
    
    void down() {
        dropTable("studies")
    }
}