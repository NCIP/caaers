class AddRetiredIndicatorToDevices extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("devices","retired_indicator", "boolean", defaultValue: 0)
    }

    void down() {
        dropColumn("devices", "retired_indicator")
    }
}