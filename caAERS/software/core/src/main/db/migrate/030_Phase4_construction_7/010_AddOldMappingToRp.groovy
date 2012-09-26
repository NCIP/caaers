class AddOldMapping extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("ae_reporting_periods","old_ae_mapping","string");
    }

    void down() {
        dropColumn("ae_reporting_periods","old_ae_mapping");
    }
}