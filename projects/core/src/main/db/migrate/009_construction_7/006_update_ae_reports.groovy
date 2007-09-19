class UpdateConcomitantMedications extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
         dropColumn("ae_reports","detection_date");
    }
      
    void down() {
        addColumn("ae_reports","detection_date", 'date');
    }
}