class RemoveEndDateToIND extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        dropColumn("investigational_new_drugs","end_date");
    }

    void down() {
       //not needed
    }
}