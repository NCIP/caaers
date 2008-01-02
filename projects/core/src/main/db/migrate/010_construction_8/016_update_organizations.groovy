class updateSAeLabs  extends edu.northwestern.bioinformatics.bering.Migration {


    public void up(){
    	addColumn("organizations", "city", "string")
    	addColumn("organizations", "state", "string")
    	addColumn("organizations", "country", "string")
    }


    public void down(){
        dropColumn("organizations", "city")
        dropColumn("organizations", "state")
        dropColumn("organizations", "country")
    }

  }