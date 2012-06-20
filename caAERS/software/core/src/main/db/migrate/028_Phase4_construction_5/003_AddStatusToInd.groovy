class AddStatusToIND extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("investigational_new_drugs","status","string");
    }

    void down() {
        dropColumn("investigational_new_drugs","status");
    }
}