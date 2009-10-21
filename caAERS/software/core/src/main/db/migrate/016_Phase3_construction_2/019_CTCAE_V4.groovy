class CTCAE_V4 extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
      addColumn("ctc_terms","term_definition", "string")
    }


    void down() {
      dropColumn("ctc_terms", "term_definition");
    }
}
