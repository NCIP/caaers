class HealthyVolunteer extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("studies", "healthy_volunteer", "boolean")
        
        if (databaseMatches('oracle')) {
            execute("UPDATE studies set healthy_volunteer=0")
        }  
        if (databaseMatches('postgresql')){
            execute("UPDATE studies set healthy_volunteer=false")
        }
        
        
        
    }

    void down() {
        dropColumn("studies", "healthy_volunteer")
    }
}