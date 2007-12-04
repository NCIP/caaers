class CreateCaaersUserTable extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
	createTable("caaers_user") {  t ->
            t.addColumn("user_fk", "integer", nullable: false)
            t.addColumn("salt", "string", limit: 127)
            t.addColumn("token", "string", limit: 127)
            t.addColumn("token_time", "timestamp")
            t.addColumn("password_last_set", "timestamp")
            t.addColumn("password_history", "string")
            t.addColumn("num_failed_logins", "integer")
 	}
        execute("ALTER TABLE caaers_user ADD CONSTRAINT fk_csm_user FOREIGN KEY (user_fk) REFERENCES csm_user")
    }
    
    void down() {
	dropTable("caaers_user")
    }
    
}
