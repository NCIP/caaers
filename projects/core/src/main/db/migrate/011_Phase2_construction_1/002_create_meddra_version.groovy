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
    	
    	execute('CREATE SEQUENCE meddra_versions_id_seq START WITH 10');    
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