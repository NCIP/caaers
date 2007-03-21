class CreateParticipants extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('disease_categories') { t ->
        	t.addColumn('parent_id', 'integer', nullable:true)
            t.addColumn('name', 'string', nullable:true)
            t.addVersionColumn()
        }
    }
    
    void down() {
        dropTable('disease_categories')
    }
}