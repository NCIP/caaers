class ModifyEpochs extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("epochs","retired_indicator", "boolean", defaultValue: 0)
		
    }

    void down() {
        dropColumn("epochs", "retired_indicator")
    }
}