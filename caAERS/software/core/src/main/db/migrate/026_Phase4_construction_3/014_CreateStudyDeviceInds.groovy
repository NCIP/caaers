class CreateStudyDeviceIndAssociationTab extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('study_device_inds') { t ->
            t.addColumn('study_device_id' , 'integer');
            t.addColumn('ind_id', 'integer')
            t.addVersionColumn()
            t.addColumn('grid_id' , 'string' , nullable:true);
        }


        //fk
        execute("alter table study_device_inds add constraint fk_sdis_study_device_id FOREIGN KEY(study_device_id) references study_devices(id)");
        execute("alter table study_device_inds add constraint fk_sdis_ind_id FOREIGN KEY(ind_id) references investigational_new_drugs(id)");


    }

    void down() {
        dropTable("study_device_inds")
    }
}