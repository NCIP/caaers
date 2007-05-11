/*
 An in-progress report may be saved with no reporter information.
 */
class NoMandatoryReporterFields extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        setNullable("reporters", "first_name", true)
        setNullable("reporters", "last_name", true)
        setNullable("physicians", "first_name", true)
        setNullable("physicians", "last_name", true)
    }

    void down() {
        // can't reverse b/c there could be data present
    }
}
