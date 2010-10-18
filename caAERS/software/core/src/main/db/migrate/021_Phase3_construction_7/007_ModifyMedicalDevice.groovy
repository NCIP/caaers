class ModifyMedicalDevices extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("ae_medical_devices", "study_device_id", "integer")
        execute('alter table ae_medical_devices add constraint fk_mdvc_stdy_dvc_id foreign key (study_device_id) references study_devices (id)')
    }

    void down(){
        dropColumn("ae_medical_devices", "study_device_id")
    }
}
