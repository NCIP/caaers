class ContextualGradingTable extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("ctc_grades") { t ->
            t.setIncludePrimaryKey(false)
            t.addColumn("id", "integer", nullable: false, primaryKey: true)
            t.addColumn("term_id", "integer", nullable: false)
            t.addColumn("grade_code", "integer", nullable: false)
            t.addColumn("grade_text", "string", nullable: false)
        }

        execute('ALTER TABLE ctc_grades ADD CONSTRAINT fk_ctc_grade_term FOREIGN KEY (term_id) REFERENCES ctc_terms')
        execute('ALTER TABLE ctc_grades ADD CONSTRAINT uk_ctc_grade_term UNIQUE (term_id, grade_code)')
    }

    void down() {
        dropTable("ctc_grades", primaryKey: false)
    }
}