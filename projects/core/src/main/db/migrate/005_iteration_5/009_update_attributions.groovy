class UpdateAttributions extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        // Study agent attributions are invalid
        execute("DELETE FROM ae_attributions WHERE cause_type='SA'")
        execute("DELETE FROM ae_cause_types WHERE code='SA'")

        insert("ae_cause_types", [code: "CA", name: "course agent"], primaryKey: false)
        insert("ae_cause_types", [code: "OC", name: "other causes"], primaryKey: false)
    }

    void down() {
        insert("ae_cause_types", [code: "SA", name: "study agent"], primaryKey: false)
        execute("DELETE FROM ae_attributions WHERE cause_type='CA'")
        execute("DELETE FROM ae_cause_types WHERE code='CA'")
        execute("DELETE FROM ae_attributions WHERE cause_type='OC'")
        execute("DELETE FROM ae_cause_types WHERE code='OC'")
    }
}