class CreatePasswordPolicyTable extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("password_policy") { t ->
            t.addColumn("hash_algorithm", "string")
            t.addColumn("ln_allowed_attempts", "integer")
            t.addColumn("ln_lockout_duration", "integer")
            t.addColumn("ln_max_age", "integer")
            t.addColumn("cn_min_age", "integer")
            t.addColumn("cn_history_size", "integer")
            t.addColumn("cn_min_length", "integer")
            t.addColumn("cn_cb_min_required", "integer")
            t.addColumn("cn_cb_is_upper_case_alphabet", "boolean")
            t.addColumn("cn_cb_is_lower_case_alphabet", "boolean")
            t.addColumn("cn_cb_is_non_alpha_numeric", "boolean")
            t.addColumn("cn_cb_is_base_ten_digit", "boolean")
            t.addColumn("cn_cb_max_substring_length", "integer")
        }
    }

    void down() {
        dropTable("password_policy")
    }
}
