class CleanMeddraTables extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	execute("DELETE FROM meddra_soc where meddra_term = 'Not Specified'");
    	execute("DELETE FROM meddra_hlgt where meddra_term = 'Not Specified'");
    	execute("DELETE FROM meddra_hlt where meddra_term = 'Not Specified'");
    	execute("DELETE FROM meddra_pt where meddra_term = 'Not Specified'");
    	execute("DELETE FROM meddra_llt where meddra_term = 'Not Specified'");
    	//execute("DELETE FROM meddra_versions");
    }
 	void down(){
 	}   
}