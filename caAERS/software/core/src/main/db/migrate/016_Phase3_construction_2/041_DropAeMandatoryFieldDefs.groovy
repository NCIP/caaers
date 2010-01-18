class DropAeReportMandatoryFieldDefs extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        dropTable('ae_mandatory_field_defs')
    }

    void down() {
    }
}