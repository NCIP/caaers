class AddZoneToLabValues extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("ae_labs","baseline_zone","integer", defaultValue: 0);
        addColumn("ae_labs","recovery_zone","integer", defaultValue: 0);
        addColumn("ae_labs","nadir_zone","integer", defaultValue: 0);
    }

    void down() {
        dropColumn("ae_labs","nadir_zone");
        dropColumn("ae_labs","recovery_zone");
        dropColumn("ae_labs","baseline_zone");
    }
}