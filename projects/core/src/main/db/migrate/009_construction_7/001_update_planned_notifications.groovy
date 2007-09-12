class UpdatePlannedNotifications extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
 
         dropColumn("planned_notifications","from_addr");
    }
      

    void down() {
        addColumn("planned_notifications","from_addr", 'string');
    }
}