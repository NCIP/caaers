class AddEndDateToIND extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("investigational_new_drugs","end_date","date");
    }

    void down() {
        dropColumn("investigational_new_drugs","end_date");
    }
}