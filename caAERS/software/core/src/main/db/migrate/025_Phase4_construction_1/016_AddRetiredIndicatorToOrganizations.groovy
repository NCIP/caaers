class AddRetiredIndicatorToOrganizations extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("organizations","retired_indicator", "boolean", defaultValue: 0)
		
    }

    void down() {
        dropColumn("organizations", "retired_indicator")
    }
}