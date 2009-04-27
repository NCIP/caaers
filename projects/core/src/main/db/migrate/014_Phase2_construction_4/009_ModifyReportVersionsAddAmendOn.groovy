class ModifyReportVersions extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		addColumn("report_versions","amended_on", "timestamp")
	}
	
	void down() {
		dropColumn("report_versions", "amended_on");
		
	}
}