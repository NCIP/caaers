class AddNormalRange extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("ae_labs","normal_range","string");
    }

    void down() {
        dropColumn("ae_labs","normal_range");
    }
}