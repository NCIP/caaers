class UpdateReportDefinitions extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
         addColumn("report_calendar_templates","attribution_required", 'boolean');
         
    }
      
    void down() {
        dropColumn("report_calendar_templates","attribution_required");
    }
}
