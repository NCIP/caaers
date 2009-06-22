class ModifyInvestigators extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        removeColumn('investigators','middle_name');  
        removeColumn('investigators','maiden_name');
  	    removeColumn('investigators','birth_date');    
  	    removeColumn('investigators','gender');  
  	    removeColumn('investigators','ethnicity');  
  	    removeColumn('investigators','race');    
    }
    
    void down() {
         addColumn('investigators','middle_name',"string",nullable:false);
         addColumn('investigators','maiden_name',"string",nullable:false);
         addColumn('investigators','birth_date',"date",nullable:false); 
         addColumn('investigators','gender',"string",nullable:false);
         addColumn('investigators','ethnicity',"string",nullable:false);  
         addColumn('investigators','race',"string",nullable:false);  
    }
}