class UpdateMetastaticDiseases extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        if (databaseMatches('postgres')) {
             execute("CREATE SEQUENCE metastatic_disease_sites_id_seq");
             execute("ALTER TABLE metastatic_disease_sites ALTER COLUMN id SET DEFAULT nextval('metastatic_disease_sites_id_seq'::regclass)");
        }
    }

    void down() {
        if (databaseMatches('postgres')) {
            execute("ALTER TABLE metastatic_disease_sites ALTER COLUMN id SET DEFAULT nextval('metastatic_disease_site_id_seq'::regclass)");
            execute("DROP SEQUENCE metastatic_disease_sites_id_seq");
        }
    }
}
