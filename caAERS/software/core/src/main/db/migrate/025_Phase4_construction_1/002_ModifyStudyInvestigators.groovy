class ModifyStudyInvestigators extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {
       setNullable("study_investigators", "site_investigators_id", true);
    }

    void down() {
        //not possible.

    }
}
