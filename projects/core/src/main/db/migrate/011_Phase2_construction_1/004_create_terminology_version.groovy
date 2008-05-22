class AddTerminologyVersion extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
       	addColumn('disease_terminologies','meddra_version_id','integer', nullable:true);
    	execute('ALTER TABLE disease_terminologies ADD CONSTRAINT fk_meddra_dt_ver FOREIGN KEY (meddra_version_id) REFERENCES meddra_versions');
    	execute('ALTER TABLE terminologies ADD CONSTRAINT fk_meddra_aet_ver FOREIGN KEY (meddra_version_id) REFERENCES meddra_versions');
    }
    
    void down(){
    	removeColumn("disease_terminologies", "meddra_version_id");
    }
}