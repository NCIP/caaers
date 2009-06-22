class UpdateAdverseEvents extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
       
      addColumn("adverse_events","low_level_term_id","integer", nullable: true)
	 	
    }

    void down() {
   		dropColumn('adverse_events','low_level_term_id')
    }
}