class CreateGridIdentifiers extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        if (databaseMatches('oracle')) {
            external("GridIdentifiersOracleSQL_create.sql")
        } else if (databaseMatches('postgresql')){
            external("GridIdentifiersPostgreSQL_create.sql")
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
        dropTable("nas", primaryKey: false)
        dropTable("handles", primaryKey: false)

        dropColumn('adverse_events', 'grid_id');
        dropColumn('ae_reports', 'grid_id');
        dropColumn('ae_labs', 'grid_id');
        dropColumn('identifiers', 'grid_id');
        dropColumn('participants', 'grid_id');
        dropColumn('sites', 'grid_id');
        dropColumn('studies', 'grid_id');
        dropColumn('participant_assignments', 'grid_id');
        dropColumn('study_sites', 'grid_id');
    }
}