class OtherToxicity extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("expected_aes","other_toxicity", "string")
        addColumn("agent_terms","other_toxicity", "string")
    }

    void down() {
        dropColumn("expected_aes", "other_toxicity")
        dropColumn("agent_terms", "other_toxicity")
    }
}