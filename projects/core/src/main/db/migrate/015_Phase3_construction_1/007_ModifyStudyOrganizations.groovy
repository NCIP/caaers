class ModifyStudyOrganizations extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("study_organizations","retired_indicator", "boolean", defaultValue: 0)
    }

    void down() {
        dropColumn("study_organizations", "retired_indicator")
    }
}