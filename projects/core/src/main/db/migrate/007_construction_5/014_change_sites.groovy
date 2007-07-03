class ChangeSites extends edu.northwestern.bioinformatics.bering.Migration{
	void up() {
		renameTable('sites','organizations');
		addColumn('organizations','nci_institute_code', 'string' , nullable:true);
		addColumn('organizations','description_text', 'string' , nullable:true);
		
		if (databaseMatches('postgres')) {
	 		 execute("ALTER TABLE organizations ALTER COLUMN id SET DEFAULT nextval('organizations_id_seq'::regclass)")	   
	 	}
    }

    void down() {
    	renameTable('organizations','sites');
    	if (databaseMatches('postgres')) {
	 		 execute("ALTER TABLE organizations ALTER COLUMN id SET DEFAULT nextval('sites_id_seq'::regclass)")	   
	 	}
		dropColumn('sites', 'nci_institute_code');
		dropColumn('sites', 'description_text');
    }
}