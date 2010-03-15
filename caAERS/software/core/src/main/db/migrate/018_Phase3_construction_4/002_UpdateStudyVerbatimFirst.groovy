class UpdateStudyVerbatimFirst extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
      if (databaseMatches('oracle')) {
              addColumn("studies", "verbatim_first", "boolean", nullable: true, defaultValue: 0)
      } else {
              addColumn("studies", "verbatim_first", "boolean", nullable: true, defaultValue: false)
      }
	}

	void down() {
		dropColumn("studies", "verbatim_first");
	}
}
