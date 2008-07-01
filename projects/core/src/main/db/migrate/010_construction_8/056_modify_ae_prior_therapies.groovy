class ModifyAdverseEventPriorTherapies extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
      addColumn("ae_prior_therapies","start_date_day", "integer")
      addColumn("ae_prior_therapies","start_date_month", "integer")
      addColumn("ae_prior_therapies","start_date_year", "integer")
      addColumn("ae_prior_therapies","start_date_zone", "integer", nullable: false, defaultValue: 0)
      execute("update ae_prior_therapies set start_date_zone = 0");
   	  execute("update ae_prior_therapies set start_date_day= to_number(to_char(start_date,'DD'),'99'), start_date_month=to_number(to_char(start_date,'MM'),'99'), start_date_year=to_number(to_char(start_date,'YYYY'),'9999')")
   	  
      dropColumn("ae_prior_therapies","start_date");
      
      
      addColumn("ae_prior_therapies","end_date_day", "integer")
      addColumn("ae_prior_therapies","end_date_month", "integer")
      addColumn("ae_prior_therapies","end_date_year", "integer")
      addColumn("ae_prior_therapies","end_date_zone", "integer", nullable: false, defaultValue: 0)
      execute("update ae_prior_therapies set end_date_zone = 0");
      execute("update ae_prior_therapies set end_date_day= to_number(to_char(end_date,'DD'),'99'), end_date_month=to_number(to_char(end_date,'MM'),'99'), end_date_year=to_number(to_char(end_date,'YYYY'),'9999')")
      	  
      dropColumn("ae_prior_therapies","end_date");
      
    }

    void down() {
     addColumn("ae_prior_therapies","start_date_date", "date")
     dropColumn("ae_prior_therapies","start_date_day");
     dropColumn("ae_prior_therapies","start_date_month");
     dropColumn("ae_prior_therapies","start_date_year");
     
     addColumn("ae_prior_therapies","end_date_date", "date")
     dropColumn("ae_prior_therapies","end_date_day");
     dropColumn("ae_prior_therapies","end_date_month");
     dropColumn("ae_prior_therapies","end_date_year");
     
    }
}
