class CreateStudyDevice extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("study_devices") { t ->
            t.addVersionColumn()
            t.addColumn("brand_name", "string")
            t.addColumn("common_name", "string")
            t.addColumn("catalog_number", "string")
            t.addColumn("manufacturer_name", "string")
            t.addColumn("manufacturer_city", "string")
            t.addColumn("manufacturer_state", "string")
            t.addColumn("model_number", "string")
            t.addColumn("device_type", "string")
            t.addColumn("device_id", "integer")
            t.addColumn('study_id', 'integer')
            t.addColumn('retired_indicator', 'boolean', defaultValue: 0)
            t.addColumn("grid_id", "string")
        }

        execute('alter table study_devices add constraint fk_stdy_dvc_dvc_id foreign key (device_id) references devices (id)')
        execute('alter table study_devices add constraint fk_stdy_dvc_stdy_id foreign key (study_id) references studies (id)')
    }

    void down() {
        dropTable("study_devices")
    }
}

