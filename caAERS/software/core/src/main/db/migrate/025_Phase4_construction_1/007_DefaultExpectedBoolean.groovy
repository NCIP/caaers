class DefaultExpectedBoolean extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {
		
		if (databaseMatches('oracle')) {
            execute(" update study_interventions_exp_aes set expected = 1 where expected is null ");
			execute(" update agent_terms set expected = 1 where expected is null ");
        } else if (databaseMatches('postgresql')){
            execute(" update study_interventions_exp_aes set expected = true where expected is null");
			execute(" update agent_terms set expected = true where expected is null");
        }  
    }

    void down() {

    }
}
