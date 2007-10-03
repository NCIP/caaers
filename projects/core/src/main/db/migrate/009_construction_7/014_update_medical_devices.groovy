class updateMedicalDevices extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
         execute("DELETE FROM medical_devices ");
         addColumn('medical_devices','list_index','integer', nullable:true)
         renameTable('medical_devices','ae_medical_devices');
         
    }
      
    void down() {
    	renameTable('ae_medical_devices','medical_devices');
        dropColumn("medical_devices","list_index");
    }
}
