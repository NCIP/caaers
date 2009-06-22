class AddMeddraVersionAssociation extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    
    	//clean tables
    	execute('DELETE FROM meddra_soc_hlgt where meddra_soc_id NOT IN (select id from meddra_soc)');
    	execute('DELETE FROM meddra_soc_hlgt where meddra_hlgt_id NOT IN (select id from meddra_hlgt)');
    	execute('DELETE FROM meddra_hlgt_hlt where meddra_hlgt_id NOT IN (select id from meddra_hlgt)');
    	execute('DELETE FROM meddra_hlgt_hlt where meddra_hlt_id NOT IN (select id from meddra_hlt)');
    	execute('DELETE FROM meddra_hlt_pt where meddra_hlt_id NOT IN (select id from meddra_hlt)');
    	execute('DELETE FROM meddra_hlt_pt where meddra_pt_id NOT IN (select id from meddra_pt)');
  		// done cleaning the tables
  
       	execute('ALTER TABLE meddra_soc ADD CONSTRAINT fk_meddra_soc_ver FOREIGN KEY (version_id) REFERENCES meddra_versions');
    	execute('ALTER TABLE meddra_hlgt ADD CONSTRAINT fk_meddra_hlgt_ver FOREIGN KEY (version_id) REFERENCES meddra_versions');
    	execute('ALTER TABLE meddra_hlt ADD CONSTRAINT fk_meddra_hlt_ver FOREIGN KEY (version_id) REFERENCES meddra_versions');
    	execute('ALTER TABLE meddra_pt ADD CONSTRAINT fk_meddra_pt_ver FOREIGN KEY (version_id) REFERENCES meddra_versions');
    	execute('ALTER TABLE meddra_llt ADD CONSTRAINT fk_meddra_llt_ver FOREIGN KEY (version_id) REFERENCES meddra_versions');
    	
    	execute('ALTER TABLE meddra_soc_hlgt ADD CONSTRAINT fk_meddra_soc_id FOREIGN KEY (meddra_soc_id) REFERENCES meddra_soc');
    	execute('ALTER TABLE meddra_soc_hlgt ADD CONSTRAINT fk_meddra_hlgt_soc_id FOREIGN KEY (meddra_hlgt_id) REFERENCES meddra_hlgt');
    	execute('ALTER TABLE meddra_hlgt_hlt ADD CONSTRAINT fk_meddra_hlgt_hlt_id FOREIGN KEY (meddra_hlgt_id) REFERENCES meddra_hlgt');
    	execute('ALTER TABLE meddra_hlgt_hlt ADD CONSTRAINT fk_meddra_hlt_id FOREIGN KEY (meddra_hlt_id) REFERENCES meddra_hlt');
    	execute('ALTER TABLE meddra_hlt_pt ADD CONSTRAINT fk_meddra_hlt_pt_id FOREIGN KEY (meddra_hlt_id) REFERENCES meddra_hlt');
    	execute('ALTER TABLE meddra_hlt_pt ADD CONSTRAINT fk_meddra_pt_id FOREIGN KEY (meddra_pt_id) REFERENCES meddra_pt');
    }
    
    void down(){
    	execute("ALTER TABLE meddra_hlt_pt DROP CONSTRAINT fk_meddra_pt_id");
    	execute("ALTER TABLE meddra_hlt_pt DROP CONSTRAINT fk_meddra_hlt_pt_id");
    	execute("ALTER TABLE meddra_hlgt_hlt DROP CONSTRAINT fk_meddra_hlt_id");
    	execute("ALTER TABLE meddra_hlgt_hlt DROP CONSTRAINT fk_meddra_hlgt_hlt_id");
    	execute("ALTER TABLE meddra_soc_hlgt DROP CONSTRAINT fk_meddra_hlgt_soc_id");
    	execute("ALTER TABLE meddra_soc_hlgt DROP CONSTRAINT fk_meddra_soc_id");
    	
    	execute("ALTER TABLE meddra_llt DROP CONSTRAINT fk_meddra_llt_ver");
    	execute("ALTER TABLE meddra_pt DROP CONSTRAINT fk_meddra_pt_ver");
    	execute("ALTER TABLE meddra_hlt DROP CONSTRAINT fk_meddra_hlt_ver");
    	execute("ALTER TABLE meddra_hlgt DROP CONSTRAINT fk_meddra_hlgt_ver");
    	execute("ALTER TABLE meddra_soc DROP CONSTRAINT fk_meddra_soc_ver");
    	
    }
}