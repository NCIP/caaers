class ChangeStudyDiseases extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	addColumn('study_diseases','term_id' , 'integer' , nullable:true);
    	addColumn('study_diseases','term_type' , 'string' , nullable:true);
    	addColumn('study_diseases','meddra_code' , 'string' , nullable:true); 
        execute("update study_diseases set term_id=disease_term_id, term_type='ctep' where  term_type is null");
        removeColumn('study_diseases','disease_term_id');      
    }

    void down() {
        removeColumn('study_diseases','term_id');
    	removeColumn('study_diseases','term_type');
    	addColumn('study_diseases','disease_term_id' , 'integer'); // can't recreate as non null 
    }
}