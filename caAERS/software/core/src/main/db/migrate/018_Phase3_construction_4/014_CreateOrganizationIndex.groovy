class CreateOrgIndex extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("organization_index") { t ->
            t.addVersionColumn()
            t.addColumn("login_id", "string")
            t.addColumn("organization_id", "integer", nullable: false)
            t.addColumn("grid_id", "string")
        }
         execute('alter table organization_index add constraint fk_org_index_org_id foreign key (organization_id) references organizations (id)')
    }

    void down() {
        dropTable("organization_index")
    }
}