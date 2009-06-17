class ModifyReportDefinition extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
	 	addColumn("REPORT_CALENDAR_TEMPLATES","report_type_id", "integer")
	 	addColumn("REPORT_CALENDAR_TEMPLATES","parent_id", "integer")
	 	execute('alter table REPORT_CALENDAR_TEMPLATES add constraint fk_rct_type_id_cfg_props_id foreign key (report_type_id) references config_properties (id)')
	 	execute('alter table REPORT_CALENDAR_TEMPLATES add constraint fk_rct_parent_id foreign key (parent_id) references REPORT_CALENDAR_TEMPLATES (id)')
	}
	void down(){
		dropColumn("REPORT_CALENDAR_TEMPLATES","report_type_id")
		dropColumn("REPORT_CALENDAR_TEMPLATES","parent_id")
	}
}