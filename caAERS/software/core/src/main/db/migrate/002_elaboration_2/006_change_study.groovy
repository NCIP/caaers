class CreateStudy extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	addColumn('studies','blinded_indicator' , 'boolean' , nullable:true);
    	addColumn('studies','randomized_indicator' , 'boolean' , nullable:true);
        addColumn('studies','precis' , 'string' , nullable:true);
        addColumn('studies','disease_code' , 'string' , nullable:true);
        addColumn('studies','monitor_code' , 'string' , nullable:true);
        addColumn('studies','status' , 'string' , nullable:true);
        addColumn('studies','target_accrual_number' , 'integer' , nullable:true);
        removeColumn('studies','principal_investigator_code');
        removeColumn('studies','principal_investigator_name');
        removeColumn('studies','primary_sponsor_name');        
        removeColumn('studies','review_date');  
    }

    void down() {
        addColumn('studies','principal_investigator_code',"string",nullable:false);
        addColumn('studies','principal_investigator_name',"string",nullable:false);
        addColumn('studies','primary_sponsor_name',"string",nullable:false);
        addColumn('studies','review_date',"date",nullable:true);
        removeColumn('studies','blinded_indicator');
    	removeColumn('studies','randomized_indicator');
        removeColumn('studies','precis');
        removeColumn('studies','disease_code');
        removeColumn('studies','monitor_code');
        removeColumn('studies','status');
        removeColumn('studies','target_accrual_number');
    }
}