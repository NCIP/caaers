class MoreAttributions extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        insert("ae_cause_types", [code: "DH", name: "disease"], primaryKey: false)
        insert("ae_cause_types", [code: "SI", name: "surgery"], primaryKey: false)
        insert("ae_cause_types", [code: "RI", name: "radiation"], primaryKey: false)
    }

    void down() {
        execute("DELETE FROM ae_attributions WHERE cause_type='DH'")
        execute("DELETE FROM ae_cause_types WHERE code='DH'")
        execute("DELETE FROM ae_attributions WHERE cause_type='SI'")
        execute("DELETE FROM ae_cause_types WHERE code='SI'")
        execute("DELETE FROM ae_attributions WHERE cause_type='RI'")
        execute("DELETE FROM ae_cause_types WHERE code='RI'")
    }
}