class ModifyStudyPersonnel extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	addColumn("study_personnel","site_research_staffs_id", "integer");
    	execute('alter table study_personnel add constraint fk_spersonnel_srstaffs foreign key (site_research_staffs_id) references site_research_staffs (id)');
    }

    void down() {
    	 dropColumn("study_personnel", "site_research_staffs_id");
    }
}