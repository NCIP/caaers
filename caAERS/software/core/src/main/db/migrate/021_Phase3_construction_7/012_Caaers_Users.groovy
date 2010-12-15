class CaaersUsers extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
      createTable("caaers_users") { t ->
          t.addVersionColumn()
          t.addColumn("login_name", "string", nullable:false)
		  t.addColumn("salt", "string", limit: 127)
          t.addColumn("token", "string", limit: 127)
          t.addColumn("token_time", "timestamp")
          t.addColumn("password_last_set", "timestamp")
          t.addColumn("last_login", "timestamp")
          t.addColumn("num_failed_logins", "integer", nullable: false, defaultValue: 0)
          t.addColumn('grid_id' , 'string' , nullable:true);
      }
    }

    void down() {
        dropTable("caaers_users")
    }
}
