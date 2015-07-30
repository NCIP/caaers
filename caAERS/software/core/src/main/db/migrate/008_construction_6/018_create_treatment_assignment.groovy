class CreateStudyTherapy extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	        createTable('treatment_assignment') { t ->
	            	t.addColumn('code','string', nullable:false)
   	           	 	t.addColumn('study_id','integer', nullable:false)
   	           	 	t.addColumn('dose_level_order','integer', nullable:true)
   	           	 	t.addColumn('description','string', nullable:true, limit:4000)
	            	t.addColumn('comments','string', nullable:true, limit: 4000)
	            	
	            	t.addVersionColumn()
	            	t.addColumn('grid_id' , 'string' , nullable:true)
	            	
        		}
 				//now add the constraints
 				execute('alter table treatment_assignment add constraint fk_treatmentassignment_id foreign key (study_id) references studies (id)')
    }

    void down() {
        dropTable('treatment_assignment')
    }
}