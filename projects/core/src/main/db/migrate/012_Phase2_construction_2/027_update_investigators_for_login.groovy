class UpdateInvestigatorsAddSupport extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("investigators", "salt", "string", limit: 127)
        addColumn("investigators", "login_id", "string", limit: 127)
        addColumn("investigators", "token", "string", limit: 127)
        addColumn("investigators", "token_time", "timestamp")
        addColumn("investigators", "password_last_set", "timestamp")
        addColumn("investigators", "num_failed_logins", "integer", nullable: false, defaultValue: 0)
    }

    void down() {
        dropColumn("investigators", "salt")
        dropColumn("investigators", "login_id")
        dropColumn("investigators", "token")
        dropColumn("investigators", "token_time")
        dropColumn("investigators", "password_last_set")
        dropColumn("investigators", "num_failed_logins")
    }
}