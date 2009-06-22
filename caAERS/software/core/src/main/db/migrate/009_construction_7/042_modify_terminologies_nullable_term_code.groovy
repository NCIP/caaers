class TerminologyTermCodeNullable extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        setNullable("terminologies", "term_code", true)
    }

    void down() {
        setNullable("terminologies", "term_code", false)
    }
}
