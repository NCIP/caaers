class UpdateReportDefinitions extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
         addColumn("report_calendar_templates","amendable", 'boolean', nullable: false);
    }
      
    void down() {
        dropColumn("report_calendar_templates","amendable");
    }
}
