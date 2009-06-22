class ChangeContactMechanisms extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	addColumn('contact_mechanisms','reporter_id' , 'string' , nullable:true);
    	addColumn('contact_mechanisms','physician_id' , 'string' , nullable:true); 
    	removeColumn('contact_mechanisms','person_id');      
    }

    void down() {
        removeColumn('contact_mechanisms','reporter_id');
    	removeColumn('contact_mechanisms','physician_id');
    	addColumn('contact_mechanisms','person_id' , 'string'); // can't re-add as not null 
    }
}