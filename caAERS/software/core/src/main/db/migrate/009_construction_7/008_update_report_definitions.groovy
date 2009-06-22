class UpdateReportDefinitions extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
         addColumn("report_calendar_templates","amendable", 'boolean');
         
    }
      
    void down() {
        dropColumn("report_calendar_templates","amendable");
    }
}
