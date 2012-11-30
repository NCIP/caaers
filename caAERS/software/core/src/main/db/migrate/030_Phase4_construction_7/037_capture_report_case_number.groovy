class CaptureReportCaseNumber extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
	 addColumn('REPORT_SCHEDULES','case_number','string');
	}
	void down(){
		dropColumn('REPORT_SCHEDULES','case_number');
	}
}