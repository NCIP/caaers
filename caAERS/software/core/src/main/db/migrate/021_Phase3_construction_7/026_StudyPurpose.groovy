class StudyPurpose extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {
        addColumn("studies", "study_purpose", "string")
    }

    void down() {
        removeColumn("studies", "study_purpose")
    }

}