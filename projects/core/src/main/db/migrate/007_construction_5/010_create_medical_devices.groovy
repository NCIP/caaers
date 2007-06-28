class CreateMedicalDevices extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    
    	createTable("medical_devices") { t ->
            t.addColumn("grid_id", "string")
            t.addVersionColumn();
            t.addColumn("report_id", "integer", nullable: false)
            t.addColumn("brand_name", "string")
            t.addColumn("common_name", "string")
            t.addColumn("device_type", "string")
            t.addColumn("manufacturer_name", "string")
            t.addColumn("manufacturer_city", "string")
            t.addColumn("manufacturer_state", "string")
            t.addColumn("model_number","string")
            t.addColumn("lot_number", "string")
            t.addColumn("catalog_number", "string")
            t.addColumn("expiration_date", "date")
            t.addColumn("serial_number", "string")
            t.addColumn("other_number", "string")
            t.addColumn("device_operator_code", "integer")
            t.addColumn("other_device_operator", "string")
            t.addColumn("implanted_date", "date")
            t.addColumn("explanted_date", "date")
            t.addColumn("device_reprocessed_code", "integer")
            t.addColumn("reprocessor_name", "string")
            t.addColumn("reprocessor_address", "string")
            t.addColumn("evaluation_availability_code", "integer")
            t.addColumn("returned_date", "date")
                         
        }
    }

    void down() {
        dropTable('medical_devices')
    }
}