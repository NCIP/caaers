class CreateSites extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('sites') { t ->
            t.addColumn('name', 'string', nullable:false)
            t.addVersionColumn()
        }
    }

    void down() {
        dropTable('sites')
    }
}