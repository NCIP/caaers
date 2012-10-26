class CreateAdditionalInformationDocument extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('additional_information_document') { t ->
            t.addColumn('file_id', 'string', nullable: false)
            t.addColumn('original_file_name', 'string', nullable: false)
            t.addColumn('file_name', 'string', nullable: false)
            t.addColumn('file_path', 'string', nullable: false)
            t.addColumn('file_size', 'string', nullable: false)
            t.addColumn('relative_path', 'string', nullable: false)
            t.addColumn('additional_information_id', 'integer', nullable: false)
            t.addColumn('grid_id', 'string', nullable: false)
            t.addColumn('document_type', 'string', nullable: false)

            t.addVersionColumn()
        }
        execute('ALTER TABLE additional_information_document ADD CONSTRAINT fk_additional_information_id FOREIGN KEY (additional_information_id) REFERENCES additional_information')
    }

    void down() {
        dropTable('additional_information_document')
    }
}