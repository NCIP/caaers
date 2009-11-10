class ModifyPasswordPolicy extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("password_policy", "allowed_login_time", "integer", defaultValue: 180)
    }

    void down() {
        dropColumn("password_policy", "allowed_login_time")
    }
}
