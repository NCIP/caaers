class ModifyReports extends edu.northwestern.bioinformatics.bering.Migration {
	
    void up() {
        addColumn("report_schedules","manually_selected", "boolean", defaultValue: 0)
        dropColumn("report_schedules", "due_on")
        dropColumn("report_schedules", "created_on")
        dropColumn("report_schedules", "submitted_on")
        dropColumn("report_schedules", "physician_signoff")
		
    }

    void down() {
        dropColumn("report_schedules", "manually_selected")
        addColumn("report_schedules", "physician_signoff", "boolean")
        addColumn("report_schedules","due_on","date", nullable:false)
        addColumn("report_schedules","created_on", "date", nullable:false) 
        addColumn("report_schedules","submitted_on","date", nullable:true) 
    }
}

