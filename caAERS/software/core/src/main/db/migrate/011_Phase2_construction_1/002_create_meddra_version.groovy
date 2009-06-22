class AddMeddraVersion extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	addColumn('meddra_soc','version_id' , 'integer' , nullable:true);
    	addColumn('meddra_hlgt','version_id' , 'integer' , nullable:true);
    	addColumn('meddra_hlt','version_id' , 'integer' , nullable:true);
    	addColumn('meddra_pt','version_id' , 'integer' , nullable:true);
    	addColumn('meddra_llt','version_id' , 'integer' , nullable:true);
    	addColumn('meddra_soc_hlgt','version_id', 'integer', nullable:true);
    	addColumn('meddra_hlgt_hlt','version_id', 'integer', nullable:true);
    	addColumn('meddra_hlt_pt','version_id', 'integer', nullable:true);
    	
    	// insert 9 into the rows existing in these tables
    	execute('update meddra_soc set version_id = 9');
    	execute('update meddra_hlgt set version_id = 9');
    	execute('update meddra_hlt set version_id = 9');
    	execute('update meddra_pt set version_id = 9');
    	execute('update meddra_llt set version_id = 9');
    	execute('update meddra_soc_hlgt set version_id = 9');
    	execute('update meddra_hlgt_hlt set version_id = 9');
    	execute('update meddra_hlt_pt set version_id = 9');
    	
    	execute('CREATE SEQUENCE meddra_versions_id_seq START WITH 10');
    	if (databaseMatches('postgresql')){
    	 
    		execute('ALTER SEQUENCE meddra_soc_id_seq RESTART WITH 10100000');
    		execute('ALTER SEQUENCE meddra_hlgt_id_seq RESTART WITH 10100000');
    		execute('ALTER SEQUENCE meddra_hlt_id_seq RESTART WITH 10100000');
    		execute('ALTER SEQUENCE meddra_pt_id_seq RESTART WITH 10100000');
    		execute('ALTER SEQUENCE meddra_llt_id_seq RESTART WITH 10100000');
    		execute('ALTER SEQUENCE meddra_soc_hlgt_id_seq RESTART WITH 100000');
    		execute('ALTER SEQUENCE meddra_hlgt_hlt_id_seq RESTART WITH 100000');
    		execute('ALTER SEQUENCE meddra_hlt_pt_id_seq RESTART WITH 100000');
    		
    	}else if(databaseMatches('oracle')){
    	
    		execute('DROP SEQUENCE seq_meddra_soc_id');
    		execute('CREATE SEQUENCE seq_meddra_soc_id START WITH 10100000');
    		execute('DROP SEQUENCE seq_meddra_hlgt_id');
    		execute('CREATE SEQUENCE seq_meddra_hlgt_id START WITH 10100000');
    		execute('DROP SEQUENCE seq_meddra_hlt_id');
    		execute('CREATE SEQUENCE seq_meddra_hlt_id START WITH 10100000');
    		execute('DROP SEQUENCE seq_meddra_pt_id');
    		execute('CREATE SEQUENCE seq_meddra_pt_id START WITH 10100000');
    		execute('DROP SEQUENCE seq_meddra_llt_id');
    		execute('CREATE SEQUENCE seq_meddra_llt_id START WITH 10100000');
    		execute('DROP SEQUENCE seq_meddra_soc_hlgt_id');
    		execute('CREATE SEQUENCE seq_meddra_soc_hlgt_id START WITH 100000');
    		execute('DROP SEQUENCE seq_meddra_hlgt_hlt_id');
    		execute('CREATE SEQUENCE seq_meddra_hlgt_hlt_id START WITH 100000');
    		execute('DROP SEQUENCE seq_meddra_hlt_pt_id');
    		execute('CREATE SEQUENCE seq_meddra_hlt_pt_id START WITH 100000');
    		
    	} 
    }
    
    void down(){
        removeColumn("meddra_llt", "version_id");
        removeColumn("meddra_pt", "version_id");
        removeColumn("meddra_hlt", "version_id");
        removeColumn("meddra_hlgt", "version_id");
        removeColumn("meddra_soc", "version_id");
        removeColumn("meddra_soc_hlgt", "version_id");
        removeColumn("meddra_hlgt_hlt", "version_id");
        removeColumn("meddra_hlt_pt", "version_id");  
    }
}