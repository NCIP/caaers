class UpdateIndTypes extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        execute("update study_agents set ind_type = 2 where ind_type = 1");
        execute("update study_agents set ind_type = 2 where ind_type = 5");
    }

    void down() {

    }
}