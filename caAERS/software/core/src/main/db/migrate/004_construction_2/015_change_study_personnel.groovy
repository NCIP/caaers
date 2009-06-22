class CreateStudyPersonnel extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	addColumn('study_personnel','role_code' , 'string' , nullable:true);
    	addColumn('study_personnel','status_code' , 'string' , nullable:true);       
    }

    void down() {
        removeColumn('study_personnel','role_code');
    	removeColumn('study_personnel','status_code');
    }
}