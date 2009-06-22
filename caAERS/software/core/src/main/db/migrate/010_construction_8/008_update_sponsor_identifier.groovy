class UpdateIdentifiers extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
      
      if (databaseMatches('oracle')) {
       execute('UPDATE identifiers SET type= \'Protocol Authority Identifier\'  WHERE type= \'Sponsor Identifier\' ');
      }
      
      if (databaseMatches('postgresql')) {
       execute('UPDATE identifiers SET "type"= \'Protocol Authority Identifier\'  WHERE "type"= \'Sponsor Identifier\' ');
      }
      
       
    }

    void down() {
    
    	if (databaseMatches('oracle')) {
          execute('UPDATE identifiers SET type= \'Sponsor Identifier\'  WHERE type= \'Protocol Authority Identifier\' ');
        }
          
        if (databaseMatches('postgresql')) {
      	 execute('UPDATE identifiers SET "type"= \'Sponsor Identifier\'  WHERE "type"= \'Protocol Authority Identifier\' ');
      	}
        
    }
}
