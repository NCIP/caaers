class AddAssignedIdentifierToReport extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		addColumn("report_schedules", "submission_message", "string")
		addColumn("report_schedules", "submission_url", "string")
	
		addColumn("report_versions", "assigned_identifer", "string")
		addColumn("report_versions", "submission_message", "string")
		addColumn("report_versions", "submission_url", "string")
				
	}

	void down() {
		dropColumn("report_schedules", "submission_message")
		dropColumn("report_schedules", "submission_url")
		
		dropColumn("report_versions", "assigned_identifer")
		dropColumn("report_versions", "submission_message")
		dropColumn("report_versions", "submission_url")		
		
	}
}