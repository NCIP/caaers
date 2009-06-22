class ModifySiteInvestigatorMiddleName extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
   	  	addColumn('research_staffs','middle_name',"string", nullable:true);
   	  	addColumn('investigators','middle_name',"string", nullable:true);
     }
    void down() {
    	removeColumn('research_staffs','middle_name'); 
    	removeColumn('investigators','middle_name');  
    }
}