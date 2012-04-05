class AddRetiredIndicatorToAgents extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("agents","retired_indicator", "boolean", defaultValue: 0)
		
    }

    void down() {
        dropColumn("agents", "retired_indicator")
    }
}