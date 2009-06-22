class ModifyDiseaseHistories extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
      addColumn("disease_histories","diagnosis_day", "integer")
      addColumn("disease_histories","diagnosis_month", "integer")
      addColumn("disease_histories","diagnosis_year", "integer")
      addColumn("disease_histories","diagnosis_zone", "integer", nullable: false, defaultValue: 0)
      execute("update disease_histories set diagnosis_zone = 0");
   	  execute("update disease_histories set diagnosis_day= to_number(to_char(diagnosis_date,'DD'), '99'), diagnosis_month=to_number(to_char(diagnosis_date,'MM'),'99'), diagnosis_year=to_number(to_char(diagnosis_date,'YYYY'), '9999')")
      dropColumn("disease_histories","diagnosis_date");
    }

    void down() {
     addColumn("disease_histories","diagnosis_date", "date")
     dropColumn("disease_histories","diagnosis_day");
     dropColumn("disease_histories","diagnosis_month");
     dropColumn("disease_histories","diagnosis_year");
    }
}
