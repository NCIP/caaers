class ChangeStudySites extends edu.northwestern.bioinformatics.bering.Migration{
	void up() {
		renameTable('study_sites','study_organizations');
		addColumn('study_organizations','type', 'string' , nullable:false);
		if (databaseMatches('postgres')) {
	 		 execute("ALTER TABLE study_organizations ALTER COLUMN id SET DEFAULT nextval('study_organizations_id_seq'::regclass)")	   
	 	}
    }

    void down() {
    	renameTable('study_organizations','study_sites');
		dropColumn('study_sites', 'type');
		if (databaseMatches('postgres')) {
	 		 execute("ALTER TABLE study_organizations ALTER COLUMN id SET DEFAULT nextval('study_sites_id_seq'::regclass)")	   
	 	}
    }
}