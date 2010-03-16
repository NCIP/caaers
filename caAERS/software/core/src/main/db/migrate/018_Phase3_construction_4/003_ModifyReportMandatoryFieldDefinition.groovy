class ModifyReportMandatoryFieldDefinition extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("mandatory_field_defs", "rule_bindurl", "string")
        addColumn("mandatory_field_defs", "rule_name", "string")
    }

    void down() {
        dropColumn("mandatory_field_defs", "rule_bindurl")
        dropColumn("mandatory_field_defs", "rule_name")
    }
}
