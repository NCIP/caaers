class ModifyReportVersions extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("report_versions","amendment_number", "integer", defaultValue: 0)
		
    }

    void down() {
        dropColumn("report_versions", "amendment_number")
    }
}