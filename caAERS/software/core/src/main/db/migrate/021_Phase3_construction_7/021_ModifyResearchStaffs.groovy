class ModifyResearchStaffs extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {

        dropColumn("research_staffs", "salt")
        dropColumn("research_staffs", "token")
        dropColumn("research_staffs", "token_time")
        dropColumn("research_staffs", "password_last_set")
        dropColumn("research_staffs", "num_failed_logins")
        dropColumn("research_staffs", "last_login")
    }

    void down() {
    }
}