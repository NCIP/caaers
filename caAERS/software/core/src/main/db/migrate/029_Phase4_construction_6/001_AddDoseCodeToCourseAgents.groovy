class AddToDoseCode extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("course_agents","dose_code","integer",defaultValue: 0, nullable: false);
        addColumn("course_agents","modified_dose_code","integer",defaultValue: 0, nullable: false);
    }

    void down() {
        dropColumn("course_agents","dose_code");
        dropColumn("course_agents","modified_dose_code");
    }
}