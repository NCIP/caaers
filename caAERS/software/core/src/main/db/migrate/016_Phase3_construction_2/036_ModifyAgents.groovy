class ModifyAgents extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	if (databaseMatches('postgresql')){
    		execute('ALTER SEQUENCE agents_id_seq RESTART WITH 1306');
    		
    	}else if(databaseMatches('oracle')){
    		execute('DROP SEQUENCE seq_meddra_soc_id');
    		execute('CREATE SEQUENCE seq_agents_id START WITH 1306');
    	} 
    }
    
    void down(){
    
    }
}