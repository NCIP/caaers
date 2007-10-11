class AddDeviceAttribution extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        insert("ae_cause_types", [code: "DV", name: "device"], primaryKey: false)
    }

    void down() {
        execute("DELETE FROM ae_attributions WHERE cause_type='DV'")
        execute("DELETE FROM ae_cause_types WHERE code='DV'")
    }
}
