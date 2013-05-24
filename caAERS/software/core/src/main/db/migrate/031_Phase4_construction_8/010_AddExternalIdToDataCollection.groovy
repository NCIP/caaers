class AddExternalIdToDC extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("ae_reports","external_id","string");
    }

    void down() {
        dropColumn("ae_reports","external_id");
    }
}