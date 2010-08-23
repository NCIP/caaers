class ModifyRoleName extends edu.northwestern.bioinformatics.bering.Migration {
	
    void up() {

      execute("update config_properties set name = 'Study Site Participation Administrator' where code = 'study_site_participation_administrator'");

    }

    void down() {
      // not required
    }
}