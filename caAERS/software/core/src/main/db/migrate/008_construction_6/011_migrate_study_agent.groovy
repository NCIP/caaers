class MigrateStudyAgents extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	if (databaseMatches('postgres')) {
          execute("insert into study_agent_inds(study_agent_id, ind_id, version, grid_id) select o.id, (select id from investigational_new_drugs where ind_number = 111), o.version, o.grid_id from study_agents o");
        }
    }

    void down() {
        //no down script possible
    }
}
