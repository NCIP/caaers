class MigrateMedicalDevices extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
       execute("update ae_medical_devices set device_reprocessed_code = 3 where device_reprocessed_code = 4");
    }
    void down(){
      //not possible
    }
}