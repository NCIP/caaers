class CreateCsmPgPeTrigger extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        if (databaseMatches('oracle')) {
            external("CSM_PG_PE_TRIGGER.sql")
        } 
    }
    
    void down() {
    }
       
}