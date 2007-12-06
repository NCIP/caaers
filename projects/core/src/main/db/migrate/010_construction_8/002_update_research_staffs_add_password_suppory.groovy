class UpdateResearchStaffsAddPasswordSupport extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        dropTable("caaers_user")
        addColumn("research_staffs", "salt", "string", limit: 127)
        addColumn("research_staffs", "token", "string", limit: 127)
        addColumn("research_staffs", "token_time", "timestamp")
        addColumn("research_staffs", "password_last_set", "timestamp")
        addColumn("research_staffs", "num_failed_logins", "integer")
        createTable("password_history") { t ->
            t.includePrimaryKey = false
            t.addColumn("user_id", "integer", nullable: false)
            t.addColumn("password", "string")
            t.addColumn("list_index", "integer", nullable: false, defaultValue: 0)
        }
        execute("ALTER TABLE password_history ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES research_staffs")
    }

    void down() {
        dropColumn("research_staffs", "salt")
        dropColumn("research_staffs", "token")
        dropColumn("research_staffs", "token_time")
        dropColumn("research_staffs", "password_last_set")
        dropColumn("research_staffs", "password_history")
        dropColumn("research_staffs", "num_failed_logins")
        dropTable("password_history")
    }
}
