class UpdatePriorTherapySequenceCurrentValue extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
	    if (databaseMatches('postgres')) {
	    	  for(int i =0; i < 200 ; i++){
		    		execute("select nextval('prior_therapies_id_seq')");
		    	}
	    	}
	    	
    	if (databaseMatches('oracle')) {
	    	  for(int i =0; i < 200 ; i++){
		    		execute("select prior_therapies_id_seq.nextval");
		    	}
    	}
    }

    void down() {
       
    }
}