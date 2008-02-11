class updateStudyDcpDesignColumn  extends edu.northwestern.bioinformatics.bering.Migration {


    public void up(){
    	addColumn("studies","design_code", "integer")
    }


    public void down(){
        dropColumn("studies", "design_code")
    }

  }
