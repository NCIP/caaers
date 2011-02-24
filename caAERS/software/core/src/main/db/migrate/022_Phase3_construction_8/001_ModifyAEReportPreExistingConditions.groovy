class ModifyPreExistingConditions extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("ae_pre_existing_conds","synced_to_cause", "boolean", defaultValue: 1)

    }

    void down() {
        dropColumn("ae_pre_existing_conds", "synced_to_cause")
    }
}