class StudyInterventionExAEAddAttributes extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {

		addColumn('study_interventions_exp_aes', "expected", "boolean");
		
    }

    void down() {

		dropColumn('study_interventions_exp_aes', "expected");

    }
}
