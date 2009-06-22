class UpdateCSMPgPeSeq extends edu.northwestern.bioinformatics.bering.Migration {
    
    void up() {
    	if (databaseMatches('oracle')) {
    	    //to be on safe side run 11 times
            execute("select CSM_PG_PE_PG_PE_ID_SEQ.nextval from dual")
            execute("select CSM_PG_PE_PG_PE_ID_SEQ.nextval from dual")
            execute("select CSM_PG_PE_PG_PE_ID_SEQ.nextval from dual")
            execute("select CSM_PG_PE_PG_PE_ID_SEQ.nextval from dual")
            execute("select CSM_PG_PE_PG_PE_ID_SEQ.nextval from dual")
            execute("select CSM_PG_PE_PG_PE_ID_SEQ.nextval from dual")
            execute("select CSM_PG_PE_PG_PE_ID_SEQ.nextval from dual")
            execute("select CSM_PG_PE_PG_PE_ID_SEQ.nextval from dual")
            execute("select CSM_PG_PE_PG_PE_ID_SEQ.nextval from dual")
            execute("select CSM_PG_PE_PG_PE_ID_SEQ.nextval from dual")
            execute("select CSM_PG_PE_PG_PE_ID_SEQ.nextval from dual")
        }  
        if (databaseMatches('postgresql')){
            execute("ALTER SEQUENCE csm_pg_pe_id_seq RESTART WITH 15")
        }	
    }
    
    void down(){
     //no down needed
    }
}
