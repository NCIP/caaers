class AlterWFComments extends edu.northwestern.bioinformatics.bering.Migration {
void up() {  
		execute("ALTER TABLE wf_review_comments DROP CONSTRAINT fk_wf_comments_ae_rp_id")
		execute("ALTER TABLE wf_review_comments ADD CONSTRAINT fk_wf_comments_ae_rp_id FOREIGN KEY (report_id) REFERENCES ae_reports (id)")
		
	}      
  	void down() {        
  	} 
 }
