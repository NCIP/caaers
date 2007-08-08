class MigrateIdentifiers extends edu.northwestern.bioinformatics.bering.Migration {
    
    void up() {
            execute("UPDATE identifiers set discriminator_column='2'") 
			execute("UPDATE identifiers set system_name='test'") 
    }

    void down() {
    		// No deleting of data necessary will be gone by 004
    }
    
}