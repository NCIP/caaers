class UpdateReviewComments extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
      if (databaseMatches('oracle')) {
        execute('delete from wf_review_comments where RP_ID is NULL');
        execute('ALTER TABLE wf_review_comments DROP CONSTRAINT FK_WF_COMMENTS_AE_RP_ID')
        execute("ALTER TABLE wf_review_comments ADD CONSTRAINT fk_wf_comments_ae_rp_id FOREIGN KEY (report_id) REFERENCES report_schedules (id)")
        
      }
    }

    void down() {
      //not needed
    }
}