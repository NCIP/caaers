class CreateStudyTherapy extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	        createTable('study_therapy') { t ->
	            	t.addColumn('study_therapy_type','string', nullable:false)
   	           	 	t.addColumn('study_id','integer', nullable:true)
	            	t.addVersionColumn()
	            	t.addColumn('grid_id' , 'string' , nullable:true)
        		}
 				//now add the constraints
 				execute('alter table study_therapy add constraint fk_studytherapy_id foreign key (study_id) references studies (id)')
    }

    void down() {
        dropTable('study_therapy')
    }
}