class CreateStudyTherapy extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    
    if (databaseMatches('oracle')) {
	 		 execute("select seq_organizations_id.nextval from dual");
	 		  execute("select seq_organizations_id.nextval from dual");	  
	 		  
	 		 execute("select seq_organizations_id.nextval from dual");
	 		  execute("select seq_organizations_id.nextval from dual");
	 		  
	 		 execute("select seq_organizations_id.nextval from dual");
	 	}
	 	
	 	
    if (databaseMatches('postgres')) {
	 		 execute("select nextval('organizations_id_seq')")	   ;
	 		 
	 		 execute("select nextval('organizations_id_seq')")	   ;
	 		 
	 		 execute("select nextval('organizations_id_seq')")	   ;
	 		 
	 		 execute("select nextval('organizations_id_seq')")	   ;
	 		 
	 		 execute("select nextval('organizations_id_seq')")	   ;
	 	}
	 	
	 	
	 	
	 	}

    void down() {
        
    }
}