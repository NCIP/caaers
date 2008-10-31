class CreateReportContentTable extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        
        createTable("report_contents") { t ->
        	t.addColumn("content_type", "string")
        	t.addColumn("report_version_id" , "integer");
        	t.addColumn("grid_id" , "string");
        	t.addVersionColumn()
        }
        
        
	 	if (databaseMatches('postgres')) {
	 		execute('alter table report_contents add column  content oid')
	 	}
	 	 
	 	if(databaseMatches('oracle')){
	 		execute('alter table report_contents add  ( content blob)')
	 	}
	 	
	 	execute('ALTER TABLE report_contents ADD CONSTRAINT FK_report_versions_contents FOREIGN KEY (report_version_id) REFERENCES report_versions (id)');
    }

    void down() {
        dropTable("report_contents")
    }
}