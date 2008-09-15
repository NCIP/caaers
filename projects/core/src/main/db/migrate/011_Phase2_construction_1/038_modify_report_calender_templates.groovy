class ModifyReportCalender extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		execute('update report_calendar_templates set label = name where label is null');
	}
	
	void down() {
		
	}
}