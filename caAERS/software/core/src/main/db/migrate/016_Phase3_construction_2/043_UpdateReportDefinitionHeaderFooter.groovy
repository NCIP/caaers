class ModifyReportDefinition extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
        addColumn("REPORT_CALENDAR_TEMPLATES", "header", "string")
        addColumn("REPORT_CALENDAR_TEMPLATES", "footer", "string")
	}
	void down(){
      dropColumn("REPORT_CALENDAR_TEMPLATES", "header")
      dropColumn("REPORT_CALENDAR_TEMPLATES", "footer")
	}
}
