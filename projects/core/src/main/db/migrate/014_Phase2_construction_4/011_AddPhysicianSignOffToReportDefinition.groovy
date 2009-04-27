class AddPhysicianSignOffToReportDefinition extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
         addColumn("report_calendar_templates","physician_signoff", "boolean",defaultValue: 0, nullable: false);
         
    }
      
    void down() {
        dropColumn("report_calendar_templates","physician_signoff");
    }
}