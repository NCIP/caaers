class AssociatePersonAndCaaersUser extends edu.northwestern.bioinformatics.bering.Migration {
	
    void up() {
    
		execute('update research_staffs rs set user_id = (select id from caaers_users cu where cu.login_name = rs.login_id)')
		execute('update investigators inv set user_id = (select id from caaers_users cu where cu.login_name = inv.login_id)')
    }

    void down() {
    }
}