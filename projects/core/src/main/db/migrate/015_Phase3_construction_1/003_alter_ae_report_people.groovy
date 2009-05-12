class ModifyReportPerson extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
	 	
	 	addColumn("ae_report_people","investigator_id", "integer")
        addColumn("ae_report_people","researchstaff_id", "integer")

        execute('alter table ae_report_people add constraint fk_rp_people_investigator_id foreign key (investigator_id) references investigators (id)')
        execute('alter table ae_report_people add constraint fk_rp_people_researchstaff_id foreign key (researchstaff_id) references research_staffs (id)')

		execute('update ae_report_people set investigator_id = (select id from investigators where id=user_id)')
		execute('update ae_report_people set researchstaff_id = (select id from research_staffs where id=user_id)')      
        
        dropColumn("ae_report_people", "user_id")
	}
	void down(){
		addColumn("ae_report_people", "user_id", "integer")
		
		execute('update ae_report_people set user_id = investigator_id where investigator_id is not null')
		execute('update ae_report_people set user_id = researchstaff_id where researchstaff_id is not null')
		
		dropColumn("ae_report_people","investigator_id")
		dropColumn("ae_report_people","researchstaff_id")
		
	}
}