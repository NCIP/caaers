class AddAssignedIdentifierToReport extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		addColumn("report_schedules", "assigned_identifer", "string")
	}

	void down() {
		dropColumn("report_schedules", "assigned_identifer")
	}
}