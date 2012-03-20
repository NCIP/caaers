class StudyAddAETermUniqueCol extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("studies", "ae_term_unique", "boolean", nullable: false, defaultValue: 1);
    }

    void down() {
        dropColumn("studies", "ae_term_unique");
    }
}