class UpdateInvestigatorSequence extends edu.northwestern.bioinformatics.bering.Migration {
    void up(){
        if (databaseMatches('postgresql')){
            execute("ALTER TABLE investigators ALTER COLUMN id SET DEFAULT nextval('research_staffs_id_seq'::regclass)");
        }
    }
    void down(){
        //NA
    }
}

