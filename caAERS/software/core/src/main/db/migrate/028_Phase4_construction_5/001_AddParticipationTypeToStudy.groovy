class StudyAddParticipationType extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("studies","participation_type","string");
    }

    void down() {
        dropColumn("studies","participation_type");
    }
}