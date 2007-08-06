class CreateINDHolders extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	        createTable('ind_holders') { t ->
	            	t.addColumn('dtype','string', nullable:false)
	            	t.addColumn('org_id','integer', nullable:true) 
   	           	 	t.addColumn('inv_id','integer', nullable:true)
   	           	 	t.addColumn('drug_id', 'integer', nullable:false)
	            	t.addVersionColumn()
	            	t.addColumn('grid_id' , 'string' , nullable:true)
        		}
 				//now add the constraints
 				execute('alter table ind_holders add constraint fk_indhldrs_org_id foreign key (org_id) references organizations (id)')
				execute('alter table ind_holders add constraint fk_indhldrs_inv_id foreign key (inv_id) references investigators (id)')
				execute('alter table ind_holders add constraint fk_indhldrs_drug_id foreign key (drug_id) references investigational_new_drugs (id)')
    }

    void down() {
        dropTable('ind_holders')
    }
}