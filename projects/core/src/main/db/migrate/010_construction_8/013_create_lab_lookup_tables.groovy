class CreateLabLookups extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
       	createTable("lab_categories") { t ->
            t.setIncludePrimaryKey(false)
            t.addColumn("id", "integer", nullable: false, primaryKey: true)
            t.addColumn("version_id", "integer", nullable: false)
            t.addColumn("name", "string", nullable: false)
        }

        createTable("lab_versions") { t->
            t.setIncludePrimaryKey(false)
            t.addColumn("id", "integer", nullable: false, primaryKey: true)
            t.addColumn("name", "string", nullable: false)
        }
        
         createTable("lab_terms") { t ->
            t.setIncludePrimaryKey(false)
            t.addColumn("id", "integer", nullable: false, primaryKey: true)
            t.addColumn("category_id", "integer", nullable: false)
            t.addColumn("term", "string", nullable: false)
        }
        execute('ALTER TABLE lab_terms ADD CONSTRAINT fk_lab_term_cat FOREIGN KEY (category_id) REFERENCES lab_categories')
		execute('ALTER TABLE lab_categories ADD CONSTRAINT fk_lab_cat_version FOREIGN KEY (version_id) REFERENCES lab_versions')

        insert("lab_versions", [ id: 1, name: "Version 1" ], primaryKey: false)
    }

    void down() {
        dropTable("lab_terms", primaryKey: false)
        dropTable("lab_categories", primaryKey: false)
        dropTable("lab_versions", primaryKey: false)
    }
}