class CreateStudySites extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	addColumn('study_sites','role_code' , 'string' , nullable:true);
    	addColumn('study_sites','status_code' , 'string' , nullable:true);
        addColumn('study_sites','irb_approval_date' , 'date' , nullable:true);
        addColumn('study_sites','start_date' , 'date' , nullable:true);
        addColumn('study_sites','end_date' , 'date' , nullable:true);
    }

    void down() {
        removeColumn('study_sites','role_code');
    	removeColumn('study_sites','status_code');
        removeColumn('study_sites','irb_approval_date');
        removeColumn('study_sites','start_date');
        removeColumn('study_sites','end_date');
    }
}