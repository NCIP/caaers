class AlterReportVersions extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
	if (databaseMatches('postgresql')){
         execute('ALTER TABLE report_versions ALTER created_on TYPE timestamp without time zone')
         execute('ALTER TABLE report_versions ALTER due_on TYPE timestamp without time zone')
         execute('ALTER TABLE report_versions ALTER submitted_on TYPE timestamp without time zone')
         execute('ALTER TABLE report_versions ALTER withdrawn_on TYPE timestamp without time zone')
     }else if(databaseMatches('oracle')){
     	execute('alter table report_versions rename column due_on to due_on_old')
		execute('alter table report_versions add (due_on timestamp)')
		execute('update report_versions set due_on = cast(due_on_old as timestamp)')
		execute('alter table report_versions drop (due_on_old)')

		execute('alter table report_versions rename column created_on to created_on_old')
		execute('alter table report_versions add (created_on timestamp)')
		execute('update report_versions set created_on = cast(created_on_old as timestamp)')
		execute('alter table report_versions drop (created_on_old)')

		execute('alter table report_versions rename column submitted_on to submitted_on_old')
		execute('alter table report_versions add (submitted_on timestamp)')
		execute('update report_versions set submitted_on = cast(submitted_on_old as timestamp)')
		execute('alter table report_versions drop (submitted_on_old)')

		execute('alter table report_versions rename column withdrawn_on to withdrawn_on_old')
		execute('alter table report_versions add (withdrawn_on timestamp)')
		execute('update report_versions set withdrawn_on = cast(withdrawn_on_old as timestamp)')
		execute('alter table report_versions drop (withdrawn_on_old)')
     }
		
	}
	void down(){
		//NOT REQUIRED
	}
}