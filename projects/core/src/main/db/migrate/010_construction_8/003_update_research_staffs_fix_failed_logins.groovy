class UpdateResearchStaffsFixFailedLogins extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        dropColumn("research_staffs", "num_failed_logins")
        addColumn("research_staffs", "num_failed_logins", "integer", nullable: false, defaultValue: 0)
    }

    void down() {
        dropColumn("research_staffs", "num_failed_logins")
        addColumn("research_staffs", "num_failed_logins", "integer")
    }
}
