class CreateCaaersUserTable extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
	createTable("caaers_user") {  t -> 
            t.addColumn("name", "string", nullable: false, limit: 255)
            t.addColumn("salt", "string", limit: 127)
            t.addColumn("token", "string", limit: 127)
            t.addColumn("token_time", "timestamp")
            t.addColumn("password_last_set", "timestamp")
            t.addColumn("password_history", "string")
            t.addColumn("num_failed_logins", "integer")
 	}
    }
    
    void down() {
	dropTable("caaers_user")
    }
    
}
