class CreateReportFormat extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	        createTable('report_format') { t ->
	            	t.addColumn('report_format_type','string', nullable:false)
   	           	 	t.addColumn('study_id','integer', nullable:true)
	            	t.addVersionColumn()
	            	t.addColumn('grid_id' , 'string' , nullable:true)
        		}
 				//now add the constraints
 				execute('alter table report_format add constraint fk_reportformat_id foreign key (study_id) references studies (id)')
    }

    void down() {
        dropTable('report_format')
    }
}