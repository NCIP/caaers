class UpdateReportDefinitions extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	addColumn('REPORT_CALENDAR_TEMPLATES','expedited' , 'boolean' , nullable:true);
    	addColumn('REPORT_CALENDAR_TEMPLATES','label' , 'string' , nullable:true);
    }
    void down() {
    	removeColumn("REPORT_CALENDAR_TEMPLATES", "expedited");
    	removeColumn("REPORT_CALENDAR_TEMPLATES", "label");
    }
} 