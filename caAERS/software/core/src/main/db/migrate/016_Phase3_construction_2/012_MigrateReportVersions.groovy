class MigrateReportVersions extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
		execute("update report_versions set amendment_number = to_number(report_version_id,'99')")
	}
	void down(){
		//not required
	}
} 

