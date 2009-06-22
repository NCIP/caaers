class AlterReviewComments extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
	if (databaseMatches('postgresql')){
         execute('ALTER TABLE wf_review_comments ALTER created_date TYPE timestamp without time zone')
     }else if(databaseMatches('oracle')){
     	execute('alter table wf_review_comments rename column created_date to created_date_old')
		execute('alter table wf_review_comments add (created_date timestamp)')
		execute('update wf_review_comments set created_date = cast(created_date_old as timestamp)')
		execute('alter table wf_review_comments drop (created_date_old)')
     }
		
	}
	void down(){
		//NOT REQUIRED
	}
}