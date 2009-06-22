class CreateINDHolders extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	        createTable('investigational_new_drugs') { t ->
	            	t.addColumn('ind_number','integer', nullable:true) 
	            	t.addVersionColumn()
	            	t.addColumn('grid_id' , 'string' , nullable:true)
        		}
    }

    void down() {
        dropTable('investigational_new_drugs')
    }
}