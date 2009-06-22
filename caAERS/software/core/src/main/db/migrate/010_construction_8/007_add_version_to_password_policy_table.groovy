class AddVersionToPasswordPolicyTable extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("password_policy", "version", "integer", defaultValue: 0, nullable: false)
    }

    void down() {
        dropColumn("password_policy", "version")
    }
}
