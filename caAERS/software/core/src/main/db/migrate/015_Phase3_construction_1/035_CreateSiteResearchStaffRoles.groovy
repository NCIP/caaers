class CreateSiteResearchStaffRoless extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('site_rs_staff_roles') { t ->           
            t.addColumn('role_code', 'string', nullable:false)
            t.addColumn('site_research_staffs_id', 'integer', nullable:false)
            t.addColumn('start_date', 'timestamp')
            t.addColumn('end_date', 'timestamp')
            t.addColumn('grid_id', 'string', nullable:true)
            t.addVersionColumn()
        }
       execute('alter table site_rs_staff_roles add constraint fk_srstaffroles_srstaffs_id foreign key (site_research_staffs_id) references site_research_staffs (id)'); 
    }

    void down() {
        dropTable('site_rs_staff_roles')
    }
}