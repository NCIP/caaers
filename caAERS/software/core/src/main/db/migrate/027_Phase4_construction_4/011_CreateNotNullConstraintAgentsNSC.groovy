class CreateNotNullConstraintAgentsNSC extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    
	     if (databaseMatches('oracle')){
		    	execute("alter table agents modify nsc not null;");
		    }
		    
	    if (databaseMatches('postgresql')) {
		    	execute("alter table agents alter column nsc set not null;");
	    }
    }

    void down() {
    
    }
}