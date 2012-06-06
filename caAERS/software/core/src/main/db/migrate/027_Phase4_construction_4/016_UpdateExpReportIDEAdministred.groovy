class UpdateExpRepIDEAdministred extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("ae_reports","ide_administered","boolean");

    }

    void down() {
        dropColumn("ae_reports","ide_administered");
    }
}