class AddRetiredIndicatorToPriorTherapy extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("prior_therapies","retired_indicator", "boolean", defaultValue: 0)
		
    }

    void down() {
        dropColumn("prior_therapies", "retired_indicator")
    }
}