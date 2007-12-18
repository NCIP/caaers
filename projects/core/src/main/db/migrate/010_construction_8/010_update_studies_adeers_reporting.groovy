class updateStudyWithAdeersReportingColumn  extends edu.northwestern.bioinformatics.bering.Migration {


    public void up(){
    	if (databaseMatches('oracle')) {
    			addColumn("studies", "adeers_reporting", "boolean", nullable: false, defaultValue: 1)
        } else {
        		addColumn("studies", "adeers_reporting", "boolean", nullable: false, defaultValue: true)
        }
    }


    public void down(){
        dropColumn("studies", "adeers_reporting")
    }

  }
