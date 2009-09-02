class SetEpochInCourseToNullable extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        setNullable("ae_reporting_periods", "epoch_id", true)
    }

    void down() {
        //not possible
    }
}