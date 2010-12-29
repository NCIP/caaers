class ModifyInvestigator extends edu.northwestern.bioinformatics.bering.Migration {
	
    void up() {
		addColumn("investigators","user_id", "integer")
		execute('alter table investigators add constraint fk_inv_caaers_users foreign key (user_id) references caaers_users (id)')
    }

    void down() {
    }
}