class CreateStudyInvestigators extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	addColumn('study_investigators','role_code' , 'string' , nullable:true);
    	addColumn('study_investigators','status_code' , 'string' , nullable:true);       
    }

    void down() {
        removeColumn('study_investigators','role_code');
    	removeColumn('study_investigators','status_code');
    }
}