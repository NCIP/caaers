class AlterWFTransitionOwners extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("wf_transition_owners","investigator_id", "integer")
        addColumn("wf_transition_owners","researchstaff_id", "integer")

        execute('alter table wf_transition_owners add constraint fk_wftowner_investigator_id foreign key (investigator_id) references investigators (id)')
        execute('alter table wf_transition_owners add constraint fk_wftowner_researchstaff_id foreign key (researchstaff_id) references research_staffs (id)')

		execute('update wf_transition_owners set investigator_id = (select id from investigators where id=user_id)')
		execute('update wf_transition_owners set researchstaff_id = (select id from research_staffs where id=user_id)')      
        
        dropColumn("wf_transition_owners", "user_id")
    }

    void down() {
        dropTable("wf_transition_owners")
    }
}