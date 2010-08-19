class DeleteRolePrivilegeData extends edu.northwestern.bioinformatics.bering.Migration {
	
    void up() {

        execute("delete from role_privilege");

    }

    void down() {
      // not required
    }
}