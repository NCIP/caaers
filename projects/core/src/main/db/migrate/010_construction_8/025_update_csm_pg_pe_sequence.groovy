class UpdateCSMPgPeSeq extends edu.northwestern.bioinformatics.bering.Migration {
    
    void up() {
    	if (databaseMatches('oracle')) {
    	    //to be on safe side run 11 times
            execute("alter sequence CSM_PG_PE_PG_PE_ID_SEQ increment by 9000")
            execute("select CSM_PG_PE_PG_PE_ID_SEQ.nextval from dual")
			execute("alter sequence CSM_PG_PE_PG_PE_ID_SEQ increment by 1")
        }  
        if (databaseMatches('postgresql')){
            execute("ALTER SEQUENCE csm_pg_pe_id_seq RESTART WITH 9000")
        }	
    }
    
    void down(){
     //no down needed
    }
}
