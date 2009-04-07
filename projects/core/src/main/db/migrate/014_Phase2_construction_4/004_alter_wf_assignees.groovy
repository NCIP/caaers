class AlterWF_Assignees extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("wf_assignees","investigator_id", "integer")
        addColumn("wf_assignees","researchstaff_id", "integer")

        execute('alter table wf_assignees add constraint fk_wfassignee_investigator_id foreign key (investigator_id) references investigators (id)')
        execute('alter table wf_assignees add constraint fk_wfassignee_researchstaff_id foreign key (researchstaff_id) references research_staffs (id)')

		execute('update wf_assignees set investigator_id = (select id from investigators where id=user_id)')
		execute('update wf_assignees set researchstaff_id = (select id from research_staffs where id=user_id)')      
        
        dropColumn("wf_assignees", "user_id")
    }

    void down() {
        dropTable("wf_assignees")
    }
}