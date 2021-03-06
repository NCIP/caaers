class UpdatePreExistingConditionsSequenceCurrentValue extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	if (databaseMatches('postgres')) {
	    	  for(int i =0; i < 200 ; i++){
		    		execute("select nextval('pre_existing_conditions_id_seq')");
		    	}
	    	}
	    	
    	if (databaseMatches('oracle')) {
	    	  for(int i =0; i < 200 ; i++){
		    		execute("select seq_pre_existing_conditions_id.nextval from dual");
		    	}
    	}
    }

    void down() {
       
    }
}