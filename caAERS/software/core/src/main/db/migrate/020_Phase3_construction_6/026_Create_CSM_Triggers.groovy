class CreateCsmTriggers extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        if (databaseMatches('oracle')) {
            external("CSM_TRIGGERS.sql")
        } 
    }
    
    void down() {
    }
       
}