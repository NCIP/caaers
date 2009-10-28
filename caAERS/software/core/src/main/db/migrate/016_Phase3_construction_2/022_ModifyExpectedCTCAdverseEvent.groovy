class ModifyExpectedAdverseEventsII extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("expected_aes","verbatim", "string")
		
    }

    void down() {
        dropColumn("expected_aes", "verbatim")
    }
}