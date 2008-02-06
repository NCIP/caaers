class updateSaeDescription  extends edu.northwestern.bioinformatics.bering.Migration {


    public void up(){
    	dropColumn("ae_radiation_interventions", "treatment_arm")
        dropColumn("ae_radiation_interventions", "description")
    }


    public void down(){
    	addColumn("ae_radiation_interventions","treatment_arm", "string")
    	addColumn("ae_radiation_interventions","description", "string")   
    }

  }
