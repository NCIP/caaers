class ModifyReportDefinition extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
		addColumn("REPORT_CALENDAR_TEMPLATES","group_id", "integer")
		addColumn("REPORT_CALENDAR_TEMPLATES","report_type", "integer")
	 	execute('alter table REPORT_CALENDAR_TEMPLATES add constraint fk_rct_group_id_cfg_props_id foreign key (group_id) references config_properties (id)')
	}
	void down(){
		dropColumn("REPORT_CALENDAR_TEMPLATES","group_id")
		dropColumn("REPORT_CALENDAR_TEMPLATES","report_type")
	 	
	}
}