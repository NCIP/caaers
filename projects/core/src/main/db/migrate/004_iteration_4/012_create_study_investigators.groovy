class CreateStudyInvestigators extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('study_investigators') { t ->           
	        t.addColumn('signature_text', 'string', nullable:true)            
            t.addColumn('study_sites_id', 'integer', nullable:false)
            t.addColumn('site_investigators_id', 'integer', nullable:false)
            t.addColumn('grid_id', 'string', nullable:true)
            t.addVersionColumn()
        }
    }

    void down() {
        dropTable('study_investigators')
    }
}