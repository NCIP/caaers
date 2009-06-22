class UpdateAdverseEvents extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
 		addColumn("adverse_events", "start_date" , 'date');
        addColumn("adverse_events", "end_date" , 'date');
    }
      

    void down() {
        dropColumn("adverse_events", "start_date");
        dropColumn("adverse_events", "end_date");
    }
}