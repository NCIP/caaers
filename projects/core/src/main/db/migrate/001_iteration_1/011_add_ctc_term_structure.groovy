class AddCtcTermStructure extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        // Adding terms requires remodeling the categories & versions a bit
        execute("ALTER TABLE adverse_events DROP CONSTRAINT fk_adv_ev_ctc_cat");
        dropTable("ctc_version_categories", primaryKey: false);
        dropTable("ctc_categories", primaryKey: false);

        createTable("ctc_categories") { t ->
            t.setIncludePrimaryKey(false)
            t.addColumn("id", "integer", nullable: false, primaryKey: true)
            t.addColumn("version_id", "integer", nullable: false)
            t.addColumn("name", "string", nullable: false)
        }
        execute('ALTER TABLE ctc_categories ADD CONSTRAINT fk_ctc_cat_ver FOREIGN KEY (version_id) REFERENCES ctc_versions')

        createTable("ctc_terms") { t ->
            t.setIncludePrimaryKey(false)
            t.addColumn("id", "integer", nullable: false, primaryKey: true)
            t.addColumn("category_id", "integer", nullable: false)
            t.addColumn("term", "string", nullable: false)
            t.addColumn("select_ae", "string")
            t.addColumn("ctep_term", "string")
            t.addColumn("ctep_code", "string")
            if (databaseMatches('oracle')) {
                t.addColumn("other_required", "boolean", nullable: false, defaultValue: 0)
            } else {
                t.addColumn("other_required", "boolean", nullable: false, defaultValue: false)
            }
        }
        execute('ALTER TABLE ctc_terms ADD CONSTRAINT fk_ctc_term_cat FOREIGN KEY (category_id) REFERENCES ctc_categories')
    }

    void down() {
        dropTable('ctc_terms', primaryKey: false)
        dropTable('ctc_categories', primaryKey: false)

        // readd as from 009
        createTable("ctc_categories") { t->
            t.setIncludePrimaryKey(false)
            t.addColumn("id", "integer", nullable: false, primaryKey: true)
            t.addColumn("name", "string", nullable: false)
        }

        createTable("ctc_version_categories") { t ->
            t.setIncludePrimaryKey(false)
            t.addColumn("version_id", "integer", nullable: false)
            t.addColumn("category_id", "integer", nullable: false)
        }

        execute('ALTER TABLE ctc_version_categories ADD CONSTRAINT fk_ctc_ver_cat_ver FOREIGN KEY (version_id) REFERENCES ctc_versions')
        execute('ALTER TABLE ctc_version_categories ADD CONSTRAINT fk_ctc_ver_cat_cat FOREIGN KEY (category_id) REFERENCES ctc_categories')

        // readd from 010
        execute("ALTER TABLE adverse_events ADD CONSTRAINT fk_adv_ev_ctc_cat FOREIGN KEY (ctc_category_id) REFERENCES ctc_categories");

        // skipped restoring data since there's no live system to restore
    }
}