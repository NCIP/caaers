class CreateAdditionalInformation extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    
    	createTable("additional_information") { t ->
            t.addColumn("grid_id", "string")
            t.addVersionColumn();
            t.addColumn("report_id", "integer", nullable: false)
            t.addColumn("autopsy_report","boolean")
            t.addColumn("consults","boolean")
            t.addColumn("discharge_summary","boolean")
            t.addColumn("flow_charts","boolean")
            t.addColumn("lab_reports","boolean")
            t.addColumn("oba_form","boolean")
            t.addColumn("other","boolean")
            t.addColumn("pathology_report","boolean")
            t.addColumn("radiology_reports", "boolean")
            t.addColumn("irb_report", "boolean")
            t.addColumn("other_information","string")
            t.addColumn("progress_notes","boolean")
            t.addColumn("referral_letters","boolean")
        }
    }

    void down() {
        dropTable('additional_information')
    }
}