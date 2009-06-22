class CreateStudyAgentsINDAssociation extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	        createTable('study_agent_inds') { t ->
	            	t.addColumn('study_agent_id','integer', nullable:false) 
   	           	 	t.addColumn('ind_id','integer', nullable:false)
	            	t.addVersionColumn()
	            	t.addColumn('grid_id' , 'string' , nullable:true)
        		}
 				//now add the constraints
 				execute('alter table study_agent_inds add constraint fk_sai_sa_id foreign key (study_agent_id) references study_agents (id)')
				execute('alter table study_agent_inds add constraint fk_sai_ind_id foreign key (ind_id) references investigational_new_drugs (id)')
    }

    void down() {
        dropTable('study_agent_inds')
    }
}