class AlterWFComments extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {  
		if (databaseMatches('postgresql')){
			execute("ALTER TABLE wf_review_comments DROP CONSTRAINT fk_wf_comments_ae_rp_id")
			execute("ALTER TABLE wf_review_comments ADD CONSTRAINT fk_wf_comments_ae_rp_id FOREIGN KEY (report_id) REFERENCES report_schedules (id)")
		}	
	}      
  	void down() {        
  	} 
 }