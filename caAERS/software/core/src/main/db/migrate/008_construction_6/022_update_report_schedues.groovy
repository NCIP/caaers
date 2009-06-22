class UpdateReportSchedules extends edu.northwestern.bioinformatics.bering.Migration {
    
    void up() {
            addColumn("report_schedules","email", 'string');
    }

    void down() {
    		dropColumn("report_schedules","email")
    }
    
}