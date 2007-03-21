class CreateParticipants extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('disease_terms') { t ->
        	t.addColumn('term', 'string')
        	t.addColumn('ctep_term', 'string')
        	t.addColumn("category_id", "integer", nullable: false)
        	t.addColumn('medra_code', 'string')
            t.addVersionColumn()
        }
        execute('ALTER TABLE disease_terms ADD CONSTRAINT fk_disease_term_cat FOREIGN KEY (category_id) REFERENCES disease_categories')
    }


    void down() {
        dropTable('disease_terms')
    }
}