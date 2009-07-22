class ModifyReportDefinition extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
		//report_type_id
		dropColumn("REPORT_CALENDAR_TEMPLATES","report_type_id")
	}
	void down(){
		addColumn("REPORT_CALENDAR_TEMPLATES","report_type_id", "integer")
		execute('alter table REPORT_CALENDAR_TEMPLATES add constraint fk_rct_type_id_cfg_props_id foreign key (report_type_id) references config_properties (id)')
	}
}