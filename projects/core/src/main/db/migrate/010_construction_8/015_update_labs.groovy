class updateSAeLabs  extends edu.northwestern.bioinformatics.bering.Migration {


    public void up(){
    	addColumn("ae_labs", "lab_term_id", "integer")
    	addColumn("ae_labs", "site", "string")
    	addColumn("ae_labs", "lab_date", "date")
    	addColumn("ae_labs", "infectious_agent", "string")
    	dropColumn("ae_labs", "name")
    }


    public void down(){
        dropColumn("ae_labs", "lab_term_id")
        dropColumn("ae_labs", "site")
        dropColumn("ae_labs", "lab_date")
        dropColumn("ae_labs", "infectious_agent")
        
    }

  }
