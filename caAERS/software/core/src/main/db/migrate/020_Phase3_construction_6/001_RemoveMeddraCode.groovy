class ModifyExpectedAdverseEventsII extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        dropColumn("expected_aes", "meddra_code")
        dropColumn("study_diseases", "meddra_code")
        dropColumn("agent_terms", "meddra_code")
        dropColumn("ae_terms", "meddra_code")
    }

    void down() {
        addColumn("ae_terms","meddra_code", "string")
        addColumn("agent_terms","meddra_code", "string")
        addColumn("study_diseases","meddra_code", "string")
        addColumn("expected_aes","meddra_code", "string")
    }
}