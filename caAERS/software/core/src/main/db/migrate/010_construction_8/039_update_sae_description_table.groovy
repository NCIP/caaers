class updateSaeDescription  extends edu.northwestern.bioinformatics.bering.Migration {


    public void up(){
    	addColumn("ae_report_descriptions","blind_broken", "boolean")
    	addColumn("ae_report_descriptions","study_drug_interrupted", "boolean")    	
		addColumn("ae_report_descriptions", "reduced_dose", "string")
        addColumn("ae_report_descriptions","reduced_date", "date")
        addColumn("ae_report_descriptions","days_not_given", "integer")
        addColumn("ae_report_descriptions","event_abate", "boolean")
    	addColumn("ae_report_descriptions","event_reappear", "boolean")  
    }


    public void down(){
        dropColumn("ae_report_descriptions", "blind_broken")
        dropColumn("ae_report_descriptions", "study_drug_interrupted")
        dropColumn("ae_report_descriptions", "reduced_dose")
        dropColumn("ae_report_descriptions", "reduced_date")
        dropColumn("ae_report_descriptions", "days_not_given")
        dropColumn("ae_report_descriptions", "event_abate")
        dropColumn("ae_report_descriptions", "event_reappear")        
    }

  }
