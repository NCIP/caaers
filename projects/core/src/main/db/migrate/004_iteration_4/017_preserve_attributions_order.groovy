class PreserveAttributionsOrder extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("ae_attributions", "list_index", "integer", nullable: false, defaultValue: 0)
    }

    void down() {
        dropColumn("ae_attributions", "list_index")
    }
}