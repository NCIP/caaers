class updateStudy  extends edu.northwestern.bioinformatics.bering.Migration {


    public void up(){
    	dropColumn("studies", "healthy_volunteer")
    }


    public void down(){
        addColumn("studies", "healthy_volunteer", "boolean")
    }

  }

