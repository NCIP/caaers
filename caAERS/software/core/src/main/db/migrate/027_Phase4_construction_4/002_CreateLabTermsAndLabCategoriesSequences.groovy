class AddLastSynchedDateToLabTerms extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    
	     if (databaseMatches('oracle')){
		    	execute("CREATE SEQUENCE lab_terms_id_seq increment by 1 start with 200 NOMAXVALUE minvalue 1 nocycle  nocache noorder;");
		    	execute("CREATE SEQUENCE lab_categories_id_seq increment by 1 start with 200 NOMAXVALUE minvalue 1 nocycle  nocache noorder;");
		    }
		    
	    if (databaseMatches('postgresql')) {
		    	execute("CREATE SEQUENCE lab_terms_id_seq INCREMENT 1 START 200;");
		    	execute("alter table lab_terms alter column id set default nextval('lab_terms_id_seq');");
		   		execute("CREATE SEQUENCE lab_categories_id_seq INCREMENT 1 START 200;");
		   		execute("alter table lab_categories alter column id set default nextval('lab_categories_id_seq');");
	    }
    }

    void down() {
    	execute("drop sequence lab_terms_id_seq;");
    	execute("drop sequence lab_categories_id_seq;");
    }
}