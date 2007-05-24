class AddAeResponseDescription extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        dropColumn("ae_report_descriptions", "removed_from_protocol")
    }

    void down() {
        addColumn("ae_report_descriptions", "removed_from_protocol", "boolean")
    }
}