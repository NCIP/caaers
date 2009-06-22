class UpdateAdverseEvents extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
      addColumn("study_terms","low_level_term_id","integer", nullable: true)
      renameTable('study_terms','expected_aes');
    }

    void down() {
        renameTable('expected_aes', 'study_terms');
   		dropColumn('study_terms','low_level_term_id')
    }
}
