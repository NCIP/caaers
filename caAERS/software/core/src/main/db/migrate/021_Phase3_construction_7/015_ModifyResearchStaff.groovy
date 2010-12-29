class ModifyResearchStaff extends edu.northwestern.bioinformatics.bering.Migration {
	
    void up() {
		addColumn("research_staffs","user_id", "integer")
		execute('alter table research_staffs add constraint fk_rs_caaers_users foreign key (user_id) references caaers_users (id)')
    }

    void down() {
    }
}