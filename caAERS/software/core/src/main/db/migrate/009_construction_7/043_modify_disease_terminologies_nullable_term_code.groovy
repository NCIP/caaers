class TerminologyTermCodeNullable extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        setNullable("disease_terminologies", "term_code", true)
    }

    void down() {
        setNullable("disease_terminologies", "term_code", false)
    }
}
