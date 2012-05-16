class AddRetiredIndicatorToPreexistingCondition extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("pre_existing_conditions","retired_indicator", "boolean", defaultValue: 0)

    }

    void down() {
        dropColumn("pre_existing_conditions", "retired_indicator")
    }
}