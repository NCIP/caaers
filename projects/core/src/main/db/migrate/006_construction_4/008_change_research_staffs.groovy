class ModifyResearchStaffs extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        removeColumn('research_staffs','middle_name');  
        removeColumn('research_staffs','maiden_name');
  	    removeColumn('research_staffs','birth_date');    
  	    removeColumn('research_staffs','gender');  
  	    removeColumn('research_staffs','ethnicity');  
  	    removeColumn('research_staffs','race');  
  	  }
    
    void down() {
         addColumn('research_staffs','middle_name',"string",nullable:false);
         addColumn('research_staffs','maiden_name',"string",nullable:false);
         addColumn('research_staffs','birth_date',"date",nullable:false); 
         addColumn('research_staffs','gender',"string",nullable:false);
         addColumn('research_staffs','ethnicity',"string",nullable:false);  
         addColumn('research_staffs','race',"string",nullable:false);    
     }
}