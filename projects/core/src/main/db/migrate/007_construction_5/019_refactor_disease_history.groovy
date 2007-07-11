class RefactorDiseaseHistory extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        renameTable('disease_history', 'disease_histories');
        renameColumn('disease_histories', 'other_disease_code', 'other_primary_disease');
        renameColumn('disease_histories', 'other_disease_site_code', 'other_primary_disease_site');
        renameColumn('disease_histories', 'anatomic_site_id', 'coded_primary_disease_site_id');
        // ensure that the new constraint will be applicable
        execute("DELETE FROM disease_histories WHERE coded_primary_disease_site_id IS NOT NULL AND coded_primary_disease_site_id NOT IN (SELECT id FROM anatomic_sites)");
        execute("ALTER TABLE disease_histories ADD CONSTRAINT fk_disease_primary FOREIGN KEY (coded_primary_disease_site_id) REFERENCES anatomic_sites");

        // pk: false here is a hack to prevent oracle from trying to rename the ID sequence
        // -- for this table, the rename doesn't change the sequence name
        renameTable('metastatic_disease_site', 'metastatic_disease_sites', primaryKey: false);
        renameColumn('metastatic_disease_sites', 'other_metastatic_disease_site', 'other_site');
        renameColumn('metastatic_disease_sites', 'anatomic_site_id', 'coded_site_id');
        // ensure that the new constraint will be applicable
        execute("DELETE FROM metastatic_disease_sites WHERE coded_site_id IS NOT NULL AND coded_site_id NOT IN (SELECT id FROM anatomic_sites)");
        execute("ALTER TABLE metastatic_disease_sites ADD CONSTRAINT fk_metastatic_anatomic FOREIGN KEY (coded_site_id) REFERENCES anatomic_sites");
    }

    void down() {
        execute("ALTER TABLE metastatic_disease_sites DROP CONSTRAINT fk_metastatic_anatomic");
        renameColumn('metastatic_disease_sites', 'other_site', 'other_metastatic_disease_site');
        renameColumn('metastatic_disease_sites', 'coded_site_id', 'anatomic_site_id');
        renameTable('metastatic_disease_sites', 'metastatic_disease_site', primaryKey: false);

        execute("ALTER TABLE disease_histories DROP CONSTRAINT fk_disease_primary");
        renameColumn('disease_histories', 'other_primary_disease', 'other_disease_code');
        renameColumn('disease_histories', 'other_primary_disease_site', 'other_disease_site_code');
        renameColumn('disease_histories', 'coded_primary_disease_site_id', 'anatomic_site_id');
        renameTable('disease_histories', 'disease_history');
    }
}