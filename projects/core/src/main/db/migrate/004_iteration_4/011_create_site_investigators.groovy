class CreateSiteInvestigators extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('site_investigators') { t ->           
	        t.addColumn('status_code', 'string', nullable:true)
            t.addColumn('status_date', 'date', nullable:true)
            t.addColumn('site_id', 'integer', nullable:false)
            t.addColumn('investigator_id', 'integer', nullable:false)
            t.addColumn('grid_id', 'string', nullable:true)
            t.addVersionColumn()
        }
    }

    void down() {
        dropTable('site_investigators')
    }
}