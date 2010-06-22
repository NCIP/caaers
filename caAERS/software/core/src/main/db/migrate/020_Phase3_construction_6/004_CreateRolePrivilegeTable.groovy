class CreateRolePrivilegeTable extends edu.northwestern.bioinformatics.bering.Migration {
	
    void up() {
 		createTable("role_privilege") { t ->
            t.addVersionColumn()
            t.addColumn("role_name", "string", nullable:false)
            t.addColumn("object_id", "string", nullable:false)
            t.addColumn("privilege", "string", nullable:false)
        }
    }

    void down() {
    }
}