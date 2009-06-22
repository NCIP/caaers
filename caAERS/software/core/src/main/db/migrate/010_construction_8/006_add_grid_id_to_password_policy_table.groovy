class AddGridIdToPasswordPolicyTable extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("password_policy", "grid_id", "string")
    }

    void down() {
        dropColumn("password_policy", "grid_id")
    }
}
