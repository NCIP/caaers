class CleanMeddraTables extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	execute("DELETE FROM meddra_soc");
    	execute("DELETE FROM meddra_hlgt");
    	execute("DELETE FROM meddra_hlt");
    	execute("DELETE FROM meddra_pt");
    	execute("DELETE FROM meddra_llt");
    	execute("DELETE FROM meddra_versions");
    }
 	void down(){
 	}   
}