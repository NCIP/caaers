class CreateSiteResearchStaffs extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('site_research_staffs') { t ->           
            t.addColumn('site_id', 'integer', nullable:false)
            t.addColumn('researchstaff_id', 'integer', nullable:false)
            t.addColumn('email_address', 'string')
            t.addColumn('phone_number', 'string')
            t.addColumn('fax_number', 'string')
            t.addColumn('grid_id', 'string', nullable:true)
            t.addColumn('street', 'string')
        	t.addColumn('city', 'string')
	        t.addColumn('state', 'string')
	        t.addColumn('zip', 'integer')
	        t.addColumn('code', 'integer', defaultValue: 0)
            t.addVersionColumn()
        }
        execute("update site_research_staffs set code = 0");
        execute('alter table site_research_staffs add constraint fk_srstaffs_researchstaffs foreign key (researchstaff_id) references research_staffs (id)');
        execute('alter table site_research_staffs add constraint fk_srstaffs_orgs foreign key (site_id) references organizations (id)');
    }

    void down() {
        dropTable('site_research_staffs')
    }
}