class AddOtherMeddraToStudy extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
       	addColumn('studies','other_meddra_id','integer', nullable:true);
    	execute('ALTER TABLE studies ADD CONSTRAINT fk_other_meddra_id FOREIGN KEY (other_meddra_id) REFERENCES meddra_versions');
    }
    
    void down(){
    	removeColumn("studies", "other_meddra_id");
    }
}