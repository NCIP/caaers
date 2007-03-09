class CreateGridIdentifiers extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        if (databaseMatches('oracle')) {
            File input = new File("src/main/db/migrate/002_iteration_2/GridIdentifiersOracleSQL_create.sql")
            execute(input.text)
        } else if (databaseMatches('postgresql')){
            File input = new File("src/main/db/migrate/002_iteration_2/GridIdentifiersPostgreSQL_create.sql")
            execute(input.text)
        }
        
        addColumn('adverse_events','grid_id' , 'string' , nullable:true);
        addColumn('ae_reports','grid_id' , 'string' , nullable:true);
        addColumn('ae_labs','grid_id' , 'string' , nullable:true);
        addColumn('identifiers','grid_id' , 'string' , nullable:true);
        addColumn('participants','grid_id' , 'string' , nullable:true);
        addColumn('sites','grid_id' , 'string' , nullable:true);
        addColumn('studies','grid_id' , 'string' , nullable:true);
        addColumn('participant_assignments','grid_id' , 'string' , nullable:true);
        addColumn('study_sites','grid_id' , 'string' , nullable:true);
    }
    
    void down() {
        dropTable("nas")
        dropTable("handles")
    }
}