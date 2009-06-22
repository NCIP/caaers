class CreateStudyTerm extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("study_terms") { t ->
            t.addVersionColumn()

            t.addColumn("study_id", "integer")
        	t.addColumn('term_id', 'integer', nullable:true)
        	t.addColumn('term_type', 'string', nullable:true)
        	t.addColumn('meddra_code', 'string', nullable:true)

            t.addColumn("grid_id", "string")
        }
    }

    void down() {
        dropTable("study_terms")
    }
}