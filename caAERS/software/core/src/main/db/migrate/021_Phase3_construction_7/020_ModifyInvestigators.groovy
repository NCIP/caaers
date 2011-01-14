class ModifyInvestigators extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        
        dropColumn("investigators", "salt")
        dropColumn("investigators", "token")
        dropColumn("investigators", "token_time")
        dropColumn("investigators", "password_last_set")
        dropColumn("investigators", "num_failed_logins")
        dropColumn("investigators", "last_login")
    }

    void down() {
    }
}